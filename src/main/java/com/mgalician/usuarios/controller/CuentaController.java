package com.mgalician.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.helper.TiempoRespuestaHelper;
import com.mgalician.usuarios.model.dto.ActualizarIngresoDeCuentaPorNumeroCuentaDto;
import com.mgalician.usuarios.model.dto.BaseRespuestaDto;
import com.mgalician.usuarios.model.dto.CrearCuentaDto;
import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.model.dto.EliminarCuentaPorIdDto;
import com.mgalician.usuarios.service.CuentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping()
    public ResponseEntity<BaseRespuestaDto> getAll() {
        long start = System.currentTimeMillis();
        List<CuentaDto> cuentaDtos = cuentaService.obtenerTodos();

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(cuentaDtos);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<BaseRespuestaDto> getById(@PathVariable long numeroCuenta) {
        long start = System.currentTimeMillis();
        CuentaDto cuentaDto = cuentaService.obtenerPorNumeroCuenta(numeroCuenta);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(cuentaDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @PostMapping()
    public ResponseEntity<BaseRespuestaDto> create(@Valid @RequestBody CrearCuentaDto crearCuentaDto) {
        long start = System.currentTimeMillis();
        CuentaDto cuentaDto = cuentaService.crear(crearCuentaDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(cuentaDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @PutMapping()
    public ResponseEntity<BaseRespuestaDto> actualizarIngreso(
            @Valid @RequestBody ActualizarIngresoDeCuentaPorNumeroCuentaDto actualizarIngresoDeCuentaPorNumeroCuentaDto)
            throws ResourceNotFoundException {
        long start = System.currentTimeMillis();
        cuentaService.actualizarIngresoPorNumeroCuenta(actualizarIngresoDeCuentaPorNumeroCuentaDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @DeleteMapping()
    public ResponseEntity<BaseRespuestaDto> delete(@Valid @RequestBody EliminarCuentaPorIdDto eliminarCuentaPorIdDto)
            throws ResourceNotFoundException {
        long start = System.currentTimeMillis();
        cuentaService.eliminarPorId(eliminarCuentaPorIdDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

}
