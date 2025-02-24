
package Arthub.repository;

import Arthub.dto.TagDTO;
import Arthub.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public interface TagRepository  {
    ArrayList<Tag> getAllTags();
    Tag getTagById(int id);

}
