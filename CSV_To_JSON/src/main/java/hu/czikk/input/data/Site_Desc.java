package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "ulSystem", "ulSubSystem", "olDegree", "ulDegree", "ocCore_A", "ocOverlay_A", "ocHops_A", "ocLength_A", "ocCore_B", "ocOverlay_B", "ocHops_B", "ocLength_B", "syncParent_A", "syncHops_A", "syncLength_A", "syncParent_B", "syncHops_B", "syncLength_B"})
public class Site_Desc {
    private String id;
    private String name;
    private Integer ulSystem;
    private Integer ulSubSystem;
    private Integer olDegree;
    private Integer ulDegree;
    private String ocCore_A;
    private String ocOverlay_A;
    private Integer ocHops_A;
    private Double ocLength_A;
    private String ocCore_B;
    private String ocOverlay_B;
    private Integer ocHops_B;
    private Double ocLength_B;
    private String syncParent_A;
    private Integer syncHops_A;
    private Double syncLength_A;
    private String syncParent_B;
    private Integer syncHops_B;
    private Double syncLength_B;
}
