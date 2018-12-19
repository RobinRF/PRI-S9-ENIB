package fr.enib.pri2018.dao;

import java.util.HashMap;

/**
DAOFactory
*/
public abstract class DAOFactory {

	/** Map dao instance with labels */
	private HashMap<String, DAO> map;

	/**
	Constructor
	*/
	public DAOFactory() {
		this.map = new HashMap<String, DAO>();
	}

	/**
	Create an instance of DAOParcours on first call and return the same
	instance for each call to this method
	@return A DAOParcours instance
	*/
	public DAOParcours getDAOParcours() {
		if (!this.map.containsKey(DAOParcours.KEY)) {
			this.map.put(DAOParcours.KEY, new DAOParcours(this));
		}
		return (DAOParcours)this.map.get(DAOParcours.KEY);
	}

	/**
	Create an instance of DAOStation on first call and return the same 
	instance for each call to this method
	@return A DAOStation instance
	*/
	public DAOStation getDAOStation() {
		if (!this.map.containsKey(DAOStation.KEY)) {
			this.map.put(DAOStation.KEY, new DAOStation(this));
		}
		return (DAOStation)this.map.get(DAOStation.KEY);
	}

	/**
	Create an instance of DAOAccroche on first call and return the same 
	instance for each call to this method
	@return A DAOAccroche instance
	*/
	public DAOAccroche getDAOAccroche() {
		if (!this.map.containsKey(DAOAccroche.KEY)) {
			this.map.put(DAOAccroche.KEY, new DAOAccroche(this));
		}
		return (DAOAccroche)this.map.get(DAOAccroche.KEY);
	}

	/**
	Create an instance of DAOAncre on first call and return the same 
	instance for each call to this method
	@return A DAOAncre instance
	*/
	public DAOAncre getDAOAncre() {
		if (!this.map.containsKey(DAOAncre.KEY)) {
			this.map.put(DAOAncre.KEY, new DAOAncre(this));
		}
		return (DAOAncre)this.map.get(DAOAncre.KEY);
	}

	/**
	Create an instance of DAOPoster on first call and return the same 
	instance for each call to this method
	@return A DAOPoster instance
	*/
	public DAOPoster getDAOPoster() {
		if (!this.map.containsKey(DAOPoster.KEY)) {
			this.map.put(DAOPoster.KEY, new DAOPoster(this));
		}
		return (DAOPoster)this.map.get(DAOPoster.KEY);
	}

	/**
	Create an instance of DAOTexte on first call and return the same 
	instance for each call to this method
	@return A DAOTexte instance
	*/
	public DAOTexte getDAOTexte() {
		if (!this.map.containsKey(DAOTexte.KEY)) {
			this.map.put(DAOTexte.KEY, new DAOTexte(this));
		}
		return (DAOTexte)this.map.get(DAOTexte.KEY);
	}

	/**
	Create an instance of DAOImage on first call and return the same 
	instance for each call to this method
	@return A DAOImage instance
	*/
	public DAOImage getDAOImage() {
		if (!this.map.containsKey(DAOImage.KEY)) {
			this.map.put(DAOImage.KEY, new DAOImage(this));
		}
		return (DAOImage)this.map.get(DAOImage.KEY);
	}

	/**
	Create an instance of DAOObjet on first call and return the same 
	instance for each call to this method
	@return A DAOObjet instance
	*/
	public DAOObjet getDAOObjet() {
		if (!this.map.containsKey(DAOObjet.KEY)) {
			this.map.put(DAOObjet.KEY, new DAOObjet(this));
		}
		return (DAOObjet)this.map.get(DAOObjet.KEY);
	}

}