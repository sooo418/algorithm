package sorting_and_searching;

public class MergeSort {
    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);
    }
    private static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if ( start < end ) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }
    private static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        {
            int i = start;
            while (i <= end) {
                tmp[i] = arr[i];
                i++;
            }
        }
        int part1 = start;
        int part2 = mid + 1;
        int index = start;
        while(part1 <= mid && part2 <= end) {
            if ( tmp[part1] <= tmp[part2] ) {
                arr[index] = tmp[part1];
                part1++;
            } else {
                arr[index] = tmp[part2];
                part2++;
            }
            index++;
        }
        int i = 0;
        while (i <= mid - part1) {
            arr[index + i] = tmp[part1 + i];
            i++;
        }
    }
    public static void printArray(int[] arr) {
        for(int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3,9,4,7,5,0,1,6,8,2};
        printArray(arr);
        mergeSort(arr);
        printArray(arr);
    }
}
