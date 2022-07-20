package com.corecompete.i2o.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EventsList implements Serializable {


    @JsonProperty(value = "event_id")
    private BigDecimal event_id;

    @JsonProperty(value = "competitors_asin")
    private String competitors_asin;

    @JsonProperty(value = "client_asin")
    private String client_asin;

    @JsonProperty(value = "event_type")
    private String eventType;

    @JsonProperty(value = "generated_at")
    private Date generatedAt;
}
