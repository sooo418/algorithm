package programmers.study.full_navigation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindPrimeNumbers {
    /**
     * 문제 풀이
     * 재귀호출을 사용하여 풀어내는 모음 사전 문제와 비슷.
     * 재귀를 이용하여 풀어봅시다.
     *
     * 재귀 정의
     * 1. 상태
     *  주어진 숫자들을 사용하여 숫자를 조합해 나가야 합니다.
     *  하나의 숫자를 계속해서 이어 붙여 나가야 하므로 상태에는 다음과 같이 2개의 변수가 포함됩니다.
     *      1. 지금까지 만들어 놓은 숫자 acc
     *      2. 사용할 수 있는 종이 조각들 numbers
     *  지금까지 만들어 놓은 숫자 acc는 계속해서 숫자를 하나씩 이어 붙여 나가는데 필요하고, 사용할 수 있는 종이 조각들 numbers는
     *  아직 사용하지 않은 종이 조각을 사용하는 데 필요합니다.
     *  따라서 이 재귀의 상태는 (acc, number)로 표기할 수 있고, 이 상태를 나타내는 부분 문제는 acc로 시작해서 numbers로 만들 수 있는
     *  숫자 중 소수의 집합이 됩니다.
     *
     * 2. 종료 조건
     *  모든 경우를 확인해보아야 하므로 더 이상 숫자를 만들 수 없을 때 재귀가 종료됩니다.
     *  더 이상 숫자를 만들 수 없을 때는 모든 종이 조각을 사용했을 때, 즉 numbers가 비어 있을 때입니다.
     *  따라서 이 재귀의 종료 조건은 (acc, 0)입니다. numbers는 숫자들의 배열 혹은 집합으로, 0으로 표기해서 비어 있다는 것을 나타냈습니다.
     *  이때는 만들 수 있는 숫자가 acc밖에 없습니다. 따라서 acc가 소수인지에 따라 다음과 같이 종료 조건이 정의됩니다.
     *      (acc, 0) = {
     *          {acc}   -> acc가 소수인 경우
     *          {}      -> acc가 소수가 아닌 경우
     *      }
     *
     * 3. 점화식
     *  만들어 놓은 숫자 acc에 사용할 수 있는 종이 조각 중 하나를 이어 붙여 상태를 전이시켜 나가야 합니다.
     *  또 acc 자체가 소수인 경우도 확인해야 합니다.
     *  따라서 점화식은 acc의 소수 검사와 전이된 상태의 결과를 모두 합친 다음 형태가 됩니다.
     *      (acc, numbers) = {
     *          {acc}   -> acc가 소수인 경우
     *          {}      -> acc가 소수가 아닌 경우
     *      } (acc * 10 + n, numbers - n)
     *  이 수식을 살펴보면 + 연산을 기준으로 앞부분과 뒷부분으로 나뉘어 있습니다.
     *  앞부분은 acc의 소수 여부에 따라 acc가 소수 집합에 포함될지 결정됩니다.
     *  뒷부분은 numbers에 포함된 모든 정수에서 acc 뒤에 이어 붙이고, numbers 집합에서 해당 정수를 제외한 상태로 전이시킵니다.
     *  그리고 전이된 상태의 결과를 모두 더하면 원래 상태의 결과가 됩니다.
     */

    /**
     * 먼저 상태를 이용하여 재귀 메서드 getPrimes()를 같이 작성합니다.
     * 소수의 집합을 반환해야 하므로 반환형은 Set<Integer>로, numbers는 숫자를 쉽게 제거할 수 있도록 List<Integer>로 작성합니다.
     * @param acc
     * @param numbers
     * @return
     */
    private Set<Integer> getPrimes(int acc, List<Integer> numbers) {
        Set<Integer> primes = new HashSet<>();
        // 가장 먼저 acc의 소수 여부에 따라 결과 집합에 포함시킵니다.
        if (isPrime(acc)) primes.add(acc);
        /* 종료 조건은 numbers에 아무 숫자도 포함되지 않은 경우입니다.
        if (numbers.isEmpty()) return primes;
        굳이 numbers.isEmpty()로 메서드를 종료하지 않아도 아래 for 문에서 numbers에 포함된 원소가 없기 때문에 수행되지 않고 primes를 반환합니다.
         */

        /*
        * 다음으로 점화식을 구현해야 합니다.
        * 상태의 결과는 숫자의 집합이 나와야 하고,
        * 전이된 상태에서 반환받은 집합도 모두 합쳐야 하기 때문에 부분 문제의 결과로 사용될 집합을 종료 조건 이후에 정의합니다.*/
        // 다음으로 numbers의 모든 숫자에 대해 순회하며 상태를 전이시킬 준비를 합니다.
        for (int i = 0; i < numbers.size(); i++) {

            // 전이되는 상태의 acc는 뒤에 numbers.get(i)가 이어 붙은 acc * 10 + numbers.get(i)가 됩니다.
            int nextAcc = acc * 10 + numbers.get(i);

            /*
            * 전이되는 상태의 numbers는 방금 사용한 숫자를 제외하고 넘겨주어야 합니다.
            * 인덱스 i번째의 숫자를 사용했으므로 리스트를 복사하고, 해당 인덱스를 삭제합니다.
            * */
            List<Integer> nextNumbers = new ArrayList<>(numbers);
            nextNumbers.remove(i);

            /*
            * 이제 전이 상태가 준비되었으므로 재귀를 이용하여 전이 상태에 대한 부분 문제를 풀고,
            * 그 결과 집합을 현재 풀고 있는 상태의 결과 집합에 합칩니다.
            * */
            primes.addAll(getPrimes(nextAcc, nextNumbers));
        }

        return primes;
    }

    /**
     * acc가 소수인지 검사해야 하므로 다음과 같이 소수 검사를 하는 isPrime()메서드를 작성합니다.
     * 왜 i * i <= n 을 사용할까요?
     *  - i <= √n 까지만 검사하면 충분
     *  - Math.sqrt(n) 호출보다 i * i <= n 이 더 빠르고 안전 (연산 최소화)
     * @param n
     * @return
     */
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * 이제 solution() 메서드에서 이 재귀 메서드를 호출하여 정답을 구해봅시다.
     * 가장 큰 부분 문제는 아직 아무런 숫자도 만들지 않았을 때입니다.
     * 즉, acc는 0이고 numbers에 모든 숫자가 들어 있는 경우가 가장 큰 부분 문제가 됩니다.
     * solution() 메서드의 입력으로는 문자열이 들어오기 때문에, 이를 문자로 분리한 후 숫자의 리스트로 변환해야 합니다.
     * 물론 직접 일일이 구현해도 좋지만, Stream을 사용하면 조금 더 편하게 변환할 수 있습니다.
     * 문자열 String의 chars() 메서드를 사용하면 문자열에 포함된 문자들의 아스키 코드를 나타내는 정수들이 IntStream 형태로 반환됩니다.
     * 여기에 map()을 사용하여 아스키 코드를 정수로 반환합니다.
     * boxed()를 사용하면 int형인 각 원소가 wrapper class인 Integer 클래스로 변환됩니다.
     * 마지막으로 collect() 메서드를 사용하여 List로 변환합니다.
     * 이 과정은 다음과 같이 작성됩니다.
     * @param numbers
     * @return
     */
    public int solution(String numbers) {
        List<Integer> nums = numbers.chars()
                .map(c -> c - '0')
                .boxed()
                .collect(Collectors.toList());

        return getPrimes(0, nums).size();
    }

    /**
     * 문제 풀이 최적화 1
     * Set을 반환으로 사용하는 것은 재귀 호출이 종료될 때마다 모든 원소를 순회하기 때문에 시간 복잡도가 기하급수적으로 증가합니다.
     * 이를 해결하기 위해 다음과 같이 Set을 반환하지 않고 매개변수로 전달해서 불필요한 원소 순회를 방지합니다.
     */

    private void getPrimes(int acc, List<Integer> numbers, Set<Integer> primes) {
        if (isPrime(acc)) primes.add(acc);
        for (int i = 0; i < numbers.size(); i++) {
            int nextAcc = acc * 10 + numbers.get(i);
            List<Integer> nextNumbers = new ArrayList<>(numbers);
            nextNumbers.remove(i);
            getPrimes(nextAcc, nextNumbers, primes);
        }
    }

    public int solution2(String nums) {
        Set<Integer> primes = new HashSet<>();
        List<Integer> numbers = nums.chars()
                .map(c -> c - '0')
                .boxed()
                .collect(Collectors.toList());
        getPrimes(0, numbers, primes);
        return primes.size();

    }

    /**
     * 문제 풀이 최적화 2
     * 지금은 사용할 수 있는 숫자들을 전달하려고 List를 복사하고 원소를 하나씩 제거하고 있습니다.
     * List에 포함된 원소 개수를 N이라고 할 때, List의 복사는 전체 원소를 순회해야 하므로 O(N) 시간 복잡도가 소요됩니다.
     * 또 원소의 삭제는 하나의 원소를 삭제한 후 해당 원소 뒤에 있는 모든 원소를 한 칸씩 앞으로 당겨야 하기 때문에 O(N)이 소요됩니다.
     * 즉, 현재 구현은 numbers 크기만큼 순회하는 for 문에서 O(N),
     * 각 반복마다 리스트를 복사하고 원소를 삭제하는 데 O(N)이 소요되어 하나의 부분 문제를 풀 때 O(N^2)의 시간 복잡도가 소요됩니다.
     * 이는 사용할 수 있는 숫자들만 전달하려고 List를 사용해서 발생한 문제로, 숫자 정보와 사용 여부 정보를 나누어 전달한다면 해결할 수 있습니다.
     * 다음과 같이 List로 전달하는 numbers를 모든 숫자가 저장된 int형 배열 numbers와 각 숫자의 사용 여부를 나타내는 isUsed로 분리합니다.
     */

    private void getPrimes(int acc, int[] numbers, boolean[] isUsed, Set<Integer> primes) {
        if (isPrime(acc)) primes.add(acc);
        /*
        * 이제 모든 숫자를 사용하는 것이 아니라 배열 isUsed에 false로 체크된, 즉 사용하지 않은 숫자들을 골라서 사용해야 합니다.
        * */
        for (int i = 0; i < numbers.length; i++) {
            if (isUsed[i]) continue;

            // nextAcc는 이전과 같이 numbers[i]를 사용하여 쉽게 구할 수 있습니다.
            int nextAcc = acc * 10 + numbers[i];

            // 또 다음과 같이 이번 재귀에서 사용한 숫자를 사용했다고 체크하고 재귀 호출을 수행합니다.
            isUsed[i] = true;
            getPrimes(nextAcc, numbers, isUsed, primes);

            // 마지막으로 재귀 호출이 종료되었을 때는 더 이상 해당 숫자를 사용하지 않으므로 다시 isUsed에 false로 체크해야 합니다.
            isUsed[i] = false;
        }
    }

    public int solution3(String nums) {
        Set<Integer> primes = new HashSet<>();
        int[] numbers = nums.chars().map(c -> c - '0').toArray();
        getPrimes(0, numbers, new boolean[numbers.length], primes);
        return primes.size();
    }
}
