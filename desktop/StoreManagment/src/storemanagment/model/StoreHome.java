/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storemanagment.model;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StoreHome other = (StoreHome) obj;
        if (this.idStore != other.idStore) {
            return false;
        }
        if (!Objects.equals(this.nameStore, other.nameStore)) {
            return false;
        }
        return true;
    }    
}
