/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fjl.desktop.storemanagment.generic.GenericDao;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.interfaces.IPisDao;
import com.fjl.desktop.storemanagment.model.ProductInStore;



/**
 *
 * @author FAQ
 */
public class ProductInStoreDAO extends GenericDao implements IPisDao {
   /* 
    private int idProduct;
    private String nameProduct;
    private double pricePrduct;
    */
    private ResultSet resultado;
    private PreparedStatement statement;
    public ProductInStoreDAO(IConexion conexion) {
	super(conexion);
    }

    @Override
    public void save(ProductInStore entity){
        try {
            statement = conexion.prepareStatement("INSERT INTO products_stores (idProduct,idStore,stock) VALUES (?,?,?)");
            statement.setInt(1, entity.getIdProduct());
            statement.setInt(2,entity.getIdStrore());
            statement.setInt(3,entity.getStock());
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode()); 
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void delete(ProductInStore entity) {
    try {
            statement = conexion.prepareStatement("DELETE FROM products_stores WHERE idProduct = ?"
                                                 + "AND idStore = ?");
            statement.setInt(1, entity.getIdProduct());
            statement.setInt(2,entity.getIdStrore());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ProductInStore get(ProductInStore entity) {
        ProductInStore pis = null;
        try {
            statement = conexion.prepareStatement("SELECT * FROM products_stores WHERE idProduct = ?"
                                                 + "AND idStore = ?");
            statement.setInt(1, entity.getIdProduct());
            statement.setInt(2,entity.getIdStrore());
            resultado = statement.executeQuery();
            while(resultado.next()){
                pis = new ProductInStore();
                pis.setIdProduct(resultado.getInt("idProduct"));
                pis.setIdStrore(resultado.getInt("idStore"));
                pis.setStock(resultado.getInt("stock"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pis;
    }

    @Override
    public List<ProductInStore> getAll() {
        List<ProductInStore> listPis = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM products_stores ");
            statement.executeQuery();
            ProductInStore pis = null;
            while(resultado.next()){
                pis = new ProductInStore();
                pis.setIdProduct(resultado.getInt("idProduct"));
                pis.setIdStrore(resultado.getInt("idStore"));
                pis.setStock(resultado.getInt("stock"));
                listPis.add(pis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPis;
    }
    
    public List<ProductInStore> getAllFromStore(Integer idStore) {
        List<ProductInStore> listPis = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM products_stores WHERE idStore = ? ");
            statement.setInt(1, idStore);
            statement.executeQuery();
            ProductInStore pis = null;
            while(resultado.next()){
                pis = new ProductInStore();
                pis.setIdProduct(resultado.getInt("idProduct"));
                pis.setIdStrore(resultado.getInt("idStore"));
                pis.setStock(resultado.getInt("stock"));
                listPis.add(pis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPis;
    }
    
    public List<ProductInStore> getAllStock(Integer idProduct) {
        List<ProductInStore> listPis = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM products_stores WHERE idProduct = ? ");
            statement.setInt(1, idProduct);
            statement.executeQuery();
            ProductInStore pis = null;
            while(resultado.next()){
                pis = new ProductInStore();
                pis.setIdProduct(resultado.getInt("idProduct"));
                pis.setIdStrore(resultado.getInt("idStore"));
                pis.setStock(resultado.getInt("stock"));
                listPis.add(pis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPis;
    }

    @Override
    public List<ProductInStore> getAllForangeKey(Integer stock) {
        List<ProductInStore> listPis = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM products_stores WHERE stock >= ? ");
            statement.setInt(1, stock);
            statement.executeQuery();
            ProductInStore pis = null;
            while(resultado.next()){
                pis = new ProductInStore();
                pis.setIdProduct(resultado.getInt("idProduct"));
                pis.setIdStrore(resultado.getInt("idStore"));
                pis.setStock(resultado.getInt("stock"));
                listPis.add(pis);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductInStoreDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listPis;
    }
 }
