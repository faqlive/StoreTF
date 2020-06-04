package storemanagment.model;

import java.time.LocalDate;

/**
 *
 * @author FAQ
 */
public class Sell {
    private int idSell;
    private int idProduct;
    private int idStore;
    // agregar cantidad de productos
    // precio final
    private LocalDate date;

    public Sell() {
    }

    public Sell(int idSell, int idProduct, int idStore, LocalDate date) {
        this.idSell = idSell;
        this.idProduct = idProduct;
        this.idStore = idStore;
        this.date = date;
    }

    public Sell(int idProduct, int idStore, LocalDate date) {
        this.idProduct = idProduct;
        this.idStore = idStore;
        this.date = date;
    }
    
    public int getIdSell() {
        return idSell;
    }

    public void setIdSell(int idSell) {
        this.idSell = idSell;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sell{" + "idSell=" + idSell + ", idProduct=" + idProduct + ", idStore=" + idStore + ", date=" + date + '}';
    }
    
 }
/***
 *  ID_venta
o ID_producto
o ID_almacen
o Fecha_de_venta (año de venta)
 * 
 * 
 */