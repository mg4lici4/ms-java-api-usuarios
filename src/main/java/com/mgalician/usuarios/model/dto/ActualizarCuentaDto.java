package com.mgalician.usuarios.model.dto;

import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.validator.LengthAccount;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarCuentaDto {
    @NotNull(message = MensajeHelper.ERROR_NUMERO_CUENTA_NOT_NULL)
    @LengthAccount(message = MensajeHelper.ERROR_NUMERO_CUENTA_LONGITUD_10)
    private Long numeroCuenta;

    @NotNull(message = MensajeHelper.ERROR_INGRESOS_NOT_NULL)
    @Min(value = 0, message = MensajeHelper.ERROR_INGRESOS_VALOR_POSITIVO)
    private float ingresos;
}
