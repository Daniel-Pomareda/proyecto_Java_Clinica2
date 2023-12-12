package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Odontologo;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Paciente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistroDeTurnoDTO {
    private Integer id;
    private Odontologo odontologo;
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;

}
