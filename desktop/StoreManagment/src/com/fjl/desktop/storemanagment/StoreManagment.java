package com.fjl.desktop.storemanagment;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Clase de entrada de la aplicación desarrollada
 * con la tecnología FX
 *
 * @author Facundo J. López
 * @version 1.0
 * @since 12/2020
 */
public class StoreManagment extends Application {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init(){
        
    }

    /**
     * Método para la inciación de la grafica del software.
     *
     * @param primaryStage
     *
     * */
    @Override
    public void start(Stage primaryStage) {
        
        try{ 
            FXMLLoader loader = new FXMLLoader();
            URL url = StoreManagment.class.getResource("view/InitDataView.fxml");
            loader.setLocation(url);
            //  Cargo el RootNode al
            Pane window = (Pane) loader.load();
            // Crear Scene con root node.
            Scene scene = new Scene(window);
            primaryStage.setTitle("Store Management");
            primaryStage.getIcons().add(new Image(StoreManagment.class.getResourceAsStream("view/img/ico.png")));
            //  Agregamos la Scene al Stage
            primaryStage.setScene(scene);
        }catch (IOException e){
            System.out.println(e.getStackTrace());
        }
            
        primaryStage.show();
    }
    
    @Override
    public void stop(){
        
    }
}
