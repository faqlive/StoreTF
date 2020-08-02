/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.service;

import storemanagment.ddbb.Conexion;
import storemanagment.ddbb.IConexion;
import storemanagment.generic.GenericService;
import storemanagment.generic.IGenericDao;
import storemanagment.interfaces.IServiceLocation;
import storemanagment.model.Location;
import storemanagment.dao.LocationDao;


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
