package com.mgalician.usuarios.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgalician.usuarios.model.entity.CuentaEntity;

@Repository
public interface CuentaRepository extends CrudRepository<CuentaEntity, Long>{
    Optional<CuentaEntity> findByNumeroCuenta(long numeroCuenta);
}
