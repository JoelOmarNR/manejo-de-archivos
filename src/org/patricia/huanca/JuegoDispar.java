package org.patricia.huanca;

import java.util.List;

public class JuegoDispar {

    public static void juegosDispares(List<Juego> juegos) {
        Juego mayorDiferenciaCriticos = null;
        Juego mayorDiferenciaUsuarios = null;
        double mayorDiferenciaPositiva = 0;
        double mayorDiferenciaNegativa = 0;

        for (Juego juego : juegos) {
            double diferencia = juego.getMetascore() - juego.getUser_score();
            if (diferencia > mayorDiferenciaPositiva) {
                mayorDiferenciaPositiva = diferencia;
                mayorDiferenciaCriticos = juego;
            } else if (diferencia < mayorDiferenciaNegativa) {
                mayorDiferenciaNegativa = diferencia;
                mayorDiferenciaUsuarios = juego;
            }
        }

        System.out.println("Juego con más nota de críticos que de usuarios: " + mayorDiferenciaCriticos);
        System.out.println("Juego con más nota de usuarios que de críticos: " + mayorDiferenciaUsuarios);
    }
}

