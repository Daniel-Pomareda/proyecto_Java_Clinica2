package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.RegistroDeTurno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistroDeTurnoRepository extends MongoRepository<RegistroDeTurno,Integer> {
}
