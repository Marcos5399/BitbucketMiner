package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.user.User;
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
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${bitbucket.username}")
    private String username;

    @Value("${bitbucket.token}")
    private String token;

    public User getUser(@RequestParam String id) {
       String uri = "https://api.bitbucket.org/2.0/users/" + id;

        String auth = username + ":" + token;
        String encodeAuhthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuhthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> request = new HttpEntity<>(null, headers);
       ResponseEntity<User> response =restTemplate.exchange(uri, HttpMethod.GET,request, User.class);
       return response.getBody();

    }

    public List<User> getAllUser(String workspace) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+workspace+"/members";

        String auth = username + ":" + token;
        String encodedAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<User[]> request = new HttpEntity<>(headers);
        User[] response = restTemplate.exchange(uri, HttpMethod.GET, request, User[].class).getBody();

        return Arrays.stream(response).toList();
    }
}
