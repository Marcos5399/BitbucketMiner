package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.issue.Assignee;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.service.issueService;
import integrationProjectBM.BitbucketMiner.service.userService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userParseTest {

    @Autowired
    userService service;

    @Autowired
    issueService issueService;




    @Test
    @DisplayName("Reporter parse")
    void reporterToUserParse() {
        userParse user = new userParse();
        List<Issue> issues = issueService.getIssues("gentlero","bitbucket-api").getBody().getValues();
        Issue issue = issues.get(0);
        Reporter reporter = issue.getReporter();
        System.out.println(user.reporterToUserParse(reporter));
    }

    @Test
    void assigneeToUserParse() {

        userParse user = new userParse();
        List<Issue> issues = issueService.getIssues("gentlero","bitbucket-api").getBody().getValues();


        Issue issue = issues.get(17);
        Assignee assignee = issue.getAssignee();
        System.out.println(user.assigneeToUserParse(assignee));
    }
}