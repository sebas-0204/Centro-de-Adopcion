package com.yulisa.centrodeadopcion.dto;

/** Datos crudos del formulario de trabajador; la edad llega como texto para validarla. */
public record RegistroTrabajadorDTO(String nombre, String edad, String documento, String contacto, String cargo) {
}
