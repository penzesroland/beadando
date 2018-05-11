package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TermekekDAOFactory;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kezdoScene.fxml"));
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();
            
            stage.setOnCloseRequest(e->{
                try {
                    closeApp();
                } catch (Exception ex) {
                    Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
      private void closeApp() throws Exception {
            TermekekDAOFactory.getInstance().close();
        }  
   
   
}
