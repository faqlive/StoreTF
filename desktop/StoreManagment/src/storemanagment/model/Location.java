package storemanagment.model;

/**
 *
 * @author FAQ
 */
public class Location {
    private int idLocation;
    private String nameLocation;

    public Location() {
    }

    public Location(int idLocation, String nameLocation) {
        this.idLocation = idLocation;
        this.nameLocation = nameLocation;
    }

    public Location(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    @Override
    public String toString() {
        return "Location{" + "idLocation=" + idLocation + ", nameLocation=" + nameLocation + '}';
    }
    
}
