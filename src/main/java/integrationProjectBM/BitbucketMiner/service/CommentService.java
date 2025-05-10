package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.response.BitbucketCommentResponse;
import integrationProjectBM.BitbucketMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseuri}")
    private String baseUri;

    @Value("${bitbucket.token}")
    private String token;
    @Value("${bitbucket.username}")
    private String username;

    // GET /repositories/{workspace}/{repo_slug}/issues/{issue_id}/comments
    public ResponseEntity<BitbucketCommentResponse> getIssueComments(String workspace, String repoSlug, String issueId){

        //cuando quito el id no me sale ningun issue

        String uri = baseUri + workspace +"/" + repoSlug + "/issues/"+issueId+"/comments";
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<BitbucketCommentResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, BitbucketCommentResponse.class);
        return response;

    }

    // GET /repositories/{workspace}/{repo_slug}/commit/{commit_hash}/comments
    public ResponseEntity<BitbucketCommentResponse> getCommitComments(String workspace, String repoSlug, String commitHash){

        String uri = baseUri + workspace +"/" + repoSlug + "/commit/"+commitHash+"/comments";
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<BitbucketCommentResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, BitbucketCommentResponse.class);
        return response;

    }
    // GET /repositories/{workspace}/{repo_slug}/issues/{issue_id}/comments/{comment_id}
    public ResponseEntity<Comment> getIssueCommentById(String workspace, String repoSlug, String issueId, String commentId){

        String uri = baseUri + workspace +"/" + repoSlug + "/issues/"+issueId+"/comments/" + commentId;
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.GET,request, Comment.class);
        return response;
    }

    // GET /repositories/{workspace}/{repo_slug}/commit/{commit_hash}/comments/{comment_id}
    public ResponseEntity<Comment> getCommitCommentById(String workspace, String repoSlug, String commitHash, String commentId){

        String uri = baseUri + workspace +"/" + repoSlug + "/commit/"+commitHash+"/comments/" + commentId;
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.GET,request, Comment.class);
        return response;
    }

    // GET /repositories/{workspace}/{repo_slug}/issues/{issue_id}/comments?page={page}&pagelen={pagelen}
    public List<Comment> getIssueCommentsPaginated(String workspace, String repoSlug, String issueId, Integer maxPages) {
        String initialUri = baseUri + workspace + "/" + repoSlug + "/issues/" + issueId + "/comments";
        List<Comment> comments = Util.getPaginatedBitbucketResources(
                initialUri,
                maxPages,
                BitbucketCommentResponse.class,
                restTemplate
        );
        return comments;
    }
}
