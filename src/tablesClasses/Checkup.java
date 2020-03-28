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
public class Checkup {
    int ch_id,cost, fk_v_id;
    String ch_date ,end_date , notes,report;

    public Checkup(int ch_id, String ch_date, String end_date,int cost, String notes, int fk_v_id) {
        this.ch_id = ch_id;
        this.cost = cost;
        this.fk_v_id = fk_v_id;
        this.ch_date = ch_date;
        this.end_date = end_date;
        this.notes = notes;
    }

    public Checkup(String ch_date, String end_date, int cost, String notes, int fk_v_id) {
        this.cost = cost;
        this.fk_v_id = fk_v_id;
        this.ch_date = ch_date;
        this.end_date = end_date;
        this.notes = notes;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }    
        
    public int getCh_id() {
        return ch_id;
    }

    public void setCh_id(int ch_id) {
        this.ch_id = ch_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getFk_v_id() {
        return fk_v_id;
    }

    public void setFk_v_id(int fk_v_id) {
        this.fk_v_id = fk_v_id;
    }

    public String getCh_date() {
        return ch_date;
    }

    public void setCh_date(String ch_date) {
        this.ch_date = ch_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
