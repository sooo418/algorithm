package programmers.study.two_dimensional_array;

public class TriangleSnail {
    /**
     * 문제 풀이 흐름
     * 1. n x n 2차원 배열 선언
     * 2. 숫자를 채울 현재 위치를 (0, 0)으로 설정
     * 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기
     *  A. 아래로 이동하면서 숫자 채우기
     *  B. 오른쪽으로 이동하면서 숫자 채우기
     *  C. 왼쪽 위로 이동하면서 숫자 채우기
     * 4. 채워진 숫자를 차례대로 1차원 배열에 옮겨서 반환
     */

    public static int[] solution(int n) {
        // 1. nxn 2차원 배열 선언
        int[][] triangle = new int[n][n];
        int v = 1;

        // 2. 숫자를 채울 현재 위치를 (0, 0)으로 설정
        int x = 0;
        int y = 0;

        // 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기
        /* 아래, 오른쪽, 윈쪽 위로 이동하면서 숫자를 계속해서 채워 넣어야 하기 때문에 무한 루프로 작성 */
        while (true) {
            // 아래로 이동
            while (true) {
                triangle[y][x] = v++;
                if (y + 1 == n || triangle[y + 1][x] != 0) break;
                y += 1;
            }
            if (x + 1 == n || triangle[y][x + 1] != 0) break;
            x +=1;

            // 오른쪽으로 이동
            while (true) {
                triangle[y][x] = v++;
                if (x + 1 == n || triangle[y][x + 1] != 0) break;
                x +=1;
            }
            if (triangle[y - 1][x - 1] != 0) break;
            x -= 1;
            y -= 1;

            // 왼쪽 위로 이동
            while (true) {
                triangle[y][x] = v++;
                if (triangle[y - 1][x - 1] != 0) break;
                x -= 1;
                y -= 1;
            }
            if (triangle[y + 1][x] != 0) break;
            y += 1;
        }

        // 4. 채워진 숫자를 차례대로 1차원 배열에 옮겨서 반환
        int[] result = new int[v - 1];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = triangle[i][j];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = solution(1);
        System.out.print("[");
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println("]");
    }
}
