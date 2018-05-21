package org.flow.exercise.hackedemails.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.flow.exercise.hackedemails.springboot.model.PwnResponse;
import org.flow.exercise.hackedemails.springboot.model.PwnResponseDetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyUtilities {

    public PwnResponse createResponseObject(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<PwnResponseDetails> pwnResponseDetailsList = new ArrayList<>();
        try {
            pwnResponseDetailsList  = objectMapper.readValue(response, new TypeReference<List<PwnResponseDetails>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        PwnResponse pwnResponse = new PwnResponse();
        pwnResponse.setResponseList(pwnResponseDetailsList);
        return pwnResponse;
    }

    public String responseObjectToMappingString(PwnResponse pwnResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        String objectJSON = "";
        try {
            objectJSON = objectWriter.writeValueAsString(pwnResponse);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectJSON;
    }

}
