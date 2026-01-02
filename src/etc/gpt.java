package etc;

import java.time.LocalDateTime;
import java.util.*;

public class gpt {
    public static int[] solution(int[] ball, int[] order) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int b : ball) deque.addLast(b);

        Set<Integer> wait = new HashSet<>();
        List<Integer> answer = new ArrayList<>();

        int idx = 0;

        while (!deque.isEmpty()) {
            // order를 끝까지 확인
            while (idx < order.length &&
                    deque.peekFirst() != order[idx] &&
                    deque.peekLast() != order[idx]) {
                wait.add(order[idx]);
                idx++;
            }

            // order에서 바로 꺼낼 수 있는 경우
            if (idx < order.length) {
                remove(deque, order[idx], answer);
                idx++;
            }

            // 보류된 것 처리 (ball 기준)
            boolean progressed;
            do {
                progressed = false;

                if (!deque.isEmpty() && wait.contains(deque.peekFirst())) {
                    int v = deque.pollFirst();
                    wait.remove(v);
                    answer.add(v);
                    progressed = true;
                } else if (!deque.isEmpty() && wait.contains(deque.peekLast())) {
                    int v = deque.pollLast();
                    wait.remove(v);
                    answer.add(v);
                    progressed = true;
                }

            } while (progressed);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void remove(Deque<Integer> deque, int value, List<Integer> answer) {
        if (deque.peekFirst() == value) {
            deque.pollFirst();
        } else {
            deque.pollLast();
        }
        answer.add(value);
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        int[] ball = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] order = {5, 6, 4, 7, 3, 8, 2, 9, 1, 10};

        System.out.println(Arrays.toString(solution(ball, order)));
        // [24, 13, 9, 2, 11]
        System.out.println(LocalDateTime.now());
    }
}
