import java.text.DecimalFormatSymbols;

public class Product implements Comparable<Product> {
    private final String id;
    private final String name;
    private final Category category;
    private final Money price;

    public Product(String id, String name, Category category, Money price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // konstruktory, gettery itp
    @Override
    public int compareTo(final Product o) {
        int thisNumber = Integer.parseInt(id.substring(7));
        int otherNumber = Integer.parseInt(o.id.substring(7));
        return thisNumber - otherNumber;
    }

    public Money getPrice() {
        return price;
    }

    public enum Category {
        HOBBY,
        CLOTHES,
        GARDEN,
        AUTOMOTIVE
    }
}