package com.rrobinvip.mapper;

import com.github.pagehelper.Page;
import com.rrobinvip.dto.EmployeePageQueryDTO;
import com.rrobinvip.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * Retrieves an employee from the database based on the username.
     * This method is annotated with @Select to indicate it's a MyBatis mapper method that executes a SQL query. The query selects all columns from the 'employee' table where the 'username' column matches the specified username.
     *
     * @param username The username of the employee to retrieve.
     * @return An Employee object containing the data of the employee with the specified username. Returns null if no employee with the given username is found.
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * Search employee by ID.
     *
     * @param id Employee id
     * @return Employee Entity
     */
    @Select("select * from employee where id = #{id}")
    Employee getByID(Long id);

    /**
     * Add employee with given entity.
     *
     * @param employee Employee entity
     * @return rows affected
     */
    @Insert("insert into employee (name, username, password, phone, sex, id_number, status,  create_time, update_time, create_user, update_user)" +
            "values " +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    int addEmployee(Employee employee);

    /**
     * Retrieved page based employee query.
     *
     * @param employeePageQueryDTO employeePageQuery data transfer object
     * @return Page<Employee>
     */
    Page<Employee> pageBasedEmployeeSearch(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * Update user info, based on what is not null in the given entity.
     *
     * @param employee Employee entity
     * @return rows affected
     */
    int updateEmployee(Employee employee);
}
