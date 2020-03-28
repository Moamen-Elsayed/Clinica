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
public class D_check {
    int fk_d_id , fk_ch_id;

    public D_check(int fk_d_id, int fk_ch_id) {
        this.fk_d_id = fk_d_id;
        this.fk_ch_id = fk_ch_id;
    }

    public int getFk_d_id() {
        return fk_d_id;
    }

    public void setFk_d_id(int fk_d_id) {
        this.fk_d_id = fk_d_id;
    }

    public int getFk_ch_id() {
        return fk_ch_id;
    }

    public void setFk_ch_id(int fk_ch_id) {
        this.fk_ch_id = fk_ch_id;
    }
    
}
