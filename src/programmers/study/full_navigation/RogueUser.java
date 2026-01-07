package programmers.study.full_navigation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RogueUser {
    /**
     * 문제 풀이
     * 이 문제는 정규표현식을 이용하면 불량 사용자 아이디 목록에 해당하는 사용자 아이디를 쉽게 구할 수 있습니다.
     * 이렇게 구한 사용자 아이디를 조합할 수 있는 경우의 수를 구해야 합니다.
     * 불량 사용자 아이디에 매칭되는 사용자 아이디를 String의 2차원 배열로 구해봅시다.
     */

    public int solution(String[] user_id, String[] banned_id) {
        /*
        * 다음과 같이 불량 사용자 아이디가 들어 있는 banned_id를 Stream으로 변환합니다.
        * 문제의 입력에서 나타난 *는 아무 문자 하나를 나타내므로 정규표현식의 .과 동일하기 때문에 *를 .으로 치환해줍니다.
        * 이제 정규표현식에 해당하는 사용자 아이디를 찾아야 합니다.
        * 불량 사용자 아이디를 나타내는 정규표현식이 banned일 때,
        * 이에 해당하는 사용자 아이디의 배열을 구하려면 전체 사용자 아이디가 들어 있는 user_id를 Stream으로 변환합니다.
        * 그리고 정규표현식에 매칭되는 아이디만 남겨 배열을 구성합니다.
        * 따라서 다음과 같이 불량 사용자 아이디를 이에 해당하는 사용자 아이디의 배열로 변환할 수 있습니다.
        * */
        String[][] bans = Arrays.stream(banned_id)
                .map(banned -> banned.replace("*", "."))
                .map(banned -> Arrays.stream(user_id)
                        .filter(id -> id.matches(banned))
                        .toArray(String[]::new))
                .toArray(String[][]::new);
        // 이렇게 각 불량 사용자 아이디에 매칭되는 사용자 목록이 담긴 2차원 String 배열이 구성되었습니다.
        /**
         * 재귀 정의
         *  앞서 구한 bans를 이용하여 서로 다른 사용자 아이디의 조합 개수를 세어 주어야 합니다.
         *  이 과정은 원소를 순서대로 나열하는 개수인 순열과 비슷하게 재귀로 구현되지만,
         *  순서가 상관없다는 점을 생각하면 Set을 이요할 수 있습니다.
         *  이 문제를 해결하는 재귀를 정의해봅시다.
         *
         *  상태
         *      각 불량 사용자 아이디 중 하나의 사용자 아이디를 선택해야 합니다.
         *      따라서 불량 사용자 아이디의 순서대로 재귀를 진행할 수 있으며,
         *      상태에는 사용자 아이디를 선택할 불량 사용자 아이디의 인덱스 index와
         *      중복된 사용자 아이디를 선택하는 것을 방지하는 Set 자료 구조인 banned로 구성할 수 있습니다.
         *      따라서 이 문제의 재귀 상태는 (index, banned)로 표기할 수 있고,
         *      이 상태가 나타내는 부분 문제는 banned에 포함된 사용자 아이디를 제외하고
         *      불량 사용자 아이디 index번째부터 가능한 사용자 아이디의 조합이 됩니다.
         *
         *  종료 조건
         *      더 이상 사용자 아이디를 선택할 수 없을 때 재귀가 종료됩니다.
         *          1) 사용자 아이디로 모든 불량 사용자 아이디를 선택했거나
         *          2) 해당 불량 사용자 아이디와 중복되지 않는 사용자 아이디가 없어 선택할 수 없을 때가 있습니다.
         *      1)은 정상적으로 모든 사용자 아이디를 선택했기 때문에 가능한 조합을 발견한 것입니다.
         *      반면 2)는 사용자 아이디가 선택되어야 하는 상황에서 적합한 사용자 아이디를 찾지 못했기 때문에 가능한 조합이 아닙니다.
         *
         *          (index, banned) =   {
         *                                  {banned}    -> index == 끝인 경우
         *                                  {}          -> 매칭할 수 있는 사용자 아이디가 없는 경우
         *                              }
         *
         *  점화식
         *      불량 사용자 인덱스를 진행시키며 가능한 조합을 찾습니다.
         *          (index, banned) = (bans[index])∑(id) (index + 1, banned + {id})
         */

        Set<Set<String>> banSet = new HashSet<>();
        count(0, new HashSet<>(), bans, banSet);
        return banSet.size();
    }

    /**
     * 이 중에서 상태를 이용하여 재귀 메서드 count()를 다음과 같이 선언합니다.
     */
    private void count(int index, Set<String> banned, String[][] bans, Set<Set<String>> banSet) {
        /*
        * count()의 매개변수 중 index와 banned는 상태 변수입니다.
        * bans는 불량 사용자 아이디별로 매칭되는 사용자 아이디 정보를 담은 매개변수이며,
        * banSet은 찾은 조합을 저장할 매개변수입니다.
        * 여기에 종료 조건과 점화식을 다음과 같이 구현합니다.
        * */
        if (index == bans.length) {
            banSet.add(new HashSet<>(banned));
            return;
        }

        for (String id : bans[index]) {
            if (banned.contains(id)) continue;
            banned.add(id);
            count(index + 1, banned, bans, banSet);
            banned.remove(id);
            /*
            * banned에 현재까지 선택한 사용자 아이디가 들어 있기 때문에 종료 조건에 도달했을 때 해당 Set에는 사용자 아이디 조합이 들어 있습니다.
            * 이를 복사하여 banSet에 넣어주면 조합을 찾을 수 있습니다.
            * */
        }
    }
}
