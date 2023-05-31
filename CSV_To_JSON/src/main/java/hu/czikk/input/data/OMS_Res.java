package hu.czikk.input.data;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"omsID", "sequence", "linkID", "linkDirection"})
public class OMS_Res {
    private Integer omsID;
    private Integer sequence;
    private String linkID;
    private String linkDirection;
}
