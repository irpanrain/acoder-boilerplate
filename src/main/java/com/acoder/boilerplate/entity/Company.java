package com.acoder.boilerplate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Company {

    @Id
    @GeneratedValue(generator = "company-system")
    @GenericGenerator(name = "company-system", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "company_name", nullable = false, length = 64)
    private String companyName;

    @Column(name = "company_address", nullable = false, length = 200)
    private String companyAddress;

    @Column(name = "company_phone", nullable = false, length = 24)
    private String companyPhone;

    @Column(name = "company_email", nullable = false, length = 64)
    private String companyEmail;
}
