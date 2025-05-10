package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.response.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${bitbucket.baseuri}")
    private String baseUri;

    @Value("${bitbucket.token}")
    private String token;
    @Value("${bitbucket.username}")
    private String username;

    public List<String> getIssuesUser(String workspace, String repoSlug){

        //cuando quito el id no me sale ningun issue

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
        ResponseEntity<PaginatedResponse<Issue>> response = restTemplate.exchange(uri, HttpMethod.GET,request, new ParameterizedTypeReference<PaginatedResponse<Issue>>() {});
        List<Issue> commits = response.getBody().getValues();
        List<Reporter> users = commits.stream().flatMap(x-> Stream.of(x.getReporter())).toList();
        List<String> res = users.stream().flatMap(x-> Stream.of(x.toString())).toList();

        return res;

    }
}
