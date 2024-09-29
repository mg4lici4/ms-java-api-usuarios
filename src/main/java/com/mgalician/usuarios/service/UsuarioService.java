package com.mgalician.usuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mgalician.usuarios.model.dto.UsuarioDto;
import com.mgalician.usuarios.model.entity.UsuarioEntity;
import com.mgalician.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> obtener() {
        return mapListUsuarioEntityToListUsuarioDto(usuarioRepository.findAll());
    }

    public UsuarioDto obtenerPorId(long id) throws NullPointerException {
        return mapUsuarioEntityToUsuarioDto(usuarioRepository.findById(id));
    }

    private List<UsuarioDto> mapListUsuarioEntityToListUsuarioDto(Iterable<UsuarioEntity> iterableUsuarioEntities) {
        List<UsuarioEntity> usuarioEntities = new ArrayList<>();
        iterableUsuarioEntities.forEach(usuarioEntities::add);

        List<UsuarioDto> userDtos = usuarioEntities.stream()
                .map(UsuarioEntity -> modelMapper.map(UsuarioEntity, UsuarioDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    private UsuarioDto mapUsuarioEntityToUsuarioDto(Optional<UsuarioEntity> usuarioEntity) {

        if (usuarioEntity.isPresent()) {
            return modelMapper.map(usuarioEntity.get(), UsuarioDto.class);
        }

        return null;

        // throw new ResourceNotFoundException("Usuario no encontrado"));
    }

}
