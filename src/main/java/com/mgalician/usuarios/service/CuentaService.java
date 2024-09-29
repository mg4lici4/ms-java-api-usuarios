package com.mgalician.usuarios.service;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.mapper.ObjectMapper;
import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.repository.CuentaRepository;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    
    private final ObjectMapper objectMapper;

    public CuentaService(CuentaRepository cuentaRepository,
            ObjectMapper objectMapper) {
        this.cuentaRepository = cuentaRepository;
        this.objectMapper = objectMapper;
    }

    public CuentaDto obtenerPorNumeroCuenta(long numeroCuenta) {
        return objectMapper.mapCuentaEntityToCuentaDto(cuentaRepository.findByNumeroCuenta(numeroCuenta).get());
    }

}
