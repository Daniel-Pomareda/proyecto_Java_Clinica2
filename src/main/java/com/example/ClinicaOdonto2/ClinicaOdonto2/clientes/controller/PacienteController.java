package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.controller;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.PacienteDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Paciente;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service.PacienteService;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorID(@PathVariable Integer id) {

        try {
            PacienteDTO pacienteDTO = pacienteService.buscarPacientePorID(id);
            if (pacienteDTO != null) {
                LOGGER.info("Le mostro el paciente al cliente");
                return ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
            }
        } catch (IllegalArgumentException e) {
            ResponseEntity<PacienteDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity<PacienteDTO> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @GetMapping
    public List<PacienteDTO> listarPacientes() {
        LOGGER.info("Le mostraron los pacientes al cliente ");
        return pacienteService.listarPacientes();
    }

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente o) throws BadRequestException {
        LOGGER.info("Le guardo el paciente al cliente ");
        return ResponseEntity.ok(pacienteService.guardarPaciente(o));
    }
    @PutMapping
    public ResponseEntity<Paciente> modificarPaciente(@RequestBody Paciente o) throws BadRequestException {
        LOGGER.info("Le modifico el paciente al cliente");
        return ResponseEntity.ok(pacienteService.modificarPaciente(o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        LOGGER.info("Le elimino el paciente al cliente");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
