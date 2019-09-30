package org.fossday.Model.Nodes.Lecture;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {

    @SerializedName("self")
    @Expose
    private List<Self> self = null;
    @SerializedName("collection")
    @Expose
    private List<Collection> collection = null;
    @SerializedName("about")
    @Expose
    private List<About> about = null;
    @SerializedName("author")
    @Expose
    private List<Author> author = null;
    @SerializedName("replies")
    @Expose
    private List<Reply> replies = null;
    @SerializedName("wp:featuredmedia")
    @Expose
    private List<WpFeaturedmedium> wpFeaturedmedia = null;
    @SerializedName("wp:attachment")
    @Expose
    private List<WpAttachment> wpAttachment = null;
    @SerializedName("wp:term")
    @Expose
    private List<WpTerm> wpTerm = null;
    @SerializedName("curies")
    @Expose
    private List<Cury> curies = null;

    public List<Self> getSelf() {
        return self;
    }

    public void setSelf(List<Self> self) {
        this.self = self;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<WpFeaturedmedium> getWpFeaturedmedia() {
        return wpFeaturedmedia;
    }

    public void setWpFeaturedmedia(List<WpFeaturedmedium> wpFeaturedmedia) {
        this.wpFeaturedmedia = wpFeaturedmedia;
    }

    public List<WpAttachment> getWpAttachment() {
        return wpAttachment;
    }

    public void setWpAttachment(List<WpAttachment> wpAttachment) {
        this.wpAttachment = wpAttachment;
    }

    public List<WpTerm> getWpTerm() {
        return wpTerm;
    }

    public void setWpTerm(List<WpTerm> wpTerm) {
        this.wpTerm = wpTerm;
    }

    public List<Cury> getCuries() {
        return curies;
    }

    public void setCuries(List<Cury> curies) {
        this.curies = curies;
    }

}
