package com.acoder.boilerplate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

    private String id;
    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;

}
