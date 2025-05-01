package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.service.issueService;

import java.util.ArrayList;
import java.util.List;

public class projectParse {
    private String id;
    private String name;
    private String webUrl;
    private List<Commit> commits;
    private List<Issue> issues;


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

    public List<Commit> getCommits() {
        return commits;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public projectParse toProjectParse(Project project, List<Commit> commits, List<Issue> issues) { //BORRAR DESPUES: tenemos que meterle como parametros lo que obtenemos de getIssue y getCommits
        projectParse pp = new projectParse();
        pp.setId(project.getKey());
        pp.setName(project.getName());
        pp.setWebUrl(project.getLinks().getSelf().getHref());
        pp.setCommits(commits);
        pp.setIssues(issues);
        return pp;
    }
}
