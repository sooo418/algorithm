package arrays_and_strings;

import java.util.HashMap;

public class StringAlgorithm {
    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));
    }
    private static boolean isUnique(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ( ( checker & ( 1 << val ) ) > 0 ) {
                return false;
            }
            checker |= ( 1 << val );
        }
        return true;
    }

    private static boolean isUnique_unicode(String str) {
        HashMap<Integer, Boolean> hashmap = new HashMap<Integer, Boolean>();
        for(int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if ( hashmap.containsKey(c) ) {
                return false;
            }
            hashmap.put(c, true);
        }
        return true;
    }

    private static boolean isUnique_ascii(String str) {
        if ( str.length() > 128 ) return false;
        boolean[] char_set = new boolean[128];
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if ( char_set[val] ) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }
}
