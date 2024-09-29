package com.mgalician.usuarios.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.model.entity.CuentaEntity;
import com.mgalician.usuarios.repository.CuentaRepository;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public CuentaDto obtenerPorNumeroCuenta(long numeroCuenta){
        return mapCuentaEntityToCuentaDto(cuentaRepository.findByNumeroCuenta(numeroCuenta));
    }

    private CuentaDto mapCuentaEntityToCuentaDto(Optional<CuentaEntity> cuentaEntity) {

        if (cuentaEntity.isPresent()) {
            return modelMapper.map(cuentaEntity.get(), CuentaDto.class);
        }

        return null;

        // throw new ResourceNotFoundException("Usuario no encontrado"));
    }
}
