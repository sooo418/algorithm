package programmers.study.two_dimensional_array;

public class keepYourDistance {
    /**
     * 문제 풀이 흐름
     * 1. 대기실의 모든 응시자 위치에 대해 반복
     *  A. 상하좌우 중 빈 테이블이 있는 방향에 대해 1-B로 진행
     *  B. 빈 테이블과 인접한 위치 중 응시자가 있다면 거리두기를 지키지 않은 것
     * 2. 모든 응시자의 위치를 검사했으나 거리두기를 지키지 않은 경우를 발견하지 못했으면 거리두기를 지킨 것
     */

    /*
     상하좌우를 체크하기 위한 dx, dy 선언하였지만,
     ※ 맨해튼 거리가 2인 P를 찾기 위해 isNextToVolunteer에 exclude값을 계산하기 위해 상좌우하 방향으로 수정
     */
    private static final int dx[] = {0, -1, 1, 0};
    private static final int dy[] = {-1, 0, 0, 1};

    public int[] solution(String[][] places) {
        /*
         우리는 원소 하나하나에 관심이 있고 각 대기실이 거리두기를 지키는지 검사할 것이므로 대기실을 char[][] 형식으로 만들어 주고,
         거리두기 결과를 저장할 배열을 선언합니다.
         */
        int[] answer = new int[places.length];
        for (int i = 0; i < answer.length; i++) {
            String[] place = places[i];
            char[][] room = new char[place.length][];
            for (int j = 0; j < room.length; j++) {
                room[j] = place[j].toCharArray();
            }
            // 거리두기 검사 후 answer에 기록
            if (isDistanced(room)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private boolean isDistanced(char[][] room) {
        // 1. 대기실의 모든 응시자 위치에 대해 반복
        for (int y = 0; y < room.length; y++) {
            for (int x = 0; x < room[y].length; x++) {
                if (room[y][x] != 'P') continue;
                if (!isDistanced(room, x, y)) return false;
            }
        }
        return true;
    }

    private boolean isDistanced(char[][] room, int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) continue;

            // room[ny][nx]를 통해 다른 응시자에게 도달할 수 있는지 검사
            switch (room[ny][nx]) {
                case 'P': return false;
                case 'O':
                    if (isNextToVolunteer(room, nx, ny, 3 - d)) return false;
                    break;
            }
        }
        return true;
    }

    private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
        for (int d = 0; d < 4; d++) {
            if (d == exclude) continue;
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) continue;
            if (room[ny][nx] == 'P') return true;
        }
        return false;
    }
}
