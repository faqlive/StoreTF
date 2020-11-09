/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fjl.desktop.storemanagment.service;

import com.fjl.desktop.storemanagment.ddbb.Conexion;
import com.fjl.desktop.storemanagment.ddbb.IConexion;
import com.fjl.desktop.storemanagment.generic.GenericService;
import com.fjl.desktop.storemanagment.generic.IGenericDao;
import com.fjl.desktop.storemanagment.interfaces.IServiceLocation;
import com.fjl.desktop.storemanagment.model.Location;
import com.fjl.desktop.storemanagment.dao.LocationDao;


/**
 *
 * @author FAQ
 */
public class LocationService extends GenericService<Location, Integer, String> implements IServiceLocation
{

    @Override
    public IGenericDao<Location, Integer, String> getDao() {
        IConexion conn = Conexion.getInstace();
        return new LocationDao(conn);
      
    }
    
}
