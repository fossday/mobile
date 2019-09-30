package org.fossday.Model;

public class Speaker {

    private int id;
    private String name;
    private String position;
    private String description;
    private String facebook;
    private String instagram;
    private String twitter;
    private String linkedin;
    private String youtube;
    private String github;

    public Speaker() {}

    public Speaker(int id, String name, String position, String description) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.description = description;
    }

    public Speaker(int id, String name, String position, String description, String facebook, String instagram, String twitter, String linkedin, String youtube, String github) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.description = description;
        this.facebook = facebook;
        this.instagram = instagram;
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.youtube = youtube;
        this.github = github;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
