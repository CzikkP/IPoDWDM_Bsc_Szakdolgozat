package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "latitude", "longitude", "primer", "isCore", "isOverlaySite", "isOla", "isUnderlay_2", "isUnderlay_3", "isOverlaySync", "bitrate", "pZone"})
public class Site {
    private String id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer primer;
    private Integer isCore;
    private Integer isOverlaySite;
    private Integer isOla;
    private Integer isUnderlay_2;
    private Integer isUnderlay_3;
    private Integer isOverlaySync;
    private Integer bitrate;
    private Integer pZone;
}
