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

# Array List

- Array List는 PHP에서는 Dynamic 한 성질을 가지지만 Java에서는 Fixed 한 성질을 가진다.
- 하지만 Java Library에서 제공하는 Array List는 데이터를 추가해주면 사이즈가 늘어난다. (Dynamic)
  - 그럼에도 불구하고 여전히 데이터를 찾는데 걸리는 시간은 O(1)이다.
  - 그 이유는 Array List는 배열방이 다 차면 배열방을 2배로 늘려주는 작업을 하기 때문에 검색할때는 고정된 배열에서 검색을 하기 때문이다.
    - 두 배 크기의 새로운 배열을 선언하고 기존에 있던 배열의 데이터를 복사해주는 작업을 ‘Doubling’이라고 한다.
    - Doubling을 실행하는 시간은 기존에 있던 데이터의 개수가 n이라고하면 O(n)만큼 걸린다.

    $$
    n/2 + n/4 + n/8 + ... + 2 + 1
    $$

    - 이런 번거로운 작업을 하는데도 불구하고 Array List에 데이터를 입력하는 시간은 O(1)이 걸린다.
    - 그 이유는 데이터를 넣을때 Doubling이 일어나지 않는다면 하나의 데이터를 넣는데 걸리는 시간은 O(1)이기 때문이다.
    - 물론 하나의 데이터를 넣는데 Doubling이 일어나면 O(n)의 시간이 걸리게 될것이다.
- 즉 평균적으로 Array List에서 데이터를 찾는 시간과 데이터를 입력하는 시간은 O(1)이 걸리게 된다.

```java
package arrays_and_strings;

class ArrayList {
    private Object[] data;
    private int size;
    private int index;

    public ArrayList() {
        this.size = 1;
        this.data = new Object[this.size];
        this.index = 0;
    }

    public void add(Object obj) {
        System.out.println("index: " + this.index + ", size: " + this.size + ", data size: " + this.data.length);
        if ( this.index == this.size - 1 ) {
            doubling();
        }
        data[this.index] = obj;
        this.index++;
    }
    private void doubling() {
        this.size = this.size * 2;
        Object[] newData = new Object[this.size];

        for(int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
        System.out.println(" ***index: " + this.index + ", size: " + this.size + ", data size: " + this.data.length);
    }
    public Object get(int i) throws Exception {
        if ( i > this.index - 1 ) {
            throw new Exception("ArrayIndexOutOfBound");
        } else if ( i < 0 ) {
            throw new Exception("Negative Value");
        }
        return this.data[i];
    }
    public void remove(int i) throws Exception {
        if (i > this.index - 1) {
            throw new Exception("ArrayIndexOutOfBound");
        } else if (i < 0) {
            throw new Exception("Negative Value");
        }
        System.out.println("data removed: " + this.data[i]);

        for(int x = i; x < this.data.length - 1; x++) {
            data[x] = data[x + 1];
        }
        this.index--;
    }
}
public class ArrayListAlgorithm {

    public static void main(String[] args) throws Exception {
        ArrayList al = new ArrayList();
        al.add("0");
        al.add("1");
        al.add("2");
        al.add("3");
        al.add("4");
        al.add("5");
        al.add("6");
        al.add("7");
        al.add("8");
        al.add("9");
        System.out.println(al.get(5));
        al.remove(5);
        System.out.println(al.get(5));
    }
}
```

# String Builder

```java
String joinWords(String[] words) {
	String sentence = "";
	for(String w : words) {
		sentence = sentence + w;
	}
}
```

- 위의 코드에서 ‘sentence = sentence + w;’ 코드는 보기에는 엄청 간단해 보이지만 뒤 단에서는 복잡한 작업을 한다.
- 뒤에서의 작업
  - entence 와 w의 문자열을 합한만큼의 문자열을 새로 생성하고 두 문자열을 앞에서부터 단어 하나씩 복사해서 갖다 붙여넣기를 해준다.

$$
x + 2x + 3x + ... + nx
$$

- 각 단어의 문자의 길이를 x라고 할때 위의 공식의 시간만큼 수행시간이 걸리게 된다. → 시간 복잡도 : O(xn^2)
- 위와 같은 문제를 해결하기 위해 Java에서는 String Builder를 제공해준다.

