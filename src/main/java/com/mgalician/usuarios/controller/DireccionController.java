package com.mgalician.usuarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.model.dto.BaseRespuestaDto;
import com.mgalician.usuarios.model.dto.EliminarDireccionPorIdDto;
import com.mgalician.usuarios.service.DireccionService;

@RestController
@RequestMapping("/api/v1/direccion")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping()
    public ResponseEntity<BaseRespuestaDto> deleteById(@RequestBody EliminarDireccionPorIdDto eliminarDireccionPorIdDto) {
        direccionService.eliminarPorId(eliminarDireccionPorIdDto.getId());

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.MENSAJE_EXITO);


        return ResponseEntity.ok(baseRespuestaDto);
    }
}
