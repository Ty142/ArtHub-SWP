package Arthub.repository.impl;

import Arthub.converter.ArtworkConverter;
import Arthub.dto.ArtworkDTO;
import Arthub.entity.Artwork;
import Arthub.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void addArtwork(ArtworkDTO artworkDTO) {
        String sql = "Insert into Artworks values ([ArtworkID],[ArtworkName],[Description],[Purchasable],[Price],[ImageFile],[UserID],[Status])";
        Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artwork.getArtworkID());
            statement.setString(2, artwork.getArtworkName());
            statement.setString(3, artwork.getDescription());
            statement.setBoolean(4, artwork.isPurchasable());
            statement.setDouble(5, artwork.getPrice());
            statement.setInt(6, artwork.getUserID());
            statement.setInt(7, artwork.getStatus());
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
