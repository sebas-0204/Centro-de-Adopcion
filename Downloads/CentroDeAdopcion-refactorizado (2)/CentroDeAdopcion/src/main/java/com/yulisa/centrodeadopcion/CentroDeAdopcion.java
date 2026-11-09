package com.yulisa.centrodeadopcion;

import com.yulisa.centrodeadopcion.modelo.Credenciales;
import com.yulisa.centrodeadopcion.modelo.Rol;
import com.yulisa.centrodeadopcion.presentacion.AdopcionPanel;
import com.yulisa.centrodeadopcion.presentacion.AnimalesController;
import com.yulisa.centrodeadopcion.presentacion.InscripcionPanel;
import com.yulisa.centrodeadopcion.presentacion.ListadoAnimalesPanel;
import com.yulisa.centrodeadopcion.presentacion.ListadoTrabajadoresPanel;
import com.yulisa.centrodeadopcion.presentacion.LoginController;
import com.yulisa.centrodeadopcion.presentacion.LoginPanel;
import com.yulisa.centrodeadopcion.presentacion.MenuAdminPanel;
import com.yulisa.centrodeadopcion.presentacion.MenuClientePanel;
import com.yulisa.centrodeadopcion.presentacion.MenuController;
import com.yulisa.centrodeadopcion.presentacion.Navegador;
import com.yulisa.centrodeadopcion.presentacion.Pantalla;
import com.yulisa.centrodeadopcion.presentacion.RegistroAnimalController;
import com.yulisa.centrodeadopcion.presentacion.RegistroAnimalPanel;
import com.yulisa.centrodeadopcion.presentacion.RegistroClienteController;
import com.yulisa.centrodeadopcion.presentacion.RegistroCuentaPanel;
import com.yulisa.centrodeadopcion.presentacion.RegistroTrabajadorPanel;
import com.yulisa.centrodeadopcion.presentacion.SeleccionRolPanel;
import com.yulisa.centrodeadopcion.presentacion.TrabajadorController;
import com.yulisa.centrodeadopcion.presentacion.VentanaPrincipal;
import com.yulisa.centrodeadopcion.repositorio.RepositorioAnimales;
import com.yulisa.centrodeadopcion.repositorio.RepositorioAnimalesArchivo;
import com.yulisa.centrodeadopcion.repositorio.RepositorioClientes;
import com.yulisa.centrodeadopcion.repositorio.RepositorioClientesArchivo;
import com.yulisa.centrodeadopcion.repositorio.RepositorioTrabajadores;
import com.yulisa.centrodeadopcion.repositorio.RepositorioTrabajadoresArchivo;
import com.yulisa.centrodeadopcion.servicio.AnimalServicio;
import com.yulisa.centrodeadopcion.servicio.AutenticacionServicio;
import com.yulisa.centrodeadopcion.servicio.ClienteServicio;
import com.yulisa.centrodeadopcion.servicio.TrabajadorServicio;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada y Composition Root: el ÚNICO lugar donde se hace "new" de
 * implementaciones concretas. Todo se conecta a mano, por constructor, en este orden:
 * repositorios -> servicios -> navegador -> controllers -> vistas.
 */
public final class CentroDeAdopcion {

    private static final String ARCHIVO_CONFIGURACION = "app.properties";

