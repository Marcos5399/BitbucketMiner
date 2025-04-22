package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.commit.Commit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${bitbucket.username}")
    private String username;

    @Value("${bitbucket.token}")
    private String token;

    public Commit getCommit(@RequestParam String workspace, @RequestParam String repo_slug, @RequestParam String commit) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+ workspace + "/" +repo_slug  + "/commit/" + commit;
        String auth = username + ":" + token;
        String encodeAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Commit> request = new HttpEntity<>(null, headers);
        ResponseEntity<Commit> response = restTemplate.exchange(uri, HttpMethod.GET,request, Commit.class);
        return response.getBody();
    }
    public List<Commit> getAllCommits(String workspace, String repo_slug) {
        String uri = "https://api.bitbucket.org/2.0/repositories/" + workspace + "/" + repo_slug + "/commits";

        String auth = username + ":" + token;
        String encodedAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Commit[]> request = new HttpEntity<>(headers);
        Commit[] response = restTemplate.exchange(uri, HttpMethod.GET, request, Commit[].class).getBody();

        return Arrays.stream(response).toList();
    }

}
