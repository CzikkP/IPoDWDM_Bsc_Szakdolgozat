package hu.czikk.output.data.site;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SiteGeometry {
    @JsonProperty("coordinates")
    private List<Double> coordinates;

    @JsonProperty("type")
    private String type;

    public SiteGeometry(List<Double> coordinates, String type) {
        this.coordinates = coordinates;
        this.type = type;
    }
}
