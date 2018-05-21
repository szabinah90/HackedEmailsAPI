package org.flow.exercise.hackedemails.springboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HackedApiResponse {
    private String status;
    private String query;
    private long results;
    private ArrayList<LeakedInfo> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public ArrayList<LeakedInfo> getData() {
        return data;
    }

    public void setData(ArrayList<LeakedInfo> data) {
        this.data = data;
    }
}
