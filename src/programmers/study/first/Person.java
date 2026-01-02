package programmers.study.first;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private final String name;
    private final List<PhoneNumber> numbers;

    public Person(String name) {
        this.name = name;
        this.numbers = new ArrayList<>();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
         numbers.add(phoneNumber);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }

    public boolean hasPhoneNumber(PhoneNumber number) {
        return numbers.contains(number);
    }

    public static void main(String[] args) {
        Person person = new Person("홍길동");
        person.addPhoneNumber(new PhoneNumber("010-1234-5678"));

        System.out.println(person.hasPhoneNumber(new PhoneNumber("01012345678")));
    }
}
