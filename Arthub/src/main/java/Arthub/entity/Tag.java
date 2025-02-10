package Arthub.entity;

import Arthub.repository.impl.TagRepositoryImpl;

public class Tag {
    private int tagId;
    private String tagName;

    public Tag(){

    }

    public int getTagId() {return tagId;}
    public void setTagId(int tagId) {this.tagId = tagId;}

    public String getTagName() {return tagName;}
    public void setTagName(String tagName) {this.tagName = tagName;}

    @Override
    public String toString() {
        return "Tag{" + "tagId=" + tagId + ", tagName=" + tagName + '}';
    }
}
