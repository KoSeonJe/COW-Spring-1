# **JPQL** ( Java Persistence Query Language )

- 엔티티 객체를 조회하는 객체지향 쿼리
- JPA Query를 작성하는 방법을 제공한다.
- Table column명 사용 대신, **엔티티 이름, 속성**을 사용함.
- ANSI 표준 SQL이 제공하는 기능을 유사하게 지원한다
- 엔티티 객체를 대상으로 쿼리한다.
- 엔티티 직접조회, 묵시적 조인, 다형성지원으로 SQL보다 간결하다.

### JPQL 기능

**예시**

- 실행 JPQL

```java
**select** m **from** **Member** **as** m **where** m.username = 'kim'
```

- 실제로 실행된 SQL

```sql
select
    member.id as id,
    member.age as age,
    member.team_id as team,
    member.name as name
from
    Member member
where
    member.name='kim'
```

### 기본 문법

- SELECT, UPDATE, DELETE 가능
- INSERT 불가능 (EntityManager.persist()를 사용해 엔티티를 저장한다. )

**select  별칭 from 엔티티명 별칭**

- `select r form Review r`
- `select m from Member as m`

### ****SELECT****

```sql
select m from Member as m where m.username='jieun
```

- **엔티티와 속성은 대소문자 구분 O**
    - `Member != member`
    - `UserName != username`
- **JPQL키워드는 대소문자 구분 x    (sql 쿼리도 그렇지)**
    - `SELECT == select`
    - `FROM == from`
- **JPQL은 별칭이 필수다**
    - `select username from Member m       // X`
    - `select m.username from Member m   // O`
- **AS는 생략 가능하다**
    - Member AS m == Member m
    

### ****쿼리의 종류 : TypeQuery와 Query****

- **작성한 JPQL을 실행하기 위한 쿼리 객체**

**TypeQuery : 반환 타입 명확한 지정 가능**

```java
TypedQuery<Member> query
    = em.createQuery("SELECT m FROM Member m", Member.class);

List<Member> resultList = query.getResultList();
for (Member member : resultList) {
    System.out.println("member = " + member);
}
```

**Query : 반환 타입 명확한 지정 불가능**

```java
Query query
    = em.createQuery("SELECT m.username, m.age FROM Member m");

List<Member> resultList = query.getResultList();
for (Object o : resultList) {
    Object[] result = (Object[]) o;  //결과가 둘 이상이면 Object[] 반환
    System.out.println("username = " + result[0]);
    System.out.println("age = " + result[1]);
}
```

em.createQuery()

- 두번째 파라미터에 반환타입 지정시 TypeQuery 반환, 지정 안할 경우 Query 반환
- 반환형이 불확실한 경우에 Query를 사용한다.

### ****결과 조회하기****

**JPQL에서 쿼리를 통한 결과를 받을 때 사용하는 메서드**

- **query.getResultList() : 결과를 리스트로 반환**
- **query.getSingleResult : 결과가 한개일 때 사용 (1개가 아니면 예외 발생 )**
    - 결과 존재 x : javax.persistence.NoResultException
    - 결과가 둘 이상임 :javax.persistence.NonUniqueResultException

## ****기본 JPQL 사용방법****

### ****검색 조건 지정****

- where + and, or, 괄호 등

```java
select r from Review r where r.hotelId = :hotelId
select r from Review r where r.hotelId = ?
select r from Review r where r.hotelId = :hotelId and r.mark > :minMark
selet p from Player p where p.position = :pos or p.team.if = :teamId
```

### ****파라미터 바인딩****

- **쿼리에 작성되는 특정 속성을 매개변수로 매핑 하는 것.**
- **쿼리에 매개변수를 매핑하는 방식엔 이름기준, 위치기준 두가지가 존재.**

****이름 기준 파라미터 (Named Parameters)****

- 파라미터를 이름으로 구분함.
- **=: 연산자** 를 사용한다 (파라미터 앞에  **:**  을 붙힌다)

`query.setParameter("hotelId", "H-001")`

```java
Query query = em.createQuery("select m from Member m where m.username =: username")
                .setParameter("username", usernameParam);
```

****인덱스 기반 파라미터 (Positional Parameters)****

- 0부터 시작
- **=? 연산자**  사용 (파라미터 앞에  **?**  을 붙힌다)

`query.setParameter(0,"H-001")`

```java
Query query = em.createQuery("select m from Member m where m.username =? 1")
                .setParameter(1, usernameParam);
```

- 매개변수 바인딩시 이름 기반 바인딩 사용을 권장함 (나중에 새로운 매개변수 추가시 위치/인덱스 기반 바인딩은 순서가 밀려서)
- 숫자를 이용하면 어떤 위치의 매개변수가 무엇을 의미하는지 쉽게 파악 불가능
- 가독성이 떨어져 유지보수 할 때 비효율적

### ****정렬 (SQL과 동일)****

- order by 기준1 (,기준2..)
- asc, desc 사용가능

```java
select r from Review r order by r.id
select r from Review r order by r.id asc
select r from Review r order by r.id desc
select p from Player p order by p.position, p.name
select p from Player p order by p.team.id, p.name
```

- default : 오름차순 정렬

### **비교연산자**

| 연산자 | example |
| --- | --- |
| = | u.name = 'JPA' |
| <  > | o.state < > ? |
| >  >=  <  <= | p.salary > 2000 |
| between | mc.expiryDate between ? and ? |
| in, not in | o.mark in (1,2,3) |
| like, not like | u.name like '%유%' |
| is null, is not null | u.name is null |


# JPA?

- Java Persistence API
- 자바 진영의 **ORM** 기술 표준

## ORM?

- Object-relational mapping(객체 관계 매핑)
- 객체는 객체대로 설계
- 관계형 데이터베이스는 관계형 데이터베이스대로 설계
- ORM 프레임워크가 중간에서 매핑
- 대중적인 언어에는 대부분 ORM 기술이 존재

### **JPA는 애플리케이션과 JDBC 사이에서 동작**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/e78cb792-7281-4abc-a65a-fd72e82ccdef)


### **JPA 동작 - 저장**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/6c4b62a6-a019-4e45-bfd0-4edc585a6fc4)


### **JPA 동작 - 조회**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/69e557aa-cb31-4bb0-afe5-1f753bba6367)


