package programmers.study.two_dimensional_array;

public class RefactoringTriangleSnail {
    /**
     * 문제 풀이 흐름
     * 1. n x n 2차원 배열 선언
     * 2. 숫자를 채울 현재 위치를 (0, 0)으로 설정
     * 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기 (dx, dy를 사용하여 복사-붙여넣기 방식을 제거)
     *  A. 아래로 이동하면서 숫자 채우기
     *  B. 오른쪽으로 이동하면서 숫자 채우기
     *  C. 왼쪽 위로 이동하면서 숫자 채우기
     * 4. 채워진 숫자를 차례대로 1차원 배열에 옮겨서 반환
     */
    // dx, dy 사용
    /*
    1. 3-A 아래 -> dx[0], dy[0]
    2. 3-B 오른쪽 -> dx[1], dy[1]
    3. 3-C 왼족 위 -> dx[2], dy[2]
    */
    private static final int[] dx = {0, 1, -1};
    private static final int[] dy = {1, 0, -1};

    public static int[] solution(int n) {
        // 1. nxn 2차원 배열 선언
        int[][] triangle = new int[n][n];
        int v = 1;

        // 2. 숫자를 채울 현재 위치를 (0, 0)으로 설정
        int x = 0;
        int y = 0;
        int d = 0;

        // 3. 방향에 따라 이동할 수 없을 때까지 반복하면서 숫자 채우기
        while (true) {
            triangle[y][x] = v++;
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 더 이상 진행할 수 없을 때의 처리
            if (nx == n || ny == n || triangle[ny][nx] != 0) {
                // 왼쪽 [0] -> 오른쪽 [1] -> 왼쪽 위 [2] -> 왼쪽 [0] ...
                d = (d + 1) % 3;
                nx = x + dx[d];
                ny = y + dy[d];
                if (nx == n || ny == n || triangle[ny][nx] != 0) break;
            }
            x = nx;
            y = ny;
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
        int[] result = solution(6);
        System.out.print("[");
        for (int i : result) {
            System.out.print(i + ",");
        }
        System.out.println("]");
    }
}
