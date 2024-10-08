package com.mgalician.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.helper.TiempoRespuestaHelper;
import com.mgalician.usuarios.model.dto.BaseRespuestaDto;
import com.mgalician.usuarios.model.dto.CrearDireccionDto;
import com.mgalician.usuarios.model.dto.DireccionDto;
import com.mgalician.usuarios.model.dto.EliminarDireccionPorIdDto;
import com.mgalician.usuarios.service.DireccionService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1/direccion")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping()
    public ResponseEntity<BaseRespuestaDto> getAll() {
        long start = System.currentTimeMillis();
        List<DireccionDto> direccionesDto = direccionService.obtener();

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(direccionesDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseRespuestaDto> getById(@PathVariable Long id) throws ResourceNotFoundException {
        long start = System.currentTimeMillis();
        DireccionDto direccionDto = direccionService.obtenerPorId(id);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(direccionDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @PostMapping()
    public ResponseEntity<BaseRespuestaDto> crear(
            @Valid @RequestBody CrearDireccionDto crearDireccionDto) {
        long start = System.currentTimeMillis();
        DireccionDto direccionDto = direccionService.crear(crearDireccionDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(direccionDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @DeleteMapping()
    public ResponseEntity<BaseRespuestaDto> deleteById(
            @Valid @RequestBody EliminarDireccionPorIdDto eliminarDireccionPorIdDto) {
        long start = System.currentTimeMillis();
        direccionService.eliminarPorId(eliminarDireccionPorIdDto.getId());

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }
}
