package fr.enib.pri2018.model;

import fr.enib.pri2018.model.Station;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Parcours
*/
public class Parcours extends PersistentBusinessObject {

	/** Nom du parcours */
	private String nom;

	/** Première station */
	private Station station;

	/**
	Constructeur
	*/
	public Parcours() {

	}

	/**
	@return Le nom du parcours
	*/
	public String getNom() {
		return this.nom;
	}

	/**
	@param nom Le nom du parcours
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	@return La station suivante
	*/
	public Station getStation() {
		return this.station;
	}

	/**
	@param station La station suivante
	*/
	public void setStation(Station station) {
		this.station = station;
	}

}