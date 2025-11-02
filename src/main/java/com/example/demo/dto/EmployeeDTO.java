package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {

    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 100, message = "name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "salary must be provided")
    @DecimalMin(value = "0.0", inclusive = false, message = "salary must be greater than 0")
    private Double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

}
