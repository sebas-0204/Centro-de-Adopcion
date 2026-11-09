package com.yulisa.centrodeadopcion.dto;

/**
 * Datos del formulario de inscripción (paso 2 del registro).
 * Usuario y contraseña llegan del paso 1 (crear cuenta).
 */
public record RegistroClienteDTO(
        String nombreUsuario,
        String contrasena,
        String nombre,
        String edad,
        String documento,
        String contacto,
        boolean tieneIngresosPropios,
        boolean tieneViviendaPropia,
        boolean tuvoMascotas) {
}
