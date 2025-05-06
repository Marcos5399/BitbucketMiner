package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.response.commitResponse;
import integrationProjectBM.BitbucketMiner.response.issueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class commitService {

    @Autowired
    RestTemplate restTemplate;

    @Value("https://api.bitbucket.org/2.0/repositories/")
    private String apipath;

    @Value("ATBBUCLz82bW4fub2CuKLk4Dd76L398E9DF4")
    private String token;
    @Value("lorenvalderramaroman")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<commitResponse> getAllCommits (String workspace, String repo_slug){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/commits";

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
        ResponseEntity<commitResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, commitResponse.class);
        return response;

    }
    public ResponseEntity<Commit> getCommit (String workspace, String repo_slug, String commit_hash){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/" + repo_slug + "/commit/" + commit_hash;

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
        ResponseEntity<Commit> response = restTemplate.exchange(uri, HttpMethod.GET,request, Commit.class);
        return response;

    }


    public List<Commit> getAllCommitsPages (String workspace, String repo_slug, Integer maxPages){

        List<Commit> commits = new ArrayList<>();

        String uri = apipath + workspace +"/" + repo_slug + "/commits";





        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));



        String nextPageUrl = uri;
        int currentPage = 1;
        int pagesNumber = (maxPages != null) ? maxPages : 2;

        while (nextPageUrl != null && currentPage < pagesNumber) {
            HttpEntity<Issue> request = new HttpEntity<>(null, headers);
            ResponseEntity<commitResponse> response = restTemplate.exchange(uri, HttpMethod.GET, request, commitResponse.class);
            commits.addAll(response.getBody().getValues());
            currentPage++;
        }
        return commits;

    }


}
