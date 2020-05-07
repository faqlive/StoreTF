/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store.model;

/**
 *
 * @author FAQ
 */
public class StoreHome {
    
    private int idStore;
    private String nameStore;
    private int idLocation;

    public StoreHome() {
    }

    public StoreHome(int idStore, String nameStore, int idLocation) {
        this.idStore = idStore;
        this.nameStore = nameStore;
        this.idLocation = idLocation;
    }

    public StoreHome(String nameStore, int idLocation) {
        this.nameStore = nameStore;
        this.idLocation = idLocation;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    @Override
    public String toString() {
        return "StoreHome{" + "idStore=" + idStore + ", nameStore=" + nameStore + ", idLocation=" + idLocation + '}';
    }
    
    
    
}
