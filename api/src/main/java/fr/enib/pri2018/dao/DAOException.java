package fr.enib.pri2018.dao;

/**
DAOException
*/
public class DAOException extends RuntimeException {

	/**
	 * Constructeur
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	Constructeur
	*/
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	Constructeur
	*/
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}