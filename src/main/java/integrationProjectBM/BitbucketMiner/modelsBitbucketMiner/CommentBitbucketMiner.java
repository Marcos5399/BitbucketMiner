package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

public class CommentBitbucketMiner {
    private String id;
    private String body;
    private UserBitbucketMiner author;
    private String created_at;
    private String updated_at;

    public CommentBitbucketMiner(String id, String body, String created_at, String updated_at, UserBitbucketMiner author) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
    // Constructor vacío
    public CommentBitbucketMiner() {
        this.id = "";
        this.body = "";
        this.author = new UserBitbucketMiner();
        this.created_at = "";
        this.updated_at = "";
    }

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

    public UserBitbucketMiner getAuthor() {
        return author;
    }

    public void setAuthor(UserBitbucketMiner author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "CommentBitbucketMiner{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", author=" + author +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
