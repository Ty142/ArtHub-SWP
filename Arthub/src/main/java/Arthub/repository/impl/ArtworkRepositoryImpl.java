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
        String sql = "Insert into Artworks values (?,?,?,?,?,?,?,?,?)";
        Artwork artwork = artworkConverter.convertArtworkDTOToArtworkEntity(artworkDTO);
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection connection = db.openConection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, artwork.getArtworkID());
            statement.setString(2, artwork.getArtworkName());
            statement.setString(3, artwork.getDescription());
            statement.setInt(4, artwork.getLikes());
            statement.setBoolean(5, artwork.isPurchasable());
            statement.setDouble(6, artwork.getPrice());
            statement.setInt(7, artwork.getUserID());
            statement.setInt(8, artwork.getLibraryID());
            statement.setInt(9, artwork.getStatus());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
