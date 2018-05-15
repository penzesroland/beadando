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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Listazo;
import static model.Listazo.termekArak;
import static model.Listazo.termekNevek;
import static model.Listazo.termekOsszar;
import static model.Listazo.numValidator;
import model.TermekekDAO;
import model.TermekekDAOFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * FXML Controller class
 *
 * @author roland
 */
public class AnyagdijController implements Initializable {
    
   private static final Logger logger=LoggerFactory.getLogger(AnyagdijController.class);
   
   @FXML
   private Label Ar;
   
    @FXML
   private Label hiba_label;
   
   @FXML
   private ComboBox combobox;
   
   @FXML
   private TextField menny;
    
   @FXML
   private TextField ujtermeknev;
   
   @FXML
   private TextField ujtermekar;
   
   @FXML
   private AnchorPane pane;
   
   private int tetelar;
   
   private Listazo lista = new Listazo();
   
   private List<Label> labelek = new ArrayList();
   
   private List<ComboBox> comboboxok = new ArrayList();
   
   private List<TextField> textfieldek = new ArrayList();
   
   private TermekekDAO td;
   
   private String cimzett;
   
   public void writeAnyagdij() {      
       int osszes=0;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(cimzett + ".doc", true)))
        {   
            bw.newLine();
            bw.write("Anyagdíj");
            bw.newLine();
            for(int i=0;i<labelek.size();i++){
            bw.write(comboboxok.get(i).getValue().toString());
            bw.write("\t");
            bw.write(textfieldek.get(i).getText() + " db");
            bw.write("\t");
            bw.write(labelek.get(i).getText() + " Ft");
            osszes+=Integer.parseInt(labelek.get(i).getText());
            bw.newLine();
            }
            bw.write("Összesen: " + osszes + " Ft");
        } catch (IOException e) {
            logger.error("Nem sikerült a fájlba írás!");
            e.printStackTrace();
        }
   }
   
   @FXML
    private void visszaAction(ActionEvent event) throws IOException {
      Stage stage = (Stage) Ar.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/kezdoScene.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();   
    }
    
    @FXML
    private void ujtermekAction(ActionEvent event) throws IOException {
    if(numValidator(ujtermekar.getText())){    
        td.createTermek(ujtermeknev.getText(), Integer.parseInt(ujtermekar.getText()));
        lista.getTermeklista().add(ujtermeknev.getText());
        lista.getArlista().add(Integer.parseInt(ujtermekar.getText()));
    for(int i=0;i<comboboxok.size();i++)
        comboboxok.get(i).setItems(FXCollections.observableArrayList(lista.getTermeklista()));
        hiba_label.setText("");
        }else{ 
        hiba_label.setText("Hiba! Írjon be számot az árhoz!");
        ujtermekar.setText("");
        }
    }
    
    @FXML
    private void setujtermeknevAction(ActionEvent event) throws IOException {
        if(combobox.getItems().contains(ujtermeknev.getText())){
        logger.error("Már létező terméknevet írtunk be");
        hiba_label.setText("Hiba! Ez a terméknév már szerepel az adatbázisban!");
        ujtermeknev.setText("");
        }else{
        hiba_label.setText("");
        }
    }
    @FXML
    private void keszAction(ActionEvent event) throws IOException {
   try{
      	writeAnyagdij();
	hiba_label.setText("");
	}catch(NullPointerException e){
	hiba_label.setText("Hiba! Válassz terméket!");
	}     
    }
    
    @FXML
    private void tovabbAction(ActionEvent event) throws IOException {
      
      Stage stage = (Stage) Ar.getScene().getWindow();

            FXMLLoader fl = new FXMLLoader(getClass().getResource("/fxml/munkadijScene.fxml"));
             Parent root = fl.load();
             fl.<MunkadijController>getController().initData(cimzett);

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/scene3.css");

            stage.setTitle("Árajánlat-készítő");
            stage.setScene(scene);
            stage.show();
    }
    
    @FXML
    private void ujtetelAction(ActionEvent event) throws IOException {
     
       Label label = new Label();
       ComboBox combo = new ComboBox();
       TextField textfield = new TextField();
       
       label.setMinWidth(labelek.get(labelek.size()-1).getWidth());
       label.setText("0");
       label.setLayoutX(labelek.get(labelek.size()-1).getLayoutX());
       label.setLayoutY(labelek.get(labelek.size()-1).getLayoutY() + 50);
       label.setVisible(true);
       
       combo.setMaxWidth(comboboxok.get(comboboxok.size()-1).getWidth());
       combo.setLayoutX(comboboxok.get(comboboxok.size()-1).getLayoutX());
       combo.setLayoutY(comboboxok.get(comboboxok.size()-1).getLayoutY() + 50);
       combo.setOnAction(comboboxok.get(comboboxok.size()-1).getOnAction());
       combo.setItems(comboboxok.get(comboboxok.size()-1).getItems());
       combo.setEditable(false);
       combo.setVisible(true);
       
       textfield.setMaxWidth(textfieldek.get(textfieldek.size()-1).getWidth());
       textfield.setLayoutX(textfieldek.get(textfieldek.size()-1).getLayoutX());
       textfield.setLayoutY(textfieldek.get(textfieldek.size()-1).getLayoutY()+ 50);
       textfield.setOnAction((ActionEvent event1) -> {
           if(numValidator(textfield.getText())){
               int i = lista.getTermeklista().indexOf(combo.getValue());
               String s = termekOsszar(i,lista.getArlista(),textfield.getText());
               label.setText(s);
               hiba_label.setText("");
           }else{
               textfield.setText("");
               hiba_label.setText("Hiba ! Számot írj be a mennyiséghez !");
           }
       });
       textfield.setVisible(true);
       
       labelek.add(label);
       comboboxok.add(combo);
       textfieldek.add(textfield);
       pane.getChildren().addAll(combo,textfield,label);
       
    }
    
    @FXML
    private void writeTextField(ActionEvent event) throws IOException {
    try{    
    if(numValidator(menny.getText())){    
    int i = lista.getTermeklista().indexOf(combobox.getValue());
    String s = termekOsszar(i,lista.getArlista(),menny.getText());
    Ar.setText(s);
    hiba_label.setText("");
    }else{
    menny.setText("");
    hiba_label.setText("Hiba ! Számot írj be a mennyiséghez !"); 
    }
    }catch(ArrayIndexOutOfBoundsException e)
    {
    logger.error("Nem választottunk terméket!");
    hiba_label.setText("Hiba! Válassz egy terméket!");
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       td = TermekekDAOFactory.getInstance().createTermekekDAO();
       lista.setTermeklista(termekNevek(td.all()));
       lista.setArlista(termekArak(td.all()));
       combobox.setItems(FXCollections.observableArrayList(lista.getTermeklista()));
        
       comboboxok.add(combobox);
       labelek.add(Ar);
       textfieldek.add(menny);
       
       
    }    

    void initData(String text) {
        cimzett=text;
    }
}
