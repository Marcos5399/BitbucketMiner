package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.service.commentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class commentParseTest {

    @Autowired
    commentService service;



    @Test
    @DisplayName("comment Parse")
    void toCommentParse() {

        commentParse test = new commentParse();
        List<Comment> comments = service.getcommentsI("gentlero", "bitbucket-api","87").getBody().getValues();
        System.out.println(comments.stream().map(c-> test.toCommentParse(c)).toList());

    }
}