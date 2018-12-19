package fr.enib.pri2018.dao;

import fr.enib.pri2018.model.Image;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
DAOImage
*/
public class DAOImage extends DAO<Image> {

    /** Key for factory mapping */
	public static final String KEY = "DAOImage";

    @Override
	public Image find(long id) throws DAOException {
        String sqlQuery = "SELECT * from images WHERE id_image = " + id + " ;";
        try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			Image image = this.map(resultSet);
			resultSet.close();
			return image;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
    }

	@Override
	public List<Image> findAll() throws DAOException {
		String sqlQuery = "SELECT * FROM images;";
		try {
			ResultSet resultSet = this.connection.createStatement().executeQuery(sqlQuery);
			List<Image> images = new ArrayList<Image>();
			while(resultSet.next()) {
				images.add(this.map(resultSet));
			}
			resultSet.close();
			return images;
		} catch (SQLException e) {
			throw new DAOException("Exception occured with the query " + sqlQuery, e);
		}
	}

	@Override
	public void add(Image image, DAOFactory factory) throws DAOException {
		String sqlQuery = "INSERT INTO images (url, hauteur, largeur) VALUES (?, ?, ?);";
		try {

			PreparedStatement statement = this.connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, image.getUrl());
			statement.setInt(2, image.getHauteur());
			statement.setInt(3, image.getLargeur());
			
			// Check only one row is affected
			if(statement.executeUpdate() == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
                	image.setId(resultSet.getLong(1));
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
	protected Image map(ResultSet resultSet) throws DAOException {
		Image image = new Image();
		try {
			image.setId(resultSet.getLong("id_image"));
            image.setUrl(resultSet.getString("url"));
            image.setHauteur(resultSet.getInt("hauteur"));
            image.setLargeur(resultSet.getInt("largeur"));
			return image;
		} catch (SQLException e) {
			throw new DAOException("Exception occurend while mapping result set from parcours query", e);
		}
	}

    /**
	Constructor
	*/
	public DAOImage(DAOFactory factory) {
		this.connection = ((DatabaseDAOFactory)factory).getConnection();
	}

}