package Arthub.repository.impl;

import Arthub.entity.Tag;
import Arthub.entity.TagArt;
import Arthub.repository.TagArtRepository;
import org.springframework.stereotype.Repository;
import utils.ConnectUtils;

import java.sql.*;
import java.util.ArrayList;
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

    @Override
    public void deleteTagArtByArtId(int artworkId) {
        String sql = "DELETE FROM TagArt WHERE ArtworkID = ?";
        try {
            ConnectUtils db = ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, artworkId);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Tag> getAllTagArtByArtworkId(int artworkId) {
        String sql = "SELECT t.TagID, tg.TagName " +
                "FROM TagArt t " +
                "JOIN Tag tg ON t.TagID = tg.TagID " +
                "WHERE t.ArtworkID = ?";  // Đặt giá trị cho tham số ?

        ArrayList<Tag> tags = new ArrayList<>();
        utils.ConnectUtils db = utils.ConnectUtils.getInstance();

        try (Connection connection = db.openConection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, artworkId); // Gán giá trị tham số ?

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tag tag = new Tag();
                    tag.setTagId(resultSet.getInt("TagID"));
                    tag.setTagName(resultSet.getString("TagName"));
                    tags.add(tag);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tags;
    }

}
