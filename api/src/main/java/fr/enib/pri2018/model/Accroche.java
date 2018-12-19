package fr.enib.pri2018.model;

import fr.enib.pri2018.model.Poster;
import fr.enib.pri2018.model.Ancre;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Accroche
*/
public class Accroche extends PersistentBusinessObject {

	/** Poster associé */
	private Poster poster;

	/** Ancre associée */
	private Ancre ancre;

	/**
	Constructeur
	*/
	public Accroche() {

	}

	/**
	@return Le poster associé
	*/
	public Poster getPoster() {
		return this.poster;
	}

	/**
	@param poster Le poster associé
	*/
	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	/**
	@return L'ancre associée
	*/
	public Ancre getAncre() {
		return this.ancre;
	}

	/**
	@param ancre L'ancre associée
	*/
	public void setAncre(Ancre ancre) {
		this.ancre = ancre;
	}

}