### JPA는 표준 명세

- JPA는 인터페이스의 모음
- JPA 2.1 표준 명세를 구현한 3가지 구현체
- **하이버네이트**, EclipseLink, DataNucleus (하이버네이트를 쓴다고 생각하면 됨- 애초에 JPA가 하이버네이트 오픈소스에서 온 표준이기 때문에 당연)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/85dc99d8-ad92-4823-9ee9-4274182ed71d)


# JPA를 왜 사용해야 하는가?

- SQL 중심적인 개발에서 객체 중심으로 개발
- 생산성
- 유지보수
- 패러다임의 불일치 해결
- 성능
- 데이터 접근 추상화와 벤더 독립성
- 표준

## **생산성 - JPA와 CRUD**

- 저장: **jpa.persist**(member)
- 조회: Member member = **jpa.find**(memberId)
- 수정: **member.setName**(“변경할 이름”)
- 삭제: **jpa.remove**(member)

## **유지보수**

- **기존: 필드 변경시 모든 SQL 수정**
- JPA: 필드만 추가하면 됨, SQL은 JPA가 처리

****

## **JPA와 패러다임의 불일치 해결**

1.JPA와 상속
2.JPA와 연관관계
3.JPA와 객체 그래프 탐색
4.JPA와 비교하기

### **JPA와 상속**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/88cdb679-880d-4b2a-8128-114a84e929e5)


**저장**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c515c15d-9a3c-4aea-89de-a8686ae62b15)


**조회**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c28b45f8-86bc-4af8-86d7-a3582dc34fb5)


### **JPA와 연관관계, 객체 그래프 탐색**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/4c3ead7d-05c4-47d1-87bd-f572c28be569)


### **신뢰할 수 있는 엔티티, 계층**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/5fe54b5c-4360-4a16-bcb6-ed85ed1bf92f)


### **JPA와 비교하기**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/efd20ab0-eb38-44a0-8fcc-19020884a1ec)


- 동일한 트랜잭션에서 조회한 엔티티는 같음을 보장

## **JPA의 성능 최적화 기능**

1. 1차 캐시와 동일성(identity) 보장
2. 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
3. 지연 로딩(Lazy Loading)

### **1차 캐시와 동일성 보장**

1. **같은 트랜잭션 안**에서는 같은 엔티티를 반환 - 약간의 조회 성능 향상
2. DB Isolation Level이 Read Commit이어도 애플리케이션에서 Repeatable Read 보장

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/8d680633-6b52-40f7-ac24-52f524b6fbc2)


- SQL 1번만 실행

### **트랜잭션을 지원하는 쓰기 지연 - INSERT**

1. 트랜잭션을 커밋할 때까지 INSERT SQL을 모음
2. JDBC BATCH SQL 기능을 사용해서 한번에 SQL 전송

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/86780016-526e-4acb-b8f8-cfd05ca671af)


### **트랜잭션을 지원하는 쓰기 지연 - UPDATE**

1. UPDATE, DELETE로 인한 로우(ROW)락 시간 최소화
2. 트랜잭션 커밋 시 UPDATE, DELETE SQL 실행하고, 바로 커밋

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/d2aaf47b-915b-4983-8b42-7132716a9fcb)


### **지연 로딩과 즉시 로딩**

- 지연로딩: 객체가 실제 사용될 때 로딩
- 즉시 로딩: JOIN SQL로 한번에 연관된 객체까지 미리 조회

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/d6de8564-8661-4711-9105-0eec7b719c47)

# **엔티티 매니저 팩토리와 엔티티 매니저**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/7a38ea46-2f25-4561-ae11-ee6e20a0c5f1)


- 요청이 올 때마다 `EntityManagerFactory` `EntityManager` 를 생성한다.
- `EntityManager` 는 내부적으로 데이터베이스 커넥션을 사용해서 DB를 사용한다.

## **영속성 컨텍스트**

- JPA를 이해하는데 가장 중요한 용어
- “엔티티를 영구 저장하는 환경”이라는 뜻
- **EntityManager.persist(entity);**

### 엔티티 매니저와 영속성 컨텍스트

- 영속성 컨텍스트는 논리적인 개념
- 눈에 보이지 않는다.
- 엔티티 매니저를 통해서 영속성 컨텍스트에 접근

### **J2SE 환경**

- 엔티티 매니저와 영속성 컨텍스트가 1:1

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ca92ceca-5be7-487b-ba37-62d51ee2c21b)


### **J2EE, 스프링 프레임워크 같은 컨테이너 환경**

- 엔티티 매니저와 영속성 컨텍스트가 N:1

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/43138136-5a98-41c3-87f6-4177c7a18fba)


## **엔티티의 생명주기**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/2abab8ca-d83e-477b-a95f-dfbdddf49d19)


### **비영속 (new/transient)**

- 영속성 컨텍스트와 전혀 관계가 없는 **새로운** 상태
    
    ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/8a3bc704-dc41-4e46-bbd8-d5683f8a8d10)
    

### **영속 (managed)**

- 영속성 컨텍스트에 **관리**되는 상태
    
  ![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/59f04a52-a53b-47fc-a684-1a672c2d506c)


### **준영속 (detached)**

- 영속성 컨텍스트에 저장되었다가 **분리**된 상태

### **삭제 (removed)**

- **삭제**된 상태

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/eb0c5651-0c25-4163-b36e-889dd1a5300b)


## **영속성 컨텍스트의 이점**

- 1차 캐시 동일성(identity) 보장
- 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
- 변경 감지(Dirty Checking)
- 지연 로딩(Lazy Loading)

### **엔티티 조회, 1차 캐시**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/1dcd3a3b-8a0c-4f1d-9704-5339e4e0e388)


```java
//엔티티를 생성한 상태(비영속)
Member member = new Member();
member.setId("member1");
member.setUsername("회원1");
//엔티티를 영속
em.persist(member);
```

- 조회할 때 이점이 있음

```java
Member member = new Member();
member.setId("member1");
member.setUsername("회원1");
//1차 캐시에 저장됨
em.persist(member);
//1차 캐시에서 조회
Member findMember = em.find(Member.class, "member1");
```

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/2d7c6de5-4cf9-434c-ae16-2dcba4aaa264)


- DB엔 있고 1차 캐시에는 없는 경우

