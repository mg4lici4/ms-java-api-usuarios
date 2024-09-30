package com.mgalician.usuarios.helper;

public class TiempoRespuestaHelper {

    public static String obtenerPorMilisegundos(long start) {
        return (System.currentTimeMillis() - start) + " ms";
    }
}
