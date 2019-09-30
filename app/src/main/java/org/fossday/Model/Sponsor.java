package org.fossday.Model;

public class Sponsor {

    private int eid;
    private String name;
    private String website;
    private String type;

    public Sponsor() {
    }

    public Sponsor(int eid, String name, String website, String type) {
        this.eid = eid;
        this.name = name;
        this.website = website;
        this.type = type;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

