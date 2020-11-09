/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fjl.desktop.storemanagment.generic.GenericDao;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.interfaces.ISellDao;
import com.fjl.desktop.storemanagment.model.Sell;
import static java.sql.Date.valueOf;
import java.util.ArrayList;

/**
 *
 * @author FAQ
 */
public class SellDao extends GenericDao implements ISellDao {
    private ResultSet resultado;
    private PreparedStatement statement;

    public SellDao(IConexion conexion) {
        super(conexion);
    }

    @Override
    public void save(Sell entity) {
        try {
            statement = conexion.prepareStatement("INSERT INTO sales (idProduct,idStore,year) VALUES (?,?,?)");
            statement.setInt(1, entity.getIdProduct());
            statement.setInt(2,entity.getIdStore());
            statement.setDate(3, valueOf(entity.getDate()));
            statement.execute();
        } catch (SQLException ex) {
            System.out.println(statement.toString());
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            statement = conexion.prepareStatement("DELETE FROM sales WHERE idSales = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Sell get(Integer id) {
        Sell sell = null;
        try {
            statement = conexion.prepareStatement("SELECT * FROM sales WHERE idSales = ?");
            statement.setInt(1, id);
            resultado = statement.executeQuery();
            while(resultado.next()){
                sell = new Sell();
                sell.setIdSell(resultado.getInt("idSales"));
                sell.setIdProduct(resultado.getInt("idProduct"));
                sell.setDate(resultado.getDate("year").toLocalDate());
                sell.setIdStore(resultado.getInt("idStore"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return sell;
    }

    @Override
    public List<Sell> getAll() {
        List<Sell> listSell = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM sales");
            resultado = statement.executeQuery();
            Sell sell = null;
            while(resultado.next()){
                sell = new Sell();
                sell.setIdSell(resultado.getInt("idSales"));
                sell.setIdProduct(resultado.getInt("idProduct"));
                sell.setDate(resultado.getDate("year").toLocalDate());
                sell.setIdStore(resultado.getInt("idStore"));
                listSell.add(sell);
             }
        } catch (SQLException ex) {
            Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return listSell;
    }

    @Override
    public List<Sell> getAllForangeKey(Integer idProduct) {
          List<Sell> listSell = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM sales where idProduct = ?");
            statement.setInt(1, idProduct);
            resultado = statement.executeQuery();
            Sell sell = null;
            while(resultado.next()){
                sell = new Sell();
                sell.setIdSell(resultado.getInt("idSales"));
                sell.setIdProduct(resultado.getInt("idProduct"));
                sell.setDate(resultado.getDate("year").toLocalDate());
                sell.setIdStore(resultado.getInt("idStore"));
                listSell.add(sell);
             }
        } catch (SQLException ex) {
            Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(SellDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return listSell;
    }
    
}
