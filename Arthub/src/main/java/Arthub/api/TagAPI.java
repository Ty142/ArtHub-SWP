
package Arthub.api;

import Arthub.repository.TagRepository;
import Arthub.repository.impl.TagRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import Arthub.entity.Tag;


import java.util.List;

@RestController
@RequestMapping("/api/Tag/")
public class TagAPI {

    @Autowired
    TagRepository tagRepository;

    @GetMapping
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagRepository.getAllTags();
        if (tags.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable int id) {
        Tag tag = tagRepository.getTagById(id);

        if (tag == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tag);
    }
}
