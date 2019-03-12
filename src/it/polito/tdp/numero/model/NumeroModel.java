package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {
	
	private final int NMAX = 100;
	private final int TMAX = 8;

	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	public NumeroModel() {
		inGioco = false;
	}

	/**
	 * Avvia nuova partita
	 */
	public void newGame() {
		inGioco = true;
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
	}
	
	/**
	 * Metodo per effettuare un tentativo
	 * 
	 * @param t il parametro
	 * @return 1 se t troppo alto, -1 se e' troppo basso, 0 se l'utente ha indovinato
	 */

	
	public int tentativo(int t) {
		//controllare se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita e' terminata");
		}
		
		//controllo se l'input e' nel range corretto
		if(t<1 || t> NMAX) {
			throw new InvalidParameterException(String.format("Devi inserire un numero"+" tra %d e %d", 1, NMAX));
		}
		
		//gestisci tentativo
		this.tentativiFatti ++;
		if(this.tentativiFatti==TMAX) {
			// la partita e' finita perche' ho esaurito i tentativi
			this.inGioco = false;
		}
		
		if(t==this.segreto) {
			//ho indovinato
			this.inGioco = false;
		    return 0;
		}
		if(t>this.segreto) {
			return 1;
		}
		
		return -1;
	}
	
	//faccio il controllo con un metodo a parte per essere agevolato se faccio cambiamenti in futuro
	public boolean tentativoValido(int t) {
		if(t<1 || t> NMAX) {
			return false;
		} else {
		return true;
	}
	}

	public boolean isInGioco() {	//non serve il setter perche' e' il model che deve controllare se la partita e' in corso
		return inGioco;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
}
