/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.storeapp.model.persist;

import cat.proven.storeapp.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmora
 */
public class ProductDao {
    
    private final DBConnect dbConnect;
    
    private final String QUERY_FIND_BY_ID = "select * from store where id=?";

    public ProductDao() throws ClassNotFoundException {
        dbConnect = new DBConnect();
        
    }
    
    
    /**
     * Converts resultset row to product
     * @param rs resultset with data
     * @return a product with data given by curren resultset row
     * @throws SQLException 
     */
    private Product resultsetToProduct(ResultSet rs) throws SQLException {
        
        Product p = null;
        long id = rs.getLong("id");
        String code = rs.getString("code");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        
        return p;
        
    }
    
    /**
     * Retrieves the product with the given id from the database
     * @param id to search 
     * @return a product with the given id or null if not found
     */
    public Product findById(long id){
        
        Product p = null;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_ID);
                st.setLong(1, id);
                ResultSet rs = st.executeQuery();
                rs.next();
                p = resultsetToProduct(rs);
            }
            
        } catch (SQLException ex) {
            p = null;
        }
        return p;
    }
    
    
    //TODO (faltan metodos)
    //Documentar
    
    public int insert(Product product)
    {
        return 0;
    }
    
    
    public int update(Product product){
        return 0;
    }
    
    
    public int delete(Product product)
    {
        return 0;
    }
    
}
