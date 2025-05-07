package integrationProjectBM.BitbucketMiner.modelsBitbucketMiner;

import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.service.ProjectService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class projectParseTest {

    @Autowired
    ProjectService service;

    @Test
    @DisplayName("Project parse ")
    void toProjectParse() {

        Project project = service.getProject("gentlero","bitbucket-api").getBody();
        System.out.println(project);
    }
}