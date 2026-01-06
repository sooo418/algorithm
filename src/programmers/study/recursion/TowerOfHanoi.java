package programmers.study.recursion;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoi {
    /**
     * 문제 풀이
     * 하노이의 탑은 재귀 호출로 해결할 수 있는 대표적인 문제입니다.
     * 이 문제를 반복문이나 다른 방식으로 해결하려고 한다면 굉장히 어렵게 느껴집니다.
     * 하지만 재귀를 이용한다면 아주 직관적이고 간단하게 해결 할 수 있습니다.
     *
     * 재귀 정의
     * 1. 상태
     *  하노이의 탑에서 제시된 문제는 원반 n개를 기둥 1에서 기둥 3으로 옮기는 과정입니다.
     *  여기에서 문제를 정의하는 총 3개의 변수를 잡을 수 있습니다.
     *      1. 옮기려는 원반의 개수 n
     *      2. 원반이 현재 위치한 기둥 from
     *      3. 원반이 이동해야 하는 기둥 to
     *  이렇게 3개의 변수가 상태를 구성합니다.
     *  따라서 하노이의 탑 문제에서 상태는 (n, from, to)로 잡을 수 있습니다.
     *  이렇게 할 경우 제시된 문제인 원반 n개를 기둥 1에서 기둥 3으로 옮기는 과정은 (n, 1, 3)으로 나타낼 수 있습니다.
     *  따라서 하노이의 탑 문제의 재귀 상태는 다음과 같이 정의됩니다.
     *      (n, from, to) = 원반 n개를 기둥 from에서 기둥 to로 옮기는 과정
     *
     * 2. 종료 조건
     *  하노이의 탑 문제에서 가장 작은 부분 문제는 원반을 고민 없이 바로 옮길 수 있는 '원반을 1개만 옮길 때'가 됩니다.
     *  이 경우 추가적인 부분 문제없이 바로 원반을 원하는 기둥으로 옮길 수 있습니다.
     *  이는 다음과 같이 표현할 수 있습니다.
     *      (1, from, to) = [[from, to]]
     *  이 조건을 살펴보면 원반 개수인 n에 1이 고정되어 있고, 기둥인 from과 to는 변수 상태 그대로 입니다.
     *  따라서 원반 개수가 1이며, 기둥 위치는 상관없는 조건이 됩니다.
     *  따라서 "원반 1개를 from에서 to로 옮기는 부분 문제"는 바로 'from에서 to로 원반을 옮기면 된다'는 의미가 됩니다.
     *  
     * 3. 점화식
     *  이제 상태를 가장 작은 상태로 전이시켜 줄 점화식을 세워얗 합니다.
     *  점화식을 간단히 찾을 수 있으면 좋겠지만 찾기 힘든 문제가 많습니다.
     *  이때는 제시된 문제를 해결할 수 있는 가장 큰 상태와 종료 조건에서 찾은 가장 작은 상태를 이용하여 유추해볼 수 있습니다.
     *  제시된 문제를 나타내는 상태는 (n, from, to)이며 종료 조건에서 찾은 가장 작은 상태는 (1, from, to)입니다.
     *  n 변수가 1로 수렴하려면 n 값이 점점 줄어들어야 함을 알 수 있습니다.
     *  따라서 상태 변수 n이 n-1로 전이되어야 한다고 가정해보고 이를 이용하여 부분 분제를 해결할 수 있는 점화식을 세울 수 있을지 살펴봅시다.
     *  즉, (n, from, to)를 나타내는 부분 문제를 (n-1, from, to)를 이용하여 풀 수 있는지 확인해야 합니다.
     *  종료 조건에서는 n 값이 1인 것만 포함됩니다.
     *  기둥 위치는 from과 to는 조건에 포함되지 않으므로 굳이 하나의 부분 문제와 여기에서 전이된 또 다른 부분 문제가 같은 기둥을 사용할 필요는 없습니다.
     *  따라서 우리 가정은 다음과 같습니다.
     *      원반 n개를 이동시키는 부분 문제는 원반 n-1개를 이동시키는 부분 문제로 해결할 수 있다.
     *  이렇게 적고 보니 굉장히 당연한 이야기입니다.
     *  원반 n-1개를 이동시킬 수 있다면 맨 위 원반 n-1개는 빈 기둥에 옮기고 가장 아래 원반은 목표 기둥에 옮긴 후,
     *   빈 기둥에 옮겨 놓았던 n-1개의 원반은 목표 기둥에 옮겨 주면 됩니다.
     *  따라서 점화식은 다음과 같습니다.
     *      (n, from, to) = (n-1, from, empty) + (1, from, to) + (n-1, empty, to) (단 empty = 6 - from - to)
     */

    private List<int[]> hanoi(int n, int from, int to) {
        // 종료 조건
        if (n == 1) return List.of(new int[] {from, to});

        // 점화식 구현
        int empty = 6 - from - to;
        List<int[]> result = new ArrayList<>();
        result.addAll(hanoi(n - 1, from, empty));
        result.addAll(hanoi(1, from, to));
        result.addAll(hanoi(n - 1, empty, to));

        return result;
    }

    public int[][] solution(int n) {
        return hanoi(n, 1, 3).toArray(new int[0][]);
    }

    /**
     * 문제 풀이 최적화
     * 위의 코드는 하노이의 탑 문제를 잘 풀어내지만 매 부분 문제마다 구해 놓은 과정을 전부 순회하며 이어 붙이기 때문에 효율적이지 않습니다.
     * 하노이의 탑 문제처럼 과정을 전부 기록해야 하는 문제의 답을 메서드의 반환을 이용하여 구하려고 하면 이렇게 비효율적인 구현이 될 수 있습니다.
     * 이 경우에는 메서드의 반환 대신 매개변수를 이용하여 과정을 기록할 수 있습니다.
     * 이 문제는 과정을 List<int[]>로 다루었습니다.
     * 이렇게 과정을 기록하는 변수를 메서드의 매개변수로 넘겨주면 다음과 같이 hanoi() 메서드를 다시 작성할 수 있습니다.
     */

    private void hanoi(int n, int from, int to, List<int[]> process) {
        if (n == 1) {
            process.add(new int[] {from, to});
            return;
        }

        int empty = 6 - from - to;

        hanoi(n - 1, from, empty, process);
        hanoi(1, from, to, process);
        hanoi(n - 1, empty, to, process);
    }

    public int[][] solution2(int n) {
        List<int[]> process = new ArrayList<>();
        hanoi(n, 1, 3, process);
        return process.toArray(new int[0][]);
    }
    /*
    process.toArray(new int[0][]) -> Collection.toArray(T[] a) 로 반환타입은 T[] 이다.
    1. 전달한 배열 a의 길이가 process.size() 보다 더 크거나 같으면 아래와 같이 동작한다.
        A. a의 배열을 그대로 재사용
        B. 거기에 원소를 채워서 반환
    2. 전달한 배열 a의 길이가 process.size() 보다 작으면 아래와 같이 동작
        A. 새 배열을 내부에서 새로 생성
        B. 정확한 크기로 만들어서 반환

    new int[0][]를 넣는 이유
        - int[0][] 의 구조
            1. 길이 0짜리
            2. int[]를 원소로 갖는 배열
        즉, 타입은 int[][]
        - 핵심
            1. 타입만 알려주면 된다.
                Java는 제네릭 타입을 직접 만들 수 없음
            2. size를 계산할 필요가 없다.
                process.size()를 굳이 호출하지 않아도 되고 코드가 간결함.
            3. Java7 부터는 toArray(new T[0]) 패턴이 내부적으로 최적화되어 성능이 다를게 없음(오히려 권장)
        - 오해
            new int[0][] 배열이 그대로 반환된다?
                1. 절대 아님
                2. 무조건 새 배열 생성
        - 왜 toArray()를 사용하지 않나?
            int[][] result = (int[][]) process.toArray(); 런타입 에러 발생 => ClassCastException
        그래서 toArray(new int[0][]); 가 좋은 코드이다.
            - 타입 안정성
            - 컴파일 타임 체크
            - 캐스팅 불필요
    */
}
