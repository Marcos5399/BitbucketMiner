package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.commit.Author;
import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.user.User;
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
import java.util.stream.Stream;

@Service
public class userService {
    @Autowired
    RestTemplate restTemplate;
    @Value("https://api.bitbucket.org/2.0/repositories/")
    private String apipath;

    @Value("ATBBUCLz82bW4fub2CuKLk4Dd76L398E9DF4")
    private String token;
    @Value("lorenvalderramaroman")
    private String username;

    public List<String> getAllUserI (String workspace, String repo_slug){

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
        List<Issue> commits = response.getBody().getValues();
        List<Reporter> users = commits.stream().flatMap(x-> Stream.of(x.getReporter())).toList();
        List<String> res = users.stream().flatMap(x-> Stream.of(x.toString())).toList();

        return res;

    }



}
