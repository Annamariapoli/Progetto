package it.polito.tdp.spellchecker.model;

import java.util.LinkedList;
import java.util.List;

public class Dizionario {
	
	private List<String> dizionario = new LinkedList<String>();
	
	public boolean isSbagliata(String parolaUtente){
		if(dizionario.contains(parolaUtente)){
			return false;
		}
		return true;
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		List<RichWord> listaCorretta = new LinkedList<RichWord>();
		for(String p : inputTextList){                                     //per tutte le parole dell'utente
			if(isSbagliata(p)==false){                                    //se la parola è corretta
				RichWord r = new RichWord(p, true);                      //allora isCorretta è vera
				listaCorretta.add(r);
			}
			else if(isSbagliata(p)==true){                            //se la parola è sbagliata
				RichWord r= new RichWord(p, false);                  //allora isCorretta è falsa
				listaCorretta.add(r);
			}
		}
		return listaCorretta;
		
	}
	
	public void loadDictionary(){
		
	}

}
