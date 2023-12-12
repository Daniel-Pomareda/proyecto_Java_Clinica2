package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OdontologoDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;
}