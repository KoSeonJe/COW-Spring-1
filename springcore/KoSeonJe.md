### 목차
1. 스프링과 객체지향
2. 스프링 컨테이너
3. 싱글톤 컨테이너
4. 스프링 빈
5. 컴포넌트 스캔
6. 의존관계 자동 주입


# 좋은 객체 지향 설계의 5가지 원칙(SOLID)

### : 클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리

### 1. SRP 단일 책임 원칙(Single Responsibility principle)

- 한 클래스는 하나의 책임만 가져야 한다.
- 하나의 책임이라는 것은 모호하다.
    - 클 수 있고, 작을 수 있다.
    - 문맥과 상황에 따라 다르다.
- 중요한 기준은 변경이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것.
- 변경이 있을 때, 하나의 클래스에서 하나의 코드만 고치면 단일 책임 원칙을 잘 지켰다고 볼 수 있다.
- 예) UI 변경, 객체의 생성과 사용을 분리

### 2. OCP 개방-폐쇄 원칙 (Open/Closed principle)

- 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 다형성을 활용.
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현.
- 역할과 구현의 분리를 생각해보면, 기존의 코드를 변경하지 않고, 확장할 수 있는 방법을 생각할 수 있을 것이다.

문제점

- MemberService 클라이언트가 구현 클래스를 직접 선택
    - MemberRehpository m = new MemoryMemberRepository();

      //기존코드

    - MemberRehpository m = new JdbcMemberRepository();

      //변경코드

- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
- 이 문제를 어떻게 해결?
    - 객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다. —> 이것을 해주는 것이 스프링 컨테이너

### 3. LSP 리스코프 치환원칙(Liskov substitution principle)

- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야한다. 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면 이 원칙이 필요.
- 단순히 컴파일에 성공하는 것을 넘어서는 이야기
- EX)자동차 인터페이스의 엑셀을 구현할 때. 무조건 앞으로 가도록 구현해야한다. 뒤로 가도록 구현해도 에러는 나지 않겠지만, 원칙을 지켜야 한다.

### 4. ISP 인터페이스 분리 원칙(Interface segregation principle)

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 자동차 인터페이스 → 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 → 운전자 클라이언트, 정비사 클라이언트로 분리.
- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않음.
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### 5. DIP 의존관계 역전 원칙(Dependency inversion principle)

- 프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.” 의존성 주입은 이 원칙을 따르는 방법 중 하나이다.
- 쉽게 말해,  구현 클래스에 의존하지 말고, 인터페이스에 의존해야 한다.
- 역할에 의존해야 한다는 것. 구현체에 의존해서는 안됨.

- OCP에서 설명한 MemberService는 인터페이스에 의존하지만, 구현 클래스에도 동시에 의존하고 있다.
    - MemberRepository m = new MemoryMemberRepository();

  → 구현 객체를 선택해야만 함. 의존하고 있음.

- DIP가 위반된다.

### 정리

- 객체 지향 핵심은 다형성
- 하지만 다형성만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다.
- 다형성만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경되어야한다.
- 다형성만으로는 OCP, DIP를 지킬 수 없다.

### 스프링 이야기에 왜 객체 지향 이야기가 나오는가?

- 스프링은 다음 기술로 다형성 + OCP, DIP를 가능하게 지원해줌.
    - DI(Dependency Injection) : 의존관계, 의존성 주입
    - DI 컨테이너 제공
- 클라이언트 코드의 변경 없이 기능 확장
- 쉽게 부품 교체하듯이

### 정리

- 모든 설계에 역할과 구현을 분리하라.
- 애플리케이션 설계도 공연을 설계 하듯이 배역만 만들어두고, 배우는 언제든지 유연하게 변경할 수 있도록 만드는 것이 좋은 객체 지향 설계이다.
- 이상적으로는 모든 설계에 인터페이스를  부여하자.

실무 고민

