package com.mgalician.usuarios.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.mapper.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
            CuentaRepository cuentaRepository,
            DireccionRepository direccionRepository,
            ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.cuentaRepository = cuentaRepository;
        this.direccionRepository = direccionRepository;
        this.objectMapper = objectMapper;
    }

    public List<UsuarioDto> obtener() {
        return objectMapper.mapListUsuarioEntityToListUsuarioDto(usuarioRepository.findAll());
    }

    public UsuarioDto obtenerPorId(long id) throws NullPointerException {
        return objectMapper.mapUsuarioEntityToUsuarioDto(usuarioRepository.findById(id).get());
    }

    public UsuarioDto crearUsuario(CrearUsuarioDto crearUsuarioDto) {
        CuentaEntity cuentaEntity = cuentaRepository
                .save(objectMapper.mapCrearUsuarioDtoToCuentaEntity(crearUsuarioDto));
        DireccionEntity direccionEntity = direccionRepository
                .save(objectMapper.mapCrearUsuarioDtoToDireccionEntity(crearUsuarioDto));
        UsuarioEntity usuarioEntity = objectMapper.mapCrearUsuarioDtoToUsuarioEntity(crearUsuarioDto);
        usuarioEntity.setCuenta(cuentaEntity);
        usuarioEntity.setDireccion(direccionEntity);

        return objectMapper.mapUsuarioEntityToUsuarioDto(usuarioRepository.save(usuarioEntity));

    }
}
