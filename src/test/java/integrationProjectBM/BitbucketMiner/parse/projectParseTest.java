package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.service.projectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class projectParseTest {

    @Autowired
    projectService service;

    @Test
    @DisplayName("Project parse ")
    void toProjectParse() {

        projectParse test = new projectParse();
        Project project = service.getProject("gentlero","bitbucket-api").getBody();
        System.out.println(project);
    }
}