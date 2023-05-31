package hu.czikk.output.data.site;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SiteFeatures {
    @JsonProperty("geometry")
    private SiteGeometry geometry;

    @JsonProperty("properties")
    private SiteProperties properties;

    @JsonProperty("type")
    private String type;

    public SiteFeatures(SiteGeometry geometry, SiteProperties properties, String type) {
        this.geometry = geometry;
        this.properties = properties;
        this.type = type;
    }
}
