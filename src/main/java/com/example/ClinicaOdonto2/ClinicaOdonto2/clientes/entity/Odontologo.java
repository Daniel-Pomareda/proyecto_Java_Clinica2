package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "odontologos")
public class Odontologo {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer matricula;
}