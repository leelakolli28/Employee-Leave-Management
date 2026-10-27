
package com.example.demo.exception;

public class DuplicateLeaveException extends RuntimeException {

    public DuplicateLeaveException(String message) {
        super(message);
    }
}
