
package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeLeave;
import com.example.demo.exception.LeaveNotFoundException;
import com.example.demo.exception.DuplicateLeaveException;

@Service
public class EmployeeLeaveService {

    // Temporary storage for employee leave requests
    private final List<EmployeeLeave> leaveRequests = new ArrayList<>();

    // Apply for leave
    public EmployeeLeave applyLeave(EmployeeLeave leave) {

        boolean exists = leaveRequests.stream()
                .anyMatch(l -> l.getEmployeeId()
                        .equals(leave.getEmployeeId()));

        if (exists) {
        	throw new DuplicateLeaveException(
                    "Leave request already exists for employee ID: "
                            + leave.getEmployeeId());
        }
        leaveRequests.add(leave);
        return leave;
    }

    // View all leave requests
    public List<EmployeeLeave> getAllLeaveRequests() {
        return new ArrayList<>(leaveRequests);
    }

    // Find a leave request by employee ID
    public EmployeeLeave getLeaveByEmployeeId(Long employeeId) {

        return leaveRequests.stream()
                .filter(l -> l.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new LeaveNotFoundException(
                        "Leave request not found for employee ID: " + employeeId));
    }

    // Update a leave request
    public EmployeeLeave updateLeave(
            Long employeeId, EmployeeLeave updatedLeave) {

        EmployeeLeave existingLeave =
                getLeaveByEmployeeId(employeeId);

        existingLeave.setEmployeeName(updatedLeave.getEmployeeName());
        existingLeave.setLeaveType(updatedLeave.getLeaveType());
        existingLeave.setNumberOfDays(updatedLeave.getNumberOfDays());
        existingLeave.setReason(updatedLeave.getReason());

        return existingLeave;
    }

    // Delete a leave request
    public String deleteLeave(Long employeeId) {

        EmployeeLeave leave = getLeaveByEmployeeId(employeeId);
        leaveRequests.remove(leave);

        return "Leave request deleted successfully for employee ID: "
                + employeeId;
    }

}
