# 데이터 베이스 기초

목차

1. 관계형 데이터베이스란?
2. DDL, DML, DCL
3. 엔터티, 테이블, 필드, 레코드
4. 키(기본키, 외래키)
5. 관계(1:1, 1:N, N:M)
6. WHERE, GROUP BY, HAVING, ORDERBY
7. select 쿼리 실행 순서

### 1. 관계형 데이터베이스란?

- 관계형 데이터베이스 (Relational Database Management System)
    - 키(key)와 값(value)들의 간단한 관계를 테이블화 시킨 매우 간단한 원칙의 전산정보 데이터베이스이다.
- 사용 이유
    - 정해진 스키마에 따라서 데이터를 저장하기 때문에 명확한 데이터 구조를 보장한다.
    - 데이터의 분류, 정렬, 탐색의 속도가 빠르다.
    - SQL을 사용하여 데이터를 다룰 수 있다.

### 2. DDL, DML, DCL

1) DDL(Data Definition Language-데이터 정의어)

- 테이블이나 관계의 구조를 생성하는 데 사용하며 CREATE, ALTER, DROP문 등이 있다.

- CREATE 문
    - 테이블의 구조를 만드는 CREATE문
    - CREATE 문 구조

    ```sql
    CREATE TABLE 테이블 이름
    ( {속성이름 데이터타입
         [NULL | NOT NULL | UNIQUE | DEFAULT 기본값 | CHECK 체크조건]
       }
        [PRIMARY KEY 속성이름(들)]
        [FOREIGN KEY 속성이름 REFERENCES 테이블이름(속성이름)]
                         [ON DELETE {CASCADE | SET NULL}]
    )
    ```

    - 문법에서 대문자는 키워드, { }안의 내용은 반복 가능, [ ]은 선택적으로 사용, |는 1개 선택, < >은 해당되는 문법 사항이 있음을 나타낸다.
    - NOT NULL :NULL 값을 허용하지 않은 제약
    - UNIQUE : 유일한 값에 대한 제약
    - DEFALUT : 기본값 설정
    - CHECK : 값에 대한 조건 부여
    - PRIMARY KEY : 기본키 지정
    - FOREIGN KEY : 외래키 지정
    - ON DELETE : 투플의 삭제 시 외래키 속성에 대한 동작을 나타낸다.
        - CASCADE
        - SET NULL
        - RESTRICT(명시되지 않으면)

- ALTER문
    - ALTER문은 생성된 테이블의 속성과 속성에 관한 제약을 변경하며, 기본키 및 외래키를 변경한다.

    ```sql
    ALGER TABLE 테이블이름
          [ADD 속성이름 데이터타입]
          [DROP COLUMN 속성이름]
          [MODIFY 속성이름 데이터타입]
          [MODIFY 속성이름 [NULL | NOT NULL]]
          [ADD PRIMARY KEY(속성이름)]
          [[ADD|DROP]제약이름]
    ```

  → 속성을 변경할때에는 MODIFY를 사용한다.

- DROP문
    - DROP문은 테이블을 삭제하는 명령이다. DROP문은 테이블의 구조와 데이터를 모두 삭제하므로 사용에 주의해야 한다.
    - 데이터만 삭제하려면 DELETE문을 사용한다.

2) DML(Data Manipulation Language-데이터 조작어)

- 테이블에 데이터를 검색, 삽입, 수정, 삭제하는 데 사용하며 SELECT, INSERT, DELETE, UPDATE 문 등이 있다. 여기서 SELECT 문은 특별히 질의어(query)라고 부른다.

- SELECT문
    - 속성을 선택한다.
    - 새로 만드는 테이블의 열 순서를 결정한다.
    - *(asterisk)는 모든 열(속성)을 나타낸다.
    - 속성 값이 중복될 수 있는데, 중복을 제거하고 싶으면 DISTINCT라는 키워드를 사용한다
        - SELECT DISTINCT publisher

- INSERT 문
    - INSERT문은 테이블에 새로운 투플을 삽입하는 명령

    ```sql
    INSERT INTO 테이블이름[(속성리스트)]
           VALUES (값리스트)
    ```

    - 대량삽입을 할 때 VALUES대신 SELECT문을 이용한다.

- UPDATE
    - UPDATE문은 특정 속성 값을 수정하는 명령이다.

    ```sql
    UPDATE 테이블이름
    SET 속성이름=업데이트 값, 속성이름2=업데이트 값, ...
    [WHERE <검색조건>];
    ```

  → UPDATE 수행 시 실수를 방지하기 위해 기본키 속성을 사용하는 것이 좋다.


- DELETE문
    - DELETE문은 테이블에 있는 기존 투플을 삭제하는 명령이다.

3) DCL

- 데이터의 사용 권한을 관리하는 데 사용하며 GRANT, REVOKE 문 등이 있다

