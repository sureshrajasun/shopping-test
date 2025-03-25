package au.com.thebigredgroup.shopping;

public class Product {

    private final String sku;
    private final String name;
    private final Price price;

    public Product(String sku, String name, Price price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
