package com.mgalician.usuarios.model.dto;

import com.mgalician.usuarios.helper.MensajeHelper;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EliminarCuentaPorIdDto {
    @NotNull(message = MensajeHelper.ERROR_ID_NOT_NULL)
    private Long id;
}
