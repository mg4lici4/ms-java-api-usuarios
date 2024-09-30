package com.mgalician.usuarios.model.dto;

import lombok.Data;

@Data
public class BaseRespuestaDto {
    private String mensaje;
    private Object datos;
    private String tiempoRespuesta;
}
