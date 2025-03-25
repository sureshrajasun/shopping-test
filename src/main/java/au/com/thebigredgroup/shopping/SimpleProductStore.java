package au.com.thebigredgroup.shopping;

import java.util.HashMap;
import java.util.Map;

public class SimpleProductStore implements ProductStore {
    private final Map<String, Product> products = new HashMap<>();

    public SimpleProductStore() {
        products.put("ipad", new Product("ipad", "Super Ipad", new Price(649.99)));
        products.put("macbookpro", new Product("macbookpro", "MacBook Pro", new Price(1499.99)));
        products.put("appletv", new Product("appletv", "Apple TV", new Price(209.50)));
        products.put("hdmiadapter", new Product("hdmiadapter", "HDMI Adapter", new Price(130.00)));
    }

    @Override
    public Product getProduct(String productSku) {
        return products.get(productSku);
    }
}

