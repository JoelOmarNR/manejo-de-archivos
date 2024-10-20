package org.patricia.huanca;

import java.io.*;
import java.util.*;

public class Juego implements Comparable<Juego>,Serializable  {
    private static final long serialVersionUID = 1L; // ID de versión para la serialización
    private String game;
    private String platform;
    private String developer;
    private String genre;
    private String number_players;
    private String rating;
    private String release_date;
    private int positive_critics;
    private int neutral_critics;
    private int negative_critics;
    private int positive_users;
    private int neutral_users;
    private int negative_users;
    private double metascore;
    private double user_score;

    public Juego() {
    }

    public Juego(String game, String platform, String developer, String genre, String number_players, String rating, String release_date, int positive_critics, int neutral_critics, int negative_critics, int positive_users, int neutral_users, int negative_users, double metascore, double user_score) {
        this.game = game;
        this.platform = platform;
        this.developer = developer;
        this.genre = genre;
        this.number_players = number_players;
        this.rating = rating;
        this.release_date = release_date;
        this.positive_critics = positive_critics;
        this.neutral_critics = neutral_critics;
        this.negative_critics = negative_critics;
        this.positive_users = positive_users;
        this.neutral_users = neutral_users;
        this.negative_users = negative_users;
        this.metascore = metascore;
        this.user_score = user_score;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNumber_players() {
        return number_players;
    }

    public void setNumber_players(String number_players) {
        this.number_players = number_players;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getPositive_critics() {
        return positive_critics;
    }

    public void setPositive_critics(int positive_critics) {
        this.positive_critics = positive_critics;
    }

    public int getNeutral_critics() {
        return neutral_critics;
    }

    public void setNeutral_critics(int neutral_critics) {
        this.neutral_critics = neutral_critics;
    }

    public int getNegative_critics() {
        return negative_critics;
    }

    public void setNegative_critics(int negative_critics) {
        this.negative_critics = negative_critics;
    }

    public int getPositive_users() {
        return positive_users;
    }

    public void setPositive_users(int positive_users) {
        this.positive_users = positive_users;
    }

    public int getNeutral_users() {
        return neutral_users;
    }

    public void setNeutral_users(int neutral_users) {
        this.neutral_users = neutral_users;
    }

    public int getNegative_users() {
        return negative_users;
    }

    public void setNegative_users(int negative_users) {
        this.negative_users = negative_users;
    }

    public double getMetascore() {
        return metascore;
    }

    public void setMetascore(double metascore) {
        this.metascore = metascore;
    }

    public double getUser_score() {
        return user_score;
    }

    public void setUser_score(double user_score) {
        this.user_score = user_score;
    }


    public static void main(String[] args) {
        List<Juego> juegos = leerCSV("juegos.csv");
        guardarBinario(juegos, "juegos.dat");
    }

    public static List<Juego> leerCSV(String csvFile) {
        List<Juego> juegos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            // Leer el encabezado
            String headerLine = br.readLine(); // leer la primera línea
            String[] headers = headerLine.split(";"); // cambiar a punto y coma
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";"); // cambiar a punto y coma
                // Verifica que el número de columnas sea el esperado
                if (values.length != headers.length) {
                    System.out.println("Línea ignorada (número de columnas no coincide): " + line);
                    continue; // omite esta línea
                }
                // Asegúrate de que el constructor de Juego maneje los valores correctamente
                Juego juego = new Juego(
                        values[0], // game
                        values[1], // platform
                        values[2], // developer
                        values[3], // genre
                        values[4], // number_players
                        values[5], // rating
                        values[6], // release_date
                        Integer.parseInt(values[7]), // positive_critics
                        Integer.parseInt(values[8]), // neutral_critics
                        Integer.parseInt(values[9]), // negative_critics
                        Integer.parseInt(values[10]), // positive_users
                        Integer.parseInt(values[11]), // neutral_users
                        Integer.parseInt(values[12]), // negative_users
                        Integer.parseInt(values[13]), // metascore
                        Double.parseDouble(values[14]) // user_score
                );
                juegos.add(juego);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return juegos;
    }


    public static void guardarBinario(List<Juego> juegos, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(juegos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Juego o) {
        return this.release_date.compareTo(o.release_date);
    }


    public static void generarFicheroAleatorio(List<Juego> juegos, String filePath) {
        Collections.sort(juegos); // Ordenar por fecha
        try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
            for (Juego juego : juegos) {
                // Escribir cada juego truncando a 15 caracteres los Strings
                raf.writeUTF(juego.getGame().substring(0, Math.min(juego.getGame().length(), 15)));
                raf.writeUTF(juego.getPlatform().substring(0, Math.min(juego.getPlatform().length(), 15)));
                // Resto de los datos...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

