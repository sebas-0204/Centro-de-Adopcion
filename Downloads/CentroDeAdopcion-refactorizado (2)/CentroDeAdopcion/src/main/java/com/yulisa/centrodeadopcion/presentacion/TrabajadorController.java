package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.RegistroTrabajadorDTO;
import com.yulisa.centrodeadopcion.dto.TrabajadorDTO;
import com.yulisa.centrodeadopcion.servicio.TrabajadorServicio;
import com.yulisa.centrodeadopcion.servicio.ValidacionException;
import java.util.List;
import java.util.Objects;

/** Alta y listado de trabajadores. */
public class TrabajadorController {

    private final TrabajadorServicio trabajadores;
    private final Navegador navegador;

    public TrabajadorController(TrabajadorServicio trabajadores, Navegador navegador) {
        this.trabajadores = Objects.requireNonNull(trabajadores);
        this.navegador = Objects.requireNonNull(navegador);
    }

    public boolean registrar(RegistroTrabajadorDTO datos, MuestraMensajes vista) {
        try {
            TrabajadorDTO registrado = trabajadores.registrar(datos);
            vista.mostrarInfo("Trabajador \"" + registrado.nombre() + "\" registrado con éxito.");
            return true;
        } catch (ValidacionException e) {
            vista.mostrarErrores(e.getErrores());
            return false;
        }
    }

    public List<TrabajadorDTO> listar() {
        return trabajadores.listar();
    }

    public void volverAlMenu() {
        navegador.irAMenuDeSesion();
    }
}
