/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp.model;

/**
 *
 * @author dmora
 */
public class Fridge extends Product {
    
    
    
    private int capacity;
    
    public Fridge(String code, String name, double price, int capacity){
        super(code, name, price);
        this.capacity = capacity;
    }

    public Fridge(String code, String name, double price) {
        super(code, name, price);
    }
    
    public Fridge(String code){
        super(code);
    }
    
    public Fridge(){
        super();
    }
    
    public Fridge(Product product, int capacity){
        super(product);
        this.capacity = capacity;
    }
    
    public int getCapacity(){
        return capacity;
    }
    
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(mssgProps.getProperty("fridge") + "(");
        sb.append(super.toString());
        sb.append(", " + mssgProps.getProperty("capacity")+"=");sb.append(capacity);
        sb.append("}");
        return sb.toString();
        
    }
    
    @Override
    public String toCsv(){
        StringBuilder sb = new StringBuilder();
        sb.append("FR;");
        sb.append(super.toCsv());
        sb.append(";");
        sb.append(capacity);
        
        return sb.toString();
    }
    
    
    
}
