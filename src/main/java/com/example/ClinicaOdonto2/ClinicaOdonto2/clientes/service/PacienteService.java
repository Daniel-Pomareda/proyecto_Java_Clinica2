package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.PacienteDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Paciente;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository.IPacienteRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PacienteService{
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    @Autowired
    private IPacienteRepository ipacienteRepository;

    @Autowired
    ObjectMapper mapper;

    public PacienteService(IPacienteRepository ipacienteRepository) {this.ipacienteRepository = ipacienteRepository;}

    public Paciente guardarPaciente(Paciente paciente) throws BadRequestException {
        // Primera letra del nombre en mayúsculas
        String inicialN = paciente.getNombre().substring(0, 1);
        String restoN = paciente.getNombre().substring(1);
        paciente.setNombre(inicialN.toUpperCase() + restoN.toLowerCase());

        // Primera letra del apellido en mayúsculas
        String inicialA = paciente.getApellido().substring(0, 1);
        String restoA = paciente.getApellido().substring(1);
        paciente.setApellido(inicialA.toUpperCase() + restoA.toLowerCase());

        if (String.valueOf(paciente.getDni()).length() == 8) {
            ipacienteRepository.save(paciente);
            LOGGER.info("Se guardo al paciente en local");
        } else {
            throw new BadRequestException("code-03","El número DNI debe ser de 8 digitos");
        }

        if (paciente.getDni() > 0) {
            ipacienteRepository.save(paciente);
            LOGGER.info("Se guardo al paciente en local");
        } else {
            throw new BadRequestException("code-04","El número de DNI debe ser mayor a 0");
        }

        if (paciente.getFechaAlta().isAfter(LocalDate.now())) {
            ipacienteRepository.save(paciente);
            LOGGER.info("Se guardo al paciente en local");
        } else {
            throw new BadRequestException("code-05","La fecha de alta no puede ser anterior a la fecha actual");
        }
        
        return paciente;
    }
    public PacienteDTO buscarPacientePorID(Integer id) throws NoSuchElementException {
        Optional<Paciente> paciente = ipacienteRepository.findById(id);
        if (paciente.isPresent()) {
            LOGGER.info("Se mostro al paciente en local");
            return mapper.convertValue(paciente.get(), PacienteDTO.class);
        } else {
            throw new NoSuchElementException("El paciente no existe");
        }
    }
    public List<PacienteDTO> listarPacientes() {
        List<PacienteDTO> listaPacientesDTO = ipacienteRepository.findAll()
                .stream()
                .map(paciente -> mapper.convertValue(paciente, PacienteDTO.class))
                .collect(Collectors.toList());
        LOGGER.info("Se mostraron los pacientes en local");
        return listaPacientesDTO;
    }
    public Paciente modificarPaciente(Paciente paciente) throws BadRequestException {
        // Primera letra del nombre en mayúsculas
        String inicialN = paciente.getNombre().substring(0, 1);
        String restoN = paciente.getNombre().substring(1);
        paciente.setNombre(inicialN.toUpperCase() + restoN.toLowerCase());

        // Primera letra del apellido en mayúsculas
        String inicialA = paciente.getApellido().substring(0, 1);
        String restoA = paciente.getApellido().substring(1);
        paciente.setApellido(inicialA.toUpperCase() + restoA.toLowerCase());
        ipacienteRepository.save(paciente);
        LOGGER.info("Se modifico al paciente en local");
        return paciente;
    }
    public void eliminarPaciente(Integer id) {
        LOGGER.info("Se elimino al paciente en local");
        ipacienteRepository.deleteById(id);
    }
}
