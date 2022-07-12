package com.corecompete.i2o.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Events implements Serializable {

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "event_id")
    private int eventId;

    @JsonProperty(value = "event_name")
    private String eventName;

    @JsonProperty(value = "result_json")
    private ArrayList<Map<String, Object>> resultJson;

    @JsonProperty(value = "event_positivity")
    private boolean eventPositivity;

    @JsonProperty(value = "generated_at")
    private Timestamp generatedAt;

}
