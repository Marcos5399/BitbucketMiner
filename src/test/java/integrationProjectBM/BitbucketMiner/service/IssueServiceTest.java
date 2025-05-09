package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {
    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Get all issues")
    void getIssues() {
        ResponseEntity<PaginatedResponse<Issue>> responseEntity = issueService.getIssues("gentlero","bitbucket-api");
        List<Issue> issues = responseEntity.getBody().getValues();
        assertNotNull(issues, "Issue list should not be null");
        assertFalse(issues.isEmpty(), "Issue list should not be empty");
        System.out.println("Total issues: " + issues.size());
        for (Issue issue : issues) {
            System.out.println(issue);
        }
    }

    @Test
    @DisplayName("Get issue by ID")
    void getIssue() {
        ResponseEntity<Issue> response = issueService.getIssue("gentlero", "bitbucket-api", "42");
        Issue issue = response.getBody();
        assertNotNull(issue, "Issue should not be null");
        System.out.println(issue);
    }

    @Test
    @DisplayName("Get all issues paginated")
    void getIssuesPages() {
        List<Issue> issues = issueService.getIssuesPages("gentlero", "bitbucket-api", 2);
        assertNotNull( issues, "Issue list should not be null");
        assertFalse(issues.isEmpty(), "Issue list should not be empty");
        System.out.println("Total issues: " + issues.size());
        for (Issue issue : issues) {
            System.out.println(issue);
        }
    }
}