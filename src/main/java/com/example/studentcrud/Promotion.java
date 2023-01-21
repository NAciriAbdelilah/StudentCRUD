package com.example.studentcrud;

import java.util.ArrayList;
import java.util.List;

public class Promotion {

    private String titre;

    private List<Student> students= new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public Promotion(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public float  calculerMoyenne(){
        List<Float> listNote=new ArrayList<>();
        float sum=0;
       for (Student s :students) {
           listNote.add(s.getNote());
           sum+=s.getNote();
       }
       float nombre =listNote.size();

       return sum/nombre;
    }
    public void validateStudent(Student s) throws IllegalArgumentException{
        if(students.contains(s)){

            throw new IllegalArgumentException("exist deja");
        }else {
            System.out.println("student ajouter ");
        }
    }






}
