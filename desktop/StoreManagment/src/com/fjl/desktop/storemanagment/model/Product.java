package com.fjl.desktop.storemanagment.model;

/**
 *
 * @author FAQ
 */
public class Product {
    private int idProduct;
    private String nameProduct;
    private double pricePrduct;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, double pricePrduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.pricePrduct = pricePrduct;
    }

    public Product(String nameProduct, double pricePrduct) {
        this.nameProduct = nameProduct;
        this.pricePrduct = pricePrduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public double getPricePrduct() {
        return pricePrduct;
    }
    /**
     * Metodo que configura el precio unitario del producto
     * Se asegura que este no tenga más de dos decimales;
     */
    public void setPricePrduct(double pricePrduct) {
        this.pricePrduct = (double) Math.round(pricePrduct * 100) / 100;
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + idProduct + ", nameProduct="
                + nameProduct + ", pricePrduct=" + pricePrduct + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 0;
        char[] name = this.nameProduct.toCharArray();
        if (this.idProduct !=0) {
            int num=0;
                    for(char i :name){
                 num = num + i;
            }
            hash = num;
        }else{
            hash = this.idProduct;
        }
        return hash*prime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.idProduct != other.idProduct) {
            return false;
        }
        return true;
    }

}
