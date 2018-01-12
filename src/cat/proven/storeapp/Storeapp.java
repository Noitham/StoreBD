package cat.proven.storeapp;


import cat.proven.storeapp.model.Product;
import cat.proven.storeapp.model.Store;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author alumne
 */

public class Storeapp {

    //Application data: list of prducts
    private final Store myStore = new Store();
    
    
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
                    listAllProducts();
                    break;
                case 2: //Search by id
                    searchProductById();
                    break;
                case 3: //Search by code
                    searchByCode();
                    break;
                case 4: //Search by name
                    searchProductByName();
                    break;
                case 5: //Search by price
                    searchProductByPrice();
                    break;
                case 6: //Add new product
                    addNewProduct();
                    break;
                case 7: //Modify a product
                    modifyProduct();
                    break;
                case 8: //Delete a product
                    deleteProduct();
                    break;
                default:
                    System.out.println("Wrong option");
            }
        } while (!exit);
        System.out.println("Exitting app");

    }

    public Storeapp() {

    }
    
    /**
     * Lists all products from database
     */
    private void listAllProducts(){
        
        List<Product> product = myStore.findAllProducts();

        if (product != null) {
        //Print result
        System.out.println(product.toString());
        } else {//Product is null
        System.out.println("No products found");
        }
        
    }
    

    /**
     * Asks the user the code of the product to search. If the code has been
     * succesfully read, it searches a prodcut with the given code in the store.
     * If not. It reports it to the user If a product with the given code is
     * found, it will be shown to user If not, it reports that the product is
     * not in the store.
     */
    private void searchByCode() {
        //Ask the code
        String scode = input("Input code: ");
        try{
            //search the product
            Product product = myStore.findByCode(scode);
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
     * Asks the user the id of the product to search. If the id has been
     * succesfully read, it searches a prodcut with the given id in the store.
     * If not. It reports it to the user If a product with the given id is
     * found, it will be shown to user If not, it reports that the product is
     * not in the store.
     */
    private void searchProductById() {
        //Ask the code
        String sid = input("Input Id: ");
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
     * Asks the user the name to search. If the name has been succesfully
     * read, it searches a prodcut with the given name in the store. If not. It
     * reports it to the user If a product with the given name is found, it
     * will be shown to user If not, it reports that the are no products with
     * the given name in the store.
     */
    private void searchProductByName() {
        //Ask the name
        String name = input("Input name: ");
        try{
            //search product with the given price
            List<Product> product = myStore.findByName(name);

            if (product != null) {
            //Print result
            System.out.println(product.toString());
            
            } else {//Product is null
                System.out.println("Product not found");
            }
                
        } catch (NumberFormatException e) {
            System.out.println("Error reading name");
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
        String sprice = input("Input price: ");
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
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
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
        Product product;
        
        product = productForm();
        
        if (product == null) {
            System.out.println("Error adding product");

        } else {
            //Add the product to the store
        int b = myStore.addProduct(product);
            //report result to user
            if (b == 1) {
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
        //We check if product with same code is already in our database, in case it's not, we add it
        //In case code already exists, we tell the user.
        if (myStore.findByCode(product.getCode()) == null) {
            System.out.println("Enter name");
            product.setName(scan.next());
            System.out.println("Enter price");
            product.setPrice(scan.nextDouble());
        } else {
            System.out.println("Code already exists");
            product = null;
        }
        
        return product;
        
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
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter code");
        
        String code = scan.next();
        
        if (code != null) {
            //Search the product
            Product p = new Product(code);
            Product product = myStore.findByCode(code);
            if (product != null) {
                System.out.println(product.toString());
                //Asks for confirmation
                String answer = input("Confirm delete product" + "?" + "(Y/N)");
                if (answer.equals("Y")) {
                    
                    //Delete product
                    int b = myStore.removeProduct(product);
                    
                    if (b == 1) {
                        
                        System.out.println("Product succesfully deleted");
                    } else {
                        System.out.println("Product not deleted");
                    }

                } else {
                    System.out.println("Aborted per user");
                }

            } else {//Product is null
                System.out.println("Product not found");
            }
        } else {
            System.out.println("Error reading code");
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
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter code");
        
        String codeSearch = scan.next();
        
        String code;
        String name;
        int comprobacion = 0;
        double price;

        if (codeSearch != null) {
            //Search the product
            Product product = myStore.findByCode(codeSearch);

            if (product != null) {
                System.out.println(product.toString());
                System.out.println("Enter new code");
                code = scan.next();
                System.out.println("Enter new name");
                name = scan.next();
                try {
                    do {
                        
                        System.out.println("Enter new price");
                        price = Double.parseDouble(scan.next());
                        
                        if (price < 0) {
                            System.out.println("Price entered is lower than 0");

                        }
                    } while (price < 0);

                    for (int i = 0; i < myStore.findAllProducts().size(); i++) {

                        if (code.equals(myStore.findByCode(code))) {
                            comprobacion = 1;

                        } else {
                        }

                    }

                    if (comprobacion == 0) {
                        product.setCode(code);
                        product.setName(name);
                        product.setPrice(price);
                        System.out.println("Product modified");
                        //p = new Product(id, code, name, price);
                        myStore.modifyProduct(product);
                        
                    } else {

                        System.out.println("Code repeated");
                    }
                } catch (NumberFormatException e) {

                    System.out.println("Wrong data");
                    product = null;
                }

            } else {//Product is null
                System.out.println("Product not found");
            }
        } else {
            System.out.println("Error reading code");
        }

    }
    
}