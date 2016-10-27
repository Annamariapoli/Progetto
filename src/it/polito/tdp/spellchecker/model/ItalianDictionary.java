package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ItalianDictionary extends Dizionario {   // 1 dao per ogni tab
	                                                 //iscrizione=lista e nn  dao (dao= stud e corso)

	@Override
	public void loadDictionary(){
		
		try
		{
			FileReader fr = new	FileReader("rsc/Italian.txt");
			BufferedReader  br= new BufferedReader(fr);
		
			String word;
	
			while((word= br.readLine()) != null){
		                                             // Aggiungere word alla struttura dati
	    	}
		br.close();
		} 
		catch(IOException e){
			System.out.println("Errore nella lettura del file");
		}
		
	}

}
