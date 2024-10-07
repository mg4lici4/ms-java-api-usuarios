package com.mgalician.usuarios.model.entity;

import com.mgalician.usuarios.helper.MensajeHelper;
import com.mgalician.usuarios.validator.LengthAccount;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Data
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numeroCuenta", unique = true)
    @LengthAccount(message = MensajeHelper.ERROR_NUMERO_CUENTA_LONGITUD_10)
    private long numeroCuenta;

    @Column(name = "ingresos")
    @Min(value = 0, message = MensajeHelper.ERROR_INGRESOS_VALOR_POSITIVO)
    private float ingresos;
}
