package com.acoder.boilerplate.controller;

import com.acoder.boilerplate.dto.CompanyRequest;
import com.acoder.boilerplate.dto.CompanyResponse;
import com.acoder.boilerplate.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    public CompanyResponse addNewCompany(@RequestBody @Validated CompanyRequest request) {
        return companyService.addNewCompany(request);
    }

    @PutMapping("{id}")
    public CompanyResponse updateExistingCompany(@PathVariable("id") String id, @Validated CompanyRequest request) {
        return companyService.updateExistingCompany(id, request);
    }

    @GetMapping
    public List<CompanyResponse> companyListData() {
        return companyService.showAllCompany();
    }

    @DeleteMapping("{id}")
    public String deleteExistingCompany(@PathVariable("id") String id) {
        companyService.deleteCompanyData(id);
        return "Delete Successfully with id = " + id;
    }
}
