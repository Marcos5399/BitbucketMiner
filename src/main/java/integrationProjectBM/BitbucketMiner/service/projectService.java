package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.project.Project;

import integrationProjectBM.BitbucketMiner.response.projectResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;

@Service
public class projectService {

   @Autowired
   RestTemplate restTemplate;

    @Value("https://api.bitbucket.org/2.0/workspaces/")
    private String apipath;

    @Value("ATBBUCLz82bW4fub2CuKLk4Dd76L398E9DF4")
    private String token;
    @Value("lorenvalderramaroman")
    private String username;


    //coger un issue sin token funciona
    public ResponseEntity<projectResponse> getProjects (String workspace){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/projects";

        // a partir de aqui es todo el lio con el token



        // Codificamos en base64 para Basic Auth
        String auth = username + ":" + token;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        String authHeader = "Basic " + encodedAuth;

        // Headers con autenticación
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));





        HttpEntity<Project> request = new HttpEntity<>(null, headers);
        ResponseEntity<projectResponse> response = restTemplate.exchange(uri, HttpMethod.GET,request, projectResponse.class);
        return response;

    }

    public ResponseEntity<Project> getProject (String workspace, String key){

        //cuando quito el id no me sale ningun issue

        String uri = apipath + workspace +"/projects/" + key;

        // a partir de aqui es todo el lio con el token



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


}
