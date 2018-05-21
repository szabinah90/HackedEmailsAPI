package org.flow.exercise.hackedemails.springboot.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.flow.exercise.hackedemails.elasticsearchConnection.ElasticClusterConnector;
import org.flow.exercise.hackedemails.getHackedEmailsResponse.MyURLConnection;
import org.flow.exercise.hackedemails.springboot.model.HackedApiResponse;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public class HackedEmailsDAO {

    private final String INDEX = "hacked_emails";
    private final String TYPE = "doc";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    public HackedEmailsDAO(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    public HackedApiResponse checkEmail(String email) {
        ElasticClusterConnector elasticClusterConnector = new ElasticClusterConnector();

        /**
         * Check on hacker api
         */
        MyURLConnection myURLConnection = new MyURLConnection();
        String emailResponse = myURLConnection.getResponse(email);

        /**
         * Elastic search query
         */
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.types(TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders
        .termQuery("query", email));
        searchRequest.source(searchSourceBuilder);

        SearchHits hits = new SearchHits(new SearchHit[0], 0, 0);;

        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
            hits = searchResponse.getHits();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String matchID = "";
        for (SearchHit hit : hits) {
            matchID = hit.getId();
        }

        /**
         * Check if document exists
         */
        if (hits.totalHits == 0) {
            elasticClusterConnector.uploadingDocuments(emailResponse);
        } else {
            UpdateRequest updateRequest  = new UpdateRequest(INDEX, TYPE, matchID);
            updateRequest.doc(emailResponse, XContentType.JSON);
            try {
                restHighLevelClient.update(updateRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        HackedApiResponse modelObject = new HackedApiResponse();
        try {
            modelObject = mapper.readValue(emailResponse, HackedApiResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return modelObject;
    }
}
