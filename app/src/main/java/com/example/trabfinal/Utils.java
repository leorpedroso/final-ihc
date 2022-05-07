package com.example.trabfinal;

import android.widget.Spinner;

import com.example.trabfinal.backend.Data;
import com.example.trabfinal.backend.Reservation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Utils {
    public static String formatDate (long dateInMillis) {
        Date selectedDate = new Date(dateInMillis);
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new java.util.Locale("pt","BR"));
        return df.format(selectedDate);
    }

    public static String formatDateNoYear(long dateInMillis) {
        Date selectedDate = new Date(dateInMillis);
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM", new java.util.Locale("pt","BR"));
        return df.format(selectedDate);
    }

    public static boolean isSendableSpinner(Spinner spinner) {
        return spinner.getSelectedItemPosition() != 0;
    }

    public static boolean isSendableTime(int id) {
        return id != 0;
    }

    public static String getReservationString(Reservation r) {
        String checkInStatus;
        if (r.getCheckInStatus())
            checkInStatus = "Confirmado";
        else
            checkInStatus = "Não confirmado";

        return r.getCourtType() + ", " + formatDate(r.getDate()) + ", " + r.getTime() + ", " + r.getCourtNumber() + ", " + checkInStatus;
    }

    public static String[] filterUnavailableCourts(String courtType, String info){

        String[] courts = Data.getCourtsFromType(courtType);
        List<String> reservations =  Data.getReservationsInfo();
        ArrayList<String> filteredCourts = new ArrayList<>();

        for(String court : courts)
            if (!reservations.contains(info + court))
                filteredCourts.add(court);

        return filteredCourts.toArray(new String[0]);
    }
}
