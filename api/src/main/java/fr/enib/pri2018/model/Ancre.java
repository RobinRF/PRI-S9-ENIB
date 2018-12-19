package fr.enib.pri2018.model;

import fr.enib.pri2018.model.PersistentBusinessObject;

/**
L'ancre est le point d'accroche d'un objet 3D
elle deifinit une position dans un système de coordonnées cartésien (x, y, z)
ainsi qu'un angle pour indiquer une rotation autour d'un axe.
*/
public class Ancre extends PersistentBusinessObject {

	/** Nom de l'ancre */
	private String nom;

	/** Coordonnée X */
	private int x;

	/** Coordonnée Y */
	private int y;

	/** Coordonnée Z */
	private int z;

	/** Angle de rotation en degree*/
	private int angle;

	/**
	Constructeur
	*/
	public Ancre() {

	}

	/**
	@return Le nom de l'ancre
	*/
	public String getNom() {
		return this.nom;
	}

	/**
	@param nom Le nom de l'ancre
	*/
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	@return Coordonnée X
	*/
	public int getX() {
		return this.x;
	}

	/**
	@param x Coordonnée X
	*/
	public void setX(int x) {
		this.x = x;
	}

	/**
	@return Coordonnée Y
	*/
	public int getY() {
		return this.y;
	}

	/**
	@param y Coordonnée Y
	*/
	public void setY(int y) {
		this.y = y;
	}

	/**
	@return Coordonnée Z
	*/
	public int getZ() {
		return this.z;
	}

	/**
	@param z Coordonnée Z
	*/
	public void setZ(int z) {
		this.z = z;
	}

	/**
	@return Angle de rotation en degree
	*/
	public int getAngle() {
		return this.angle;
	}

	/**
	@param angle Angle de rotation en degree
	*/
	public void setAngle(int angle) {
		this.angle = angle;
	}

}