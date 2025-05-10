package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.BitbucketCommitResponse;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import integrationProjectBM.BitbucketMiner.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.baseuri}")
    private String baseUri;

    @Value("${bitbucket.token}")
    private String token;
    @Value("${bitbucket.username}")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<BitbucketCommitResponse> getAllCommits (String workspace, String repoSlug){

        //cuando quito el id no me sale ningun issue

        String uri = baseUri + workspace +"/" + repoSlug + "/commits";
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<BitbucketCommitResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, BitbucketCommitResponse.class);
        return response;

    }
    public ResponseEntity<Commit> getCommit (String workspace, String repoSlug, String commitHash){

        //cuando quito el id no me sale ningun issue

        String uri = baseUri + workspace +"/" + repoSlug + "/commit/" + commitHash;
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Issue> request = new HttpEntity<>(null, headers);
        ResponseEntity<Commit> response = restTemplate.exchange(uri, HttpMethod.GET,request, Commit.class);
        return response;

    }


    public List<Commit> getAllCommitsPages (String workspace, String repoSlug, Integer nCommits, Integer maxPages){
        String initialUri = baseUri + workspace +"/" + repoSlug + "/commits?pagelen=";
        if (nCommits != null) {
            initialUri += nCommits;
        } else {
            initialUri += 5; // Default value
        }
        List<Commit> commits = Util.getPaginatedBitbucketResources(
               initialUri,
               maxPages,
               BitbucketCommitResponse.class,
               restTemplate
        );
        return commits;
    }

}
