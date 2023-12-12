package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "pacientes")
public class Paciente {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaAlta;
    private String domicilio;
}
