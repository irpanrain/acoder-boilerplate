package com.acoder.boilerplate.service;

import com.acoder.boilerplate.dto.EmployeeRequest;
import com.acoder.boilerplate.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse addNewEmployee(EmployeeRequest request);
    EmployeeResponse updateExistingEmployee(String id, EmployeeRequest request);
    List<EmployeeResponse> showAllEmployee();
    void deleteEmployeeData(String id);
}
