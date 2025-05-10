package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.service.CommitService;
import integrationProjectBM.BitbucketMiner.util.Formatters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommitBitbucketMinerTest {

    @Autowired
    CommitService service;

    @Test
    @DisplayName("Commit Formatter")
    void toCommitFormatter() {
        List<Commit> commits = service.getAllCommits("gentlero","bitbucket-api").getBody().getValues();
        System.out.println(commits.stream().map(c-> Formatters.commitFormatter(c)).toList());
    }
}