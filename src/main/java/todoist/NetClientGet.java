package todoist;

import java.net.HttpURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientGet {
    public static void main(String[] args) {

        String token = "c0d2f28135872130984acf1ccf39461203f233db";
        try {

            URL url = new URL("https://api.todoist.com/rest/v1/projects");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization","Bearer "+token);
            conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

}


