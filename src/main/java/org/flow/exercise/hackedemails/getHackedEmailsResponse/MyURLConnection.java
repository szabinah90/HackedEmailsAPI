package org.flow.exercise.hackedemails.getHackedEmailsResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class MyURLConnection {

    private final String url = "https://haveibeenpwned.com/api/v2/breachedaccount/";

    public String getResponse(String email) {
        URLConnection connection = null;
        InputStream response = null;

        try {
            connection = new URL(url + email).openConnection();
            connection.addRequestProperty("User-Agent", "Leaked-Emails-Checker-Java");
            response = connection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BufferedReader(new InputStreamReader(response)).lines().collect(Collectors.joining("\n"));
    }
}
