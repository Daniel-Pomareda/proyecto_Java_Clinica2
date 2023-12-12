package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.OdontologoDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Odontologo;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository.IOdontologoRepository;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OdontologoServiceTest {
    //@Autowired
    @InjectMocks
    OdontologoService odontologoservice;
    @Mock
    IOdontologoRepository iodontologoRepository;

    @Test
    public void crearOdontologo() throws BadRequestException {
        //ARRANGE
        Odontologo odontologo1 = new Odontologo(1,"Javier","Perez", 1020);
        when(iodontologoRepository.findById(any())).thenReturn(Optional.of(odontologo1));
        //ACT
        OdontologoDTO odontologoDTO = odontologoservice.buscarOdontologoPorID(1);
        //ASSERT
        Assertions.assertEquals("Javier",odontologoDTO.getNombre());
    }
}
/*
        Odontologo odontologo1 = new Odontologo();
        odontologo1.setNombre("Javier");
        odontologo1.setApellido("Perez");
        odontologo1.setMatricula(7415);

        odontologoservice.guardarOdontologo(odontologo1);
        OdontologoDTO odontologoJavier = odontologoservice.buscarOdontologoPorID(1);

        assertTrue(odontologoJavier != null);
*/