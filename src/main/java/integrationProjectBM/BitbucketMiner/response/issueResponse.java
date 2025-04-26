package integrationProjectBM.BitbucketMiner.response;

import integrationProjectBM.BitbucketMiner.model.issue.Issue;

import java.util.List;

public class issueResponse {

    private int pagelen;
    private int page;
    private int size;
    private List<Issue> values;

    // Getters y Setters

    public int getPagelen() {
        return pagelen;
    }

    public void setPagelen(int pagelen) {
        this.pagelen = pagelen;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Issue> getValues() {
        return values;
    }

    public void setValues(List<Issue> values) {
        this.values = values;

    }


}

