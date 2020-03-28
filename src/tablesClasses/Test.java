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
public class Test {
    int t_id;
    String t_name;

    public Test(int t_id, String t_name) {
        this.t_id = t_id;
        this.t_name = t_name;
    }

    public Test(String t_name) {
        this.t_name = t_name;
    }
    

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }
    
    
}
