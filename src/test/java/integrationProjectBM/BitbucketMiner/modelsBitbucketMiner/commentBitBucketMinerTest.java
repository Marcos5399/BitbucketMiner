package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class commentBitBucketMinerTest {

    @Autowired
    CommentService service;



    @Test
    @DisplayName("comment Parse")
    void toCommentParse() {

        CommentBitbucketMiner test = new CommentBitbucketMiner();
        List<Comment> comments = service.getIssueComment("gentlero", "bitbucket-api","87").getBody().getValues();
        System.out.println(comments.stream().map(c-> test.toCommentParse(c)).toList());

    }
}