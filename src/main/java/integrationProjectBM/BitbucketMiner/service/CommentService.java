package integrationProjectBM.BitbucketMiner.service;


import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    public List<Comment> findAllComments() {
        List<Comment> = null;
        String uri = ""
    }

}
