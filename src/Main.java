import java.time.LocalDate;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        System.out.println("Liczba klientów która dokonała zakupów: " + countClients(DataFactory.produce()));

        System.out.println("Liczba klientów którzy dokonali zakupów za pomocą BLIK: " + countClientsBlik(DataFactory.produce()));
        System.out.println("Liczba klientów którzy dokonali zakupów za pomoca Karty Kredytowej: " + countClientsCreditCard(DataFactory.produce()));

        //EUR
        System.out.println("Liczba klientów którzy dokonali zakupów w EUR: " + countPurchasesInEuro(DataFactory.produce()));

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

}



