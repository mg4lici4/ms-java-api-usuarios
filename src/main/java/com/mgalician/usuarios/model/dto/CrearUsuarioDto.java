package com.mgalician.usuarios.model.dto;

import lombok.Data;

@Data
public class CrearUsuarioDto {
    private String nombre;
    private String apellidoPaterno;
	private String apellidoMaterno;
	private String fechaNacimiento;

    private CrearCuentaDto cuenta;
    private CrearDireccionDto direccion;
}