```java
Member findMember2 = em.find(Member.class, "member2");
```

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/af43a431-e602-419c-9861-bdadf7183075)


> 사실 1차 캐시는 큰 도움이 되지 않는다. 이유는 EntityManager는 DB transaction 단위로 주로 만들고, DB transaction이 끝날 때 EntityManager를 종료 시킨다. 즉, 고객의 요청이 하나 들어온 후 끝나면 영속성 컨텓스트를 지운다는 소리이다. 때문에 이때 1차 캐시가 모두 날라간다. 이런 짧은 순간에서 이득을 보기엔 어렵다.
> 

**영속 엔티티의 동일성 보장**

```java
Member a = em.find(Member.class, "member1");
Member b = em.find(Member.class, "member1");
System.out.println(a == b); //동일성 비교 true
```

- **1차 캐시로 반복 가능한 읽기(REPEATABLE READ) 등급의 트랜잭
션 격리 수준을 데이터베이스가 아닌 애플리케이션 차원에서 제공**

### **엔티티 등록 트랜잭션을 지원하는 쓰기 지연**

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

1. persist(memberA)를 하면 1차 캐시에 저장하고, INSERT SQL 생성 쿼리를 쓰끼 지연 SQL 저장소에 저장한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/2cef6fcb-cb21-496a-981f-8a1de6c11d38)


2. persist(memberB)를 하면 1차 캐시에 저장하고, INSERT SQL 생성 쿼리를 쓰끼 지연 SQL 저장소에 저장한다. 이때에도 아직 INSERT SQL을 데이터베이스에 저장하지 않는다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/9d53c0b4-a11a-49a7-bf48-c8e8359d5003)


3. `transaction.commit();` 를 통해서 flush 되면서 쿼리를 보내고 DB에 transaction이 커밋된다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/4c7892c8-4279-43b9-9a78-3fa9834c5005)


- 이런 식으로 트랜잭션이 커밋되는 시점에 실제 쿼리를 DB에 보내는 이유는 만약 persist() 시점마다 쿼리를 날린다면 최적화를 할 수 없기 때문이다.

```
<property name "hibernate.jdbc.batch_size" value="10"/>
```

- 위 코드를 `persistence.xml` 에 옵션을 추가하면 size만큼 모아서 한번에 DB로 보내도록 할 수 있다.

> 실무에서는 잘 활용하기 어렵지만 이런 점을 잘 사용하면 엄청난 이점을 챙길 수 있다.
> 

### 엔티티 수정 변경 감지

```java
EntityManager em = emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
transaction.begin(); // [트랜잭션] 시작

// 영속 엔티티 조회
Member memberA = em.find(Member.class, "memberA");

// 영속 엔티티 데이터 수정
memberA.setUsername("hi");
memberA.setAge(10);

//em.update(member) 이런 코드가 있어야 하지 않을까? -> 변경감지로 인해 고민 해결

transaction.commit(); // [트랜잭션] 커밋
```

**변경 감지 (Dirty Checking)**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/c759782e-fcbd-402b-b548-58e93b83f51a)


1. JPA는 `transaction.commit()` 시점에 내부적으로 `flush()` 를 호출하게 된다.
2. 엔티티와 스냅샷을 비교한다.
    - 스냅샷: 값이 영속성 컨텍스트로 읽어온 최초 시점에 상태
3. 스냅샷과 엔티티가 다르면 쓰기 지연 SQL 저장소에 UPDATE 쿼리를 저장해둔다.
4. flush() 시점에 UPDATE 쿼리를 DB에 반영하고 commit한다.

### **엔티티 삭제**

```java
//삭제 대상 엔티티 조회
Member memberA = em.find(Member.class, “memberA");

em.remove(memberA); //엔티티 삭제
```

## Flush란

- 영속성 컨텍스트의 변경내용을 데이터베이스에 반영하는 것을 말한다.

### Flush 발생 순서

- transaction 커밋이 일어나면 flush가 발생한다.
- 변경 감지(dirty checking)이 일어난다.
- 수정된 엔티티 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 데이터베이스에 전송 (등록, 수정, 삭제 쿼리)

### 영속성 컨텍스트를 플러시하는 방법

- **em.flush() -** 직접 호출
- **트랜잭션 커밋 -** 플러시 자동 호출
- **JPQL 쿼리 실행 -** 플러시 자동 호출
    - **JPQL 쿼리 실행시 플러시가 자동으로 호출되는 이유:** JPQL에서 조회하기 전에 flush가 일어나지 않았을 때 생기는 문제를 방지하기 위해 플러시가 자동으로 호출됨.

> Flush를 해도 1차 캐시에 저장된 것은 삭제되지 않는다.
> 

### **플러시 모드 옵션**

**`em.setFlushMode(FlushModeType.COMMIT)`**

**FlushModeType.AUTO** 

- 커밋이나 쿼리를 실행할 때 플러시 (기본값)
- 보통 그냥 AUTO로 쓰는 것을 권장함

**FlushModeType.COMMIT** 

- 커밋할 때만 플러시

### 주의할 점

- 영속성 컨텍스트를 비우지 않음
- 영속성 컨텍스트의 변경내용을 데이터베이스에 동기화
- 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화하면 됨

# **준영속 상태**

- 영속성 컨텍스트가 더는 **관리하지 않는 엔티티**를 말한다.
    - EntityManger로 조회하고, set을 통해 값을 변경(변경 감지 기능)이 DB에 반영되지 않음
- 영속 상태의 엔티티가 영속성 컨텍스트에서 분리(detached)
- 영속성 컨텍스트가 제공하는 기능을 사용 못함

## **준영속 상태로 만드는 방법**

- **em.detach(entity)**
    - 특정 엔티티만 준영속 상태로 전환
- **em.clear()**
    - 영속성 컨텍스트를 완전히 초기화
- **em.close()**
    - 영속성 컨텍스트를 종료

### **준영속 엔티티를 수정하는 2가지 방법**

- 변경 감지 기능 사용
- 병합( merge ) 사용

## 변경 감지 기능 사용 (더 나은 방법)

```java
@Transactional
void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
	Item findItem = em.find(Item.class, itemParam.getId()); //같은 엔티티를 조회한다.
	findItem.setPrice(itemParam.getPrice()); //데이터를 수정한다.
}
```

