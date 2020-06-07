/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.dao;

import storemanagment.interfaces.IProductDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericDao;
import storemanagment.model.Product;

/**
 *
 * @author FAQ
 */
public class ProductDao extends GenericDao implements IProductDao{
    private ResultSet resultado;
    private PreparedStatement statement;
	
    public ProductDao(IConexion conexion) {
	super(conexion);
    }

    @Override
    public void save(Product entity) {
            Product product = null;
            try {
                    statement = conexion.prepareStatement("INSERT INTO products(nameProduct,priceProduct) VALUES(?,?)");
                    statement.setString(1, entity.getNameProduct());
                    statement.setDouble(2, entity.getPricePrduct());
                    statement.execute();
            } catch (SQLException e) {
                    e.printStackTrace();
            }finally {
                    try {
                            conexion.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }	
    }

   @Override
    public void delete(Integer id) {
            try {
                    statement = conexion.prepareStatement("DELETE FROM products WHERE idProduct = ?");
                    statement.setInt(1, id);
                    statement.executeQuery();
            } catch (SQLException e) {
                    e.printStackTrace();
            }finally {
                    try {
                            conexion.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
    }

    @Override
     public Product get(Integer id) {
         Product product = null;
         try {
                    statement = conexion.prepareStatement("SELECT * FROM products WHERE idProduct = ?");
                    statement.setInt(1, id);
                    resultado = statement.executeQuery();
                    
                    while(resultado.next()) {
                            product = new Product();
                            product.setIdProduct(resultado.getInt("idProduct"));
                            product.setNameProduct("nameProduct");
                            product.setPricePrduct(resultado.getDouble("pricePrduct")); 
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            }finally {
                   try {
                            conexion.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
            return product;
    }

    @Override
     public List<Product> getAll() {
            List<Product> list = new ArrayList<>();
            try {
                    statement = conexion.prepareStatement("SELECT * FROM products");
                    resultado = statement.executeQuery();
                    Product product = null;
                    while(resultado.next()) {
                            product = new Product();
                            product.setIdProduct(resultado.getInt("idProduct"));
                            product.setNameProduct(resultado.getString("nameProduct"));
                            product.setPricePrduct(resultado.getDouble("priceProduct")); 
                            list.add(product);
                    }
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }finally {
                   try {
                            conexion.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
            return list;
    }
     /**
      * Esta entidad no tiene clave foraña. 
      */

    @Override
    public List<Product> getAllForangeKey(String fkey) {
        List<Product> list = new ArrayList<>();
        try {
                    statement = conexion.prepareStatement("SELECT * FROM products where nameProduct = ? ");
                    statement.setString(1, "%"+fkey+"%");
                    resultado = statement.executeQuery();
                    Product product = null;
                    while(resultado.next()) {
                            product = new Product();
                            product.setIdProduct(resultado.getInt("idProduct"));
                            product.setNameProduct(resultado.getString("nameProduct"));
                            product.setPricePrduct(resultado.getDouble("pricePrduct")); 
                            list.add(product);
                    }
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }finally {
                   try {
                            conexion.close();
                    } catch (SQLException e) {
                            e.printStackTrace();
                    }
            }
            return list;
    }
}
