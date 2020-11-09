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
import com.fjl.desktop.storemanagment.interfaces.ILocationDao;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.model.Location;

/**
 *
 * @author FAQ
 */
public class LocationDao extends GenericDao implements ILocationDao {
    
    private ResultSet resultado;
    private PreparedStatement statement;

    public LocationDao(IConexion conexion) {
        super(conexion);
    }

    @Override
    public void save(Location entity) {
        try{
            statement = conexion.prepareStatement("INSERT INTO location (nameLocation) VALUES(?)");
            statement.setString(1,entity.getNameLocation());
            statement.execute();
        }catch(SQLException e){
            e.getMessage();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        try{
            statement = conexion.prepareStatement("DELETE FROM location WHERE idLocation = ?");
            statement.setInt(1, id);            
        }catch (SQLException e){
            e.getSQLState();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
       }
    }

    @Override
    public Location get(Integer id) {
        Location location = null;
        try{
            statement = conexion.prepareStatement("SELECT * FROM location WHERE idLocation = ?");
            statement.setInt(1, id);
            resultado = statement.executeQuery();
            while(resultado.next()){
                location = new Location();
                location.setIdLocation(id);
                location.setNameLocation(resultado.getString("nameLocation"));
            }
        }catch(SQLException e){
            e.getSQLState();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return location;
    }

    @Override
    public List<Location> getAll() {
        List<Location> listLocation = new ArrayList<>();
        
        try{
            statement = conexion.prepareStatement("SELECT * FROM location");
            resultado = statement.executeQuery();
            Location location = null;
            while(resultado.next()){
                location = new Location();                
                location.setIdLocation(resultado.getInt("idLocation"));
                location.setNameLocation(resultado.getString("nameLocation"));
                listLocation.add(location);
            }
        }catch(SQLException e){
            e.getSQLState();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLocation;
    }

    @Override
    public List<Location> getAllForangeKey(String fkey) {
        List<Location> listLocation = new ArrayList<>();
        try{
            statement = conexion.prepareStatement("SELECT * FROM location WHERE nameLocation = ?");
            statement.setString(1, "%"+fkey+"%");
            resultado = statement.executeQuery();
            Location location = null;
            while(resultado.next()){
                location = new Location();                
                location.setIdLocation(resultado.getInt("idLocation"));
                location.setNameLocation(resultado.getString("nameLocation"));
                listLocation.add(location);
            }
        }catch(SQLException e){
            e.getSQLState();
        }finally{
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocationDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listLocation;
    }
    
}
