package programmers.study.character_array;

public class StrangeCharacters {
    /**
     * 문제 풀이 흐름
     * 1. 문자열의 모든 문자에 대해 반복
     *  A. 문자가 공백 문자일 경우
     *      ⅰ. 그대로 이어 붙이기
     *      ⅱ. 다음 등장하는 알파벳은 대문자
     *  B. 공백 문자가 아닌 경우
     *      ⅰ. 대 소문자 변환하여 이어 붙이기
     *      ⅱ. 다음 등장하는 알파벳의 대 소문자는 현재 변환하는 문자와 반대
     * 2. 구성한 문자열 반환
     */

    public String solution(String s) {
        // 1. 문자열의 모든 문자에 대해 반복
        StringBuilder sb = new StringBuilder();
        boolean toUpper = true;

        for (char c : s.toCharArray()) {
            if (!Character.isAlphabetic(c)) {
                // 1-A. 문자가 공백 문자일 경우

                // 1-A-ⅰ. 그대로 이어 붙이기
                sb.append(c);
                // 1-A-ⅱ. 다음 등장하는 알파벳은 대문자
                toUpper = true;
            } else {
                // 1-B. 공백 문자가 아닌 경우

                // 1-B-ⅰ. 대 소문자 변환하여 이어 붙이기
                if (toUpper) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
                // 1-B-ⅱ. 다음 등장하는 알파멧의 대 소문자는 현재 변환하는 문자와 반대
                toUpper = !toUpper;
            }
        }

        return sb.toString();
    }
}
