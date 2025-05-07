package integrationProjectBM.BitbucketMiner.util;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.commit.Author;
import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Assignee;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.model.user.User;
import integrationProjectBM.BitbucketMiner.modelsBitbucketMiner.*;
import integrationProjectBM.BitbucketMiner.service.CommentService;

import java.util.ArrayList;
import java.util.List;

public class Formatters {
    public static CommentBitbucketMiner commentFormatter(Comment comment) {

        String commentId = comment.getId().toString();
        UserBitbucketMiner author = userToUserFormatter(comment.getUser()) != null ? userToUserFormatter(comment.getUser()) : new UserBitbucketMiner();
        String body = comment.getContent().getRaw();
        if (body == null) {
            body = "No content";
        }
        String createdAt = comment.getCreatedOn();
        String updatedAt = "";
        if (comment.getUpdatedOn() != null) {
            updatedAt = comment.getUpdatedOn().toString();
        }
        return new CommentBitbucketMiner( commentId, body, createdAt, updatedAt, author);
    }


    public static CommitBitbucketMiner commitFormatter(Commit commit) {
        String commitId = commit.getHash();
        String title = commit.getSummary().getRaw();
        String message = commit.getMessage();
        Author author = commit.getAuthor();
        User user = (author != null) ? author.getUser() : null;
        String authorName = ((user != null) && (user.getDisplayName() != null)) ? user.getDisplayName() : "unknown";
        // El email del autor del Commit se encuentra en el campo "raw" del Author, pero hay que parsear
        //"raw": "John Doe <john@example.com>",
        String email;
        if (author.getRaw() != null) {
            Integer start = author.getRaw().indexOf("<");
            Integer end = author.getRaw().indexOf(">");
            email = author.getRaw().substring(start + 1, end).trim();
        } else {
            email = "unknown@mail";
        }
        String authoredDate = commit.getDate();
        String webUrl = commit.getLinks().getHtml().getHref();
        return new CommitBitbucketMiner( commitId, title, message, authorName, email, authoredDate, webUrl);
    }

    public static IssueBitbucketMiner issueFormatter (Issue issue, CommentService commentService, String workspace, String repoSlug, Integer maxPages) {

        List<CommentBitbucketMiner> comments = commentService.getIssueCommentPaginated(workspace,repoSlug,issue.getId().toString(), maxPages).stream().map(c-> commentFormatter(c)).toList();
        List<String> aux = new ArrayList<>();
        aux.add(issue.getKind());
        aux.add(issue.getPriority());
        String isueId = issue.getId().toString();
        String title = issue.getTitle();
        String description = issue.getContent().getRaw();
        String state = issue.getState();
        String createAt = issue.getCreatedOn();
        String updatedAt = issue.getUpdatedOn();
        String closedAt = issue.getState().equals("resolved") ? issue.getUpdatedOn() : "";
        List<String> labels = aux;
        UserBitbucketMiner author = reporterToUserFormatter(issue.getReporter()) != null ? reporterToUserFormatter(issue.getReporter()) : new UserBitbucketMiner();//BORRAR DESPUES: no tenemos clase user en issue, tenemos clase reporter y assignee que tienen las mismas propiedades.
        if (issue.getAssignee() != null) {
            assigneeToUserFormatter(issue.getAssignee());
        }else {
            new UserBitbucketMiner();
        }

        Integer votes = issue.getVotes();
        return new IssueBitbucketMiner( isueId, title, description, state, createAt, updatedAt, closedAt, labels, votes, author, assigneeToUserFormatter(issue.getAssignee()), comments);
    }

    public static UserBitbucketMiner userToUserFormatter(User user){
        if(user == null) {
            return null;
        }
        String uuid = user.getUuid(); // Ej: "{f7a0b1c2-d3e4-5678-90a1-b2c3d4e5f6g7}"

        if (uuid != null && uuid.startsWith("{") && uuid.endsWith("}") && uuid.length() > 2) {
            uuid = uuid.substring(1, uuid.length() - 1); // Quitamos los { y } que rodean el uuid
        }
        String userName = user.getNickname();
        String name = user.getDisplayName();
        String avatarUrl = user.getLinks().getAvatar().getHref();
        String webUrl = user.getLinks().getHtml().getHref();
        return new UserBitbucketMiner( uuid, userName, name, avatarUrl, webUrl);
        }


    public static UserBitbucketMiner reporterToUserFormatter(Reporter reporter){
        if(reporter == null) {
            return null;
        }
        String uuid = reporter.getUuid(); // Ej: "{f7a0b1c2-d3e4-5678-90a1-b2c3d4e5f6g7}"

        if (uuid != null && uuid.startsWith("{") && uuid.endsWith("}") && uuid.length() > 2) {
            uuid = uuid.substring(1, uuid.length() - 1);
        }
        String userName = reporter.getNickname();
        String name = reporter.getDisplayName();
        String avatarUrl = reporter.getLinks().getAvatar().getHref();
        String webUrl = reporter.getLinks().getHtml().getHref();
        return new UserBitbucketMiner( uuid, userName, name, avatarUrl, webUrl);
    }

    public static UserBitbucketMiner assigneeToUserFormatter(Assignee assignee){
        if(assignee == null) {
            return null;
        }

        String uuid = assignee.getUuid(); // Ej: "{f7a0b1c2-d3e4-5678-90a1-b2c3d4e5f6g7}"
        if (uuid != null && uuid.startsWith("{") && uuid.endsWith("}") && uuid.length() > 2) {
            uuid = uuid.substring(1, uuid.length() - 1);
        }
        String username = assignee.getNickname();
        String name = assignee.getDisplayName();
        String avatarURL = assignee.getLinks().getAvatar().getHref();
        String webURL = assignee.getLinks().getHtml().getHref();
        return new UserBitbucketMiner(uuid, username, name, avatarURL, webURL);
    }

    public static ProjectBitbucketMiner projectFormatter(Project project, List<CommitBitbucketMiner> commits, List<IssueBitbucketMiner> issues) {
        String uuid = project.getUuid(); // Ej: "{f7a0b1c2-d3e4-5678-90a1-b2c3d4e5f6g7}"

        if (uuid != null && uuid.startsWith("{") && uuid.endsWith("}") && uuid.length() > 2) {
            uuid = uuid.substring(1, uuid.length() - 1);
        }
        String name = project.getName();
        String webUrl =project.getLinks().getHtml().getHref();
        return new ProjectBitbucketMiner(uuid, name, webUrl, commits, issues );
    }
}
