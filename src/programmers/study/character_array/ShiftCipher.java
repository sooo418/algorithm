package programmers.study.character_array;

import java.util.stream.Collectors;

public class ShiftCipher {
    /**
     * 해당 문제는 아스키 코드 값을 n만큼 증가시키는 것과 같다.
     * 주의 해야할 점은 z에서 n이 1이면 a로 다시 돌아온다는 점 (122 + 1 -> 97)
     *
     * 문제 풀이 흐름
     * 1. 입력 문자열의 모든 문자에 대해 반복
     *  A. 알파벳이 아닌 경우 문자를 그대로 이어 붙이기
     *  B. 알파벳인 경우 n만큼 밀어 이어 붙이기
     */

    public String solution(String s, int n) {
        // 1. 입력 문자열의 모든 문자에 대해 반복
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(push(c, n));
        }
        return sb.toString();
    }

    // 람다식과 IntStream을 이용한 코드 작성
    public static String solution2(String s, int n) {
        return s.chars()
                .map(c -> push((char) c, n))
                .collect(StringBuilder::new,
                    StringBuilder::appendCodePoint,
                    StringBuilder::append)
                .toString();
    }

    private static char push(char c, int n) {
        // 1-A. 알파벳이 아닌 경우 문자를 그대로 이어 붙이기
        if (!(c >= 'A' && c <= 'Z') && !(c >= 'a'  && c <= 'z')) return c;
        // 아래는 내부 라이브러리 사용
        if (!Character.isAlphabetic(c)) return c;

        // 1-B. 알파벳인 경우 n만큼 밀어 이어 붙이기
        int offset = Character.isUpperCase(c) ? 'A' : 'a';
        int position = c - offset;
        position = (position + n) % ('Z' - 'A' + 1);

        return (char) (position + offset);
    }

    public static void main(String[] args) {
        System.out.println(solution2("AB", 1));
    }
}
