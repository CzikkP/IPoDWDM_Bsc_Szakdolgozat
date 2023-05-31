package hu.czikk.output.data.link;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class LinkProperties {
    @JsonProperty("#UL_reservation")
    private Integer uLReservation;

    @JsonProperty("OL")
    private Boolean oL;

    @JsonProperty("UL_systems")
    private Map<String, List<Integer>> uLSystems;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("endpoint0")
    private String endpoint0;

    @JsonProperty("endpoint1")
    private String endpoint1;

    @JsonProperty("from_id")
    private Integer fromId;

    @JsonProperty("to_id")
    private Integer toId;

    @JsonProperty("label")
    private String label;

    @JsonProperty("name")
    private String name;

    @JsonProperty("length")
    private Double length;

    @JsonProperty("type")
    private Integer type;
}
