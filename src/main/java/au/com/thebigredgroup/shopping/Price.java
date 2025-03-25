package au.com.thebigredgroup.shopping;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
    private final BigDecimal amount;

    public Price(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    public Price(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Price add(Price other) {
        return new Price(this.amount.add(other.amount));
    }

    public Price subtract(Price other) {
        return new Price(this.amount.subtract(other.amount));
    }

    public Price multiply(int quantity) {
        return new Price(this.amount.multiply(BigDecimal.valueOf(quantity)));
    }

    @Override
    public String toString() {
        return "$" + amount.setScale(2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Price)) return false;
        Price price = (Price) obj;
        return amount.equals(price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
