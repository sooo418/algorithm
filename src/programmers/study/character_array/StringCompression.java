package programmers.study.character_array;

import java.util.ArrayList;
import java.util.List;

public class StringCompression {
    /**
     * 문제 풀이 흐름
     * 1. 1부터 입력 문자열 s의 길이만큼 자를 문자열의 길이를 설정하며 반복
     * 2. 설정된 길이만큼 문자열을 잘라 낸 token의 배열 생성
     * 3. 문자열을 비교하며 token의 배열을 하나의 문자열로 압축
     * 4. 1~3 과정으로 압축된 문자열 중 가장 짧은 길이 반환
     */

    public int solution(String s) {
        // 1. 1부터 입력 문자열 s의 길이만큼 자를 문자열의 길이를 설정하며 반복
        int min = Integer.MAX_VALUE;
        for (int length = 1; length <= s.length(); length++) {
            // 4. 1~3 과정으로 압축된 문자열 중 가장 짧은 길이 반환
            int compressed = compress(s, length);
            if (compressed < min) {
                min = compressed;
            }
        }
        return min;
    }

    private int compress(String source, int length) {
        StringBuilder sb = new StringBuilder();

        // 3. 문자열을 비교하며 token의 배열을 하나의 문자열로 압축
        String last = "";
        int count = 0;
        for (String token : split(source, length)) {
            if (token.equals(last)) {
                count++;
            } else {
                if (count > 1) sb.append(count);
                sb.append(last);
                last = token;
                count = 1;
            }
        }
        if (count > 1) sb.append(count);
        sb.append(last);

        return sb.length();
    }

    // 2. 설정된 길이만큼 문자열을 잘라 낸 token의 배열 생성
    private List<String> split(String source, int length) {
        List<String> tokens = new ArrayList<>();
        // source를 length만큼씩 잘라 tokens 리스트에 추가
        for (int startIndex = 0; startIndex < source.length(); startIndex += length) {
            int endIndex = startIndex + length;
            if (endIndex > source.length()) endIndex = source.length();
            tokens.add(source.substring(startIndex, endIndex));
        }
        return tokens;
    }
}
