package programmers.study.sort;

import java.util.HashSet;
import java.util.Set;

public class PickToAndAddThem {
    /**
     * 문제 풀이
     * 이번 문제에서는 만들 수 있는 모든 정수를 중복 없이 만들고 정렬해야 합니다.
     * 만든 정수의 중복을 제거하는 데 Set을, 정렬한 후 배열로 반환하는 데 Stream을 사용해봅시다.
     */

    public int[] solution(int[] numbers) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                result.add(numbers[i] + numbers[j]);
            }
        }
        return result.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}
