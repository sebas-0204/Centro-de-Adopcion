/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */
import com.yulisa.centrodeadopcion.entidades.Cliente;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorClientes {
 
    private static List<Cliente> listaClientes = cargarDatos();
    private static final String ARCHIVO = "clientes.dat";

    public static void agregarCliente(Cliente c) {
        listaClientes.add(c);
        guardarDatos();
    }

    public static List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(listaClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private static List cargarDatos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {

            return (List) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}