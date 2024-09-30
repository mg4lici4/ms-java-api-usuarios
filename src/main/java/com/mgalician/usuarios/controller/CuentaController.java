package com.mgalician.usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.helper.TiempoRespuestaHelper;
import com.mgalician.usuarios.model.dto.BaseRespuestaDto;
import com.mgalician.usuarios.model.dto.CuentaDto;
import com.mgalician.usuarios.service.CuentaService;


@RestController
@RequestMapping("/api/v1/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
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
}
