package com.example.search.model;

public class Record {

    private int id;
    private String name;
    private int age;

    public Record(int id, String name, int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "Record{id=" + id + ", name='" + name + "', age=" + age + "}";
    }
}