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

/**
 * La classe RequestHandler gère les requêtes HTTP pour récupérer les informations sur les produits.
 */
public class RequestHandler {

    /**
     * Recherche des produits cosmétiques en fonction des paramètres spécifiés.
     * @param params Les paramètres de recherche.
     * @return Un tableau de produits correspondant aux critères de recherche.
     */
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

    /**
     * Recherche des produits cosmétiques par nom.
     * @param name Le nom du produit à rechercher.
     * @return Un tableau de produits correspondant au nom spécifié.
     */
    public Product[]  searchCosmeticsByName(String name) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("name", name);
        }});
    }

    /**
     * Recherche des produits cosmétiques par type.
     * @param type Le type de produit à rechercher.
     * @return Un tableau de produits correspondant au type spécifié.
     */
    public Product[] searchCosmeticsByType(String type) {
        return searchCosmetics(new HashMap<String, String>() {{
            put("type", type);
        }});
    }

    /**
     * Récupère les informations sur un produit cosmétique spécifique.
     * @param id L'identifiant du produit à rechercher.
     * @return Un tableau contenant le produit correspondant à l'identifiant spécifié.
     */
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

    /**
     * Convertit un flux d'entrée en une chaîne de caractères.
     * @param in Le flux d'entrée à convertir.
     * @return La chaîne de caractères correspondant au flux d'entrée.
     */
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

    /**
     * Convertit un flux d'entrée en un tableau de produits.
     * @param in Le flux d'entrée à convertir.
     * @return Un tableau de produits correspondant au flux d'entrée.
     */
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
                System.out.println(json);
                System.out.println(productList);
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