# JAVA WEEK1

### 코드

[https://github.com/COW-edu/practice-oop-lotto/pull/5/commits/992f0d29808b606d11047e1be421364b85921038](https://github.com/COW-edu/practice-oop-lotto/pull/5/commits/992f0d29808b606d11047e1be421364b85921038)

```java
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        int[] winnerNumbers = new int[6];
        int bonus;
        int amount;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the winner numbers (6 numbers):");
        for (int i = 0; i < 6; i++) {
            winnerNumbers[i] = scanner.nextInt();
        }

        System.out.println("Enter the bonus number:");
        bonus = scanner.nextInt();

        System.out.println("Enter the amount:");
        amount = scanner.nextInt();

        int count = amount / 1000;

        for (int i = 0; i < count; i++) {
            int[] lottoNumbers = generateLottoNumbers();
            System.out.println("Lotto number " + (i + 1) + ": " + Arrays.toString(lottoNumbers));

            int matchCount = countMatchingNumbers(winnerNumbers, lottoNumbers);
            boolean hasBonus = contains(lottoNumbers, bonus);

            printPrize(matchCount, hasBonus);
        }
    }

    public static int[] generateLottoNumbers() {
        int[] numbers = new int[6];
        Random random = new Random();

        int index = 0;
        while (index < 6) {
            int number = random.nextInt(45) + 1;
            if (!contains(numbers, number)) {
                numbers[index] = number;
                index++;
            }
        }

        Arrays.sort(numbers);
        return numbers;
    }

    public static boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }

    public static int countMatchingNumbers(int[] array1, int[] array2) {
        int count = 0;
        for (int num1 : array1) {
            for (int num2 : array2) {
                if (num1 == num2) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void printPrize(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            System.out.println("Congratulations! You won the 1st prize! (6 numbers match)");
            System.out.println("Prize: 2,000,000,000 won");
            return;
        }
        if (matchCount == 5 && hasBonus) {
            System.out.println("Congratulations! You won the 2nd prize! (5 numbers + bonus match)");
            System.out.println("Prize: 30,000,000 won");
            return;
        }
        if (matchCount == 5) {
            System.out.println("Congratulations! You won the 3rd prize! (5 numbers match)");
            System.out.println("Prize: 1,500,000 won");
            return;
        }
        if (matchCount == 4) {
            System.out.println("Congratulations! You won the 4th prize! (4 numbers match)");
            System.out.println("Prize: 50,000 won");
            return;
        }
        if (matchCount == 3) {
            System.out.println("Congratulations! You won the 5th prize! (3 numbers match)");
            System.out.println("Prize: 5,000 won");
            return;
        }
        System.out.println("Sorry, you didn't win any prize.");
        System.out.println();
    

    }
}
```

### 학습한 내용

- enum
    - 상수를 정의할 때 `final` `static` `string`과 같은 방식으로 상수를 정의한다.
    - 하지만 이렇게 상수를 정의해서 코딩하는 경우 다양한 문제가 발생된다.
    - 이런 문제를 보완하기 위해 새롭게 추가된 것이다.
    - 관련이 있는 상수들의 집합이다.
    - 자바에서는 `final`로 `String`과 같은 문자열이나 숫자들을 나타내는 기본 자료형의 값을 고정할 수 있다.
    - 고정된 값을 상수라고 한다.(`constant`)
    - 어떤 클래스가 상수만으로 작성되어 있으면 반드시 `class`로 선언할 필요는 없다.
    - `class`로 선언된 부분에 enum이라고 선언하면 이 객체는 상수의 집합이라는 것을 명시적으로 나타낸다.
    - `enum`은 `enumeration`이라는 셈, 계산, 열거, 목록이라는 영어 단어의 앞부분만 따서 만든 예약어이다.
- 객체 지향 생활 체조 9원칙
    - 한 메서드에 오직 한 단계의 들여쓰기만 한다.
        - 코드에 너무 많은 들여쓰기가 있다면, 가독성과 유지 관리성에 좋지 않은 경우가 많다.
        - 대부분의 경우 머릿속으로 컴파일 하지 않고는 쉽게 이해하지 못하도록 하는 경우가 대부분이다.
    - else 예약어를 쓰지 않는다.
        - 조건문은 복제의 원인이 되기도 한다.
        - 가독성 또한 좋지 않다.
    - 모든 원시 값과 문자열을 포장한다.
        - 원시형 변수로는 컴파일러가 의미적으로 맞는 프로그램 작성을 안내할 수 없다.
        - 포장한 객체로라면 아주 사소하더라도 컴파일러와 개발자에게 해당 값이 어떤 값이며 왜 쓰는지에 대해 정보를 전달할 수 있다.
        - 간단하게 객체 내의 모든 원시 요소를 캡슐화하기만 하면 된다.
        - 안티 패턴 중 하나인 `primitive obsession`을 피하기 위해서이다.
    - 한 줄에 점을 하나만 찍는다.
        - 일반적으로 자바, C# 등을 사용하면서 메서드를 호출할 때 .(dot) 연산을 사용하게 된다.
        - 이 규칙은 보통 “메서드 호출을 연쇄적으로 연결해서는 안된다”라고 한다.
        - 어느 코드 한 곳에서 점이 둘 이상 있다면, 해당 부분을 다시 리팩토링 해야 한다.
    - 줄여쓰지 않는다.
        - 과도한 축약은 코드 가독성을 저해한다.
    - 모든 엔티티를 작게 유지한다.
        - 50줄 이상 되는 클래스 또는 10개 파일 이상의 패키지는 없어야 한다.
    - 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
        - 새로운 인스턴스 변수를 가진 클래스는 응집도가 떨어진다.
        - 많은 인스턴스 변수를 가진 클래스로 응집력 있는 단일 작업을 설명할 수 있는 경우는 거의 없다.
        - 인스턴스 변수의 분해는 여러 개의 관련 인스턴스 변수의 공통성을 이해하게 하여 자료구조형으로 묶어 일급 컬렉션으로 생성할 수 있게 해준다.
    - 일급 콜렉션을 쓴다.
        - 컬렉션을 포함한 클래스는 반드시 다른 멤버 변수가 없어야 한다.
    - getter/setter/property를 쓰지 않는다.

### 고민했던 점

- `else`를 사용하지 않고 코드를 완성하는 것에 생각을 많이 했다.
- 찾아보니 대신 `return`을 사용하여 문법을 끝내는 방법이 있다고 하여 각각 `if`문을 새롭게 사용하고 마지막에 계속 `return`을 사용하니 문제 없이 코드가 잘 작동되었다.
- `enum`의 정확한 정의를 아는 것이 먼저라 생각해서 찾아보았다.
- 정의와 활용에 관한 정보를 찾아보아도 내 코드에서는 어떻게 응용을 해야 하는지 갈피를 잡지 못하여 직접 사용하지는 못하였다.
- 아직은 많이 부족한 것 같아서 객체 지향의 모르는 단어와 문법에 대해 더 찾아보고 확실히 공부해야 할 것 같다.