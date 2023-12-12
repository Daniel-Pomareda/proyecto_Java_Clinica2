package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.PacienteDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Paciente;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteservice;

    @Test
    public void crearPaciente() throws BadRequestException {

        Paciente paciente1 = new Paciente();
        paciente1.setNombre("Javier");
        paciente1.setApellido("Perez");
        paciente1.setDomicilio("Calle las madrigueras");
        paciente1.setDni(123);
        paciente1.setFechaAlta(LocalDate.parse("2023-06-27"));

        pacienteservice.guardarPaciente(paciente1);
        PacienteDTO pacienteJavier = pacienteservice.buscarPacientePorID(1);

        assertTrue(pacienteJavier != null);

    }
}