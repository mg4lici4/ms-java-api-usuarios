package com.mgalician.usuarios.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mgalician.usuarios.model.dto.CrearUsuarioDto;
import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.model.dto.UsuarioDto;
import com.mgalician.usuarios.model.entity.CuentaEntity;
import com.mgalician.usuarios.model.entity.DireccionEntity;
import com.mgalician.usuarios.model.entity.UsuarioEntity;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper = new ModelMapper();

        public List<UsuarioDto> mapListUsuarioEntityToListUsuarioDto(Iterable<UsuarioEntity> iterableUsuarioEntities) {
        List<UsuarioEntity> usuarioEntities = new ArrayList<>();
        iterableUsuarioEntities.forEach(usuarioEntities::add);

        List<UsuarioDto> userDtos = usuarioEntities.stream()
                .map(UsuarioEntity -> modelMapper.map(UsuarioEntity, UsuarioDto.class))
                .collect(Collectors.toList());
        return userDtos;
    }

    public UsuarioEntity mapCrearUsuarioDtoToUsuarioEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto, UsuarioEntity.class);
    }

    public UsuarioDto mapUsuarioEntityToUsuarioDto(UsuarioEntity usuarioEntity) {
        return modelMapper.map(usuarioEntity, UsuarioDto.class);
    }

    public CuentaDto mapCuentaEntityToCuentaDto(CuentaEntity cuentaEntity) {
        return modelMapper.map(cuentaEntity, CuentaDto.class);
    }
    
    public CuentaEntity mapCrearUsuarioDtoToCuentaEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto.getCuenta(), CuentaEntity.class);
    }

    public DireccionEntity mapCrearUsuarioDtoToDireccionEntity(CrearUsuarioDto crearUsuarioDto) {
        return modelMapper.map(crearUsuarioDto.getDireccion(), DireccionEntity.class);
    }
}
