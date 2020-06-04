
package storemanagment.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import storemanagment.interfaces.IServiceLocation;
import storemanagment.interfaces.IServicePis;
import storemanagment.interfaces.IServiceProduct;
import storemanagment.interfaces.IServiceSell;
import storemanagment.interfaces.IServiceStoreHome;
import storemanagment.model.Location;
import storemanagment.model.Product;
import storemanagment.model.ProductInStore;
import storemanagment.model.Sell;
import storemanagment.model.StoreHome;
import storemanagment.service.LocationService;
import storemanagment.service.PisService;
import storemanagment.service.ProductService;
import storemanagment.service.SellService;
import storemanagment.service.StoreHomeService;


/**
 * Inicializar la aplicaciópn con los datos 
 * generados automaticamente.
 * 
 * @author FAQ
 */
public class Init {
    
    public void initialization(){
        IServiceStoreHome storeService = new StoreHomeService();
        initStores().stream().forEach(store -> storeService.save(store));
        //  inicializar porducto.
        IServiceProduct productService = new ProductService();
        initProducts().stream().forEach(product -> productService.save(product));
        // inicializar Ventas
        IServiceSell serviceSell = new SellService();
        initSells().stream().forEach(sell -> serviceSell.save(sell));
        // Inicialzar Stock almacentes.
        IServicePis servicePis = new PisService();
        fillInStore().stream().forEach(pis -> servicePis.save(pis));
    }
    
    private static List<Sell> initSells(){
        List<Sell> listSell = new ArrayList<>();
        List <StoreHome> listStore = null;
        StoreHome store = null;
        List <Product> listProduct = new ArrayList<>();;
        Product product = null;
        Sell sell = new Sell();
        int totalStore = listStore.size();
        int totalProduct = listProduct.size();
        //------------------------
        Random random = new Random();
        int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = 0;
        
         for(int i=0;i<1000; i++){
             // ---------------------
             int indxStore = (int) Math.random()*totalStore+1;
             store = listStore.get(indxStore);
             int idStore = store.getIdStore();
             // ------------------------- RAMOM PRODICT
             int indxProduct = (int) Math.random()*totalProduct+1;
             product = listProduct.get(indxProduct);
             int idProduct =  product.getIdProduct();
             double priceProduct = product.getPricePrduct();
             // ----------------------- RAMDOM DATE
             randomDay = minDay + random.nextInt(maxDay - minDay);
             sell.setDate(LocalDate.ofEpochDay(randomDay));
         }
         return listSell;
    }
    
    private static List<Product> initProducts(){
        List<Product> listProduct = new ArrayList<>();
        Product product = new Product();
        for(int i=0;i<100; i++){
            // last Product i= id+1
            String prodcutName = "PRODUCTO_" + String.valueOf(i+1);
            double priceProduct = Math.random()*100+1;
            product.setNameProduct(prodcutName);
            product.setPricePrduct(priceProduct);
            listProduct.add(product);
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
        List<Location> listLocation = new ArrayList<>();
        StoreHome store = new StoreHome();
        Location location = new Location();
        for(int i=0; i<10; i++){
             // last almacen i= id+1
            String storeName = "ALMACEN_" + String.valueOf(i+1);
            int idLocation = i+1;
            location.setIdLocation(idLocation);
            location.setNameLocation("Localidad_" + String.valueOf(i+1));
            listLocation.add(location);
            store.setNameStore(storeName);
            store.setIdLocation(idLocation);
            listStore.add(store);
        }
        IServiceLocation serviceLocation = new LocationService();
        listLocation.stream().forEach(loc -> serviceLocation.save(loc));
        return listStore;
    }
    
    private static List<ProductInStore> fillInStore (){
        // consutatar lista de productos
        IServiceProduct productService = new ProductService();
        List<Product> listProduct = productService.getAll();
        IServiceStoreHome storeService = new StoreHomeService();
        List<StoreHome> listStore = storeService.getAll();
        
        List<ProductInStore> listPis = new ArrayList<>();
        ProductInStore pis = new ProductInStore();
        for(Product product : listProduct){
            pis.setIdProduct(product.getIdProduct());
            for (int i=0;i<2;i++){
                Random rand = new Random();
                StoreHome ramdomSH = listStore.get(rand.nextInt(listStore.size()));
                pis.setIdStrore(ramdomSH.getIdStore());
                int stock = (int) Math.random()*10+1;
                listPis.add(pis);
            }
        }
        return listPis;
    }
}
