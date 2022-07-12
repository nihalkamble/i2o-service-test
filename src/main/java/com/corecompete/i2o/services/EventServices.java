package com.corecompete.i2o.services;

import com.corecompete.i2o.bq.BigQueryServices;
import com.corecompete.i2o.constants.BQQueries;
import com.corecompete.i2o.models.EventsPerDay;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class EventServices {

    @Autowired
    BigQueryServices bigQueryServices;

    @Value("${google.cloud.project-id}")
    private String projectId;

    @Value("${google.cloud.dataset}")
    private String dataset;


    public ArrayList<EventsPerDay> getNumberOfEventsPerDay() throws Exception {
        TableResult result = bigQueryServices.getData(projectId, BQQueries.getNumberOfEventsPerDay(projectId, dataset));

        ArrayList<EventsPerDay> eventResults = new ArrayList<>();
        for (FieldValueList row : result.iterateAll()) {
            EventsPerDay eventsPerDay = new EventsPerDay();
            eventsPerDay.setEventName(row.get("event_name").getStringValue());
            eventsPerDay.setCount(row.get("count").getNumericValue().toBigInteger().intValue());
            try{
                eventsPerDay.setEventPositivity(row.get("event_positivity").getBooleanValue());
            } catch (Exception ignored){}
            eventsPerDay.setGeneratedAt(Date.valueOf(row.get("generated_at").getStringValue()));
            eventResults.add(eventsPerDay);
        }
        return eventResults;
    }


}
