/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yulisa.centrodeadopcion.repositorios;

/**
 *
 * @author YULI
 */
public class PersistenciaException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public PersistenciaException(String mensaje, Throwable causa) { super(mensaje, causa); }
}
