package fr.enib.pri2018.model;

import java.util.List;
import java.util.ArrayList;
import fr.enib.pri2018.model.Objet;
import fr.enib.pri2018.model.PersistentBusinessObject;

/**
Image
*/
public class Image extends PersistentBusinessObject {

	/** Chemin relatif de l'image */
	private String url;

	/** Hauteur de l'image en pixels */
	private int hauteur;

	/** Largeur de l'image en pixels */
	private int largeur;

	/** List des objets associés */
	private List<Objet> objets;

	/**
	Constructeur
	*/
	public Image() {
		this.objets = new ArrayList<Objet>();
	}

	/**
	@return Le chemin d'accès relatif
	*/
	public String getUrl() {
		return this.url;
	}

	/**
	@param url Le chemin d'accès relatif
	*/
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	@return La hauteur de l'image
	*/
	public int getHauteur() {
		return this.hauteur;
	}

	/**
	@param hauteur La hauteur de l'image
	*/
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/**
	@return La largeur de l'image
	*/
	public int getLargeur() {
		return this.largeur;
	}

	/**
	@param largeur La largeur de l'image
	*/
	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	/**
	@return La liste des objets associés
	*/
	public List<Objet> getObjets() {
		return this.objets;
	}

}