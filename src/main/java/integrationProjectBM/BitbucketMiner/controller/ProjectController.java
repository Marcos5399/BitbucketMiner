
package integrationProjectBM.BitbucketMiner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.CommitBitbucketMiner;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.IssueBitbucketMiner;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.ProjectBitbucketMiner;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import integrationProjectBM.BitbucketMiner.service.CommitService;
import integrationProjectBM.BitbucketMiner.service.IssueService;
import integrationProjectBM.BitbucketMiner.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static integrationProjectBM.BitbucketMiner.util.Formatters.*;


@RestController
@RequestMapping("/bitbucket")
public class ProjectController {

    private final RestTemplate restTemplate;
    private final ProjectService projectService;
    private final CommitService commitService;
    private final IssueService issueService;
    private final CommentService commentService;

    @Autowired
    public ProjectController(
            RestTemplate restTemplate,
            ProjectService projectService,
            CommitService commitService,
            IssueService issueService,
            CommentService commentService
    ){
        this.restTemplate = restTemplate;
        this.projectService = projectService;
        this.commitService = commitService;
        this.issueService = issueService;
        this.commentService = commentService;
    }


    @GetMapping("/{workspace}/{repoSlug}")
    public ProjectBitbucketMiner getProject(@PathVariable String workspace, @PathVariable String repoSlug,
                                            @RequestParam(required = false, name = "nCommits", defaultValue = "5") Integer nCommits,
                                            @RequestParam(required = false, name = "nIssues", defaultValue = "5") Integer nIssues,
                                            @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages){


        Project project = projectService.getProject(workspace, repoSlug).getBody();
        List<CommitBitbucketMiner> commits= commitService.getAllCommitsPages(workspace, repoSlug,maxPages).stream().map(c-> commitFormatter(c)).toList().subList(0,nCommits);
        List<IssueBitbucketMiner> issues = issueService.getIssuesPages(workspace, repoSlug,maxPages).stream().map(i-> issueFormatter(i,commentService,workspace, repoSlug, maxPages)).toList().subList(0,nIssues);

    return projectFormatter(project,commits,issues);
    }

    @PostMapping("/{workspace}/{repoSlug}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectBitbucketMiner createProject(@PathVariable String workspace, @PathVariable String repoSlug,
                                               @RequestParam(required = false, name = "nCommits", defaultValue = "5") Integer nCommits,
                                               @RequestParam(required = false, name = "nIssues", defaultValue = "5") Integer nIssues,
                                               @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages){


        Project project = projectService.getProject(workspace, repoSlug).getBody();
        List<CommitBitbucketMiner> commits= commitService.getAllCommitsPages(workspace, repoSlug,maxPages).stream().map(c-> commitFormatter(c)).toList().subList(0,nCommits);
        List<IssueBitbucketMiner> issues = issueService.getIssuesPages(workspace, repoSlug,maxPages).stream().map(i-> issueFormatter(i,commentService,workspace, repoSlug, maxPages)).toList().subList(0,nIssues);

        ProjectBitbucketMiner ProjectBitbucketMiner = projectFormatter(project,commits,issues);
        System.out.println("Web URL: " + ProjectBitbucketMiner.getWeb_url()); //debug
        return projectService.sendProjectToGitMiner(ProjectBitbucketMiner).getBody();
    }
}
