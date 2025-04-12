
package integrationProjectBM.BitbucketMiner.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    @JsonProperty("rendered")
    private Rendered rendered;
    @JsonProperty("hash") // Equivale al id del GitMiner
    private String hash;
    @JsonProperty("repository")
    private Repository repository;
    @JsonProperty("links") // Equivale a web_url del GitMiner
    private Links__1 links;
    @JsonProperty("author") // Mirar clase Author
    private Author author;
    @JsonProperty("summary") // Equivale al titulo del GitMiner
    private Summary summary;
    @JsonProperty("participants")
    private List<Object> participants;
    @JsonProperty("parents")
    private List<Parent> parents;
    @JsonProperty("date") // Equivale al authored_date del GitMiner
    private String date;
    @JsonProperty("message") // Equivale al commit_message del GitMiner
    private String message;
    @JsonProperty("type")
    private String type;

    @JsonProperty("rendered")
    public Rendered getRendered() {
        return rendered;
    }

    @JsonProperty("rendered")
    public void setRendered(Rendered rendered) {
        this.rendered = rendered;
    }

    @JsonProperty("hash")
    public String getHash() {
        return hash;
    }

    @JsonProperty("hash")
    public void setHash(String hash) {
        this.hash = hash;
    }

    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @JsonProperty("links")
    public Links__1 getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(Links__1 links) {
        this.links = links;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    @JsonProperty("summary")
    public Summary getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    @JsonProperty("participants")
    public List<Object> getParticipants() {
        return participants;
    }

    @JsonProperty("participants")
    public void setParticipants(List<Object> participants) {
        this.participants = participants;
    }

    @JsonProperty("parents")
    public List<Parent> getParents() {
        return parents;
    }

    @JsonProperty("parents")
    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Commit.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rendered");
        sb.append('=');
        sb.append(((this.rendered == null)?"<null>":this.rendered));
        sb.append(',');
        sb.append("hash");
        sb.append('=');
        sb.append(((this.hash == null)?"<null>":this.hash));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null)?"<null>":this.repository));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        sb.append("author");
        sb.append('=');
        sb.append(((this.author == null)?"<null>":this.author));
        sb.append(',');
        sb.append("summary");
        sb.append('=');
        sb.append(((this.summary == null)?"<null>":this.summary));
        sb.append(',');
        sb.append("participants");
        sb.append('=');
        sb.append(((this.participants == null)?"<null>":this.participants));
        sb.append(',');
        sb.append("parents");
        sb.append('=');
        sb.append(((this.parents == null)?"<null>":this.parents));
        sb.append(',');
        sb.append("date");
        sb.append('=');
        sb.append(((this.date == null)?"<null>":this.date));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
