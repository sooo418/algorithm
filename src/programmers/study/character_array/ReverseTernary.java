package programmers.study.character_array;

public class ReverseTernary {
    /**
     * 문제 풀이 흐름
     * 1. 정수를 3진법으로 변환
     * 2. 변환된 문자열 뒤집기
     * 3. 뒤집은 문자열을 정수로 변환
     */

    public int solution(int n) {
        // 1. 정수를 3진법으로 변환
        String str = Integer.toString(n, 3);

        // 2. 변환된 문자열 뒤집기
        String revered = new StringBuilder(str).reverse().toString();

        // 3. 뒤집은 문자열을 정수로 변환
        return Integer.valueOf(revered, 3);
    }

    public static int solution2(int n) {
        return Integer.parseInt(new StringBuilder(Integer.toString(n, 3)).reverse().toString(), 3);
    }

    public static void main(String[] args) {
        System.out.println(solution2(45));
    }
}
