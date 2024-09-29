package com.mgalician.usuarios.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mgalician.usuarios.model.entity.DireccionEntity;

@Repository
public interface DireccionRepository extends CrudRepository<DireccionEntity, Long>{

}
