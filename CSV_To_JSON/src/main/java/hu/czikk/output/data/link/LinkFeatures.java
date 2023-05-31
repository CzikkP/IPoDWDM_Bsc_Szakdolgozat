package hu.czikk.output.data.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LinkFeatures {
    @JsonProperty("geometry")
    private LinkGeometry geometry;

    @JsonProperty("properties")
    private LinkProperties properties;

    @JsonProperty("type")
    private String type;

    public LinkFeatures(LinkGeometry geometry, LinkProperties properties, String type) {
        this.geometry = geometry;
        this.properties = properties;
        this.type = type;
    }

}
