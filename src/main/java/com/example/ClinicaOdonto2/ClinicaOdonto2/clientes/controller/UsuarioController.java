package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.controller;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.UsuarioDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Usuario;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service.UsuarioService;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private static final Logger LOGGER = Logger.getLogger(UsuarioController.class);
    @Autowired
    UsuarioService usuarioservice;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorID(@PathVariable Integer id) {

        try {
            UsuarioDTO usuarioDTO = usuarioservice.buscarUsuarioPorID(id);
            if (usuarioDTO != null) {
                LOGGER.info("Le mostro el usuario al cliente");
                return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
            }
        } catch (IllegalArgumentException e) {
            ResponseEntity<UsuarioDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity<UsuarioDTO> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        LOGGER.info("Le mostraron los usuarios al cliente");
        return usuarioservice.listarUsuarios();
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarOUsuario(@RequestBody Usuario o) throws BadRequestException {
        LOGGER.info("Le guardo el usuario al cliente");
        return ResponseEntity.ok(usuarioservice.guardarUsuario(o));
    }
    @PutMapping
    public ResponseEntity<Usuario> modificarUsuario(@RequestBody Usuario o) throws BadRequestException {
        LOGGER.info("Le modifico el usuario al cliente");
        return ResponseEntity.ok(usuarioservice.modificarUsuario(o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        usuarioservice.eliminarUsuario(id);
        LOGGER.info("Le elimino el usuario al cliente");
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
