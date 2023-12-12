package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends MongoRepository<Usuario,Integer> {
}
