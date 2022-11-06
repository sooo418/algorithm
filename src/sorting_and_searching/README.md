# Quick Sort

- 퀵정렬이란 배열에서 중간 위치의 값을 기준으로 잡고 기준값보다 작으면 왼쪽에 크면 오른쪽에 옮기는 방식입니다.
- 시간 복잡도 : O(n log n)

## partitioning

s                                                                                            e

| 3 | 9 | 4 | 7 | 5 | 0 | 1 | 6 | 8 | 2 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |

기준값 pivot = 5

풀이 방법

1. 각 끝에 s(start)와 e(end)를 둔다.
2. s는 pivot값보다 작은값들을 무시하며 뒤로 가고, e는 pivot값보다 큰값들을 무시하며 앞으로 간다.

풀이 Step

1. s = 3, e = 2 : s가 pivot값보다 작으므로 무시
2. s = 9, e = 2 : s가 pivot값보다 크면 e를 비교한다. e가 pivot값보다 작으니 s값과 e값으로 서로 스왑하고 s,e 포인터를 한칸씩 앞당겨준다.

                        s                                                              e

| 3 | 2 | 4 | 7 | 5 | 0 | 1 | 6 | 8 | 9 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. s = 4, e = 8 : s가 pivot값보다 작으므로 무시
2. s = 7, e = 8 : s가 pivot값보다 크니 e를 비교한다. e가 pivot값보다 크므로 무시
3. s = 7, e = 6 : 전단계와 같이 e가 pivot값보다 크므로 무시
4. s = 7, e = 1 : e가 pivot값보다 작으므로 s와 e를 스왑하고 포인터를 앞당겨줌.

                                              s        e

| 3 | 2 | 4 | 1 | 5 | 0 | 7 | 6 | 8 | 9 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. s = 5, e = 0 : s가 pivot값보다 크지 않으니 e를 비교한다. e가 pivot값보다 작으니 둘이 스왑하고 포인터를 앞당겨줌

                                             e         s

| 3 | 2 | 4 | 1 | 0 | 5 | 7 | 6 | 8 | 9 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. 이때 e와 s가 정해진 범위를 벗어나므로 반복문이 종료되면서 pivot의 위치값을 반환해준다.
2. 그럼 반환받은 위치값으로 배열을 나누고 왼쪽 배열과 오른쪽 배열을 위와 같은 방식으로 정렬해준다.

```java
public class QuickSort {
    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }
    private static void quickSort(int[] arr, int start, int end) {
        int part2 = partition(arr, start, end);
        // part2 - 1 값이 start와 같으면 왼쪽 배열은 하나의 값만 가지고있는 배열이라 start보다 클 경우에만 메소드를 호출한다.
        if ( start < part2 - 1) {
            quickSort(arr, start, part2 - 1);
        }
        // 마찬가지로 part2가 end와 같으면 오른쪽 배열이 하나의 값만 가지고있는 배열이라 end보다 작을 경우에만 메소드를 호출한다.
        if ( part2 < end) {
            quickSort(arr, part2, end);
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
        quickSort(arr);
        printArray(arr);
    }
}
```