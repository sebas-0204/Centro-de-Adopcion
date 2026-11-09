package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.dto.AnimalDTO;
import com.yulisa.centrodeadopcion.dto.RegistroAnimalDTO;
import com.yulisa.centrodeadopcion.servicio.AnimalServicio;
import com.yulisa.centrodeadopcion.servicio.ValidacionException;
import java.util.Objects;

public class RegistroAnimalController {

    private final AnimalServicio animales;
    private final Navegador navegador;

    public RegistroAnimalController(AnimalServicio animales, Navegador navegador) {
        this.animales = Objects.requireNonNull(animales);
        this.navegador = Objects.requireNonNull(navegador);
    }

    /** @return true si se registró (la vista entonces limpia el formulario). */
    public boolean registrar(RegistroAnimalDTO datos, MuestraMensajes vista) {
        try {
            AnimalDTO registrado = animales.registrar(datos);
            vista.mostrarInfo(registrado.tipo() + " \"" + registrado.nombre() + "\" registrado con éxito.");
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
