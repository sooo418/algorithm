# 최소비용으로 계단오르기

계단을 오를때 한 칸에서 두 칸까지 오를 수 있을며 계단마다 비용이 존재한다. 계단을 다 오를때 최소비용을 구하라.

Example 1:

Input : cost = [10, 15, 20]

Output : 15

Example 2:

Input : cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]

Output : 6

풀이방법

앞에서부터 풀어보면 뒤에 있는값에 따라 계단 한 칸을 오를지 두 칸을 오를지 결정해야 하는데 뒤에 있는 계단의 비용을 알아야 앞에서 어떤 계단을 선택할지 정확히 알 수 있기 때문에 뒤에서부터 풀어야한다.

cost = [1, 2, 3, 4, 5, 6, 7]

- 7번째 계단의 최소비용 = min(0, 0) + 7 = 7
- 6번째 계단의 최소비용 = min(7, 0) + 6 = 6
- 5번째 계단의 최소비용 = min(6, 7) + 5 = 11
- 4번째 계단의 최소비용 = min(11, 6) + 4 = 10
- 3번째 계단의 최소비용 = min(10, 11) + 3 = 13
- 2번째 계단의 최소비용 = min(13, 10) + 2 = 12
- 1번째 계단의 최소비용 = min(12, 13) + 1 = 13
- 1과 2는 시작점이기에 2에서 시작하면 12의 최소비용이 발생한다.

```java
public class Test {
	public static int minCostClimbingStairs(int[] cost) {
		int case1 = 0, case2 = 0, current;
		for (int i = cost.length - 1; i >= 0; i--) {
			current = Math.min(case1, case2) + cost[i];
			case2 = case1;
			case1 = current;
		}
		return Math.min(case1, case2);
	}
	public static void main(String[] args) {
		int[] cost = new int[] {1, 2, 3, 4, 5, 6, 7};
		int result = minCostClimbingStairs(cost);
		System.out.println(result);
	}
}
```

# Robot int a Grid

그리드 안에 로봇이 있는데 항상 좌측 상단에 위치한다. 로봇은 오른쪽이랑 아래쪽 방향으로만 움직일 수 있고 특정 공간은 제한되어 있어서 갈 수가 없는 길이 있다. 이 로봇이 좌측 상단에서 우측 하단으로 가는 길을 반환해라.

| Robot |  |  |  |
| --- | --- | --- | --- |
|  |  |  | X |
| X | X |  |  |
|  |  |  | Goal |

이렇게 그리드가 있을때 Robot이 X를 지나지않고 Goal에 도달해야한다.

Robot부터 시작해서 Goal로 가는 경로를 출력하면 반대로 출력되기 때문에 Goal에서 Robot가 있는 곳으로 가는 경로를 구하면된다.

풀이방법

Goal에서 Robot을 갈 때에는 왼쪽 또는 위쪽 방향으로만 움직여야하기에 맨 처음에는 일단 무작정 왼쪽으로 가고 더 이상 갈 수 없거나 X가 있으면 위로 가도록 프로그래밍한다.

```java
// 그리드위치 클래스
class Point {
    int row, col;
    Point ( int row, int col ) {
        this.row = row;
        this.col = col;
    }
}

class Solution {
  public ArrayList<Point> findPath(boolean[][] grid) {
      if (grid == null || grid.length == 0) return null;
      ArrayList<Point> path = new ArrayList<Point>();
      if (findPath(grid, grid.length - 1, grid[0].length - 1, path))
          return path;
      else return null;
  }

	// 실제 경로를 찾는 재귀함수
  private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path) {
		// 그리드의 범위를 벗어나거나 제한된 구역이면 false를 반환
    if (!isInRange(grid, row, col) || grid[row][col]) return false;
		/* 
			1.시작점에 도착할때까지 재귀함수를 호출한다.
			2.시작점에 도착하면 줄줄이 true를 반환하며 그리드위치 객체를 생성해 현재 위치를
			  path에 저장한다.
			3.만약 왼쪽 또는 위쪽으로 가는 재귀함수 호출에도 조건에 만족하지 못할 경우
			  false를 반환
		*/
    if ((row == 0 && col == 0)
        || findPath(grid, row, col - 1, path)
        || findPath(grid, row - 1, col, path)) {
      Point p = new Point(row, col);
      path.add(p);
      return true;
    }
    return false;
  }
	// 현재 위치가 그리드 범위 내에 있는 위치인지 구분해주는 함수
  private boolean isInRange(boolean[][] grid, int row, int col) {
      return row >= 0 && row <= grid.length - 1
              && col >= 0 && col <= grid[row].length - 1;
  }
}

class robotInaGrid {
    public static void main(String[] args) {
        boolean[][] grid = {
                {false, false, false, false},
                {false, false, false, true},
                {true, true, false, false},
                {false, false, false, false}
        };
        Solution sol = new Solution();
        ArrayList<Point> path = sol.findPath(grid);
        if (path != null)
            for (Point p : path)
                System.out.print(p.row + "" + p.col + " -> ");
    }
}
```