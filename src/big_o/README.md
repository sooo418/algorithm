# 피보나치수열의 시간복잡도

- 피보나치 수열이란
  - 1 → 1 → 2 → 3 → 5 → 8 …
- 바로 전의 숫자와 전의 전 숫자를 더하여 수열을 이룬다.

```java
F(n, r) {
    if (n <= 0) return 0;
    else if (n == 1) return r[n] = 1;
    return r[n] = F(n - 1, r) + F(n - 2, r);
}
```

- 해당 코드의 시간 복잡도 : O(2^n)

위의 코드를 최적화해보자

```java
F(n, r) {
    if (n <= 0) return 0;
    else if (n == 1) return r[n] = 1;
    else if (r[n] > 0) return r[n];
    return r[n] = F(n - 1, r) + F(n - 2, r);
}
```

- 해당 코드의 시간 복잡도 : O(n)

피보나치 수열의 코드를 비효율적으로 작성해보자

```java
allF(n) {
    for (i = 1 to n) print F(i)
}
F(n) {
    if (n <= 0) return 0
    else if (n == 1) return 1
    return F(n - 1) + F(n - 2)
}
```

- 해당 코드의 시간 복잡도는 O(n2^n)으로 보이지만
- for (i = 1 to n) print F(i)를 보면 1부터 시작하기에 2^1 + 2^2 + 2^3 + … + 2^n-1 + 2^n = 2^n - 2 + 2^n이 된다.
  - 2^n - 2 + 2^n = 2 * 2^n - 2
  - 해당 식을 Big O 표기법을 쓰면 O(2^n)이 된다.