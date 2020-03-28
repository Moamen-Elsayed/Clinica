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
public class Doctor {
    int d_id ;
    String name , phone , email , sp;

    public Doctor(int d_id, String name, String phone, String email, String sp) {
        this.d_id = d_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sp = sp;
    }

    public Doctor(String name, String phone, String email, String sp) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sp = sp;
    }
    

    public int getD_id() {
        return d_id;
    }

    public void setD_id(int d_id) {
        this.d_id = d_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }
    
}
