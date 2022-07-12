package com.corecompete.i2o.constants;

public class BQQueries {
    private BQQueries(){

    }

    public static String getNumberOfEventsPerDay(String projectId, String dataset){
        return "SELECT count(event_id) as count, event_name, event_positivity, date(generated_at) as generated_at from `"+projectId+"."+dataset+".events` group by date(generated_at), event_positivity, event_name;";
    }
}
