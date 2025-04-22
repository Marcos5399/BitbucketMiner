package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.project.Project;

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
public class ProjectService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${bitbucket.username}")
    private String username;

    @Value("${bitbucket.token}")
    private String token;

    public Project getProject(@RequestParam String workspace, @RequestParam String key) {
        String uri = "https://api.bitbucket.org/2.0/workspaces/" + workspace + "/projects/" + key;

        String auth = username + ":" + token;
        String encodeAuhthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuhthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Project> request = new HttpEntity<>(null, headers);
        ResponseEntity <Project> response = restTemplate.exchange(uri, HttpMethod.GET,request, Project.class);
        return response.getBody();
    }

    public List<Project> getAllProjects(String workspace) {
        String uri = "https://api.bitbucket.org/2.0/repositories/"+workspace+"/projects";

        String auth = username + ":" + token;
        String encodedAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuthorization;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Project[]> request = new HttpEntity<>(headers);
        Project[] response = restTemplate.exchange(uri, HttpMethod.GET, request, Project[].class).getBody();

        return Arrays.stream(response).toList();
    }

    public Project postProject( String workspace, Project project) {
        String uri = "https://api.bitbucket.org/2.0/workspaces/" + workspace + "/projects";

        String auth = username + ":" + token;
        String encodeAuthorization = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodeAuthorization;


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Project> request = new HttpEntity<>(project, headers);
        ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.POST,request, Project.class);
        return response.getBody();

    }


}
