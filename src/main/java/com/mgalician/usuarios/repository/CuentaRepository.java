package com.mgalician.usuarios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgalician.usuarios.model.entity.CuentaEntity;

@Repository
public interface CuentaRepository extends CrudRepository<CuentaEntity, Long>{

}
