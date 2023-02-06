import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {


//        System.out.println("Liczba klientów która dokonała zakupów: " + countClients(DataFactory.produce()));
//
//        System.out.println("Liczba klientów którzy dokonali zakupów za pomocą BLIK: " + countClientsBlik(DataFactory.produce()));
//        System.out.println("Liczba klientów którzy dokonali zakupów za pomoca Karty Kredytowej: " + countClientsCreditCard(DataFactory.produce()));
//
//        //EUR
//        System.out.println("Liczba klientów którzy dokonali zakupów w EUR: " + countPurchasesInEuro(DataFactory.produce()));
//        //unikalne EUR
//        System.out.println("Liczba klientów którzy dokonali zakupu unikalnych produktów w EUR: " + countUniqueProductsInEuro(DataFactory.produce()));

         //countMoneySpentInPln(DataFactory.produce());
         //sumMoneySpentInPln(DataFactory.produce());
        //sumMoneySpentInPlnForEachClient(DataFactory.produce());
        System.out.println("Podsumowanie wydaktów każdego klienta: " + calculateTotalMoneySpentInPlnByEachClient(DataFactory.produce()));

    }

    //Stwórz metode która oblicza jaka ilość klientów dokonała zakupu w naszym sklepie
    public static long countClients(List<Purchase> purchases) {
        return purchases.stream()
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    //Stwórz metode która oblicza jaka ilość klientów płaciła Blikiem.
    public static long countClientsBlik(List<Purchase> purchases) {
        return purchases.stream()
                .filter(purchase -> Purchase.Payment.BLIK.equals(purchase.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    public static long countClientsCreditCard(List<Purchase> purchases) {
        return purchases.stream()
                .filter(purchase -> Purchase.Payment.CREDIT_CARD.equals(purchase.getPayment()))
                .map(Purchase::getBuyer)
                .distinct()
                .count();
    }

    //Stwórz metoda która oblicza jaka ilość zakupów została wykonana w walucie EUR.
    public static long countPurchasesInEuro(List<Purchase> purchases) {
        return purchases.stream()
                .filter(purchase -> Money.Currency.EUR.equals(purchase.getProduct().getPrice().getCurrency()))
                .count();
    }
    //stwórz metode która oblicza ile unikalnych kupionych produktów zostało zakupionych w EUR.
    public static long countUniqueProductsInEuro(List<Purchase> purchases) {
        return purchases.stream()
                .filter(purchase -> Money.Currency.EUR.equals(purchase.getProduct().getPrice().getCurrency()))
                .map(Purchase::getProduct)
                .distinct()
                .count();
    }
   // Oblicz ile PLN wydała w sklepie każda z osób, które dokonały u nas zakupu. Uwzględnij tylko zakupy w PLN.
    public static void countMoneySpentInPln(List<Purchase> purchases) {
        purchases.stream()
                .filter(purchase -> Money.Currency.PLN.equals(purchase.getProduct().getPrice().getCurrency()))
                .map(Purchase::getProduct)
                .map(Product::getPrice)
                .map(Money::getValue)
                .forEach(System.out::println);
    }





    public static void sumMoneySpentInPlnForEachClient( final List<Purchase> purchasesList) {
        var howMuchSpent = purchasesList.stream()
                .filter(purchase -> Money.Currency.PLN.equals(purchase.getProduct().getPrice().getCurrency()))
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getBuyer().getId(),
                        TreeMap::new
                        , Collectors.mapping(
                                purchase -> purchase.getProduct().getPrice().getValue().
                                        multiply(BigDecimal.valueOf(purchase.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
        System.out.println(howMuchSpent);





    }
    public static void sumMoneySpentInPlnForEachClient1(final List<Purchase> purchasesList) {
        var howMuchSpent = purchasesList.stream()
                .filter(purchase -> Money.Currency.PLN.equals(purchase.getProduct().getPrice().getCurrency()))
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getBuyer().getId(),
                        TreeMap::new,
                        Collectors.mapping(
                                purchase -> purchase.getProduct().getPrice().getValue().
                                        multiply(BigDecimal.valueOf(purchase.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
        System.out.println(howMuchSpent);
    }


    public static Map<String, BigDecimal> calculateTotalMoneySpentInPlnByEachClient(final List<Purchase> purchasesList) {
        if (purchasesList == null || purchasesList.isEmpty()) {
            return Collections.emptyMap();
        }

        return purchasesList.stream()
                .filter(purchase -> Money.Currency.PLN.equals(purchase.getProduct().getPrice().getCurrency()))
                .collect(Collectors.groupingBy(
                        purchase -> purchase.getBuyer().getId(),
                        TreeMap::new,
                        Collectors.mapping(
                                purchase -> purchase.getProduct().getPrice().getValue().
                                        multiply(BigDecimal.valueOf(purchase.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
    }
}


















