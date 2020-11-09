package com.tobzzo.mmmpk.ui.dashboardView.model.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "line",
        "station",
        "direction",
        "day"

})
public class Departures {
    private String line;
    private String station;
    private String direction;
    private DeparturesDay[] day;

    @JsonProperty("line")
    public String getLine() {
        return line;
    }

    @JsonProperty("station")
    public String getStation() {
        return station;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("day")
    public DeparturesDay[] getDay() {
        return day;
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
        return "Departures{" +
                "line='" + line + '\'' +
                ", station='" + station + '\'' +
                ", direction='" + direction + '\'' +
                ", day=" + day +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
