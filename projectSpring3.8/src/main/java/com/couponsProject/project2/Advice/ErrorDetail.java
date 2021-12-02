package com.couponsProject.project2.Advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is build as model to handle sending errors by REST
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
    private String error;
    private String description;
}
