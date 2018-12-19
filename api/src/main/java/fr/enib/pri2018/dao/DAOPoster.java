package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Poster;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOAncre
*/
public class DAOPoster extends DAO<Poster> {

    /** Key for factory mapping */
	public static final String KEY = "DAOPoster";

    @Override
	public Poster find(long id) throws DAOException {
        String sqlQuery = "SELECT * from posters WHERE id_poster = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Poster poster =  this.map(resultSet);
			resultSet.close();
			return poster;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Poster> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM posters;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Poster> posters = new ArrayList<Poster>();
			while(resultSet.next()) {
				posters.add(this.map(resultSet));
			}
			resultSet.close();
			return posters;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Poster poster, DAOFactory factory) throws DAOException {
		String sqlQuery = "INSERT INTO posters (id_objet, id_image, id_texte, nom) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			// Check null reference on objet association
			if(poster.getObjet() != null) {
				DAOObjet daoObjet = factory.getDAOObjet();
				daoObjet.add(poster.getObjet(), factory);
				statement.setLong(1, poster.getObjet().getId());
			} else {
				statement.setNull(1, java.sql.Types.BIGINT);
			}
			// check null reference on image association
			if(poster.getImage() != null) {
				DAOImage daoImage = factory.getDAOImage();
				daoImage.add(poster.getImage(), factory);
				statement.setLong(2, poster.getImage().getId());
			} else {
				statement.setNull(2, java.sql.Types.BIGINT);
			}
			// check null reference on image association
			if(poster.getTexte() != null) {
				DAOTexte daoTexte = factory.getDAOTexte();
				daoTexte.add(poster.getTexte(), factory);
				statement.setLong(3, poster.getTexte().getId());
			} else {
				statement.setNull(3, java.sql.Types.BIGINT);
			}
			statement.setString(4, poster.getNom());

			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	poster.setId(resultSet.getLong(1));
            	}
				resultSet.close();
			} else {
				throw new DAOException("More rows than expected have been affected by the query : " + sqlQuery);
			}
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

    @Override
	protected Poster map(ResultSet resultSet) throws DAOException {
		Poster poster = new Poster();
		try {
			poster.setId(resultSet.getLong("id_poster"));
            poster.setNom(resultSet.getString("nom"));
			return poster;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

	/**
	Fetch texte from poster
	@param poster the poster to fetch from
	@param daoTexte the dao object to fetch from the database
	*/
	public void fetchTexte(Poster poster, DAOTexte daoTexte) {
		String sqlQuery = "SELECT id_texte FROM posters WHERE id_poster = " + Long.toString(poster.getId()) + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			poster.setTexte(daoTexte.find(resultSet.getLong("id_texte")));
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	/**
	Fetch image from poster
	@param poster the poster to fetch from
	@param daoImage the dao object to fetch from the database
	*/
	public void fetchImage(Poster poster, DAOImage daoImage) {
		String sqlQuery = "SELECT id_image FROM posters WHERE id_poster = " + Long.toString(poster.getId()) + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			poster.setImage(daoImage.find(resultSet.getLong("id_image")));
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	/**
	Fetch objet from poster
	@param poster the poster to fetch from
	@param daoObjet the dao object to fetch from the database
	*/
	public void fetchObjet(Poster poster, DAOObjet daoObjet) {
		String sqlQuery = "SELECT id_objet FROM posters WHERE id_poster = " + Long.toString(poster.getId()) + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			poster.setObjet(daoObjet.find(resultSet.getLong("id_objet")));
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

    /**
	Constructor
	*/
	public DAOPoster(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}