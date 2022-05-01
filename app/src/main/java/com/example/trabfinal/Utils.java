package com.example.trabfinal;

import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Utils {
    public static String formatDate (long dateInMillis) {
        Date selectedDate = new Date(dateInMillis);
        SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new java.util.Locale("pt","BR"));
        return df.format(selectedDate);
    }

    public static boolean isSendableSpinner(Spinner spinner) {
        if (spinner.getSelectedItemPosition() == 0)
            return false;
        return true;
    }

    public static boolean isSendableTime(int id) {
        if (id == 0)
            return false;
        return true;
    }

}
