package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import integrationProjectBM.BitbucketMiner.service.IssueService;
import integrationProjectBM.BitbucketMiner.util.Formatters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IssueBitbucketMinerTest {

    @Autowired
    IssueService service;
    @Autowired
    CommentService comments;

    @Test
    @DisplayName("Issue Formatters")
    void toIssueFormatter() {
        List<Issue> issues = service.getIssues("gentlero","bitbucket-api").getBody().getValues();
        Issue issue = issues.get(0);
        System.out.println(Formatters.issueFormatter(issue, comments, "gentlero", "bitbucket-api", 2));

    }
}