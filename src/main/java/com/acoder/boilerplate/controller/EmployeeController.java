package com.acoder.boilerplate.controller;

import com.acoder.boilerplate.dto.EmployeeRequest;
import com.acoder.boilerplate.dto.EmployeeResponse;
import com.acoder.boilerplate.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public EmployeeResponse addNewEmployee(@RequestBody @Validated EmployeeRequest request) {
        return employeeService.addNewEmployee(request);
    }

    @PutMapping("{id}")
    public EmployeeResponse updateExistingEmployee(@PathVariable("id") String id, @RequestBody @Validated EmployeeRequest request) {
        return employeeService.updateExistingEmployee(id, request);
    }

    @GetMapping
    public List<EmployeeResponse> employeeListData() {
        return employeeService.showAllEmployee();
    }

    @DeleteMapping("{id}")
    public String deleteExistingEmployee(@PathVariable("id") String id) {
        employeeService.deleteEmployeeData(id);
        return "Delete successfully with id "+ id;
    }
}
