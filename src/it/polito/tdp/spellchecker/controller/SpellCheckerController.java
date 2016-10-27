package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dizionario;
import it.polito.tdp.spellchecker.model.EnglishDictionary;
import it.polito.tdp.spellchecker.model.ItalianDictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SpellCheckerController {
	
	private Dizionario dizionario;
	
	
	public void setModel(){   // qui inizial
		comboLingua.getItems().addAll("English", "Italian");
        labelTime.setText("");
    	labelErrori.setText("");
    	txtArea.clear();
    	txtTesto.clear();
		
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboLingua;

    @FXML
    private TextField txtTesto;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnClear;

    @FXML
    private Label labelErrori;

    @FXML
    private Label labelTime;

    @FXML
    void doClear(ActionEvent event) {
    	labelTime.setText("");
    	labelErrori.setText("");
    	txtArea.clear();
    	txtTesto.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	if(comboLingua.getValue()==null){
    		txtArea.appendText("Seleziona una lingua! \n");
    		txtTesto.clear();
    		return;
    	}
    	if(comboLingua.getValue() == "English" ){
    		Dizionario englishDictionary = new EnglishDictionary();
			dizionario = englishDictionary;
    	
    		
    	}
    	
    	if(comboLingua.getValue() == "Italian" ){
    		Dizionario italianDictionary = new ItalianDictionary();
			dizionario= italianDictionary;
    		
    		
    	}
    
    	String s = txtTesto.getText();
    	txtTesto.clear();
    	txtArea.clear();
    	
    	if(s.length()==0){
    		txtArea.appendText("Inserisci il testo! \n");
    		return;
    	}
    	s.toLowerCase();
    	
    	List <String> listaDaCorreggere = new LinkedList<String>();
    	
    	StringTokenizer st = new StringTokenizer(s, " ");                                   // Divido il testo usando gli spazi
		while (st.hasMoreTokens()) {                                              //fino a quando c'è una parola separata da uno spazio
			listaDaCorreggere.add(st.nextToken().trim());
		}
		
		long l1 = System.nanoTime();        //memorizzo il tempo attuale in una variabile
		List <RichWord> listaCorr = dizionario.spellCheckText(listaDaCorreggere);   //ritorna lista di rich so le assegno un nome x rikiamarla
		long l2 =System.nanoTime();          //dopo aver kiamato il metodo memorizzo il tempo attuale in un 'altra variabile
		
		int errori=0;
		for(RichWord r : listaCorr){
			if(!r.isCorretta()){        //se la parola è sbagliata  
				errori++;
			}
			txtArea.appendText(r.getParola()+ " "  +   r.isCorretta()   + "\n");   //append scrive una stringa nella txtArea
			                                                                     //string format serve anke x concatenare varie stringhe in generale
			                                                                    //dopo string format uso append x scrivere dentro txtarea
			
		}
		long l3 = l2-l1;
		txtArea.appendText(" Il testo contiene  " + errori + " errori \n "); 
		txtArea.appendText("Il tempo impiegato per la correzione è "  + l3 );
		
		
		

    }

    @FXML
    void initialize() {
        assert comboLingua != null : "fx:id=\"comboLingua\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelErrori != null : "fx:id=\"labelErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert labelTime != null : "fx:id=\"labelTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";

        comboLingua.getItems().addAll("English", "Italian");
        
    }
}

