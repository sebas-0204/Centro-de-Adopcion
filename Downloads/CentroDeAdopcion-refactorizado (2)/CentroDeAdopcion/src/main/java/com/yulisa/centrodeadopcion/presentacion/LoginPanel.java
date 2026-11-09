package com.yulisa.centrodeadopcion.presentacion;

import com.yulisa.centrodeadopcion.modelo.Rol;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Login reutilizable para los dos roles (reemplaza VistaAdminLogin y VistaClienteSignIn).
 * La contraseña se escribe en un JPasswordField para que no se vea en pantalla.
 */
public class LoginPanel extends VistaBase {

    private final JTextField txtUsuario = new JTextField(18);
    private final JPasswordField pwdContrasena = new JPasswordField(18);

    public LoginPanel(Rol rol, LoginController controller) {
        String texto = (rol == Rol.ADMINISTRADOR) ? "Ingreso de administrador" : "Ingreso de cliente";
        JButton btnIniciarSesion = new JButton("Iniciar sesión");
        JButton btnVolver = new JButton("Volver");

        Runnable iniciar = () -> {
            if (controller.iniciarSesion(rol, txtUsuario.getText(), new String(pwdContrasena.getPassword()), this)) {
                limpiar();
            } else {
                pwdContrasena.setText("");
            }
        };
        btnIniciarSesion.addActionListener(e -> iniciar.run());
        pwdContrasena.addActionListener(e -> iniciar.run());
        btnVolver.addActionListener(e -> {
            limpiar();
            controller.volverAlInicio();
        });

        List<JButton> botones = new ArrayList<>(List.of(btnIniciarSesion));
        if (rol == Rol.CLIENTE) {
            JButton btnIrARegistro = new JButton("¿No tiene cuenta? Regístrese");
            btnIrARegistro.addActionListener(e -> {
                limpiar();
                controller.irARegistro();
            });
            botones.add(btnIrARegistro);
        }
        botones.add(btnVolver);

        add(titulo(texto), BorderLayout.NORTH);
        add(new Formulario()
                .campo("Usuario:", txtUsuario)
                .campo("Contraseña:", pwdContrasena), BorderLayout.CENTER);
        add(filaDeBotones(botones.toArray(JButton[]::new)), BorderLayout.SOUTH);
    }

    @Override
    protected void alMostrar() {
        limpiar();
        txtUsuario.requestFocusInWindow();
    }

    private void limpiar() {
        txtUsuario.setText("");
        pwdContrasena.setText("");
    }
}
