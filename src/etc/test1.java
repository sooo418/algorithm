package etc;

import java.time.LocalDateTime;
import java.util.*;

public class test1 {
    static class Shape {
        private String type;

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
    }
    static class Rectangle extends Shape {
        private double area;

        public double getArea() {
            return area;
        }
    }
    public static void main(String[] args) {
        int[] ball = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] order = {5, 6, 4, 7, 3, 8, 2, 9, 1, 10};

        List<Integer> lastOrder = new ArrayList<>();
        List<Integer> answer = new ArrayList<>();

        System.out.println(LocalDateTime.now());

        for (int o : order) {
            if (!isPop(ball, o)) {
                lastOrder.add(o);
                continue;
            }

            answer.add(o);
            ball = removeArr(ball, o);

            for (int i = 0; i < lastOrder.size(); i++) {
                if (isPop(ball, lastOrder.get(i))) {
                    answer.add(lastOrder.get(i));
                    ball = removeArr(ball, lastOrder.get(i));
                    lastOrder.remove(i);
                    i = (-1);
                }
            }
        }
        System.out.println(answer);
        System.out.println(LocalDateTime.now());

        Shape shape = new Rectangle();
        shape.setType("color");
        System.out.println(shape);
        System.out.println(shape.getType());
        Rectangle rect = (Rectangle) shape;
        System.out.println(rect);
        System.out.println(rect.getArea());
    }

    public static boolean isPop(int[] ball, int o) {
        return ball[0] == o || ball[ball.length - 1] == o;
    }
    public static int[] removeArr(int[] ball, int o) {
        return Arrays.stream(ball).filter(num -> num != o).toArray();
    }
}
