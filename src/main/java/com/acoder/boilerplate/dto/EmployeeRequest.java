package com.acoder.boilerplate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRequest {

    private String companyId;
    private String employeeName;
    private String employeeAddress;
    private String employeePhone;
    private String employeeEmail;

}
