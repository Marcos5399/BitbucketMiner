package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import java.util.ArrayList;
import java.util.List;

public class IssueBitbucketMiner {
    private String id;
    private String title;
    private String description;
    private String state;
    private String created_at;
    private String updated_at;
    private String closed_at;
    private List<String> labels;
    private UserBitbucketMiner author;
    private UserBitbucketMiner assignee;
    private Integer votes;
    private List<CommentBitbucketMiner> comments;

    public IssueBitbucketMiner(String id, String title, String description,
                            String state, String created_at, String updated_at, String closed_at,
                            List<String> labels, Integer votes, UserBitbucketMiner author, UserBitbucketMiner assignee, List<CommentBitbucketMiner> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.closed_at = closed_at;
        this.labels = labels;
        this.votes = votes;
        this.author = author;
        this.assignee = assignee;
        this.comments = comments;
    }

    // Constructor vacío
    public IssueBitbucketMiner(){
        this.id = "";
        this.title = "";
        this.description = "";
        this.state = "";
        this.created_at = "";
        this.updated_at = "";
        this.closed_at = "";
        this.labels = new ArrayList<String>();
        this.votes = 0;
        this.author = new UserBitbucketMiner();
        this.assignee = new UserBitbucketMiner();
        this.comments = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(String closed_at) {
        this.closed_at = closed_at;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public UserBitbucketMiner getAuthor() {
        return author;
    }

    public void setAuthor(UserBitbucketMiner author) {
        this.author = author;
    }

    public UserBitbucketMiner getAssignee() {
        return assignee;
    }

    public void setAssignee(UserBitbucketMiner assignee) {
        this.assignee = assignee;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<CommentBitbucketMiner> getComments() {
        return comments;
    }

    public void setComments(List<CommentBitbucketMiner> comments) {
        this.comments = comments;
    }

}
