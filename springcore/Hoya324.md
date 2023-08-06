> 스프링 핵심 개념을 정리한 글입니다.

## 스프링이란?

1. 스프링 DI 컨테이너 기술
2. 스프링 프레임워크
3. 스프링 부트, 스프링 프레임워크 등을 모두 포함한 스프링 생태계

### 스프링의 핵심

- 스프링은 자바 언어 기반의 프레임워크
- 자바 언어의 가장 큰 특징 - 객체 지향 언어
- 스프링은 객체 지향 언어가 가진 강력한 특징을 살려내는 프레임워크
- 스프링은 좋은 객체 지향 애플리케이션을 개발할 수 있게 도와주는 프레임워크이다.

### 좋은 객체 지향  프로그래밍이란?

- 역할과 구현을 분리한 것
    - 인터페이스가 바뀌는 일이 없도록 안정적으로 잘 설계하는 것이 중요

## **좋은 객체 지향 설계의 5가지 원칙(SOLID)**

### 1. **SRP 단일 책임 원칙 Single responsibility principle**

- 한 클래스는 하나의 책임만 가져야 한다.
- 하나의 책임이라는 것은 모호하다.
    - 클 수 있고, 작을 수 있다.
    - 문맥과 상황에 따라 다르다.
- 중요한 기준은 변경이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것
- 예) UI 변경, 객체의 생성과 사용을 분리

### 2. **OCP 개방-폐쇄 원칙 Open/closed principle (가장 중요한 원칙)**

- 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 이런 거짓말 같은 말이? 확장을 하려면, 당연히 기존 코드를 변경?
- 다형성을 활용해보자
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현
- 지금까지 배운 역할과 구현의 분리를 생각해보자

### OCP 개방-폐쇄 원칙 문제점

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/32b0b9e7-19dd-4e4c-bec5-769ca781d1d7)


- MemberService 클라이언트가 구현 클래스를 직접 선택
    - `MemberRepository m = new MemoryMemberRepository();` //기존 코드
    - `MemberRepository m = new JdbcMemberRepository();` //변경 코드
- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
- 이 문제를 어떻게 해결해야 하나?
- **객체를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다. (스프링 컨테이너의 역할)**

### 3. **LSP 리스코프 치환 원칙 Liskov substitution principle**

- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.
- 단순히 컴파일에 성공하는 것을 넘어서는 이야기
- 예) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반, 느리더라도 앞으로 가야함

### 4. **ISP 인터페이스 분리 원칙 Interface segregation principle**

- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다
- 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
- 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않음
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

### 5. DIP 의존관계 역전 원칙 Dependency inversion principle(중요)

- 프로그래머는 “추상화에 의존해야지, 구체화에 의존하면 안된다.” 의존성 주입은 이 원칙 을 따르는 방법 중 하나다.
- 쉽게 이야기해서 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻
- 앞에서 이야기한 역할(Role)에 의존하게 해야 한다는 것과 같다. 객체 세상도 클라이언트 가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다! 구현체에 의존하게 되면 변경이 아주 어려워진다.

### DIP 의존관계 역전 원칙 Dependency inversion principle

- 그런데 OCP에서 설명한 `MemberService`는 인터페이스에 의존하지만, 구현 클래스도 동시에 의존한다.
- `MemberService` 클라이언트가 구현 클래스를 직접 선택
- `MemberRepository m = new MemoryMemberRepository();`
- DIP 위반

### 정리

- 객체 지향의 핵심은 다형성
- 다형성 만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다.
- 다형성 만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다. 다형성 만으로는 OCP, DIP 를 지킬 수 없다.
- 뭔가 더 필요하다.(`AppConfig`와 같은 외부 컨트롤)

## 객체 지향 설계와 스프링

- 스프링은 다음 기술로 다형성 + OCP,DIP 를 가능하게 지원
    - DI(Dependency Injection): 의존관계, 의존성 주입
    - DI 컨테이너 제공
- 클라이언트 코드의 변경 없이 기능 확장
- 쉽게 부품을 교체하듯이 개발
- 옛날 어떤 개발자가 좋은 객체 지향 개발을 하려고 OCP, DIP 원칙을 지키면서 개발을 해 보니, 너무 할일이 많았다. 배보다 배꼽이 크다. 그래서 프레임워크로 만들어버림
- 순수하게 자바로 OCP, DIP 원칙들을 지키면서 개발을 해보면, 결국 스프링 프레임워크를 만들게 된다. (더 정확히는 DI 컨테이너)

## IoC, DI 컨테이너

### 제어의 역전 IoC(Inversion of Control)

- 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다. 한마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종했다. 개발자 입장에서는 자연스러운 흐름이다.
- 반면에 `AppConfig`가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당한다. **프로그램의 제어 흐름은 이제 AppConfig가 가져간다.** 예를 들어서 `OrderServiceImpl`은 필요한 인터페이스들을 호출하지만 어떤 구현 객체들이 실행될지 모른다.
- 프로그램에 대한 제어 흐름에 대한 권한은 모두 `AppConfig`가 가지고 있다. 심지어 `OrderServiceImpl` 도 `AppConfig`가 생성한다. 그리고 `AppConfig`는 `OrderServiceImpl`이 아닌 `OrderService` 인터페이스의 다른 구현 객체를 생성하고 실행할 수도 있다. 그런 사실도 모른체 `OrderServiceImpl`은 묵묵히 자신의 로직을 실행할 뿐이다.**(역할과 구현을 분리한 것)**
- 이렇듯 **프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부(`AppConfig`)에서 관리하는 것을 제어의 역전(IoC)**이라 한다.

### 의존관계 주입 DI(Dependency Injection)

- `OrderServiceImpl` 은 `DiscountPolicy` 인터페이스에 의존한다. 실제 어떤 구현 객체가 사용될지는 모른다.
- 의존관계는 정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스) 의존 관계 둘을 분리해서 생각해야 한다.

