package com.mgalician.usuarios.service;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.repository.DireccionRepository;

@Service
public class DireccionService {

    private final DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public void eliminarPorId(long id){
        direccionRepository.deleteById(id);
    }
}
