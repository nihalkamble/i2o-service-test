package com.corecompete.i2o.constants;

public class BQQueries {
    private BQQueries() {
    }

    public static String getNumberOfEventsPerDay(String projectId, String dataset) {
        return "SELECT COUNT(event_id) AS count,FROM`" + projectId + "." + dataset + ".events` WHERE DATE(generated_at) > CURRENT_DATE() - 4;";
    }

    public static String getEventsList(String projectId, String dataset) {
        return "SELECT client_asin,event_type,competitors_asin,event_id,generated_at FROM `" + projectId + "." + dataset + ".events` WHERE DATE(generated_at) > CURRENT_DATE() - 4;";

    }

    public static String getEventDashboardData(String projectId, String dataset) {
        return "SELECT event_type, event_category, metric_impacted, COUNT(DATE(generated_at)) AS trigger_count,DATE(generated_at) AS generated_at, FROM `" + projectId + "." + dataset + ".events` WHERE DATE(generated_at) > CURRENT_DATE() - 4 GROUP BY event_type, event_category, metric_impacted, DATE(generated_at);";
    }
}
