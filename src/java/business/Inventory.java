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
public class Inventory {
    
    // Store, Book Cd, Title, Retail Price, Quantity
    private int storeid;
    private String bookcd;
    private String title;
    private double price;
    private int quantity;
    
    public Inventory() {
        storeid = 0;
        bookcd = "";
        title = "";
        price = 0;
        quantity = 0;
    }
    
    public Inventory(int storeID, String bookcd, String title, double price, int quantity  ) {
        this.storeid = storeID;
        this.bookcd = bookcd;
        this.title = title;
        this.price = price;
        this.quantity = quantity;       
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public String getBookcd() {
        return bookcd;
    }

    public void setBookcd(String bookcd) {
        this.bookcd = bookcd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
