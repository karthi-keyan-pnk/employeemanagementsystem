package com.example.EmployeeManagementSystem.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String empName;
    @Column(unique = true, nullable = false)

    private String email;
    @Column(nullable = false)
    private String deptName;
    @Column(nullable = false)
    private Integer age;
    private Double salary;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
