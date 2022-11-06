package sorting_and_searching;

public class QuickSort {
    private static void quickSort2(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }
    private static void quickSort2(int[] arr, int start, int end) {
        int part2 = partition(arr, start, end);
        // part2 - 1 값이 start와 같으면 왼쪽 배열은 하나의 값만 가지고있는 배열이라 start보다 클 경우에만 메소드를 호출한다.
        if ( start < part2 - 1) {
            quickSort2(arr, start, part2 - 1);
        }
        // 마찬가지로 part2가 end와 같으면 오른쪽 배열이 하나의 값만 가지고있는 배열이라 end보다 작을 경우에만 메소드를 호출한다.
        if ( part2 < end) {
            quickSort2(arr, part2, end);
        }
    }
    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2];
        // start가 end와 같거나 보다 크면 반복문 종료
        while ( start <= end ) {
            // start값이 pivot값보다 작으면 start포인터 증가
            while ( arr[start] < pivot ) start++;
            // start값이 pivot값보다 클 경우에 end값이 pivot보다 크면 end포인터 증가
            while ( arr[end] > pivot ) end--;
            if ( start <= end ) {
                // start값이 pivot값보다 크고 end값이 pivot값보다 작을 경우 둘의 값을 swap해준다.
                swap ( arr, start, end );
                start++;
                end--;
            }
        }
        return start;
    }
    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }
    private static void printArray(int[] arr) {
        for ( int data : arr ) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3, 9, 4, 7, 5, 0, 1, 6, 8, 2};
        printArray(arr);
        quickSort2(arr);
        printArray(arr);
    }
}
