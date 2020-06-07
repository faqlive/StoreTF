package storemanagment.model;

import java.io.Serializable;

/**
 *
 * @author FAQ
 */
public class ProductInStore implements Serializable {
    private int idStrore;
    private int idProduct;
    private int stock;

    public ProductInStore() {
    }

    public ProductInStore(int idStrore, int idProduct, int stock) {
        this.idStrore = idStrore;
        this.idProduct = idProduct;
        this.stock = stock;
    }

    public int getIdStrore() {
        return idStrore;
    }

    public void setIdStrore(int idStrore) {
        this.idStrore = idStrore;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductInStore{" + "idStrore=" + idStrore + ", idProduct=" + idProduct + ", stock=" + stock + '}';
    }

    @Override
    public int hashCode() {
        String idP = String.valueOf(this.idProduct);
        String idS = String.valueOf(this.idStrore);
        int hash = Integer.valueOf(idP.concat(idS));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (ProductInStore.class != obj.getClass()) {
            return false;
        }
        final ProductInStore other = (ProductInStore) obj;
        if (this.idStrore != other.idStrore) {
            return false;
        }
        if (this.idProduct != other.idProduct) {
            return false;
        }
        return true;
    }    
}
