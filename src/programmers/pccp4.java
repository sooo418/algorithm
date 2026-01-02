package programmers;

import java.util.*;

public class pccp4 {
    public static String[] solution(String[] expressions) {
        int xCnt = 0;
        Set<Integer> inputValueSet = new HashSet<>();
        // 가능한 진법 후보: 2진법부터 9진법까지
        Set<Integer> possibleBases = new HashSet<>();
        for (int i = 2; i <= 9; i++) {
            possibleBases.add(i);
        }

        // 진법 필터링
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            String a = parts[0];
            String b = parts[2];
            String c = parts[4];

            inputValueSet.add(findMaxDigit(a));
            inputValueSet.add(findMaxDigit(b));
            inputValueSet.add(findMaxDigit(c));
        }

        int maxDigit = Collections.max(inputValueSet);
        possibleBases.removeIf(digit -> digit <= maxDigit);

        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            String a = parts[0];
            String op = parts[1];
            String b = parts[2];
            String c = parts[4];

            if (!c.equals("X")) { // 이미 결과가 주어진 경우 진법 필터링
                Set<Integer> validBases = new HashSet<>();
                for (int base : possibleBases) {
                    if (isValidInBase(a, base) && isValidInBase(b, base) && isValidInBase(c, base)) {
                        int aDec = Integer.parseInt(a, base);
                        int bDec = Integer.parseInt(b, base);
                        int cDec = Integer.parseInt(c, base);

                        if ((op.equals("+") && aDec + bDec == cDec) || (op.equals("-") && aDec - bDec == cDec)) {
                            validBases.add(base);
                        }
                    }
                }
                possibleBases.retainAll(validBases); // 유효한 진법만 남김
            } else {
                xCnt++;
            }
        }

        // 결과 계산
        String[] results = new String[xCnt];
        int i = 0;
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            String a = parts[0];
            String op = parts[1];
            String b = parts[2];
            String c = parts[4];

            if (c.equals("X")) { // 결과가 비어 있는 경우
                int minBase = Collections.min(possibleBases); // 가능한 최소 진법
                Set<String> possibleResults = new HashSet<>();
                String minBaseResult = null;

                for (int base : possibleBases) {
                    if (isValidInBase(a, base) && isValidInBase(b, base)) {
                        int aDec = Integer.parseInt(a, base);
                        int bDec = Integer.parseInt(b, base);
                        int resultDec = op.equals("+") ? aDec + bDec : aDec - bDec;

                        String resultBase = Integer.toString(resultDec, base);
                        possibleResults.add(resultBase);
                        if (base == minBase) {
                            minBaseResult = resultBase; // 최소 진법에서 계산된 결과 저장
                        }
                    }
                }

                if (possibleResults.size() > 1 && Integer.parseInt(minBaseResult) > minBase) {
                    results[i] = a + " " + op + " " + b + " = ?";
                } else if (minBaseResult != null) {
                    results[i] = a + " " + op + " " + b + " = " + minBaseResult;
                } else {
                    results[i] = a + " " + op + " " + b + " = ?";
                }
                i++;
            }
        }

        return results;
    }

    public static int findMaxDigit(String value) {
        int max = 1;

        // 문자열의 각 문자를 순회하며 숫자로 변환 후 비교
        for (char ch : value.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return max;
            }
            int digit = ch - '0'; // 문자 숫자를 정수로 변환
            if (digit > max) {
                max = digit;
            }
        }

        return max;
    }

    public static boolean isValidInBase(String value, int base) {
        for (char c : value.toCharArray()) {
            if (Character.digit(c, base) == -1) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String[] expressions = {
                "10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"
        };

        String[] result = solution(expressions);
        for (String res : result) {
            System.out.println(res);
        }
    }

}
