package com.rrobinvip.controller.admin;

import com.rrobinvip.constant.JwtClaimsConstant;
import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.dto.EmployeeDTO;
import com.rrobinvip.dto.EmployeeLoginDTO;
import com.rrobinvip.dto.EmployeePageQueryDTO;
import com.rrobinvip.entity.Employee;
import com.rrobinvip.properties.JwtProperties;
import com.rrobinvip.result.PageResult;
import com.rrobinvip.result.Result;
import com.rrobinvip.service.EmployeeService;
import com.rrobinvip.utils.JwtUtil;
import com.rrobinvip.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Employee controller
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee login and logout")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Processes the employee login request by authenticating their credentials and generates a JWT token for session management.
     * This method accepts an EmployeeLoginDTO (Data transfer) object containing the login credentials, performs authentication,
     * and returns an EmployeeLoginVO (Value) object with login details including a JWT token if authentication is successful.
     *
     * @param employeeLoginDTO An EmployeeLoginDTO object containing the employee's login credentials.
     * @return A Result<EmployeeLoginVO> object containing the login details of the employee including a JWT token upon successful authentication.
     */
    @ApiOperation(value = "Handle employee login")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee login：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //Generate JWT token after successful login
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // builder method only available with builder annotation.
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * Processes the user logout request.
     * This endpoint invalidates the current user's session to effectively log them out of the application.
     *
     * @return A Result object indicating the success of the operation with message
     */
    @ApiOperation(value = "Handle employee logout")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * Adds a new employee to the system.
     *
     * @param employeeDTO The data transfer object containing the new employee's information.
     * @return A Result object indicating the success of the operation.
     */
    @ApiOperation(value = "Handle adding new employee")
    @PostMapping
    public Result addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Adding new employee, {}", employeeDTO);
        return employeeService.addEmployee(employeeDTO) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * Update employee info.
     *
     * @param employeeDTO The data transfer object containing the new employee's information.
     * @return A Result object indicating the success of the operation.
     */
    @ApiOperation(value = "Edit employee info")
    @PutMapping
    public Result updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Updating employee info..");
        return employeeService.updateEmployeeInfo(employeeDTO) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * Searches for employees based on page query criteria.
     *
     * @param employeePageQueryDTO the data transfer object containing search criteria such as employee name, page number, and page size.
     * @return A Result object indicating the success of the operation and the pageResult.
     */
    @ApiOperation(value = "Page based employee search")
    @GetMapping("/page")
    public Result<PageResult> searchEmployee(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("Searching employee info, employee name: {}", employeePageQueryDTO.getName());
        PageResult pageResult = employeeService.pageBasedEmployeeSearch(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * Update the status of a employee, 1 as available 2 as non-available.
     *
     * @param status status to be set
     * @param id     employee id to be set
     * @return A Result object indicating the success of the operation.
     */
    @ApiOperation(value = "Enable or disable employee")
    @PostMapping("/status/{status}")
    public Result setEmployeeStatus(@PathVariable Integer status, Long id) {
        log.info("Enable or disable employee.. status: {}, employee id: {}", status, id);

        return employeeService.updateEmployeeStatus(status, id) > 0
                ? Result.success()
                : Result.error(MessageConstant.NO_RECORDS_EFFECTED);
    }

    /**
     * @param id employee id to be search
     * @return A Result object indicating the success of the operation, contains Employee entity.
     */
    @ApiOperation(value = "Search employee by ID")
    @GetMapping("/{id}")
    public Result<Employee> getByEmployeeID(@PathVariable Long id) {
        log.info("Searching employee by ID, ID: {}", id);
        return Result.success(employeeService.getEmployeeByID(id));
    }


}
