
package integrationProjectBM.BitbucketMiner.model.issue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Links__5 {

    @JsonProperty("self")
    private Self__5 self;

    @JsonProperty("self")
    public Self__5 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__5 self) {
        this.self = self;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__5 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
