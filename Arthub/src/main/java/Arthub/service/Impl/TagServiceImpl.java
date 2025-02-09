package Arthub.service.Impl;

import Arthub.entity.Tag;
import Arthub.repository.TagRepository;
import Arthub.repository.impl.TagRepositoryImpl;
import Arthub.service.TagService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepositoryImpl tagRepositoryImpl;

    public TagServiceImpl() { this.tagRepositoryImpl = new TagRepositoryImpl(); }


    public ArrayList<Tag> getAllTags() { return tagRepositoryImpl.getAllTags(); }

    @Override
    public Tag getTagById(int id) { return tagRepositoryImpl.getTagById(id); }

}