- 인터페이스를 도입하면 추상화라는 비용이 발생.
- 기능을 확장할 가능성이 없다면, 구현 클래스를 직접 사용하고, 향후 꼭필요할 때 리팩토링해서 인터페이스를 도입하는 것도 방법.

# 스프링 컨테이너

### 스프링 컨테이너 생성

```java
ApplicationContext applicationContext =
           new AnnotationConfigApplicationContext(AppConfig.class);
```

- ApplicationContext를 스프링 컨테이너라 하고, 이것은 인터페이스이다.
- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 어노테이션 기반의 자바 설정 클래스로 만들 수 있다.
- 직전에 AppConfig를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다.

→ ‘new AnnotationConfigApplicationContext(AppConfig.class);’

→ 이 클래스는 ‘ApplicationContext’ 인터페이스의 구현체이다.

### 1. 스프링 컨테이너 생성

<스프링 빈 저장소>

| 빈 이름 | 빈 객체 |
| --- | --- |
|  |  |
|  |  |
|  |  |
- 스프링 컨테이너를 생성할 때는 구성 정보를 지정해줘야 함.
- ‘new AnnotationConfigApplicationContext(AppConfig.class);’
- 여기서 구성정보 : AppConfig.class

# 싱글톤 컨테이너

### 싱글톤 패턴

- 클래스의 인스턴스가 닥 1개만 생성되는 것을 보장하는 디자인 패턴
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
    - private 생성자를 사용해 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.

1. static영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 객체 인스턴스가 필요하면 오직 getInstance() 메소드를 통해서만 조회할 수 있다. 이 메소드를 호출하면 항상 같은 인스턴스를 반환한다.
3. 딱1 개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.

- 싱글톤의 문제점
    - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어감.
    - 의존관계상 클라이언트가 구채 클래스에 의존

      → DIP 위반

    - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
    - 테스트하기 어렵다
    - 내부 속성을 변경하거나 초기화 하기 어렵다
    - private 생성자로 자식 클래스를 만들기 어렵다.
    - 결론적으로 유연성이 떨어진다.
    - 안티패턴으로 불리기도 한다.

### 싱글톤 컨테이너

- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라고 한다.
- 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
    - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
    - DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.

### 싱글톤 컨테이너 적용 후

- 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용 사용할 수 있다.

참고 : 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할 때마다 새로운 객체를 생성해서 반환하는 기능도 제공한다.

### 싱글톤 방식의 주의점

- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)해야 한다.
- 무상태로 설계!
    - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
    - 특정 클라이언트가 값을 변경할 수 잇는 필드가 있으면 안된다.
    - 가급적 읽기만 가능해야 한다.
    - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다.

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
```

- statefulService1과 statefulService2는 같은 인스턴스로 서로의 값을 공유한다. statefulService1의 값을 변경하면 2도 같이 변경되고, 그 반대도 성립한다
- 엄청난 문제가 된다.

# 스프링 빈

### 스프링 빈 등록

<스프링 빈 저장소>

| 빈 이름 | 빈 객체 |
| --- | --- |
| memberService | MemberServiceImpl@x01 |
| orderService | OrderServiceImpl@x02 |
| memberRepository | MemoryMemberRepository@x03 |
| discountPolicy | RateDiscountPolicy@x04 |
- 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해 스프링 빈을 등록

- 빈 이름
    - 빈 이름은 메소드 이름 사용
    - 빈 이름 직접 부여 가능
        - ‘@Bean(name=”memberService2”)
        - 항상 다른 이름 부여해야함

### 스프링 빈 조회 - 기본

- ‘ac.getBean(빈이름, 타입)
- ‘ac.getBean(타입)’
- 조회대상 스프링 빈이 없으면 예외 발생

### 스프링 빈 조회 - 동일한 타입이 둘 이상

- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생.
- ‘ac.getBeansOfType()’을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.

### 스프링 빈 조회 - 상속관계

- 부모 타입으로 조회하면, 자식 타입도 함께 조회된다.
- 그래서 모든 자바 객체의 최고 부모인 ‘Object’타입으로 조회하면, 모든 스프링 빈을 조회한다.

# 컴포넌트 스캔

### 컴포넌트 스캔

- 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.
- 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
- 의존관계도 자동으로 주입하는 ‘@Autowired’라는 기능도 제공한다.

- 컴포넌트 스캔을 사용하려면 먼저 ‘@ComponentScan’을 설정 정보에 붙여주면 된다.
```java
@ComponentScan(
 excludeFilters =@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
```

→ ComponentScan을 사용하면 ‘@Configuration’이 붙은 설정 정보도 자동으로 등록되기 때문에 제외시키는 코드를 작성함.

- ComponentScan은 이름 그대로 ‘@Component’애노테이션이 붙은 클래스를 스캔해 스프링 빈으로 등록한다. ‘@Configuration’이 컴포넌트 스캔 대상이 된 것도 ‘@Configuration’소스코드를 열어보면 ‘@Component’애노테이션이 붙어있기 때문이다.

![컴포넌트스캔.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/49483982-8767-4413-b84e-ce1371a405ac/%EC%BB%B4%ED%8F%AC%EB%84%8C%ED%8A%B8%EC%8A%A4%EC%BA%94.png


# 의존관계 자동 주입

### 다양한 의존관계 주입 방법

- 의존관계 주입 4가지 방법
    - 생성자 주입
    - 수정자 주입(setter 주입)
    - 필드 주입
    - 일반 메소드 주입

1. 생성자 주입
    - 이름 그대로 생성자를 통해서 의존 관계 주입 받는 방법이다.

```java
private final MemberRepository memberRepository;
private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
```

- 특징
    - 생성자 호출시점에 딱 1번만 호출되는 것이 보장
    - ‘불변, 필수’ 의존관계에 사용
        - 불변이라는 것이 개발할 때 엄청 중요하다.

2. 수정자 주입
    - setter라 불리는 필드의 값을 변경하는 수정자 메소드를 통해서 의존관계를 주입하는 방법

    ```java
        @Autowired
        public void setMemberRepository(MemberRepository memberRepository) {
            System.out.println("memberRepository = " + memberRepository);
            this.memberRepository = memberRepository;
        }
        @Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
            System.out.println("discountPolicy = " + discountPolicy);
            this.discountPolicy = discountPolicy;
        }
    ```


→ 스프링 컨테이너가 스프링 빈을 등록할 때 @Autowired가 붙은 메소드를 호출한다.

- 특징
    - ‘선택,변경’ 가능성이 있는 의존관계에 사용

- 스프링 컨테이너는 크게 두가지 라이프 사이클.
    - 스프링 빈을 모두 등록하기
    - 연관관계를 모두 자동으로 주입 ‘@Autowired”가 걸린 메소드 호출.

3. 필드 주입
- 이름 그대로 필드에 바로 주입하는 방법이다.
- 특징
    - 코드가 간결해서 좋은 것 같지만, 외부에서 값 변경이 불가능해서 테스트 하기 힘들다.
    - DI프레임워크가 없으면 아무것도 할 수가 없음.
    - 사용하지 말자

4. 일반 메소드 주입
- 일반 메소드를 통해 주입 받을 수 있다.
- 특징
    - 한번에 여러 필드를 주입 받을 수 있다.
    - 일반적으로 잘 사용 X

> 참고 : 어쩌면 당연한 이야기이지만 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.
>
### 생성자 주입을 선택해라! 무조건!

1. 불변
- 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.
- 수정자 주입을 사용하면, setXXX메소드를 public으로 열어두어야함.
- 변경하면 안되는 메소드를 열어두는 것은 좋은 설계 방법이 아님.
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다.

2. 누락
- 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우
- 생성자 주입을 사용하면 주입 데이터를 누락 했을 때 ‘컴파일 오류’가 발생한다.

3. final 키워드
- 생성자 주입을 사용하면 ‘final’키워드를 사용할 수 있다. 그래서 생성자에게 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.