package com.yulisa.centrodeadopcion.dto;

import com.yulisa.centrodeadopcion.modelo.TipoAnimal;
import java.time.LocalDate;

/**
 * Datos crudos del formulario de animal. La edad llega como texto para que
 * el servicio la valide y explique el error (sin NumberFormatException en la vista).
 * El tipo puede ser null si el usuario no eligió ninguno.
 */
public record RegistroAnimalDTO(
        TipoAnimal tipo,
        String nombre,
        String edad,
        String raza,
        LocalDate fechaIngreso,
        String descripcion,
        boolean entrenado,
        boolean protector,
        boolean tieneDiscapacidad,
        String cuidadosEspeciales) {
}