```java
String joinWords(String[] words) {
	StringBuilder sb = new StringBuilder();
	for(String w : words) {
		sb.append(w);
	}
	return sb.toString();
}
```

- String Builder는 클래스 안에 배열 공간을 미리 만들어놓고 append함수가 호출되면 추가되는 문자열을 해당 배열 공간에 바로 추가해주기 때문에 문자열을 더할때 마다 문자열을 새로 추가하지 않는다.
- 배열 공간이 부족하면 그때만 배열을 복사하여 시간을 현저히 절약할 수 있다.
- 그 밖에 StringBuffer를 제공해주는데 StringBuilder가 비동기라면 StringBuffer는 동기적인 특성을 가진다.

<aside>
💡 StringBuilder - asynchronized
StringBuffer  - synchronized

</aside>

```java
package arrays_and_strings;

class StringBuilder {
    private char[] value;
    private int size;
    private int index;

    StringBuilder() {
        size = 1;
        value = new char[size];
        index = 0;
    }

    public void append(String str) {
        if ( str == null ) str = "null";
        int len = str.length();
        ensureCapacity(len);
        for(int i = 0; i < str.length(); i++) {
            value[index] = str.charAt(i);
            index++;
        }
        System.out.println(size + ", " + index);
    }

    private void ensureCapacity(int len) {
        if ( index + len > size ) {
            size = (size + len) * 2;
            char[] newValue = new char[size];
            for(int i = 0; i < value.length; i++) {
                newValue[i] = value[i];
            }
            value = newValue;
            System.out.println("*** " + size + ", " + index);
        }
    }

    public String toString() {
        return new String(value, 0, index);
    }
}

public class StringBuilderAlgorithm {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("sung");
        sb.append(" is");
        sb.append(" pretty");
        System.out.println(sb.toString());
    }
}
```

# String

- 주어진 문자열의 문자들이 모두 고유한지를 확인하는 함수를 구현하시오. 만약 별도의 저장공간을 사용하지 못하는 경우에는 어떻게 해결할지도 추가로 설명하시오.
- ex) “abcddef”
- 문자열이 ASCII 로만 이루어진 문자열인지 Unicode까지 포괄하고있는 문자열인지에 대해 푸는 방식이 달라진다.

## ASCII로만 이루어진 문자열일 경우

1. boolean을 값으로 가지는 128개의 배열방을 만들고 모든값을 false로 초기화를 시켜준다.
2. 해당 문자열을 한문자씩 돌면서 해당 문자의 ASCII값으로 배열방을 접근해서 true인지 체크한다.
3. true가 아니라면 값을 true로 바꿔주고, 이미 true라면 기존에 앞에서 한번 나왔던 값이므로 해당 문자를 중복으로 인식한다.

```java
package arrays_and_strings;

public class StringAlgorithm {
    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));
    }
    private static boolean isUnique(String str) {
        if ( str.length() > 128 ) return false;
        boolean[] char_set = new boolean[128];
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if ( char_set[val] ) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }
}
```

## Unicode까지 포괄한 문자열일 경우

- Unicode의 문자 길이는 2^20 + 2^16개로 1,114,112개의 문자가 존재합니다.
- 이 경우는 Java의 HashMap을 사용해서 해결할 수 있다.

```java
package arrays_and_strings;

import java.util.HashMap;

public class StringAlgorithm {
    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));
    }
    private static boolean isUnique(String str) {
        HashMap<Integer, Boolean> hashmap = new HashMap<Integer, Boolean>();
        for(int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            if ( hashmap.containsKey(c) ) {
                return false;
            }
            hashmap.put(c, true);
        }
        return true;
    }
}
```

## 문자열의 문자들이 소문자로만 이루어졌을 경우

- a - z : 26 개의 문자이기에 Bit 연산자로만으로 문제를 풀 수 있다.

```java
package arrays_and_strings;

import java.util.HashMap;

public class StringAlgorithm {
    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));
    }
    private static boolean isUnique(String str) {
        int checker = 0;
        for(int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ( ( checker & ( 1 << val ) ) > 0 ) {
                return false;
            }
            checker |= ( 1 << val );
        }
        return true;
    }
}
```

## 두개의 문자열이 서로 치환인지 알아기

- 주어진 두개의 문자열이 서로 치환되는지를 알아내는 함수를 구현하시오.
- ex) ABC, ACB, BAC
  - BCA, CAB, CBA
