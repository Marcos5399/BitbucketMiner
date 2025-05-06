package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.issue.Assignee;
import integrationProjectBM.BitbucketMiner.model.issue.Comments;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.user.User;
import integrationProjectBM.BitbucketMiner.service.commentService;

import java.util.ArrayList;
import java.util.List;

public class issueParse {
    private String id;
    private String title;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    private String closedAt;
    private List<String> labels;
    private userParse author;
    private userParse assignee;
    private Integer votes;
    private List<commentParse> comments;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public userParse getAuthor() {
        return author;
    }

    public void setAuthor(userParse author) {
        this.author = author;
    }

    public userParse getAssignee() {
        return assignee;
    }

    public void setAssignee(userParse assignee) {
        this.assignee = assignee;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer upvotes) {
        this.votes = upvotes;
    }


    public List<commentParse> getComments() {
        return comments;
    }

    public void setComments(List<commentParse> comments) {
        this.comments = comments;
    }

    public issueParse toissueParse(Issue issue, commentService commentService, String workspace, String repo_slug) {

        commentParse cp = new commentParse();
        List<commentParse> comments = commentService.getcommentsI(workspace,repo_slug,issue.getId().toString()).getBody().getValues().stream().map(c-> cp.toCommentParse(c)).toList();
        issueParse ip = new issueParse();
        userParse uPr = new userParse();
        userParse uPa = new userParse();
        List<String> aux = new ArrayList<>();
        aux.add(issue.getKind());
        aux.add(issue.getPriority());
        ip.setId(issue.getId().toString());
        ip.setTitle(issue.getTitle());
        ip.setDescription(issue.getContent().getRaw());
        ip.setState(issue.getState());
        ip.setCreatedAt(issue.getCreatedOn());
        ip.setUpdatedAt(issue.getUpdatedOn());
        ip.setClosedAt(issue.getState().equals("resolved") ? issue.getUpdatedOn() : null);
        ip.setLabels(aux);
        ip.setAuthor(uPr.reporterToUserParse(issue.getReporter()));//BORRAR DESPUES: no tenemos clase user en issue, tenemos clase reporter y assignee que tienen las mismas propiedades.
        if (issue.getAssignee() != null) {
            ip.setAssignee(uPa.assigneeToUserParse(issue.getAssignee()));
        }       else {
            ip.setAssignee(new userParse());
        }

        ip.setVotes(issue.getVotes());
        ip.setComments(comments);
        return ip;


    }
    @Override
    public String toString() {
        return "issueParse{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt='" + closedAt + '\'' +
                ", labels=" + (labels != null ? labels.toString() : "null") +
                ", author=" + (author != null ? author.toString() : "null") +
                ", assignee=" + (assignee != null ? assignee.toString() : "null") +
                ", votes=" + votes +
                ", comments=" + (comments != null ? comments.toString() : "null") +
                '}';
    }


}
