package programmers.study.recursion;

public class QuadCompression {
    /**
     * 문제 풀이
     * 정사각형 모양의 그리드 안에 0 혹은 1로 된 작은 정사각형들을 구해야 합니다.
     * 특정 정사각형 범위 안의 원소들이 모두 0 혹은 1로만 되어 있다면 해당 범위는 하나의 숫자로 압축됩니다.
     * 또는 0과 1을 모두 포함한다면 범위는 4등분이 되어 작은 정사각형 범위들로 나누어집니다.
     * 이때 작은 정사각형들에는 큰 정사각형 범위를 처리하는 방식과 같은 방식을 다시 적용합니다.
     *
     * 문제 풀이 흐름
     * 1. 상태
     *  '쿼드압축 후 개수 세기' 문제에서는 정사각형 범위가 계속해서 분할되어 가므로 하나의 상태는 정사각형 범위를 나타냅니다.
     *  2차원 배열에서 정사각형 범위는 정사각형이 시작되는 좌표와 한 변의 크기를 사용해서 나타낼 수 있습니다.
     *  즉, 상태는 (offsetX, offsetY, size)처럼 나타낼 수 있습니다.
     *  이 상태는 2차원 배열의 (offsetX, offsetY) 좌표에서 시작하여 가로 길이와 세로 길이가 size인 정사각형 범위를 나타냅니다.
     *  이 상태가 나타내는 부분 문제는 해당 상태가 나타내는 정사각형 범위를 압축했을 때 남아 있는 0의 개수와 1의 개수를 구하는 것입니다.
     *  즉, 상태는 다음과 같이 정의됩니다.
     *      (offsetX, offsetY, size) = 좌표 (offsetX, offsetY)에서 시작하여 가로 길이와 세로 길이가 size인 정사각형을 압축했을 때 남아있는 0과 1의 개수
     *
     * 2. 종료 조건
     *  상태가 나타내는 범위의 크기와 관계없이 범위 안 원소들이 모두 0이거나 1이면 정사각형은 더 이상 나누어지지 않고 하나의 숫자로 압축됩니다.
     *  따라서 원소 구성에 따라 재귀가 종료되어야 합니다.
     *  0의 개수가 zero, 1의 개수가 one일 때 결과 표기를 {0: zero, 1: one}이라고 한다면 종료 조건은 다음과 같이 정의할 수 있습니다.
     *      (offsetX, offsetY, size) = {
     *          {0:1, 1:0} -> 모든 원소가 0일 때
     *          {0:0, 1:1} -> 모든 원소가 1일 때
     *      }
     *
     * 3. 점화식
     *  점화식은 부분 문제를 해결하는 로직으로, 큰 문제를 작은 문제로 나타내야 합니다.
     *  이 문제에서는 큰 정사각형이 4개의 작은 정사각형으로 나뉘어지고, 작은 정사각형을 압축한 결과를 모두 더한 것이 큰 정사각형의 결과가 됩니다.
     *  예를 들어 다음과 같이 4 x 4 크기의 정사각형이 구성되어 있습니다.
     *      {
     *          {0, 0, 0, 1},
     *          {0, 0, 0, 1},
     *          {1, 0, 1, 1},
     *          {0, 1, 1, 1}
     *      }
     *  이 정사각형은 2 x 2 크기의 정사각형들로 나뉘어 압축됩니다. 작은 정사각형들이 압축된 상태는 다음과 같습니다.
     *      {
     *          0:1     0:2
     *          1:0     1:2
     *
     *          0:2     0:0
     *          1:2     1:1
     *      }
     *  2 x 2 정사각형이 압축되는 과정은 4 x 4 정사각형이 압축되는 과정과 같은 로직을 사용하므로
     *  다시 1 x 1 정사각형으로 나뉘는 과정을 진행하지 않고 직접 0과 1의 개수를 세어 주면 됩니다.
     *  이렇게 작은 부분 문제인 2 x 2 정사각형들의 압축 결과를 구했습니다.
     *  4 x 4 정사각형을 압축했을 때 0과 1의 개수는 2 x 2 정사각형들의 압축 결과를 모두 합친 결과입니다.
     *  모든 결과를 합쳐보면 0이 5개, 1이 5개가 되므로 4 x 4 정사각형은 다음 그림과 같이 압축됩니다.
     *      {
     *          0:5
     *          1:5
     *      }
     *  현재 상태가 나타내는 정사각형 한 변의 길이가 size이므로 작은 부분 문제에서 해결해야 하는 정사각형 한 변의 길이는 size/2입니다.
     *  나누어진 정사각형의 시작 위치 또한 x, y좌표를 size/2만큼씩 이동해야 합니다.
     *  즉, 점화식은 다음과 같이 정의할 수 있습니다.
     *      (offsetX, offsetY, size) = (offsetX, offsetY, size/2)
     *                               + (offsetX + size/2, offsetY, size/2)
     *                               + (offsetX + offsetY + size/2, size/2)
     *                               + (offsetX + size/2, offsetY + size/2, size/2)
     */

    // 상태가 나타내는 정사각형을 압축했을 때 남아있는 0과 1의 개수를 구해야하는데 이를 위해 0과 1의 개수를 한 번에 담을 수 있는 Count 클랙스 작성
    private static class Count {
        public final int zero;
        public final int one;

        public Count(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }

        // 점화식에서는 4개의 작은 정사각형 결과 합을 구해야 하므로 이를 간단하게 구현하고자 Count 클래스에 두 Count 객체를 합하는 add() 메서드 정의
        public Count add(Count other) {
            return new Count(zero + other.zero, one + other.one);
        }
    }

    // 하나의 상태를 나타내는 부분 문제를 해결할 수 있는 재귀 메서드 정의
    private Count count(int offsetX, int offsetY, int size, int[][] arr) {
        int h = size / 2;
        for (int x = offsetX; x < offsetX + size; x++) {
            for (int y = offsetY; y < offsetY + size; y++) {
                if (arr[y][x] != arr[offsetY][offsetX]) {
                    // 점화식 구현
                    return count(offsetX, offsetY, h, arr)
                            .add(count(offsetX + h, offsetY, h, arr))
                            .add(count(offsetX, offsetY + h, h, arr))
                            .add(count(offsetX + h, offsetY + h, h, arr));
                }
            }
        }
        // 종료 조건 정의
        if (arr[offsetY][offsetX] == 1) {
            return new Count(0, 1);
        }
        return new Count(1, 0);
    }

    public int[] solution(int[][] arr) {
        Count count = count(0, 0, arr.length, arr);
        return new int[] {count.zero, count.one};
    }

    /**
     * 1. 정사각형인 2차원 배열에서 특정 범위의 정사각형의 숫자(0,1)들이 같다면 하나로 압춥하고 섞여있다면 4등분하여 숫자를 압축한다.
     * 2. 길이, 좌표가 상태로 존재해야함 (x, y, length)
     * 3. 정사각형 범위의 숫자가 섞여있을때 4등분
     * 4. 정사각형 범위의 숫자가 모두 같거나 한 개일때 종료
     */
}
