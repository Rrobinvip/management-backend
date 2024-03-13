package com.rrobinvip.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.constant.PasswordConstant;
import com.rrobinvip.constant.StatusConstant;
import com.rrobinvip.dto.EmployeeDTO;
import com.rrobinvip.dto.EmployeeLoginDTO;
import com.rrobinvip.dto.EmployeePageQueryDTO;
import com.rrobinvip.entity.Employee;
import com.rrobinvip.exception.AccountLockedException;
import com.rrobinvip.exception.AccountNotFoundException;
import com.rrobinvip.exception.PasswordErrorException;
import com.rrobinvip.mapper.EmployeeMapper;
import com.rrobinvip.result.PageResult;
import com.rrobinvip.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Authenticates an employee's login based on the provided credentials.
     * This method attempts to log in an employee using a username and password contained within an EmployeeLoginDTO object. It performs the following steps:
     * 1. Retrieves the Employee record from the database based on the username.
     * 2. Validates the existence of the account, the correctness of the password, and the account status (e.g., not locked).
     *
     * @param employeeLoginDTO An object containing the login credentials (username and password) of the employee.
     * @return An Employee object (entity) representing the logged-in employee.
     * @throws AccountNotFoundException if no account is found for the provided username.
     * @throws PasswordErrorException   if the provided password does not match the password stored in the database.
     * @throws AccountLockedException   if the account is found but has been locked.
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // Query DB with username
        Employee employee = employeeMapper.getByUsername(username);

        // Handling exceptions
        if (employee == null) {
            // Account doesn't exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

        // Password comparison, using bcrypt encryption
        if (!pwEncoder.matches(password, employee.getPassword())) {
            // Incorrect password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // Account is locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // Return entity object
        return employee;
    }


    /**
     * Adds an employee to the database with provided employee data.
     * Converts the EmployeeDTO to an Employee entity, encrypts the password,
     * sets default values for status, creation, and update times, and assigns default user IDs for creation and update.
     * Finally, it persists the Employee entity to the database.
     *
     * @param employeeDTO The data transfer object containing the employee's information to be added.
     * @return Rows been effected.
     */
    public int addEmployee(EmployeeDTO employeeDTO) {
        // Convert dto to entity
        Employee employee = new Employee();

        // Object attributes copy with BeanUtils
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);

        // Encrypt password
        BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String rawPassword = employee.getPassword() == null ? PasswordConstant.DEFAULT_PASSWORD : employee.getPassword();
        employee.setPassword(pwEncoder.encode(rawPassword));

        return employeeMapper.addEmployee(employee);
    }

    /**
     * Executes a paginated search for employees based on specified criteria.
     * <p>
     * This method leverages MyBatis PageHelper to apply pagination to a dynamic SQL query for searching employees.
     * It initiates pagination with the specified page number and page size from EmployeePageQueryDTO, and executes the search query via the employeeMapper.
     * The results, along with total record count, are then encapsulated in a PageResult object for easy retrieval.
     *
     * @param employeePageQueryDTO The DTO containing pagination parameters and any search criteria such as employee name.
     * @return PageResult containing the total count of matching records and a list of Employee objects for the requested page.
     */
    public PageResult pageBasedEmployeeSearch(EmployeePageQueryDTO employeePageQueryDTO) {
        // Dynamic SQL query
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageBasedEmployeeSearch(employeePageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }


    /**
     * Updates the status of an existing employee by their ID.
     *
     * @param status the new status to be set for the employee.
     * @param id     the ID of the employee whose status is to be updated.
     * @return the number of records that were updated.
     */
    public int updateEmployeeStatus(Integer status, Long id) {
        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();

        return employeeMapper.updateEmployee(employee);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return the Employee entity corresponding to the specified ID, or null if not found.
     */
    public Employee getEmployeeByID(Long id) {
        return employeeMapper.getByID(id);
    }

    /**
     * Updates an employee's information based on the provided EmployeeDTO.
     *
     * @param employeeDTO Data Transfer Object containing updated employee information.
     * @return the number of records (rows) that were updated.
     */
    public int updateEmployeeInfo(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        return employeeMapper.updateEmployee(employee);
    }
}
