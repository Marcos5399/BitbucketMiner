package integrationProjectBM.BitbucketMiner.response;

import integrationProjectBM.BitbucketMiner.model.project.Project;

import java.util.List;

public class projectResponse {
    private int pagelen;
    private int page;
    private int size;
    private List<Project> values;

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

    public List<Project> getValues() {
        return values;
    }

    public void setValues(List<Project> values) {
        this.values = values;
    }
}
