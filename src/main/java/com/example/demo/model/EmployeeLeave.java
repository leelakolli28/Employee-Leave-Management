
package com.example.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeLeave {

    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be greater than zero")
    private Long employeeId;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotBlank(message = "Leave type is mandatory")
    private String leaveType;

    @NotNull(message = "Leave days are required")
    @Min(value = 1, message = "Leave days should be minimum 1")
    private Integer numberOfDays;

    @NotBlank(message = "Leave reason is required")
    private String reason;
}
