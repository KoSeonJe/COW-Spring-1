### Spring

자바 언어 기반의 프레임워크

OOP 어플리케이션 개발에 적합하다

### SOLID 원칙

- 정리
    - SRP(single responsibility principle) 단일 책임 원칙
        - 변경이 있을 때 파급 효과가 적은 것 ex) UI 변경, 객체의 생성과 사용을 분리
        - 하나의 객체는 한 가지의 책임을 가져야 한다.
    - **OCP(Open/closed principle) 개방-폐쇄 원칙**(가장 중요!)
        - 확장에는 열려 있으나 변경에는 닫혀 있어야 함
        - 구현 객체를 변경하려면 클라이언트 코드를 변경해야 하는데 그렇게 되면 OCP 원칙을 지킬 수 없다
        - 이 문제를 해결하기 위해 스프링 컨테이너를 통해 객체 생성, 연관관계를 맺어주는 별도의 조립, 설정자를 만들 수 있다.
    - LSP(Liskov substitution principle) 리스코프 치환 원칙
        - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것
        - ex: 자동차에서 엑셀을 밟으면 앞으로 가야 함. 뒤로 가면 LSP 위반하는 것임
    - ISP(Interface segregation principle) 인터페이스 분리 원칙
        - 특정 클라이언트를 위한 인터페이스를 분리하는 것은 범용 인터페이스 하나보다 낫다
        - 인터페이스가 명확해지고, 대체 가능성이 높아짐
    - DIP(Dependency inversion principle) 의존관계 역전 원칙
        - 구현 클래스에 의존하지 말고, 인터페이스에 의존해야 한다
        - 한 역할을 맡은 배우가 특정 배우하고만 연기하고 싶어한다면, 변경이 어렵기 때문

기존 자바의 다형성만으로는 OCP, DIP를 지키기 어렵다

스프링은 DI와 DI 컨테이너로 이 단점을 해소해준다

### 스프링 컨테이너

자바 객체의 생명 주기를 관리함

스프링 빈: 스프링 컨테이너에 의해 관리되는 자바 객체

스프링 컨테이너는 객체를 싱글톤으로 관리하기에 **싱글톤 컨테이너**라고도 한다

### 왜 쓸까? (IoC Inversion of Control)

개발자는 프로그래밍을 하며 로직 상 필요한 객체들을 관리해야 한다. 객체를 어떻게 관리하는지 묻는다면, 객체를 적재적소에 사용하기 위해 그때그때마다 직접 생성하고 필요한 메소드를 호출하는 작업 등을 하는 것이라고 답할 수 있겠다.

하지만 백엔드 개발자라면, 비즈니스 로직에 대해 고민하기에도 바쁠 것이다. 그래서 스프링에서는 객체 관리를 스프링 컨테이너에서 대신 해주는데, 이것이 스프링의 중요한 특징인 **제어의 역전(Inversion of Control)**이다. 

개발자는 스프링 컨테이너에 미리 생성되어 있는 객체를 가져와서 사용할 수 있다. 이 객체의 생성에서 소멸까지의 모든 과정이 스프링 컨테이너에서 관리된다. 이는 객체에 대한 제어권을 개발자가 아닌 스프링이 가지고 있기 때문에 **제어의 역전**이라고 한다.

### 싱글톤 패턴이란?

생성 패턴 중 하나로, 생성 패턴이란 인스턴스를 만드는 절차를 추상화하는 패턴임

클래스의 인스턴스가 **딱 하나만 생성**되는 것을 보장하는 디자인 패턴

### 왜 만들어졌을까?

클래스 하나 당 하나의 인스턴스만을 가져도 되는 상황이 있음

대표적으로 로깅 객체, 쓰레드 풀, 윈도우 관리자 등의 객체가 있다.

> 전역 변수를 사용하는 방법도 있지 않은가?
> 

이것도 가능한 방법이지만, 클래스가 자신만의 유일한 인스턴스를 가지며 그 인스턴스로 접근하는 방법을 관리하는 차원에서 싱글톤 패턴이 더욱 좋다고 할 수 있다.

### 구현 방법

다양한 방법이 있지만, 공통된 특징이 있다

