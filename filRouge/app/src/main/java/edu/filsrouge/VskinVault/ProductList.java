package edu.filsrouge.VskinVault;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static ProductList instance;
    private List<Product> products;

    private ProductList() {
        products = new ArrayList<>();
    }
    
    public static synchronized ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

}
