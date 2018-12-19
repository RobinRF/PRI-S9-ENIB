package fr.enib.pri2018.model;

import fr.enib.pri2018.model.Texte;
import fr.enib.pri2018.model.Objet;
import fr.enib.pri2018.model.Image;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Poster
*/
public class Poster extends PersistentBusinessObject {

	/** Nom */
	private String nom;

	/** Texte */
	private Texte texte;

	/** Objet */
	private Objet objet;

	/** Image */
	private Image image;

	/**
	Constructeur
	*/
	public Poster() {

	}

	/**
	@return Le nom associé
	*/
	public String getNom() {
		return this.nom;
	}

	/**
	@param nom Le nom associé
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	@return Le texte associé
	*/
	public Texte getTexte() {
		return this.texte;
	}

	/**
	@param texte Le texte associé
	*/
	public void setTexte(Texte texte) {
		this.texte = texte;
	}

	/**
	@return L'objet associé
	*/
	public Objet getObjet() {
		return this.objet;
	}

	/**
	@param objet L'objet associé
	*/
	public void setObjet(Objet objet) {
		this.objet = objet;
	}

	/**
	@return L'image associée
	*/
	public Image getImage() {
		return this.image;
	}

	/**
	@param image L'image associée
	*/
	public void setImage(Image image) {
		this.image = image;
	}

}