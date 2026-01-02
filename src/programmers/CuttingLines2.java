package programmers;

public class CuttingLines2 {
    public static int minCutTime(int n, int[] times) {
        // 메모이제이션을 위한 배열
        int[] memo = new int[n + 1];
        // 메모이제이션 배열 초기화
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        // 재귀 호출 시작
        return minCutTimeHelper(n, times, memo);
    }

    private static int minCutTimeHelper(int n, int[] times, int[] memo) {
        // 기본 케이스: n이 1이면 자를 필요가 없음
        if (n == 1) {
            return 0;
        }
        // 이미 계산된 경우 메모이제이션 값 반환
        if (memo[n] != -1) {
            return memo[n];
        }
        // 최소 시간을 큰 값으로 초기화
        int minTime = Integer.MAX_VALUE;
        // 가능한 모든 자르기 방법을 시도
        for (int i = 1; i <= n / 2; i++) {
            int time = times[i - 1] + minCutTimeHelper(n - i, times, memo);
            minTime = Math.min(minTime, time);
        }
        // 메모이제이션 배열에 결과 저장
        memo[n] = minTime;
        return minTime;
    }

    public static void main(String[] args) {
        int n = 15;
        int[] times = {2,3,5,6,8,9,10,15,16};
        System.out.println(minCutTime(n, times)); // 출력: 5
    }
}
