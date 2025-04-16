package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;

    public User getUser(@RequestParam String id) {
       String uri = "https://api.bitbucket.org/2.0/users/" + id;
       ResponseEntity<User> response =restTemplate.exchange(uri, HttpMethod.GET,null, User.class);
       return response.getBody();

    }
}
