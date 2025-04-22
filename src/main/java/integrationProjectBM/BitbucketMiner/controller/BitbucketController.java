package integrationProjectBM.BitbucketMiner.controller;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bitbucketminer")
public class BitbucketController {
    @Autowired
    ProjectService projectService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/projects")
    public Project[] getProjects(@PathVariable String workspace, @RequestParam(defaultValue = "5") Integer nCommits, @RequestParam(defaultValue = "5") Integer nIssues, @RequestParam(defaultValue = "2") Integer maxPages) {
        return projectService.getAllProjects(workspace, nCommits, nIssues, maxPages );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{workspace}")
    public Project postProject(@PathVariable String workspace,@Valid @RequestBody Project project) {
        return projectService.postProject(workspace, project);
    }
}
