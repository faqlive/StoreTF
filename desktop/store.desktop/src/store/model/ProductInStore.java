package store.model;

/**
 *
 * @author FAQ
 */
public class ProductInStore {
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
        int hash = 7;
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
