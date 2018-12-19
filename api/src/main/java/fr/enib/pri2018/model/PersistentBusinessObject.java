package fr.enib.pri2018.model;

/**
Définit des propriétés communes aux objets métiers persistants
*/
public abstract class PersistentBusinessObject {

	/** Identifiant de l'objet */
	private long id = 0;

	/**
	@return L'identifiant de l'objet
	*/
	public long getId() {
		return this.id;
	}

	/**
	@param id L'identifiant de l'objet
	*/
	public void setId(long id) {
		this.id = id;
	}

}