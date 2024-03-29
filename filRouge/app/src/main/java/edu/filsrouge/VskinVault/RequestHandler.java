package edu.filsrouge.VskinVault;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class RequestHandler {
    public HashMap<String, String>[] searchCosmetics(HashMap<String, String> params) {
        HashMap<String,String>[] response = null;
        String reqUrl="https://fortnite-api.com/v2/cosmetics/br/search/all";

        for (String key : params.keySet()) {
            reqUrl += "?"+key+"="+params.get(key);
        }

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToMaps(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public HashMap<String, String>[]  searchCosmeticsByName(String name) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("name", name);
        }});
    }

    public HashMap<String, String>[]  searchCosmeticsByType(String type) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("type", type);
        }});
    }

    public HashMap<String, String>[]  getCosmeticInfo(String id) {
        HashMap<String,String>[] response = null;
        String reqUrl="https://fortnite-api.com/v2/cosmetics/br/"+id;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToMaps(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private HashMap<String, String>[] convertStreamToMaps(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String json = result.toString();
        System.out.println(json);
        return new HashMap[]{new HashMap<String, String>()};
    }
}
