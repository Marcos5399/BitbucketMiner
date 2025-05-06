package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.response.commitResponse;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class commitServiceTest {

    @Autowired
    private commitService service;
    @Test
    @DisplayName("Get all commits")
    void getAllCommits() {
        ResponseEntity<commitResponse> response = service.getAllCommits("gentlero", "bitbucket-api");
        commitResponse commits = response.getBody();
        assertNotNull(commits);
        System.out.println(commits.getValues());
    }

    @Test
    @DisplayName("Get one commit")
    void getCommit() {

        ResponseEntity<Commit> response = service.getCommit("gentlero", "bitbucket-api","67a0362b29f34c45251ce88c5851756fb30a65cc");
        Commit commit = response.getBody();
        assertNotNull(commit);
        System.out.println(commit);
    }

    @Test
    @DisplayName("Get all commits maxPages")
    void getAllCommitsPages() {

        List<Commit> commits = service.getAllCommitsPages("gentlero", "bitbucket-api",2);
        assertNotNull(commits);
        System.out.println(commits);

    }
}