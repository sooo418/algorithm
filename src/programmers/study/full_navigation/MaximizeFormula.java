package programmers.study.full_navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MaximizeFormula {
    /**
     * 문제 풀이
     * 이 문제에서는 3개의 연산자 +, -, *가 등장합니다.
     * 이 세 연산자의 우선순위는 전체 수식의 결과가 가장 큰 절댓값으로 계산되는 순서대로 정해야 합니다.
     * 일반적으로 이렇게 여러 원소의 순서를 정하는 것은 재귀로 구현할 수 있습니다.
     * 이 문제에는 순서를 정해줄 원소인 연산자가 최대 3개밖에 없으므로 가능한 순서 개수가 6개뿐입니다.
     * 따라서 굳이 재귀를 이용하지 않아도 다음과 같이 직접 우선순위를 정해줄 수 있습니다.
     */
    private static final String[][] precedences = {
            "+-*".split(""),
            "+*-".split(""),
            "-+*".split(""),
            "-*+".split(""),
            "*+-".split(""),
            "*-+".split("")
    };
    /**
     * 이렇게 경우의 수가 많지 않을 때는 재귀를 고안하고 구현해내기보다 직접 모든 경우의 수를 나열하는 것이 더욱 빠를 수 있습니다.
     */

    /**
     * 문제의 입력에서 연산자는 문자열로 표현되어 있으므로 이를 직접 연산에 적용할 수는 없습니다.
     * 따라서 다음과 같이 두 피연산자와 연산자를 입력받아 연산 결과를 반환하는 calculate() 메서드를 작성해봅시다.
     * @param lhs
     * @param rhs
     * @param op
     * @return
     *
     * 이 메서드로 수식을 계산해야 합니다.
     * 일반적으로 수식을 계산할 때는 스택 자료 구조를 이용하여 중위 표기법을 연산자 우선순위에 맞게 후위 표기법으로 변환한 후 계산합니다.
     * 이 방법은 수식을 효율적으로 계산할 수 있지만 스택을 이용해야 하고, 변환 방법을 정확히 알고 구현해야 합니다.
     * 하지만 이 문제는 숫자와 연산자 개수가 크지 않으므로 조금 비효율적이더라도 쉽게 구현할 수 있습니다.
     *
     * 우선 순위에 따라 연산자를 하나씩 처리하면 전체 수식을 순회하며 연산자를 하나씩 계산해나갈 수 있습니다.
     * 예를 들어 수식 100 - 200 * 300 - 500 + 20이 입력되었다고 합시다.
     * 연산자 우선순위가 +,-,* 순이라면 먼저 수식 전체를 순회하고, + 연산자를 계산하면 100 - 200 * 300 - 520으로 계산할 수 있습니다.
     * 다음 연산자는 -이므로 이를 다시 수식 전체를 순회하면서 적용하여 -100 * -220으로 계산합니다.
     * 마지막으로 * 연산자를 계산하여 22000으로 계산합니다.
     * 이 방법을 사용할 때는 수식에 등장하는 연산자 하나당 수식 전체를 순회해야 합니다.
     * 수식 길이가 N이라면 최대 등장할 수 있는 연산자 개수는 모든 숫자가 한 자릿수일 때로, N/2입니다.
     * 또 하나의 연산자를 계산할 때 해당 연산자 뒤에 있는 문자들을 배열에서 당겨 와야 합니다.
     * 이 과정은 O(N)이 소요되므로 연산자 우선순위에 따라 수식을 계산하는 시간 복잡도는 O(N^3)이 됩니다.
     * 등장하는 연산자 개수를 M이라고 하면, 순서를 정하는 경우의 수는 M!가 되어 전체 시간 복잡도는 O(N^3M!)가 됩니다.
     * N의 최댓값은 100, M의 최댓값은 3이므로 이를 대입해서 계산하면 6,000,000이 되어 제한 시간 안에 충분히 계산되는 시간 복잡도를 얻을 수 있습니다.
     *
     * ※ 배열이나 ArrayList를 사용하면 하나의 연산자를 계산할 때 해당 연산자 뒤에 있는 모든 문자를 당겨 와야 하므로 추가적인 O(N)이 발생합니다.
     * 이 문제는 N이 작기 때문에 시간 복잡도에 문제없었습니다.
     * LinkedList를 사용하면 리스트 중간에 원소 삽입이나 제거가 O(1) 만에 되므로 LinkedList와 iterator를 사용하면 전체 시간 복잡도를 O(N^2M!)로 줄일 수 있습니다.
     */
    private long calculate(long lhs, long rhs, String op) {
        return switch(op) {
            case "+" -> lhs + rhs;
            case "-" -> lhs - rhs;
            case "*" -> lhs * rhs;
            default -> 0;
        };
    }

    private long calculate(List<String> tokens, String[] precedence) {
        /*
        * 이 메서드에서는 연산자를 우선순위가 높은 순서대로 순회하며 수식을 계산합니다. 따라서 다음과 같이 연산자를 순회하며 수식을 검사합니다.
        * */
        for (String op : precedence) {
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(op)) {
                    long lhs = Long.parseLong(tokens.get(i - 1));
                    long rhs = Long.parseLong(tokens.get(i + 1));
                    long result = calculate(lhs, rhs, op);
                    /*
                     * 이를 수식에 다시 반영해야 합니다.
                     * 한 연산자의 계산이 완료되면 다음과 같이 리스트에서 뒷부분의 원소들을 당긴 후 연산 결과를 넣어 주어야 합니다.
                     *           |   i-1   |   i   |   i+1   |...
                     * tokens    |   10    |   +   |   20    |   -   |   100   |...
                     *                   ▽
                     *           |   i-1   |   i   |...
                     * tokens    |   -     |   100 |...
                     *                   ▽
                     *           |   i-1   |   i   |...
                     * tokens    |   30    |   -   |   100   |...
                     * 이는 tokens의 i - 1번째 원소를 반복해서 세 번 지우고, i - 1 위치에 연산 결과를 삽입해서 구현할 수 있습니다.
                     * 이후에는 리스트의 뒷부분을 앞으로 당겼기 때문에 쉰회하는 인덱스 i 또한 앞으로 당겨 옵니다.
                     * */
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.remove(i - 1);
                    tokens.add(i - 1, String.valueOf(result));
                    i -= 2;
                }
            }
        }
        return Long.parseLong(tokens.get(0));
    }

    public long solution(String expression) {
        /*
        * 먼저 하나의 문자열로 구성된 수식을 여러 개의 토큰으로 분리해야 합니다.
        * 문자열의 split() 메서드를 사용하여 연산자를 기준으로 문자열을 분리한다면 연산자와 숫자는 나눌 수 있지만 분리 기준인 연산자는 잃게 됩니다.
        * 따라서 이 문제에서는 StringTokenizer 클래스로 다음과 같이 문자열을 분리합니다.
        * */
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*", true);
        List<String> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        /*
        * solution() 메서드에서는 수식을 구성한 후 앞서 작성한 calculate() 메서드로 가장 큰 절댓값을 찾아야 합니다.
        * 이를 위해 다음과 같이 최댓값을 저장할 max 변수를 정의하고, 연산자 우선순위를 순회합니다.
        * */
        long max = 0;
        for (String[] precedence : precedences) {
            long value = Math.abs(calculate(new ArrayList<>(tokens), precedence));
            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}
