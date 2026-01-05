package programmers.study.character_array;

public class CountOfPAndY {
    /**
     * 문제 풀이 흐름
     * 1. 문자열을 모두 소문자로 변환
     * 2. "p"의 개수 세기
     *  A. 문자열에 등장하는 모든 "p"를 빈 문자열 ""로 치환
     *  B. 원본 문자열과 변환된 문자열의 길이 차이가 p의 개수
     * 3. 2와 같은 방식으로 y의 개수 세기
     * 4. 구한 p의 개수와 y의 개수 비교
     */

    boolean solution(String s) {
        s = s.toLowerCase();

        int ps = s.length() - s.replace("p", "").length();
        int ys = s.length() - s.replace("y", "").length();
        return ps == ys;
    }

    boolean solution2(String s) {
        s = s.toLowerCase();
        if (!s.contains("p") && !s.contains("y")) {
            return true;
        }
        int pCnt = 0;
        int yCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == 'p') pCnt++;
            else if (c == 'y') yCnt++;
            else continue;
        }
        return pCnt == yCnt;
    }

    boolean solution3(String s) {
        s = s.toLowerCase();
        return s.chars().filter(c -> c == 'p').count() == s.chars().filter(c -> c == 'y').count();
    }
}
