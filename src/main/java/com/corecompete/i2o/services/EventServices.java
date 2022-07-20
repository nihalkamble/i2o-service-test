package com.corecompete.i2o.services;

import com.corecompete.i2o.bq.BigQueryServices;
import com.corecompete.i2o.constants.BQQueries;
import com.corecompete.i2o.models.EventDashboard;
import com.corecompete.i2o.models.EventsList;
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
            eventsPerDay.setCount(row.get("count").getNumericValue().toBigInteger().intValue());
            eventResults.add(eventsPerDay);
        }
        return eventResults;
    }


    public ArrayList<EventsList> getEventsList() throws Exception {
        TableResult result = bigQueryServices.getData(projectId, BQQueries.getEventsList(projectId, dataset));

        ArrayList<EventsList> eventResults = new ArrayList<>();
        for (FieldValueList row : result.iterateAll()) {
            EventsList eventsList = new EventsList();
            eventsList.setEvent_id(row.get("event_id").getNumericValue());

            eventsList.setEventType(row.get("event_type").getStringValue());
            try {
                eventsList.setClient_asin(row.get("client_asin").getStringValue());
                eventsList.setGeneratedAt(Date.valueOf(row.get("generated_at").getStringValue()));
                eventsList.setCompetitors_asin(row.get("competitors_asin").getStringValue());

            } catch (Exception ignored) {
            }


            eventResults.add(eventsList);
        }
        return eventResults;
    }

    public ArrayList<EventDashboard> getEventDashboardData() throws Exception {
        TableResult result = bigQueryServices.getData(projectId, BQQueries.getEventDashboardData(projectId, dataset));

        ArrayList<EventDashboard> eventResults = new ArrayList<>();
        for (FieldValueList row : result.iterateAll()) {
            EventDashboard eventDashboard = new EventDashboard();


            eventDashboard.setEventType(row.get("event_type").getStringValue());
            eventDashboard.setEvent_category(row.get("event_category").getStringValue());
            try {
                eventDashboard.setMetric_impacted(row.get("metric_impacted").getStringValue());
            } catch (Exception ignored) {
            }
            eventDashboard.setGeneratedAt(Date.valueOf(row.get("generated_at").getStringValue()));
            eventDashboard.setTriggerCount(row.get("trigger_count").getNumericValue().toBigInteger().intValue());
            eventResults.add(eventDashboard);
        }
        return eventResults;
    }
}
