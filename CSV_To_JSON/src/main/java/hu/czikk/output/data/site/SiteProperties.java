package hu.czikk.output.data.site;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SiteProperties {
    @JsonProperty("UL_systems")
    private Map<String, List<Integer>> uLSystems;

    @JsonProperty("UL_system")
    private Integer uLSystem;

    @JsonProperty("UL_subsystem")
    private Integer uLSubsystem;

    @JsonProperty("UL_amplifiers")
    private List<Integer> uLAmplifiers;

    @JsonProperty("bp_node")
    private Boolean bpNode;

    @JsonProperty("existing_er")
    private Boolean existingEr;

    @JsonProperty("hop_number")
    private Integer hopNumber;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("ol_role")
    private Integer olRole;

    @JsonProperty("ul_role")
    private Integer ulRole;

    @JsonProperty("ol_traffic1")
    private List<String> olTraffic1;

    @JsonProperty("ol_traffic2")
    private List<String> olTraffic2;

    @JsonProperty("ul_traffic1")
    private List<String> ulTraffic1;

    @JsonProperty("ul_traffic2")
    private List<String> ulTraffic2;

    @JsonProperty("wl_traffic1")
    private List<Integer> wlTraffic1;

    @JsonProperty("wl_traffic2")
    private List<Integer> wlTraffic2;
}
