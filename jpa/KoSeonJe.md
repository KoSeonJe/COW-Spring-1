### 1주차

1. JPQL
2. 영속성 컨텍스트
3. 엔터티 매핑

### 2주차

1. 연관관계 매핑
2. 다양한 매핑
3. 단방향, 양방향
4. Spring Data JPA

## 1주차

## 1. JPQL(Java Persistence Query Language)

- SQL을 추상화한 JPQL이라는 객체 지향 쿼리 언어를 제공
- 테이블을 대상으로 쿼리하는 것이 아닌 **엔터티 객체**를 대상으로 쿼리한다.
- JPQL은 SQL과 문법이 유사하며, SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN을 지원한다.
- 결국에는 SQL로 변환된다.

- 예시

```sql
String jpql = "select m From Member m where m.name like ‘%hello%'";
List<Member> result = em.createQuery(jpql, Member.class).getResultList();

for (Member member : result) {
    System.out.println("member = " + member);
}
```

→ 객체(엔터티)를 대상으로 검색하는 객체지향 쿼리 언어

### JPQL 문제점

- 컴파일 에러를 발생시키지 않는다.

→ 문제가 있음에도 정상적으로 작동하여 배포 시 문제가 발생할 수 있다.

- 동적으로 쿼리 언어를 작성하는 데 효율적이지 못하다.

→ 예 ) 특정 조건의 참일 경우엔 A SQL쿼리를, 거짓일 경우엔 B쿼리를 실행하는 등

### JPQL과 SQL의 큰 차이점

- JPQL은 엔터티 객체를 대상으로 쿼리문을 작성
- SQL은 데이터베이스 테이블을 대상으로 쿼리문을 작성

### JPQL 문법

```sql
select _ from _ [where] _ [groupby] _ [having] _ [orderby] _

update _ [where] _

delete _ [where] _
```

### JPQL 특징

```sql
select m from Member as m where m.age>18;
```

- 엔터티와 속성은 대소문자를 구분한다.(Member(엔터티),age(속성))
- ‘@Entity’의 name을 지정하지 않으면, 클래스 이름이 엔티티 이름이다.
- JPQL키워드는 대소문자를 구분하지 않는다.(Select, FROM, where)
- 테이블 이름이 아닌 엔터티 이름을 사용한다.(Member, Team)
- 별칭(m) 사용은 필수적이다.
- as는 생략이 가능하다.

### TypeQuery

- 반환 타입이 명확할 때 사용한다.

```sql
TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
```

### Query

- 반환 타입이 명확하지 않을 때 사용

```sql
Query query3 = em.createQuery("select m.username, m.age from Member m");
```

### 결과 조회 API

- getResultList()
    - 결과가 하나 이상일 때 사용, 리스트로 반환한다.
    - 결과가 없을 경우 빈 리스트를 반환.

```sql
TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
List<Member> resultList = query1.getResultList();

for (Member m : resultList) {
    System.out.println("m = " + m.getUsername());
}
```

- getSingleResult()
    - 결과가 단 하나일 경우 사용. 단일 객체를 반환한다.
    - 결과가 없거나 둘 이상일 때 예외 발생

```sql
TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.id = 1L", Member.class);
Member singleResult = query2.getSingleResult();

System.out.println("singleResult = " + singleResult.getUsername());
```

## 2. 영속성 컨텍스트

### 영속성 컨텍스트란?

- 영속성 컨텍스트는 엔터티를 영구 저장하는 환경이라는 뜻이다.
- 영속성 컨텍스트는 애플리케이션과 DB사이에서 객체를 보관하는 가상의 DB 역할을 한다.
- 엔터티 매니저를 통해 엔터티를 저장하거나 조회하면 엔터티 매니저는 영속성 컨텍스트에 엔터티를 보관하고 관리하게 한다.

### 영속성 컨텍스트 특징

- 영속성 컨텍스트는 엔티티를 식별자 값으로 구분한다. 따라서 영속 상태는 식별자 값이 반드시 있어야 한다.
- JPA는 보통 트랜잭션을 커밋하는 순간 영속성 컨텍스트에 새로 저장된 엔티티를 데이터 베이스에 반영하는 데 이를 flush라 한다.

### 엔터티 생명 주기

- 비영속상태 : 영속성 컨텍스트와 관계가 없는 상태

```java
Member member = new Member();
member.setId("member1");
member.setUsername("회원1");
```

- 영속 상태 : 영속성 컨텍스트에 저장된 상태

```java
EntityManager em = emf.createEntityManager();
em.getTransaction().begin();
//객체를 저장한 상태(영속)
em.persist(member);
```

- 준영속 상태 : 영속성 컨텍스트에 저장되었다가 분리된 상태

