# Quick Sort

- 퀵정렬이란 배열에서 중간 위치의 값을 기준으로 잡고 기준값보다 작으면 왼쪽에 크면 오른쪽에 옮기는 방식입니다.
- 시간 복잡도 : O(n log n)
- 최악의 경우 : O(n2)

## partitioning

| s 3 | 9 | 4 | 7 | 5 | 0 | 1 | 6 | 8 | e 2 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |

기준값 pivot = 5

풀이 방법

1. 각 끝에 s(start)와 e(end)를 둔다.
2. s는 pivot값보다 작은값들을 무시하며 뒤로 가고, e는 pivot값보다 큰값들을 무시하며 앞으로 간다.

풀이 Step

1. s = 3, e = 2 : s가 pivot값보다 작으므로 무시
2. s = 9, e = 2 : s가 pivot값보다 크면 e를 비교한다. e가 pivot값보다 작으니 s값과 e값으로 서로 스왑하고 s,e 포인터를 한칸씩 앞당겨준다.

| 3 | 2 | s 4 | 7 | 5 | 0 | 1 | 6 | e 8 | 9 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. s = 4, e = 8 : s가 pivot값보다 작으므로 무시
2. s = 7, e = 8 : s가 pivot값보다 크니 e를 비교한다. e가 pivot값보다 크므로 무시
3. s = 7, e = 6 : 전단계와 같이 e가 pivot값보다 크므로 무시
4. s = 7, e = 1 : e가 pivot값보다 작으므로 s와 e를 스왑하고 포인터를 앞당겨줌.

| 3 | 2 | 4 | 1 | s 5 | e 0 | 7 | 6 | 8 | 9 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. s = 5, e = 0 : s가 pivot값보다 크지 않으니 e를 비교한다. e가 pivot값보다 작으니 둘이 스왑하고 포인터를 앞당겨줌

| 3 | 2 | 4 | 1 | e 0 | s 5 | 7 | 6 | 8 | 9 |
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

# Merge Sort

- 배열을 2개의 원소를 가진 배열로 쪼개 배열을 병합해주며 정렬해주는 방식
- 시간 복잡도 : O(n log n)
   - 배열의 개수 : n X 배열을 나누는 횟수 : log n
- merge sort는 실행시 별도의 저장공간이 필요시 함.

| 4 | 2 | 6 | 3 | 7 | 8 | 5 | 1 |
| --- | --- | --- | --- | --- | --- | --- | --- |
1. 정렬이 되지않은 배열을 절반씩 자르며 2개의 배열이 될때까지 자른다.

| 4 | 2 | 6 | 3 |
| --- | --- | --- | --- |

| 7 | 8 | 5 | 1 |
| --- | --- | --- | --- |

| 4 | 2 |
| --- | --- |

| 6 | 3 |
| --- | --- |

| 7 | 8 |
| --- | --- |

| 5 | 1 |
| --- | --- |
1. 2개의 배열을 하나씩 정렬해준다.

| 2 | 4 |
| --- | --- |

| 3 | 6 |
| --- | --- |

| 7 | 8 |
| --- | --- |

| 1 | 5 |
| --- | --- |
1. 정렬이 된 배열 2개를 비교해 정렬해준다. → 반복

| 2 | 3 | 4 | 6 |
| --- | --- | --- | --- |

| 1 | 5 | 7 | 8 |
| --- | --- | --- | --- |

| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
| --- | --- | --- | --- | --- | --- | --- | --- |

```java
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

```

# Bubble Sort

- 맨 앞의 원소부터 뒤의 원소를 비교하면서 맨 뒤의 원소까지 비교하여 정렬하는 방식
- 시간 복잡도 : O(n^2)

| 3 | 5 | 4 | 2 | 1 |
| --- | --- | --- | --- | --- |
1. 앞의 두 원소를 비교한다. (3, 5) → 변함없음
2. 이어서 1번과 같이 맨 끝의 원소까지 비교하여 위치를 변경해준다.

(5, 4) → 위치변경  (5, 2) → 위치변경  (5, 1) → 위치변경

| 3 | 4 | 2 | 1 | 5 |
| --- | --- | --- | --- | --- |
1. 또 다시 맨 앞에서부터 비교 시작

(3, 4) → 변함없음  (4, 2) → 위치변경  (4, 1) → 위치변경

| 3 | 2 | 1 | 4 | 5 |
| --- | --- | --- | --- | --- |
1. 위와 같이 반복

(3, 2) → 위치변경  (3, 1) → 위치변경

| 2 | 1 | 3 | 4 | 5 |
| --- | --- | --- | --- | --- |

(2, 1) → 위치변경

| 1 | 2 | 3 | 4 | 5 |
| --- | --- | --- | --- | --- |

```java
package sorting_and_searching;

public class BubbleSort {
    private static void bubbleSort(int[] arr) {
        bubbleSort(arr, arr.length - 1);
    }
    private static void bubbleSort(int[] arr, int last) {
        if ( last > 0 ) {
            for(int i = 1; i <= last; i++) {
                if ( arr[i - 1] > arr[i] ) {
                    swap(arr, i - 1, i);
                }
            }
            bubbleSort(arr, last - 1);
        }
    }
    private static void swap(int[] arr, int source, int target) {
        int tmp = arr[source];
        arr[source] = arr[target];
        arr[target] = tmp;
    }
    private static void printArray(int[] arr) {
        for(int data : arr) {
            System.out.print(data + ", ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 2, 1};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }
}
```