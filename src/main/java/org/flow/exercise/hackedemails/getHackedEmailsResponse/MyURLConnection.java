package org.flow.exercise.hackedemails.getHackedEmailsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MyURLConnection {

    private final String url = "https://hacked-emails.com/api?q=";

    public String getResponse(String email) {
        URLConnection connection = null;
        InputStream response = null;

        try {
            connection = new URL(url + email).openConnection();
            response = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String responseBody = null;

        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }

        return responseBody;
    }
}
