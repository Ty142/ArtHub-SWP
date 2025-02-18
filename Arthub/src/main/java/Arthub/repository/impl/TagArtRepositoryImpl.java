package Arthub.repository.impl;

import Arthub.repository.TagArtRepository;
import utils.ConnectUtils;

import java.sql.*;

public class TagArtRepositoryImpl implements TagArtRepository {
    @Override
    public void addTagArtUserIdAndTagId(int artworkId, int tagId) {
        String sql = "INSERT INTO TagArt values (?,?)";
        try{
            ConnectUtils db = ConnectUtils.getInstance();
            Connection conn = db.openConection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, artworkId);
            statement.setInt(2, tagId);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTagIdByArtId(int ArtworkId) {
        String sql = "SELECT t.[TagArtID] FROM TagArt t WHERE t.[ArtworkID] = ?";


    }
}
