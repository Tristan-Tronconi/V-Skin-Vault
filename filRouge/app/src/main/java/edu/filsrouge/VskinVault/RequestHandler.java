package edu.filsrouge.VskinVault;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class RequestHandler {
    public Product[] searchCosmetics(HashMap<String, String> params) {

        Product[] response = null;
        String reqUrl="https://fortnite-api.com/v2/cosmetics/br/search/all";

        for (int i = 0; i < params.size(); i++) {
            String key = (String) params.keySet().toArray()[i];
            String value = params.get(key);
            if(i == 0)
                reqUrl += "?";
            else
                reqUrl += "&";
            reqUrl += key + "=" + value;
        }

        try {
            System.out.println("Request URL: " + reqUrl); //TODO remove

            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToProducts(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public Product[]  searchCosmeticsByName(String name) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("name", name);
        }});
    }

    public Product[] searchCosmeticsByType(String type) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("type", type);
        }});
    }

    public Product[]  getCosmeticInfo(String id) {
        Product[] response = null;
        String reqUrl="https://fortnite-api.com/v2/cosmetics/br/"+id;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToProducts(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    private String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private Product[] convertStreamToProducts(InputStream in) {
        String json = convertStreamToString(in);
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON as tree
            JsonNode rootNode = objectMapper.readTree(json);

            // Get the "data" array from the JSON
            JsonNode dataArray = rootNode.get("data");

            // If "data" array exists and is an array
            if (dataArray != null && dataArray.isArray()) {
                // Iterate through the array and convert each element to Product
                List<Product> productList = new ArrayList<>();
                for (JsonNode productNode : dataArray) {
                    Product product = new Product(
                            productNode.get("id").asText(),
                            productNode.get("name").asText(),
                            productNode.get("type").get("value").asText(),
                            productNode.get("rarity").get("value").asText(),
                            productNode.get("introduction").get("chapter").asText(),
                            productNode.get("introduction").get("season").asText(),
                            productNode.get("description").asText(),
                            productNode.get("images").get("featured").asText(),
                            productNode.get("images").get("icon").asText(),
                            productNode.get("images").get("smallIcon").asText()
                    );

                    productList.add(product);
                }
                return productList.toArray(new Product[0]);
            } else {
                System.out.println("No data array found in the JSON.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
