package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.response.BitbucketCommitResponse;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {
    @Autowired
    CommitService commitService;

    @Test
    @DisplayName("Get all commits")
    void getAllCommits() {
        ResponseEntity<BitbucketCommitResponse> response = commitService.getAllCommits("gentlero", "bitbucket-api");
        List<Commit> commits = response.getBody().getValues();
        assertFalse(commits.isEmpty(), "Commit list should not be empty");
        System.out.println("Número de commits:" + commits.size());
        for (Commit commit : commits) {
            System.out.println(commit);
        }
    }

    @Test
    @DisplayName("Get commit by ID")
    void getCommit() {
        ResponseEntity<Commit> commit = commitService.getCommit("gentlero", "bitbucket-api", "67a0362b29f34c45251ce88c5851756fb30a65cc");
        assertNotNull(commit, "Commit should not be null");
        assertEquals("67a0362b29f34c45251ce88c5851756fb30a65cc", commit.getBody().getHash(), "Commit ID should match");
        System.out.println(commit.getBody());
    }

    @Test
    @DisplayName("Get all commits paginated")
    void getAllCommitsPages() {
        Integer nCommits = 7;
        Integer maxPages = 3;
        List<Commit> commits = commitService.getAllCommitsPages("gentlero", "bitbucket-api", nCommits, maxPages);
        assertFalse(commits.isEmpty(), "Commit list should not be empty");
        System.out.println("Número de commits:" + commits.size());
        for (Commit commit : commits) {
            System.out.println(commit);
        }
    }
}