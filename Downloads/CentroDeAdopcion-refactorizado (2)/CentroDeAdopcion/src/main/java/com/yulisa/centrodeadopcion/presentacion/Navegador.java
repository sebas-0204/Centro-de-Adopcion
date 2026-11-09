package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.modelo.Rol;
import com.yulisa.centrodeadopcion.servicio.AutenticacionServicio;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Cambia de pantalla. Ninguna vista hace "new" de otra vista: todas piden ir a una Pantalla.
 * El menú de cada rol se decide con un mapa, no con if por rol.
 */
public class Navegador {

    private static final Map<Rol, Pantalla> MENU_POR_ROL = new EnumMap<>(Map.of(
            Rol.ADMINISTRADOR, Pantalla.MENU_ADMIN,
            Rol.CLIENTE, Pantalla.MENU_CLIENTE));

    private final VentanaPrincipal ventana;
    private final AutenticacionServicio autenticacion;
    private final Map<Pantalla, VistaBase> vistas = new EnumMap<>(Pantalla.class);

    public Navegador(VentanaPrincipal ventana, AutenticacionServicio autenticacion) {
        this.ventana = Objects.requireNonNull(ventana);
        this.autenticacion = Objects.requireNonNull(autenticacion);
    }

    public void registrar(Pantalla pantalla, VistaBase vista) {
        vistas.put(pantalla, vista);
        ventana.agregarPantalla(pantalla, vista);
    }

    public void irA(Pantalla pantalla) {
        VistaBase vista = vistas.get(pantalla);
        if (vista == null) {
            throw new IllegalStateException("Pantalla no registrada: " + pantalla);
        }
        vista.alMostrar();
        ventana.mostrarPantalla(pantalla);
    }

    /** Vuelve al menú que corresponde al rol de la sesión; sin sesión, al inicio. */
    public void irAMenuDeSesion() {
        Pantalla destino = autenticacion.sesionActual()
                .map(s -> MENU_POR_ROL.get(s.rol()))
                .orElse(Pantalla.SELECCION_ROL);
        irA(destino);
    }

    public void cerrarSesion() {
        autenticacion.cerrarSesion();
        irA(Pantalla.SELECCION_ROL);
    }
}