- GRANT : 특정 데이터베이스 사용자에게 특정 작업에 대한 수행 권한을 부여
- REVOKE : 특정 데이터베이스 사용자에게 특정 작업에 대한 수행 권한을 박탈, 회수
- commit :  트랜잭션의 작업을 저장
- rollback : 트랜잭션의 작업을 취소, 원래대로 복구

### 3. 엔터티, 테이블, 필드, 레코드

1) 엔터티(Entity)

- 사람, 장소, 물건, 사건, 개념 등의 명사이다. 업무상 관리가 필요하여 저장되기 위한 어떤 것이다.
- 인스턴스의 집합이고, 자신이 가진 인스턴스들을 설명하고 나타낼 수 있는 속성(Attribute)를 가진다.

- Entity의 특징
    - 엔터티가 포함하는 인스턴스에 대해 유일한 식별자로 식별이 가능해야 함
    - 엔터티는 지속적으로 존재하는 두개 이상의 인스턴스들의 조합이어야 함
    - 엔터티는 반드시 속성을 지녀야 함
    - 엔터티는 업무 프로세스에 의해서 이용되어야 함
    - 엔터티는 다른 엔터티와 최소 한 개 이상의 관계가 있어야 함

2) 테이블(Table)

- 테이블, 빠른 참조를 위해 적당한 형태로 자료를 모아 놓은 것이다.
- 관계 데이터 베이스 모델에서 자료의 구조를 2차원의 표로 나타낸 것이다.
- 행과 열의 형태로 관리되며 키를 지정함으로써 원하는 자료를 빠르고 쉽게 찾아 낼 수도 있다.
- 스키마와 인스턴스로 이루어진다.
    - 스키마
        - 어떻게 구성되는지 어떤 정보를 담고 있는지에 대한 기본적인 구조
    - 인스턴스
        - 정의된 스키마에 따라 테이블에 실제로 저장되는 데이터의 집합을 의미한다.

3) 필드(Fields)

- 열(column)에 해당하는 가장 작은 단위의 데이터를 의미한다. 필드는 엔터티의 속성을 표현한다.
- 속성과 필드의 차이
    - 속성(스키마)의 실체가 담기는 곳이 필드(인스턴스)이다.

4) 레코드(Records)

- 연관된 필드의 집합이다. 행(row)에 해당되고, 튜플(Tuple)이라고 불리기도 한다.

### 4. 키(기본키, 외래키)

1) 기본키

- 기본키(primary key)는 여러 후보키(candidate key) 중 하나를 선정하여 대표로 삼는 키를 말한다.
- 기본키 특성
    - 릴레이션 내 투플을 식별할 수 있는 고유한 값을 가져야 한다.
    - NULL값은 허용하지 않는다.
    - 키값의 변동이 일어나지 않아야 한다.
    - 최대한 적은 수의 속성을 가진 것이어야 한다.
    - 키를 사용하는 데 있어서 문제 발생 소지가 없어야 한다.

2) 외래키

- 외래키(FK, foreign key)는 다른 릴레이션의 기본키(primary key)를 참조하는 속성을 말한다.
- 외래키는(foreign key) 다른 릴레이션의 기본키(primary key)를 참조하여 관계 데이터 모델의 특징인 릴레이션 간의 관계(relationship)를 표현한다.
- 외래키의 특성
    - 릴레이션의 기본키와 달리 NULL 값을 포함할 수 있다.
    - 중복값도 허용한다.
    - 참조하고 참조되는 양쪽 릴레이션의 도메인이 서로 같아야한다.
    - 데이터의 일관성을 유지해야한다.
    - 외래키 사용시 참조하는 릴레이션이 꼭 다른 릴레이션일 필요가 없다. 자기 자신이 자신의 기본키를 외래키로 사용할 수 있다.
    - 다른 릴레이션의 기본키를 참조하는 속성이다.
    - 외래키가 기본키의 일부가 될 수 있다.

### 5. 관계(1:1, 1:N, N:M)

1) 일대일(1:1) 관계

- 개체와 개체가 일대일로 대응하는 관계
- ex) 사원이 개인별로 한 대의 컴퓨터만 사용하는 경우

2) 일대다(1:N), 다대일(N:1) 관계

- 하나의 개체가 다른 타입의 여러 개체와 관계를 맺는 경우
- ex) 하나의 학과에는 여러 명의 학생이 소속되어 있는 경우

3) 다대다(N:N) 관계

- 서로 복합적인 관계를 맺고 있는 경우
- ex) 한 학생은 여러 강좌를 수강할 수 있고, 한 강좌 역시 여러 학생이 들을 수 있다.

### 6. WHERE, GROUP BY, HAVING, ORDER BY

1) WHERE

