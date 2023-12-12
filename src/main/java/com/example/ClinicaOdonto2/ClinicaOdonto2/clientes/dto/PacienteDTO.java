package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private Integer dni;
    private LocalDate fechaAlta;
}
