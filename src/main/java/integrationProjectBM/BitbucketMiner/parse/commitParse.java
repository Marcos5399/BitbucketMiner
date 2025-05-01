package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;

public class commitParse {
    private String id;
    private String title;
    private String message;
    private String authorName;
    private String authorEmail;
    private String authoredDate;
    private String committerName;
    private String committerEmail;
    private String committedDate;
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

    public String getCommitterName() {
        return committerName;
    }

    public void setCommitterName(String committerName) {
        this.committerName = committerName;
    }

    public String getCommitterEmail() {
        return committerEmail;
    }

    public void setCommitterEmail(String committerEmail) {
        this.committerEmail = committerEmail;
    }

    public String getCommittedDate() {
        return committedDate;
    }

    public void setCommittedDate(String committedDate) {
        this.committedDate = committedDate;
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
        cp.setAuthorName(commit.getAuthor().getUser().getUsername());
        cp.setAuthorEmail(commit.getAuthor().getUser().getNickname()); //BORRAR DESPUES: no tenemos el email definido
        cp.setAuthoredDate(commit.getDate());
        cp.setCommitterName(commit.getAuthor().getUser().getUsername()); //BORRAR DESPUES: no tenemos nada de committer
        cp.setCommitterEmail(commit.getAuthor().getUser().getNickname()); //BORRAR DESPUES: lo mismo
        cp.setCommittedDate(commit.getDate()); // BORRAR DESPUES no lo tenemos en nuestra clase esto definido, pongo esto
        cp.setWebUrl(commit.getLinks().getSelf().getHref());
        return cp;

    }
}
