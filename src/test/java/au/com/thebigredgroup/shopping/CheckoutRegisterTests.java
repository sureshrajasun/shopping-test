package au.com.thebigredgroup.shopping;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckoutRegisterTests {
    private ProductStore productStore;

    @Before
    public void setUp() {
        productStore = new SimpleProductStore();
    }

    /**
     * Test Case 1: Apple TV "3 for 2" Deal
     * Expected: When buying 3 Apple TVs, the price should be for 2 only.
     * Expected Total: $549.00
     */
    @Test
    public void testAppleTvThreeForTwoDeal() {
        CheckoutRegister checkout = new SimpleCheckoutRegister(productStore);
        checkout.read("appletv");
        checkout.read("appletv");
        checkout.read("appletv");
        checkout.read("hdmiadapter");

        Price expectedTotal = new Price(549.00);
        assertEquals(expectedTotal, checkout.total());
    }

    /**
     * Test Case 2: Bulk Discount on iPads
     * Expected: When buying more than 4 iPads, each iPad should be priced at $499.99 instead of $649.99.
     * Expected Total: $2918.95
     */
    @Test
    public void testBulkDiscountOniPads() {
        CheckoutRegister checkout = new SimpleCheckoutRegister(productStore);
        checkout.read("appletv");
        checkout.read("ipad");
        checkout.read("ipad");
        checkout.read("appletv");
        checkout.read("ipad");
        checkout.read("ipad");
        checkout.read("ipad");

        Price expectedTotal = new Price(2918.95);
        assertEquals(expectedTotal, checkout.total());
    }

    /**
     * Test Case 3: MacBook Pro Bundle Deal
     * Expected: Every MacBook Pro purchase comes with a free HDMI adapter.
     * Expected Total: $2149.98
     */
    @Test
    public void testMacBookProBundle() {
        CheckoutRegister checkout = new SimpleCheckoutRegister(productStore);
        checkout.read("macbookpro");
        checkout.read("hdmiadapter");
        checkout.read("ipad");

        Price expectedTotal = new Price(2149.98);
        assertEquals(expectedTotal, checkout.total());
    }

    /**
     * Test Case 4: MacBook Pro and Apple TV Purchase
     * Expected: No special discount applies, just summing individual prices.
     * Expected Total: $1709.49
     */
    @Test
    public void testMacBookProAndAppleTv() {
        CheckoutRegister checkout = new SimpleCheckoutRegister(productStore);
        checkout.read("macbookpro");
        checkout.read("appletv");

        Price expectedTotal = new Price(1709.49);
        assertEquals(expectedTotal, checkout.total());
    }

}
