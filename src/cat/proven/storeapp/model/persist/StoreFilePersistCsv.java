package cat.proven.storeapp.model.persist;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import cat.proven.storeapp.model.Tv;
import cat.proven.storeapp.model.Microwave;
import cat.proven.storeapp.model.Product;
import cat.proven.storeapp.model.StoreList;
import cat.proven.storeapp.model.Fridge;

public class StoreFilePersistCsv implements StoreFilePersistInterface {

    private String filename;

    public StoreFilePersistCsv(String f) {
        filename = f;
    }

    public String getFileName() {
        return filename;
    }

    public void setFilename(String f) {
        this.filename = f;
    }

    @Override
    public int save(StoreList store) {

        int flag;
        try {
            FileWriter fw = new FileWriter(filename);
            for (Product p : store.getProducts()) {
                fw.write(p.toCsv());
                fw.write("\n");
            }
            flag = 1;
            fw.close();
        } catch (IOException ex) {
            System.out.println("File not found");
            flag = 0;
        }

        return flag;
    }

    @Override
    public StoreList load() {

        StoreList store = new StoreList();
        int x;
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(filename);
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

    public static Product fromCsvProduct(String s, String delimiter) {

        Product p;
        String[] tokens = s.split(";");
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
