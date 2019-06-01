package niles.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Http {

    public static String httpGet(String url) throws IOException
    {
        //create an http connection object
        URL inUrl = new URL(url);
        HttpsURLConnection connect = (HttpsURLConnection) inUrl.openConnection();
        connect.setRequestMethod("GET");

        //check to make sure we get a valid response
        int responseCode = connect.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else{
            System.out.println("GET request failed.");
            return "";
        }
    }
}
