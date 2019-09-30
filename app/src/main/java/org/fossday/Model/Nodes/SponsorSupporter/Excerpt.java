package org.fossday.Model.Nodes.SponsorSupporter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Excerpt {

    @SerializedName("rendered")
    @Expose
    private String rendered;
    @SerializedName("protected")
    @Expose
    private Boolean _protected;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public Boolean getProtected() {
        return _protected;
    }

    public void setProtected(Boolean _protected) {
        this._protected = _protected;
    }

}

