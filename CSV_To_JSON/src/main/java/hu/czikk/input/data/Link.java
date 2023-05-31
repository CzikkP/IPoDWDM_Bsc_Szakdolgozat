package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "site1_ID", "site2_ID", "name", "length", "leased"})
public class Link {
    private String id;
    private String site1_ID;
    private String site2_ID;
    private String name;
    private Double length;
    private Integer leased;
}
