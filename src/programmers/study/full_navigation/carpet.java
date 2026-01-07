package programmers.study.full_navigation;

public class carpet {
    /**
     * 문제 풀이
     * 이 문제에서는 주어진 조건을 만족하는 카펫의 가로 길이와 세로 길이를 구해야 합니다.
     * 그림을 보면 갈색 가로 줄에는 한 줄에 width개의 격자가 있고 두줄이 있으므로 총 width x 2개의 격자가 있습니다.
     * 또 갈색 세로 줄에는 한 줄에 height - 2개의 격자가 있고 두 줄이 있으므로 총 (height - 2) x 2개의 격자가 있습니다.
     * 즉, 갈색으로 표시된 격자는 width x 2 + (height - 2) x 2개, 정리하면 (width + height - 2) x 2개입니다.
     *
     * 이를 이용하면 노란색으로 표시된 격자 개수도 쉽게 구할 수 있습니다.
     * 카펫의 총 격자 개수가 width x height개미으로,
     * 여기에서 갈색 격자를 제외한 width x height - (width + height - 2) x 2개가 노란색 격자 개수가 됩니다.
     *
     * 완전 탐색
     * 이제 가능한 모든 경우의 수를 살펴보며 문제 조건을 만족하는 경우를 찾으면 됩니다.
     * 문제 조건을 이용하여 탐색 공간을 정해봅시다.
     * 먼저 노랜삭 격자 개수는 1 이상이라고 명시되어 있습니다. 이는 가로세로의 길이가 최소 3 이상이라는 것을 의미합니다.
     * 가로나 세로의 길이가 2 이하라면, 노란색의 격자는 없고 갈색 격자만 존재하게 됩니다.
     * 또 다른 조건으로는 갈색 격자 개수가 5,000 이하라는 조건이 있습니다.
     * 가로 길이가 세로 길이보다 크거나 같다는 조건을 이용하여 세로 길이는 3이상, 가로 길이 이하라는 범위를 찾아낼 수 있습니다.
     */

    public int[] solution(int brown, int yellow) {
        for (int width = 3; width <= 5000; width++) {
            for (int height = 3; height <= width; height++) {
                int boundary = (width + height - 2) * 2;
                int center = width * height - boundary;
                if (brown == boundary && yellow == center) {
                    return new int[] {width, height};
                }
            }
        }
        return null;
    }
}
