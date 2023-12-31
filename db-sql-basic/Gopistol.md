## 관계형 데이터베이스 (RDBMS)

Relational Database Management System의 약자로, 데이터를 관계형 모델로 표현하고 데이터를 효율적으로 관리하기 위해 SQL을 사용한다.

관계형 모델로 표현한다는 것은, 행과 열의 형태로 데이터를 저장하고 데이터 테이블 간 관계를 정의하는 것을 말한다. RDBMS의 대표적인 예로 Oracle, MySQL 등이 있다. 

비관계형 데이터베이스(NoSQL)도 있는데, NoSQL에는 키-값 형태로 데이터를 저장하거나, 문서, 그래프 데이터 등의 다양한 데이터 저장 방식을 사용한다.

RDBMS는 정형화된 데이터를 다루며, 데이터의 무결성, 일관성을 보장할 수 있다. 하지만 대규모 데이터 처리에는 한계가 있을 수 있다.

NoSQL은 비정형화된 데이터를 다루며, 대용량 데이터 처리에 용이하다. 하지만 RDBMS처럼 데이터의 일관성, 무결성을 보장할 수 없다.

## DDL, DML, DCL

SQL 문법은 DDL, DML, DCL로 나눌 수 있다.

### DDL

데이터베이스의 구조 정의에 사용하는 언어로, 테이블이나 관계의 구조를 생성할 때 사용한다.

- CREATE: 테이블, 뷰 또는 인덱스와 같은 새 데이터베이스 개체를 만든다
- ALTER: 기존 데이터베이스의 개체를 수정한다.
- DROP: 기존 테이블을 제거한다.
- TRUNCATE: 기존 테이블을 초기화한다.
- RENAME: 기존 테이블의 이름을 변경한다.

### DML

데이터 조작어로, 데이터베이스에 저장된 자료들을 입력, 수정, 삭제, 조회하는 언어이다.

- SELECT: 저장된 데이터를 조회한다.
- INSERT: 새로운 데이터를 저장한다.
- UPDATE: 저장된 데이터를 수정한다.
- DELETE: 저장된 데이터를 삭제한다.

### DCL

데이터 제어어로, 데이터베이스에 대한 각종 권한을 부여, 회수한다.

- GRANT: 유저에게 권한을 부여한다.
- REVOKE: 유저에게 권한을 회수한다.

### TCL

트랜잭션 제어어로, DCL에서 트랜잭션을 컨트롤하는 명령어를 TCL이라고 분류한다. DCL로 분류하는 경우도 있다.

- COMMIT: 올바르게 완료한 작업의 데이터를 데이터베이스에 영구적으로 반영한다.
- ROLLBACK: 작업 시작 이전의 상태로 되돌린다.
- SAVEPOINT: 저장점을 지정해, 이후 ROLLBACK과 함께 사용하여 특정 지점까지 롤백이 가능하다.

## 엔터티, 테이블, 필드, 레코드

### 엔터티

데이터 모델링에서 사용되는 객체이다. 예를 들어 학교, 학생처럼 눈에 보이는 개념일 수도 있고, 주문이나 결제처럼 눈에 보이지 않는 개념일 수도 있다.

<img width="681" alt="스크린샷 2023-08-14 오후 11 40 06" src="https://github.com/Youngcircle-kim/23-S-SW-backend/assets/104254012/e34eb059-af2c-4253-adc6-05e38cca0a2b">

그림처럼 엔터티는 테이블 하나라고 생각할 수 있다.

### 특징

- 유일한 식별자가 있어야 함
- 두 개 이상의 인스턴스의 집합이어야 함
- 속성을 포함해야 함
- 다른 엔터티와의 관계가 존재해야 함

### 테이블

관계 데이터베이스 모델에서 자료의 구조를 2차원의 표(행과 열)로 나타낸 것이다. 키를 지정함으로써 자료를 쉽고 빠르게 찾을 수 있다.

### 필드

항목, 어떠한 의미를 지니는 정보로써 데이터베이스 시스템에서 처리되는 최소 단위이다.

### 레코드

데이터베이스에서 하나의 단위로 취급되는 자료의 집합
하나의 레코드는 테이블에서 가로 한 줄, 행, 튜플이라고도 한다.

## 키(기본키, 외래키)

### 기본키

후보키들 중 하나를 선택한 키로, 최소성과 유일성을 만족한다.

테이블 하나 당 오직 1개만 지정할 수 있다.

NULL값을 가질 수 없고, 중복된 값을 가질 수 없다.

대체키: 후보키가 두 개 이상일 경우 기본키로 지정되지 않고 남은 후보 키를 말한다.

### 외래키

기본키를 참조하는 속성으로, 테이블 간 관계를 나타내기 위해 사용한다.

외래키가 참조하는 테이블은 외래키가 있는 테이블보다 먼저 만들어져야 한다.

참조될 열의 값은 참조될 테이블의 기본키여야 한다.

## 관계(1:1, 1:N, N:M)

한쪽 엔터티와 관계를 맺은 엔터티 쪽이 하나의 객체만 가지는지, 여러 개의 객체를 가질 수 있는지에 따라 세 가지로 나뉜다.

### 1:1

하나의 레코드가 다른 테이블의 레코드 하나와 연결된 경우

### 1:N

하나의 레코드가 서로 다른 여러 개의 레코드와 연결된 경우

### N:M

여러 개의 레코드가 다른 테이블의 여러 개의 레코드와 관계가 있는 경우

## WHERE, GROUP BY, HAVING, ORDER BY

### WHERE + 비교대상 + 비교연산자 + 비교할 값

SELECT와 FROM 절까지는 똑같고, WHERE 옆에 조건문을 적어주면 된다.

비교 연산자 종류

- =: 값이 같은지 비교
- ≠, <>, ^=: 값이 다른지 비교
- <, >: 작거나 큰지 비교
- BETWEEN A AND B: 값이 A ~ B 안에 있는지 비교
- IN: 비교할 대상들 안에 비교되는 값이 포함되는지 비교
- LIKE: 특정 값과 비슷한 값 탐색 가능
- IS NULL: 칼럼이 NULL인지 비교

### GROUP BY, HAVING

같은 값을 가진 행끼리 하나의 그룹으로 뭉쳐줌

Having 절은 Group by 를 통해 그룹핑한 행에만 사용할 수 있음 (조건)

## SELECT 쿼리 실행 순서

각각의 쿼리가 언제 실행되는지 모르면 에러가 날 수 있다.

<img width="667" alt="스크린샷 2023-08-15 오후 11 12 24" src="https://github.com/Youngcircle-kim/23-S-SW-backend/assets/104254012/8037cdac-2f33-42ce-b96b-76b235cd85d4">

### FROM

조회할 테이블을 지정함
이후 JOIN을 실행해 하나의 가상 테이블로 결합

### LIMIT

결과 중 몇 개의 행을 보여줄지 선택

SELECT → FROM → WHERE 순으로 쿼리를 작성하곤 하지만, 실행 순서에 맞추어 FROM → WHERE → SELECT 순으로 작성하게 되면 성능적으로 튜닝할 수 있는 요소를 발견하기 쉽다고 한다.

## Programmers SQL Practice

1번

```sql
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE '%강원도%'
ORDER BY FACTORY_ID
```

2번

```sql
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME ASC LIMIT 1
```

3번

```sql

SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = 'Sick'
```

4번

```sql
SELECT COUNT(DISTINCT NAME) 
FROM ANIMAL_INS
WHERE NAME IS NOT NULL;
```

5번

```sql
SELECT MAX(PRICE) AS MAX_PRICE
FROM PRODUCT
```
