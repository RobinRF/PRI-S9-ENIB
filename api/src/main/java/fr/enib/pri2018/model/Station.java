package fr.enib.pri2018.model;

import java.util.List;
import java.util.ArrayList;
import fr.enib.pri2018.model.Accroche;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Station
*/
public class Station extends PersistentBusinessObject {

	/** Station suivante */
	private Station stationSuivante;

	/** Accroches */
	private List<Accroche> accroches;

	/**
	Constructeur
	*/
	public Station() {
		this.accroches = new ArrayList<Accroche>();
	}

	/**
	@return La station suivante
	*/
	public Station getStationSuivante() {
		return this.stationSuivante;
	}

	/**
	@param station La station suivante
	*/
	public void setStationSuivante(Station stationSuivante) {
		this.stationSuivante = stationSuivante;
	}

	/**
	@return La liste des accroches
	*/
	public List<Accroche> getAccroches() {
		return this.accroches;
	}


}