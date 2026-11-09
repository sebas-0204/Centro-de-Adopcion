package com.yulisa.centrodeadopcion.modelo;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

/**
 * Usuario y contraseña de una cuenta.
 * El nombre de usuario se normaliza a minúsculas: "Juan" y "juan" son la misma cuenta.
 */
public record Credenciales(String nombreUsuario, String contrasena) implements Serializable {

    public Credenciales {
        Objects.requireNonNull(nombreUsuario, "nombreUsuario");
        Objects.requireNonNull(contrasena, "contrasena");
        nombreUsuario = normalizar(nombreUsuario);
    }

    public static String normalizar(String nombreUsuario) {
        return nombreUsuario.trim().toLowerCase(Locale.ROOT);
    }

    /** Comprueba si la contraseña escrita coincide con la de la cuenta. */
    public boolean coincide(String contrasenaEscrita) {
        return contrasena.equals(contrasenaEscrita);
    }
}
