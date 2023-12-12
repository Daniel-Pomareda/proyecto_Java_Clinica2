package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.service;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.dto.UsuarioDTO;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Usuario;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository.IUsuarioRepository;
import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.excepciones.BadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioService{
    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class);
    @Autowired
    private IUsuarioRepository iusuarioRepository;

    @Autowired
    ObjectMapper mapper;

    public UsuarioService(IUsuarioRepository iusuarioRepository) {this.iusuarioRepository = iusuarioRepository;}

    public Usuario guardarUsuario(Usuario usuario) throws BadRequestException {
        iusuarioRepository.save(usuario);
        LOGGER.info("Se guardo el usuario en local");
        return usuario;
    }
    public UsuarioDTO buscarUsuarioPorID(Integer id) throws NoSuchElementException {
        Optional<Usuario> usuario = iusuarioRepository.findById(id);
        if (usuario.isPresent()) {
            LOGGER.info("Se mostro el usuario en local");
            return mapper.convertValue(usuario.get(), UsuarioDTO.class);
        } else {
            throw new NoSuchElementException("El usuario no existe");
        }

    }
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> listaUsuariosDTO = iusuarioRepository.findAll()
                .stream()
                .map(usuario -> mapper.convertValue(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        LOGGER.info("Se mostraron los usuarios en local");
        return listaUsuariosDTO;
    }
    public Usuario modificarUsuario(Usuario usuario) throws BadRequestException {
        iusuarioRepository.save(usuario);
        LOGGER.info("Se modifico el usuario en local");
        return usuario;
    }
    public void eliminarUsuario(Integer id) {
        LOGGER.info("Se elimino el usuario en local");
        iusuarioRepository.deleteById(id);
    }
}
