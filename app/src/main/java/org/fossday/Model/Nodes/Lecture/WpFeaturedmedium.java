package org.fossday.Model.Nodes.Lecture;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WpFeaturedmedium {

    @SerializedName("embeddable")
    @Expose
    private Boolean embeddable;
    @SerializedName("href")
    @Expose
    private String href;

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

