package com.mgalician.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.mapper.ObjectMapper;
import com.mgalician.usuarios.model.dto.CrearDireccionDto;
import com.mgalician.usuarios.model.dto.DireccionDto;
import com.mgalician.usuarios.model.entity.DireccionEntity;
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

    public DireccionDto obtenerPorId(Long id) throws ResourceNotFoundException {
        Optional<DireccionEntity> direccionEntity = direccionRepository.findById(id);

        if (direccionEntity.isPresent()) {
            return objectMapper.mapDireccionEntityToDireccionDto(direccionEntity.get());
        }

        throw new ResourceNotFoundException(MensajeHelper.ERROR_DIRECCION_NO_ENCONTRADA);
    }

    public DireccionDto crear(CrearDireccionDto crearDireccionDto) {
        DireccionEntity direccionEntity = direccionRepository
                .save(objectMapper.mapCrearDireccionDtoToDirreccionEntity(crearDireccionDto));
        return objectMapper.mapDireccionEntityToDireccionDto(direccionEntity);
    }

    public void eliminarPorId(long id) {
        direccionRepository.deleteById(id);
    }
}
