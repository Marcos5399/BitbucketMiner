
package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.project.Project;

import java.util.List;

public class ProjectBitbucketMiner {
    private String id;
    private String name;
    private String webUrl;
    private List<CommitBitbucketMiner> commits;
    private List<IssueBitbucketMiner> issues;


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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
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


