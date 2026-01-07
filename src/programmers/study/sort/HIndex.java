package programmers.study.sort;

import java.util.Arrays;

public class HIndex {
    /**
     * 문제 풀이
     * citations의 길이를 가장 큰값 h를 잡고,
     * stream.filter() 메서드를 이용해서 h보다 큰 값들인 원소들로만 이루어진 배열의 길이를 받아 길이가 h보다 같거나 크다면 반환하도록 구현
     * 간단하지만 시간복잡도가 O(N^2)로 비효율적임
     */
    public int solution(int[] citations) {
        for (int h = citations.length; h >= 1; h--) {
            int finalH = h;
            int length = Arrays.stream(citations).filter(value -> value >= finalH).toArray().length;
            if (length >= h) return h;
        }
        return 0;
    }
    /**
     * 문제 풀이(정렬)
     * citations를 오른차순으로 정렬합니다. -> [0, 1, 3, 5, 6]
     * h는 0~5 중 하나입니다. 가장 먼저 h = 5를 만족하는지 검사해봅시다.
     * h = 5가 되려면 5회 이상 인용된 논문이 5개 이상이어야 하지만, 실제로 5회 이상 인용된 논문은 2개로 5는 h의 조건을 만족하지 못합니다.
     * 이를 정렬된 논문 인용 횟수를 이용하여 모든 인용 횟수를 순회하지 않고 구하는 방법을 알아보겠습니다.
     *
     * 오름차순으로 정렬되었기 때문에 특정 인덱스의 인용 횟수가 x일 때 이보다 뒤에 있는 원소는 모두 x회 이상 인용되었습니다.
     * 따라서 5회 이상 인용된 논문이 5개 이상인지 검사하려면 뒤에서 5번째 논문이 5회 이상 인용되었는지 검사합니다.
     * 이 위치의 논문이 5회 이상 인용되었다면 그 이후의 논문은 모두 5회 이상 인용되었다는 것이 확실하기 때문입니다.
     * 실제로 이 방법을 이용하여 정렬된 논문 인용 횟수에서 h = 5를 검사해봅시다.
     * 뒤에서 5번째 인용 횟수는 0입니다. 0은 5보다 작으므로 5는 h의 조건을 만족시키지 못합니다.
     * 마찬가지로 h = 4를 검사해봅시다. 뒤에서 4번째에 있는 인용 횟수는 1로, 4보다 작으므로 4도 h의 조건을 만족시키지 못합니다.
     * h = 3을 검사해봅시다. 뒤에서 3번째에 있는 인용 횟수는 3으로, 3이상이므로 3은 h의 조건을 만족시킵니다.
     * 이를 구현하려면 우선 입력받은 배열 citations를 정렬해주어야 합니다.
     * 한 번 정렬시켜 놓으면 h를 검사할 때마다 사용할 수 있으므로 solution() 메서드의 가장 첫 번째 줄에서 정렬을 수행합니다.
     */
    public int solution2(int[] citations) {
        Arrays.sort(citations);
        for (int h = citations.length; h >= 1; h--) {
            if (isValid(citations, h)) return h;
        }
        return 0;
    }

    /**
     * 이제 isValid() 메서드에서는 앞의 내용을 구현합니다.
     */
    private boolean isValid(int[] citations, int h) {
        int index = citations.length - h;
        return citations[index] >= h;
    }
    /**
     * 이 풀이의 시간 복잡도를 계산해봅시다. 우선 정렬하는데 O(N logN)의 시간 복잡도가 소요됩니다.
     * 반복문 N번 순회하므로 O(N)이 소요되며, 각 반복마다 isValid() 검사에는 상수 시간 O(1)이 소요됩니다.
     * 따라서 전체 시간 복잡도는 O(N logN) + O(N) * O(1) = O(N logN)이 됩니다.
     * 이 시간 복잡도는 약 13만 정도의 값을 가지므로 제한 시간 안에 충분히 동작하는 풀이입니다.
     */
}
