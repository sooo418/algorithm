package programmers;

import java.util.Arrays;
import java.util.Iterator;

public class Sort1 {
    public static int[] solution1(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] nums = {};
        int index = 0;
        for(int[] command : commands) {
            nums = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(nums);
            answer[index] = nums[command[2] - 1];
            index++;
        }
        return answer;
    }
    public static int[] solution(int[] array, int[][] commands) {
        int n = 0;
        int[] ret = new int[commands.length];

        while(n < commands.length){
            int m = commands[n][1] - commands[n][0] + 1;

            if(m == 1){
                ret[n] = array[commands[n++][0]-1];
                continue;
            }

            int[] a = new int[m];
            int j = 0;
            for(int i = commands[n][0]-1; i < commands[n][1]; i++)
                a[j++] = array[i];

            sort(a, 0, m-1);

            ret[n] = a[commands[n++][2]-1];
        }

        return ret;
    }

    static void sort(int[] a, int left, int right){
        int pl = left;
        int pr = right;
        int x = a[(pl+pr)/2];

        do{
            while(a[pl] < x) pl++;
            while(a[pr] > x) pr--;
            if(pl <= pr){
                int temp = a[pl];
                a[pl] = a[pr];
                a[pr] = temp;
                pl++;
                pr--;
            }
        }while(pl <= pr);

        if(left < pr) sort(a, left, pr);
        if(right > pl) sort(a, pl, right);
    }
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2,5,3}};
        Iterator<Integer> iterator = Arrays.stream(solution(array, commands)).iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
