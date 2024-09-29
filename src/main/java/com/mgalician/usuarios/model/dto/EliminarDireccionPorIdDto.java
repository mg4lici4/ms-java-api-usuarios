package com.mgalician.usuarios.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EliminarDireccionPorIdDto {

    @NotNull(message = "Indicar el id a eliminar")
    private Long id;
}
