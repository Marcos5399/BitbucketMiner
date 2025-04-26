package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.response.commentResponse;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class commentServiceTest {

    @Autowired
    commentService service;



    @Test
    @DisplayName("Get Comments from a pull request")
    void getcommentsPR() {

        ResponseEntity<commentResponse> response = service.getcommentsPR("gentlero", "bitbucket-api","82");
        commentResponse comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments.getValues());
    }

    @Test
    @DisplayName("Get Comments from a issue")
    void getcommentsI() {
        ResponseEntity<commentResponse> response = service.getcommentsI("gentlero", "bitbucket-api","87");
        commentResponse comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments.getValues());


    }

    @Test
    @DisplayName("Get Comments from a Commit")
    void getcommentsC() {

        ResponseEntity<commentResponse> response = service.getcommentsC("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467");
        commentResponse comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments.getValues());

    }

    @Test
    @DisplayName("Get all commits")
    void getallcommits() {
        ResponseEntity<commentResponse> responseC = service.getcommentsC("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467");
        ResponseEntity<commentResponse> responseI = service.getcommentsI("gentlero", "bitbucket-api","87");
        ResponseEntity<commentResponse> responsePR = service.getcommentsPR("gentlero", "bitbucket-api","82");

        commentResponse commentsC = responseC.getBody();
        commentResponse commentsI = responseI.getBody();
        commentResponse commentsPR = responsePR.getBody();


        commentsC.getValues().addAll(commentsPR.getValues());
        commentsC.getValues().addAll(commentsI.getValues());
        System.out.println(commentsC.getValues());

    }

    @Test
    @DisplayName("Get a comment from an issue")
    void getCommentI() {

        ResponseEntity<Comment> response = service.getCommentI("gentlero", "bitbucket-api","87","57887979");
        Comment comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments);
    }

    @Test
    @DisplayName("Get a comment from a commit")
    void getCommentC() {

        ResponseEntity<Comment> response = service.getcommentC("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467","");
        Comment comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments);
    }
}