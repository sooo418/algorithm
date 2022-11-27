package arrays_and_strings;

public class PalindromeOfPermutation {
    public static void main(String[] args) {
        System.out.println(isPermutationOfPalindrome("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e fff"));
    }
    private static boolean isPermutationOfPalindrome(String s) {
        int bitVector = createBitVector(s);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }
    private static int createBitVector(String s) {
        int bitVector = 0;
        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }
    private static int toggle(int bitVector, int index) {
        if ( index < 0 ) return bitVector;
        int mask = 1 << index;
        if ( (bitVector & mask) == 0 ) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }
    private static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }
    private static boolean isPermutationOfPalindrome2(String s) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
            if ( x != -1 ) {
                table[x]++;
                if ( table[x] % 2 == 1 ) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }

    private static boolean isPermutationOfPalindrome1(String s) {
        int[] table = buildCharFrequencyTable1(s);
        return checkMaxOneOdd1(table);
    }
    private static int[] buildCharFrequencyTable1(String s) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for(char c : s.toCharArray()) {
            int x = getCharNumber(c);
            if ( x != -1 ) {
                table[x]++;
            }
        }
        return table;
    }
    private static int getCharNumber(Character c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if ( a <= val && val <= z ) {
            return val - a;
        }
        return -1;
    }
    private static boolean checkMaxOneOdd1(int[] table) {
        boolean foundOdd = false;
        for(int count : table) {
            if ( count % 2 == 1 ) {
                if ( !foundOdd ) {
                    foundOdd = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
