package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;

    @Test
    @DisplayName("Get comments for Issue")
    void getIssueComment() {
        ResponseEntity<PaginatedResponse<Comment>> response = commentService.getIssueComment("gentlero", "bitbucket-api", "42");
        List<Comment> comments = response.getBody().getValues();
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    @DisplayName("Get comments for Commit")
    void getCommitComments() {
        ResponseEntity<PaginatedResponse<Comment>> response = commentService.getCommitComments("gentlero", "bitbucket-api", "3945f4821b8ed40978870ff848f676aabe830f18");
        List<Comment> comments = response.getBody().getValues();
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }

    @Test
    void getIssueCommentById() {
        ResponseEntity<Comment> response = commentService.getIssueCommentById("gentlero", "bitbucket-api", "1", "5835489");
        Comment comment = response.getBody();
        assertNotNull(comment, "Comment should not be null");
        System.out.println(comment);
    }

   /* @Test
    void getCommitCommentById() {
        ResponseEntity<Comment> response = commentService.getCommitCommentById("gentlero", "bitbucket-api", "3945f4821b8ed40978870ff848f676aabe830f18", "5835489");
        Comment comment = response.getBody();
        assertNotNull(comment, "Comment should not be null");
        System.out.println(comment);
    }
    // No encuentro ningún commit que tenga comentarios
    */

    @Test
    void getIssueCommentPaginated() {
        List<Comment> comments = commentService.getIssueCommentPaginated("gentlero", "bitbucket-api", "85", 2);
        assertFalse(comments.isEmpty(), "Comment list should not be empty");
        System.out.println(comments.size());
        for (Comment comment : comments) {
            System.out.println(comment);
        }
    }
}