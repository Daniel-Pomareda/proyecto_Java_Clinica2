package com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.repository;

import com.example.ClinicaOdonto2.ClinicaOdonto2.clientes.entity.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends MongoRepository<Paciente,Integer> {
}
