package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"srcID", "dstID", "srcName", "dstName", "ocName", "hop", "omsID", "omsName", "omsDirection", "ulSystem", "ulSubSystem"})
public class OC {
    private String srcID;
    private String dstID;
    private String srcName;
    private String dstName;
    private String ocName;
    private Integer hop;
    private Integer omsID;
    private String omsName;
    private String omsDirection;
    private Integer ulSystem;
    private Integer ulSubSystem;
}
