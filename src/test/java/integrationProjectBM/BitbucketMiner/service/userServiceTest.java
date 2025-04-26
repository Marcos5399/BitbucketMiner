package integrationProjectBM.BitbucketMiner.service;

import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userServiceTest {
    @Autowired
    private userService service;

    @Test
    @DisplayName("Get all users")
    void getAllUser() {
        List<String> users = service.getAllUserI("gentlero", "bitbucket-api");
        assertNotNull(users);
        System.out.println(users);
    }

}