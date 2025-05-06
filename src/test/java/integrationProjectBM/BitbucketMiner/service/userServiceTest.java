package integrationProjectBM.BitbucketMiner.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userServiceTest {
    @Autowired
    private UserService service;

    @Test
    @DisplayName("Get all users")
    void getAllUser() {
        List<String> users = service.getIssuesUser("gentlero", "bitbucket-api");
        assertNotNull(users);
        System.out.println(users);
    }

}