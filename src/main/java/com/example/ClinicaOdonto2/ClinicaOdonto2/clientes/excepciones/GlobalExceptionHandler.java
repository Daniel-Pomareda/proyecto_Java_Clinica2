package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> emitirIllegalArgumentException(IllegalArgumentException e) {
        e.printStackTrace();
        LOGGER.error("mensaje de error de illegal -->" + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ClinicaErrorResponse> emitirBadRequestException(BadRequestException e) {
        e.printStackTrace();
        LOGGER.error("mensaje de error de BadRequestException -->" + e.getCode() + " , " + e.getMessage());
        ClinicaErrorResponse errorResponse = new ClinicaErrorResponse(e.getCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
