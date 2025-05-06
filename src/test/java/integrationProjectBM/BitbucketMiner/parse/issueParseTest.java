package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.service.commentService;
import integrationProjectBM.BitbucketMiner.service.issueService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class issueParseTest {

    @Autowired
    issueService service;
    @Autowired
    commentService comments;

    @Test
    @DisplayName("issue parse")
    void toissueParse() {
        issueParse test = new issueParse();
        List<Issue> issues = service.getIssues("gentlero","bitbucket-api").getBody().getValues();
        Issue issue = issues.get(0);
        System.out.println(test.toissueParse(issue,comments,"gentlero","bitbucket-api").getAssignee());

    }
}