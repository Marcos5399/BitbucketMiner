package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.parse.issueParse;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class issueService {
    @Autowired
    RestTemplate restTemplate;

    @Value("https://api.bitbucket.org/2.0/repositories/")
    private String apipath;

    @Value("ATBBUCLz82bW4fub2CuKLk4Dd76L398E9DF4")
    private String token;
    @Value("lorenvalderramaroman")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<issueResponse> getIssues (String workspace, String repo_slug){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/issues";

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<issueResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, issueResponse.class);
        return response;

    }

    public ResponseEntity<Issue> getIssue (String workspace, String repo_slug, String issue_id){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/issues/" + issue_id;

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<Issue> response = restTemplate.exchange(uri, HttpMethod.GET,request, Issue.class);
        return response;

    }


    public List<Issue> getIssuesPages (String workspace, String repo_slug, Integer maxPages){


        List<Issue> issues = new ArrayList<>();
        String uri = apipath + workspace +"/" + repo_slug + "/issues";


        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));



        String nextPageUrl = uri;
        int currentPage = 1;
        int pagesNumber = (maxPages != null) ? maxPages : 2;

        while (nextPageUrl != null && currentPage < pagesNumber){

            HttpEntity<Issue> request = new HttpEntity<>(null, headers);
            ResponseEntity<issueResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, issueResponse.class);
            issues.addAll(response.getBody().getValues());

            currentPage++;
        }
        return issues;

    }







}
