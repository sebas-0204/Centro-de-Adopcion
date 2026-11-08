/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios.archivo;

/**
 *
 * @author YULI
 */

import com.yulisa.centrodeadopcion.entidades.Trabajador;
import com.yulisa.centrodeadopcion.repositorios.TrabajadorRepositorio;

public final class TrabajadorRepositorioArchivo
        extends RepositorioEnArchivo<Trabajador, String> implements TrabajadorRepositorio {

    public TrabajadorRepositorioArchivo(ArchivoSerializadoStore<Trabajador> store) { super(store); }

    @Override protected String idDe(Trabajador t) { return t.getDocumento(); }
}
