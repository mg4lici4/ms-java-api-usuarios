package com.mgalician.usuarios.model.dto;

import com.mgalician.usuarios.helper.MensajeHelper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CrearUsuarioDto {

    @NotNull(message = MensajeHelper.ERROR_NOMBRE_NOT_NULL)
    private String nombre;

    @NotNull(message = MensajeHelper.ERROR_APELLIDO_PATERNO_NOT_NULL)
    private String apellidoPaterno;

    private String apellidoMaterno;

    @NotNull(message = MensajeHelper.ERROR_FECHA_NACIMIENTO_NOT_NULL)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = MensajeHelper.ERROR_FECHA_NACIMIENTO_FORMATO)
    private String fechaNacimiento;

    @Valid
    private CrearCuentaDto cuenta;

    @Valid
    private CrearDireccionDto direccion;
}