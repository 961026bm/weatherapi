package com.careerdevs.weatherapi;

public class User {
    private String name;
    private int age;
    private String status;

    public User(String name, int age, String status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"").append(name).append('"');
        sb.append(", \"age\":").append(age);
        sb.append(", \"status\":\"").append(status).append('"');
        sb.append('}');
        return sb.toString();
    }
}
