package programmers.study.two_dimensional_array;

public class MatrixMultiplication {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        /**
         * 이 문제를 풀려면 행렬의 곱셈이 어떻게 구성되어 있는지 살펴보아야 합니다.
         * 행렬의 곱셈은 다음과 같이 왼쪽 행렬의 행과 오른쪽 행렬의 열이 짝을 이루어 수행합니다.
         * ┌ 1 2 3 ┐      ┌ 7 10 ┐
         * └ 4 5 6 ┘      │ 8 11 │
         *                └ 9 12 ┘
         * ① 1, 2, 3 ⇔ 7, 8, 9
         * ② 4, 5, 6 ⇔ 10, 11, 12
         * ┌  50  68 ┐
         * └ 122 167 ┘
         * 행렬의 곱셈 결과는 또 다른 행렬이 됩니다.
         * 이 결과 행렬은 왼쪽의 행 개수와 오른쪽 행렬의 열 개수를 갖게 되므로 결과 행렬은 다음과 같이 선언할 수 있습니다.
         */
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[i].length; j++) {
                /*
                 행렬 연산 과정에서 알 수 있듯이, 행렬의 곱셈 결과는 곱해지는 두 행렬의 행과 열을 순회하면서 곱한 값들을 모두 더해주어야 합니다.
                 */
                answer[i][j] = 0;
                for (int k = 0; k < arr1[i].length; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return answer;
    }
}
