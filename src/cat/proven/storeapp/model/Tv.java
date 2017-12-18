/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp.model;

public class Tv extends Product {

    public int inches;

    public Tv(String code, String name, double price, int inches) {
        super(code, name, price);
        this.inches = inches;
    }

    public Tv(String code, String name, double price) {
        super(code, name, price);
    }

    public Tv(String code) {
        super(code);
    }

    public Tv() {
        super();
    }

    public Tv(Product product, int inches) {
        super(product);
        this.inches = inches;
    }

    public int getInches() {
        return inches;
    }

    public void setInches(int inches) {
        this.inches = inches;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mssgProps.getProperty("tv") + "(");
        sb.append(super.toString());
        sb.append(", " + mssgProps.getProperty("inches") + "=");
        sb.append(inches);
        sb.append("}");
        return sb.toString();

    }
    
    @Override
        public String toCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("TV;");
        sb.append(super.toCsv());
        sb.append(";");
        sb.append(inches);

        return sb.toString();
    }

}
