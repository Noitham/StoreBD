package cat.proven.storeapp;

import cat.proven.storeapp.model.Fridge;
import cat.proven.storeapp.model.Product;
import cat.proven.storeapp.model.Tv;
import cat.proven.storeapp.model.Microwave;
import cat.proven.storeapp.model.Store;
import cat.proven.storeapp.model.persist.StoreFilePersistBinary;
import cat.proven.storeapp.model.persist.StoreFilePersistCsv;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author alumne
 */

public class Storeapp {

    //Application data: list of prducts
    private Store myStore = new Store();
    
    
    public static void main(String[] args) throws FileNotFoundException {
        Storeapp storeApp = new Storeapp();
        storeApp.run();

    }

    public void run() throws FileNotFoundException {

        StoreMenu mainMenu = new StoreMenu("Store");
        boolean exit = false;
        int optionSelected;

        do {
            mainMenu.show();
            System.out.print("Select an option");
            System.out.println("");
            optionSelected = mainMenu.choose();
            // Process option
            switch (optionSelected) {
                case 0: // exit
                    exit = true;
                    break;
                case 1: //List all Products
                    
                    break;
                case 2: //Search by code
                    searchProductById();
                    break;
                case 3: //Search by price
                    searchProductByPrice();
                    break;
                case 4: //Add new product
                    addNewProduct();
                    break;
                case 5: //Modify a product
                    modifyProduct();
                    break;
                case 6: //Delete a product
                    deleteProduct();
                    break;
                case 7: //Load from bin
                    loadFileBinary();
                    break;
                case 8: //Save to bin
                    saveFileBinary();
                    break;
                case 9: //Load from csv
                    loadFile();
                    break;
                case 10: //Save to csv
                    saveFile();
                    break;
                default:
                    System.out.println("Wrong option");
            }
        } while (!exit);
        System.out.println("Exitting app");

    }


    public Storeapp() {

    }
    

    //TODO
    private void searchByCode() {
        System.out.println("Searching by code");
    }
    

