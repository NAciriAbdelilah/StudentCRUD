package com.example.studentcrud;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DBManager {


    public List<Student> loadStudents() {
        List<Student> studentAll= new ArrayList<Student>();
        Connection myConn= null;
        try {
            myConn = this.Connector();
            System.out.println("Connexion réussite");
            Statement myStmt= myConn.createStatement();
            String sql = "select * from studenttable order by note desc";
            ResultSet myRs= myStmt.executeQuery(sql);
            while (myRs.next()) {
                Student s = new Student(
                        myRs.getInt("id"),
                        myRs.getString("name"),
                        myRs.getString("gender"),
                        myRs.getFloat("note")
                        );
                studentAll.add(s);

                System.out.println(studentAll);  // this shows student by student
            }

            this.close(myConn, myStmt, myRs);
            return studentAll;

        }
        catch (SQLException e) {e.printStackTrace();
        }
        return null;
    }


    public void addStudent(Student student) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        try {
            myConn = this.Connector();
            String sql = "INSERT INTO studenttable (name,gender,note) VALUES (?,?,?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getName());
            myStmt.setString(2, student.getGender());
            myStmt.setFloat(3, student.getNote());
            myStmt.execute();
            loadStudents();
        }
        catch(Exception e){System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void updateStudent(Student student, int id) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = this.Connector();
            String sql = "UPDATE  studenttable SET name=? ,gender=?, note=? WHERE id =?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, student.getName());
            myStmt.setString(2, student.getGender());
            myStmt.setFloat(3, student.getNote());
            myStmt.setInt(4, id);
            myStmt.execute();
            loadStudents();
        }
        catch(Exception e){System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void deleteStudent(int id) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = this.Connector();

            String sql = "DELETE FROM studenttable WHERE id =?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myStmt.execute();
            loadStudents();
        }
        catch(Exception e){System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,myRs);
        }

    }
















    //___________________CONNEXION FUNCTION WITH CLOSE -----------------------------------
    public Connection Connector() throws SQLException {

        Connection connection = null;
        connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=Europe%2FParis", "root","naciri");
        return connection;
    }
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

        try{if(myStmt!=null)myStmt.close();
            if(myRs!=null)myRs.close();
            if(myConn!=null)myConn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //------------------------------------------------------------------------------------------------

}
