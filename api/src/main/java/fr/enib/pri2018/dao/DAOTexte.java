package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Texte;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOTexte
*/
public class DAOTexte extends DAO<Texte> {

    /** Key for factory mapping */
	public static final String KEY = "DAOTexte";

    @Override
	public Texte find(long id) throws DAOException {
        String sqlQuery = "SELECT * from textes WHERE id_texte = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Texte texte =  this.map(resultSet);
			resultSet.close();
			return texte;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Texte> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM textes;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Texte> textes = new ArrayList<Texte>();
			while(resultSet.next()) {
				textes.add(this.map(resultSet));
			}
			resultSet.close();
			return textes;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Texte texte, DAOFactory factory) throws DAOException {
		String sqlQuery = "INSERT INTO textes (nom, titre, detail) VALUES (?, ?, ?);";
		try {

			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, texte.getNom());
			statement.setString(2, texte.getTitre());
			statement.setString(3, texte.getDetail());
			
			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	texte.setId(resultSet.getLong(1));
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
	protected Texte map(ResultSet resultSet) throws DAOException {
		Texte texte = new Texte();
		try {
			texte.setId(resultSet.getLong("id_texte"));
            texte.setNom(resultSet.getString("nom"));
            texte.setTitre(resultSet.getString("titre"));
            texte.setDetail(resultSet.getString("detail"));
			return texte;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

    /**
	Constructor
	*/
	public DAOTexte(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}