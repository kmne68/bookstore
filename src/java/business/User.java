/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author kmne6
 */
public class User {

    private int userid, storeid, password, pwdattempt;
    private String username, adminlevel;

    public User() {
        userid = 0;
        storeid = 0;
        password = 0;
        pwdattempt = -1;
        username = "";
        adminlevel = "";
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPwdattempt() {
        return pwdattempt;
    }

    public void setPwdattempt(int pwdattempt) {
        this.pwdattempt = pwdattempt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminlevel() {
        return adminlevel;
    }

    public void setAdminlevel(String adminlevel) {
        this.adminlevel = adminlevel;
    }

    public boolean isAuthenticated() {

        if (this.password <= 0) {
            return false;
        }
        return (this.password == this.pwdattempt);
        
    }

}