    private CentroDeAdopcion() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            aplicarNimbus();
            try {
                iniciar();
            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(null,
                        "No se pudo iniciar la aplicación:\n" + e.getMessage(),
                        "Error al iniciar", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }

    private static void iniciar() {
        Properties config = cargarConfiguracion();
        Path carpetaDatos = Path.of(config.getProperty("datos.carpeta", "datos"));
        Credenciales administrador = new Credenciales(
                requerida(config, "admin.usuario"), requerida(config, "admin.contrasena"));

        // 1. Repositorios (persistencia en archivos)
        RepositorioAnimales animales = new RepositorioAnimalesArchivo(carpetaDatos.resolve("animales.dat"));
        RepositorioClientes clientes = new RepositorioClientesArchivo(carpetaDatos.resolve("clientes.dat"));
        RepositorioTrabajadores trabajadores =
                new RepositorioTrabajadoresArchivo(carpetaDatos.resolve("trabajadores.dat"));

        // 2. Servicios (reglas de negocio)
        AutenticacionServicio autenticacion = new AutenticacionServicio(clientes, administrador);
        ClienteServicio clienteServicio = new ClienteServicio(clientes);
        AnimalServicio animalServicio = new AnimalServicio(animales, clientes);
        TrabajadorServicio trabajadorServicio = new TrabajadorServicio(trabajadores);

        // 3. Ventana y navegación
        VentanaPrincipal ventana = new VentanaPrincipal();
        Navegador navegador = new Navegador(ventana, autenticacion);

        // 4. Controllers
        MenuController menu = new MenuController(navegador);
        LoginController login = new LoginController(autenticacion, navegador);
        RegistroClienteController registroCliente = new RegistroClienteController(clienteServicio, navegador);
        AnimalesController animalesController = new AnimalesController(animalServicio, autenticacion, navegador);
        RegistroAnimalController registroAnimal = new RegistroAnimalController(animalServicio, navegador);
        TrabajadorController trabajadorController = new TrabajadorController(trabajadorServicio, navegador);

        // 5. Vistas
        navegador.registrar(Pantalla.SELECCION_ROL, new SeleccionRolPanel(menu));
        navegador.registrar(Pantalla.LOGIN_ADMIN, new LoginPanel(Rol.ADMINISTRADOR, login));
        navegador.registrar(Pantalla.LOGIN_CLIENTE, new LoginPanel(Rol.CLIENTE, login));
        navegador.registrar(Pantalla.REGISTRO_CUENTA, new RegistroCuentaPanel(registroCliente));
        navegador.registrar(Pantalla.INSCRIPCION, new InscripcionPanel(registroCliente));
        navegador.registrar(Pantalla.MENU_ADMIN, new MenuAdminPanel(menu));
        navegador.registrar(Pantalla.MENU_CLIENTE, new MenuClientePanel(menu));
        navegador.registrar(Pantalla.LISTADO_ANIMALES, new ListadoAnimalesPanel(animalesController));
        navegador.registrar(Pantalla.ADOPCION, new AdopcionPanel(animalesController));
        navegador.registrar(Pantalla.REGISTRO_ANIMAL, new RegistroAnimalPanel(registroAnimal));
        navegador.registrar(Pantalla.REGISTRO_TRABAJADOR, new RegistroTrabajadorPanel(trabajadorController));
        navegador.registrar(Pantalla.LISTADO_TRABAJADORES, new ListadoTrabajadoresPanel(trabajadorController));

        navegador.irA(Pantalla.SELECCION_ROL);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Lee app.properties de la carpeta de trabajo si existe (para cambiar la clave del admin
     * sin recompilar); si no, usa el que viene dentro del jar. Así la clave no queda en el código.
     */
    private static Properties cargarConfiguracion() {
        Properties config = new Properties();
        Path externo = Path.of(ARCHIVO_CONFIGURACION);
        try (InputStream in = Files.exists(externo)
                ? Files.newInputStream(externo)
                : CentroDeAdopcion.class.getResourceAsStream("/" + ARCHIVO_CONFIGURACION)) {
            if (in == null) {
                throw new IllegalStateException("No se encontró " + ARCHIVO_CONFIGURACION);
            }
            config.load(in);
            return config;
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + ARCHIVO_CONFIGURACION, e);
        }
    }

    private static String requerida(Properties config, String clave) {
        String valor = config.getProperty(clave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Falta la propiedad '" + clave + "' en " + ARCHIVO_CONFIGURACION);
        }
        return valor.trim();
    }

    private static void aplicarNimbus() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException e) {
            // Si Nimbus no está disponible se usa el look and feel por defecto; la app funciona igual.
        }
    }
}
