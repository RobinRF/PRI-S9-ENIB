package fr.enib.pri2018.model;

import fr.enib.pri2018.model.Objet;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Texte
*/
public class Texte extends PersistentBusinessObject {

	/** Nom */
	private String nom;

	/** Titre */
	private String titre;

	/** Détails */
	private String detail;

	/** Objet associé */
	private Objet objet;

	/**
	Constructeur 
	*/
	public Texte() {

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
	@return Le titre du texte
	*/
	public String getTitre() {
		return this.titre;
	}

	/**
	@param titre Le titre du texte
	*/
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	@return Le contenu du texte
	*/
	public String getDetail() {
		return this.detail;
	}

	/**
	@param detail Le contenu du texte
	*/
	public void setDetail(String detail) {
		this.detail = detail;
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

}