### 정적인 클래스 의존관계

클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단할 수 있다. 

정적인 의존관계는 애플리케이션을 실행하지 않아도 분석할 수 있다. 클래스 다이어그램을 보자 `OrderServiceImpl`은 `MemberRepository`, `DiscountPolicy`에 의존한다는 것을 알 수 있다. 

그런데 이러한 클래스 의존관계 만으로는 실제 어떤 객체가 `OrderServiceImpl`에 주입 될지 알 수 없다.

- 클래스 다이어그램
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c2069802-210d-4742-888b-728814bfee45)

    

### 동적인 객체 인스턴스 의존 관계

애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존관계이다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7a1f2ad3-400d-4140-9d30-363f2af1e8f7)


### IoC 컨테이너, DI 컨테이너

- AppConfig 처럼 객체를 생성하고 관리하면서 **의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너**라 한다.
- 의존관계 주입에 초점을 맞추어 최근에는 주로 **DI 컨테이너**라 한다.
- 또는 **어샘블러, 오브젝트 팩토리** 등으로 불리기도 한다.

## 스프링 컨테이너

- `ApplicationContext`를 스프링 컨테이너라 한다.
- 기존에는 개발자가 `AppConfig`를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.
- 스프링 컨테이너는 `@Configuration`이 붙은 `AppConfig`를 설정(구성) 정보로 사용한다. 여기서 `@Bean` 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된 객체를 **스프링 빈**이라 한다.
- 스프링 빈은 `@Bean` 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. (`memberService` , `orderService` )
- 이전에는 개발자가 필요한 객체를 `AppConfig`를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈(객체)를 찾아야 한다. 스프링 빈은 `applicationContext.getBean()`메서드를 사용해서 찾을 수 있다.
- 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.

### 스프링 컨테이너와 스프링 빈

```java
//스프링 컨테이너 생성
ApplicationContext applicationContext =
                            new
    AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext` 를 스프링 컨테이너라 한다.
- `ApplicationContext` 는 인터페이스이다.
- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
- 직전에 `AppConfig` 를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다.
- 자바 설정 클래스를 기반으로 스프링 컨테이너( `ApplicationContext` )를 만들어보자.
    - `new AnnotationConfigApplicationContext(AppConfig.class);`
    - 이 클래스는 ApplicationContext 인터페이스의 구현체이다.

> 참고: 더 정확히는 스프링 컨테이너를 부를 때 `BeanFactory`, `ApplicationContext`로 구분해서 이야기한다. 이 부분은 뒤에서 설명하겠다. `BeanFactory`를 직접 사용하는 경우는 거의 없으므로 일반적으로 `ApplicationContext`를 스프링 컨테이너라 한다.
> 

### 스프링 컨테이너 생성 과정

1. 스프링 컨테이너 생성
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a1a3b4e3-15fa-46a4-8b92-d05afc982417)

    
    - `new AnnotationConfigApplicationContext(AppConfig.class)`
    - 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다.
    - 여기서는 `AppConfig.class` 를 구성 정보로 지정했다.
2. 스프링 빈 등록
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/118bd7c5-a388-45a9-a4b3-b2a326876cbb)

    
    - 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.
    
    **빈 이름**
    
    - 빈 이름은 메서드 이름을 사용한다.
    - 빈 이름을 직접 부여할 수 도 있다.
        - `@Bean(name="memberService2")`
    
    > **주의**: 빈 이름은 항상 다른 이름을 부여해야 한다. 같은 이름을 부여하면, 다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.
    > 
3. 스프링 빈 의존관계 설정 - 준비
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/265544a1-1c09-4508-a039-57dc084faaf4)

    
4. 스프링 빈 의존관계 설정 - 완료
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/07debbbb-a7cf-4c33-9a95-7fe9e8748162)

    
    - 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다.
    - 단순히 자바 코드를 호출하는 것 같지만, 차이가 있다. 이 차이는 뒤에 싱글톤 컨테이너에서 설명한다.
    
    > **참고** 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다. 그런데 이렇게 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다. 여기서는 이해를 돕기 위해 개념적으로 나누어 설명했다. 자세한 내용은 의존관계 자동 주입에서 다시 설명하겠다.
    

## 컨테이너에 등록된 모든 빈 조회

- 스프링 컨테이너에 실제 스프링 빈들이 잘 등록되었는지 확인.

> **TIP**💡 리스트 형식의 객체가 있을 때, iter + tap을 누르면 for문이 자동 생성된다.
> 

### 전체 빈 확인

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/f664474b-afb5-41de-ba0d-20555494ca5f)


### 내가 입력한 스프링 빈만 보고싶을 때(혹은 스프링 내부에서 사용하는 빈을 보고싶을 때는 Role ROLE_INFRASTRUCTURE)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/2b87b1d2-a333-4545-bc76-a1ca21c4d39b)


- `ac.getBeanDefinitionNames()` : 스프링에 등록된 모든 빈 이름을 조회한다.
- `ac.getBean()` : 빈 이름으로 빈 객체(인스턴스)를 조회한다.

## 스프링 빈 조회 - 기본

### 스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법

- `ac.getBean(빈이름, 타입)`
- `ac.getBean(타입)`

### 조회 대상 스프링 빈이 없으면 예외 발생

- `NoSuchBeanDefinitionException: No bean named 'xxxxx' available`
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/3224ef7b-25df-4c18-9a33-bfe7aa084aca)

    
    > 참고: 구체 타입으로 조회하면 변경시 유연성이 떨어진다.
    > 

## 스프링 빈 조회 - 동일한 타입이 둘 이상

- 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
- `ac.getBeansOfType()`을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
- `org.springframework.beans.factory.NoUniqueBeanDefinitionException` 오류 발생하는 것을 이용해서 테스트
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/f0b38732-e1b3-4303-8cd6-325bf74d732a)

    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/d107fbb2-52c9-4e4b-a955-a1644d35493c)

    

