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
    private String refId;
    private String title;
    private String description;
    private String state;
    private String createdAt;
    private String updatedAt;
    private String closedAt;
    private List<String> labels;
    private userParse author;
    private userParse assignee;
    private Integer upvotes;
    private Integer downvotes;
    private String webUrl;
    private List<commentParse> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
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

    public Integer getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(Integer downvotes) {
        this.downvotes = downvotes;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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
        ip.setRefId(issue.getId().toString()); //BORRAR DESPUES: no tengo nada que se llame refid
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

        ip.setUpvotes(issue.getVotes()); //BORRAR DESPUES: no tenemos clase upvotes ni downvotes
        ip.setDownvotes(issue.getVotes());
        ip.setWebUrl(issue.getLinks().getSelf().getHref());
        ip.setComments(comments);
        return ip;


    }


}
