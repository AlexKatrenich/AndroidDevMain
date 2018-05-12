package com.katrenich.alex.touristdiary.entity;

import android.os.Build;
import java.util.Objects;


public class User {
    private int id;
    private String login;
    private String email;
    private long firebaseID;

    public User() {
    }

    public User(String login, String email, long firebaseID) {
        this.login = login;
        this.email = email;
        this.firebaseID = firebaseID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFirebaseID() {
        return firebaseID;
    }

    public void setFirebaseID(long firebaseID) {
        this.firebaseID = firebaseID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return id == user.id &&
                    firebaseID == user.firebaseID &&
                    Objects.equals(login, user.login) &&
                    Objects.equals(email, user.email);
        } else {
            return id == user.id && firebaseID == user.firebaseID && login.equals(user.login) && email.equals(user.email);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + login.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int)firebaseID;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", firebaseID=" + firebaseID +
                '}';
    }
}
