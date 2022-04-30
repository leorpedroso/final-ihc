package com.example.trabfinal;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {
    public static String formatDate (long dateInMillis) {
        Date selectedDate = new Date(dateInMillis);
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new java.util.Locale("pt","BR"));
        return df.format(selectedDate);
    }

}
