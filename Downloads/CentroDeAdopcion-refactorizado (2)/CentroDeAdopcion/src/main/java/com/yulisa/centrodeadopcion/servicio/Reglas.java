package com.yulisa.centrodeadopcion.servicio;

import java.util.List;

/** Comprobaciones de formato compartidas por los servicios (uso interno del paquete). */
final class Reglas {

    private Reglas() {
    }

    static boolean vacio(String valor) {
        return valor == null || valor.isBlank();
    }

    static void requerido(String valor, String campo, List<String> errores) {
        if (vacio(valor)) {
            errores.add("El campo " + campo + " es obligatorio.");
        }
    }

    /** Devuelve la edad o -1 si no es válida (y agrega el mensaje de error). */
    static int edadEnRango(String texto, int min, int max, List<String> errores) {
        if (vacio(texto)) {
            errores.add("El campo edad es obligatorio.");
            return -1;
        }
        try {
            int edad = Integer.parseInt(texto.trim());
            if (edad < min || edad > max) {
                errores.add("La edad debe estar entre " + min + " y " + max + ".");
                return -1;
            }
            return edad;
        } catch (NumberFormatException e) {
            errores.add("La edad debe ser un número entero.");
            return -1;
        }
    }

    static void lanzarSiHayErrores(List<String> errores) throws ValidacionException {
        if (!errores.isEmpty()) {
            throw new ValidacionException(errores);
        }
    }
}
