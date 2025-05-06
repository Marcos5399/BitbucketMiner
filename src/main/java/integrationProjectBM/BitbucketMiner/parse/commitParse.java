package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;

public class commitParse {
    private String id;
    private String title;
    private String message;
    private String authorName;
    private String authorEmail;
    private String authoredDate;
    private String webUrl;


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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthoredDate() {
        return authoredDate;
    }

    public void setAuthoredDate(String authoredDate) {
        this.authoredDate = authoredDate;
    }



    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public commitParse toCommitParse(Commit commit) {
        commitParse cp = new commitParse();
        cp.setId(commit.getHash());
        cp.setTitle(commit.getSummary().getRaw());
        cp.setMessage(commit.getMessage());
        cp.setAuthorName(commit.getAuthor().getUser().getNickname());
        cp.setAuthorEmail(commit.getAuthor().getUser().getNickname()); //BORRAR DESPUES: no tenemos el email definido
        cp.setAuthoredDate(commit.getDate());
        cp.setWebUrl(commit.getLinks().getSelf().getHref());
        return cp;

    }

    @Override
    public String toString() {
        return "commitParse{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authoredDate='" + authoredDate + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
