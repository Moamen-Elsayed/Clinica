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
public class Check_test {
    int fk_ch_id , fk_t_id;

    public Check_test(int fk_ch_id, int fk_t_id) {
        this.fk_ch_id = fk_ch_id;
        this.fk_t_id = fk_t_id;
    }

    public int getFk_ch_id() {
        return fk_ch_id;
    }

    public void setFk_ch_id(int fk_ch_id) {
        this.fk_ch_id = fk_ch_id;
    }

    public int getFk_t_id() {
        return fk_t_id;
    }

    public void setFk_t_id(int fk_t_id) {
        this.fk_t_id = fk_t_id;
    }
    
}
