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
    private int quantity;
    private String title;
    private double price;
    
    public Inventory() {
        storeid = 0;
        bookcd = "";
        quantity = 0;
        title = "";
        price = 0;
    }
    
    public Inventory(int storeID, String bookcd, int quantity, String title, double price  ) {
        this.storeid = storeID;
        this.bookcd = bookcd;
        this.quantity = quantity;
        this.title = title;
        this.price = price;       
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