- `ItemService` 에서 `findItem` 은 영속 상태
- 코드가 끝나면, Transactional에 의해 Transactional commit이 일어남
- commit이 되면, spring은 flush를 날림(영속성 엔티티 중에 변경된 사항을 찾음, 바뀐 내용을 UPDATE 쿼리를 날려서, DB 변경)

```java
@Transactional
public void updateItem(Long itemId, Book param) {
    Item findItem = itemRepository.findOne(itemId);
// 이런 식으로 set하는 것보단 findItem.change(param.getPrice(), param.getName(),param.getStockQuantity()); 이런 식으로 의미있는 메서드를 사용하는 것이 좋음
    findItem.setPrice(param.getPrice());
    findItem.setName(param.getName());
    findItem.setStockQuantity(param.getStockQuantity());
}
```

- 영속성 컨텍스트에서 엔티티를 다시 조회한 후에 데이터를 수정하는 방법
- 트랜잭션 안에서 엔티티를 다시 조회, 변경할 값 선택 트랜잭션 커밋 시점에([flush 할 때](https://gwonbookcase.tistory.com/36)) 변경 감지(Dirty Checking)가 동작해서 데이터베이스에 UPDATE SQL 실행

## **병합 사용**

병합은 준영속 상태의 엔티티를 영속 상태로 변경할 때 사용하는 기능이다.

```java
@Transactional
void update(Item itemParam) { //itemParam: 파리미터로 넘어온 준영속 상태의 엔티티
	Item mergeItem = em.merge(itemParam);
}
```

**병합: 기존에 있는 엔티티**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/30287b94-5f54-483c-a3ae-4823117eed36)


### **병합 동작 방식**

1. `merge()`를 실행한다.
2. 파라미터로 넘어온 준영속 엔티티의 식별자 값으로 1차 캐시에서 엔티티를 조회한다. 
    
    2-1. 만약 1차 캐시에 엔티티가 없으면 데이터베이스에서 엔티티를 조회하고, 1차 캐시에 저장한다.
    
3. 조회한 영속 엔티티( `mergeMember` )에 member 엔티티의 값을 채워 넣는다. (member 엔티티의 모든 값을 mergeMember에 밀어 넣는다. 이때 mergeMember의 “회원1”이라는 이름이 “회원명변경”으로 바뀐다.)
4. 영속 상태인 mergeMember를 반환한다. (기존의 member 엔티티이 영속석으로 바뀌는 것이 아니라 새로운 영속성 엔티티를 복제하는 느낌)

> 참고: 책 자바 ORM 표준 JPA 프로그래밍 3.6.5
> 

### **병합 시 동작 방식을 간단히 정리**

1. 준영속 엔티티의 식별자 값으로 영속 엔티티를 조회한다.
2. 영속 엔티티의 값을 준영속 엔티티의 값으로 모두 교체한다.(병합한다.)
3. 트랜잭션 커밋 시점에 변경 감지 기능이 동작해서 데이터베이스에 UPDATE SQL이 실행

> 주의: 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 병합을 사용하면 모든 속성이 변경된다. 병합시 값이 없으면 null로 업데이트 할 위험도 있다. 
(병합은 모든 필드를 교체한다.) -> 원래 있던 값을 새로운 객체로 저장할 때 null로 업데이트 될 수 있는 것
> 

### **상품 리포지토리의 저장 메서드 분석 ItemRepository**

- `save()` 메서드는 식별자 값이 없으면( `null` ) 새로운 엔티티로 판단해서 영속화(persist)하고 식별자가 있 으면 병합(merge)
- 지금처럼 준영속 상태인 상품 엔티티를 수정할 때는 id 값이 있으므로 병합 수행

### **새로운 엔티티 저장과 준영속 엔티티 병합을 편리하게 한번에 처리**

- 상품 리포지토리에선 `save()` 메서드를 유심히 봐야 하는데, 이 메서드 하나로 저장과 수정(병합)을 다 처리한다.
- 코드를 보면 식별자 값이 없으면 새로운 엔티티로 판단해서 `persist()` 로 영속화하고 만약 식별자 값이 있으면 이미 한번 영속화 되었던 엔티티로 판단해서 `merge()` 로 수정(병합)한다.
- 결국 여기서의 저장(save)이라는 의미는 신규 데이터를 저장하는 것뿐만 아니라 변경된 데이터의 저장이라는 의미도 포함한다. 이렇게 함으로써 이 메서드를 사용하는 클라이언트는 저장과 수정을 구분하지 않아도 되므로 클라이언트의 로직이 단순해진다.

여기서 사용하는 수정(병합)은 준영속 상태의 엔티티를 수정할 때 사용한다. 

영속 상태의 엔티티는 변경 감지(dirty checking)기능이 동작해서 트랜잭션을 커밋할 때 자동으로 수정되므로 별도의 수정 메서드를 호 출할 필요가 없고 그런 메서드도 없다.

> **참고**:

`save()` 메서드는 식별자를 자동 생성해야 정상 동작한다. 여기서 사용한 Item 엔티티의 식별자는 자동으로 생성되도록 `@GeneratedValue` 를 선언했다. 따라서 식별자 없이 `save()` 메서드를 호출하면 `persist()` 가 호출되면서 식별자 값이 자동으로 할당된다. 
반면에 식별자를 직접 할당하도록 `@Id` 만 선언 했다고 가정하자. 이 경우 식별자를 직접 할당하지 않고, `save()` 메서드를 호출하면 식별자가 없는 상태로 `persist()` 를 호출한다. 그러면 식별자가 없다는 예외가 발생한다.
> 

> **참고:**

실무에서는 보통 업데이트 기능이 매우 제한적이다. 그런데 병합은 모든 필드를 변경해버리고, 데이터가 없으면 `null` 로 업데이트 해버린다. 병합을 사용하면서 이 문제를 해결하려면, 변경 폼 화면에서 모든 데이터를 항상 유지해야 한다. 실무에서는 보통 변경가능한 데이터만 노출하기 때문에, 병합을 사용하는 것이 오히려 번거롭다.
> 

## 가장 좋은 해결 방법

### **엔티티를 변경할 때는 항상 변경 감지를 사용**

- 컨트롤러에서 어설프게 엔티티를 생성하지 말자.
- 트랜잭션이 있는 서비스 계층에 식별자( `id` )와 변경할 데이터를 명확하게 전달하세요.(파라미터 or dto)

`ItemController`

```java
@PostMapping("items/{itemId}/edit")
public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {
    UpdateItemDto ItemDto = new UpdateItemDto(form.getName(), form.getPrice(), form.getStockQuantity());

    itemService.updateItem(itemId, ItemDto);

    return "redirect:/items";
}
```

`ItemService`

```java
/**
 * 영속성 컨텍스트가 자동 변경
 */
@Transactional
public void updateItem(Long itemId, UpdateItemDto itemDto) {
    Item findItem = itemRepository.findOne(itemId);
    findItem.change(itemDto);
//        findItem.setName(itemDto.getName());
//        findItem.setPrice(itemDto.getPrice());
//        findItem.setStockQuantity(itemDto.getStockQuantity());
}
```

`ItemService`

```java
package jpaBook.jpaShop.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;

    public UpdateItemDto(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
```

`item`

```java
 /**
 * item 갱신
 */
public void change(UpdateItemDto itemDto) {
    this.name = itemDto.getName();
    this.price = itemDto.getPrice();
    this.stockQuantity = itemDto.getStockQuantity();
}
```

- 트랜잭션이 있는 서비스 계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경하세요.
- 트랜잭션 커밋 시점에 변경 감지가 실행됩니다.

# **엔티티 매핑**

- 객체와 테이블 매핑: **@Entity, @Table**
- 필드와 컬럼 매핑: **@Column**
- 기본 키 매핑: **@Id**
- 연관관계 매핑: **@ManyToOne,@JoinColumn**

## **객체와 테이블 매핑**

### **@Entity**

- @Entity가 붙은 클래스는 JPA가 관리, 엔티티라 한다.
- JPA를 사용해서 테이블과 매핑할 클래스는 **@Entity** 필수

> 💡**주의**

- 기본 생성자 필수**(파라미터가 없는 public 또는 protected 생성자)
- final 클래스, enum, interface, inner 클래스 사용 X
> 
> 
> - 저장할 필드에 final 사용 X
> 

### **@Entity** 속성 정리

- 속성: **name**
- JPA에서 사용할 엔티티 이름을 지정한다.
    - 기본값: 클래스 이름을 그대로 사용(예: Member)
    - 같은 클래스 이름이 없으면 가급적 기본값을 사용한다.
    

### **@Table**

- @Table은 엔티티와 매핑할 테이블 지정

| 속성  | 기능 |  기본값 |
| --- | --- | --- |
| name | 매핑할 테이블 이름 | 엔티티 이름을
사용 |
| catalog | 데이터베이스 catalog 매핑 |  |
| schema | 데이터베이스 schema 매핑 |  |
| uniqueConstraints
(DDL) | DDL 생성 시에 유니크 제약 조건 생성 |  |

## **데이터베이스 스키마 자동 생성**

- DDL을 애플리케이션 실행 시점에 자동 생성 (CREATE TABLE)
- 테이블중심->객체중심
- 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 생성
- 이렇게 **생성된 DDL은 개발 장비에서만 사용**
- **생성된 DDL은 운영서버에서는 사용하지 않거나**, 적절히 다듬은 후 사용

### **데이터베이스 스키마 자동 생성 - 속성**

**`[hibernate.hbm2ddl.auto](http://hibernate.hbm2ddl.auto)`**

| 옵션 | 설명 |
| --- | --- |
| create | 기존테이블 삭제 후 다시 생성 (DROP + CREATE) |
| create-drop | create와 같으나 종료시점에 테이블 DROP (주로 테스트할 때) |
| update | 변경분만 반영(운영DB에는 사용하면 안됨) |
| validate | 엔티티와 테이블이 정상 매핑되었는지만 확인 |
| none | 사용하지 않음 (관례상 none이라고 하는 것) |

### 💡데이터베이스 스키마 자동 생성 - 주의

- **운영 장비에는 절대 create, create-drop, update 사용하면 안된다.**
- 개발 초기 단계는 create 또는 update
- 테스트 서버는 update 또는 validate
- 스테이징과 운영 서버는 validate 또는 none

### DDL 생성 기능

- 제약조건 추가: 회원 이름은 **필수**, 10자 초과X
    - **`@Column(nullable = false, length = 10)`**
- 유니크 제약조건 추가
    - **`@Table(uniqueConstraints = {@UniqueConstraint( name ="NAME_AGE_UNIQUE", columnNames = {"NAME", "AGE"} )})`**
- DDL 생성 기능은 DDL을 자동 생성할 때만 사용되고 JPA의 **실행 로직에는 영향을 주지 않는다.**

## **필드와 컬럼 매핑**

### **매핑 어노테이션 정리**

- **`hibernate.hbm2ddl.auto`**

| 어노테이션 | 설명 |
| --- | --- |
| @Column | 컬럼 매핑 |
| @Temporal | 날짜 타입 매핑 |
| @Enumerated | enum 타입 매핑 |
| @Lob | BLOB, CLOB 매핑 |
| @Transient | 특정 필드를 컬럼에 매핑하지 않음(매핑 무시) |

### ****@Column****

| 속성 |  설명 |  기본값 |
| --- | --- | --- |
| name | 필드와 매핑할 테이블의 컬럼 이름 | 객체의 필드 이름 |
| insertable,
updatable | 등록, 변경 가능 여부 | TRUE |
| nullable(DDL) | null 값의 허용 여부를 설정한다. false로 설정하면 DDL 생성 시에
not null 제약조건이 붙는다. |  |
| unique(DDL)(잘 사용하지 않음) | @Table의 uniqueConstraints와 같지만 한 컬럼에 간단히 유니크 제
약조건을 걸 때 사용한다. |  |
| columnDefinition
(DDL) | 데이터베이스 컬럼 정보를 직접 줄 수 있다.  
ex) varchar(100) default ‘EMPTY' | 필드의 자바 타입과
방언 정보를 사용해 |
| length(DDL) | 문자 길이 제약조건, String 타입에만 사용한다. | 서 적절한 컬럼 타입

