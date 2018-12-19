package fr.enib.pri2018.model;

import java.util.List;
import java.util.ArrayList;
import fr.enib.pri2018.model.Texte;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Objet
*/
public class Objet extends PersistentBusinessObject {

	/** Nom */
	private String nom;

	/** List des textes associés */
	private List<Texte> textes;

	/** List des images associés */
	private List<Image> images;

	/**
	Constructeur
	*/
	public Objet() {
		this.textes = new ArrayList<Texte>();
		this.images = new ArrayList<Image>();
	}

	/**
	@return Le nom du texte
	*/
	public String getNom() {
		return this.nom;
	}

	/**
	@param nom Le nom du texte
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	@return La liste des textes associés
	*/
	public List<Texte> getTextes() {
		return this.textes;
	}

	/**
	@return La liste des iamges associés
	*/
	public List<Image> getImages() {
		return this.images;
	}

}