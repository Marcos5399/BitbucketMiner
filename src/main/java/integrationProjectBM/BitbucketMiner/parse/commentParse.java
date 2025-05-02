package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.user.User;

public class commentParse {
    private String id;
    private String body;
    private userParse author;
    private String createdAt;
    private String updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public userParse getAuthor() {
        return author;
    }

    public void setAuthor(userParse author) {
        this.author = author;
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


    public commentParse toCommentParse(Comment comment) {
        commentParse cP = new commentParse();
        userParse uP = new userParse();

        cP.setId(comment.getId().toString());
        cP.setAuthor(uP.userToUserParse(comment.getUser()));
        cP.setBody(comment.getContent().getRaw());
        cP.setCreatedAt(comment.getCreatedOn());
        cP.setUpdatedAt(comment.getUpdatedOn().toString());
        return cP;

    }

}