- private 생성자 사용으로 외부로부터 인스턴스 생성을 차단
- 싱글톤 구현 클래스 내부 멤버 변수에 private static 사용
- public static 메소드를 통해 외부에서 접근할 수 있는 접점 제공

1. **Eager Initialization**
    
    싱글톤 클래스의 인스턴스를 컴파일 단계에서 생성하는 방법
    
    ```java
    public class Singleton {
        
        private static final Singleton instance = new Singleton();
        
        // private constructor to avoid client applications to use constructor
        private Singleton(){}
     
        public static Singleton getInstance(){
            return instance;
        }
    }
    ```
    
    단점
    
    - 큰 리소스를 다루는 싱글톤을 구현할 때는 리소스 낭비가 심함
    - 예외 처리를 다루지 않음
    
2. ****Static Block Initialization****
    
    위와 유사하지만 static block을 통해 예외를 처리할 수 있음
    
    ```java
    public class Singleton {
     
        private static Singleton instance;
        
        private Singleton(){}
        
        //static block initialization for exception handling
        static{
            try{
                instance = new Singleton();
            }catch(Exception e){
                throw new RuntimeException("Exception occured in creating singleton instance");
            }
        }
        
        public static Singleton getInstance(){
            return instance;
        }
    }
    ```
    

1. ****Lazy Initialization****
    
    인스턴스를 조회하는 메소드를 호출할 때에 인스턴스 유무를 검사하고, 없다면 생성해줌
    
    ```java
    public class Singleton {
     
        private static Singleton instance;
        
        private Singleton(){}
        
        public static Singleton getInstance(){
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```
    
    인스턴스 낭비에 대한 문제는 해결되지만, **멀티 쓰레드 환경에서의 동기화 문제**가 발생함
    
    → 인스턴스가 생성되지 않았을 때 여러 쓰레드에서 동시에 인스턴스를 호출한다면 예상치 못한 결과를 얻을 수 있음
    
    따라서 단일 쓰레드 환경이 보장되어야 함
    
2. ****Thread Safe Singleton****
    
    위의 문제를 해결하기 위해, 조회 메소드에 synchronized를 사용하는 방법임
    
    synchronized 키워드는 Critical Section을 형성하여 해당 영역에 오직 하나의 쓰레드만 접근할 수 있게 해줌
    
    ```java
    public class Singleton {
     
        private static Singleton instance;
        
        private Singleton(){}
        
        public static synchronized Singleton getInstance(){
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }
    ```
    
    단점
    
    synchronized 자체에 대한 비용이 크기 때문에 인스턴스 호출이 잦으면 성능이 떨어짐
    
    이를 보완하기 위해 double checked locking 방식이 있음
    
    ```java
    public static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class) {
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
    ```
    
    synchronized 키워드의 사용 범위를 더욱 축소해 인스턴스가 없을 경우에만 동작함
    
3. ****Bill Pugh Singleton Implementaion****
    
    inner static helper class를 사용하는 방식
    
    앞의 방식들의 문제점을 대부분 해결한 방식으로, 현재 가장 널리 쓰임
    
    ```java
    public class Singleton {
     
        private Singleton(){}
        
        private static class SingletonHelper{
            private static final Singleton INSTANCE = new Singleton();
        }
        
        public static Singleton getInstance(){
            return SingletonHelper.INSTANCE;
        }
    }
    ```
    
    SingletonHelper 클래스는 싱글톤 인스턴스가 호출된 시점에서야 JVM 메모리에 로드된다.
    
4. ****Enum Singleton****
    
    앞의 모든 방법은 class 안에서 싱글톤을 생성하므로 java reflection을 통해 파괴될 수 있는 위험이 있음
    
    java reflection: 구체적인 클래스를 모르더라도 해당 클래스의 메소드나 변수에 접근할 수 있게 해주는 API
    
    enum을 통해 싱글톤을 구현하는 방법
    
    ```java
    public enum EnumSingleton {
     
        INSTANCE;
        
        public static void doSomething(){
            //do something
        }
    }
    ```
    
    하지만 이 방법 역시 1, 2번처럼 메모리 문제와 유연성 저하의 문제가 있음
    

