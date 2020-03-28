/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablesClasses;

/**
 *
 * @author Mohamed
 */
public class Patient {
    int p_id,age;
    String p_name , med_history , gender , district;

    public Patient(int p_id, String p_name, int age, String med_history, String gender, String district) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.age = age;
        this.med_history = med_history;
        this.gender = gender;
        this.district = district;
    }

    public Patient(String p_name,int age, String med_history, String gender, String district) {
        this.age = age;
        this.p_name = p_name;
        this.med_history = med_history;
        this.gender = gender;
        this.district = district;
    }
    

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMed_history() {
        return med_history;
    }

    public void setMed_history(String med_history) {
        this.med_history = med_history;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    
    
}