255 |
| precision,
scale(DDL) | BigDecimal 타입에서 사용한다(BigInteger도 사용할 수 있다).
precision은 소수점을 포함한 전체 자 릿수를, scale은 소수의 자릿수
다. 참고로 double, float 타입에는 적용되지 않는다. 아주 큰 숫자나
정 밀한 소수를 다루어야 할 때만 사용한다. | precision=19,
scale=2 |

### ****@Enumerated****

- ****자바 enum 타입을 매핑할 때 사용****

> 💡****주의!**** 
ORDINAL 사용X
순서가 바뀌는 값이 enum에 추가되면 db와 다른 결과를 반환하게 되기 때문
> 

| 속성 |  설명 | 기본값 |
| --- | --- | --- |
| value | • EnumType.ORDINAL: enum 순서를 데이터베이스에 저장
• EnumType.STRING: enum 이름을 데이터베이스에 저장 | EnumType.ORDINAL |

### @Temporal

- **날짜 타입(java.util.Date, java.util.Calendar)을 매핑할 때 사용**

> **참고: LocalDate, LocalDateTime을 사용할 때는 생략 가능-잘 안 씀(최신 하이버네이트 지원)**
> 

| 속성 |  설명 |
| --- | --- |
| value | - TemporalType.DATE: 날짜, 데이터베이스 date 타입과 매핑 (예: 2013–10–11)
- TemporalType.TIME: 시간, 데이터베이스 time 타입과 매핑 (예: 11:11:11)
- TemporalType.TIMESTAMP: 날짜와 시간, 데이터베이스 timestamp 타입과 매핑(예: 2013–10–11 11:11:11) |

