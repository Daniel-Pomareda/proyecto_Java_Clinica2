package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.controller;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.RegistroDeTurnoDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.RegistroDeTurno;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service.RegistroDeTurnoService;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservaDeTurnos")
public class RegistroDeTurnoController {
    private static final Logger LOGGER = Logger.getLogger(RegistroDeTurnoController.class);
    @Autowired
    RegistroDeTurnoService registroDeTurnoService;

    @GetMapping("/{id}")
    public ResponseEntity<RegistroDeTurnoDTO> buscarRegistroDeTurnoPorID(@PathVariable Integer id) {

        try {
            RegistroDeTurnoDTO registroDeTurnoDTO = registroDeTurnoService.buscarRegistroDeTurnoPorID(id);
            if (registroDeTurnoDTO != null) {
                LOGGER.info("Le mostro el turno al cliente");
                return ResponseEntity.status(HttpStatus.OK).body(registroDeTurnoDTO);
            }
        } catch (IllegalArgumentException e) {
            ResponseEntity<RegistroDeTurnoDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity<RegistroDeTurnoDTO> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }
    @GetMapping
    public List<RegistroDeTurnoDTO> listarRegistroDeTurnos() {
        LOGGER.info("Le mostraron los Turnos al cliente");
        return registroDeTurnoService.listarRegistroDeTurnos();
    }

    @PostMapping
    public ResponseEntity<RegistroDeTurno> guardarRegistroDeTurno(@RequestBody RegistroDeTurno o) throws BadRequestException {
        LOGGER.info("Le guardo el Turno al cliente");
        return ResponseEntity.ok(registroDeTurnoService.guardarRegistroDeTurno(o));
    }
    @PutMapping
    public ResponseEntity<RegistroDeTurno> modificarRegistroDeTurno(@RequestBody RegistroDeTurno o) throws BadRequestException {
        LOGGER.info("Le modifico el Turno al cliente");
        return ResponseEntity.ok(registroDeTurnoService.modificarRegistroDeTurno(o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRegistroDeTurno(@PathVariable Integer id) {
        registroDeTurnoService.eliminarRegistroDeTurno(id);
        LOGGER.info("Le elimino el Turno al cliente");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
