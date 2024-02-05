package com.team4.payroll.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    
    private String code;
    private String message;
    private Object details;
}
