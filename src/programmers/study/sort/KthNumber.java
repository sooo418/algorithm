package programmers.study.sort;

import java.util.Arrays;

public class KthNumber {
    /**
     * 문제 풀이
     * 주어진 배열의 특정 구간을 정렬한 후 k번째 수를 찾아야 합니다.
     * 따라서 문제에서 주어지는 명령별로 다음 작업을 수행합니다.
     * 1. array에서 특정 구간 잘라 내기
     * 2. 잘라 낸 구간 정렬하기
     * 3. 정렬된 배열에서 k번째 수 구하기
     */

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] sorted;
        int index = 0;

        for (int[] command : commands) {
            /*
            * 입력되는 위치 정보는 1부터 시작하는 반면, 배열의 인덱스는 0부터 시작합니다.
            * 따라서 각 위치에 1을 빼서 0 기반의 인덱스로 변환합니다.
            * 단 일반적으로 범위를 나타낼 때는 시작 위치를 포함하고, 마지막 위치는 포함하지 않습니다.
            * 예를 들어 배열 [0, 1, 2, 3, 4, 5]가 있을 때,
            * [2, 3, 4] 범위는 원소 2의 인덱스인 2를 시작 위치로, 원소 4의 인덱스 + 1인 5를 끝 위치로 표현합니다.
            * 이는 포함(inclusive)인 시작 위치는 대괄호, 미포함(exclusive)인 끝 위치는 소괄호로 [2, 5]처럼 표련합니다.
            * 이에 따라 Arrays.copyOfRange(original, from, to)에 from과 to에는 아래와 같이 입력됩니다.
            * */
            sorted = Arrays.stream(Arrays.copyOfRange(array, command[0] - 1, command[1])).sorted().toArray();
            answer[index] = sorted[command[2] - 1];
            index++;
        }

        return answer;
    }
}