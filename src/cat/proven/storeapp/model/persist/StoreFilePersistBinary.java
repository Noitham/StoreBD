package cat.proven.storeapp.model.persist;

import cat.proven.storeapp.model.Fridge;
import cat.proven.storeapp.model.Microwave;
import cat.proven.storeapp.model.Product;
import cat.proven.storeapp.model.StoreList;
import cat.proven.storeapp.model.Tv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmora
 */
public class StoreFilePersistBinary implements StoreFilePersistInterface {

    private String filename;

    public StoreFilePersistBinary(String filename) {
        this.filename = filename;
    }

    public void setFilename(String namefile) {
        this.filename = filename;
    }

    public String getFileName() {
        return this.filename;
    }

    public StoreFilePersistBinary() {
    }

    @Override //TODO
    public int save(StoreList store) {

        int flag = 0;
        FileOutputStream fos = null;
        Product p = new Product();
        try {

            fos = new FileOutputStream(filename);

            for (Product pr : store.getProducts()) {

                String string = pr.toCsv();
                byte[] c = string.getBytes(StandardCharsets.UTF_8);

                fos.write(c);
                fos.write('\n');
                
                flag = 1;

            }

            fos.close();

        } catch (FileNotFoundException ex) {
            flag = 0;
            Logger.getLogger(StoreFilePersistBinary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            flag = 0;
            Logger.getLogger(StoreFilePersistBinary.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                flag = 0;
                Logger.getLogger(StoreFilePersistBinary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;

    }

    /**
     * This method load the data from the csv in format binary and put it in the
     * store
     *
     * @return the StoreList with the data loaded from the csv
     */
    @Override
    public StoreList load() {

        StoreList store = new StoreList();
        int x;
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fr = new FileInputStream(filename);
            while ((x = fr.read()) != -1) {
                sb.append((char) x);
            }
            String[] tokens = sb.toString().split("\n");
            for (String s : tokens) {
                store.add(fromCsvProduct(s, ";"));
            }

            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StoreFilePersistCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StoreFilePersistCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return store;

    }

    /**
     *
     * @param s all of the data from the csv
     * @param delimiter that we are going to use to separate the products
     * @return the product
     */
    public static Product fromCsvProduct(String s, String delimiter) {

        Product p;
        String[] tokens = s.split(delimiter);
        String code, name;
        int potence, capacity, inches;
        double price;
        switch (tokens[0]) {

            case "PR":
                code = tokens[1];
                price = Double.parseDouble(tokens[3]);
                name = tokens[2];
                p = new Product(code, name, price);

                break;
            case "FR":
                code = tokens[2];
                price = Double.parseDouble(tokens[4]);
                name = tokens[3];
                capacity = Integer.parseInt(tokens[5]);
                p = new Fridge(code, name, price, capacity);
                break;
            case "TV":
                code = tokens[2];
                price = Double.parseDouble(tokens[4]);
                name = tokens[3];
                inches = Integer.parseInt(tokens[5]);
                p = new Tv(code, name, price, inches);
                break;
            case "MW":
                code = tokens[2];
                price = Double.parseDouble(tokens[4]);
                name = tokens[3];
                potence = Integer.parseInt(tokens[5]);
                p = new Microwave(code, name, price, potence);
                break;
            default:
                p = null;
                break;

        }

        return p;
    }
}
