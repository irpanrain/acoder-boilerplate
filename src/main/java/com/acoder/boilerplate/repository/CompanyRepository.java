package com.acoder.boilerplate.repository;

import com.acoder.boilerplate.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