    /**
     * Asks the user the code of the product to search. If the code has been
     * succesfully read, it searches a prodcut with the given code in the store.
     * If not. It reports it to the user If a product with the given code is
     * found, it will be shown to user If not, it reports that the product is
     * not in the store.
     */
    private void searchProductById() {
        //Ask the code
        String sid = input("Input Id");
        try{
            long id = Long.parseLong(sid);
            //search the product
            Product product = myStore.findById(id);
            if (product != null) {
                //Print result
                System.out.println(product.toString());
            } else {//Product is null
                System.out.println("Product not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Errror reading code");
        }
    }

    /**
     * Asks the user the price to search. If the price has been succesfully
     * read, it searches a prodcut with the given price in the store. If not. It
     * reports it to the user If a product with the given price is found, it
     * will be shown to user If not, it reports that the are no products with
     * the given price in the store.
     */
    private void searchProductByPrice() {
        //Ask the price
        String sprice = input("Input price");
        try{
                double price = Double.parseDouble(sprice);

                //search product with the given price
                List<Product> product = myStore.findByPrice(price);

                if (product != null) {
                //Print result
                System.out.println(product.toString());
            } else {//Product is null
                System.out.println("Product not found");
            }
                
        } catch (NumberFormatException e) {
            System.out.println("Errror reading price");
        }
    }

    /**
     * Shows a question to the user and reads their answer.
     *
     * @param message to be shown
     * @return User input
     */
    private String input(String message) {
        System.out.print(message);
        Scanner scan = new Scanner(System.in);
        String answer = scan.next();
        return answer;
    }

    /**
     * Shows a form to input product data. if data has been succesfully entered
     * it adds the new product to the store. If not, it reports the erro to the
     * user. Preventing code duplication is a must. Reporting errors to the user
     * is a must, as well.
     */
    private void addNewProduct() {
        //Ask the user to input data for the new product.
        Scanner scan = new Scanner(System.in);
        Product product;
        //System.out.println("Select your option: 1-TV. 2-Fridge. 3-Microwave. 4-Other.");
        System.out.println("Select option" + "1-" + "Tv" + ". " + "2-" + "Fridge" + ". " + "3-" + "Micro" + ". " + "4-" + "Other" + ". ");

        int option = scan.nextInt();
        switch (option) {
            case 1:
                product = productFormTV();
                myStore.addProduct(product);
                break;
            case 2:
                product = productFormFridge();
                myStore.addProduct(product);
                break;
            case 3:
                product = productFormMicrowave();
                myStore.addProduct(product);
                break;
            case 4:
                product = productForm();
                myStore.addProduct(product);
                break;
            default:
                product = null;
                break;
        }
        if (product == null) {
            System.out.println("Error reading product");

        } else {
            //Add the product to the store
            int b = myStore.addProduct(product);
            //report result to user
            if (b == 0) {
                System.out.println("Product succesfully added");
            } else {
                System.out.println("Error adding product");
            }
        }

    }

    /**
     * collects data from the user for a product.
     *
     * @return a product with the data entered by the user or null in case of
     * error.
     *
     */
    private Product productForm() {
        Scanner scan = new Scanner(System.in);
        Product product = new Product();

        System.out.println("Enter code");
        product.setCode(scan.next());
        if (myStore.find(product) == null) {
            System.out.println("Enter name");
            product.setName(scan.next());
            System.out.println("Enter price");
            product.setPrice(scan.nextDouble());
        } else {
            System.out.println(mssgProps.getProperty("codealreadyexists"));
            product = null;
        }
        
        return product;
    }

    private Product productFormTV() {

        Product product = this.productForm();
        Scanner scan = new Scanner(System.in);

        System.out.println(mssgProps.getProperty("inputinches"));
        Tv tvf = new Tv(product, scan.nextInt());

        return tvf;

    }

    private Product productFormFridge() {

        Product product = this.productForm();
        Scanner scan = new Scanner(System.in);

        System.out.println(mssgProps.getProperty("inputcapacity"));
        Fridge fridgef = new Fridge(product, scan.nextInt());

        return fridgef;

    }

    private Product productFormMicrowave() {

        Product product = this.productForm();
        Scanner scan = new Scanner(System.in);

        System.out.println(mssgProps.getProperty("inputpower"));
        Microwave mwf = new Microwave(product, scan.nextInt());

        return mwf;

    }

    /**
     * Asks the user the code of the product to delete In case of error reading
     * the code, it reports the error to the user. If not, it searches the
     * product with the given code in the sore. If not found, it reports the
     * error to the user. If found, it will show the product, and ask the user
     * for confirmation. If the user confirms, it deletes the preduct from the
     * store. Otherwise, it reports it to the user.
     */
    private void deleteProduct() {
        //Ask the code
        String code = input(mssgProps.getProperty("entercode"));
        if (code != null) {
            //Search the product
            Product p = new Product(code);
            Product product = myStore.find(p);
            if (product != null) {
                System.out.println(product.toString());
                //Asks for confirmation
                String answer = input(mssgProps.getProperty("confirmdeleteproduct") + "?" + "(Y/N)");
                if (answer.equals("Y")) {
                    //Delete product
                    boolean b;
                    if (b = true) {
                        b = myStore.remove(product);
                        System.out.println(mssgProps.getProperty("productdeleted"));
                    } else {
                        System.out.println(mssgProps.getProperty("productnotdeleted"));
                    }

                } else {
                    System.out.println(mssgProps.getProperty("abortedperuser"));
                }

            } else {//Product is null
                System.out.println(mssgProps.getProperty("productnotfound"));
            }
        } else {
            System.out.println(mssgProps.getProperty("errorreadingcode"));
        }
    }

    
    /**
     * Asks the user the code of the product to modify. In case of error reading
     * the code, it reports it to the user. Searches the product in the store.
     * If not found, it reports that to the user. Shows the product to modify to
     * user. Asks for new data to the user. Checks validity of data. Modifies
     * the product and reports the data to the user.
     */
    private void modifyProduct() {

        String codeSearch = input(mssgProps.getProperty("entercode"));
        String code;
        String name;
        int comprobacion = 0;
        double price;

        if (codeSearch != null) {
            //Search the product
            Product p = new Product(codeSearch);
            Product product = myStore.find(p);

            if (product != null) {
                System.out.println(product.toString());
                code = input(mssgProps.getProperty("entercode"));
                name = input(mssgProps.getProperty("entername"));

                try {
                    do {

                        price = Double.parseDouble(input(mssgProps.getProperty("enterprice")));
                        if (price < 0) {
                            System.out.println(mssgProps.getProperty("pricelower0"));

                        }
                    } while (price < 0);

                    for (int i = 0; i < myStore.getNumProducts(); i++) {

                        if (code.equals(myStore.get(i).getCode())) {
                            comprobacion = 1;

                        } else {
                        }

                    }

                    if (comprobacion == 0) {
                        myStore.modify(product, code, name, price);
                        System.out.println(mssgProps.getProperty("codemodified"));
                    } else {

                        System.out.println(mssgProps.getProperty("coderepeated"));
                    }
                } catch (NumberFormatException e) {

                    System.out.println(mssgProps.getProperty("wrongdata"));
                    product = null;
                }

            } else {//Product is null
                System.out.println(mssgProps.getProperty("productnotfound"));
            }
        } else {
            System.out.println(mssgProps.getProperty("errorreadingcode"));
        }
    }

    private void saveFile() {
        StoreFilePersistCsv spc = new StoreFilePersistCsv(PATH);
        int b = spc.save(myStore);
        if (b == 1) {
            System.out.println("Saved!");
        } else {
            System.out.println("Not Saved!");
        }
    }

    public void loadFile() {
        StoreFilePersistCsv spc = new StoreFilePersistCsv(PATH);
        myStore = spc.load();
        if (myStore != null) {
            System.out.println("File loaded");
        } else {
            System.out.println("File not loaded");
        }

    }
    
    private void saveFileBinary() {
        StoreFilePersistBinary spc = new StoreFilePersistBinary(PATH2);
        int b = spc.save(myStore);
        if (b == 1) {
            System.out.println("Saved!");
        } else {
            System.out.println("Not Saved!");
        }
    }
        
    public void loadFileBinary() {

        StoreFilePersistBinary spc = new StoreFilePersistBinary(PATH2);
        myStore = spc.load();
        if (myStore != null) {
            System.out.println("File loaded");
        } else {
            System.out.println("File not loaded");
        }

    }

}