### ****@Lob****

- 데이터베이스 BLOB, CLOB 타입과 매핑
- @Lob에는 지정할 수 있는 속성이 없다.
- 매핑하는 필드 타입이 문자면 CLOB 매핑, 나머지는 BLOB 매핑
    - CLOB: String, char[], java.sql.CLOB
    - BLOB: byte[], java.sql. BLOB

### ****@Transient****

- ****필드 매핑X****
- ****데이터베이스에 저장X, 조회X****
- **주로 메모리상에서만 임시로 어떤 값을 보관하고 싶을 때 사용**
- **@Transient**
    
    **private Integer temp;**
    

## 기본 키 매핑

### **기본 키 매핑 어노테이션**

- **@Id**
- **@GeneratedValue**

```java
@Id @GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
```

### **기본 키 매핑 방법**

- 직접 할당: **@Id만 사용**
- 자동 생성(**@GeneratedValue**)
    - **IDENTITY**: 데이터베이스에 위임, MYSQL
    - **SEQUENCE**: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
        - @SequenceGenerator 필요
    - **TABLE**: 키 생성용 테이블 사용, 모든 DB에서 사용
        - @TableGenerator 필요
    - **AUTO**: 방언에 따라 자동 지정, 기본값
    

### **IDENTITY 전략**

- 기본 키 생성을 데이터베이스에 위임
- 주로 MySQL, PostgreSQL, SQL Server, DB2에서 사용
    - (예: MySQL의 AUTO_ INCREMENT)
- JPA는 보통 트랜잭션 커밋 시점에 INSERT SQL 실행
- AUTO_ INCREMENT는 데이터베이스에 INSERT SQL을 실행
- IDENTITY 전략은 `em.persist()` 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자를 조회 (즉, DB에 들어가야(INSERT 후) PK 값을 알 수 있음.)

### **SEQUENCE 전략**

- 데이터베이스 시퀀스는 유일한 값을 순서대로 생성하는 특별한 데이터베이스 오브젝트(예: 오라클 시퀀스)
- 오라클, PostgreSQL, DB2, H2 데이터베이스에서 사용

```java
@Entity
@SequenceGenerator(
	name = “MEMBER_SEQ_GENERATOR",
	sequenceName = “MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
	initialValue = 1, allocationSize = 1)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
```

**SEQUENCE - @SequenceGenerator**

> **주의: allocationSize 기본값 = 50**
> 

| 속성 |  설명 | 기본값 |
| --- | --- | --- |
| name | 식별자 생성기 이름 | 필수 |
| sequenceName | 데이터베이스에 등록되어 있는 시퀀스 이름 | hibernate_sequence |
| initialValue | DDL 생성 시에만 사용됨, 시퀀스 DDL을 생성할 때 처음 1 시작하는
수를 지정한다. | 1 |
| allocationSize | 시퀀스 한 번 호출에 증가하는 수(⭐️성능 최적화에 사용됨)
데이터베이스 시퀀스 값이 하나씩 증가하도록 설정되어 있으면 이 값
을 반드시 1로 설정해야 한다 | 50 |
| catalog, schema | 데이터베이스 catalog, schema 이름 |  |

### **TABLE 전략**

- 키 생성 전용 테이블을 하나 만들어서 데이터베이스 시퀀스를 흉내내는 전략
- 장점: 모든 데이터베이스에 적용 가능
- 단점: 성능

**매핑**

```sql
create table MY_SEQUENCES (
	sequence_name varchar(255) 
	not null,next_val bigint,
	primary key ( sequence_name )
)
```

```java
@Entity
@TableGenerator(
	name = "MEMBER_SEQ_GENERATOR",
	table = "MY_SEQUENCES",
	pkColumnValue = “MEMBER_SEQ", allocationSize = 1)
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,
				generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
```

### **권장하는 식별자 전략**

- **기본 키 제약 조건**: null 아님, 유일, **변하면 안된다.**
- 미래까지 이 조건을 만족하는 자연키는 찾기 어렵다. 대리키(대체키)를 사용하자.
    - 예를 들어 주민등록번호도 기본 키로 적절하기 않다.
- **권장: Long형(약 10억이 넘어도 동작하도록) + 대체키 + 키 생성전략 사용**

# 연관관계 매핑 기초

## 단방향 연관관계

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/b3515be6-99f6-4e3e-be4e-25ecb2912a15)


```java
@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
 

	@Column(name = "USERNAME")
	private String name;
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
 ...
}
```

### **객체 지향 모델링(ORM 매핑)**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/63bd542c-6989-41f0-a8d2-4e7fafb4d22c)


## **양방향 연관관계와 연관관계의 주인**

### **양방향 매핑**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/07ec5399-00b8-4100-b2ff-f1e80ef9aad9)


`Member.class`

```java
@Entity
public class Member {
	
	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
 

	@Column(name = "USERNAME")
	private String name;
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
 ...
}
```

