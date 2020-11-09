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
        "hour",
        "minute"

})
public class DeparturesTime {
    private String hour;
    private String minute;

    @JsonProperty("hour")
    public String getHour() {
        return hour;
    }

    @JsonProperty("minute")
    public String getMinute() {
        return minute;
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
        return "DepartureTimeModel{" +
                "hour='" + hour + '\'' +
                ", minute='" + minute + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
