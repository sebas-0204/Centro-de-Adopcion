package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.AnimalDTO;
import com.yulisa.centrodeadopcion.modelo.Rol;
import com.yulisa.centrodeadopcion.servicio.AnimalServicio;
import com.yulisa.centrodeadopcion.servicio.AutenticacionServicio;
import com.yulisa.centrodeadopcion.servicio.Sesion;
import com.yulisa.centrodeadopcion.servicio.ValidacionException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** Listado de animales y adopción (por id, nunca por nombre). */
public class AnimalesController {

    private final AnimalServicio animales;
    private final AutenticacionServicio autenticacion;
    private final Navegador navegador;

    public AnimalesController(AnimalServicio animales, AutenticacionServicio autenticacion, Navegador navegador) {
        this.animales = Objects.requireNonNull(animales);
        this.autenticacion = Objects.requireNonNull(autenticacion);
        this.navegador = Objects.requireNonNull(navegador);
    }

    public List<AnimalDTO> listarTodos() {
        return animales.listarTodos();
    }

    public List<AnimalDTO> listarDisponibles() {
        return animales.listarDisponibles();
    }

    /** @return true si la adopción se realizó (la vista entonces recarga la tabla). */
    public boolean adoptar(UUID idAnimal, MuestraMensajes vista) {
        String usuario = autenticacion.sesionActual()
                .filter(s -> s.rol() == Rol.CLIENTE)
                .map(Sesion::nombreUsuario)
                .orElse(null);
        try {
            AnimalDTO adoptado = animales.adoptar(idAnimal, usuario);
            vista.mostrarInfo("¡Felicitaciones! Ha adoptado a " + adoptado.nombre() + ".");
            return true;
        } catch (ValidacionException e) {
            vista.mostrarErrores(e.getErrores());
            return false;
        }
    }

    public void volverAlMenu() {
        navegador.irAMenuDeSesion();
    }
}
