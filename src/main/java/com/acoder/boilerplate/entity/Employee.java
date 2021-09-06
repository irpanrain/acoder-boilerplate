package com.acoder.boilerplate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Employee {

    @Id
    @GeneratedValue(generator = "employee-system")
    @GenericGenerator(name = "employee-system", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "employee_name", nullable = false, length = 64)
    private String employeeName;

    @Column(name = "employee_address", nullable = false, length = 200)
    private String employeeAddress;

    @Column(name = "employee_phone", nullable = false, length = 24)
    private String employeePhone;

    @Column(name = "employee_email", nullable = false, length = 64)
    private String employeeEmail;
}
