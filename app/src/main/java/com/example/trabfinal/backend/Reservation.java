package com.example.trabfinal.backend;

import com.example.trabfinal.Utils;

import java.util.Date;

public class Reservation {
    private boolean checkInStatus;
    private final String courtNumber;
    private final String courtType;
    private final String time;
    private final long date;

    private final String info;

    public Reservation(String courtNumber, String courtType, String time, long date) {
        this.courtNumber = courtNumber;
        this.courtType = courtType;
        this.time = time;
        this.date = date;
        this.checkInStatus = false;

        this.info = courtType + Utils.formatDate(date) + time + courtNumber;
    }

    public boolean getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckIn(boolean checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public String getCourtNumber() {
        return courtNumber;
    }

    public String getCourtType() {
        return courtType;
    }

    public String getTime() {
        return time;
    }

    public long getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }
}
