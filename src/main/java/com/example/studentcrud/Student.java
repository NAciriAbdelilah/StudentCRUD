package com.example.studentcrud;

public class Student {

    private int id;
    private String name;
    private String gender;
    private float note;


    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Student(int id, String name, String gender, float note) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.note = note;
    }

    public Student(String name, String gender, float note) {
        this.name = name;
        this.gender = gender;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return name+" "+gender+" "+note;
    }
}


