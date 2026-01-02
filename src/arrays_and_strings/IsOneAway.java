package arrays_and_strings;

public class IsOneAway {
    public static void main(String[] args) {
        System.out.println(isOneAway("pal", "pale"));
        System.out.println(isOneAway("pale", "pal"));
        System.out.println(isOneAway("pale", "bale"));

        System.out.println(isOneAway("pal", "pales"));
        System.out.println(isOneAway("pale", "pel"));
        System.out.println(isOneAway("pale", "bake"));
    }
    private static boolean isOneAway(String s1, String s2) {
        String ss, ls;
        if ( s1.length() < s2.length() ) {
            ss = s1;
            ls = s2;
        } else {
            ss = s2;
            ls = s1;
        }
        if ( ls.length() - ss.length() > 1 ) return false;
        boolean flag = false;
        for(int i = 0, j = 0; i < ss.length(); i++, j++) {
            if ( ss.charAt(i) != ls.charAt(j) ) {
                if ( flag ) {
                    return false;
                }
                flag = true;
                if ( ss.length() != ls.length() ) {
                    j++;
                }
            }
        }
        return true;
    }
}
