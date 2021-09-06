package com.acoder.boilerplate.service;

import com.acoder.boilerplate.dto.CompanyRequest;
import com.acoder.boilerplate.dto.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse addNewCompany(CompanyRequest request);
    CompanyResponse updateExistingCompany(String id, CompanyRequest request);
    List<CompanyResponse> showAllCompany();
    void deleteCompanyData(String id);
}
