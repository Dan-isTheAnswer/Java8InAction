package lambdasinaction.scratch.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String[] args) {

        List<Apple> apples = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), 
                                            new Apple(160, "red"));

        // 1)
        List<Apple> greenApples1 = filterGreenApple(apples, "green");
        List<Apple> greenApples2 = filterApplesByColor(apples, new AppleColorPredicate());
        System.out.println("1) Green Apples 1 : " + greenApples1); 
        System.out.println("1) Green Apples 2 : " + greenApples2);

        // 2)
        List<Apple> greenApples3 = filter(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals("green");
            }
        });
        System.out.println("2) Green Apples 3 : " + greenApples3);
    }

	public static class Apple {
		private int weight = 0;
		private String color = "";

		public Apple(int weight, String color){
			this.weight = weight;
			this.color = color;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String toString() {
			return "Apple{" +
					"color='" + color + '\'' +
					", weight=" + weight +
					'}';
		}
    }

    // 1)
    private static List<Apple> filterGreenApple(List<Apple> apples, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (apple.getColor().equals("green")) result.add(apple);
        }

        return result;
    }

    private static List<Apple> filterApplesByColor (List<Apple> apples, AppleColorPredicate colorPredicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples ){
            if (colorPredicate.test(apple)) result.add(apple);
        }

        return result;
    }



    // 2)
    private static List<Apple> filter (List<Apple> apples, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : apples) {
            if (p.test(apple)) result.add(apple);
        }

        return result;
    }
    

    // utils
    interface ApplePredicate {
        public boolean test(Apple a);
    }

    static class AppleWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple a) {
            return "red".equals(a.getColor())
                    && a.getWeight() > 150;
        }
    }
}