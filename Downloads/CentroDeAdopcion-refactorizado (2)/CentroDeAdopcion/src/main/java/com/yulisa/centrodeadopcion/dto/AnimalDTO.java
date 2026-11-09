package com.yulisa.centrodeadopcion.dto;

import com.yulisa.centrodeadopcion.modelo.Animal;
import java.time.LocalDate;
import java.util.UUID;

/** Fila de las tablas de animales. Lleva el id para adoptar sin depender del nombre. */
public record AnimalDTO(
        UUID id,
        String nombre,
        int edad,
        String raza,
        String tipo,
        LocalDate fechaIngreso,
        boolean adoptado,
        String descripcion,
        String descripcionCuidados) {

    public static AnimalDTO desde(Animal a) {
        return new AnimalDTO(
                a.getId(),
                a.getNombre(),
                a.getEdad(),
                a.getRaza(),
                a.getTipo().getEtiqueta(),
                a.getFechaIngreso(),
                a.isAdoptado(),
                a.getDescripcion(),
                a.descripcionCuidados());
    }
}
