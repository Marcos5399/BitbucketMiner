package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class commentServiceTest {

    @Autowired
    CommentService service;





    @Test
    @DisplayName("Get Comments from a issue")
    void getcommentsI() {
        ResponseEntity<CommentResponse> response = service.getIssueComment("gentlero", "bitbucket-api","87");
        CommentResponse comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments.getValues());


    }

    @Test
    @DisplayName("Get Comments from a Commit")
    void getcommentsC() {

        ResponseEntity<CommentResponse> response = service.getCommitComments("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467");
        CommentResponse comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments.getValues());

    }

    @Test
    @DisplayName("Get all commits")
    void getallcommits() {
        ResponseEntity<CommentResponse> responseC = service.getCommitComments("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467");
        ResponseEntity<CommentResponse> responseI = service.getIssueComment("gentlero", "bitbucket-api","87");


        CommentResponse commentsC = responseC.getBody();
        CommentResponse commentsI = responseI.getBody();



        commentsC.getValues().addAll(commentsI.getValues());
        System.out.println(commentsC.getValues());

    }

    @Test
    @DisplayName("Get a comment from an issue")
    void getCommentI() {

        ResponseEntity<Comment> response = service.getIssueCommentById("gentlero", "bitbucket-api","87","57887979");
        Comment comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments);
    }

    @Test
    @DisplayName("Get a comment from a commit")
    void getCommentC() {

        ResponseEntity<Comment> response = service.getCommitCommentById("gentlero", "bitbucket-api","d0b0fcf55f7099d3a43c29dab76631c66d1e5467","");
        Comment comments = response.getBody();
        assertNotNull(comments);
        System.out.println(comments);
    }
}