package com.mgalician.usuarios.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgalician.usuarios.model.dto.UsuarioDto;
import com.mgalician.usuarios.service.UsuarioService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDto>> getAll() {
        return  ResponseEntity.ok(usuarioService.obtener());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }
}
