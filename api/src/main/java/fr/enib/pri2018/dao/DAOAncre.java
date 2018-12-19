package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Ancre;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOAncre
*/
public class DAOAncre extends DAO<Ancre> {

    /** Key for factory mapping */
	public static final String KEY = "DAOAncre";

    @Override
	public Ancre find(long id) throws DAOException {
        String sqlQuery = "SELECT * from ancres WHERE id_ancre = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Ancre ancre = this.map(resultSet);
			resultSet.close();
			return ancre;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Ancre> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM ancres;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Ancre> ancres = new ArrayList<Ancre>();
			while(resultSet.next()) {
				ancres.add(this.map(resultSet));
			}
			resultSet.close();
			return ancres;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Ancre ancre, DAOFactory factory) {
		String sqlQuery = "INSERT INTO ancres (nom, x, y, z, angle) VALUES (?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, ancre.getNom());
			statement.setInt(2, ancre.getX());
			statement.setInt(3, ancre.getY());
			statement.setInt(4, ancre.getZ());
			statement.setInt(5, ancre.getAngle());

			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	ancre.setId(resultSet.getLong(1));
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
	protected Ancre map(ResultSet resultSet) throws DAOException {
		Ancre ancre = new Ancre();
		try {
			ancre.setId(resultSet.getLong("id_ancre"));
            ancre.setNom(resultSet.getString("nom"));
            ancre.setX(resultSet.getInt("x"));
            ancre.setY(resultSet.getInt("y"));
            ancre.setZ(resultSet.getInt("z"));
            ancre.setAngle(resultSet.getInt("angle"));
			return ancre;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

    /**
	Constructor
	*/
	public DAOAncre(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}