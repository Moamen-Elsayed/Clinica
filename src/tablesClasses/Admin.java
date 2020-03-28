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
public class Admin {
    String userName ,password;
    int fk_d_id ;

    public Admin(String userName, String password, int fk_d_id) {
        this.userName = userName;
        this.password = password;
        this.fk_d_id = fk_d_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFk_d_id() {
        return fk_d_id;
    }

    public void setFk_d_id(int fk_d_id) {
        this.fk_d_id = fk_d_id;
    }
    
    
}
