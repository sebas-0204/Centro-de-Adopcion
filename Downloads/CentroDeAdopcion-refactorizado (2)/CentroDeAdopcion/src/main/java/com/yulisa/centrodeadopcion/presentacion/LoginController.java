package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.modelo.Rol;
import com.yulisa.centrodeadopcion.servicio.AutenticacionServicio;
import com.yulisa.centrodeadopcion.servicio.ValidacionException;
import java.util.Objects;

/** Login de administrador y de cliente (misma lógica, distinto rol). */
public class LoginController {

    private final AutenticacionServicio autenticacion;
    private final Navegador navegador;

    public LoginController(AutenticacionServicio autenticacion, Navegador navegador) {
        this.autenticacion = Objects.requireNonNull(autenticacion);
        this.navegador = Objects.requireNonNull(navegador);
    }

    /** @return true si inició sesión (la vista entonces limpia sus campos). */
    public boolean iniciarSesion(Rol rol, String usuario, String contrasena, MuestraMensajes vista) {
        try {
            autenticacion.iniciarSesion(rol, usuario, contrasena);
            navegador.irAMenuDeSesion();
            return true;
        } catch (ValidacionException e) {
            vista.mostrarErrores(e.getErrores());
            return false;
        }
    }

    public void irARegistro() {
        navegador.irA(Pantalla.REGISTRO_CUENTA);
    }

    public void volverAlInicio() {
        navegador.irA(Pantalla.SELECCION_ROL);
    }
}
