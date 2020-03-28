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
public class P_phones {
    int phone_id,fk_p_id;
    String phone_no;

    public P_phones(int phone_id, int fk_p_id, String phone_no) {
        this.phone_id = phone_id;
        this.fk_p_id = fk_p_id;
        this.phone_no = phone_no;
    }

    public P_phones(int fk_p_id, String phone_no) {
        this.fk_p_id = fk_p_id;
        this.phone_no = phone_no;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public int getFk_p_id() {
        return fk_p_id;
    }

    public void setFk_p_id(int fk_p_id) {
        this.fk_p_id = fk_p_id;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
    
    
    
}
