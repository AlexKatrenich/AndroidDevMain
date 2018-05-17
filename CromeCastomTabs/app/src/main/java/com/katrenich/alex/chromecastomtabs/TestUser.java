package com.katrenich.alex.chromecastomtabs;

import java.util.Objects;

public class TestUser {
    private int age;
    private String name;

    public TestUser() {
    }

    public TestUser(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestUser user = (TestUser) o;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
