package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    // add new employee
    void save(EmployeeDTO employeeDTO);

    // search by pages
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void enableOrDisable(Integer status, Long id);

    // get employee's infomation by ID
    Employee getById(Long id);

    // Update employee information
    void update(EmployeeDTO employeeDTO);
}
