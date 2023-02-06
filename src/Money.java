import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;
    private final Currency currency;
    private  Product product;


    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Product getProduct() {
        return product;
    }




    // konstruktory, gettery itp
    public enum Currency {
        PLN,
        EUR
    }
}