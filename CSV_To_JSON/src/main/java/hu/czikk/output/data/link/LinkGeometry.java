package hu.czikk.output.data.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LinkGeometry {
    @JsonProperty("coordinates")
    private List<List<Double>> coordinates;

    @JsonProperty("type")
    private String type;

    public LinkGeometry(List<List<Double>> coordinates, String type) {
        this.coordinates = coordinates;
        this.type = type;
    }
}
