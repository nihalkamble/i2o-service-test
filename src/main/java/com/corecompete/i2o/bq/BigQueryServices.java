package com.corecompete.i2o.bq;

import com.google.cloud.bigquery.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BigQueryServices {

    public BigQuery getBigqueryInstance(String projectId) {
        return BigQueryOptions.getDefaultInstance().toBuilder().setProjectId(projectId).build().getService();
    }

    public TableResult getData(String projectId, String query) throws Exception {
        BigQuery bigQuery = getBigqueryInstance(projectId);
        QueryJobConfiguration finalQueryObject = null;
        QueryJobConfiguration.Builder queryBuilder = QueryJobConfiguration.newBuilder(query);
        finalQueryObject = queryBuilder.build();

        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigQuery.create(JobInfo.newBuilder(finalQueryObject).setJobId(jobId).build());

        queryJob = queryJob.waitFor();
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }
        return queryJob.getQueryResults();
    }

}
