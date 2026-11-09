package com.yulisa.centrodeadopcion.servicio;

import com.yulisa.centrodeadopcion.dto.ClienteDTO;
import com.yulisa.centrodeadopcion.dto.RegistroClienteDTO;
import com.yulisa.centrodeadopcion.modelo.Cliente;
import com.yulisa.centrodeadopcion.modelo.Credenciales;
import com.yulisa.centrodeadopcion.repositorio.RepositorioClientes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/** Registro de clientes en dos pasos: crear cuenta y completar la inscripción. */
public class ClienteServicio {

    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 120;
    private static final int LARGO_MINIMO_CONTRASENA = 4;

    private final RepositorioClientes clientes;

    public ClienteServicio(RepositorioClientes clientes) {
        this.clientes = Objects.requireNonNull(clientes);
    }

    /** Paso 1: comprueba que la cuenta se pueda crear, sin guardar nada todavía. */
    public void validarCuenta(String nombreUsuario, String contrasena) throws ValidacionException {
        List<String> errores = new ArrayList<>();
        validarCuenta(nombreUsuario, contrasena, errores);
        Reglas.lanzarSiHayErrores(errores);
    }

    /** Paso 2: valida todo y guarda el cliente. */
    public ClienteDTO registrar(RegistroClienteDTO datos) throws ValidacionException {
        List<String> errores = new ArrayList<>();
        validarCuenta(datos.nombreUsuario(), datos.contrasena(), errores);
        Reglas.requerido(datos.nombre(), "nombre", errores);
        Reglas.requerido(datos.documento(), "documento", errores);
        Reglas.requerido(datos.contacto(), "contacto", errores);
        int edad = Reglas.edadEnRango(datos.edad(), EDAD_MINIMA, EDAD_MAXIMA, errores);
        Reglas.lanzarSiHayErrores(errores);

        Credenciales credenciales = new Credenciales(datos.nombreUsuario(), datos.contrasena());
        Cliente cliente = new Cliente(
                datos.nombre().trim(), edad, datos.documento().trim(), datos.contacto().trim(),
                credenciales,
                datos.tieneIngresosPropios(), datos.tieneViviendaPropia(), datos.tuvoMascotas());
        clientes.guardar(cliente);
        return ClienteDTO.desde(cliente);
    }

    public Optional<ClienteDTO> buscar(String nombreUsuario) {
        return clientes.buscarPorId(nombreUsuario).map(ClienteDTO::desde);
    }

    private void validarCuenta(String nombreUsuario, String contrasena, List<String> errores) {
        if (Reglas.vacio(nombreUsuario)) {
            errores.add("El campo usuario es obligatorio.");
        } else if (nombreUsuario.trim().contains(" ")) {
            errores.add("El usuario no puede contener espacios.");
        } else if (clientes.existeNombreUsuario(nombreUsuario)) {
            errores.add("El usuario \"" + nombreUsuario.trim() + "\" ya está registrado.");
        }
        if (contrasena == null || contrasena.length() < LARGO_MINIMO_CONTRASENA) {
            errores.add("La contraseña debe tener al menos " + LARGO_MINIMO_CONTRASENA + " caracteres.");
        }
    }
}
