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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmora
 */
public class ProductDao {
    
    private final DBConnect dbConnect;
    
    private final String QUERY_LIST_ALL = "select * from products";
    private final String QUERY_FIND_BY_ID = "select * from products where id=?";
    private final String QUERY_FIND_BY_CODE = "select * from products where code=?";
    private final String QUERY_FIND_BY_NAME = "select * from products where name=?";
    private final String QUERY_FIND_BY_PRICE = "select * from products where price=?";
    private final String QUERY_INSERT_PRODUCT = "insert into products (code, name, price) values (?, ?, ?)";
    private final String QUERY_DELETE_PRODUCT = "delete from products where id =?";
    private final String QUERY_MODIFY_PRODUCT = "update products set code=?, name=?, price=? where id=?";

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
        
        p = new Product(id, code, name, price);
        
        return p;
        
    }
    
    /**
     * Retrieves all products from the database
     * @return all products from the database
     */
    public List<Product> listAll(){
        
        List <Product> productlist = new ArrayList<>();
        
        Product p = null;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_LIST_ALL);
                ResultSet rs = st.executeQuery();
                while(rs.wasNull() == false){
                rs.next();
                p = resultsetToProduct(rs);
                productlist.add(p);
                }
            }
            
        } catch (SQLException ex) {
            p = null;
        }
        return productlist;
    }
    
    
    public Product findByCode(String code){
        
        Product p = null;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_CODE);
                st.setString(1, code);
                ResultSet rs = st.executeQuery();
                rs.next();
                p = resultsetToProduct(rs);
            }
            
        } catch (SQLException ex) {
            p = null;
        }
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
    
    /**
     * Retrieves the product with the given price from the database
     * @param price to search 
     * @return a product with the given id or null if not found
     */
    public List<Product> findByPrice(double price){
        
        List <Product> productlist = new ArrayList<>();
        
        Product p = null;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_PRICE);
                st.setDouble(1, price);
                
                ResultSet rs = st.executeQuery();
                
                while(rs.wasNull() == false){
                rs.next();
                p = resultsetToProduct(rs);
                productlist.add(p);
                }
            }
            
        } catch (SQLException ex) {
            p = null;
        }
        return productlist;
    }
    
    
    /**
     * Retrieves the product with the given id from the database
     * @param name to search 
     * @return a product with the given id or null if not found
     */
    public List<Product> findByName(String name){
        
        List <Product> productlist = new ArrayList<>();
        
        Product p = null;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_FIND_BY_NAME);
                st.setString(1, name);
                
                ResultSet rs = st.executeQuery();
                while(rs.wasNull() == false){
                rs.next();
                p = resultsetToProduct(rs);
                productlist.add(p);
                }
            }
            
        } catch (SQLException ex) {
            p = null;
        }
        return productlist;
    }
    
    //Documentar
    
    /**
     * Inserts product in the database
     * @param product to add
     * @return the product added
     */
    public int insert(Product product)
    {
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_INSERT_PRODUCT);
                st.setString(1, product.getCode());
                st.setString(2, product.getName());
                st.setDouble(3, product.getPrice());

                st.executeUpdate();
            }
            
        } catch (SQLException ex) {
            return 0;
        }
        
        return 1;
    }
    
    /**
     * Updates product in the database
     * @param product to add
     * @return the product modified
     */
    public int update(Product product)
    {
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_MODIFY_PRODUCT);
                st.setString(1, product.getCode());
                st.setString(2, product.getName());
                st.setDouble(3, product.getPrice());
                st.setLong(4, product.getId());
                System.out.println(product);

                st.executeUpdate();
            }
            
        } catch (SQLException ex) {
            return 0;
        }
        
        return 1;
    }
    
    /**
     * Deletes a product from the database
     * @param product to add
     * @return the product deleted
     */
    public int delete(Product product)
    {
        Product p = product;
        
        try {
            
            Connection connection = dbConnect.getConnection();
            
            if(connection != null){
                
                PreparedStatement st = connection.prepareStatement(QUERY_DELETE_PRODUCT);
                st.setLong(1, product.getId());

                st.executeUpdate();
            }
            
        } catch (SQLException ex) {
            p = null;
            return 0;
        }
        
        return 1;
        
    }
    
    
}
