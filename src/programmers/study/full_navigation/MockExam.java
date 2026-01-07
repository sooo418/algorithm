package programmers.study.full_navigation;

import java.util.stream.IntStream;

public class MockExam {
    /**
     * 문제 풀이
     *
     * 규칙 찾기
     * 1번 수포자: 1, 2, 3, 4, 5
     * 2번 수포자: 2, 1, 2, 3, 2, 4, 2, 5
     * 3번 수포자: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
     *
     * 세 명 모두 각각의 규칙으로 문제를 찍습니다.
     * 하지만 시험의 정답은 특별한 규칙이 없으므로 각 수포자의 정답 개수는 직접 세어 보지 않으면 알 수 없습니다.
     *
     * 완전 탐색
     * 세 명의 수포자가 각각 몇 개의 정답을 맞혔는지 살표본다고 할 때, 시간 복잡도가 어떻게 계산되는지 살펴봅시다.
     * 한 명의 수포자가 찍은 모든 문제의 정답을 검사하려면 시험에 포함된 문제 개수에 비례한 시간 복잡도가 필요합니다.
     * 문제 개수를 N이라고 했을 때 한 명의 수포자에 대해 O(N)이 소요됩니다.
     * 수포자들의 수를 M이라고 한다면, O(N)이 M번 반복되므로 O(NM)이 소요됩니다.
     * 문제 조건에 따라 N은 최대 10,000이고, M은 3이므로, 시간 복잡도 로 계산되는 최댓값은 30,000이 됩니다.
     * 이는 100,000,000보다 훨씬 작은 값이므로 충분히 시간 제한 안에 들어오는 풀이법입니다.
     */

    private static final int[][] RULES = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    private int getPicked(int person, int problem) {
        int[] rule = RULES[person];
        int index = problem % rule.length;
        return rule[index];
    }

    public int[] solution(int[] answers) {
        int[] corrects = new int[3];
        int max = 0;

        for (int problem = 0; problem < answers.length; problem++) {
            int answer = answers[problem];

            for (int person = 0; person < 3; person++) {
                int picked = getPicked(person, problem);
                if (answer == picked) {
                    if (++corrects[person] > max) {
                        max = corrects[person];
                    }
                }
            }
        }
        /*
        * maxCorrects를 final로 선언한 이유는 Stream의 filter() 메서드에 maxCorrects가 참조되는데,
        * 자바에서는 이렇게 람다 혹은 익명 메서드로 전달되는 메서드에서 외부 변수를 참조할 때는 해당 변수 값을 수정하면 안 됩니다.
        * 따라서 final 키워드를 붙여 값이 수정되지 않는다는 것을 명시합니다.
        *  */
        final int maxCorrects = max;
        return IntStream.range(0, 3)
                .filter(i -> corrects[i] == maxCorrects)
                .map(i -> i + 1)
                .toArray();
    }
}
