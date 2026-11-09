package com.yulisa.centrodeadopcion.dto;

import com.yulisa.centrodeadopcion.modelo.Cliente;

/** Vista de solo lectura de un cliente (sin credenciales). */
public record ClienteDTO(String nombreUsuario, String nombre, String contacto, boolean capacitado) {

    public static ClienteDTO desde(Cliente c) {
        return new ClienteDTO(c.getNombreUsuario(), c.getNombre(), c.getContacto(), c.estaCapacitado());
    }
}