```java
//회원 엔터티를 영속성 컨텍스트에서 분리, 준영속 상태
em.detach(member);
```

- 삭제 상태 : 삭제된 상태

```java
//객체를 삭제한 상태(삭제)
em.remove(member)
```

### 영속성 컨텍스트의 이점

1. 1차 캐시
- 영속성 내부에는 1차 캐시가 존재
- 1차 캐시에 엔터티가 존재한다면 DB를 찾아보지 않아도 된다.
- 만약 1차 캐시에 없을 경우, DB에서 직접 조회하고 1차 캐시에 저장 후 반환.
- 애플리케이션 전체에서 공유하는 것이 아님(애플리케이션 전체에서 공유하는 캐시는 2차 캐시)
- 캐시의 기능을 가짐.

1. 영속 엔터티의 동일성 보장

```java
Member a = em.find(Member.class, "member1");
Member b = em.find(Member.class, "member1");

System.out.println(a==b) // true
```

- em.find()를 반복해서 호출해도 영속성 컨텍스트는 1차 캐시에 있는 같은 인스턴스를 반환한다.

1. 트랜잭션을 지원하는 쓰기 지연

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
//엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
transaction.begin(); // [트랜잭션] 시작

em.persist(memberA);
em.persist(memberB);
//여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

//커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
transaction.commit(); // [트랜잭션] 커밋
```

- em.persist()로 객체를 영속성 컨텍스트에 저장해도 DB에 바로 Insert쿼리를 날리지 않는다. 1차 캐시에 저장하면서 동시에 쓰기 지연 SQL저장소에 쌓아 둔다.
- SQL 쿼리들을 모아 놓았다가 flush될 때(영속성 컨텍스트의 변경내용을 DB에 반영할 때) 모아둔 쿼리를 모두 날린다.

→ 쓰기 지연

1. 변경 감지

```java
Member member = entityManager.find(Member.class, 10L);
member.setName("변경 후");

transaction.commit(); //Update 쿼리가 나감
```

- 영속성 컨텍스트에서 엔터티를 조회해서 해당 엔터티를 수정한다.
- 동작 원리
    1. 트랜잭션 되는 순간 flush() 호출
    2. 엔터티와 1차 캐시 내의 스냅샷(최초 상태)을 비교한다.
    3. 변경이 있을 때, UPDATE SQL을 쓰기 지연 SQL저장소에 저장.
    4. 커밋이 되면 flush()가 호출되서 DB에서 UPDATE쿼리가 나감.

1. 지연 로딩(Lazy Loading)
- 연관 관계 매핑되어 있는 엔터티를 조회 시 우선 프록시 객체를 반환하고, 실제로 필요할 때 쿼리를 날려 가져오는 기능이다.
- 필요할 때 데이터를 가져오는 기능

## 3. 엔터티 매핑

### ‘@Entity’

- JPA를 사용해 테이블과 매핑할 클래스에 필수적으로 붙히는 어노테이션
- ‘@Entity’를 붙히면 JPA가 관리하게 된다.
- 기본 생성자가 필수적이다.
    - JPA는 엔터티 객체를 생성할 때 기본 생성자를 사용한다.
- final클래스, enum, interface, inner클래스 사용 X
- 저장할 필드에 final 사용 x
- name 속성을 사용하면 JPA에서 사용할 엔터티 이름을 지정할 수 있다.
- 예시

```java
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
    
    Member(){}	// 생략하면 자동으로 생성 but, 다른 생성자가 있으면 자동 생성 안됨
    
}
```

### ‘@Table’

- 엔터티와 매핑할 테이블을 지정한다.
- name 속성으로 매핑할 테이블 이름 지정이 가능하다.
- 기본값은 엔터티 이름을 테이블 이름으로 사용한다.

```java
@Table(name = MEMBERS)
```

### 주의할 점

- ‘@Table’ 어노테이션은 필수가 아니지만, ‘@Entity’ 어노테이션과 ‘@Id’ 어노테이션은 필수이다.
- ‘@Entity’와 ‘@Id’는 함께 사용해야 한다. ‘@Id’ 어노테이션이 없으면 AnnotationException 예외가 발생
- 기본 생성자는 필수로 추가(JPA는 엔터티 객체를 생성할 때 기본 생성자로 추가하기 때문에)

### 데이터베이스 스키마 자동 생성

- JPA는 데이터베이스 스키마를 자동으로 생성하는 기능을 지원한다.
- 클래스의 매핑 정보를 통해 어떤 테이블이 어떤 컬럼을 사용하는 지 알 수 있다.

application.yml

현재 진행중인 프로젝트에서 사용하는 속성인데

ddl-auto:create = 어플리케이션 실행 시점에 DB table을 자동으로 생성한다.

show-sql: true = 콘솔에 실행되는 DDL(데이터 정의어)를 출력할 수 있다.

```yaml
jpa:
  hibernate:
    ddl-auto: create
  properties:
    hibernate:
      format_sql: true
  show-sql: true
  open-in-view: false
