/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paymentchain.product.exception;

import com.paymentchain.product.common.StandarizedApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Esta clase maneja excepciones de forma global en la aplicación REST. La
 * anotación @RestControllerAdvice permite capturar excepciones en los
 * controladores y devolver respuestas adecuadas en formato JSON sin necesidad
 * de manejarlas individualmente en cada endpoint.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Método que maneja todas las excepciones genéricas de la aplicación. La
     * anotación @ExceptionHandler(Exception.class) captura cualquier excepción
     * no manejada y devuelve una respuesta adecuada.
     *
     * @param ex La excepción lanzada en la aplicación.
     * @return ResponseEntity con el mensaje de error y el código de estado
     * HTTP.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownHostException(Exception ex) {
        StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse("TECNICO", "Input Output Error", "1000", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standarizedApiExceptionResponse);
    }
    
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<?> handleBusinessRuleException(BusinessRuleException ex) {
        StandarizedApiExceptionResponse standarizedApiExceptionResponse = new StandarizedApiExceptionResponse("BUSINESS", "Error de validacion", ex.getCode(), ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(standarizedApiExceptionResponse);
    }
}
