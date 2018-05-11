package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KezdoController implements Initializable {
    
    @FXML
    private TextField kesz;
    
    @FXML
    private TextField cim;
    
    @FXML
    private void tovabbAction(ActionEvent event) throws IOException {
       
       try(BufferedWriter bw = new BufferedWriter(new FileWriter(cim.getText() + ".doc", true)))
        {   
            bw.write("Készítő: " + kesz.getText());
            bw.newLine();
            bw.write("Címzett: "+ cim.getText());
            bw.newLine();
             
        } catch (IOException e) {
            e.printStackTrace();
        }
       
       Stage stage = (Stage) kesz.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/anyagdijScene.fxml"));
            Parent root = fl.load();
            fl.<AnyagdijController>getController().initData(cim.getText());

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/scene2.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

   
}
