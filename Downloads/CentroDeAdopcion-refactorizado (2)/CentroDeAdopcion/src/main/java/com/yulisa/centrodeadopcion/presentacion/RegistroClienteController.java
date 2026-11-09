package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.RegistroClienteDTO;
import com.yulisa.centrodeadopcion.servicio.ClienteServicio;
import com.yulisa.centrodeadopcion.servicio.ValidacionException;
import java.util.Objects;

/**
 * Registro en dos pasos: (1) usuario y contraseña, (2) formulario de inscripción.
 * Entre un paso y otro guarda la cuenta pendiente; nada se persiste hasta el paso 2.
 */
public class RegistroClienteController {

    private final ClienteServicio clientes;
    private final Navegador navegador;
    private String usuarioPendiente;
    private String contrasenaPendiente;

    public RegistroClienteController(ClienteServicio clientes, Navegador navegador) {
        this.clientes = Objects.requireNonNull(clientes);
        this.navegador = Objects.requireNonNull(navegador);
    }

    public boolean continuarConCuenta(String usuario, String contrasena, MuestraMensajes vista) {
        try {
            clientes.validarCuenta(usuario, contrasena);
            usuarioPendiente = usuario.trim();
            contrasenaPendiente = contrasena;
            navegador.irA(Pantalla.INSCRIPCION);
            return true;
        } catch (ValidacionException e) {
            vista.mostrarErrores(e.getErrores());
            return false;
        }
    }

    public boolean hayCuentaPendiente() {
        return usuarioPendiente != null;
    }

    public String getUsuarioPendiente() {
        return usuarioPendiente;
    }

    public boolean finalizarInscripcion(String nombre, String edad, String documento, String contacto,
                                        boolean ingresos, boolean vivienda, boolean tuvoMascotas,
                                        MuestraMensajes vista) {
        if (!hayCuentaPendiente()) {
            vista.mostrarError("Primero cree su cuenta de usuario.");
            navegador.irA(Pantalla.REGISTRO_CUENTA);
            return false;
        }
        RegistroClienteDTO datos = new RegistroClienteDTO(
                usuarioPendiente, contrasenaPendiente,
                nombre, edad, documento, contacto, ingresos, vivienda, tuvoMascotas);
        try {
            clientes.registrar(datos);
            descartarCuentaPendiente();
            vista.mostrarInfo("¡Registro completado con éxito! Ya puede iniciar sesión.");
            navegador.irA(Pantalla.LOGIN_CLIENTE);
            return true;
        } catch (ValidacionException e) {
            vista.mostrarErrores(e.getErrores());
            return false;
        }
    }

    public void cancelar() {
        descartarCuentaPendiente();
        navegador.irA(Pantalla.LOGIN_CLIENTE);
    }

    private void descartarCuentaPendiente() {
        usuarioPendiente = null;
        contrasenaPendiente = null;
    }
}
