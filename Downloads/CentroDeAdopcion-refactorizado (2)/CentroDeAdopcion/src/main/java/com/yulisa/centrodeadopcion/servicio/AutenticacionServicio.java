package com.yulisa.centrodeadopcion.servicio;

import com.yulisa.centrodeadopcion.modelo.Cliente;
import com.yulisa.centrodeadopcion.modelo.Credenciales;
import com.yulisa.centrodeadopcion.modelo.Rol;
import com.yulisa.centrodeadopcion.repositorio.RepositorioClientes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Login de administrador y cliente. La sesión solo se abre después de verificar la contraseña.
 * Las credenciales del administrador llegan por constructor (desde app.properties).
 */
public class AutenticacionServicio {

    private static final String CREDENCIALES_INVALIDAS = "Usuario o contraseña incorrectos.";

    private final RepositorioClientes clientes;
    private final Credenciales administrador;
    private Sesion sesionActual;

    public AutenticacionServicio(RepositorioClientes clientes, Credenciales administrador) {
        this.clientes = Objects.requireNonNull(clientes);
        this.administrador = Objects.requireNonNull(administrador);
    }

    public Sesion iniciarSesion(Rol rol, String nombreUsuario, String contrasena) throws ValidacionException {
        List<String> errores = new ArrayList<>();
        Reglas.requerido(nombreUsuario, "usuario", errores);
        Reglas.requerido(contrasena, "contraseña", errores);
        Reglas.lanzarSiHayErrores(errores);

        String usuario = Credenciales.normalizar(nombreUsuario);
        boolean valido = switch (rol) {
            case ADMINISTRADOR -> usuario.equals(administrador.nombreUsuario())
                    && administrador.coincide(contrasena);
            case CLIENTE -> clientes.buscarPorId(usuario)
                    .map(Cliente::getCredenciales)
                    .map(c -> c.coincide(contrasena))
                    .orElse(false);
        };
        if (!valido) {
            throw new ValidacionException(CREDENCIALES_INVALIDAS);
        }
        sesionActual = new Sesion(usuario, rol);
        return sesionActual;
    }

    public Optional<Sesion> sesionActual() {
        return Optional.ofNullable(sesionActual);
    }

    public void cerrarSesion() {
        sesionActual = null;
    }
}
