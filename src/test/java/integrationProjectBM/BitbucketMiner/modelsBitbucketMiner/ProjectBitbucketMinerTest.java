package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import integrationProjectBM.BitbucketMiner.service.CommitService;
import integrationProjectBM.BitbucketMiner.service.IssueService;
import integrationProjectBM.BitbucketMiner.service.ProjectService;
import integrationProjectBM.BitbucketMiner.util.Formatters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.stream.Collectors.toList;

@SpringBootTest
class ProjectBitbucketMinerTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    IssueService issueService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Project Formatter ")
    void toProjectFormatter() {
        Project project = projectService.getProject("gentlero","bitbucket-api").getBody();
        List<IssueBitbucketMiner> issues = issueService.getIssues("gentlero","bitbucket-api").getBody().getValues().stream()
                .map(i -> Formatters.issueFormatter(i, commentService,"gentlero", "bitbucket-api", 2 )).toList();
        List<CommitBitbucketMiner> commits = commitService.getAllCommits("gentlero","bitbucket-api").getBody().getValues().stream()
                .map(c -> Formatters.commitFormatter(c)).toList();
        ProjectBitbucketMiner projectBitbucketMiner = Formatters.projectFormatter(project, commits, issues);
        System.out.println(projectBitbucketMiner);
    }
}