<aside>
💡 가급적이면 inner static class 방식을 사용하자!

</aside>

주의해야 할 점은, 다음과 같이 상태를 유지하게 설계하면 문제가 발생한다

```java
public class StatefulService {
 	private int price; //상태를 유지하는 필드
 	public void order(int price) {
		 this.price = price; //문제 지점
 	}
 	public int getPrice() {
 		return price;
 	}
}
```

하나의 객체 인스턴스를 공유하므로 다른 price로 두 번을 호출했을 때, 가장 나중에 호출된 값이 저장되는 문제가 발생한다

따라서 무상태성을 가지도록 설계해야 함

### 컴포넌트 스캔

@Bean 어노테이션을 통해 자동으로 스프링 빈을 등록하는 방법

싱글톤 방식으로 빈들을 관리하기 위해 구성 정보를 지정해주는 AppConfig class를 사용함

```java
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

<aside>
💡 @Configuration을 붙이면 어떤 일이 발생할까?

</aside>

1. Configuration 클래스 및 Annotation에 사용하는 설정들을 파싱
2. basePackage 밑의 모든 .class 자원을 불러와서 component 후보인지 확인하여 BeanDefinition 생성
3. 생성된 빈 정의를 바탕으로 빈을 생성하고 의존성 있는 빈들을 주입

### 의존관계 주입

객체 간 의존성을 외부에서 런타임 시점에 결정하는 것

- 생성자 주입: 생성자를 통해 의존 관계를 주입받는 방법
    
    특징
    
    - 생성자가 호출되는 시점에 한 번만 호출된다
    - 생성자가 한 개만 있을 경우 @Autowired를 생략해도 자동 주입된다
    - NullPointerException 방지
    - 주입받는 필드를 final로 선언해 불변하고 필수적인 특성을 가질 수 있다
    
    lombok 을 통해 편하게 멤버 변수에 private final로만 선언하는 방식을 사용함
    
    ```java
    @Service
    @RequiredArgsConstructor // 생성자 생략 가능
    public class MemberServiceImpl implements MemberService {
    	private final MemberRepository memberRepository;
    }
    ```
    
- Setter 주입: 수정자 setter를 이용해 의존 관계를 주입하는 방법
    
    특징
    
    - 선택, 변경 가능성이 있는 의존 관계에 사용함
    - @Autowired를 꼭 붙여줘야 함
    
    ```java
    @Component
    public class CoffeeService {
        private final MemberRepository memberRepository;
        private final CoffeeRepository coffeeRepository;
    
        @Autowired
        public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    
        @Autowired
        public void setCoffeeRepository(CoffeeRepository coffeeRepository) {
            this.coffeeRepository = coffeeRepository;
        }
    }
    ```
    
- 필드 주입: 필드에 @Autowired를 붙여서 바로 주입하는 방법
    
    특징
    
    - 외부에서 변경이 불가능해 테스트하기 어렵다
    - DI 프레임워크가 없으면 아무것도 못한다
    
    ```java
    @Component
    public class CoffeeService {
        @Autowired
        private final MemberRepository memberRepository;
        @Autowired
        private final CoffeeRepository coffeeRepository;
    }
    ```
    
    가급적 쓰지 말자
    
- 메소드 주입: 일반 메소드를 통해 의존관계를 주입받는 방법
    
    특징
    
    한 번에 여러 필드를 주입받을 수 있음
    

### DI vs DIP

DI: Dependency Injection (의존성 주입)

DIP: Dependency Inversion Principle

추상화(인터페이스)에 의존함으로써 구현체의 의존 관계를 숨겨라

### @Component vs @Bean

@Component

- 클래스 레벨에서 적용
- Class 자체를 빈으로 등록할 때

@Bean

- 메소드 레벨에서 적용
- 사용자가 제어하지 못하는 외부 라이브러리 등록에 사용

### Reference

[https://velog.io/@hyun-jii/스프링-component-scan-개념-및-동작-과정](https://velog.io/@hyun-jii/%EC%8A%A4%ED%94%84%EB%A7%81-component-scan-%EA%B0%9C%EB%85%90-%EB%B0%8F-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95)

https://prodo-developer.tistory.com/107
