package hu.czikk.output.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import hu.czikk.input.data.OC;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class FeatureOutput {

    @JsonProperty("features")
    private List<Object> features;

    @JsonProperty("systems")
    private Map<String, List<Integer>> systems;

    public void setSystems(Map<String, List<Integer>> systems) {
        this.systems = systems;
    }

    public void setAllSystems(List<OC> OC_arr) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (OC oc : OC_arr) {
            map.computeIfAbsent(oc.getUlSystem().toString(), i -> new ArrayList<>())
                    .addAll(Arrays.asList(oc.getUlSubSystem()));
        }
        for (String name : map.keySet()) {
            List<Integer> withDupes = map.get(name);
            List<Integer> withoutDupes = withDupes.stream()
                    .distinct()
                    .collect(Collectors.toList());
            map.put(name, withoutDupes);
        }
        this.setSystems(map);
    }
}
