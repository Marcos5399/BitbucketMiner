package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.service.CommentService;
import integrationProjectBM.BitbucketMiner.util.Formatters;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommentBitBucketMinerTest {

    @Autowired
    CommentService service;

    @Test
    @DisplayName("Comment Formatter")
    void toCommentFormatter() {
        List<Comment> comments = service.getIssueComments("gentlero", "bitbucket-api","87").getBody().getValues();
        System.out.println(comments.stream().map(c-> Formatters.commentFormatter(c)).toList());
    }
}