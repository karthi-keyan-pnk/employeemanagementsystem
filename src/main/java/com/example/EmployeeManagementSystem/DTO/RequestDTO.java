package com.example.EmployeeManagementSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;

public record RequestDTO(String empName,
                         @Email(message = "Invalid Email formate")
                         @NotBlank(message = "cannot be null")
                          String email,

                         String deptName,

                         Integer age ,
                         @Positive(message = "Salary must be positive")
                         Double salary) {
}
