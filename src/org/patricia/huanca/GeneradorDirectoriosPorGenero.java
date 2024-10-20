package org.patricia.huanca;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GeneradorDirectoriosPorGenero {

    public static void crearCarpetasPorGenero(List<Juego> juegos, String rootDir) {
        Set<String> generosProcesados = new HashSet<>();

        for (Juego juego : juegos) {
            if (juego.getMetascore() >= 8) {
                // Limpiar el nombre del género
                String nombreGenero = limpiarNombreArchivo(juego.getGenre());

                // Declarar generoDir aquí para que esté disponible más tarde
                File generoDir = new File(rootDir + "/" + nombreGenero);

                // Agregar solo géneros únicos
                if (generosProcesados.add(nombreGenero)) {
                    if (generoDir.mkdirs()) {
                        System.out.println("Directorio creado: " + generoDir.getPath());
                    } else {
                        System.out.println("Directorio ya existe o no se pudo crear: " + generoDir.getPath());
                    }
                }

                // Limpiar el nombre del juego para que sea seguro
                String nombreArchivoSeguro = limpiarNombreArchivo(juego.getGame()) + ".txt";

                // Crear el archivo con el nombre seguro
                try (FileWriter writer = new FileWriter(new File(generoDir, nombreArchivoSeguro))) {
                    writer.write(juego.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String limpiarNombreArchivo(String nombre) {
        // Reemplaza caracteres no válidos por un guion bajo y elimina espacios innecesarios
        String nombreLimpio = nombre.replaceAll("[\\\\/:*?\"<>|]", "_").trim();
        return nombreLimpio.replaceAll("\\s+", "_");
    }
}
