package yogiyo;

import java.util.*;

public class Second {
    static final String SMALL = "Small";
    static final String MEDIUM = "Medium";
    static final String LARGE = "Large";

    public static int solution(Pizza[] menu, Pizza.OrderItem[] order) {
        int basePrice = 0;
        int totalPizzas = 0;
        int cheapestPrice = Integer.MAX_VALUE;
        int discount1 = 0, discount2 = 0, discount3 = 0, discount4 = 0;
        int largeCnt = 0;

        Map<String, Integer> orderMap = new HashMap<>();
        Map<String, Integer> smallPizzaMap = new HashMap<>();
        Map<String, Integer> largePizzaMap = new HashMap<>();

        for (Pizza.OrderItem item : order) {
            totalPizzas += item.quantity;
            orderMap.put(item.name, orderMap.getOrDefault(item.name, 0) + item.quantity);

            if (item.size.equals(SMALL)) {
                smallPizzaMap.put(item.name, item.quantity);
            } else if (item.size.equals(LARGE)) {
                largePizzaMap.put(item.name, item.quantity);
                largeCnt++;
            }

            Pizza pizza = searchPizza(menu, item.name);
            if (pizza != null) {
                basePrice += getPizzaPrice(pizza, item.size) * item.quantity;
                cheapestPrice = Math.min(cheapestPrice, getPizzaPrice(pizza, item.size));
            }
        }

        // Apply Discount 1
        if (totalPizzas >= 3) {
            discount1 = cheapestPrice;
        }

        // Apply Discount 2
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            if (entry.getValue() >= 5) {
                discount2 = Math.max(discount2, calculateDiscount2(entry.getKey(), menu, order));
            }
        }
        discount2 = Math.max(0, discount2 - 100);

        // Apply Discount 3
        discount3 = calculateDiscount3(menu, smallPizzaMap, largePizzaMap);

        // Apply Discount 4
        if (largeCnt >= 3) {
            discount4 = calculateDiscount4(menu, largePizzaMap);
        }

        return basePrice - Math.max(Math.max(discount1, discount2), Math.max(discount3, discount4));
    }

    private static int getPizzaPrice(Pizza pizza, String size) {
        if (size.equals(SMALL)) return pizza.price_S;
        if (size.equals(MEDIUM)) return pizza.price_M;
        return pizza.price_L;
    }

    private static Pizza searchPizza(Pizza[] menu, String name) {
        for (Pizza pizza : menu) {
            if (pizza.name.equals(name)) {
                return pizza;
            }
        }
        return null;
    }

    private static int calculateDiscount2(String name, Pizza[] menu, Pizza.OrderItem[] order) {
        List<Integer> prices = new ArrayList<>();
        for (Pizza.OrderItem item : order) {
            if (item.name.equals(name)) {
                Pizza pizza = searchPizza(menu, item.name);
                if (pizza != null) {
                    for (int i = 0; i < item.quantity; i++) {
                        prices.add(getPizzaPrice(pizza, item.size));
                    }
                }
            }
        }
        Collections.sort(prices, Comparator.reverseOrder());
        return prices.stream().limit(5).mapToInt(Integer::intValue).sum();
    }

    private static int calculateDiscount3(Pizza[] menu, Map<String, Integer> smallPizzaMap, Map<String, Integer> largePizzaMap) {
        int discount = 0;
        for (Map.Entry<String, Integer> entry : largePizzaMap.entrySet()) {
            Pizza pizza = searchPizza(menu, entry.getKey());
            int largeCnt = entry.getValue();
            int smallCnt = smallPizzaMap.getOrDefault(entry.getKey(), 0);

            while (largeCnt > 0 && smallCnt > 0) {
                discount += pizza.price_S;
                largeCnt--;
                smallCnt--;
            }
        }
        return discount;
    }

    private static int calculateDiscount4(Pizza[] menu, Map<String, Integer> largePizzaMap) {
        int discount = 0;
        List<Pizza> pizzaList = Arrays.asList(menu);
        pizzaList.sort(Comparator.comparingInt(p -> p.price_M - p.price_L));

        int ableCnt = 3;
        for (Pizza pizza : pizzaList) {
            if (ableCnt <= 0) break;
            int largeCnt = largePizzaMap.getOrDefault(pizza.name, 0);
            while (largeCnt > 0 && pizza.price_M < pizza.price_L && ableCnt > 0) {
                discount += (pizza.price_L - pizza.price_M);
                largeCnt--;
                ableCnt--;
            }
        }
        return discount;
    }

    public static class Pizza {
        public String name;
        public int price_S;
        public int price_M;
        public int price_L;

        public Pizza(String name, int priceSmall, int priceMedium, int priceLarge) {
            this.name = name;
            this.price_S = priceSmall;
            this.price_M = priceMedium;
            this.price_L = priceLarge;

        }

        public static class OrderItem {
            public String name;
            public String size;
            public int quantity;

            public OrderItem(String name, String size, int quantity) {
                this.name = name;
                this.size = size;
                this.quantity = quantity;

            }
        }
    }

    public static void main(String[] args) {
        Second.Pizza[] menu = {
                new Second.Pizza("margherita", 7, 8, 10),
                new Second.Pizza("hawaii", 8, 9, 12),
                new Second.Pizza("capricciosa", 5, 7, 13)
        };

        // 주문 설정
        Second.Pizza.OrderItem[] order = {
                new Second.Pizza.OrderItem("margherita", "Small", 1),
                new Second.Pizza.OrderItem("hawaii", "Large", 1)
        };

        // 할인된 금액 계산
        int discountedPrice = Second.solution(menu, order);
        System.out.println("할인된 금액: $" + discountedPrice);

    }
}