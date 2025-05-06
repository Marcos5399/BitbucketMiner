package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import integrationProjectBM.BitbucketMiner.service.IssueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class issueBitbucketMinerTest {

    @Autowired
    IssueService service;
    @Autowired
    CommentService comments;

    @Test
    @DisplayName("issue parse")
    void toissueParse() {
        IssueBitbucketMiner test = new IssueBitbucketMiner();
        List<Issue> issues = service.getIssues("gentlero","bitbucket-api").getBody().getValues();
        Issue issue = issues.get(0);
        System.out.println(test.toissueParse(issue,comments,"gentlero","bitbucket-api"));

    }
}