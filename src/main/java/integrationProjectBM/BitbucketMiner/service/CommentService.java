package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.username}")
    private String username;

    @Value("${bitbucket.token}")
    private String token;


    public Comment getComment(String workspace, String repo_slug, String issue_id, String comment_id) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace +"/"+ repo_slug  + "/issues/" + issue_id + "/comments/"+ comment_id;

        String auth = username + ":" + token;
        String encodeAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuthorization;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<Comment> response = restTemplate.exchange(uri, HttpMethod.GET,request, Comment.class);
        return response.getBody();

    }

    public List<Comment> getAllComments(String workspace, String repo_slug, String issue_id) {

        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug + "/issues/" + issue_id + "/comments";

        String auth = username + ":" + token;
        String encodedAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Comment[]> request = new HttpEntity<>(headers);
        Comment[] response = restTemplate.exchange(uri, HttpMethod.GET, request, Comment[].class).getBody();

        return Arrays.stream(response).toList() ;
    }



}
