package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.project.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class projectServiceTest {

    @Autowired
    private ProjectService projectService;

    @Test
    @DisplayName("Get all projects")
    void getProjects() {
        ResponseEntity<Project> response = projectService.getProject("gentlero", "bitbucket-api");
        Project projects = response.getBody();
        assertNotNull(projects);
        System.out.println(projects);
    }


}