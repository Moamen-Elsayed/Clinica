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
public class Check_drug {
    int fk_ch_id , fk_drug_id ;

    public Check_drug(int fk_ch_id, int fk_drug_id) {
        this.fk_ch_id = fk_ch_id;
        this.fk_drug_id = fk_drug_id;
    }

    public int getFk_ch_id() {
        return fk_ch_id;
    }

    public void setFk_ch_id(int fk_ch_id) {
        this.fk_ch_id = fk_ch_id;
    }

    public int getFk_drug_id() {
        return fk_drug_id;
    }

    public void setFk_drug_id(int fk_drug_id) {
        this.fk_drug_id = fk_drug_id;
    }
    
}
