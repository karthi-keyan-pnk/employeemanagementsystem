package com.example.EmployeeManagementSystem.DTO;

import java.time.LocalDateTime;

public record Errorcode(LocalDateTime time, String message, int status, String errorMessage, String path) {
}
