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
import com.fjl.desktop.storemanagment.interfaces.IServiceStoreHome;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.model.StoreHome;

/**
 *
 * @author FAQ
 */
public class StoreHomeDao extends GenericDao implements IServiceStoreHome {
    
    private ResultSet resultado;
    private PreparedStatement statement;

    public StoreHomeDao(IConexion conexion) {
        super(conexion);
    }



    @Override
    public void save(StoreHome entity) {
        
        try {
            statement = conexion.prepareStatement("INSERT INTO storehouse (nameStore,idLocation) VALUES (?,?)");
            statement.setString(1, entity.getNameStore());
            statement.setInt(2, entity.getIdLocation());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            statement = conexion.prepareStatement("DELETE FROM storehouse WHERE idStore = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public StoreHome get(Integer id) {
        StoreHome store = null;
        try {
            statement = conexion.prepareStatement("SELECT * FROM storehouse WHERE idStore = ?");
            statement.setInt(1, id);
            resultado = statement.executeQuery();
            while(resultado.next()){
                store = new StoreHome();
                store.setIdStore(resultado.getInt("idStore"));
                store.setIdLocation(resultado.getInt("idLocation"));
                store.setNameStore(resultado.getString("nameStore"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return store;
        }
    }

    @Override
    public List<StoreHome> getAll() {
        List<StoreHome> listStore = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM storehouse");
            resultado = statement.executeQuery();
            StoreHome store = null;
            while(resultado.next()){
                store = new StoreHome();
                store.setIdStore(resultado.getInt("idStore"));
                store.setIdLocation(resultado.getInt("idLocation"));
                store.setNameStore(resultado.getString("nameStore"));
                listStore.add(store);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listStore;
        }
    }

    @Override
    public List<StoreHome> getAllForangeKey(Integer fkey) {
        List<StoreHome> listStore = new ArrayList<>();
        try {
            statement = conexion.prepareStatement("SELECT * FROM storehouse idLocation = ?");
            statement.setInt(1, fkey);
            resultado = statement.executeQuery();
            StoreHome store = null;
            while(resultado.next()){
                store = new StoreHome();
                store.setIdStore(resultado.getInt("idStore"));
                store.setIdLocation(resultado.getInt("idLocation"));
                store.setNameStore(resultado.getString("nameStore"));
                listStore.add(store);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(StoreHomeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return listStore;
        }
    }
    
}
