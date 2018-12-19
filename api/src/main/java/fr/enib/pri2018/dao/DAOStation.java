package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Station;
import fr.enib.pri2018.model.Accroche;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
DAOStation
*/
public class DAOStation extends DAO<Station> {

    /** Key for factory mapping */
	public static final String KEY = "DAOStation";

    @Override
	public Station find(long id) throws DAOException {
		// Check if id exist and build an new instance of Station
		String sqlQuery = "SELECT * FROM stations WHERE id_station = " + id + ";";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Station station = this.map(resultSet);
			resultSet.close();
			return station;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public List<Station> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM stations;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Station> stations = new ArrayList<Station>();
			while(resultSet.next()) {
				stations.add(this.map(resultSet));
			}
			resultSet.close();
			return stations;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Station station, DAOFactory factory) {
		String sqlQuery = "INSERT INTO stations (station_suivante) VALUES (?);";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			// Check null reference for station suivante
			Station stationSuivante = station.getStationSuivante();
			if (stationSuivante != null) {
				// If station suivante has no id the recursive call to add
				if(stationSuivante.getId() == 0) {
					this.add(stationSuivante, factory);
				}
				statement.setLong(1, stationSuivante.getId());
			} else {
				statement.setNull(1, java.sql.Types.BIGINT);
			}

			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	station.setId(resultSet.getLong(1));
            	}
				resultSet.close();
			} else {
				throw new DAOException("More rows than expected have been affected by the query : " + sqlQuery);
			}

			// Check references to accroches
			List<Accroche> accroches = station.getAccroches();
			if(accroches.size() > 0) {
				Iterator i = accroches.iterator();
				while(i.hasNext()) {
					Accroche accroche = (Accroche)i.next();
					if(accroche.getId() != 0) {
						String query = "INSERT INTO stations_accroches VALUES (?, ?);";
						PreparedStatement state = this.connection.prepareStatement(query);
						state.setLong(1, station.getId());
						state.setLong(2, accroche.getId());
						state.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	protected Station map(ResultSet resultSet) throws DAOException {
		Station station = new Station();
		try {
			station.setId(resultSet.getLong("id_station"));
			return station;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

	/**
	Fetch station suivante
	@param station The to fetch the station suivante
	*/
	public void fetchStationSuivante(Station station) {
		String sqlQuery = "SELECT station_suivante FROM stations WHERE id_station = " + Long.toString(station.getId()) + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			long station_suivante = resultSet.getLong("station_suivante");
			if(station_suivante != 0) {
				station.setStationSuivante(this.find(station_suivante));
			} // Else no station suivante 
			resultSet.close();
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	/** 
	Fetch all the accroches from the specified station
	@param station The station to fetch from
	@param daoAccroche The dao object to fetch accroche from database
	*/
	public void fetchAccroches(Station station, DAOAccroche daoAccroche) {
		String sqlQuery = "SELECT id_accroche FROM stations_accroches WHERE id_station = " + Long.toString(station.getId()) + " ;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Accroche> accroches = station.getAccroches();
			while(resultSet.next()) {
				accroches.add(daoAccroche.find(resultSet.getLong("id_accroche")));
			}
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	/**
	Constructor
	*/
	public DAOStation(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}
}
