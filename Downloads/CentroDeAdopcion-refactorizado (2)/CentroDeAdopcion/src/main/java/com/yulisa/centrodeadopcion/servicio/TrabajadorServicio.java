package com.yulisa.centrodeadopcion.servicio;

import com.yulisa.centrodeadopcion.dto.RegistroTrabajadorDTO;
import com.yulisa.centrodeadopcion.dto.TrabajadorDTO;
import com.yulisa.centrodeadopcion.modelo.Trabajador;
import com.yulisa.centrodeadopcion.repositorio.RepositorioTrabajadores;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Registro y consulta del personal. El repositorio persiste en cada guardar (corrige B1). */
public class TrabajadorServicio {

    private static final int EDAD_MINIMA = 18;
    private static final int EDAD_MAXIMA = 100;

    private final RepositorioTrabajadores trabajadores;

    public TrabajadorServicio(RepositorioTrabajadores trabajadores) {
        this.trabajadores = Objects.requireNonNull(trabajadores);
    }

    public TrabajadorDTO registrar(RegistroTrabajadorDTO d) throws ValidacionException {
        List<String> errores = new ArrayList<>();
        Reglas.requerido(d.nombre(), "nombre", errores);
        Reglas.requerido(d.documento(), "documento", errores);
        Reglas.requerido(d.contacto(), "contacto", errores);
        Reglas.requerido(d.cargo(), "cargo", errores);
        int edad = Reglas.edadEnRango(d.edad(), EDAD_MINIMA, EDAD_MAXIMA, errores);
        if (!Reglas.vacio(d.documento()) && trabajadores.existeDocumento(d.documento())) {
            errores.add("Ya existe un trabajador con el documento " + d.documento().trim() + ".");
        }
        Reglas.lanzarSiHayErrores(errores);

        Trabajador trabajador = new Trabajador(
                d.nombre().trim(), edad, d.documento().trim(), d.contacto().trim(), d.cargo().trim());
        trabajadores.guardar(trabajador);
        return TrabajadorDTO.desde(trabajador);
    }

    public List<TrabajadorDTO> listar() {
        return trabajadores.listar().stream()
                .sorted(Comparator.comparing(Trabajador::getNombre))
                .map(TrabajadorDTO::desde)
                .toList();
    }
}
