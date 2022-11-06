package dynamic_programming;

public class MinCostClimbingStairs {
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
        int[] cost = new int[] {1,100,1,1,1,100,1,1,100};
        int result = minCostClimbingStairs(cost);
        System.out.println(result);
    }
}