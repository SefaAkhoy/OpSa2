
package gui.guiSportstaetten;
   
import business.Freizeitbad;
import business.FreizeitbaederModel;
import gui.guiFreizeitbaeder.FreizeitbaederControl;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class SportstaettenView {
	
		private FreizeitbaederModel freizeitbaederModel;
		
    	//---Anfang Attribute der grafischen Oberflaeche---
    	private Pane pane						   = new Pane();
    	private Label lblAnzeigeFreizeitbaeder     = new Label("Anzeige Freizeitbäder");
    	private TextArea txtAnzeigeFreizeitbaeder  = new TextArea();
    	private Button btnAnzeigeFreizeitbaeder    = new Button("Anzeige");
    	//-------Ende Attribute der grafischen Oberflaeche-------
    
    	public SportstaettenView(FreizeitbaederModel freizeitbaederModel, Stage primaryStage){
    		Scene scene = new Scene(this.pane, 560, 340);
    		this.freizeitbaederModel = freizeitbaederModel;
    		primaryStage.setScene(scene);
    		primaryStage.setTitle("Anzeige von Sportstätten");
    		primaryStage.show();

    		this.initKomponenten();
    		this.initListener();
    	}
    	
    	private void initKomponenten(){
    		// Label
 		Font font = new Font("Arial", 20);
       	lblAnzeigeFreizeitbaeder.setLayoutX(310);
    		lblAnzeigeFreizeitbaeder.setLayoutY(40);
    		lblAnzeigeFreizeitbaeder.setFont(font);
    		lblAnzeigeFreizeitbaeder.setStyle("-fx-font-weight: bold;"); 
       	pane.getChildren().add(lblAnzeigeFreizeitbaeder);    
       	// Textbereich	
        	txtAnzeigeFreizeitbaeder.setEditable(false);
     		txtAnzeigeFreizeitbaeder.setLayoutX(310);
    		txtAnzeigeFreizeitbaeder.setLayoutY(90);
     		txtAnzeigeFreizeitbaeder.setPrefWidth(220);
    		txtAnzeigeFreizeitbaeder.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeigeFreizeitbaeder);        	
        	// Button
          	btnAnzeigeFreizeitbaeder.setLayoutX(310);
        	btnAnzeigeFreizeitbaeder.setLayoutY(290);
        	pane.getChildren().add(btnAnzeigeFreizeitbaeder); 
   }
   
   private void initListener() {
	    btnAnzeigeFreizeitbaeder.setOnAction(
 			new EventHandler<ActionEvent>() {
	    		@Override
	        	public void handle(ActionEvent e) {
	            	zeigeFreizeitbaederAn();
	        	} 
   	    });
    }
   
    void zeigeFreizeitbaederAn(){
    		if(freizeitbaederModel.getFreizeitbad().size() > 0){
    			StringBuffer text = new StringBuffer();
    			for(Freizeitbad fzb : this.freizeitbaederModel.getFreizeitbad()) {
    				text.append(fzb.gibFreizeitbadZurueck(' ') + "\n");
    			}
    			this.txtAnzeigeFreizeitbaeder.setText(text.toString());
    			/*txtAnzeigeFreizeitbaeder.setText(
    				freizeitbaederModel.getFreizeitbad()
 				.gibFreizeitbadZurueck(' ')); */
    		}
    		else{
    			zeigeInformationsfensterAn(
 				"Bisher wurde kein Freizeitbad aufgenommen!");
    		}
    }	
    
	void zeigeFehlermeldungAn(String fehlertyp, String meldung) {
		new MeldungsfensterAnzeiger(AlertType.ERROR, fehlertyp + "Fehler", meldung).zeigeMeldungsfensterAn();
	}
   
    void zeigeInformationsfensterAn(String meldung){
    	  	new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", "Anmeldung").zeigeMeldungsfensterAn();
    }	
    
}

