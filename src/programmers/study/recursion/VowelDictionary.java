package programmers.study.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VowelDictionary {
    /**
     * 문제 풀이 흐름
     * 1. 상태
     *  (word) => 이전까지 작성한 word
     * 2. 종료 조건
     *  (길이가 5인 word) = word
     * 3. 점화식
     *  (word) = [word] + (word + 'A') + (word + 'E') + (word + 'I') + (word + 'O') + (word + 'U')
     */

    /**
     * 재귀를 진행하면서 생성되는 단어들은 이미 사전 순으로 정렬되어 있습니다.
     * 이는 재귀의 특성인데, 재귀는 하나의 상태가 여러 상태로 전이될 때 종료 조건에 도달할 때까지 첫번째 전이를 계속합니다.
     * 이 경우 A, E, I, O, U를 이용하여 5개의 상태로 전이되는데, 첫 번째 전이인 A를 붙이는 전이가 종료 조건에 도달할 때까지 계속되는 것입니다.
     * 따라서 A만으로 구성된 단어들이 가장 먼저 생성되고, 가장 마지막 재귀부터 다음 전이를 성택합니다.
     *
     * 이런 재귀의 특성 덕분에 우리는 만들 수 있는 단어를 생성하는 것뿐 아니라, 사전 순으로 정렬된 단어의 리스트도 얻게 되었습니다.
     *
     * word가 AAAA일때 words에는 AAAA가 첫 인자로 들어가고 다음 재귀호출에서 종료 조건이 되어 AAAAA가 반환되어 words에 추가되고,
     * 다음 AAAAE, AAAAI, AAAAO, AAAAU가 추가된다.
     * 그 이후 words = {AAAA, AAAAA, AAAAE, AAAAI, AAAAO, AAAAU}를 반환한다.
     * 이런 재귀의 특성 때문에 순차적으로 words에 문자들이 담기게 된다.
     */

    private static final char[] CHARS = "AEIOU".toCharArray();

    private List<String> generate(String word) {
        // 종료 조건
        List<String> words = new ArrayList<>();
        words.add(word);

        if (word.length() == 5) return words;

        // 점화식
        for (char c : CHARS) {
            words.addAll(generate(word + c));
        }

        return words;
    }

    public int solution(String word) {
        return generate("").indexOf(word);
    }

    /**
     * 문제 풀이 최적화
     * 하노이의 탑 문제와 마찬가지로 모음 사전 문제 또한 리스트를 이용하여 기록하는 문제입니다.
     * 매 부분 문제마다 불필요한 리스트를 복사하는 것을 막고자 하노이의 탑 문제에서 적용했던 것과 같은 방식을 이용하여 최적합할 수 있습니다.
     */

    private void generate(String word, List<String> words) {
        words.add(word);

        if (word.length() == 5) return;
        for (char c : CHARS) {
            generate(word + c, words);
        }
    }

    public int solution2(String word) {
        List<String> words = new ArrayList<>();
        generate("", words);
        return words.indexOf(word);
    }
}
