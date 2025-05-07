
package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import java.util.ArrayList;
import java.util.List;

public class ProjectBitbucketMiner {
    private String id;
    private String name;
    private String web_url;
    private List<CommitBitbucketMiner> commits;
    private List<IssueBitbucketMiner> issues;

    public ProjectBitbucketMiner(String id, String name, String webUrl, List<CommitBitbucketMiner> commits, List<IssueBitbucketMiner> issues) {
        this.id = id;
        this.name = name;
        this.web_url = webUrl;
        this.commits = commits;
        this.issues = issues;
    }
    //Constructor vacío
    public ProjectBitbucketMiner(){
        this.id = "";
        this.name = "";
        this.web_url = "";
        this.commits = new ArrayList();
        this.issues = new ArrayList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public List<CommitBitbucketMiner> getCommits() {
        return commits;
    }

    public void setCommits(List<CommitBitbucketMiner> commits) {
        this.commits = commits;
    }

    public List<IssueBitbucketMiner> getIssues() {
        return issues;
    }

    public void setIssues(List<IssueBitbucketMiner> issues) {
        this.issues = issues;
    }

}


