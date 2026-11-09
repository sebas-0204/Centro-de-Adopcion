package com.yulisa.centrodeadopcion.servicio;

import com.yulisa.centrodeadopcion.modelo.Rol;

/** Quién inició sesión y con qué rol. Inmutable; reemplaza a SesionActual (static). */
public record Sesion(String nombreUsuario, Rol rol) {
}
