package com.tobzzo.mmmpk.ui.dashboardView.model.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "direction"

})
public class DeparturesStation {
    private String name;
    private DeparturesDirection[] direction;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("direction")
    public DeparturesDirection[] getDirection() {
        return direction;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "DeparturesStation{" +
                "name='" + name + '\'' +
                ", direction=" + Arrays.toString(direction) +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
