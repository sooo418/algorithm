package programmers.study.character_array;

public class StringHandlingBasic {
    /**
     * 문제 풀이
     * 우선 문자열이 숫자로만 구성되어야 하므로 [0-9]를 이용할 수 있습니다. 여기에 길이가 4 또는 6이라는 조건을 추가하면
     * [0-9]{4}|[0-9]{6}과 같은 정규표현식이 만들어집니다. 이를 이용하여 문자열을 검사하고 그 결괏값을 반환하면 됩니다.
     */

    // 정규표현식을 이용한 방법
    public boolean solution(String s) {
        // [0-9] 와 \\d는 같은 정규표현식
        return s.matches("\\d{4}|\\d{6}");
    }

    // 내장 라이브러리를 이용한 방법
    public boolean solution2(String s) {
        if (s.length() != 4 && s.length() != 6) return false;

        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }

        return true;
    }
}
