package cat.proven.storeapp.model;

import cat.proven.storeapp.Storeapp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class StoreList {

    private final ArrayList<Product> products; //List of products

    //costructor
   public StoreList() {

        this.products = new ArrayList<Product>();
    }

    //accesors
    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getNumProducts() {
        return products.size();
    }

    //methods
    //add new product
    //modify a product
    //delete a product
    //find a product
    /**
     * Gets element of position Index
     *
     * @param index position of element to retrieve
     * @return element at position index or null if not exists.
     */
    public Product get(int index) {

        return products.get(index);

    }
    


    /**
     * Adds a new product to the store It prevents writing if list is full. It
     * avoids duplicates
     *
     * @param product to add.
     * @return true if product has been successfully added false otherwise
     */
    public boolean add(Product product) {

        boolean b;

        if (!product.equals(this.find(product))) {
            //ToDo avoid duplicates
            products.add(product);
            //increase counter
            b = true;

        } else {//list is full

            b = false;
        }

        return b;
    }

    /**
     * Search p in the list.
     *
     * @param p is the product to find
     * @return the product found or null if not found.
     */
    public Product find(Product p) {
        Product found = null;
        for (int i = 0; i < products.size(); i++) {
            if (get(i).equals(p)) {
                found = get(i);
                break;
            }

        }

        return found;
    }

    /**
     * Search products with the given price.
     *
     * @param price to find
     * @return with the list of products with the given price or an empty store
     * if no one is found.
     */
    public StoreList findByPrice(double price) {

        StoreList found = new StoreList();
        for (int i = 0; i < products.size(); i++) {
            if (get(i).getPrice() == price) {
                found.add(get(i));
            }
        }
        return found;
    }

    /**
     * removes a product from the store.
     *
     * @param product to remove
     * @return true if product has been removed, false otherwise.
     */
    public boolean remove(Product product) {

        boolean b = false;
        if (product != null) {
            b = products.remove(product);
        }

        return b;
    }

    /**
     * searches a product and gives its position
     *
     * @param product to search
     * @return position of product or -1 if not found.
     */
    public int indexOf(Product product) {

        return products.indexOf(product);
    }

    public void modify(Product product, String code, String name, double price) {

        Storeapp myStoreapp = new Storeapp();

        Properties mssgProps = myStoreapp.initConfig();

        Scanner scan = new Scanner(System.in);

        int index = indexOf(product);

        if (product.getClass().getSimpleName().equals("Tv")) {

            Tv tv = (Tv) product;

            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            System.out.println(mssgProps.getProperty("inputinches"));
            tv.setInches(scan.nextInt());

        } else if (product.getClass().getSimpleName().equals("Fridge")) {

            Fridge f = (Fridge) product;

            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            System.out.println(mssgProps.getProperty("inputcapacity"));
            f.setCapacity(scan.nextInt());

        } else if (product.getClass().getSimpleName().equals("Microwave")) {

            Microwave mr = (Microwave) product;

            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            System.out.println(mssgProps.getProperty("inputpower"));
            mr.setPower(scan.nextInt());
        } else {

            if (product != null) {
                product.setCode(code);
                product.setName(name);
                product.setPrice(price);
            }

        }
    }
}
