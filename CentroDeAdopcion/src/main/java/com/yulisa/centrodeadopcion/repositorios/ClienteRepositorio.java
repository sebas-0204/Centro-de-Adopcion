/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */
import java.util.List;
import java.util.Optional;
import com.yulisa.centrodeadopcion.entidades.Cliente;

public interface ClienteRepositorio {
    List<Cliente> listar();
    Optional<Cliente> buscarPorId(String nombreUsuario);
    void guardar(Cliente cliente);
    boolean existeNombreUsuario(String nombreUsuario);
}