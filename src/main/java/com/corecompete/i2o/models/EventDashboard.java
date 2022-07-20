package com.corecompete.i2o.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EventDashboard implements Serializable {
    @JsonProperty(value = "triggerCount")
    private int triggerCount;

    @JsonProperty(value = "event_category")
    private String event_category;

    @JsonProperty(value = "metric_impacted")
    private String metric_impacted;

    @JsonProperty(value = "event_type")
    private String eventType;

    @JsonProperty(value = "generated_at")
    private Date generatedAt;

}
