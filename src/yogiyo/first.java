package yogiyo;

public class first {
    public static boolean solution(String S) {
        boolean result = true;

        if (S.length() < 3) {
            result = false;
        }

        if (S.length() == 3 && !S.matches("\\d+")) {
            result = false;
        }

        String[] strings = S.split("-");

        for(String str : strings) {
            if (str.length() != 3 || !str.matches("\\d+")) {
                result = false;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String S = "123-123 123";
        System.out.println(solution(S));
    }
}
