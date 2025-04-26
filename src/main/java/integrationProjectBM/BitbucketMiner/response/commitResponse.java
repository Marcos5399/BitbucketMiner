package integrationProjectBM.BitbucketMiner.response;

import integrationProjectBM.BitbucketMiner.model.commit.Commit;


import java.util.List;

public class commitResponse {

        private int pagelen;
        private int page;
        private int size;
        private List<Commit> values;

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

        public List<Commit> getValues() {
            return values;
        }

        public void setValues(List<Commit> values) {
            this.values = values;
        }
    }

