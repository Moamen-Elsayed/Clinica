/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinic;

import tablesClasses.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Mohamed
 */
public class Database {
    static String conUrl = "jdbc:sqlite:D:/clinic Database/database.db";
    public int initializeDB(ArrayList<String> tables){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("connection Error");
            return 1;
        }
        try {
            for(int i = 0; i < tables.size(); i++){
                connection.prepareStatement(tables.get(i)).execute();
            }
            connection.close();
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("failed to create table");
            return 2;
        }
        
        return 0;
    }
    public int checkIfAdmin(String userName , String password){
        String sqlString = "SELECT * FROM admin";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("connection Error");
            return 2;//error
        }
        try {
            ResultSet resultSet = connection.prepareStatement(sqlString).executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("username");
                String pass = resultSet.getString("password");
                if (name.equals(userName) && pass.equals(password) ){
                    return 1;//true
                }
            }
            connection.close();
            return 0;//false
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return 2;//error
        }
    }
    
    public ObservableList<CheckupPageTableRow> getCheckupDataForTable(String date){
        String sqlString = "SELECT * FROM  checkup";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        ObservableList<CheckupPageTableRow> checkupPageTableRows = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.prepareStatement(sqlString).executeQuery();
            while (resultSet.next()) {
                int ch_id = resultSet.getInt("ch_id");
                String ch_date = resultSet.getString("ch_date");
                String end_date = resultSet.getString("end_date");
                int v_id = resultSet.getInt("fk_v_id");
                int cost = resultSet.getInt("cost");
                if(ch_date.contains(date)){
                    checkupPageTableRows.add(new CheckupPageTableRow(ch_id,cost
                            ,getPatientformPcheck(ch_id).getP_name()
                            ,getVisit(v_id).getType() 
                            ,ch_date , end_date));
                    
                }
            }
            connection.close();
            return checkupPageTableRows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return null;
        }
    }
    public ArrayList<P_check> getAllPatientCheckups(int id){
        String sqlString ="SELECT * FROM p_check where fk_p_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        ArrayList<P_check> list = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int p_id = resultSet.getInt("fk_p_id");
                int ch_id = resultSet.getInt("fk_ch_id");
                list.add(new P_check(p_id, ch_id));
            }
            connection.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return null;
        }
    }
    public ObservableList<PaymentPageTableRow> getPaymentDataForTable(int p_id){
        ObservableList<PaymentPageTableRow> paymentPageTableRows = FXCollections.observableArrayList();
        ArrayList<P_check> list = getAllPatientCheckups(p_id);
        for(int i = 0 ; i < list.size();i++) {
            Checkup currentCheckup = getCheckup(list.get(i).getFk_ch_id());
            String p_name = getPatient(p_id).getP_name();
            String visitType = getVisit(currentCheckup.getFk_v_id()).getType();
            String date = currentCheckup.getCh_date();
            paymentPageTableRows.add(new PaymentPageTableRow(p_name, visitType, date));
        }
        return paymentPageTableRows;
    }
    public Checkup getCheckup(int id){
         String sqlString = "SELECT * FROM checkup WHERE ch_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Checkup checkup = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                checkup = new Checkup(resultSet.getInt("ch_id")
                        ,resultSet.getString("ch_date")
                        ,resultSet.getString("end_date")
                        ,resultSet.getInt("cost")
                        ,resultSet.getString("notes")
                        ,resultSet.getInt("fk_v_id"));
                checkup.setReport(resultSet.getString("report"));
            }
            connection.close();
            return checkup;
        } catch (SQLException ex) {
            System.out.println("failed to get table");
            return null;
        }
    }
    public Patient getPatientformPcheck(int id){
         String sqlString = "SELECT * FROM p_check WHERE fk_ch_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Patient patient = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                patient = getPatient(resultSet.getInt("fk_p_id"));
            }
            connection.close();
            return patient;
        } catch (SQLException ex) {
            System.out.println("failed to get table");
            return null;
        }
     }
    public Patient getPatient(int id){
        String sqlString = "SELECT * FROM patient WHERE p_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Patient patient = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                patient = new Patient( resultSet.getInt("p_id") 
                        ,resultSet.getString("p_name")  
                        , resultSet.getInt("age") 
                        , resultSet.getString("med_history") 
                        , resultSet.getString("gender") 
                        , resultSet.getString("district"));
            }
            connection.close();
            return patient;
        } catch (SQLException ex) {
            System.out.println("failed to get table");
            return null;
        }
    }
    public ArrayList<P_phones> getP_phonesList(int p_id){
        String sqlString = "SELECT * FROM  P_phones WHERE fk_p_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        ArrayList<P_phones> list = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, p_id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                int fk_p_id = resultSet.getInt("fk_p_id");
                String phone_no = resultSet.getString("phone_no");
                list.add(new P_phones(fk_p_id, phone_no));
            }
            connection.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return null;
        }
    }
    public Visit getVisit(int id){
        String sqlString = "SELECT * FROM visit WHERE v_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Visit visit = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                visit = new Visit(resultSet.getInt("v_id") 
                        ,resultSet.getInt("cost") 
                        , resultSet.getString("type"));
            }
            connection.close();
            return visit;
        } catch (SQLException ex) {
            System.out.println("failed to get table");
            return null;
        }
    }
    public Sqlite_sequence getSqliteSeq(String tableName){
        String sqlString = "SELECT * FROM sqlite_sequence WHERE name = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Sqlite_sequence sqlite_sequence = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, tableName);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                sqlite_sequence = new Sqlite_sequence(resultSet.getString("name") 
                        ,resultSet.getInt("seq"));
            }
            connection.close();
            return sqlite_sequence;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("failed to get table");
            return null;
        }
        
    }
    public int insertInDoctor(Doctor doctor){
        String sqlString = "INSERT INTO doctor (name,phone,Email,sp) values (?,?,?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, doctor.getName());
            prepareStatement.setString(2, doctor.getPhone());
            prepareStatement.setString(3, doctor.getEmail());
            prepareStatement.setString(4, doctor.getSp());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }

    }
    public int insertInAdmin(Admin admin){
        String sqlString = "INSERT INTO admin values (?,?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, admin.getUserName());
            prepareStatement.setString(2, admin.getPassword());
            prepareStatement.setInt(3, admin.getFk_d_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }

    }
    
    public int setFinishCheckup(int id , String finishDate){
        String sqlString = "Update checkup set end_date = ? where ch_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1; // error connection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(2, id);
            prepareStatement.setString(1, finishDate);
            prepareStatement.executeUpdate();
            connection.close();
            return 0; // done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to Update table");
            return 2; // error update
        }
    }
       
    public int setCostCheckup(int id , int cost){
        String sqlString = "Update checkup set cost = ? where ch_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1; // error connection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(2, id);
            prepareStatement.setInt(1, cost);
            prepareStatement.executeUpdate();
            connection.close();
            return 0; // done
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("failed to Update table");
            return 2; // error update
        }
    }
    public int insertInPatient(Patient patient){
        String sqlString = "INSERT INTO patient (p_name,age,med_history,gender,district) values (?,?,?,?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, patient.getP_name());
            prepareStatement.setInt(2, patient.getAge());
            prepareStatement.setString(3, patient.getMed_history());
            prepareStatement.setString(4, patient.getGender());
            prepareStatement.setString(5, patient.getDistrict());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    public int insertInCheckup(Checkup checkup){
        String sqlString = "INSERT INTO checkup (ch_date,end_date,cost,notes,fk_v_id) values (?,?,?,?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, checkup.getCh_date());
            prepareStatement.setString(2, checkup.getEnd_date());
            prepareStatement.setInt(3, checkup.getCost());
            prepareStatement.setString(4, checkup.getNotes());
            prepareStatement.setInt(5, checkup.getFk_v_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    public int deleteFromCheckup(int ch_id){
        String sqlString = "DELETE FROM checkup WHERE ch_id =  ?";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, ch_id);
            prepareStatement.executeUpdate();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to delete from table");
            return 2;//error insertion
        }
    }
    
    public int insertInP_check(P_check p_check){
        String sqlString = "INSERT INTO p_check values (?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, p_check.getFk_p_id());
            prepareStatement.setInt(2, p_check.getFk_ch_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    public int insertInP_phones(P_phones p_phones){
        String sqlString = "INSERT INTO p_phones(phone_no,fk_p_id) values (?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, p_phones.getPhone_no());
            prepareStatement.setInt(2, p_phones.getFk_p_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    public int insertInCheck_drug(Check_drug check_drug){
        String sqlString = "INSERT INTO check_drug values (?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, check_drug.getFk_ch_id());
            prepareStatement.setInt(2, check_drug.getFk_drug_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    public int insertInCheck_test(Check_test check_test){
        String sqlString = "INSERT INTO check_test values (?,?)";
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(1, check_test.getFk_ch_id());
            prepareStatement.setInt(2, check_test.getFk_t_id());
            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    
    public ObservableList<Drug> getDrug() {
        String sqlString = "SELECT * FROM  drug";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        ObservableList<Drug> drugs = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.prepareStatement(sqlString).executeQuery();
            while (resultSet.next()) {
                int d_id = resultSet.getInt("d_id");
                String d_name = resultSet.getString("d_name");
                drugs.add(new Drug(d_id,
                        d_name
                ));
            }
            connection.close();
            return drugs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return null;
        }
    }

    public ObservableList<Test> getTest() {
        String sqlString = "SELECT * FROM  Test";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        ObservableList<Test> tests = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = connection.prepareStatement(sqlString).executeQuery();
            while (resultSet.next()) {
                int t_id = resultSet.getInt("t_id");
                String t_name = resultSet.getString("t_name");
                tests.add(new Test(t_id,
                        t_name
                ));
            }
            connection.close();
            return tests;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
            System.out.println("failed to select table");
            return null;
        }
    }

    public Patient getPatient(String name) {
        String sqlString = "SELECT * FROM patient WHERE p_name = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
        }
        Patient patient = null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, name);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                patient = new Patient(resultSet.getInt("p_id"),
                        resultSet.getString("p_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("med_history"),
                        resultSet.getString("gender"),
                        resultSet.getString("district"));
            }
            connection.close();
            return patient;
        } catch (SQLException ex) {
            System.out.println("failed to get table");
            return null;
        }
    }

    public int setReportCheckup(int id, String report) {
        String sqlString = "Update checkup set report = ? where ch_id = ?";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1; // error connection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setInt(2, id);
            prepareStatement.setString(1, report);
            prepareStatement.executeUpdate();
            connection.close();
            return 0; // done
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("failed to Update table");
            return 2; // error update
        }
    }
    

    public int insertInDrug(Drug drug) {
        String sqlString = "INSERT INTO drug (d_name) values (?)";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, drug.getDrug_name());

            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }

    }
    
    public int insertInTest(Test test) {
        String sqlString = "INSERT INTO test (t_name) values (?)";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(conUrl);
        } catch (SQLException ex) {
            System.out.println("DriverManager error");
            return 1;//error conection
        }
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(sqlString);
            prepareStatement.setString(1, test.getT_name());

            prepareStatement.execute();
            System.out.println("insert Done ...");
            connection.close();
            return 0;//Done
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("failed to insert table");
            return 2;//error insertion
        }
    }
    
}
