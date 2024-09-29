package com.mgalician.usuarios.model.dto;

import lombok.Data;

@Data
public class UsuarioDto {

    private long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String fechaNacimiento;
    
    private CuentaDto cuenta;
    private DireccionDto direccion;

}
