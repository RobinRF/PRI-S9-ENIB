package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Parcours;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
DAOParcours
*/
public class DAOParcours extends DAO<Parcours> {

	/** Key for factory mapping */
	public static final String KEY = "DAOParcours";

	@Override
	public Parcours find(long id) throws DAOException {
		String sqlQuery = "SELECT * FROM parcours WHERE id_parcours = " + id + ";";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Parcours parcours = this.map(resultSet);
			resultSet.close();
			return parcours;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		} 
	}

	@Override
	public List<Parcours> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM parcours;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Parcours> parcours = new ArrayList<Parcours>();
			while(resultSet.next()) {
				parcours.add(this.map(resultSet));
			}
			resultSet.close();
			return parcours;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	protected Parcours map(ResultSet resultSet) throws DAOException {
		Parcours parcours = new Parcours();
		try {
			parcours.setId(resultSet.getLong("id_parcours"));
			parcours.setNom(resultSet.getString("nom"));
			return parcours;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

	@Override
	public void add(Parcours parcours, DAOFactory factory) {
		String sqlQuery = "INSERT INTO parcours (nom, station_depart) VALUES (?, ?);";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, parcours.getNom());
			if(parcours.getStation() != null) {
				statement.setLong(2, parcours.getStation().getId());
			} else {
				statement.setNull(2, java.sql.Types.BIGINT);
			}

			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	parcours.setId(resultSet.getLong(1));
            	}
				resultSet.close();
			} else {
				throw new DAOException("More rows than expected have been affected by the query : " + sqlQuery);
			}

		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

	/** 
	Fetch the station from already fetch parcours
	@param parcours The parcours to fetch station from
	@param daoStation The dao object to fetch station
	*/
	public void fetchStationDepart(Parcours parcours, DAOStation daoStation) throws DAOException {
		if (parcours.getId() == 0) {
			throw new DAOException("Cannot fetch parcours station with no set id");
		}
		String sqlQuery = "SELECT station_depart FROM parcours where id_parcours = " + parcours.getId() + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			long station_depart = resultSet.getLong("station_depart");
			if(station_depart != 0) {
				parcours.setStation(daoStation.find(station_depart));
			}
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}

	}

	/**
	Constructor
	*/
	public DAOParcours(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}