package org.patricia.huanca;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Ruta relativa a la carpeta 'data' dentro del proyecto
        String csvFile = "C:\\Users\\joelo\\OneDrive\\Documentos\\NetBeansProjects\\manejo_de_archivo\\src\\org\\patricia\\huanca\\data\\metacritic_games.csv";

        // Verificar existencia del archivo CSV
        File file = new File(csvFile);
        if (!file.exists()) {
            System.out.println("El archivo CSV no se encontró en la ruta especificada.");
            return;
        }

        try {
            // 1. Leer CSV y guardar como binario
            List<Juego> juegos = Juego.leerCSV(csvFile);
            Juego.guardarBinario(juegos, "juegos.dat");
            System.out.println("CSV guardado como archivo binario (juegos.dat)");

            // 2. Generar fichero de acceso aleatorio
            Juego.generarFicheroAleatorio(juegos, "juegosOrdenado.dat");
            System.out.println("Fichero de acceso aleatorio (juegosOrdenado.dat) generado y ordenado por fecha");

            // 3. Generar XML agrupado por plataforma
            GeneradorXML.generarXML(juegos, "juegos.xml");
            System.out.println("Archivo XML generado (juegos.xml)");

            // 4. Crear estructura de carpetas y archivos desde el XML
            GeneradorDirectorios.crearEstructuraDesdeXML("juegos.xml", "carpetas_juegos");
            System.out.println("Estructura de carpetas creada a partir del XML");

            // 5. Crear carpetas por género y juegos con nota >= 8
            GeneradorDirectoriosPorGenero.crearCarpetasPorGenero(juegos, "juegos_por_genero");
            System.out.println("Carpetas por género creadas para juegos con nota >= 8");

            // 6. Mostrar juegos con mayor disparidad de notas
            JuegoDispar.juegosDispares(juegos);
            System.out.println("Juegos con mayor disparidad de notas entre críticos y usuarios mostrados");

            // 7. Buscar juegos para mayores de 18 años en el fichero aleatorio
            JuegosParaMayores.buscarJuegosMayores("juegosOrdenado.dat");
            System.out.println("Juegos para mayores de 18 años listados");
        } catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción para más detalles
        }
    }
}


