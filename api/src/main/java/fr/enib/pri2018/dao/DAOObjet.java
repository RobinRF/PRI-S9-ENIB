package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Objet;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOObjet
*/
public class DAOObjet extends DAO<Objet> {

    /** Key for factory mapping */
	public static final String KEY = "DAOObjet";

    @Override
	public Objet find(long id) throws DAOException {
        String sqlQuery = "SELECT * from objets WHERE id_objet = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Objet objet = this.map(resultSet);
			resultSet.close();
			return objet;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Objet> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM objets;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Objet> objets = new ArrayList<Objet>();
			while(resultSet.next()) {
				objets.add(this.map(resultSet));
			}
			resultSet.close();
			return objets;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Objet objet, DAOFactory factory) throws DAOException {
		String sqlQuery = "INSERT INTO objets (nom) VALUES (?);";
		try {

			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, objet.getNom());
			
			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	objet.setId(resultSet.getLong(1));
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
	protected Objet map(ResultSet resultSet) throws DAOException {
		Objet objet = new Objet();
		try {
			objet.setId(resultSet.getLong("id_objet"));
            objet.setNom(resultSet.getString("nom"));
			return objet;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

    /**
	Constructor
	*/
	public DAOObjet(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}