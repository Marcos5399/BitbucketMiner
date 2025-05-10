package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.CommitBitbucketMiner;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.IssueBitbucketMiner;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.ProjectBitbucketMiner;
import integrationProjectBM.BitbucketMiner.util.Formatters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {
    @Autowired
    ProjectService projectService;
    @Autowired
    CommitService commitService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get a project")
    void getProject() {
        ResponseEntity<Project> response = projectService.getProject("gentlero","bitbucket-api");
        Project project = response.getBody();
        assertNotNull(project, "Project should not be null");
        assertEquals("bitbucket-api", project.getName(), "Project name should match");
        System.out.println(project);
    }

    @Test
    // Para que funciones este test tiene que estar GitMiner corriendo, ya que hace llamada a localhost:8080
    @DisplayName("Send project to GitMiner")
    void sendProjectToGitMiner() {
        Project project = projectService.getProject("gentlero","bitbucket-api").getBody();
        assertNotNull(project, "Project should not be null");
        List<IssueBitbucketMiner> issues = issueService.getIssues("gentlero","bitbucket-api").getBody().getValues().stream()
                .map(i -> Formatters.issueFormatter(i, commentService,"gentlero", "bitbucket-api", 2 )).toList();
        List<CommitBitbucketMiner> commits = commitService.getAllCommits("gentlero","bitbucket-api").getBody().getValues().stream()
                .map(c -> Formatters.commitFormatter(c)).toList();
        ProjectBitbucketMiner projectBitbucketMiner = Formatters.projectFormatter(project, commits, issues);


        ResponseEntity<ProjectBitbucketMiner> response = projectService.sendProjectToGitMiner(projectBitbucketMiner);
        ProjectBitbucketMiner sentProject = response.getBody();
        assertNotNull(sentProject, "Sent project should not be null");
        assertEquals(project.getName(), sentProject.getName(), "Sent project name should match");
        System.out.println(sentProject);
    }
}