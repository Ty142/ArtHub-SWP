package Arthub.repository.impl;

import Arthub.entity.Artwork;
import Arthub.entity.TagArt;
import Arthub.repository.TagArtRepository;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.List;

@Repository
public class TagArtRepositoryImpl implements TagArtRepository {
    @Override
    public void addTagArtUserIdAndTagId(List<TagArt> tagArtList, int artworkId) {
        for (TagArt tagArt : tagArtList) {
            String sql = "INSERT INTO TagArt values (?,?)";
            try {
                ConnectUtils db = ConnectUtils.getInstance();
                Connection conn = db.openConection();
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1,artworkId );
                statement.setInt(2, tagArt.getTagID());
                statement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getTagIdByArtId(int ArtworkId) {
        String sql = "SELECT t.[TagArtID] FROM TagArt t WHERE t.[ArtworkID] = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ArtworkId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("TagArtID");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
