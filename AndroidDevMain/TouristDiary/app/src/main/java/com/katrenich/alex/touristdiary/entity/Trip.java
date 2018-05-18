package com.katrenich.alex.touristdiary.entity;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Trip {
    private String name;
    private Date dateOfBeginingTrip;
    private List<Date> dates;
    private List<String> notes;
    private String shortDescription; // відображення назви та дати початку подорожі в текстовому форматі

    public Trip(String name, Date dateOfBeginingTrip) {
        this.name = name;
        this.dateOfBeginingTrip = dateOfBeginingTrip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBeginingTrip() {
        return dateOfBeginingTrip;
    }

    public void setDateOfBeginingTrip(Date dateOfBeginingTrip) {
        this.dateOfBeginingTrip = dateOfBeginingTrip;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public String getShortDescription() {
        shortDescription = new StringBuilder(name).
                append(", ").
                append(new SimpleDateFormat("dd MMM yyyy").format(dateOfBeginingTrip)).
                toString();

        return shortDescription;
    }

}
