package com.corecompete.i2o.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EventsPerDay implements Serializable {

    @JsonProperty(value = "count")
    private int count;

}
