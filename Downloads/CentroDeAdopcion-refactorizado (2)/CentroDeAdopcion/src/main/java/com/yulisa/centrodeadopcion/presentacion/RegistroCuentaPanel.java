package com.yulisa.centrodeadopcion.presentacion;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** Paso 1 del registro de cliente (antes VistaClienteSignUp). */
public class RegistroCuentaPanel extends VistaBase {

    private final JTextField txtNombreUsuario = new JTextField(18);
    private final JPasswordField pwdContrasena = new JPasswordField(18);

    public RegistroCuentaPanel(RegistroClienteController controller) {
        JButton btnContinuarRegistro = new JButton("Continuar");
        JButton btnIrAInicioSesion = new JButton("Ya tengo cuenta");

        btnContinuarRegistro.addActionListener(e -> {
            if (controller.continuarConCuenta(txtNombreUsuario.getText(), new String(pwdContrasena.getPassword()), this)) {
                limpiar();
            } else {
                pwdContrasena.setText("");
            }
        });
        btnIrAInicioSesion.addActionListener(e -> {
            limpiar();
            controller.cancelar();
        });

        add(titulo("Crear cuenta"), BorderLayout.NORTH);
        add(new Formulario()
                .campo("Usuario:", txtNombreUsuario)
                .campo("Contraseña:", pwdContrasena), BorderLayout.CENTER);
        add(filaDeBotones(btnContinuarRegistro, btnIrAInicioSesion), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        limpiar();
    }

    private void limpiar() {
        txtNombreUsuario.setText("");
        pwdContrasena.setText("");
    }
}
