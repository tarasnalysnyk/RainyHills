package test.task.rest.resource.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@XmlRootElement(name = "RainyHillsResponse")
public class RainyHillsResponse {

    private BigInteger volume;

    private RainyHillsResponse(Builder builder) {
        this.volume = builder.volume;
    }

    public static Builder builder() {
        return new Builder();
    }

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public static final class Builder {

        private BigInteger volume;

        private Builder() {
        }

        public RainyHillsResponse build() {
            return new RainyHillsResponse(this);
        }

        public Builder volume(BigInteger volume) {
            this.volume = volume;
            return this;
        }
    }
}
