package com.yulisa.centrodeadopcion.presentacion;

import java.util.List;

/** Lo único que un controller necesita de una vista para comunicarse con el usuario. */
public interface MuestraMensajes {

    void mostrarInfo(String mensaje);

    void mostrarError(String mensaje);

    default void mostrarErrores(List<String> errores) {
        mostrarError("• " + String.join("\n• ", errores));
    }
}
