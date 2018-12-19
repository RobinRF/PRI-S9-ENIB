package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Accroche;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOAccroche
*/
public class DAOAccroche extends DAO<Accroche> {

    /** Key for factory mapping */
	public static final String KEY = "DAOAccroche";

    @Override
	public Accroche find(long id) throws DAOException {
        String sqlQuery = "SELECT * from accroches WHERE id_accroche = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Accroche accroche = this.map(resultSet);
			resultSet.close();
			return accroche;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Accroche> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM accroches;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Accroche> accroches = new ArrayList<Accroche>();
			while(resultSet.next()) {
				accroches.add(this.map(resultSet));
			}
			resultSet.close();
			return accroches;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

    @Override
	protected Accroche map(ResultSet resultSet) throws DAOException {
		Accroche accroche = new Accroche();
		try {
			accroche.setId(resultSet.getLong("id_accroche"));
			return accroche;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

    @Override
	public void add(Accroche accroche, DAOFactory factory) {
		String sqlQuery = "INSERT INTO accroches (id_ancre, id_poster) VALUES (?, ?);";
		try {
			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			// Check null reference on objet association
			if(accroche.getAncre() != null) {
				factory.getDAOAncre().add(accroche.getAncre(), factory);
				statement.setLong(1, accroche.getAncre().getId());
			} else {
				statement.setNull(1, java.sql.Types.BIGINT);
			}
			// Check null reference on objet association
			if(accroche.getPoster() != null) {
				factory.getDAOPoster().add(accroche.getPoster(), factory);
				statement.setLong(2, accroche.getPoster().getId());
			} else {
				statement.setNull(2, java.sql.Types.BIGINT);
			}
			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	accroche.setId(resultSet.getLong(1));
            	}
				resultSet.close();
			} else {
				throw new DAOException("More rows than expected have been affected by the query : " + sqlQuery);
			}
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

    /**
    Fetch the associated ancre
    @param accroche the accroche to fetch from
    @param daoAncre the dao object to fetch ancre from the database
    */
    public void fetchAncre(Accroche accroche, DAOAncre daoAncre) {
        String sqlQuery = "SELECT id_ancre FROM accroches WHERE id_accroche = " + accroche.getId() + " ;";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
            accroche.setAncre(daoAncre.find(resultSet.getLong("id_ancre")));
			resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Exception occured with the query " + sqlQuery, e);
        }
    }

    /**
    Fetch the associated poster
    @param poster the poster to fetch from
    @param daoPoster the dao object to fetch poster from the database
    */
    public void fetchPoster(Accroche accroche, DAOPoster daoPoster) {
        String sqlQuery = "SELECT id_poster FROM accroches WHERE id_accroche = " + accroche.getId() + " ;";
        try {
            ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
            accroche.setPoster(daoPoster.find(resultSet.getLong("id_poster")));
			resultSet.close();
        } catch (SQLException e) {
            throw new DAOException("Exception occured with the query " + sqlQuery, e);
        }
    }

    /**
	Constructor
	*/
	public DAOAccroche(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}