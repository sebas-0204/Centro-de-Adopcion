package com.yulisa.centrodeadopcion.modelo;

import java.time.LocalDate;

/**
 * Datos ya validados para construir un animal mediante {@link TipoAnimal#crear(DatosAnimal)}.
 * Cada especie usa solo los campos que le corresponden.
 */
public record DatosAnimal(
        String nombre,
        int edad,
        String raza,
        LocalDate fechaIngreso,
        String descripcion,
        boolean entrenado,
        boolean protector,
        boolean tieneDiscapacidad,
        String cuidadosEspeciales) {
}
