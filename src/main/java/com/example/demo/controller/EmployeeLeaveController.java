
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.EmployeeLeave;
import com.example.demo.service.EmployeeLeaveService;

@RestController
@RequestMapping("/leaves")
public class EmployeeLeaveController {

    private final EmployeeLeaveService employeeLeaveService;

    public EmployeeLeaveController(
            EmployeeLeaveService employeeLeaveService) {
        this.employeeLeaveService = employeeLeaveService;
    }

    // Apply for leave
    @PostMapping
    public ResponseEntity<EmployeeLeave> applyLeave(
            @Valid @RequestBody EmployeeLeave leave) {

        EmployeeLeave savedLeave =
                employeeLeaveService.applyLeave(leave);

        return new ResponseEntity<>(
                savedLeave, HttpStatus.CREATED);
    }

    // View all leave requests
    @GetMapping("/getall")
    public List<EmployeeLeave> getAllLeaveRequests() {
        return employeeLeaveService.getAllLeaveRequests();
    }

    // Find leave request by employee ID
    @GetMapping("/byid/{employeeId}")
    public EmployeeLeave getLeaveByEmployeeId(
            @PathVariable Long employeeId) {

        return employeeLeaveService
                .getLeaveByEmployeeId(employeeId);
    }

    // Update leave request
    @PutMapping("/update/{employeeId}")
    public EmployeeLeave updateLeave(
            @PathVariable Long employeeId,
            @Valid @RequestBody EmployeeLeave leave) {

        return employeeLeaveService
                .updateLeave(employeeId, leave);
    }

    // Delete leave request
    @DeleteMapping("/delete/{employeeId}")
    public String deleteLeave(
            @PathVariable Long employeeId) {

        return employeeLeaveService
                .deleteLeave(employeeId);
    }

   
}
