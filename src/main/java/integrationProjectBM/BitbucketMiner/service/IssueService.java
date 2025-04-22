package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.comment.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class IssueService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${bitbucket.username}")
    private String username;

    @Value("${bitbucket.token}")
    private String token;

    public Issue getIssue(@RequestParam String workspace, @RequestParam String repo_slug, @RequestParam String issue_id) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+ workspace +"/" + repo_slug  + "/issues/" + issue_id;
        String auth = username + ":" + token;
        String encodeAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<Issue> response = restTemplate.exchange(uri, HttpMethod.GET,request, Issue.class);
        return response.getBody();
    }
    public List<Issue> getAllIssues(String workspace, String repo_slug) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+workspace+"/"+repo_slug+"/issues";

        String auth = username + ":" + token;
        String encodedAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Issue[]> request = new HttpEntity<>(headers);
        Issue[] response = restTemplate.exchange(uri, HttpMethod.GET, request, Issue[].class).getBody();

        return Arrays.stream(response).toList();
    }
}
