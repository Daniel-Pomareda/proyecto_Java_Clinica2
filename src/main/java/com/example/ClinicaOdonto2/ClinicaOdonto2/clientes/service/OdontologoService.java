package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.OdontologoDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Odontologo;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository.IOdontologoRepository;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OdontologoService{

    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    private IOdontologoRepository iodontologoRepository;

    @Autowired
    ObjectMapper mapper;

    public OdontologoService(IOdontologoRepository iodontologoRepository) {
        this.iodontologoRepository = iodontologoRepository;
        this.mapper = new ObjectMapper();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) throws BadRequestException {
        // Primera letra del nombre en mayúsculas
        String inicialN = odontologo.getNombre().substring(0, 1);
        String restoN = odontologo.getNombre().substring(1);
        odontologo.setNombre(inicialN.toUpperCase() + restoN.toLowerCase());

        // Primera letra del apellido en mayúsculas
        String inicialA = odontologo.getApellido().substring(0, 1);
        String restoA = odontologo.getApellido().substring(1);
        odontologo.setApellido(inicialA.toUpperCase() + restoA.toLowerCase());

        if (odontologo.getMatricula() > 0) {
            iodontologoRepository.save(odontologo);
            LOGGER.info("Se guardo al odontologo en local");
        } else {
            throw new BadRequestException("code-01","El número de matrícula debe ser mayor a 0");
        }

        if (odontologo.getNombre().length() > 2 && odontologo.getApellido().length() > 2) {
            iodontologoRepository.save(odontologo);
            LOGGER.info("Se guardo al odontologo en local");
        } else {
            throw new BadRequestException("code-02","El Nombre y Apellido debe contener un minimo de 3 letras");
        }

        return odontologo;
    }
    public OdontologoDTO buscarOdontologoPorID(Integer id) throws NoSuchElementException {
        Optional<Odontologo> odontologo = iodontologoRepository.findById(id);
        if (odontologo.isPresent()) {
            LOGGER.info("Se mostro al odontologo en local");
            return mapper.convertValue(odontologo.get(), OdontologoDTO.class);
        } else {
            throw new NoSuchElementException("El odontologo no existe");
        }
    }
    public List<OdontologoDTO> listarOdontologos() {
            List<OdontologoDTO> listaOdontologosDTO = iodontologoRepository.findAll()
                    .stream()
                    .map(odontologo -> mapper.convertValue(odontologo, OdontologoDTO.class))
                    .collect(Collectors.toList());
        LOGGER.info("Se mostraron los odontologos en local");
            return listaOdontologosDTO;
        }
    public Odontologo modificarOdontologo(Odontologo odontologo) throws BadRequestException {
        // Primera letra del nombre en mayúsculas
        String inicialN = odontologo.getNombre().substring(0, 1);
        String restoN = odontologo.getNombre().substring(1);
        odontologo.setNombre(inicialN.toUpperCase() + restoN.toLowerCase());

        // Primera letra del apellido en mayúsculas
        String inicialA = odontologo.getApellido().substring(0, 1);
        String restoA = odontologo.getApellido().substring(1);
        odontologo.setApellido(inicialA.toUpperCase() + restoA.toLowerCase());

        if (odontologo.getMatricula() > 0 && odontologo.getMatricula() <= 10000) {
            LOGGER.info("Se modifico al odontologo en local");
            iodontologoRepository.save(odontologo);
        } else {
            throw new BadRequestException("code-01","El número de matrícula debe estar entre 0 y 10000");
        }
        return odontologo;
    }
    public void eliminarOdontologo(Integer id) {
        LOGGER.info("Se elimino al odontologo en local");
        iodontologoRepository.deleteById(id);
    }
}