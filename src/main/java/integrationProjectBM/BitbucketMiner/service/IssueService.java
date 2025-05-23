package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.BitbucketIssueResponse;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import integrationProjectBM.BitbucketMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseuri}")
    private String baseUri;

    @Value("${bitbucket.token}")
    private String token;
    @Value("${bitbucket.username}")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<BitbucketIssueResponse> getIssues (String workspace, String repoSlug){

        String uri = baseUri + workspace +"/" + repoSlug + "/issues";
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<BitbucketIssueResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, BitbucketIssueResponse.class);
        return response;
    }

    public ResponseEntity<Issue> getIssue (String workspace, String repoSlug, String issueId){

        String uri = baseUri + workspace +"/" + repoSlug + "/issues/" + issueId;
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


    public List<Issue> getIssuesPages(String workspace, String repoSlug, Integer nIssues, Integer maxPages) {
        String initialUri = baseUri + workspace + "/" + repoSlug + "/issues?pagelen=";
        if (nIssues != null) {
            initialUri += nIssues;
        } else {
            initialUri += 5; // Default value
        }
        List<Issue> issues = Util.getPaginatedBitbucketResources(
                initialUri,
                maxPages,
                BitbucketIssueResponse.class,
                restTemplate
        );
        return issues;
    }
}
