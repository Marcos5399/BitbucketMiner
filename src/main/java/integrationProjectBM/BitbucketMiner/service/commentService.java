package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.commentResponse;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class commentService {
    @Autowired
    RestTemplate restTemplate;

    @Value("https://api.bitbucket.org/2.0/repositories/")
    private String apipath;

    @Value("ATBBUCLz82bW4fub2CuKLk4Dd76L398E9DF4")
    private String token;
    @Value("lorenvalderramaroman")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<commentResponse> getcommentsPR (String workspace, String repo_slug, String pull_request_id){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/pullrequests/"+pull_request_id+"/comments";

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<commentResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, commentResponse.class);
        return response;

    }

    public ResponseEntity<commentResponse> getcommentsI (String workspace, String repo_slug, String issue_id){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/issues/"+issue_id+"/comments";

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<commentResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, commentResponse.class);
        return response;

    }

    public ResponseEntity<commentResponse> getcommentsC (String workspace, String repo_slug, String commit_hash){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/commit/"+commit_hash+"/comments";

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Comment> request = new HttpEntity<>(null, headers);
        ResponseEntity<commentResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, commentResponse.class);
        return response;

    }

    public ResponseEntity<Comment> getCommentI (String workspace, String repo_slug, String issue_id,String comment_id){


        String uri = apipath + workspace +"/" + repo_slug + "/issues/"+issue_id+"/comments/" + comment_id;

        // a partir de aqui es todo el lio con el token



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


    public ResponseEntity<Comment> getcommentC (String workspace, String repo_slug, String commit_hash, String comment_id){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/commit/"+commit_hash+"/comments/" + comment_id;

        // a partir de aqui es todo el lio con el token



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

}
