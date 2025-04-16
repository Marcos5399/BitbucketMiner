package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.model.project.Workspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired

    public Project getProject(@RequestParam String workspace, @RequestParam String key) {
        String uri = "https://api.bitbucket.org/2.0/workspaces/" + workspace + "/projects/" + key;
        ResponseEntity <Project> response = restTemplate.exchange(uri, HttpMethod.GET,null, Project.class);
        return response.getBody();
    }


}
