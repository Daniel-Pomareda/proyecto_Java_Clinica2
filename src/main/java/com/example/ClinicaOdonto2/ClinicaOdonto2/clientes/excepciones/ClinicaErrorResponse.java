package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClinicaErrorResponse {
    private String code;
    private String message;
}
