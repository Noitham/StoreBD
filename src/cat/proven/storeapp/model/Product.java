package cat.proven.storeapp.model;

import cat.proven.storeapp.Storeapp;
import java.util.Objects;
import java.util.Properties;

public class Product {

    Storeapp myStoreapp = new Storeapp();

    Properties mssgProps = myStoreapp.initConfig();

    //attributes
    private long id;
    private String code;
    private String name;
    private double price;

    
    //constructors
    public Product(long id) {
        this.id = id;
    }

    public Product(long id, String code, String name, double price) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
    }
    
    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public Product(String code) {
        this.code = code;
    }

    public Product() {

    }

    public Product(Product other) {
        this.code = other.code;
        this.name = other.name;
        this.price = other.price;
    }

    //accessors

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //methods
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.code);
        return hash;
    }

    /**
     * Compare product with another one Two products are equal if their codes
     * are equals
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        boolean b = false;

        if (obj == null) {//null object

            b = false;
        } else {

            if (obj == this) {//same object

                b = true;
            } else {

                if (obj instanceof Product) { //obj is product

                    Product other = (Product) obj;
                    b = (this.code.equals(other.code)) ? true : false;

                } else { //obj is not a product

                    b = false;

                }

            }
        }

        return b;
    }

    @Override
    public String toString() {
        //return "Product{" + "code=" + code + ", name=" + name + ", price=" + price + '}';
        StringBuilder sb = new StringBuilder();
        sb.append(mssgProps.getProperty("product") + "{");
        sb.append(mssgProps.getProperty("id") + "=");
        sb.append(id);
        sb.append(mssgProps.getProperty("code") + "=");
        sb.append(code);
        sb.append(", " + mssgProps.getProperty("name") + "=");
        sb.append(name);
        sb.append(", " + mssgProps.getProperty("price") + "=");
        sb.append(price);

        return sb.toString();
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append("PR;");
        sb.append(id);
        sb.append(";");
        sb.append(code);
        sb.append(";");
        sb.append(name);
        sb.append(";");
        sb.append(price);

        return sb.toString();
    }

}
