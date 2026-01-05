package programmers.study.character_array;

import java.util.stream.IntStream;

public class ReverseNaturalNumber {
    /**
     * 문제 풀이 흐름
     * 1. 입력받은 숫자를 문자열로 변환합니다.
     * 2. 문자열을 뒤집습니다.
     * 3. 뒤집힌 문자열을 문자의 배열로 변환합니다.
     * 4. 배열의 각 문자를 정수로 변환합니다.
     */

    public static int[] solution(long n) {
        // 1. 입력받은 숫자를 문자열로 변환합니다.
        String str = Long.toString(n);

        // 2. 문자열을 뒤집습니다.
        String reversed = new StringBuilder(str).reverse().toString();

        // 3. 뒤집힌 문자열을 문자의 배열로 변환합니다.
        char[] arr = reversed.toCharArray();

        // 4. 배열의 각 문자를 정수로 변환합니다.
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Character.getNumericValue(arr[i]);
        }

        return result;
    }

    public static int[] solution2(long n) {
        // programmers 다른 사람 풀이(대단한데?)
        return new StringBuilder().append(n).reverse().chars().map(Character::getNumericValue).toArray();
    }

    public static void main(String[] args) {
        for (int i : solution2(4521783)) {
            System.out.print(i);
        }
    }
}
