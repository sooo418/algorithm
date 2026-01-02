package programmers.study.two_dimensional_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DrawingStar {
    private static class Point {
        public final long x, y;
        private Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
    * 문제 풀이 흐름
    * 1. 모든 직성 쌍에 대해 반복
    *   A. 교점 좌표 구하기
    *   B. 정수 좌표만 저장
    * 2. 저장된 정수들에 대해 x, y 좌표의 최댓값, 최솟값 구하기
    * 3. 구한 최댓값, 최솟값을 이용하여 2차원 배열의 크기 결정
    * 4. 2차원 배열에 별 표시
    * 5. 문자열 배열로 변환 후 반환
    * */

    public static String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        // 1. 모든 직선 쌍에 대해 반복
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                // 1-A 교점 좌표 구하기
                Point intersection = intersection(line[i][0], line[i][1], line[i][2],
                                                line[j][0], line[j][1], line[j][2]);

                // 1-B 정수 좌표만 저장
                if (intersection != null) {
                    points.add(intersection);
                }
            }
        }

        // 2. 저장된 정수들에 대해 x, y 좌표의 최댓값, 최솟값 구하기
        Point minimum = getMinimumPoint(points);
        Point maximum = getMaximumPoint(points);

        int width = (int) (maximum.x - minimum.x + 1);
        int height = (int) (maximum.y - minimum.y + 1);

        // 3. 구한 최댓값, 최솟값을 이용하여 2차원 배열의 크기 결정
        char[][] arr = new char[height][width];
        for (char[] row : arr) {
            Arrays.fill(row, '.');
        }

        // 4. 2차원 배열에 별 표시
        for (Point p : points) {
            int x = (int) (p.x - minimum.x);
            int y = (int) (maximum.y - p.y);
            arr[y][x] = '*';
        }

        // 5. 문자열 배열로 변환 후 반환
        String[] result = new String[arr.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String(arr[i]);
        }
        return result;
    }

    // 1-A 교점 좌표 구하기
    private static Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
        double x = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
        double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

        if (x % 1 != 0 || y % 1 != 0) return null;

        return new Point((long) x, (long) y);
    }

    /*
        2. 저장된 정수들에 대해 x,y 좌표의 최대값 최솟값 구하기
         - 별을 표시할 2차원 배열은 정확히 별을 표시할 수 있을 정도로 작게 잡아야 하므로 이를 위해 각 좌표의 최대값과 최솟값을 구해야 함
    */
    private static Point getMinimumPoint(List<Point> points) {
        long x = Long.MAX_VALUE;
        long y = Long.MAX_VALUE;

        for (Point p : points) {
            if (p.x < x) x = p.x;
            if (p.y < y) y = p.y;
        }

        return new Point(x, y);
    }

    private static Point getMaximumPoint(List<Point> points) {
        long x = Long.MIN_VALUE;
        long y = Long.MIN_VALUE;

        for (Point p : points) {
            if (p.x > x) x = p.x;
            if (p.y > y) y = p.y;
        }

        return new Point(x, y);
    }

    public static void main(String[] args) {
        int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};

        for (String row : solution(line)) {
            System.out.println(row);
        }
    }
}
