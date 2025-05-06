package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.service.CommitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class commitBitbucketMinerTest {

    @Autowired
    CommitService service;

    @Test
    @DisplayName("Commit parse")
    void toCommitParse() {
        CommitBitbucketMiner test = new CommitBitbucketMiner();
        List<Commit> commits = service.getAllCommits("gentlero","bitbucket-api").getBody().getValues();
        System.out.println(commits.stream().map(c->test.toCommitParse(c)).toList());
    }
}