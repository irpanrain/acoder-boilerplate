package com.acoder.boilerplate.service.impl;

import com.acoder.boilerplate.dto.CompanyRequest;
import com.acoder.boilerplate.dto.CompanyResponse;
import com.acoder.boilerplate.entity.Company;
import com.acoder.boilerplate.repository.CompanyRepository;
import com.acoder.boilerplate.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Transactional
    @Override
    public CompanyResponse addNewCompany(CompanyRequest request) {
        Company requestDataFromTableToRequest = Company.builder()
                .companyName(request.getCompanyName())
                .companyAddress(request.getCompanyAddress())
                .companyPhone(request.getCompanyPhone())
                .companyEmail(request.getCompanyEmail())
                .build();
        Company savedToDatabase = companyRepository.save(requestDataFromTableToRequest);

        return CompanyResponse.builder()
                .id(savedToDatabase.getId())
                .companyName(savedToDatabase.getCompanyName())
                .companyAddress(savedToDatabase.getCompanyAddress())
                .companyPhone(savedToDatabase.getCompanyPhone())
                .companyEmail(savedToDatabase.getCompanyEmail())
                .build();
    }

    @SneakyThrows
    @Transactional
    @Override
    public CompanyResponse updateExistingCompany(String id, CompanyRequest request) {
        Optional<Company> findIdExistingCompany = companyRepository.findById(id);
        if (findIdExistingCompany.isEmpty()) {
            throw new Exception("ID Company not founds");
        }
        log.info("id founds, get all data");

        findIdExistingCompany.get().setCompanyName(request.getCompanyName());
        findIdExistingCompany.get().setCompanyAddress(request.getCompanyAddress());
        findIdExistingCompany.get().setCompanyPhone(request.getCompanyPhone());
        findIdExistingCompany.get().setCompanyEmail(request.getCompanyEmail());

        Company saveExistingCompanyId = companyRepository.save(findIdExistingCompany.get());

        return CompanyResponse.builder()
                .id(saveExistingCompanyId.getId())
                .companyName(saveExistingCompanyId.getCompanyName())
                .companyAddress(saveExistingCompanyId.getCompanyAddress())
                .companyPhone(saveExistingCompanyId.getCompanyPhone())
                .companyEmail(saveExistingCompanyId.getCompanyEmail())
                .build();
    }

    @Override
    public List<CompanyResponse> showAllCompany() {
        List<Company> findAllExistingCompany = companyRepository.findAll();
        List<CompanyResponse> returnResponseFromComp = new ArrayList<>();

        findAllExistingCompany.forEach(search -> {
            CompanyResponse searchResponse = CompanyResponse.builder()
                    .id(search.getId())
                    .companyName(search.getCompanyName())
                    .companyAddress(search.getCompanyAddress())
                    .companyPhone(search.getCompanyPhone())
                    .companyEmail(search.getCompanyEmail())
                    .build();
            returnResponseFromComp.add(searchResponse);
        });
        return returnResponseFromComp;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteCompanyData(String id) {
        Optional<Company> deleteExistingCompany = companyRepository.findById(id);

        if(deleteExistingCompany.isEmpty()) {
            throw new Exception("ID Company Not Founds");
        }
        log.info("id founds, delete existing id company");
        companyRepository.deleteById(id);
    }
}
