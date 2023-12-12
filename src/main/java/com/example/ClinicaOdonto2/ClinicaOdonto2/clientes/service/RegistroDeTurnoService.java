package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.RegistroDeTurnoDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.RegistroDeTurno;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository.IRegistroDeTurnoRepository;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistroDeTurnoService {
    private static final Logger LOGGER = Logger.getLogger(RegistroDeTurnoService.class);
    @Autowired
    private IRegistroDeTurnoRepository iregistroDeTurnoRepository;

    @Autowired
    ObjectMapper mapper;

    public RegistroDeTurnoService(IRegistroDeTurnoRepository iregistroDeTurnoRepository) { this.iregistroDeTurnoRepository = iregistroDeTurnoRepository;}

    public RegistroDeTurno guardarRegistroDeTurno(RegistroDeTurno registroDeTurno) throws BadRequestException {

        LocalTime horaInicio = LocalTime.of(8, 0);
        LocalTime horaFin = LocalTime.of(17, 0);
        if (registroDeTurno.getHora().isAfter(horaInicio) && registroDeTurno.getHora().isBefore(horaFin)) {
        iregistroDeTurnoRepository.save(registroDeTurno);
        LOGGER.info("Se guardo el turno en local");
        } else {
            throw new BadRequestException("code-06","Solo se puede programar citas desde las 8am hasta las 5pm");
        }

        if (registroDeTurno.getFecha().isAfter(LocalDate.now())) {
            iregistroDeTurnoRepository.save(registroDeTurno);
            LOGGER.info("Se guardo al turno en local");
        } else {
            throw new BadRequestException("code-07","La fecha de la cita no puede ser anterior a la fecha actual");
        }

        return registroDeTurno;
    }

    public RegistroDeTurnoDTO buscarRegistroDeTurnoPorID(Integer id) throws NoSuchElementException {
        Optional<RegistroDeTurno> registroDeTurno = iregistroDeTurnoRepository.findById(id);
        if (registroDeTurno.isPresent()) {
            LOGGER.info("Se mostro el turno en local");
            return mapper.convertValue(registroDeTurno.get(), RegistroDeTurnoDTO.class);
        } else {
            throw new NoSuchElementException("El Registro De Turno no existe");
        }
    }
    public List<RegistroDeTurnoDTO> listarRegistroDeTurnos() {
        List<RegistroDeTurnoDTO> listaRegistroDeTurnosDTO = iregistroDeTurnoRepository.findAll()
                .stream()
                .map(paciente -> mapper.convertValue(paciente, RegistroDeTurnoDTO.class))
                .collect(Collectors.toList());
        LOGGER.info("Se mostraron los turnos en local");
        return listaRegistroDeTurnosDTO;
    }

    public RegistroDeTurno modificarRegistroDeTurno(RegistroDeTurno registroDeTurno) throws BadRequestException {
        iregistroDeTurnoRepository.save(registroDeTurno);
        LOGGER.info("Se modifico el turno en local");
        return registroDeTurno;
    }

    public void eliminarRegistroDeTurno(Integer id) {
        LOGGER.info("Se elimino el turno en local");
        iregistroDeTurnoRepository.deleteById(id);
    }
}
