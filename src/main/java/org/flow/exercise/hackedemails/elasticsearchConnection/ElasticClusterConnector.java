package org.flow.exercise.hackedemails.elasticsearchConnection;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.flow.exercise.hackedemails.fileReader.MyFileReader;

import java.io.IOException;

public class ElasticClusterConnector {

    private RestHighLevelClient establishConnection() {

        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
    }

    public void createIndex() {

        MyFileReader myFileReader = new MyFileReader();
        RestHighLevelClient client = establishConnection();
        ClassLoader classLoader = getClass().getClassLoader();

        String mappingFilePath = classLoader.getResource("elasticMap.json").getFile();
        String mappingFile = myFileReader.readFromFile(mappingFilePath);

        CreateIndexRequest request = new CreateIndexRequest("have_i_been_pwned");
        request.mapping("doc", mappingFile, XContentType.JSON);

        try {
            client.indices().create(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Index created.");
    }

    public void uploadingDocuments(String response, String email) {

        RestHighLevelClient client = establishConnection();
        IndexRequest request = new IndexRequest("have_i_been_pwned", "doc", email);

        try {
            client.index(request.source(response, XContentType.JSON));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Upload complete.");
    }
}
