package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.controller;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.OdontologoDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Odontologo;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service.OdontologoService;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    @Autowired
    OdontologoService odontologoservice;

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarOdontologoPorID(@PathVariable Integer id) {

        try {
            OdontologoDTO odontologoDTO = odontologoservice.buscarOdontologoPorID(id);
            if (odontologoDTO != null) {
                LOGGER.info("Le mostro el odontologo al cliente");
                return ResponseEntity.status(HttpStatus.OK).body(odontologoDTO);
            }
        } catch (IllegalArgumentException e) {
            ResponseEntity<OdontologoDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ResponseEntity<OdontologoDTO> response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping
    public List<OdontologoDTO> listarOdontologos() {
        LOGGER.info("Le mostraron los odontologos al cliente");
        return odontologoservice.listarOdontologos();
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo o) throws BadRequestException {
        LOGGER.info("Le guardo el odontologo al cliente");
        return ResponseEntity.ok(odontologoservice.guardarOdontologo(o));
    }
    @PutMapping
    public ResponseEntity<Odontologo> modificarOdontologo(@RequestBody Odontologo o) throws BadRequestException {
        LOGGER.info("Le modifico el odontologo al cliente");
        return ResponseEntity.ok(odontologoservice.modificarOdontologo(o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id) {
        odontologoservice.eliminarOdontologo(id);
        LOGGER.info("Se elimino al Odontologo");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}