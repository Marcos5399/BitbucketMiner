package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.response.projectResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class projectServiceTest {

    @Autowired
    private projectService projectService;

    @Test
    @DisplayName("Get all projects")
    void getProjects() {
        ResponseEntity<projectResponse> response = projectService.getProjects("gentlero");
        projectResponse projects = response.getBody();
        assertNotNull(projects);
        System.out.println(projects.getValues());
    }

    @Test
    @DisplayName("Get a project")
    void getProject() {


        ResponseEntity<Project> response = projectService.getProject("gentlero", "OSS");
        Project project = response.getBody();
        assertNotNull(project);
        System.out.println(project);
    }
}