package Arthub.service;

import Arthub.entity.Tag;
import java.util.ArrayList;

public interface TagService {
    ArrayList<Tag> getAllTags();

    Tag getTagById(int id);
}
