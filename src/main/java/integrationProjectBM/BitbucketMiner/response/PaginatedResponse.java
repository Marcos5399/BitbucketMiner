package integrationProjectBM.BitbucketMiner.response;

import java.util.List;

public class PaginatedResponse<T> {
    private int pagelen;
    private int page;
    private int size;
    private String next;
    private List<T> values;

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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}