## 스프링 빈 - 상속 관계

- 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
- 그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ac23565d-213a-4b61-8bcc-7ffa15e0b44e)

    
- 부모 타입으로 조회할 경우, 자식(`rateDiscountPolicy`, `fixDiscountPolicy`)이 함께 조회되므로 중복 오류(`NoUniqueBeanDefinitionException`)가 발생한다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/54bbbdbd-6d6d-4597-9506-103827ddd15c)

    
- 해결방법은 빈 이름을 지정하면된다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7992851b-eece-43e9-a97c-f9f44085b3cf)

    
- 특정 하위 타입으로 조회할 수도 있지만, DIP에 위반된다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/e14652d7-4fb1-45df-9c57-35de22f3cec1)

    
- 부모 타입으로 모두 조회하기, 연습이기 때문에 출력하여 확인한 것이다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/fb41f97e-346f-4f2f-868a-7a56bab4d3bd)

    
- 부모 타입으로 모두 조회하기 - Object
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/04e845f3-b35f-4c7e-b09b-7ca9eeeda3ac)


## BeanFactory와 ApplicationContext

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/06aa5bd7-82d1-4cff-a6a2-80a2f9342a7c)


### BeanFactory

- 스프링 컨테이너의 최상위 인터페이스다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- `getBean()`을 제공한다.
- 지금까지 우리가 사용했던 대부분의 기능은 `BeanFactory`가 제공하는 기능이다.

### ApplicationContext

- `BeanFactory` 기능을 모두 상속받아서 제공한다.
- 빈을 관리하고 검색하는 기능을 `BeanFactory`가 제공해주는데, 그러면 둘의 차이가 뭘까?
- 애플리케이션을 개발할 때는 **빈을 관리하고 조회하는 기능**은 물론이고, **수 많은 부가기능이 필요**하다.

> TIP💡 cmd + O를 누르면 외부 라이브러리까지 볼 수 있음.
> 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/b487536e-32a3-4a3f-b02b-bf85c9eef6bd)


> cmd + B
> 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/6aa21ca1-9a2e-40c5-943e-aaac709ab667)

**ApplicatonContext가 제공하는 부가기능**

<img width="687" alt="스크린샷 2023-08-06 오후 8 35 44" src="https://github.com/Hoya324/COW-Spring-1/assets/96857599/63de7f50-e82d-46d8-8a05-5288bb8e19fd">

- **메시지소스를 활용한 국제화 기능**
  - 예를 들어서 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
- **환경변수**~
  - 로컬, 개발, 운영등을 구분해서 처리
- **애플리케이션 이벤트**
  - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
- **편리한 리소스 조회**
  - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회


### 정리

- `ApplicationContext`는 `BeanFactory`의 기능을 상속받는다.
- `ApplicationContext`는 빈 관리기능 + 편리한 부가 기능을 제공한다.
- `BeanFactory`를 직접 사용할 일은 거의 없다. 부가기능이 포함된 `ApplicationContext`를 사용한다.
- `BeanFactory`나 `ApplicationContext`를 **스프링 컨테이너**라 한다.

## 다양한 설정 형식 지원 - 자바 코드, XML

- 스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계되어 있다.
    - 자바 코드, XML, Groovy 등등 (요즘엔 자바 코드를 주로 많이 씀)
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/6151d4af-f1b4-4fe5-b6ab-8900ebd19688)

    

### 애노테이션 기반 자바 코드 설정 사용

- 지금까지 했던 것이다.
- `new AnnotationConfigApplicationContext(AppConfig.class)`
- `AnnotationConfigApplicationContext` 클래스를 사용하면서 자바 코드로된 설정 정보를 넘기면 된다.

### XML 설정 사용

- 최근에는 스프링 부트를 많이 사용하면서 XML기반의 설정은 잘 사용하지 않는다. 아직 많은 레거시 프로젝트 들이 XML로 되어 있고, 또 XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있으므로 한번쯤 배워두는 것도 괜찮다.
- `GenericXmlApplicationContext`를 사용하면서 xml 설정 파일을 넘기면 된다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/4957f39e-7d25-4c52-a58c-1460f4da5cf6)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/52215089-40e2-4798-ac26-5fb95d64fdda)


- xml 기반의 appConfig.xml 스프링 설정 정보와 자바 코드로 된 `AppConfig.java`설정 정보를 비교해보면 거의 비슷하다는 것을 알 수 있다.
- xml 기반으로 설정하는 것은 최근에 잘 사용하지 않으므로 이정도로 마무리 하고, 필요하면 스프링 공식 레퍼런스 문서를 확인하자.
    - https://spring.io/projects/spring-framework

### 스프링 빈 설정 메타 정보 - BeanDefinition

- 스프링은 어떻게 이런 다양한 설정 형식을 지원하는 것일까? 그 중심에는 `BeanDefinition` 이라는 추상화가 있다.
- 쉽게 이야기해서 **역할과 구현을 개념적으로 나눈 것**이다!
    - XML을 읽어서 `BeanDefinition`을 만들면 된다.
    - 자바 코드를 읽어서 `BeanDefinition`을 만들면 된다.
    - 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 `BeanDefinition`만 알면 된다.
- `BeanDefinition`을 빈 설정 메타정보라 한다.
    - `@Bean` , `<bean>` 당 각각 하나씩 메타 정보가 생성된다.
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/66c384fc-ec85-4189-844d-652b887112dc)


- `AnnotationConfigApplicationContext`는 `AnnotatedBeanDefinitionReader`를 사용해서 `AppConfig.class` 를 읽고 `BeanDefinition` 을 생성한다.
- `GenericXmlApplicationContext`는 `XmlBeanDefinitionReader`를 사용해서 `appConfig.xml` 설정 정보를 읽고 `BeanDefinition` 을 생성한다.
- 새로운 형식의 설정 정보가 추가되면, `XxxBeanDefinitionReader`를 만들어서 `BeanDefinition` 을 생성하면 된다.

