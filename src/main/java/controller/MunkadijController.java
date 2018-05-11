package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static model.Listazo.numValidator;

/**
 * FXML Controller class
 *
 * @author roland
 */
public class MunkadijController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField Tetel_field;
    
    @FXML
    private TextField Ar_field;
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Label hiba_label;
    
    private List<TextField> tetelek = new ArrayList();
    
    private List<TextField> arak = new ArrayList();
    
    private String cim;
    
    public void writeMunkadij() {      

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(cim+".doc", true)))
        {  
           int osszes=0; 
            bw.newLine();
            bw.write("Munkadíj");
            bw.newLine();
            for(int i=0;i<tetelek.size();i++){
            bw.write(tetelek.get(i).getText()); 
            bw.write("\t");
            bw.write(arak.get(i).getText() + " Ft");
            osszes+=Integer.parseInt(arak.get(i).getText());
            bw.newLine();
            }
            bw.newLine();
            bw.write("Összesen: " + osszes + " Ft");
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
    
    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
      Stage stage = (Stage) Ar_field.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/anyagdijScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/scene2.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();
        
    }
     @FXML
    private void ujtetelAction(ActionEvent event) throws IOException {
    TextField tetel = new TextField();
    TextField ar = new TextField();
    
    tetel.setMaxWidth(tetelek.get(tetelek.size()-1).getWidth());
    tetel.setLayoutX(tetelek.get(tetelek.size()-1).getLayoutX());
    tetel.setLayoutY(tetelek.get(tetelek.size()-1).getLayoutY()+51);
    tetel.setVisible(true);
    
    ar.setMaxWidth(arak.get(arak.size()-1).getWidth());
    ar.setLayoutX(arak.get(arak.size()-1).getLayoutX());
    ar.setLayoutY(arak.get(arak.size()-1).getLayoutY()+50);
    ar.setVisible(true);
    ar.setOnAction((ActionEvent event1) -> {
        if(numValidator(ar.getText())){
            hiba_label.setText("");
        }else{
            hiba_label.setText("Hiba ! Számot írj be az árhoz !");
            ar.setText("");
        }
    });      
    
    tetelek.add(tetel);
    arak.add(ar);
    
    pane.getChildren().addAll(tetel,ar);
    }
    
     @FXML
    private void keszAction(ActionEvent event) throws IOException {
      writeMunkadij(); 
    }
    
    @FXML
    private void ujajanlatAction(ActionEvent event) throws IOException {
      
      Stage stage = (Stage) Ar_field.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kezdoScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/scene3.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();
    }
    @FXML
    private void arAction(ActionEvent event) throws IOException {
       if(numValidator(Ar_field.getText())){
       hiba_label.setText("");
       }else{
       hiba_label.setText("Hiba ! Számot írj be az árhoz !");
       Ar_field.setText("");
       } 
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    tetelek.add(Tetel_field);
    arak.add(Ar_field);
    }    

    void initData(String cimzett) {
         cim=cimzett;
    }
    
}
