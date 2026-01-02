package programmers;

public class CuttingLines {
    public static void main(String[] args) {
        int n = 10;
        int[] times = {2, 5, 8, 9, 10};

        System.out.println(solution(n, times));
    }

    public static int solution(int n, int[] times) {
        int answer = 0;
        int line = n;
        if (line % 2 == 1) {
            line--;
            answer += times[0];
        }
        while((line /= 2) >= 1) {
            if (times[line - 1] > (times[0] * line)) {
                answer += times[0] * line;
            } else {
                answer += times[line - 1];
            }

            if (line > 1 && line % 2 == 1) {
                line--;
                answer += times[0];
            }
        }

        return answer;
    }
}