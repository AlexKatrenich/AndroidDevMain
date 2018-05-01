package com.katrenich.alex.touristdiary.entity;


import java.util.Date;
import java.util.List;

public class Trip {
    private String name;
    private Date dateOfBeginingTrip;
    private List<Date> dates;
    private List<String> notes;
    private int photoId;

    public Trip(String name, Date dateOfBeginingTrip, int photoId) {
        this.name = name;
        this.dateOfBeginingTrip = dateOfBeginingTrip;
        this.photoId = photoId;
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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
