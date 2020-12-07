
package com.fjl.desktop.storemanagment.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.fjl.desktop.storemanagment.ddbb.CleanDDBB;
import com.fjl.desktop.storemanagment.hand.ExceptionNoDB;
import com.fjl.desktop.storemanagment.service.LocationService;
import com.fjl.desktop.storemanagment.service.PisService;
import com.fjl.desktop.storemanagment.service.ProductService;
import com.fjl.desktop.storemanagment.interfaces.IServiceLocation;
import com.fjl.desktop.storemanagment.interfaces.IServicePis;
import com.fjl.desktop.storemanagment.interfaces.IServiceProduct;
import com.fjl.desktop.storemanagment.interfaces.IServiceSell;
import com.fjl.desktop.storemanagment.interfaces.IServiceStoreHome;
import com.fjl.desktop.storemanagment.model.Location;
import com.fjl.desktop.storemanagment.model.Product;
import com.fjl.desktop.storemanagment.model.ProductInStore;
import com.fjl.desktop.storemanagment.model.Sell;
import com.fjl.desktop.storemanagment.model.StoreHome;
import com.fjl.desktop.storemanagment.service.SellService;
import com.fjl.desktop.storemanagment.service.StoreHomeService;
import javafx.concurrent.Task;


/**
 * Inicializar la aplicaciópn con los datos 
 * generados automaticamente.
 * 
 * @author FAQ
 */
public class Init {

     
    public static Task initialization() throws ExceptionNoDB {

        Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                // limpiando Base de datos.
                    updateMessage("Limpiando BBDD");
                    new CleanDDBB().cleanAll();
                    updateProgress(1, 6);

                    // inicializando Almacenes
                    updateMessage("Creando tiendas");
                    IServiceStoreHome storeService = new StoreHomeService();
                    initStores().stream().forEach(store -> storeService.save(store));
                    updateProgress(2, 6);

                    //  inicializar porducto.
                    updateMessage("Creando Productos");
                    IServiceProduct productService = new ProductService();
                    initProducts().stream().forEach(product -> productService.save(product));
                    updateProgress(3, 6);

                    // inicializar Ventas
                    updateMessage("Recreando ventas");
                    IServiceSell serviceSell = new SellService();
                    initSells().stream().forEach(sell -> serviceSell.save(sell));
                    updateProgress(4, 6);

                    // Inicialzar Stock almacentes.
                    updateMessage("Llenando tiendas");
                    IServicePis servicePis = new PisService();
                    fillInStore().stream().forEach(pis -> servicePis.save(pis));
                    updateProgress(5, 6);
                    Thread.sleep(500);
                    //updateMessage("CARGA EXITO");
                    updateProgress(6, 6);
                return true;
            }
        };
        return task;
    }
      
    private static List<Sell> initSells(){
        List<Sell> listSell = new ArrayList<>();
        IServiceStoreHome storeService = new StoreHomeService();
        List <StoreHome> listStore = storeService.getAll();
        StoreHome store = null;
        IServiceProduct productService = new ProductService();
        List <Product> listProduct = productService.getAll();
        Product product = null;
        Sell sell = null; 
        //------------------------
        Random random = new Random();
        int minDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = 0;
        
        for(int i=0;i<1000; i++){
            sell = new Sell();
            // -------------- Store al azar
            store = listStore.get(random.nextInt(listStore.size()));
            sell.setIdStore(store.getIdStore()); 
            // -------------- RAMOM PRODuCT
            product = listProduct.get(random.nextInt(listProduct.size()));
            sell.setIdProduct(product.getIdProduct());
            /*
            double priceProduct = product.getPricePrduct();
            */
            // ----------------------- RAMDOM DATE
            randomDay = random.nextInt(maxDay - minDay) + minDay ;
            sell.setDate(LocalDate.ofEpochDay(randomDay));
            listSell.add(sell);
        }
        return listSell;
   }
    
    private static List<Product> initProducts(){
        int max = 100;
        int min = 1;
        List<Product> listProduct = new ArrayList<>();
        Product product = null;
        for(int i=0;i<100; i++){
            product = new Product();
            String prodcutName = "PRODUCTO_" + String.valueOf(i+1);
            double priceProduct = Math.random()*(max - min) + min;
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
        StoreHome store = null;
        Location location = null;
        for(int i=0; i<10; i++){
            store = new StoreHome();
            location = new Location();
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
    
    private static Set<ProductInStore> fillInStore () {
        // consutatar lista de productos
        //  List<ProductInStore> listPis = new ArrayList<>();
        Set<ProductInStore> hashSetPis = new HashSet<ProductInStore>();
        
        IServiceProduct productService = new ProductService();
        List<Product> listProduct = productService.getAll();
        IServiceStoreHome storeService = new StoreHomeService();
        List<StoreHome> listStore = storeService.getAll();
        ProductInStore pis = null;
        Random rand = new Random();
        for(Product product : listProduct){
            for (int i=0;i<2;i++){
                pis = new ProductInStore();
                pis.setIdProduct(product.getIdProduct());
                //
                StoreHome ramdomSH = listStore.get(rand.nextInt(listStore.size()));
                pis.setIdStrore(ramdomSH.getIdStore());
                // EVITA PONER EL MISMO PRODUCTO EN LA MISMA TIENDA
                while(hashSetPis.contains(pis)){
                    ramdomSH = listStore.get(rand.nextInt(listStore.size()));
                    pis.setIdStrore(ramdomSH.getIdStore());
                }
                // PASADO EL FILTRO SE AGREGA
                pis.setIdStrore(ramdomSH.getIdStore());
                //  STOCK
                int stock = (int) (Math.random() * 10 + 1);
                pis.setStock(stock);
                hashSetPis.add(pis);
                
            }
        }
        return hashSetPis;
    }
}
