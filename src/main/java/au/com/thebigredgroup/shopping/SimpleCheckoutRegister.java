package au.com.thebigredgroup.shopping;

import java.util.HashMap;
import java.util.Map;

public class SimpleCheckoutRegister implements CheckoutRegister {
    private final ProductStore productStore;
    private final Map<String, Integer> cart = new HashMap<>();

    public SimpleCheckoutRegister(ProductStore productStore) {
        this.productStore = productStore;
    }

    @Override
    public void read(String sku) {
        cart.put(sku, cart.getOrDefault(sku, 0) + 1);
    }

    @Override
    public Price total() {
        Price totalPrice = new Price(0);
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String sku = entry.getKey();
            int quantity = entry.getValue();
            Product product = productStore.getProduct(sku);

            if (product == null) continue;

            switch (sku) {
                case "appletv":
                    totalPrice = totalPrice.add(applyThreeForTwoDeal(product, quantity));
                    break;
                case "ipad":
                    totalPrice = totalPrice.add(applyBulkDiscount(product, quantity));
                    break;
                case "macbookpro":
                    totalPrice = totalPrice.add(applyMacbookBundle(product, quantity));
                    break;
                default:
                    totalPrice = totalPrice.add(product.getPrice().multiply(quantity));
                    break;
            }
        }
        return totalPrice;
    }

    private Price applyThreeForTwoDeal(Product product, int quantity) {
        int chargeableUnits = (quantity / 3) * 2 + (quantity % 3);
        return product.getPrice().multiply(chargeableUnits);
    }

    private Price applyBulkDiscount(Product product, int quantity) {
        Price unitPrice = (quantity > 4) ? new Price(499.99) : product.getPrice();
        return unitPrice.multiply(quantity);
    }

    private Price applyMacbookBundle(Product product, int quantity) {
        Price macbookTotal = product.getPrice().multiply(quantity);

        // Check if HDMI adapters exist in cart
        int hdmiAdapters = cart.getOrDefault("hdmiadapter", 0);
        int freeHdmiAdapters = Math.min(quantity, hdmiAdapters);

        Price discount = new Price(130.00).multiply(freeHdmiAdapters);
        return macbookTotal.add(new Price(0).subtract(discount));
    }
}
