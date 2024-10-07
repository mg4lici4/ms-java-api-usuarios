package com.mgalician.usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.mapper.ObjectMapper;
import com.mgalician.usuarios.model.dto.ActualizarIngresoDeCuentaPorNumeroCuentaDto;
import com.mgalician.usuarios.model.dto.CrearCuentaDto;
import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.model.dto.EliminarCuentaPorIdDto;
import com.mgalician.usuarios.model.entity.CuentaEntity;
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

    public List<CuentaDto> obtenerTodos() {
        return objectMapper.mapListCuentaEntityToListCuentaDto(cuentaRepository.findAll());
    }

    public CuentaDto obtenerPorNumeroCuenta(long numeroCuenta) {
        return objectMapper.mapCuentaEntityToCuentaDto(cuentaRepository.findByNumeroCuenta(numeroCuenta).get());
    }

    public CuentaDto crear(CrearCuentaDto crearCuentaDto) {
        CuentaEntity cuentaEntity = objectMapper.mapCuentaDtoToCuentaEntity(crearCuentaDto);
        return objectMapper.mapCuentaEntityToCuentaDto(cuentaRepository.save(cuentaEntity));
    }

    public void actualizarIngresoPorNumeroCuenta(ActualizarIngresoDeCuentaPorNumeroCuentaDto actualizarIngresoDeCuentaPorNumeroCuentaDto) throws ResourceNotFoundException{
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.findByNumeroCuenta(actualizarIngresoDeCuentaPorNumeroCuentaDto.getNumeroCuenta());
        if (cuentaEntity.isPresent()) {
            CuentaEntity cuentaEnt = cuentaEntity.get();
            cuentaEnt.setIngresos(actualizarIngresoDeCuentaPorNumeroCuentaDto.getIngresos());
            cuentaRepository.save(cuentaEnt);
            return;
        }

        throw new ResourceNotFoundException(MensajeHelper.ERROR_CUENTA_NO_ENCONTRADA);
    }

    public void eliminarPorId(EliminarCuentaPorIdDto eliminarCuentaPorIdDto) throws ResourceNotFoundException{
        Optional<CuentaEntity> cuentaEntity = cuentaRepository.findById(eliminarCuentaPorIdDto.getId());
        if (cuentaEntity.isPresent()) {
            cuentaRepository.delete(cuentaEntity.get());
            return;
        }

        throw new ResourceNotFoundException(MensajeHelper.ERROR_CUENTA_NO_ENCONTRADA);
    }

}
