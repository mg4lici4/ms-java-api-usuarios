package com.mgalician.usuarios.model.dto;

import com.mgalician.usuarios.helper.MensajeHelper;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CrearDireccionDto {

    @NotNull(message = MensajeHelper.ERROR_CODIGO_POSTAL_NOT_NULL)
    @Size(min = 6, max = 6, message = MensajeHelper.ERROR_CODIGO_POSTAL_LONGITUD_6)
    private String codigoPostal;

    @NotNull(message = MensajeHelper.ERROR_ESTADO_NOT_NULL)
    private String estado;
}
