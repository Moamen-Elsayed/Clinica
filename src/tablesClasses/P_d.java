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
public class P_d {
    int fk_p_id , fk_dr_id;

    public P_d(int fk_p_id, int fk_dr_id) {
        this.fk_p_id = fk_p_id;
        this.fk_dr_id = fk_dr_id;
    }

    public int getFk_p_id() {
        return fk_p_id;
    }

    public void setFk_p_id(int fk_p_id) {
        this.fk_p_id = fk_p_id;
    }

    public int getFk_dr_id() {
        return fk_dr_id;
    }

    public void setFk_dr_id(int fk_dr_id) {
        this.fk_dr_id = fk_dr_id;
    }
    
    
}
