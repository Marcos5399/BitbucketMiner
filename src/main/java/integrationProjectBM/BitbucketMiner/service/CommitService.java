package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.model.project.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class CommitService {
    @Autowired
    RestTemplate restTemplate;
    public Project getCommit(@RequestParam String workspace, @RequestParam String repo_slug, @RequestParam String commit) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+ workspace + repo_slug  + "/commit/" + commit;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer <KEY>");
        ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.GET,null, Project.class);
        return response.getBody();
    }
}
