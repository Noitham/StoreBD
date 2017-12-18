
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
     * Search a product in the database with the given id
     * @param id to search
     * @return the product retrieved or null if not found in case of error.
     */
    public Product findById (long id)
    {
        Product p = productDao.findById(id);
        
        return p;
    }
    
    public Product findByCode(String code)
    {
        return null;
    }
    
    public List<Product> findAllProducts()
    {
        return null;
    }
    
    public List<Product> findByName(String name)
    {
        return null;
    }
    
    public List<Product> findByPrice (double price)
    {
        return null;
    }
    
    public int addProduct(Product product)
    {
        return 0;
    }
    
    public int modifyProduct(Product product)
    {
        return 0;
    }
    
    public int removeProduct(Product product)
    {
        return 0;
    }
    
}
