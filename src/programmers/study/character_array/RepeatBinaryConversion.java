package programmers.study.character_array;

public class RepeatBinaryConversion {
    /**
     * 문제 풀이 흐름
     * 1. 검사하는 문자열이 "1"이 될 때까지 반복
     *  A. 문자열에 포함된 0의 개수 세기
     *      ⅰ. 0의 개수와 제거 횟수 누적
     *  B. 나머지 1의 개수를 사용해서 2진법으로 변환하여 1부터 반복
     * 2. 누적된 제거 횟수와 0의 개수를 배열로 반환
     */

    public int[] solution(String s) {
        int loop = 0;
        int removed = 0;

        // 1. 검사하는 문자열이 "1"이 될 때까지 반복
        while(!s.equals("1")) {
            // 1-A-ⅰ. 0의 개수와 제거 횟수 누적
            int zeros = countZeros(s);
            loop += 1;
            removed += zeros;

            // 1-B. 나머지 1의 개수를 사용해서 2진법으로 변환하여 1부터 반복
            int ones = s.length() - zeros;
            s = Integer.toString(ones, 2);
        }

        // 2. 누적된 제거 횟수와 0의 개수를 배열로 반환
        return new int[] {loop, removed};
    }

    // 1-A. 문자열에 포함된 0의 개수 세기
    private int countZeros(String s) {
        int zeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') zeros++;
        }
        return zeros;
    }

    public int[] solution2(String s) {
        int[] answer = new int[2];

        while(!s.equals("1")) {
            char[] arr = s.toCharArray();
            for (char c : arr) {
                if (c == '0') {
                    answer[2]++;
                }
            }
            s = Integer.toString(s.replace("0", "").length(), 2);
            answer[0]++;
        }

        return answer;
    }
}
