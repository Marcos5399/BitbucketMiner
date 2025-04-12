
package integrationProjectBM.BitbucketMiner.model.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links__2 {

    @JsonProperty("self")
    private Self__2 self;
    @JsonProperty("html")
    private Html__2 html;
    @JsonProperty("repositories")
    private Repositories repositories;
    @JsonProperty("avatar")
    private Avatar__2 avatar;

    @JsonProperty("self")
    public Self__2 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__2 self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html__2 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__2 html) {
        this.html = html;
    }

    @JsonProperty("repositories")
    public Repositories getRepositories() {
        return repositories;
    }

    @JsonProperty("repositories")
    public void setRepositories(Repositories repositories) {
        this.repositories = repositories;
    }

    @JsonProperty("avatar")
    public Avatar__2 getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar__2 avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("repositories");
        sb.append('=');
        sb.append(((this.repositories == null)?"<null>":this.repositories));
        sb.append(',');
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