- 치환이 되려면 두개의 문자열 중 가지고 있는 문자의 종류와 개수가 같아야합니다.
- 치환의 조건이 대소문자를 구분하는지와 공백을 무시할건지 따져야한다.

### 공백도 문자로 따지고 대소문자를 구분해서 풀 경우

```java
package arrays_and_strings;

import java.util.Arrays;

public class StringAlgorithm2 {
    public static void main(String[] args) {
        System.out.println(permutation("ABC", "BCA"));
        System.out.println(permutation("ABC", "BDA"));
    }
    private static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
    private static boolean permutation(String s, String t) {
        if ( s.length() != t.length() ) return false;
        return sort(s).equals(sort(t));
    }
}
```

### ASCII - 128 개의 문자로만 이루어져 있다고 가정하여 풀 경우

1. 첫 번째 문자열의 문자 ASCII값으로 정수형 배열을 접근해 값을 증가시켜준다.
2. 두 번째 문자열의 문자 ASCII값으로 정수형 배열을 접근해 값을 감소시켜준다.
3. 이때 배열의 값이 모든 원소들의 값이 0일 경우 두 문자는 같은 문자열이 된다.

```java
package arrays_and_strings;

import java.util.Arrays;

public class StringAlgorithm2 {
    public static void main(String[] args) {
        System.out.println(permutationAscii("ABC", "BCA"));
        System.out.println(permutationAscii("ABC", "BDA"));
    }
    private static boolean permutationAscii(String s, String t) {
        if ( s.length() != t.length() ) return false;
        int[] letters = new int[128];
        for(int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }
        for(int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            if ( letters[t.charAt(i)] < 0 ) return false;
        }
        return true;
    }
}
```

# 문자열안의 공백을 URL인코딩하기

- 주어진 문자열의 공백을 %20으로 변환하는 함수를 구현하시오.
  - 문자열의 맨끝에는 변환에 필요한 충분한 공백이 있고, 실제 문자열의 사이즈를 알고있음.
- Example
  - Input    : “Mr John Smith“
  - Output : “Mr&20John%20Smith”
1. 본 문자열을 캐릭터 별로 배열에 담는다.

| M | r |  | J | o | h | n |  | S | m | i | t | h |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. 해당 문자열의 길이는 13인데 빈칸을 %20으로 치환해야하기 때문에 빈칸 하나당 2의 길이를 더해주어야한다.
  1. 치환 후 문자열의 길이 13 + 2 X 2
2. 위의 배열은 충분한 사이즈를 가지고 있기 때문에 뒤에서부터 문자열을 이동시켜준다.
3. i를 16의 인덱스부터 시작하여 값을 줄이면서 12의 인덱스에 위치한 h부터 문자열을 옮겨준다.

| M | r |  | J | o | h | n |  | S | m | i | t | (p) |  |  |  | h(i) |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. i와 p를 줄여나가며 문자열을 옮기다보면 빈칸을 만나게된다. 그때 빈칸을 %20으로 치환해주어야 하기때문에 p는 값을 유지하면서 i의 값을 감소시켜주면서 %20을 배열에 채워준다.

| M | r |  | J | o | h | n | (p) |  |  |  | (i) | S | m | i | t | h |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |

>>>

| M | r |  | J | o | h | n | (p) |  | %(i) | 2 | 0 | S | m | i | t | h |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
1. 위와 같이 반복해주면 결과값이 나온다.

| M | r | % | 2 | 0 | J | o | h | n | % | 2 | 0 | S | m | i | t | h |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |

```java
package arrays_and_strings;

public class UrlEncoding {
    public static void main(String[] args) {
        System.out.println(URLify("Mr John Smith      ", 13));
    }
    private static String URLify(String str, int len) {
        return URLify(str.toCharArray(), len);
    }
    private static String URLify(char[] str, int len) {
        int spaces = 0;
        for(int i = 0; i < len; i++) {
            if ( str[i] == ' ' ) spaces++;
        }
        int index = len + spaces * 2 - 1;
        for(int p = len - 1; p >= 0; p--) {
            if ( str[p] == ' ' ) {
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            } else {
                str[index--] = str[p];
            }
        }
        return new String(str);
    }
}
```