`Team.class`

```java
@Entity
public class Team {

	@Id @GeneratedValue
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "team")
	List<Member> members = new ArrayList<Member>();
	...
}
```

### **연관관계의 주인과 mappedBy**

**객체와 테이블이 관계를 맺는 차이**

- **객체 연관관계는 2개**
    - 회원 -> 팀 연관관계 1개(단방향)
    - 팀 -> 회원 연관관계 1개(단방향)
- **테이블 연관관계는 1개**
    - 회원 <-> 팀의 연관관계 1개(양방향, 방향이 없음 FK로 모두 조회 가능)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/dde04e6a-7332-4b0d-9eeb-4990f3fd7784)


### **객체의 양방향 관계**

- 객체의 **양방향 관계는 사실 양방향 관계가 아니라 서로 다른 단뱡향 관계 2개다.**
- 객체를 양방향으로 참조하려면 **단방향 연관관계를 2개** 만들어야 한다.

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/57426385-08a7-4e84-a61d-055331e9c0d2)


### **테이블의 양방향 연관관계**

- 테이블은 **외래 키 하나**로 두 테이블의 연관관계를 관리
- MEMBER.TEAM_ID 외래 키 하나로 양방향 연관관계 가짐(양쪽으로 조인할 수 있다.)

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/65348ba4-45fd-4853-9f1a-e6006ed7cfce)


 

> **둘 중 하나로 외래 키를 관리해야 한다. 
> 즉, 연관관계의 주인을 정해야한다.**
> 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/2b24ad68-c5a5-4eb2-96b3-f36abe0b6adc)


### **연관관계의 주인(Owner)**

**양방향 매핑 규칙**

- 객체의 두 관계중 하나를 연관관계의 주인으로 지정
- **연관관계의 주인만이 외래 키를 관리(등록, 수정)**
- **주인이 아닌쪽은 읽기만 가능**
- 주인은 mappedBy 속성 사용X
- 주인이 아니면 mappedBy 속성으로 주인 지정

> ⭐️ **외래 키가 있는 곳을 주인으로 정해라!⭐️**
> - 1:다 관계에서는 다 쪽에 외래키가 있다.
> 

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/93a3cf0e-a82b-416b-9761-040d756a76b2)


### **양방향 매핑시 가장 많이 하는 실수**

- **연관관계의 주인에 값을 입력하지 않음**

```java
Team team = new Team();
team.setName("TeamA");
em.persist(team);

Member member = new Member();
member.setName("member1");

//역방향(주인이 아닌 방향)만 연관관계 설정
team.getMembers().add(member);

em.persist(member);
```

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/874c7210-d822-4dcc-bfac-7801bcc4f210)


### **양방향 매핑시 연관관계의 주인에 값을 입력해야 한다.**

- **순수한 객체 관계를 고려하면 항상 양쪽다 값을 입력해야 한다.**

```java
Team team = new Team();
team.setName("TeamA");
em.persist(team); // 영속성 등록

Member member = new Member();
member.setName("member1");

team.getMembers().add(member); // 역방향 연관관계 설정
//연관관계의 주인에 값 설정
member.setTeam(team); 

em.persist(member);
```

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/ee18cc28-952a-46bb-a467-03049c4a00f9)


### 주인에게만 입력해줬을 경우

- flush 이후에 JPA에서 members 컬렉션에 갱신된 member를 적용하기 때문에 중간에 `em.flush()` 가 없는 경우 (`em.clear()` 도) 순수한 Team 객체에서 조회한 팀의 members는 값이 동기화되어 있지 않다.

```java
Team team = new Team();
team.setName("TeamA");
em.persist(team);

Member member = new Member();
member.setName("member1");
member.setTeam(team); 

em.persist(member);

Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
List<Member> members = findTeam.getMembers();
```

> **즉, 양쪽 다 입력해주자!**
> 

### **양방향 연관관계 주의**

- **순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자**
- 연관관계 편의 메소드를 생성하자
    - `Member.class`
        
        ```java
        public void changeTeam(Team team) {
        		this.team = team;
        		team.getMembers().add(this);
        	}
        ```
        
- 양방향 매핑시에 무한 루프를 조심하자
    - 예: toString(), lombok, JSON 생성 라이브러리
        - lombok에서 toString() 사용하지 않는것 추천

### **양방향 매핑 정리**

- **단방향 매핑만으로도 이미 연관관계 매핑은 완료**
- 양방향 매핑은 반대 방향으로 조회(객체 그래프 탐색) 기능이 추
가된 것 뿐
- JPQL에서 역방향으로 탐색할 일이 많음
- 단방향 매핑을 잘 하고 양방향은 필요할 때 추가해도 됨
(테이블에 영향을 주지 않음)

### **연관관계의 주인을 정하는 기준**

- 비즈니스 로직을 기준으로 연관관계의 주인을 선택하면 안됨
- **연관관계의 주인은 외래 키의 위치를 기준으로 정해야함**

# 다양한 연관관계 매핑

### **연관관계의 주인**

- 테이블은 **외래 키 하나**로 두 테이블이 연관관계를 맺음
- 객체 양방향 관계는 A->B, B->A 처럼 **참조가 2군데**
- 객체 양방향 관계는 참조가 2군데 있음. 둘중 테이블의 외래 키를 관리할 곳을 지정해야함
- 연관관계의 주인: 외래 키를 관리하는 참조
- **주인의 반대편: 외래 키에 영향을 주지 않음, 단순 조회만 가능**

## 다대일 [N:1]

### 다대일 단방향

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/1bcd1d5c-4d05-4cb3-a4b7-0133b9f6eb12)


- ****가장 많이 사용하는 연관관계****
- ****다대일의 반대는 일대다****

### **다대일 양방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/435c34e5-d7d4-4bc8-8e0c-f1275590fccf)


- **외래 키가 있는 쪽이 연관관계의 주인**
- **양쪽을 서로 참조하도록 개발**

## 일대다 [1:N]

### **일대다 단방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/dc8df4ec-8a34-4105-8696-6fc6a585bebe)


- 일대다 단방향은 일대다(1:N)에서 **일(1)이 연관관계의 주인**
- 테이블 일대다 관계는 항상 **다(N) 쪽에 외래 키가 있음**
- 객체와 테이블의 차이 때문에 반대편 테이블의 외래 키를 관리하는 특이한 구조 (잘 안 씀)
- @JoinColumn을 꼭 사용해야 함. 그렇지 않으면 조인 테이블 방식을 사용함(중간에 테이블을 하나 추가함)

