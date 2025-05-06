package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class issueServiceTest {

    @Autowired
    private issueService service;


    @Test
    @DisplayName("Get all issues")
    void getIssues() {

    ResponseEntity<issueResponse> response = service.getIssues("gentlero", "bitbucket-api");
    issueResponse issues = response.getBody();
    assertNotNull(issues);
    System.out.println(issues.getValues());

    }


    @Test
    @DisplayName("Get an issue")
    void getIssue() {

        ResponseEntity<Issue> response = service.getIssue("gentlero", "bitbucket-api", "87");
        Issue issues = response.getBody();
        assertNotNull(issues);
        System.out.println(issues);
    }


    @Test
    @DisplayName ("Get issues maxPages")
    void getIssuesPages() {

        List<Issue> response = service.getIssuesPages("gentlero", "bitbucket-api", 2);
        assertNotNull(response);
        System.out.println(response);
    }
}