package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.service.commitService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class commitParseTest {

    @Autowired
    commitService service;

    @Test
    @DisplayName("Commit parse")
    void toCommitParse() {
        commitParse test = new commitParse();
        List<Commit> commits = service.getAllCommits("gentlero","bitbucket-api").getBody().getValues();
        Commit commit = commits.get(0);
        System.out.println(test.toCommitParse(commit).getAuthorName());
    }
}