### BeanDefinition 정보

- BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
- factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
- factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
- Scope: 싱글톤(기본값)
- lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부
- InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
- DestroyMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
- Constructor arguments, Properties: 의존관계 주입에서 사용한다. (자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/e8a3db77-5cbc-42d3-9945-29d016ac436b)


### 정리

- `BeanDefinition`을 직접 생성해서 스프링 컨테이너에 등록할 수 도 있다. 하지만 실무에서 `BeanDefinition`을 직접 정의하거나 사용할 일은 거의 없다.
- `BeanDefinition`에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를 `BeanDefinition`으로 추상화해서 사용하는 것 정도만 이해하면 된다.
- 가끔 스프링 코드나 스프링 관련 오픈 소스의 코드를 볼 때, `BeanDefinition` 이라는 것이 보일 때가 있다. 이때 이러한 메커니즘을 떠올리면 된다.

## 싱글톤 컨테이너 - 웹 애플리케이션과 싱글톤

- 스프링은 태생이 기업용 온라인 서비스 기술을 지원하기 위해 탄생했다.
- 대부분의 스프링 애플리케이션은 웹 애플리케이션이다. 물론 웹이 아닌 애플리케이션 개발도 얼마든지
개발할 수 있다.
- 웹 애플리케이션은 고객의 끊임 없는 요청이 발생한다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7be59208-999f-4e89-9877-03da8fff5831)

    

- 요청할 때마다 다른 객체가 생성됨.

**스프링 없는 순수한 DI 컨테이너 테스트**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/b9c83e67-9490-4cae-962b-3accf09d90fb)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/3124d7bc-340d-4cc6-9536-93a55dee117c)


- 우리가 만들었던 스프링 없는 순수한 DI 컨테이너인 `AppConfig`는 요청을 할 때 마다 객체를 새로 생성한다.
- 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! → 메모리 낭비가 심하다.
- 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. → 싱글톤 패턴

## 싱글톤 패턴

- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
- private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/0f136a94-afca-4949-8339-b150732f17e0)


1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를
호출하면 항상 같은 인스턴스를 반환한다.
3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new
키워드로 객체 인스턴스가 생성되는 것을 막는다.
- new로 인한 객체 인스턴스 생성 막음

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/4a041b0e-8768-4dc0-add8-d5d6975cfa29)


- 같은 인스턴스를 사용하는 것을 알 수 있음

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/0e1b4aaa-d66d-4891-9812-3013ffff4e1a)


- private으로 new 키워드를 막아두었다.
- 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.

> 참고: 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택했다.
> 

### 싱글톤 패턴 문제점

- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 **구체 클래스에 의존**한다. -> **DIP를 위반**한다.
- 클라이언트가 구체 클래스에 의존해서 **OCP 원칙을 위반**할 가능성이 높다.
- 테스트하기 어렵다.
- **내부 속성을 변경하거나 초기화 하기 어렵다.**
- private 생성자로 자식 클래스를 만들기 어렵다.
- 결론적으로 **유연성이 떨어진다.**
- 안티패턴으로 불리기도 한다.

## 싱글톤 컨테이너

- 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
    - 컨테이너는 객체를 하나만 생성해서 관리한다.
- **스프링 컨테이너는 싱글톤 컨테이너 역할**을 한다.
    - **싱글톤 레지스트리:** 싱글톤 객체를 생성하고 관리하는 기능
- 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
    - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
    - **DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.**

**스프링 컨테이너를 사용하는 테스트 코드**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/eb6ca1f0-606f-4ef6-9625-cf926baa4844)


**싱글톤 컨테이너 적용 후**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ef12ed2a-f67c-4e8c-8da7-fd7700c8377d)


- 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다.

> **참고**: 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할 때 마다 새로운 객체를 생성해서 반환하는 기능도 제공한다.
> 

## ⭐️싱글톤 방식의 주의점⭐️

- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, **객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.**

### ⭐️**무상태(stateless)로 설계해야 한다!**

- **특정 클라이언트에 의존적인 필드가 있으면 안된다.**
- 특정 클라이언트가 **값을 변경할 수 있는 필드가 있으면 안된다!**
- **가급적 읽기만 가능**해야 한다.
- **필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용**해야 한다.
- **스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생**할 수 있다!!!

**상태를 유지할 경우 발생하는 문제점 예시**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/86555d23-7b52-492e-851e-0c65bd745fc8)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/96c0217d-2eea-4354-80a6-b7f3135a4ce2)


- 최대한 단순히 설명하기 위해, 실제 쓰레드는 사용하지 않았다.
- ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정하자.
- `StatefulService`의 `price` 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
- 사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.
- 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다.
- **진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.**

**해결코드** 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a258ffd5-508d-4062-91f6-f39890379494)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/021ec390-40be-46a3-8f64-7ddfc7e09173)


## @Configuration과 싱글톤

- `@Configuration`은 싱글톤을 위해 존재하는 것이다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/968f70af-a8e6-4e72-a07c-4e485fb21c95)


- AppConfig에서 `memberService` 빈을 만드는 코드를 보면 `memberRepository()`를 호출한다.
    - 이 메서드를 호출하면 `new MemoryMemberRepository()`를 호출한다.
- orderService 빈을 만드는 코드도 동일하게 `memberRepository()`를 호출한다.
    - 이 메서드를 호출하면 `new MemoryMemberRepository()`를 호출한다.

> 결과적으로 각각 다른 2개의 `MemoryMemberRepository`가 생성되면서 싱글톤이 깨지는 것 처럼 보인다. 스프링 컨테이너는 이 문제를 어떻게 해결할까?
> 

