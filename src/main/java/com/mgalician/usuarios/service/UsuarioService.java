package com.mgalician.usuarios.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mgalician.usuarios.model.dto.CrearUsuarioDto;
import com.mgalician.usuarios.model.dto.UsuarioDto;
import com.mgalician.usuarios.model.entity.CuentaEntity;
import com.mgalician.usuarios.model.entity.DireccionEntity;
import com.mgalician.usuarios.model.entity.UsuarioEntity;
import com.mgalician.usuarios.repository.CuentaRepository;
import com.mgalician.usuarios.repository.DireccionRepository;
import com.mgalician.usuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CuentaRepository cuentaRepository;
    private final DireccionRepository direccionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public UsuarioService(UsuarioRepository usuarioRepository,
            CuentaRepository cuentaRepository,
            DireccionRepository direccionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
        this.direccionRepository = direccionRepository;
    }

    public List<UsuarioDto> obtener() {
        return mapListUsuarioEntityToListUsuarioDto(usuarioRepository.findAll());
    }

    public UsuarioDto obtenerPorId(long id) throws NullPointerException {
        return mapUsuarioEntityToUsuarioDto(usuarioRepository.findById(id).get());
    }

    public UsuarioDto crearUsuario(CrearUsuarioDto crearUsuarioDto) {
        CuentaEntity cuentaEntity = cuentaRepository.save(mapCrearUsuarioDtoToCuentaEntity(crearUsuarioDto));
        DireccionEntity direccionEntity = direccionRepository
                .save(mapCrearUsuarioDtoToDireccionEntity(crearUsuarioDto));
        UsuarioEntity usuarioEntity = mapCrearUsuarioDtoToUsuarioEntity(crearUsuarioDto);
        usuarioEntity.setCuenta(cuentaEntity);
        usuarioEntity.setDireccion(direccionEntity);

        return mapUsuarioEntityToUsuarioDto(usuarioRepository.save(usuarioEntity));

    }

    private List<UsuarioDto> mapListUsuarioEntityToListUsuarioDto(Iterable<UsuarioEntity> iterableUsuarioEntities) {
        List<UsuarioEntity> usuarioEntities = new ArrayList<>();
        iterableUsuarioEntities.forEach(usuarioEntities::add);

        List<UsuarioDto> userDtos = usuarioEntities.stream()
                .map(UsuarioEntity -> modelMapper.map(UsuarioEntity, UsuarioDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    private UsuarioEntity mapCrearUsuarioDtoToUsuarioEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto, UsuarioEntity.class);
    }

    private CuentaEntity mapCrearUsuarioDtoToCuentaEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto, CuentaEntity.class);
    }

    private DireccionEntity mapCrearUsuarioDtoToDireccionEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto, DireccionEntity.class);
    }

    private UsuarioDto mapUsuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity) {

        return modelMapper.map(usuarioEntity, UsuarioDto.class);
    }

}
