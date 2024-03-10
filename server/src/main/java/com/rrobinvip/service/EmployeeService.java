package com.rrobinvip.service;

import com.rrobinvip.dto.EmployeeDTO;
import com.rrobinvip.dto.EmployeeLoginDTO;
import com.rrobinvip.dto.EmployeePageQueryDTO;
import com.rrobinvip.entity.Employee;
import com.rrobinvip.exception.AccountLockedException;
import com.rrobinvip.exception.AccountNotFoundException;
import com.rrobinvip.exception.PasswordErrorException;
import com.rrobinvip.result.PageResult;

public interface EmployeeService {
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
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * Adds an employee to the database with provided employee data.
     * Converts the EmployeeDTO to an Employee entity, encrypts the password,
     * sets default values for status, creation, and update times, and assigns default user IDs for creation and update.
     * Finally, it persists the Employee entity to the database.
     *
     * @param employeeDTO The data transfer object containing the employee's information to be added.
     * @return Rows been effected.
     */
    int addEmployee(EmployeeDTO employeeDTO);

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
    PageResult pageBasedEmployeeSearch(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Updates the status of an existing employee by their ID.
     *
     * @param status the new status to be set for the employee.
     * @param id     the ID of the employee whose status is to be updated.
     * @return the number of records that were updated.
     */
    int updateEmployeeStatus(Integer status, Long id);

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee to retrieve.
     * @return the Employee entity corresponding to the specified ID, or null if not found.
     */
    Employee getEmployeeByID(Long id);

    /**
     * Updates an employee's information based on the provided EmployeeDTO.
     *
     * @param employeeDTO Data Transfer Object containing updated employee information.
     * @return the number of records (rows) that were updated.
     */
    int updateEmployeeInfo(EmployeeDTO employeeDTO);
}
