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
public class PaymentPageTableRow {
    String p_name , visitType , date;

    public PaymentPageTableRow(String p_name, String visitType, String date) {
        this.p_name = p_name;
        this.visitType = visitType;
        this.date = date;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
