package fr.enib.pri2018.gui;

/**
*	Abstract event manager
*/
public abstract class EventManager {

	public abstract void addAccroche();

	public abstract void openTexte();

	public abstract void openImage();

	public abstract void texteNomUpdate();

	public abstract void texteTitreUpdate();

	public abstract void texteDetailUpdate();

	public abstract void nomAncreUpdate();

	public abstract void ancreXUpdate();

	public abstract void ancreYUpdate();

	public abstract void ancreZUpdate();

	public abstract void ancreAngleUpdate();

	public abstract void nomPosterUpdate();

}