```

→ 객체, 테이블 매핑이 익숙하지 않을 경우 이 기능을 적극 활용하여 생성된 DDL을 보고 엔터티, 테이블이 어떻게 매핑되는지 이해하면 된다.

- ddl-auto 속성
    - create : 기존 테이블을 삭제하고 새로 생성함(Drop + Create)
    - create - drop : create + 어플리케이션 종료할 때 생성한 DDL제거(Drop + Create + Drop)
    - update : DB 테이블과 엔터티 매핑정보를 비교해서 변경사항만 수정함(운영 DB에는 사용 x)
    - validate : DB테이블과 엔터티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션 실행을 안함
    - none : 자동생성 기능을 사용하지 않기 위해 속성 자체를 안주거나, 유효하지 않은 옵션값(none)을 주면 된다.
        - 개발 초기 단계 - create/ update
        - 초기화 상태로 자동화된 테스트 진행하는 개발자 환경. CI서버 - create/ create-drop
        - 테스트 서버 - update/ validate
        - 스테이징과 운영서버 -validate /none
    - 운영서버에서는 절대 create, create-drop, update 사용하면 안된다.

### Unique  제약 조건

- 중복 값 x
1. 한개 컬럼에 UNIQUE 설정

```yaml
@Column(name="column" , unique =true)
long column
```

1. 두개 컬럼을 묶어서 UNIQUE를 설정하면

‘@Table’의 uniqueConstraints 사용

```yaml
@Table(
	name="tableName",
    uniqueConstraints={
        @UniqueConstraint(
            name={"contstraintName"}
            columnNames={"col1", "col2"}
        )
    }
)
@Data
public class Entity{
    @Column(name="col1")
    int col1;
    
    @Column(name="col2")
    int col2;
}
```

### 기본키 매핑 전략(IDENTITY, SEQUENCE, TABLE, AUTO)

- 영속성 컨텍스트는 Entity를 식별자 값으로 구분하기 때문에 엔터티를 영속 상태로 만들기 위해 식별자 값이 반드시 필요하다.

### ‘@Id’

- 기본키를 사용자가 직접 할당하여 사용

```java
@Id
prviate String id;
```

### ‘@GeneratedValue’

- 기본키를 자동 생성할 때 사용

```java
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

→ ‘@GeneratedValue 어노테이션은 4가지 전략이 있다.

1. ****@GeneratedValue (strategy = GenerationType.IDENTITY)****
- 기본 키 생성을 데이터베이스에 위임하는 전략이다.
- 데이터베이스의 AUTO_INCREMENT 기능을 통해 데이터베이스가 기본 키를 자동으로 생성해준다.
- IDENTITY 전략은 AUTO_INCREMENT처럼 데이터베이스에 값을 저장하고 나서 기본 키 값을 구할 수 있을 때 사용한다.
- 주로 MySQL, PostgreSQL, SQL Server에서 사용
- 주의점
    - 엔터티가 영속 상태가 되기 위해선 식별자가 꼭 필요하다.
    - IDENTITY 전략을 사용하면 식별자를 데이터베이스에서 지정하기 전까지 알 수 없기에 em.persist()를 하는 즉시 INSERT SQL이 데이터베이스에 전달된다.
    - 이 전략은 트랜잭션을 지원하는 쓰기 지연이 동작하지 않는다.

2.****@GeneratedValue (strategy = GenerationType.SEQUENCE)****

- 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 데이터베이스 오브젝트이다.
- SEQUENCE전략은 이 시퀀스를 사용해 기본 키를 생성한다.
- ‘@SequenceGenerator’를 사용해 시퀀스 생성기를 등록한 후, ‘@GeneratedValue’의 generator 속성으로 시퀀스 생성기를 선택한다.

```java
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "facility_id")
    private Long id;

    @OneToMany(mappedBy = "facility")
    private List<RoomFacility> roomFacilities;
}
```

- ‘@SequenceGenerator’ 속성 표
    - name : 식별자 생성기 이름
    - sequenceName : 데이터베이스에 등록되어 있는 시퀀스 이름
    - initialValue : DDL 생성 시에만 사용, 시퀀스 DDL을 생성할 때 처음 시작하는 수 지정
    - allocationSize : 시퀀스 한 번 호출에 증가하는 수

