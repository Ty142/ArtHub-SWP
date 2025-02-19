package Arthub.repository.impl;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtworkRepositoryImpl implements ArtworkRepository {
    @Autowired
    private ArtworkConverter artworkConverter;

    @Override
    public void saveArtPicture(int id, String Artwork) {
        String sql = "UPDATE Artworks SET ImageFile where id = ?";
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, Artwork);
            statement.executeUpdate();
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addArtwork(Artwork artwork) {
        String sql = "Insert into Artworks([ArtworkName],[Description],[Purchasable],[Price],[ImageFile],[UserID],[Status],[DateCreated]) values (?,?,?,?,?,?,?,?)";
        int generatedID = -1;
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, artwork.getArtworkName());
            statement.setString(2, artwork.getDescription());
            statement.setBoolean(3, artwork.isPurchasable());
            statement.setDouble(4, artwork.getPrice());
            statement.setString(5, artwork.getImageFile());
            statement.setInt(6, artwork.getUserID());
            statement.setInt(7, artwork.getStatus());
            statement.setString(8, artwork.getDateCreated());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                generatedID = rs.getInt(1);
            }
            rs.close();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generatedID;
    }

    @Override
    public List<Artwork> getArtworks() {
        List<Artwork> result = new ArrayList<Artwork>();
        String sql = "SELECT * FROM Artworks";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Artwork artwork = new Artwork();
                artwork.setArtworkID(resultSet.getInt("ArtworkID"));
                artwork.setArtworkName(resultSet.getString("ArtworkName"));
                artwork.setDescription(resultSet.getString("Description"));
                artwork.setPurchasable(resultSet.getBoolean("Purchasable"));
                artwork.setPrice(resultSet.getDouble("Price"));
                artwork.setUserID(resultSet.getInt("UserID"));
                artwork.setImageFile(resultSet.getString("ImageFile"));
                artwork.setLikes(resultSet.getInt("Likes"));
                result.add(artwork);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new    RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


}
