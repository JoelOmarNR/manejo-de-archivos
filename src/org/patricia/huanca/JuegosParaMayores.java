package org.patricia.huanca;

import java.io.IOException;
import java.io.RandomAccessFile;

public class JuegosParaMayores {

    public static void buscarJuegosMayores(String filePath) {
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                String nombre = raf.readUTF();
                // Asumimos que hay un campo "rating" en cada registro
                String rating = raf.readUTF();
                if (rating.equals("18+")) {
                    System.out.println(nombre);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

