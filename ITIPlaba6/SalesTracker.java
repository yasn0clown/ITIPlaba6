import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

public class SalesTracker {
    private final ConcurrentHashMap<String, AtomicInteger> salesMap = new ConcurrentHashMap<>();
    private double totalSales = 0.0;

    public synchronized void addSale(String product, double price) {
        salesMap.putIfAbsent(product, new AtomicInteger(0));
        salesMap.get(product).incrementAndGet();
        totalSales += price;
    }

    public void displaySales() {
        System.out.println("Список проданных товаров:");
        salesMap.forEach((product, count) -> System.out.println(product + ": " + count.get() + " шт."));
    }

    public synchronized double getTotalSales() {
        return totalSales;
    }

    public String getMostPopularProduct() {
        return salesMap.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue().get(), e2.getValue().get())).map(Map.Entry::getKey).orElse("Нет проданных товаров");
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        tracker.addSale("Хлеб", 50.0);
        tracker.addSale("Чай", 30.0);
        tracker.addSale("Хлеб", 50.0);
        tracker.addSale("Сахар", 40.0);
        tracker.addSale("Хлеб", 50.0);

        tracker.displaySales();
        System.out.println("Общая сумма продаж: " + tracker.getTotalSales());
        System.out.println("Самый популярный товар: " + tracker.getMostPopularProduct());
    }
}