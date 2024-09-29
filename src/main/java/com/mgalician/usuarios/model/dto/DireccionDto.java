package com.mgalician.usuarios.model.dto;

import lombok.Data;

@Data
public class DireccionDto {
    private Long id;
    private String codigoPostal;
    private String estado;
}
