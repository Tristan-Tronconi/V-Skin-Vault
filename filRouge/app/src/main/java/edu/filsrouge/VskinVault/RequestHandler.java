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
    public Product getCosmeticInfo(String id) {
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
        return response[0];
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
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Convertit un flux d'entrée en un tableau de produits.
     * @param in Le flux d'entrée à convertir.
     * @return Un tableau de produits correspondant au flux d'entrée.
     */
    private Product[] convertStreamToProducts(InputStream in) {
        String json = convertStreamToString(in);
        ObjectMapper mapper = new ObjectMapper();
        Product[] products = null;

        try {
            JsonNode root = mapper.readTree(json);
            System.out.println("ok1 "+root.toString());
            JsonNode data = root.get("data");
            System.out.println("ok2 "+data.toString());
            if (data.isArray()) {
                System.out.println("ok3");
                List<Product> productList = new ArrayList<>();
                Iterator<JsonNode> elements = data.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    Product product = new Product(
                        element.get("id").asText(),
                        element.get("name").asText(),
                        element.get("type").get("value").asText(),
                        element.get("rarity").get("value").asText(),
                        element.get("introduction").get("chapter").asText(),
                        element.get("introduction").get("season").asText(),
                        element.get("description").asText(),
                        element.get("images").get("icon").asText(),
                        element.get("images").get("featured").asText(),
                        element.get("images").get("smallIcon").asText()
                    );
                    productList.add(product);
                }
                products = productList.toArray(new Product[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}