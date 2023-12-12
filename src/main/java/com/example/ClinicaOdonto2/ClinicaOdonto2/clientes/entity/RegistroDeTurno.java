package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "registroDeTurnos")
public class RegistroDeTurno {
    @Id
    private Integer id;
    @Field
    @DBRef
    private Odontologo odontologo;
    @Field
    @DBRef
    private Paciente paciente;
    private LocalDate fecha;
    private LocalTime hora;

}
