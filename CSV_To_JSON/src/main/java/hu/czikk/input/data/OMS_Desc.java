package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "layer", "site1_ID", "site2_ID", "hops", "length", "cost"})
public class OMS_Desc {
    private Integer id;
    private String name;
    private String layer;
    private String site1_ID; //from
    private String site2_ID; //to
    private Integer hops;
    private Double length;
    private Double cost;
}
