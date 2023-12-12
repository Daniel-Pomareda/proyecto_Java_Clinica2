package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private String rol;

}
