package com.acoder.boilerplate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyRequest {

    private String companyName;
    private String companyAddress;
    private String companyPhone;
    private String companyEmail;
}