**검증 용도의 코드 추가**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/be65247d-b73c-4d9b-bab8-f1ce366482e8)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a9108bee-281c-48e2-8325-22a80abaada2)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/1a932f28-49a0-40f9-9988-aaf7f04f875e)


- 확인해보면 `memberRepository` 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.
- `AppConfig`의 자바 코드를 보면 분명히 각각 2번 `new MemoryMemberRepository`를 호출해서 다른 인스턴스가 생성되어야 하지만 같은 인스턴스로 보인다.

### AppConfig 호출로그 남기기

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ee1b9e7c-61ef-413e-81d7-84d58d0247a9)


- `memberRepository`가 3번 출력될 것이라는 예상과 다르게 1번만 출력되었다.

### @Configuration과 바이트코드 조작의 마법

- 스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다.
- 그런데 스프링이 자바 코드까지 어떻게 하기는 어렵다. 저 자바 코드를 보면 분명 3번 호출되어야 하는 것이 맞다. 그래서 **스프링은 클래스의 바이트코드를 조작하는 라이브러리를 사용**한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/1594dc80-c39e-4eb1-8cfe-b4cac4001cd7)


- 순수한 클래스라면 다음과 같이 출력되어야 한다. `class hello.core.AppConfig`
- 그런데 예상과는 다르게 클래스 명에 `xxxCGLIB`가 붙으면서 상당히 복잡해진 것을 볼 수 있다.
- 이것은 내가 만든 클래스가 아니라 스프링이 `CGLIB`라는 바이트코드 조작 라이브러리를 사용해서 `AppConfig` 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/0c61763d-6af3-48c7-aa32-fe48ad4eb41d)

    

**AppConfig@CGLIB 예상 코드**

```java
@Bean
public MemberRepository memberRepository() {
    if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
        return 스프링 컨테이너에서 찾아서 반환;
    } else { //스프링 컨테이너에 없으면
        기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
        return 반환
    }
}
```

- @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
- 덕분에 싱글톤이 보장되는 것이다.

> 참고 `AppConfig@CGLIB`는 `AppConfig`의 자식 타입이므로, `AppConfig` 타입으로 조회 할 수 있다.
> 

### 정리

- `@Bean`만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
- `memberRepository()`처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
- 크게 고민할 것이 없다. 스프링 설정 정보는 항상 `@Configuration`을 사용하자.


## 컴포넌트 스캔과 의존관계 자동 주입 시작하기

- 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능을 제공한다.

**AutoAppConfig.java**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/fb407c02-3a59-4706-9e8a-0b6dcaae8ecb)


- 컴포넌트 스캔을 사용하려면 먼저 `@ComponentScan`을 설정 정보에 붙여주면 된다.
- 기존의 `AppConfig`와는 다르게 `@Bean`으로 등록한 클래스가 하나도 없다!

> **참고:** 컴포넌트 스캔을 사용하면 `@Configuration`이 붙은 설정 정보도 자동으로 등록되기 때문에, `AppConfig`, `TestConfig` 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다.
> 

> 그래서 `excludeFilters` 를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서 이 방법을 선택했다.
> 

컴포넌트 스캔은 이름 그대로 `@Component` 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다. `@Component`를 붙인다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/17394607-3dc0-41a9-9475-88f7de28670e)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/9a7480f4-9280-40af-972e-738bec7c819e)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/586c9393-fe5c-41c1-b37d-3ed7bfdd5a38)


- 의존관계를 주입하기 위한 방법 -> **`@Autowired`를 붙이면 자동으로 의존관계를 주입**해준다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c0ce466a-29db-4559-99b6-7f653be8e33d)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c9dbb60c-9e6a-4f99-b888-693e5136cef1)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c437e2e6-fe26-4395-a4e2-82e6138cdab6)


- `@Autowired` 를 사용하면 생성자에서 여러 의존관계도 한번에 주입받을 수 있다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/980eb961-f177-415b-9374-fa54bd805600)


- 로그를 잘 보면 컴포넌트 스캔이 잘 동작하는 것을 확인할 수 있다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/52bd8f7c-b970-430f-8996-2263573c5b62)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/4f1e5908-b90e-4f5a-ad54-3f06d4da26e3)


- `@ComponentScan` 은 `@Component` 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
    - **빈 이름 기본 전략**: `MemberServiceImpl`클래스 -> `memberServiceImpl`
    - **빈 이름 직접 지정**: 만약 스프링 빈의 이름을 직접 지정하고 싶으면 `@Component("memberService2")` 이런식으로 이름을 부여하면 된다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/22a66863-a0ce-4994-a580-f52ff769eaa4)


- 생성자에 `@Autowired` 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입한다.
- 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
    - `getBean(MemberRepository.class)` 와 동일하다고 이해하면 된다.
- 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입한다.
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/6b15f148-9b74-452f-8c31-5f6aec4ccdb5)

    

### 정리

- `@Component`가 붙은 클래스들이 **스프링 컨테이너에 저장**된다.
- `@Autowired`를 통해 **의존성을 주입**해준다.

## 탐색 위치와 기본 스캔 대상

### **탐색할 패키지의 시작 위치 지정**

모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터 탐색하도록 시작
위치를 지정할 수 있다.

```java
@ComponentScan(
  basePackages = "hello.core",
}
```

- `basePackages` : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
    - `basePackages = {"hello.core", "hello.service"}` 이렇게 여러 시작 위치를 지정할 수도 있다.
- `basePackageClasses` : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
- 만약 지정하지 않으면 `@ComponentScan`이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

### 권장하는 방법

- 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
- 최근 스프링 부트도 이 방법을 기본으로 제공한다.

예를 들어서 프로젝트가 다음과 같이 구조가 되어 있으면

