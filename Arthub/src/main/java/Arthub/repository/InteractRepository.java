    package Arthub.repository;

    import Arthub.entity.Interact;

    import java.util.List;

    public interface InteractRepository {
        void save(Interact interact);
        List<Interact> findByArtworkIDAndUserIDAndActivityID(int artworkID, int userID, int activityID, String date);
    }
