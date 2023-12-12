package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;

}
