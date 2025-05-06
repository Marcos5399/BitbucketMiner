
package integrationProjectBM.BitbucketMiner.controller;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.parse.commitParse;
import integrationProjectBM.BitbucketMiner.parse.issueParse;
import integrationProjectBM.BitbucketMiner.parse.projectParse;
import integrationProjectBM.BitbucketMiner.service.commentService;
import integrationProjectBM.BitbucketMiner.service.commitService;
import integrationProjectBM.BitbucketMiner.service.issueService;
import integrationProjectBM.BitbucketMiner.service.projectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/bitbucket")
public class projectController {

    private final RestTemplate restTemplate;
    private final projectService projectService;
    private final commitService commitService;
    private final issueService issueService;
    private final commentService commentService;

    @Autowired
    public projectController(
            RestTemplate restTemplate,
            projectService projectService,
            commitService commitService,
            issueService issueService,
            commentService commentService
    ){
        this.restTemplate = restTemplate;
        this.projectService = projectService;
        this.commitService = commitService;
        this.issueService = issueService;
        this.commentService = commentService;
    }


    @GetMapping("/{workspace}/{repo_slug}")
    public projectParse getProject(@PathVariable String workspace, @PathVariable String repo_slug,
                                   @RequestParam(required = false, name = "nCommits", defaultValue = "5") Integer nCommits,
                                   @RequestParam(required = false, name = "nIssues", defaultValue = "5") Integer nIssues,
                                   @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages){


        Project project = projectService.getProject(workspace,repo_slug).getBody();
        commitParse cP = new commitParse();
        issueParse iP = new issueParse();

        List<commitParse> commits= commitService.getAllCommitsPages(workspace,repo_slug,maxPages).stream().map(c-> cP.toCommitParse(c)).toList().subList(0,nCommits-1);
        List<issueParse> issues = issueService.getIssuesPages(workspace,repo_slug,maxPages).stream().map(i-> iP.toissueParse(i,commentService,workspace,repo_slug)).toList().subList(0,nIssues-1);

    return new projectParse().toProjectParse(project,commits,issues);



    }

    @PostMapping("/{workspace}/{repo_slug}")
    @ResponseStatus(HttpStatus.CREATED)
    public projectParse createProject(@PathVariable String workspace, @PathVariable String repo_slug,
                                      @RequestParam(required = false, name = "nCommits", defaultValue = "5") Integer nCommits,
                                      @RequestParam(required = false, name = "nIssues", defaultValue = "5") Integer nIssues,
                                      @RequestParam(required = false, name = "maxPages", defaultValue = "2") Integer maxPages){


        Project project = projectService.getProject(workspace,repo_slug).getBody();
        commitParse cP = new commitParse();
        issueParse iP = new issueParse();

        List<commitParse> commits= commitService.getAllCommitsPages(workspace,repo_slug,maxPages).stream().map(c-> cP.toCommitParse(c)).toList().subList(0,nCommits-1);
        List<issueParse> issues = issueService.getIssuesPages(workspace,repo_slug,maxPages).stream().map(i-> iP.toissueParse(i,commentService,workspace,repo_slug)).toList().subList(0,nIssues-1);

        projectParse projectParse = new projectParse().toProjectParse(project,commits,issues);

        return projectService.sendProjectToGitMiner(projectParse).getBody();
    }

}
