/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */
import com.yulisa.centrodeadopcion.entidades.Trabajador;
import java.util.List;
import java.util.Optional;
public interface TrabajadorRepositorio {
    List<Trabajador> listar();
    Optional<Trabajador> buscarPorId(String documento);
    void guardar(Trabajador trabajador);
}
