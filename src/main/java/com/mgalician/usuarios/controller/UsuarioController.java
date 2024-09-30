package com.mgalician.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.exception.ResourceNotFoundException;
import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.helper.TiempoRespuestaHelper;
import com.mgalician.usuarios.model.dto.ActualizarCuentaDto;
import com.mgalician.usuarios.model.dto.BaseRespuestaDto;
import com.mgalician.usuarios.model.dto.CrearUsuarioDto;
import com.mgalician.usuarios.model.dto.EliminarUsuarioPorIdDto;
import com.mgalician.usuarios.model.dto.UsuarioDto;
import com.mgalician.usuarios.service.UsuarioService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<BaseRespuestaDto> getAll() {
        long start = System.currentTimeMillis();
        List<UsuarioDto> usuarioDtos = usuarioService.obtener();

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(usuarioDtos);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseRespuestaDto> getById(@PathVariable long id) throws ResourceNotFoundException {

        long start = System.currentTimeMillis();
        UsuarioDto usuarioDto = usuarioService.obtenerPorId(id);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(usuarioDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @PostMapping()
    public ResponseEntity<BaseRespuestaDto> create(@Valid @RequestBody CrearUsuarioDto crearUsuarioDto) {
        long start = System.currentTimeMillis();
        UsuarioDto usuarioDto = usuarioService.crearUsuario(crearUsuarioDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setDatos(usuarioDto);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @DeleteMapping()
    public ResponseEntity<BaseRespuestaDto> eliminarPorId(
            @Valid @RequestBody EliminarUsuarioPorIdDto eliminarUsuarioPorIdDto) throws ResourceNotFoundException {
        long start = System.currentTimeMillis();
        usuarioService.eliminarPorId(eliminarUsuarioPorIdDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }

    @PutMapping("/{idUsuario}/cuenta")
    public ResponseEntity<BaseRespuestaDto> putCuentaByIdUsuario(@PathVariable Long idUsuario,
            @Valid @RequestBody ActualizarCuentaDto actualizarCuentaDto) {
        long start = System.currentTimeMillis();
        usuarioService.actualizarCuentaPorIdUsuario(idUsuario, actualizarCuentaDto);

        BaseRespuestaDto baseRespuestaDto = new BaseRespuestaDto();
        baseRespuestaDto.setMensaje(MensajeHelper.OPERACION_CORRECTA);
        baseRespuestaDto.setTiempoRespuesta(TiempoRespuestaHelper.obtenerPorMilisegundos(start));
        return ResponseEntity.ok(baseRespuestaDto);
    }
}
