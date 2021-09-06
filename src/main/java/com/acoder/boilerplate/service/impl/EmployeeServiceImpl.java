package com.acoder.boilerplate.service.impl;

import com.acoder.boilerplate.dto.CompanyResponse;
import com.acoder.boilerplate.dto.EmployeeRequest;
import com.acoder.boilerplate.dto.EmployeeResponse;
import com.acoder.boilerplate.entity.Company;
import com.acoder.boilerplate.entity.Employee;
import com.acoder.boilerplate.repository.CompanyRepository;
import com.acoder.boilerplate.repository.EmployeeRepository;
import com.acoder.boilerplate.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {

    private CompanyRepository companyRepository;
    private EmployeeRepository employeeRepository;

    private Employee transformDataFromModelToRequest(EmployeeRequest request, Company company) {
        return Employee.builder()
                .company(company)
                .employeeName(request.getEmployeeName())
                .employeeAddress(request.getEmployeeAddress())
                .employeePhone(request.getEmployeePhone())
                .employeeEmail(request.getEmployeeEmail())
                .build();
    }

    private EmployeeResponse transformDataFromResponseToModel(Employee employee, CompanyResponse response) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .companyId(response)
                .employeeName(employee.getEmployeeName())
                .employeeAddress(employee.getEmployeeAddress())
                .employeePhone(employee.getEmployeePhone())
                .employeeEmail(employee.getEmployeeEmail())
                .build();
    }

    private CompanyResponse transformDataFromCompanyToResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyAddress(company.getCompanyAddress())
                .companyPhone(company.getCompanyPhone())
                .companyEmail(company.getCompanyEmail())
                .build();
    }

    @SneakyThrows
    @Transactional
    @Override
    public EmployeeResponse addNewEmployee(EmployeeRequest request) {
        Optional<Company> findExistingCompanyId = companyRepository.findById(request.getCompanyId());
        log.info("search existing company id");

        if (findExistingCompanyId.isEmpty()) {
            throw new Exception("Company not founds");
        }
        log.info("Company founds, get data company");
        Company foundCompanyId = findExistingCompanyId.get();
        Employee employeeRequest = transformDataFromModelToRequest(request, foundCompanyId);
        Employee responseSaved = employeeRepository.save(employeeRequest);

        CompanyResponse responseDataCompany = transformDataFromCompanyToResponse(foundCompanyId);
        return transformDataFromResponseToModel(responseSaved, responseDataCompany);
    }

    @SneakyThrows
    @Transactional
    @Override
    public EmployeeResponse updateExistingEmployee(String id, EmployeeRequest request) {
        Optional<Company> findExistingCompanyId = companyRepository.findById(request.getCompanyId());
        log.info("search existing company id");
        if(findExistingCompanyId.isEmpty()) {
            throw new Exception("Company not founds");
        }
        log.info("company founds, get data company");
        Company foundCompanyId = findExistingCompanyId.get();

        Optional<Employee> findEmployeeExistingId = employeeRepository.findById(id);
        log.info("search id existing Employee");

        if(findEmployeeExistingId.isEmpty()) {
            throw new Exception("ID employee not founds");
        }

        log.info("id company founds, get data Employee");
        findEmployeeExistingId.get().setEmployeeName(request.getEmployeeName());
        findEmployeeExistingId.get().setEmployeeAddress(request.getEmployeeAddress());
        findEmployeeExistingId.get().setEmployeePhone(request.getEmployeePhone());
        findEmployeeExistingId.get().setEmployeeEmail(request.getEmployeeEmail());

        Employee savedDataEmployee = employeeRepository.save(findEmployeeExistingId.get());

        CompanyResponse responseFromCompany = transformDataFromCompanyToResponse(foundCompanyId);

        return transformDataFromResponseToModel(savedDataEmployee, responseFromCompany);
    }

    @SneakyThrows
    @Override
    public List<EmployeeResponse> showAllEmployee() {
        List<Employee> findAllExistingEmployee = employeeRepository.findAll();
        List<EmployeeResponse> returnResponseEmployee = new ArrayList<>();

        findAllExistingEmployee.forEach(searching -> {
            CompanyResponse getResponseCompany = transformDataFromCompanyToResponse(searching.getCompany());
            EmployeeResponse getResponseEmployee = transformDataFromResponseToModel(searching, getResponseCompany);

            returnResponseEmployee.add(getResponseEmployee);
        });
        return returnResponseEmployee;
    }

    @SneakyThrows
    @Transactional
    @Override
    public void deleteEmployeeData(String id) {
        Optional<Employee> findExistingEmployeeId = employeeRepository.findById(id);
        if(findExistingEmployeeId.isEmpty()) {
            throw new Exception("ID employee not founds");
        }
        log.info("id founds, execute to delete id");
        employeeRepository.deleteById(id);
    }

}
