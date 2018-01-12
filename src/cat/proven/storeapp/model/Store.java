
package cat.proven.storeapp.model;

import cat.proven.storeapp.model.persist.ProductDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dmora
 */

public class Store {
    
    private ProductDao productDao;

    public Store() {
        try {
            productDao = new ProductDao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Search all the products in the database
     * @return the list of products
     */
    public List<Product> findAllProducts()
    {
        List<Product> p = productDao.listAll();
        
        return p;
    }
    
    
    /**
     * Search a product in the database with the given id
     * @param id to search
     * @return the product retrieved or null if not found in case of error.
     */
    public Product findById (long id)
    {
        Product p = productDao.findById(id);
        
        return p;
    }
    
    
    /**
     * Search a product in the database with the given code
     * @param code to search
     * @return the product retrieved or null if not found in case of error.
     */
    public Product findByCode(String code)
    {
        Product p = productDao.findByCode(code);
        
        return p;
    }
    
    
    /**
     * Search a product in the database with the given name
     * @param name to search
     * @return the product retrieved or null if not found in case of error.
     */
    public List<Product> findByName(String name)
    {
        List<Product> p = productDao.findByName(name);
        
        return p;
    }
    
    
    /**
     * Search a product in the database with the given price
     * @param price to search
     * @return the product retrieved or null if not found in case of error.
     */
    public List<Product> findByPrice (double price)
    {
        List<Product> p = productDao.findByPrice(price);
        
        return p;
    }
    
    
    /**
     * Adds product in the database
     * @param product to add
     * @return the product added
     */
    public int addProduct(Product product)
    {
        int p = productDao.insert(product);
        
        return p;
    }
    
    
    /**
     * Modifies product in the database
     * @param product to add
     * @return the product modified
     */
    public int modifyProduct(Product product)
    {
        int p = productDao.update(product);
        
        return p;
    }
    
    
    /**
     * Deletes a product from the database
     * @param product to add
     * @return the product deleted
     */
    public int removeProduct(Product product)
    {
        int p = productDao.delete(product);
        
        return p;
    }
    
}