### 일대다 단방향 매핑의 단점

- 엔티티가 관리하는 외래 키가 다른 테이블에 있음
- 연관관계 관리를 위해 추가로 UPDATE SQL 실행
- 일대다 단방향 매핑보다는 **다대일 양방향 매핑을 사용**하자

## 일대일 [1:1]

### **일대일 관계**

- **일대일** 관계는 그 반대도 **일대일**
- 주 테이블이나 대상 테이블 중에 외래 키 선택 가능
    - 주 테이블에 외래 키
    - 대상 테이블에 외래 키
- 외래 키에 데이터베이스 유니크(UNI) 제약조건 추가

### **일대일: 주 테이블에 외래 키 단방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/10f87a1c-49d8-4fd0-b391-a044a78dd571)


- **다대일(@ManyToOne) 단방향 매핑과 유사**

### **일대일: 주 테이블에 외래 키 양방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/0d483a93-bf5d-4262-af12-280cc986efa8)


- **다대일 양방향 매핑 처럼 외래 키가 있는 곳이 연관관계의 주인**
- **반대편은 mappedBy 적용**

### **일대일: 대상 테이블에 외래 키 단방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a7d444f7-2e27-4d12-b08b-d8f2b48f0db9)


- **단방향 관계는 JPA 지원X**
- **양방향 관계는 지원**

### **일대일: 대상 테이블에 외래 키 양방향**

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/d71be21c-f595-4beb-b3e0-a7a71dd6b507)


- **사실 일대일 주 테이블에 외래 키 양방향과 매핑 방법은 같음**

### 정리

**주 테이블에 외래 키**

- 주 객체가 대상 객체의 참조를 가지는 것 처럼 주 테이블에 외래 키를 두고 대상 테이블을 찾음
- 객체지향 개발자 선호
- JPA 매핑 편리
- 장점: 주 테이블만 조회해도 대상 테이블에 데이터가 있는지 확인 가능
- 단점: 값이 없으면 외래 키에 null 허용

**대상 테이블에 외래키**

- 대상 테이블에 외래 키가 존재
- 전통적인 데이터베이스 개발자 선호
- 장점: 주 테이블과 대상 테이블을 **일대일에서 일대다 관계로 변경할 때 테이블 구조 유지**
- 단점: 프록시 기능의 한계로 **지연 로딩으로 설정해도 항상 즉시 로딩됨**

## 다대다 [N:M]

### **다대다**

- 관계형 데이터베이스는 정규화된 테이블 2개로 다대다 관계를 표현할 수 없음
- 연결(조인) 테이블을 추가해서 일대다, 다대일 관계로 풀어내야함

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/da33b85a-9edc-4b9d-b2e5-b37c41e2eadc)


- ****객체는 컬렉션을 사용해서 객체 2개로 다대다 관계 가능****

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/a09aa5d3-0abc-4bd7-9350-cc5b055acca8)


- **@ManyToMany 사용**
- **@JoinTable로 연결 테이블 지정**
- **다대다 매핑: 단방향, 양방향 가능**

### **다대다 매핑의 한계**

- **편리해 보이지만 실무에서 사용X**
- 연결 테이블이 단순히 연결만 하고 끝나지 않음
- 주문시간, 수량 같은 데이터가 들어올 수 있음

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/893eda7a-8c66-429e-b03b-b4393691e558)


### **다대다 한계 극복**

- ****연결 테이블용 엔티티 추가(연결 테이블을 엔티티로 승격)****
- ****@ManyToMany -> @OneToMany, @ManyToOne****

![image](https://github.com/Hoya324/COW-Spring-1/assets/96857599/8c2e094a-c1cf-4120-958f-5e8f7d1dfffa)


# Spring Data JPA

- **Spring Data JPA는 JPA를 사용하기 편하도록 만들어놓은 모듈이다.**
- Spring Data JPA는 JPA를 한 단계 더 추상화시킨 Repository 인터페이스를 제공한다.
- 이러한 Spring Data JPA는 Hibernate와 같은 JPA구현체를 사용해서 JPA를 사용하게 된다.
- Spring Data JPA를 사용하면 사용자는 더욱 간단하게 데이터 접근이 가능해진다.

## Spring Data JPA 기능

### **공통 인터페이스 기능**

- JpaRepository 인터페이스를 통해서 기본적인 CRUD 기능을 제공한다.

### **JpaRepository 사용법**

- **JpaRepository 인터페이스를 상속 받고, 제네릭에 관리할 <엔티티, 엔티티ID>를 주면 된다.**
- JpaRepository 인터페이스만 상속받으면 Spring Data JPA가 프록시 기술을 사용해서 구현 클래스를 만들고, 구현 클래스의 인스턴스를 만들어 스프링 빈으로 등록한다.

```java
public interface ItemRepository extends JpaRepository<Member, Long> {...}
```

### **쿼리 메서드 기능**

- **Spring Data JPA는 인터페이스에 메서드만 적어두면 메서드 이름을 분석해서 쿼리를 자동으로 만들고 실행해주는 기능을 제공한다.**

```java
public interface MemberRepository extends JpaRepository<Member, Long> {
        List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}

```

### **스프링 데이터 JPA가 제공하는 쿼리 메소드 기능**

- **조회**: find...By , read...By , query...By , get...By 예:) findHelloBy 처럼 ...에 식별하기 위한 내용(설명)이 들어가도 된다.
- **COUNT**: count...By 반환타입 long
- **EXISTS**: exists...By 반환타입 boolean
- **삭제**: delete...By , remove...By 반환타입 long
- **DISTINCT**: findDistinct , findMemberDistinctBy
- **LIMIT**: findFirst3 , findFirst , findTop , findTop3

### **@Query**

- 쿼리 메서드 기능 대신에 **직접 JPQL 또는 네이티브 쿼리를 사용하고 싶을 때는 @Query와 함께 작성하면 된다.**
- 이때는 메서드 규칙은 무시

```java
public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

    //쿼리 메서드 (아래 메서드와 같은 기능 수행)
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

    //쿼리 직접 실행
    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
}
```
