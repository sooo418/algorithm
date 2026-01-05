package programmers.study.character_array;

import java.util.Map;

public class NumberStringsAndEnglish {
    /**
     * 문제 풀이 흐름
     * 1. 각 인덱스 값에 해당하는 영단어가 저장되어 있는 영단어 문자열 배열을 구성
     * 2. 영단어 배열을 순회하며 입력 문자열에 등장하는 모든 영단어를 치환한 문자열 생성
     * 3. 변환된 문자열을 정수로 변환한 후 반환
     */

    // 1. 각 인덱스 값에 해당하는 영단어가 저장되어 있는 영단어 문자열 배열을 구성
    private static final String[] words = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
    };

    private int solution(String s) {
        // 2. 영단어 배열을 순회하며 입력 문자열에 등장하는 모든 영단어를 치환한 문자열 생성
        for (int i = 0; i < words.length; i++) {
            s = s.replace(words[i], Integer.toString(i));
        }
        // 3. 변환된 문자열을 정수로 변환한 후 반환
        return Integer.parseInt(s);
    }

    private static final Map<String, String> numStrMaps =
            Map.of("zero", "0",
                    "one", "1",
                    "two", "2",
                    "three", "3",
                    "four", "4",
                    "five", "5",
                    "six", "6",
                    "seven", "7",
                    "eight", "8",
                    "nine", "9");
    public static int solution2(String s) {
        for (Map.Entry<String, String> numStr : numStrMaps.entrySet()) {
            s = s.replace(numStr.getKey(), numStr.getValue());
        }
        return Integer.parseInt(s);
    }

    public static void main(String[] args) {
        System.out.println(solution2("one4seveneight"));
    }
}
