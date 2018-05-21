package org.flow.exercise.hackedemails.springboot.model;

import java.util.List;

public class PwnResponse {
    private List<PwnResponseDetails> responseList;

    public List<PwnResponseDetails> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<PwnResponseDetails> responseList) {
        this.responseList = responseList;
    }

    @Override
    public String toString() {
        return "PwnResponse{" +
                "responseList=" + responseList +
                '}';
    }
}
