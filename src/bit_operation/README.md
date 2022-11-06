## Integer

- 4bytes = 32bits = 2^32 - 1
- 음수랑 양수를 모두 다뤄야 하기때문에 맨앞의 비트는 음의 정수인지 양의 정수인지 판별하기 위해 사용
- 때문에 Integer의 범위는 -2^31 ~ 2^31 - 1이 된다.

## 비트연산자

- + : 두 항을 말 더한다.
    - ex) 0101 + 0011 = 1000
- - : 첫번째 항에서 두번째 항을 뺀다.
    - ex) 0100 - 0011 = 0001
- OR : 두 항을 비교해 하나라도 1이 존재하면 결과는 1이된다.
    - ex) 0101 OR 0011 = 0111
- AND : 두 항을 비교해 둘 다 1이어야 결과는 1이된다.
    - ex) 0101 AND 0011 = 0001
- XOR : 두 항을 비교해 서로 다르면 결과는 1이된다.
    - ex) 0101 XOR 0011 = 0110
- Shift : 비트를 왼쪽 또는 오른쪽으로 민다.
    - ex) 1001 << 2 = 100100
    - ex) 1001 >> 2 = 10

## 연산자의 특징

- 0과 XOR연산을 하면 자기자신이 나온다.
- 1과 XOR연산을 하면 원래값의 반대값이 나온다.
- 같은값끼리 XOR연산하면 0이 나온다.
- 0과 AND연산하면 0이 나온다.
- 1과 AND연산하면 자기 자신이 나온다.
- 같은값끼리 AND연산하면 값은 그대로다.
- 1과 OR연산하면 1이 나온다.
- 0과 OR연산하면 자기자신이 나온다.
- right shift연산은 sign비트를 염두해두지 않은 (>>>) logical right shift랑 sign비트를 염두하여 그대로 고정되는 (>>) arithmetic right shift가 존재한다.
    - ex) 10110101 >>> 1 = 01011010
    - ex) 10110101 >> 1 = 11011010

## getBit 알고리즘

num의 i번째 인덱스의 비트값을 반환해라.

int num = 9

int i = 3

풀이 방법 i번째 비트값만 1인 값과 num을 &연산을 해주면 i번째 비트값이 주어진다.

- num & (1 << i)

```java
class Test {
	static boolean getBit(int num, int i) {
		return (num & (1 << i)) != 0;
	}

	public static void main(String[] args) {
		// 1 0 0 1
		System.out.println(getBit(9, 3));
		// 0 1 0 1
		System.out.println(getBit(5, 3));
	}
}
```

## setBit 알고리즘

num의 i번째 인덱스의 비트값을 1로 세팅해라.

int num = 5

int i = 3

풀이방법 i번째 비트값만 1인 값과 num을 | 연산해주면 i번째 비트값이 1로 세팅된 값이 반환된다.

- num | (1 << i)

```java
class Test {
	static int setBit(int num, int i) {
		return num | (1 << i);
	}
	public static void main(String[] args) {
		// 0 1 0 1
		System.out.println(setBit(5, 3));
	}
}
```

## clearBit 알고리즘

num의 i번째 비트값을 0으로 세팅

int num = 9

int i = 3

풀이방법 i번째 비트값만 0이고 나머지 비트값은 1인 값과 num을 &연산해준다.

- num & ~(1 << i)

```java
class Test {
	static int clearBit(int num, int i) {
		return num & ~(1 << i);
	}
	public static void main(String[] args) {
		// 1 0 0 1
		System.out.println(clearBit(9, 3));
	}
}
```

## clearLeftBits 알고리즘

num의 i번째 비트값부터 왼쪽 끝의 비트값들을 0으로 세팅해라.

int num = 169

int i = 3

풀이방법 i번째 이후부터 왼쪽 끝의 비트값이 0인 값과 num을 &연산해준다.

- num & (1 << i) - 1
    - not을 하면 i 번째 이후부터의 비트값들도 1이 되어서 만족이안됨

```java
public class bitOperator {
    static int clearLeftBits(int num, int i) {
        return num & (1 << i) - 1;
    }
    public static void main(String[] args) {
        System.out.println(clearLeftBits(169,3));
    }
}
```

## clearRightBits 알고리즘

num의 i번째 비트값부터 오른쪽 끝의 비트값들을 0으로 세팅해라.

int num = 169

int i = 3

풀이방법 i번째 이후부터 오른쪽 끝의 비트값이 0 인값과 num을 &연산해준다.

- num & (-1 << (i + 1))

```java
public class bitOperator {
    static int clearRightBits(int num, int i) {
        return num & (-1 << (i + 1));
    }
    public static void main(String[] args) {
        System.out.println(clearRightBits(169,3));
    }
}
```

## updateBit 알고리즘

num의 i번째 인덱스에 입력받은 값(val)으로 세팅해라.

int num = 169

int i = 3

boolean val = true

풀이방법 i번째 인덱스만 0인값과 num을 &연산한 후에 이어서 i번째 인덱스값이 val인값과 | 연산해준다.

- (num & ~(1 << i)) | ((val? 1 : 0) << i)

```java
class Test {
	static int updateBit(int num, int i, boolean val) {
		return (num & ~(1 << i)) | ((val? 1 : 0) << i);
	}
	public static void main(String[] args) {
		System.out.println(updateBit(169, 3, true));
	}
}
```