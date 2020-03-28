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
public class Drug {
    int drug_id;
    String drug_name;

    public Drug(int drug_id, String drug_name) {
        this.drug_id = drug_id;
        this.drug_name = drug_name;
    }

    public Drug(String drug_name) {
        this.drug_name = drug_name;
    }
    

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }
    
    
}
