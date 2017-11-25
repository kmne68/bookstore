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
public class Store {
    
    private int storeid;
    private int storeemp;
    private String storename, storeaddr;
    
    public Store() {
        storeid = 0;
        storeemp = 0;
        storename = "";
        storeaddr = "";
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getStoreemp() {
        return storeemp;
    }

    public void setStoreemp(int storeemp) {
        this.storeemp = storeemp;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoreaddr() {
        return storeaddr;
    }

    public void setStoreaddr(String storeaddr) {
        this.storeaddr = storeaddr;
    }
    
    
}