- `com.hello`
- `com.hello.serivce`
- `com.hello.repository`
- `com.hello` 프로젝트 시작 루트, 여기에 `AppConfig` 같은 메인 설정 정보를 두고, `@ComponentScan` 어노테이션을 붙이고, `basePackages` 지정은 생략한다.

이렇게 하면 `com.hello` 를 포함한 하위는 모두 자동으로 컴포넌트 스캔의 대상이 된다. 

그리고 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는 것이 좋다 생각한다.

> 참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 `@SpringBootApplication`를 이 프로젝트 시작 루트 위치에 두는 것이 관례이다. (그리고 이 설정안에 바로 `@ComponentScan`이 들어있다.)
> 

### 컴포넌트 스캔 기본 대상

컴포넌트 스캔은 `@Component` 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다. (모두 `@Component`를 포함한다.)

- `@Component` : 컴포넌트 스캔에서 사용
- `@Controlller` : 스프링 MVC 컨트롤러에서 사용
- `@Service` : 스프링 비즈니스 로직에서 사용
- `@Repository` : 스프링 데이터 접근 계층에서 사용
- `@Configuration` : 스프링 설정 정보에서 사용

> 참고: 사실 어노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 어노테이션이 특정 어노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능이다.
> 

컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 스프링은 부가 기능을 수행한다.

- `@Controller` : 스프링 MVC 컨트롤러로 인식
- `@Repository` : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다. (데이터 베이스에 따른 예외를 서비스 계층까지 가지 않도록 한다.)
- `@Configuration` : 앞서 보았듯이 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
- `@Service` : 사실 `@Service`는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을 인식하는데 도움이 된다.

> **참고**: `useDefaultFilters` 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
> 

## 필터

- `includeFilters` : 컴포넌트 스캔 대상을 추가로 지정한다.
- `excludeFilters` : 컴포넌트 스캔에서 제외할 대상을 지정한다.
- `MyIncludeComponent`와 `MyExcludeComponent` 어노테이션을 생성한다.

**컴포넌트 스캔 대상에 추가할 애노테이션**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/83bbb65a-360e-49d8-a372-a06b656c2560)


**컴포넌트 스캔 대상에서 제외할 애노테이션**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/f778c507-a7b2-4e5c-9d32-c95d0ec511d1)


- `@MyIncludeComponent` 어노테이션을 갖는 `BeanA`를 생성한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/dc2d5d79-1c1c-4747-852e-e079393b47ef)


- `@MyExcludeComponent` 어노테이션을 갖는 `BeanB`를 생성한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/15a80bf7-6aac-429f-bcd5-9c46aed0848a)


- `includeFilters`에 `MyIncludeComponent` 어노테이션을 추가해서 `BeanA`가 스프링 빈에 등록된다.
- `excludeFilters`에 `MyExcludeComponent` 어노테이션을 추가해서 `BeanB`는 스프링 빈에 등록되지 않는다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7efe4d59-f7a7-4f9c-ac70-4f2b6185f37c)


### FilterType 옵션

FilterType은 5가지 옵션이 있다.

- ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
    - ex) `org.example.SomeAnnotation`
- ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
    - ex) `org.example.SomeClass`
- ASPECTJ: AspectJ 패턴 사용
    - ex) `org.example..*Service+`
- REGEX: 정규 표현식
    - ex) `org.example.Default.*`
- CUSTOM: `TypeFilter` 이라는 인터페이스를 구현해서 처리
    - ex) `org.example.MyTypeFilter`

> **참고**: `@Component` 면 충분하기 때문에, `includeFilters`를 사용할 일은 거의 없다. `excludeFilters`는 여러가지 이유로 간혹 사용할 때가 있지만 많지는 않다. 특히 최근 스프링 부트는 컴포넌트 스캔을 기본으로 제공하는데, 개인적으로는 옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장하고, 선호하는 편이다.
> 

### 중복 등록과 충돌

1. **자동빈등록vs자동빈등록**
- 컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 스프링은 오류를 발생시킨다.
    - `ConflictingBeanDefinitionException` 예외 발생

2. **수동빈등록vs자동빈등록**
- `AutoAppConfig`에 `memoryMemberRepository`라는 이름으로 bean 등록을 해준다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/e7a8b10e-49ae-4a59-b7f6-e128e3a4cccc)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/5676d5ca-9c8f-4fee-b6dd-54c0c7d6c225)


- 둘의 이름이 같아서 오류가 생겨야하지만 생기지 않았다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/906f8e44-3283-49f9-9661-c83241a07ac2)


- 이유는 **이런 경우에 수동 빈 등록이 우선권을 가진다.** (수동 빈이 자동 빈을 오버라이딩 해버린다.)
- 로그를 통해 알 수 있다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/84de878a-4783-40d1-be61-fc84ac00b7b2)


> 물론 개발자가 의도적으로 이런 결과를 기대했다면, 자동 보다는 수동이 우선권을 가지는 것이 좋다. 하지만 현실은 개발자가 의도적으로 설정해서 이런 결과가 만들어지기 보다는 여러 설정들이 꼬여서 이런 결과가 만들어지는 경우가 대부분이다! 그러면 정말 잡기 어려운 버그가 만들어진다. 항상 잡기 어려운 버그는 애매한 버그다. **그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었다.**
> 
- 스프링부트 실행시

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/01937504-7f35-401a-8f12-54f3de1fa9fc)


```
Description:

The bean 'memoryMemberRepository', defined in class path resource [hello/springStudyBasic/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in file [/Users/gangho/Desktop/springStudyBasic/out/production/classes/hello/springStudyBasic/member/MemoryMemberRepository.class] and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true

```

> 코드를 더 써야할지라도, 명확하고 확실하게 작성하는게 좋다. → 그렇지 않으면 더 큰 버그가 발생한다!


## 다양한 의존관계 주입 방법

### 생성자 주입

