package com.yulisa.centrodeadopcion.presentacion;

import java.util.Objects;

/** Navegación de la pantalla de inicio y de los menús de administrador y cliente. */
public class MenuController {

    private final Navegador navegador;

    public MenuController(Navegador navegador) {
        this.navegador = Objects.requireNonNull(navegador);
    }

    public void irA(Pantalla pantalla) {
        navegador.irA(pantalla);
    }

    public void cerrarSesion() {
        navegador.cerrarSesion();
    }
}
