package integrationProjectBM.BitbucketMiner.parse;

import integrationProjectBM.BitbucketMiner.model.issue.Assignee;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.user.User;

public class userParse {

    private String id;
    private String username;
    private String name;
    private String avatarUrl;
    private String webUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public userParse userToUserParse(User user){
        userParse up = new userParse();
        up.setId(user.getUuid());
        up.setUsername(user.getNickname());
        up.setName(user.getUsername());
        up.setAvatarUrl(user.getLinks().getAvatar().getHref());
        up.setWebUrl(user.getLinks().getSelf().getHref());
        return up;


    }

    public userParse reporterToUserParse(Reporter reporter){
        userParse up = new userParse();
        up.setId(reporter.getUuid());
        up.setUsername(reporter.getNickname());
        up.setName(reporter.getDisplayName());
        up.setAvatarUrl(reporter.getLinks().getAvatar().getHref());
        up.setWebUrl(reporter.getLinks().getSelf().getHref());
        return up;
    }

    public userParse assigneeToUserParse(Assignee assignee){
        userParse up = new userParse();
        up.setId(assignee.getUuid());
        up.setUsername(assignee.getNickname());
        up.setName(assignee.getDisplayName());
        up.setAvatarUrl(assignee.getLinks().getAvatar().getHref());
        up.setWebUrl(assignee.getLinks().getSelf().getHref());
        return up;
    }
}
