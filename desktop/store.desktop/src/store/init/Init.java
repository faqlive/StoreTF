
package store.init;

import java.util.ArrayList;
import java.util.List;
import store.model.*;

/**
 * Inicializar la aplicaciópn con los datos 
 * insertados automaticamente.
 * 
 * @author FAQ
 */
public class Init {
    
    public void initialization(){
        initStores();
        initProducts();
        initSells();    
    }
    
    private static List<Sell> initSells(){
        List<Sell> listSell = new ArrayList<>();
         for(int i=0;i<1000; i++){
            //  TODO crear 1000 ventas
        }
         return listSell;
    }
    
    private static List<Product> initProducts(){
        List<Product> listProduct = new ArrayList<>();
        for(int i=0;i<100; i++){
            //  TODO crear 100 productos
        }
        return listProduct;
    }   
    /**
     * Metodo destinado para
     * inicializar Almacenes
     * Un total de 10 según requerimientos
     * 
     */
    private static List<StoreHome> initStores(){
        List<StoreHome> listStore = new ArrayList<>();
        StoreHome store = new StoreHome();
        for(int i=0; i<10; i++){
            //  TODO agregar 10 almacenes y su ubicacion 
        }
        return listStore;
    }
}
