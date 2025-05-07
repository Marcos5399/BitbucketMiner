package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.project.Project;

import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.ProjectBitbucketMiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;

@Service
public class ProjectService {

   @Autowired
   RestTemplate restTemplate;

    @Value("${bitbucket.baseuri}")
    private String baseUri;

    @Value("${bitbucket.token}")
    private String token;
    @Value("${bitbucket.username}")
    private String username;

    @Value("${gitminer.baseuri}")
    private String baseUriGitMiner;


    //coger un issue sin token funciona
    public ResponseEntity<Project> getProject (String workspace, String repoSlug){

        //cuando quito el id no me sale ningun issue

        String uri = baseUri + workspace + "/" + repoSlug ;
        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Project> request = new HttpEntity<>(null, headers);
        ResponseEntity<Project> response = restTemplate.exchange(uri, HttpMethod.GET,request, Project.class);
        return response;

    }

    public ResponseEntity<ProjectBitbucketMiner> sendProjectToGitMiner (ProjectBitbucketMiner project){
        String uri = baseUriGitMiner;
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<ProjectBitbucketMiner> request = new HttpEntity<>(project, headers);
        return restTemplate.exchange(uri, HttpMethod.POST,request, ProjectBitbucketMiner.class);
    }
}
