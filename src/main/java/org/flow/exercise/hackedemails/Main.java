package org.flow.exercise.hackedemails;

import org.flow.exercise.hackedemails.elasticsearchConnection.ElasticClusterConnector;
import org.flow.exercise.hackedemails.getHackedEmailsResponse.MyURLConnection;
import org.flow.exercise.hackedemails.springboot.model.PwnResponse;
import org.flow.exercise.hackedemails.utilities.MyUtilities;

public class Main {
    public static void main(String[] args) {
        MyURLConnection myURLConnection = new MyURLConnection();
        ElasticClusterConnector elasticClusterConnector = new ElasticClusterConnector();
        MyUtilities myUtilities = new MyUtilities();

        /*
        String response = myURLConnection.getResponse("szabinahazi@gmail.com");
        System.out.println(response);


        PwnResponse pwnResponse = myUtilities.createResponseObject(response);
        String stringResponse = myUtilities.responseObjectToMappingString(pwnResponse);

        elasticClusterConnector.uploadingDocuments(stringResponse, "szabinahazi@gmail.com");
        */
    }
}