- 이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
- 특징
    - 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    - **불변, 필수 의존관계에 사용**
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7a3c1623-a171-4d4a-a757-cdf33fde14c6)

    

### ⭐️중요! 생성자가 딱 1개만 있으면 `@Autowired`를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당한다.

### 수정자 주입(setter 주입)

- setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
- 특징
    - **선택, 변경** 가능성이 있는 의존관계에 사용 (스프링 빈에 등록된 것만 선택할 수 있다.)
    - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.

> **참고**: `@Autowired` 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대상이 없어도 동작하게 하려면 `@Autowired(required = false)` 로 지정하면 된다.
> 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/f1d56a17-cc7f-4f9b-9535-05a49722af14)


> **참고**: 자바빈 프로퍼티, 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, setXxx, getXxx 라는 메서드를 통해서 값을 읽거나 수정하는 규칙을 만들었는데, 그것이 자바빈 프로퍼티 규약이다. 더 자세한 내용이 궁금하면 자바빈 프로퍼티로 검색해보자.
> 

**자바빈 프로퍼티 규약 예시**

```java
 class Data {
	  private int age;
	  
		public void setAge(int age) {
			  this.age = age;
	  }
	  public int getAge() {
		    return age;
		}
}
```

### 필드 주입

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/e978e26a-2e86-42eb-9713-f2e8b3bf48a9)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ecc9c0a9-3b86-4d1e-9fe7-adf2d95db59b)


- 이름 그대로 필드에 바로 주입하는 방법이다.
- 특징
    - 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
    - DI 프레임워크가 없으면 아무것도 할 수 없다.
    - **사용하지 말자!**
        - 애플리케이션의 실제 코드와 관계 없는 테스트 코드
        - 스프링 설정을 목적으로 하는 `@Configuration` 같은 곳에서만 특별한 용도로 사용

```java
@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;
}
```

> **참고**: 순수한 자바 테스트 코드에는 당연히 `@Autowired`가 동작하지 않는다. `@SpringBootTest` 처럼 스프링 컨테이너를 테스트에 통합한 경우에만 가능하다.
> 

> **참고**: 다음 코드와 같이 `@Bean` 에서 파라미터에 의존관계는 자동 주입된다. 수동 등록시 자동 등록된 빈의 의존관계가 필요할 때 문제를 해결할 수 있다.
> 

```java
@Bean
OrderService orderService(MemberRepository memberRepoisitory, DiscountPolicy
discountPolicy) {
    new OrderServiceImpl(memberRepository, discountPolicy)
}
```

### 일반 메서드 주입

- 일반 메서드를 통해서 주입 받을 수 있다.
- 특징
    - 한번에 여러 필드를 주입 받을 수 있다.
    - 일반적으로 잘 사용하지 않는다.

> **참고**: 어쩌면 당연한 이야기이지만 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다. 스프링 빈이 아닌 `Member` 같은 클래스에서 `@Autowired` 코드를 적용해도 아무 기능도 동작하지 않는다.
> 

## 옵션 처리

주입할 스프링 빈이 없어도 동작해야 할 때가 있다. 그런데 `@Autowired`만 사용하면 `required` 옵션의 기본값이 `true` 로 되어 있어서 자동 주입 대상이 없으면 오류가 발생한다.

자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같다.

- `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
- `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 null이 입력된다.
- `Optional<>` : 자동 주입할 대상이 없으면 `Optional.empty`가 입력된다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/0a9f1997-ea08-4971-9d3c-035cc895a90c)


- `Member`는 스프링 빈이 아니다
- `setNoBean1()`은 `@Autowired(required = false)`이므로 호출 자체가 안 된다.

## 생성자 주입을 선택하는 이유

### 불변

- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
- 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다.
- 누군가 실수로 변경할 수도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다.

### 누락

- 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우에 다음과 같이 수정자 의존관계인 경우
- 아래의 코드처럼 수정자 주입인 경우

```java
public class OrderServiceImpl implements OrderService {
		private MemberRepository memberRepository;
		private DiscountPolicy discountPolicy;

		@Autowired
		public void setMemberRepository(MemberRepository memberRepository) {
		    this.memberRepository = memberRepository;
		}

		@Autowired
		public void setDiscountPolicy(DiscountPolicy discountPolicy) {
		    this.discountPolicy = discountPolicy;
		}

		//...
}
```

- `@Autowired`가 프레임워크 안에서 동작할 때는 의존관계가 없으면 오류가 발생하지만, 지금은 프레임워크 없이 순수한 자바 코드로만 단위 테스트를 수행하고 있다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/8042fd48-f4ad-420c-a792-8af564d1ed98)


### final 키워드

- 생성자 주입을 사용하면 필드에 `final` 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/1d89a14f-093d-4d58-a36d-978f4075a638)


- 잘 보면 필수 필드인 `discountPolicy` 에 값을 설정해야 하는데, 이 부분이 누락되었다. 자바는 컴파일 시점에 다음 오류를 발생시킨다.
- `java: variable discountPolicy might not have been initialized`
- **컴파일 오류는 세상에서 가장 빠르고, 좋은 오류다!**

> **참고**: 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 `final` 키워드를 사용할 수 없다. 오직 생성자 주입 방식만 `final` 키워드를 사용할 수 있다.
> 

### 정리

- 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
- 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
- 항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지 않는게 좋다.

### 롬복(Lombok)을 이용한 생성자 주입

```java
@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
}
```

- `@RequiredArgsConstructor`는 **`final` 혹은 `@NotNull`**이 붙은 **필드의 생성자를 자동으로 만들어**준다.

## NoUniqueBeanDefinitionException 오류(**조회 빈이 2개 이상 - 문제**)

- `@Autowired` 는 타입(Type)으로 조회한다.

```java
@Autowired
private DiscountPolicy discountPolicy

```

