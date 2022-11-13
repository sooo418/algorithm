# Hash Table

- 검색하고자 하는 키값을 받아서 Hash Method를 돌려 반환받은 Hash Code를 배열의 인덱스로 환산해서 데이터에 접근을 하는 자료구조
- 암호화폐의 핵심 기술인 블록체인에서도 사용된다.
- Hash Table의 가장 큰 장점은 검색속도가 매우 빠르다는거다.
    - 빠른 이유는 배열 공간을 고정된 크기만큼 만들어놓고 Hash Method를 이용해 반환받은 Hash Code는 정수이기에 Hash Code를 배열의 개수로 나머지연산을 해서 배열에 나눠담아주면 Hash Code 자체가 배열의 인덱스로 사용된다고 보면 된다.
- Hash Table도 단점이 존재한다.
    - 배열을 만들때 한 공간에 데이터가 집중이 되면 충돌현상이 생긴다그래서 Collision이 일어난다라고 한다.
    - Hash Table의 큰 장점은 O(1)이 실행시간이 걸리지만 Collision이 일어나면 최대 O(n)만큼의 실행시간이 걸린다.

## Hash Algorithm & Collision

- Hash Method는 가끔 다른 키값으로 동일한 Hash Code를 반환하기도 한다.
    - 이유는 키값은 문자열이고 그 가지수가 무한한데에 반해서 Hash Code는 정수형이기 때문에 알고리즘이 아무리 좋아도 어떤 키들은 중복된 Hash Code를 가질 수밖에 없다.
    - different keys → same code
- 때로는 Hash Method가 서로 다른 Hash Code를 만들었는데 배열방이 한정되어 있어서 같은 방에 배정되는 경우가 발생한다.
    - different code → same index
- 위와 같은 경우들을 Collision이라고 한다.

## Hash Method를 만들어보자

- 간단하게 ascii 코드를 사용하여 알고리즘을 짜보기로하자.

### getHashCode(key)

k의 ASCII + e의 ASCII + y의 ASCII = HashCode

ex) input : sung

s(115) + u(117) + n(110) + g(103) = 445

j(106) + i(105) + n(110) = 321

h(104) + e(101) + e(101) = 306

m(109) + i(105) + n(110) = 324

### ConvertToIndex(Hash Code)

Hash Code를 배열의 길이로 나눈 나머지로 인덱스를 구하면 된다.

ex) array의 길이 : 3

445 % 3 = 1

321 % 3 = 0

306 % 3 = 0

324 % 3 = 0

- 예제와 같이 Hash Code가 다른데 배열의 방이 겹치는 경우를 보면 Hash Algorithm은 그닥 효율이 좋은 알고리즘으로 보긴 힘들다.

```java
package arrays_and_strings;

import java.util.LinkedList;

class HashTable {
    class Node {
        String key;
        String value;
        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
        String value() {
            return value;
        }
        void value(String value) {
            this.value = value;
        }
    }
    int getHashCode(String key) {
        int hashcode = 0;
        for(char c : key.toCharArray()) {
            hashcode += c;
        }
        return hashcode;
    }
    int convertToIndex(int hashcode) {
        return hashcode % data.length;
    }
    Node searchKey(LinkedList<Node> list, String key) {
        if (list == null) return null;
        for (Node node : list) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }
    void put(String key, String value) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        System.out.println(key + ", hashcode(" + hashcode + "), index(" + index + ")");
        LinkedList<Node> list = data[index];
        if ( list == null ) {
            list = new LinkedList<Node>();
            data[index] = list;
        }
        Node node = searchKey(list, key);
        if ( node == null ) {
            list.addLast(new Node(key, value));
        } else {
            node.value(value);
        }
    }
    String get(String key) {
        int hashcode = getHashCode(key);
        int index = convertToIndex(hashcode);
        LinkedList<Node> list = data[index];
        Node node = searchKey(list, key);
        return node == null ? "Not found" : node.value();
    }
    LinkedList<Node>[] data;
    HashTable(int size) {
        this.data = new LinkedList[size];
    }
}

public class HashTableAlgorithm {
    public static void main(String[] args) {
        HashTable h = new HashTable(3);
        h.put("sung", "She is pretty");
        h.put("jin", "She is a model");
        h.put("hee", "She is an angel");
        h.put("min", "She is cute");
        h.put("sung", "She is beautiful");

        System.out.println(h.get("sung"));
        System.out.println(h.get("jin"));
        System.out.println(h.get("hee"));
        System.out.println(h.get("min"));
        System.out.println(h.get("jae"));
    }
}
```