- 테이블에서 특정 조건에 부합하는 데이터만 조회하고 싶을 때 사용하는 것
    - WHERE 절에 조건으로 사용할 수 있는 술어

  | 술어 | 연산자 | 사용 예 |
      | --- | --- | --- |
  | 비교 | =,<>, ≤, >, ≥ | price <20000 |
  | 범위 | BETWEEN | price BETWEEN 10000 AND 20000 |
  | 집합 | IN, NOT IN | price IN(10000, 20000, 30000) |
  | 패턴 | LIKE | bookname LIKE ‘축구의 역사’ |
  | NULL | IS NULL, IS NOT NULL | price is NULL |
  | 복합조건 | AND, OR, NOT | (price<20000) AND (bookname LIKE ‘축구의 역사’ |
    - 패턴
        - ` ` 를 사용해야함.
        - 축구가 포함된 도서를 찾고 싶다면 와일드 문자(%)를 사용
            - WHERE bookname LIKE `%축구%`;
        - 와일드 문자(_)는 특정 위치에 한 문자만 대신할 때 사용
            - EX) 도서이름의 왼쪽 두번째 위치에 ‘구’라는 문자열을 갖는 도서를 검색하시오
            - WHERE bookname LIKE `_구%`
        - 와일드 문자의 종류

      | 와일드 문자 | 의미 | 사용 예 |
              | --- | --- | --- |
      | + | 문자열을 연결 | ‘골프’+’바이블’ : 골프 바이블 |
      | % | 0개 이상의 문자열과 일치 | %축구% : 축구를 포함하는 문자열 |
      | [] | 1개의 문자와 일치 | ‘[0-5]%’ :0-5사이 숫자로 시작하는 문자열 |
      | [^] | 1개의 문자와 불일치 | ‘[^0-5]%’ :0-5사이 숫자로 시작하지 않는 문자열 |
      | _ | 특정 위치의 1개의 문자와 일치 | ‘_구%’ : 두 번째 위치에 ‘구’가 들어가는 문자열 |

2) GROUP BY

- GROUP BY 절을 사용하여 속성 값이 같은 값끼리 그룹을 만들 수 있다.

```sql
SELECT 컬럼 FROM 테이블 WHERE 조건식 GROUP BY 그룹화할 컬럼;
```

- GROUP BY절은 주로 집계 함수와 같이 사용된다.
    - 집계함수

        1. COUNT() : 행의 개수를 세어줌

        2. AVG() : 행 안에 있는 값의 평균을 내어줌

        3. MIN() : 행 안에 있는 값의 최솟값을 반환해줌

        4. MAX() : 행 안에 있는 값의 최댓값을 반환해줌

        5. SUM() : 행 안에 있는 값의 합을 내어줌


→ GROUP BY와 집계함수를 같이 사용하여 집계성 데이터를 추출한다.

3) HAVING

- SELECT문에 GROUP BY절이 존재할 때만 사용할 수 있으며, GROUP BY절을 통해 그룹화된 결과 값의 범위를 제한하는 데 사용한다.
- HAVING과 WHERE 차이점
    - WHERE 절은 출력 대상 행을 제한하고, HAVING절은 그룹화된 대상을 출력에서 제한한다는 것이 차이점이다.


4) ORDER BY

- 데이터베이스에서 조회된 결과를 특정 컬럼의 값에 따라 정렬할 때 사용합니다. SELECT와 함께 사용하며, 오름차순(ASC) 또는 내림차순(DESC) 순서로 정렬할 수 있습니다.
- 기본값은 오름차순(ASC)이다. 내림차순 순서로 정렬하기 위해선 원하는 속성 뒤에 DESC를 작성하면 된다.
- 두 가지 이상의 속성을 적을 경우, 기입한 순서대로 우선순위를 가지고 정렬하게 된다.

### 7. SELECT 쿼리 실행 순서

```sql
SELECT [조회할 열1 이름], [열2 이름], ..., [열N 이름]
FROM [조회할 테이블 이름]
WHERE [조회할 행을 선별하는 조건식]
GROUP BY [그룹화할 열 지정(여러 개 지정 가능)]
HAVING [출력 그룹을 제한하는 조건식]
ORDER BY [정렬하려는 열 지정];
```

1. FROM : 조회 테이블 확인
2. ON : 조인 조건 확인
3. JOIN : 테이블 조인
4. WHERE : 데이터 추출 조건 확인
5. GROUP BY : 특정 컬럼 그룹화
6. HAVING : 그룹화 이후 데이터 추출 조건
7. SELECT : 데이터 추출
8. DISTINCT : 중복 제거
9. ORDER BY : 데이터 순서 정렬

# SQL 연습

### 1번 문제

```sql
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE "%강원도%"
ORDER BY FACTORY_ID
;
```

### 2번 문제

```sql
SELECT NAME
FROM ANIMAL_INS 
ORDER BY DATETIME
LIMIT 1
;
```

### 3번 문제

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = "Sick"
ORDER BY ANIMAL_ID
;
```

### 4번 문제

```sql
SELECT COUNT(DISTINCT NAME)
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
;
```

### 5번 문제

```sql
SELECT PRICE AS MAX_PRICE
FROM PRODUCT
WHERE PRICE IN(SELECT MAX(PRICE)
              FROM PRODUCT)
;
```