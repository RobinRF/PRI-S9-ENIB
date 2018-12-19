package fr.enib.pri2018.dao;

/**
DAOConfigurationException
*/
public class DAOConfigurationException extends RuntimeException {

	/**
	 * Constructeur
	 */
	public DAOConfigurationException(String message) {
		super(message);
	}

	/**
	Constructeur
	*/
	public DAOConfigurationException(Throwable cause) {
		super(cause);
	}

	/**
	Constructeur
	*/
	public DAOConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
}