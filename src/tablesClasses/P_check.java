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
public class P_check {
    int fk_p_id , fk_ch_id;

    public P_check(int fk_p_id, int fk_ch_id) {
        this.fk_p_id = fk_p_id;
        this.fk_ch_id = fk_ch_id;
    }

    public int getFk_p_id() {
        return fk_p_id;
    }

    public void setFk_p_id(int fk_p_id) {
        this.fk_p_id = fk_p_id;
    }

    public int getFk_ch_id() {
        return fk_ch_id;
    }

    public void setFk_ch_id(int fk_ch_id) {
        this.fk_ch_id = fk_ch_id;
    }
    
    
    
}
