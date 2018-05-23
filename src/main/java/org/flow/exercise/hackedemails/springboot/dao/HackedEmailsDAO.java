package org.flow.exercise.hackedemails.springboot.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.flow.exercise.hackedemails.elasticsearchConnection.ElasticClusterConnector;
import org.flow.exercise.hackedemails.getHackedEmailsResponse.MyURLConnection;
import org.flow.exercise.hackedemails.springboot.model.PwnResponse;
import org.springframework.stereotype.Repository;

import org.flow.exercise.hackedemails.utilities.MyUtilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class HackedEmailsDAO {

    private final String INDEX = "have_i_been_pwned";
    private final String TYPE = "doc";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    public HackedEmailsDAO(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }


    public PwnResponse checkEmail(String email) throws IOException {

        ElasticClusterConnector elasticClusterConnector = new ElasticClusterConnector();
        MyURLConnection myURLConnection = new MyURLConnection();
        MyUtilities myUtilities = new MyUtilities();

        String emailResponse = myURLConnection.getResponse(email);
        String mappingResponse = myUtilities.responseObjectToMappingString(myUtilities.createResponseObject(emailResponse));

        GetRequest getRequest = new GetRequest(INDEX, TYPE, email);
        boolean docExist = false;

        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest);
            docExist = getResponse.isExists();
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }

        if (docExist) {
            UpdateRequest updateRequest  = new UpdateRequest(INDEX, TYPE, email);
            updateRequest.doc(mappingResponse, XContentType.JSON);
        } else {
            elasticClusterConnector.uploadingDocuments(mappingResponse, email);
        }

        ObjectMapper mapper = new ObjectMapper();
        PwnResponse modelObject = new PwnResponse();
        try {
            modelObject = mapper.readValue(mappingResponse, PwnResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return modelObject;
    }

    public boolean deleteDocument(String email) {

        GetRequest getRequest = new GetRequest(INDEX, TYPE, email);
        boolean docExist = false;

        try {
            GetResponse getResponse = restHighLevelClient.get(getRequest);
            docExist = getResponse.isExists();
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }

        if (docExist) {
            DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, email);

            try {
                DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return docExist;
    }
}
