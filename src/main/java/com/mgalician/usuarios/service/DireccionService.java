package com.mgalician.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.mapper.ObjectMapper;
import com.mgalician.usuarios.model.dto.DireccionDto;
import com.mgalician.usuarios.repository.DireccionRepository;

@Service
public class DireccionService {

    private final DireccionRepository direccionRepository;

    private final ObjectMapper objectMapper;

    public DireccionService(DireccionRepository direccionRepository, ObjectMapper objectMapper) {
        this.direccionRepository = direccionRepository;
        this.objectMapper = objectMapper;
    }

    public List<DireccionDto> obtener() {
        return objectMapper.mapListDireccionEntityToListDireccionDto(direccionRepository.findAll());
    }

    public void eliminarPorId(long id) {
        direccionRepository.deleteById(id);
    }
}