- IDENTITY와의 차이점
    - SEQUENCE 전략

  em.persist()를 호출할 때 먼저 데이터베이스 시퀀스를 사용해 식별자를 조회 → 식별자를 엔터티에 할당 → 엔터티를 영속성 컨텍스트에 저장 → 트랜잭션 커밋 시점에 flush가 발생하면 엔티티를 데이터베이스에 저장

    - IDENTITY 전략

  먼저 엔티티를 데이터베이스에 저장 → 식별자 조회 → 엔티티에 식별자에 할당 → 영속성 컨텍스트에 저장


1. ****@GeneratedValue (strategy = GenerationType.TABLE)****
- TABLE 전략은 시퀀스 대신 테이블을 사용한다는 것 이외에 SEQUNCE전략과 동작하는 방식이 같다.
- 성능이 좋지 않다.
- ‘@TableGenerator’ 속성 표
    - name: 식별자 생성기 이름
    - table : 키 생성 테이블 명
    - pkColumnName : 시퀀스 컬럼명
    - valueColumnNa : 시퀀스 값 컬럼명
    - pkColumnValue : 키로 사용할 값 이름
    - initialValue : 초기 값, 마지막으로 생성된 값이 기준이다.
    - allocationSize : 시퀀스 한 번 호출에 증가하는 수(성능 최적화에 사용됨)

1. ****@GeneratedValue (strategy = GenerationType.AUTO)****
- AUTO로 설정하면 데이터베이스 방언에 따라 IDENTITY, SEQUENCE, TABLE 전략 중 하나를 자동으로 선택한다.
- ‘@GeneratedValue’의 strategy의 기본값은 AUTO이다.

### Entity와 Table 매핑 사용 권장 방법

- 특별한 사유가 없다면 ‘@Entity’와 ‘@Id’만 사용
- 기본키 생성 전략은 IDENTITY 또는 SEQUENCE전략을 사용
    - DB에서 지원하는 AUTO_INCREMENT 또는 SEQUENCE를 사용
- ‘@Column’ 정보 명시
    - 다른 사람이 보았을 때, Entity 클래스를 보고 쉽게 테이블 설계를 알아볼 수 있다.
- ‘@Enumerated’ 사용 시, EnumType.STRING을 사용하자.

# 2주차

## 1. 연관관계 매핑

### 연관 관계 매핑할 때 생각할 점

- 방향 : 단방향, 양방향
- 연관 관계의 주인 : 양방향일 때, 연관 관계에서 관리 주체
- 다중성 : 다대일, 일대다, 일대일, 다대다

1. 방향
- 테이블은 외래키 하나로 양방향 쿼리가 가능해서 방향이라는 개념이 없다.
- 객체는 참조용 필드를 가지고 있는 객체만 연관된 객체를 조회할 수 있다.
- 단방향 : 객체 관계에서 한쪽만 참조하는 것

→ 단방향일 경우

```java
@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
}

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    public Team team;
}
```

- 양방향 : 객체 관계에서 양쪽이 서로 참조하는 것
    - 객체의 양방향 관계는 사실 양방향 관계가 아니라 서로 다른 단방향 관계 2개다.
    - 객체를 양방향으로 참조하려면 단방향 연관관계를 2개 만들어야 한다.

→ 양방향일 경우

```java
@Entity
@Getter @Setter
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    public Team team;
}
```

## 2. Spring Data JPA

### **Spring Data JPA란?**

- Spring Data JPA는 JPA를 사용하기 편하도록 만들어놓은 모듈이다.
- JPA를 한 단계 더 추상화시킨 Repository 인터페이스를 제공한다.
- Hibernate와 같은 JPA구현체를 사용해서 JPA를 사용하게 된다.
- 데이터 접근이 더욱 쉬워 진다.

<img width="506" alt="스크린샷 2023-09-12 오후 11 45 41" src="https://github.com/COW-edu/COW-Spring-1/assets/127813439/8311a66d-d14e-47bc-b2e0-fcd0994852b5">

```java
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```

→ 위 라이브러리를 사용한다.

### 사용방법

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataJpaRepository extends JpaRepository<Type, Id> {}
```

- Repository를 인터페이스로 선언하고 다음과 같이 JpaRepository를 상속받는다.
- Type : 사용할 Repository의 기준이 되는 Entity의 이름을 기입한다.
- Id : 기입된 Entity의 PK자료형을 기입한다.

[Reference]

- https://ittrue.tistory.com/270
- https://code-lab1.tistory.com/290
- https://jie0025.tistory.com/371
- [https://velog.io/@tritny6516/JPA-기본키Primary-Key-매핑-Id-GeneratedValue](https://velog.io/@tritny6516/JPA-%EA%B8%B0%EB%B3%B8%ED%82%A4Primary-Key-%EB%A7%A4%ED%95%91-Id-GeneratedValue)
- https://velog.io/@gwichanlee/JPA-Entity-Mapping
- https://devraphy.tistory.com/622