- 타입으로 조회하기 때문에, 마치 다음 코드와 유사하게 동작한다. `ac.getBean(DiscountPolicy.class)`
- 스프링 빈 조회에서 학습했듯, 타입으로 조회하면 선택된 빈이 2개 이상일 때 문제가 발생한다.
- `DiscountPolicy` 의 하위 타입인 `FixDiscountPolicy` , `RateDiscountPolicy` 둘다 스프링 빈으로
선언해보자.

```java
@Component
public class FixDiscountPolicy implements DiscountPolicy {}

```

```java
@Component
public class RateDiscountPolicy implements DiscountPolicy {}

```

- 그리고 이렇게 의존관계 자동 주입을 실행하면

```java
@Autowired
private DiscountPolicy discountPolicy

```

- `NoUniqueBeanDefinitionException` 오류가 발생한다.

```
NoUniqueBeanDefinitionException: No qualifying bean of type
'hello.core.discount.DiscountPolicy' available: expected single matching bean
but found 2: fixDiscountPolicy,rateDiscountPolicy
```

- 오류메시지가 친절하게도 하나의 빈을 기대했는데 `fixDiscountPolicy` , `rateDiscountPolicy` 2개가
발견되었다고 알려준다.

## 해결방법

- `@Autowired` 필드명 매칭
- `@Quilifier` -> `@Quilifier`끼리 매칭
    - 빈 이름 매칭
- `@Primary` 사용

### 1. @Autowired 필드 명 매칭(빈 이름으로 매칭을 시도하는 것)

- `DiscountPolicy`의 객체 인스턴스명을 `discountPolicy` → `rateDiscountPolicy`
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/dfa716d7-edcb-4488-8db0-c05b0519591e)

    
- 테스트 성공
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/9141fb88-917c-4089-90a2-0d20b3ff1c77)

    

필드명이 `rateDiscountPolicy` 이므로 정상 주입된다. 필드 명 매칭은 먼저 타입 매칭을 시도 하고 그 결과에 여러 빈이 있을 때 추가로 동작하는 기능이다.

**@Autowired 매칭 정리**

1. 타입 매칭
2. 타입 매칭의 결과가 2개 이상일 때 **필드 명, 파라미터명으로 빈 이름 매칭**

### 2. @Qualifier 사용

`@Qualifier` 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.

- **빈 등록시** `@Qualifier`**를 붙여 준다.**
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/bc1d93c2-7578-4b5f-8ae8-b1f87a6bb1c2)

  
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a34a79e2-9437-4fa0-a9b8-b0d73268d413)

    
- 중복되는 생성자의 객체 앞에 `@Qualifier`을 넣어준다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/64bf0154-fcab-4c38-a4dd-01d32ebd8ca0)


- `@Qualifier` 로 주입할 때 `@Qualifier("mainDiscountPolicy")`를 못찾으면 `mainDiscountPolicy`라는 이름의 스프링 빈을 추가로 찾는다.
- 하지만 `@Qualifier`는 `@Qualifier`를 찾는 용도로만 사용하는게 명확하고 좋다.

**@Qualifier 정리**

1. `@Qualifier`끼리 매칭
2. 빈 이름 매칭
3. `NoSuchBeanDefinitionException` 예외 발생

### 3. @Primary 사용 (자주 사용하는 방법)

- `@Primary` 는 우선순위를 정하는 방법이다. `@Autowired` 시에 여러 빈이 매칭되면 `@Primary` 가 우선권을 가진다.

**rateDiscountPolicy에 우선권**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/9633ced5-fb2d-4877-9bfb-3297ae197b22)


- 여기까지 보면 `@Primary`와 `@Qualifier` 중에 어떤 것을 사용하면 좋을지 고민이 될 것이다.
- `@Qualifier` 의 단점은 주입 받을 때 모든 코드에 `@Qualifier`를 붙여주어야 한다는 점이다.
- `@Primary` 를 사용하면 이렇게 `@Qualifier` 를 붙일 필요가 없다.

### **@Primary, @Qualifier 활용**

- 메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 `@Primary` 를 적용해서 조회하는 곳에서 `@Qualifier` 지정 없이 편리하게 조회한다.
- 서브 데이터베이스 커넥션 빈을 획득할 때는 `@Qualifier`를 지정해서 명시적으로 획득 하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다.

### 우선순위

`@Primary`는 기본값 처럼 동작하는 것이고, `@Qualifier`는 매우 상세하게 동작한다. 이런 경우 어떤 것이 우선권을 가져갈까? 스프링은 자동보다는 수동이, 넒은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다. 따라서 여기서도 `@Qualifier`가 우선권이 높다.


## **조회한 빈이 모두 필요할 때, List, Map**

- 의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
- 예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate, fix)를 선택할 수 있다고 가정해보자.
- Map에는 key(Stirng), value(스프링 빈)이, List에는 value(스프링 빈)이 저장된다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/cd03cbd9-6fc8-4c25-abd5-da00d08f7364)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/de1b6439-8037-482d-9d86-21c7f6e6bb9c)


![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/bfbb55f6-c068-4b82-a55c-af60738e3169)


**로직 분석**

- `DiscountService`는 Map으로 모든 `DiscountPolicy`를 주입받는다. 이때 `fixDiscountPolicy`, `rateDiscountPolicy`가 주입된다.
- `discount()` 메서드는 `discountCode`로 "fixDiscountPolicy"가 넘어오면 map에서 `fixDiscountPolicy` 스프링 빈을 찾아서 실행한다. 물론 “rateDiscountPolicy”가 넘어오면 `rateDiscountPolicy` 스프링 빈을 찾아서 실행한다.

**주입 분석**

- `Map<String, DiscountPolicy>` : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로
`DiscountPolicy` 타입으로 조회한 모든 스프링 빈을 담아준다.
- `List<DiscountPolicy>` : `DiscountPolicy` 타입으로 조회한 모든 스프링 빈을 담아준다.
- 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.
