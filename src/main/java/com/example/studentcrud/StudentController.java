package com.example.studentcrud;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private ComboBox<String> cmbGender;

    @FXML
    private Label lblerror;

    @FXML
    private Label lblDetails;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStudent;

    @FXML
    private ListView<Student> lvStudent;

    @FXML
    private TextField txtName;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private Label lblnote;
    @FXML
    private TextField txtnote;
    @FXML
    private Label lblMoyenne;

    DBManager manager;
    Promotion promotion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        manager = new DBManager();  //  Create instance of DBManager ( se trouve LoadStudent, AddStudent ....)
        promotion = new Promotion("promo sg");
        List<String>genderValues = new ArrayList<String>();

        genderValues.add("Male");
        genderValues.add("Female");
        ObservableList<String> gender = FXCollections.observableArrayList(genderValues);
        cmbGender.setItems(gender);
        fetchStudents();

        //------------ Methode : USING LAMBDA LAMBDA-LAMBDA-LAMBDA-LAMBDA-LAMBDA------------------------------------------
            lvStudent.getSelectionModel().selectedItemProperty().addListener(
                e -> displayStudentsDetails(lvStudent.getSelectionModel().getSelectedItem()));
        //--------------------------------------------------------------------------------------------------------------
    }


    private void displayStudentsDetails(Student selectedStudent) {
        if (selectedStudent!= null){
            txtName.setText(selectedStudent.getName());
            cmbGender.setValue( selectedStudent.getGender());
            txtnote.setText(String.valueOf(selectedStudent.getNote()));
        }
    }

    private void fetchStudents() {
        List<Student> listStudents=manager.loadStudents();
        promotion.setStudents(listStudents);
        lblMoyenne.setText(String.valueOf(promotion.calculerMoyenne()));
        if (listStudents!=null) {
            ObservableList<Student> students;
            students= FXCollections.observableArrayList(listStudents);
            lvStudent.setItems(students);
        }
    }
    public void onNew(){

        lvStudent.getSelectionModel().clearSelection();
        this.txtName.setText(null);
        this.cmbGender.setValue(null);
        this.txtnote.setText(null);
    }
    public void onCancel(){
        lvStudent.getSelectionModel().selectFirst();
    }

    public void onSave(){
        if(Float.parseFloat(txtnote.getText())<0) {
            lblerror.setText("Note invalide!");
        }
        else {
            Student s = new Student(txtName.getText(), cmbGender.getValue(), Float.parseFloat(txtnote.getText()));
            promotion.validateStudent(s);
            manager.addStudent(s);
            fetchStudents(); // shows all student another time in the listView
        }
    }

    public void onDelete(){
        int idTrouver = lvStudent.getSelectionModel().getSelectedItem().getId();
        manager.deleteStudent(idTrouver);
        fetchStudents();
        txtName.clear();
        txtnote.clear();
        cmbGender.setValue(null);
    }

    public void onUpdate(){
        Student student= new Student(txtName.getText(),cmbGender.getValue(),Float.parseFloat(txtnote.getText()));
        int idTrouverUpdate = lvStudent.getSelectionModel().getSelectedItem().getId();
        manager.updateStudent(student,idTrouverUpdate);
        fetchStudents();
        txtName.clear();
        txtnote.clear();
        cmbGender.setValue(null);
    }

    }
