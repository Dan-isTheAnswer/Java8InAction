package lambdasinaction.scratch.ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                                            new Apple(155, "green"),
                                            new Apple(120, "red"));
                                    
        // 1) Green Apples and Heavy Apples 
        List<Apple> greenApples = new ArrayList<>();
        greenApples = filterGreenApples(inventory);
        System.out.println(greenApples);

        List<Apple> heavyApples = new ArrayList<>();
        heavyApples = filterHeavyApples(inventory);
        System.out.println(heavyApples);


        // 2) Green Apples and Heavy Apples using Method References 
        List<Apple> greens = new ArrayList<>();
        greens = filterApples(inventory, FilteringApples::isGreen);
        System.out.println(greens); 
        
        // 3) Abstaction via Behavior Parameterization
        List<Apple> filteredApples1 = new ArrayList<>();
        List<Apple> filteredApples2 = new ArrayList<>();
        
        filteredApples1 = filterApples(inventory, (apple) -> apple.getColor().equals("green"));
        filteredApples2 = filterApples(inventory, (apple) -> apple.getWeight() > 150); 
        System.out.println(filteredApples1);
        System.out.println(filteredApples2);
    }

    // 1)
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> listApple = new ArrayList<>();
        for (Apple a : inventory) {
            if (a.getColor().equals("green")) {
                listApple.add(a);
            }
        }    

        return listApple;
    }

    private static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> listApple = new ArrayList<>();
        for (Apple a : inventory) {
            if (a.getWeight() > 150) {
                listApple.add(a);
            }
        }    

        return listApple;
    }

    // 2)
    private static boolean isGreen(Apple apple) {
        return apple.getColor().equals("green");
    }
    private static boolean isHeavy(Apple apple) {
        return apple.getWeight() > 150;
    }

    // 3) 
    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> isPredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple a : inventory) {
            if (isPredicate.test(a))
                result.add(a);
        }

        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight (Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Apple{" + 
                    "color='" + color + '\'' + 
                    ", weight=" + weight + 
                    '}';
        } // Apple{color='color', weight=weight}
    }
}