package bit_operation;

public class BitOperator {
    static int clearLeftBits(int num, int i) {
        return num & (1 << i) - 1;
    }
    static int clearRightBits(int num, int i) {
        return num & (-1) << (i + 1);
    }
    static int updateBit(int num, int i, boolean val) {
        return (num & ~(1 << i)) | ((val? 1 : 0) << i);
    }
    public static void main(String[] args) {
        int num = 169;
        int i = 3;
        boolean val = true;
        System.out.println(updateBit(num, i, val));
    }
}
