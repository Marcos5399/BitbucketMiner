package integrationProjectBM.BitbucketMiner.util;

import integrationProjectBM.BitbucketMiner.model.comment.Comment;
import integrationProjectBM.BitbucketMiner.model.commit.Author;
import integrationProjectBM.BitbucketMiner.model.commit.Commit;
import integrationProjectBM.BitbucketMiner.model.issue.Assignee;
import integrationProjectBM.BitbucketMiner.model.issue.Issue;
import integrationProjectBM.BitbucketMiner.model.issue.Reporter;
import integrationProjectBM.BitbucketMiner.model.project.Project;
import integrationProjectBM.BitbucketMiner.model.user.User;
import integrationProjectBM.BitbucketMiner.parse.*;
import integrationProjectBM.BitbucketMiner.service.CommentService;

import java.util.ArrayList;
import java.util.List;

public class Formatters {
    public static CommentBitbucketMiner commentFormatter(Comment comment) {
        CommentBitbucketMiner cP = new CommentBitbucketMiner();

        cP.setId(comment.getId().toString());
        cP.setAuthor(userToUserFormatter(comment.getUser()) != null ? userToUserFormatter(comment.getUser()) : new UserBitbucketMiner());
        cP.setBody(comment.getContent().getRaw());
        cP.setCreatedAt(comment.getCreatedOn());
        if (comment.getUpdatedOn() != null) {
            cP.setUpdatedAt(comment.getUpdatedOn().toString());
        } else {
            cP.setUpdatedAt("");
        }

        return cP;
    }


    public static CommitBitbucketMiner commitFormatter(Commit commit) {
        CommitBitbucketMiner cp = new CommitBitbucketMiner();
        cp.setId(commit.getHash());
        cp.setTitle(commit.getSummary().getRaw());
        cp.setMessage(commit.getMessage());
        Author author = commit.getAuthor();
        User user = (author != null) ? author.getUser() : null;
        cp.setAuthorName(((user != null) && (user.getDisplayName() != null)) ? user.getDisplayName() : "unknown");
        cp.setAuthorEmail("unknown@example.com"); //No hay campo Email en la clase User de Bitbucket
        cp.setAuthoredDate(commit.getDate());
        cp.setWebUrl(commit.getLinks().getSelf().getHref());
        return cp;
    }

    public static IssueBitbucketMiner issueFormatter (Issue issue, CommentService commentService, String workspace, String repoSlug, Integer maxPages) {

        List<CommentBitbucketMiner> comments = commentService.getIssueCommentPaginated(workspace,repoSlug,issue.getId().toString(), maxPages).stream().map(c-> commentFormatter(c)).toList();
        IssueBitbucketMiner ip = new IssueBitbucketMiner();
        List<String> aux = new ArrayList<>();
        aux.add(issue.getKind());
        aux.add(issue.getPriority());
        ip.setId(issue.getId().toString());
        ip.setTitle(issue.getTitle());
        ip.setDescription(issue.getContent().getRaw());
        ip.setState(issue.getState());
        ip.setCreatedAt(issue.getCreatedOn());
        ip.setUpdatedAt(issue.getUpdatedOn());
        ip.setClosedAt(issue.getState().equals("resolved") ? issue.getUpdatedOn() : null);
        ip.setLabels(aux);
        ip.setAuthor(reporterToUserFormatter(issue.getReporter()) != null ? reporterToUserFormatter(issue.getReporter()) : new UserBitbucketMiner());//BORRAR DESPUES: no tenemos clase user en issue, tenemos clase reporter y assignee que tienen las mismas propiedades.
        if (issue.getAssignee() != null) {
            ip.setAssignee(assigneeToUserFormatter(issue.getAssignee()));
        }       else {
            ip.setAssignee(new UserBitbucketMiner());
        }

        ip.setVotes(issue.getVotes());
        ip.setWebUrl(issue.getLinks().getSelf().getHref());
        ip.setComments(comments);
        return ip;


    }

    public static UserBitbucketMiner userToUserFormatter(User user){
        if(user == null) {
            return null;
        }
        UserBitbucketMiner up = new UserBitbucketMiner();
        up.setId(user.getUuid());
        up.setUsername(user.getNickname());
        up.setName(user.getUsername());
        up.setAvatarUrl(user.getLinks().getAvatar().getHref());
        up.setWebUrl(user.getLinks().getSelf().getHref());
        return up;


    }

    public static UserBitbucketMiner reporterToUserFormatter(Reporter reporter){
        if(reporter == null) {
            return null;
        }
        UserBitbucketMiner up = new UserBitbucketMiner();
        up.setId(reporter.getUuid());
        up.setUsername(reporter.getNickname());
        up.setName(reporter.getDisplayName());
        up.setAvatarUrl(reporter.getLinks().getAvatar().getHref());
        up.setWebUrl(reporter.getLinks().getSelf().getHref());
        return up;
    }

    public static UserBitbucketMiner assigneeToUserFormatter(Assignee assignee){
        if(assignee == null) {
            return null;
        }
        UserBitbucketMiner up = new UserBitbucketMiner();
        up.setId(assignee.getUuid());
        up.setUsername(assignee.getNickname());
        up.setName(assignee.getDisplayName());
        up.setAvatarUrl(assignee.getLinks().getAvatar().getHref());
        up.setWebUrl(assignee.getLinks().getSelf().getHref());
        return up;
    }

    public static ProjectBitbucketMiner projectFormatter(Project project, List<CommitBitbucketMiner> commits, List<IssueBitbucketMiner> issues) { //BORRAR DESPUES: tenemos que meterle como parametros lo que obtenemos de getIssue/getCommits habiendole hecho el parse
        ProjectBitbucketMiner pp = new ProjectBitbucketMiner();
        pp.setId(project.getUuid());
        pp.setName(project.getName()); // BORRAR DESPUES, PUEDE SER NAME O FULLNAME
        pp.setWebUrl(project.getLinks().getSelf().getHref());
        pp.setCommits(commits);
        pp.setIssues(issues);
        return pp;
    }
}
