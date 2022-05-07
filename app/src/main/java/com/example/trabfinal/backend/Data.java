package com.example.trabfinal.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private static final int MAX_RESERVATIONS = 10;

    private static List<Reservation> reservations = new ArrayList<>();
    private static List<String> reservationsInfo = new ArrayList<>();

    private static final Map<String, String[]> courts = new HashMap<String, String[]>() {{
        put("Basquete", new String[] {"Quadra 4"});
        put("Beach Tennis", new String[] {"Quadra 27", "Quadra 28", "Quadra 29"});
        put("Futebol 7", new String[] {"Quadra 7", "Quadra 8", "Quadra 9", "Quadra 20", "Quadra 21"});
        put("Futebol infantil", new String[] {"Quadra 2", "Quadra 3", "Quadra 5", "Quadra 6"});
        put("Poliesportiva", new String[]{"Quadra 1"});
        put("Futebol de areia", new String[]{"Quadra 10"});
        put("Tênis", new String[]{"Quadra 22"});
        put("Vôlei de praia e Futevôlei", new String[]{"Quadra 11", "Quadra 12", "Quadra 13", "Quadra 14", "Quadra 15", "Quadra 16", "Quadra 17", "Quadra 18", "Quadra 19", "Quadra 24", "Quadra 25", "Quadra 26"});
    }};

    private static final String[] times = new String[] {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};

    public static String[] getTimes() {
        return times;
    }

    public static String[] getCourtsFromType(String type) {
        return courts.get(type);
    }

    public static List<Reservation> getReservations() {
        return reservations;
    }

    public static List<String> getReservationsInfo() {
        return reservationsInfo;
    }

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
        Collections.sort(reservations, (r1, r2) -> Long.compare(r1.getDate(), r2.getDate()));
        reservationsInfo.add(reservation.getInfo());
    }

    public static void removeReservation(Reservation reservation) {
        reservationsInfo.remove(reservation.getInfo());
        reservations.remove(reservation);
    }

    public static int getAvailableReservations() {
        return MAX_RESERVATIONS - reservations.size();
    }

    public static boolean hasAvailableReservations() {
        return getAvailableReservations() > 0;
    }

}
