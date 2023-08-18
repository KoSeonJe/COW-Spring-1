# 관계 데이터 모델

## 1. 관계 데이터 모델의 개념

### 릴레이션

![릴레이션 스키마와 인스턴스](https://github.com/Hoya324/2023-DBStudy/assets/128007622/fcaf6167-8676-4eba-be66-b0c4b87a3d6c)

- 행과 열로 구성된 테이블
- 릴레이션의 각 열은 속성(attribute), 각 행은 튜플(tuple)이라는 용어로 정의한다.
- 스키마와 인스턴스로 이루어진다.

#### 스키마(schema)

- 관계 데이터베이스의 릴레이션이 구성되는 방식과 담고있는 정보에 대한 구조를 정의
- 테이블의 첫 행인 헤더(header)에 나타낸다.
- 각 속성이 가질 수 있는 값의 종류를 나타내는 집합을 도메인(domain)이라고 한다.
- 릴레이션이 가지는 속성의 개수를 차수(degree)라고 한다.

#### 인스턴스(instance)

- 정의된 스키마에 따라 테이블에 실제로 저장되는 데이터의 집합
- 릴레이션 내의 모든 튜플들은 서로 중복되지 않아야 한다.
- 릴레이션에 저장된 튜플의 수를 카디날리티(cardinality)라도 한다.

#### 릴레이션 구조 관련 용어 정리

|    릴레이션 용어     | 같은 의미로 통용되는 용어 |  파일 시스템 용어 |
|:--------------:|:--------------:|:----------:|
| 릴레이션(relation) |   테이블(table)   |  파일(file)  |
|  스키마(schema)   | 내포(intension)  | 헤더(header) |
| 인스턴스(instance) | 외연(extension)  |  데이터(data) |
|   튜플(tuple)    |     행(row)     | 레코드(record) |
| 속성(attribute)  |   열(column)    | 필드(field)  |

#### 릴레이션의 특징

1. 속성은 단일 값을 가진다.
2. 속성은 서로 다른 이름을 가져야 한다.
3. 한 속성의 값은 모두 같은 도메인 값을 가진다.
4. 속성의 순서는 상관없다.
5. 릴레이션 내의 튜플은 중복될 수 없다.
6. 튜플의 순서는 상관없다.

#### 관계 데이터 모델

##### 제약조건(constraints)

- 각 릴레이션에 저장된 데이터 값이 가져야하는 제약

##### 관계 대수(relational algebra)

- 릴레이션을 다루기 위한 연산

## 2. 무결성 제약조건

- 데이터베이스의 정확성, 일관성을 보장하기 위해 저장, 삭제, 수정 등을 제약하기 위한 조건
- 데이터베이스에 저장된 데이터는 결함이 없어야 한다.
- 따라서 데이터의 삽입, 삭제, 수정 시에 여러 제약조건이 따른다.

### 키(Key)

- 릴레이션에서 특정 튜플을 식별할 때 사용하는 속성 혹은 속성의 집합
- 릴레이션은 중복된 튜플을 허용하지 않기 때문에 각 튜플의 속성중 적어도 어느 하나는 다른 값을 가져야 한다.
- 키가 되는 속성은 값이 달라서 서로 구별이 가능해야 한다.

#### 슈퍼키(super key)

- 튜플을 식별할 수 있는 하나의 속성 혹은 속성의 집합

#### 후보키(candidate key)

- 튜플을 식별할 수 있는 속성의 최소 집합

#### 기본키(primary key)

- 여러 후보키 중 하나를 선정하여 대표로 삼은 키
- 후보키가 하나인 경우 그 후보키를 기본키로 사용하고, 여러개 인 경우 릴레이션의 특성을 반영하여 하나를 선택한다.

##### 기본키 선정 시 고려할 사항

- 릴레이션 내 튜플을 식별할 수 있는 고유한 값을 가져야 한다.
- NULL값은 허용하지 않는다.
- 키 값의 변동이 일어나지 않아야 한다.
- 최대한 적은 수의 속성을 가진 것이여야 한다.
- 향후 키를 사용하는데 있어서 문제 발생 소지가 없어야 한다.

릴레이션 스키마를 표현할 때 기본키는 다음과 같이 밑줄을 그어 표시한다.

> 릴레이션 이름(<U>속성 1</U>, 속성 2, ···, 속성 N)

#### 대리키(surrogate key) / 인조키(artificial key)

- 기본키를 사용할 수 없는 경우 일련번호와 같은 가상의 속성을 만들어 삼는 키
- 대리키는 DBMS에서 임의로 생성하는 값이기 때문에 사용자가 직관적으로 그 값의 의미를 알 수 없다.

#### 대체키(alternate key)

- 기본키로 선정되지 않은 후보키

#### 외래키(foreign key)

- 다른 릴레이션의 기본키를 참조하는 속성
- 외래키가 성립하기 위해서는 참조하고 참조되는 양쪽 릴레이션의 도메인이 서로 같아야 한다.

### 무결성 제약조건 (integrity constraint)

#### 데이터 무결성(data integrity)

- 데이터베이스에 저장된 데이터의 일관성과 정확성을 지키는 것

#### 도메인 무결성 제약조건 / 도메인 제약

- 릴레이션 내의 튜플들이 각 속성의 도메인에 지정된 값만을 가져야 한다는 조건

#### 개체 무결성 제약조건 / 기본키 제약

- 기본키는 NULL값을 가져서는 안되며 릴레이션 내에 오직 하나의 값만 존재해야 한다는 조건

#### 참조 무결성 제약조건 / 외래키 제약

- 자식 릴레이션의 외래키는 부모 릴레이션의 기본키와 도메인이 동일해야 함
- 자식 릴레이션의 값이 변경될 때 부모 릴레이션의 제약을 받음

### 무결성 제약조건의 수행

- 릴레이션의 데이터가 변경될때 제약조건의 위배가 발생할 수 있음
- 따라서 제약조건의 준수 여부는 데이터의 변경(삽입, 수정, 삭제)이 있을 때마다 확인해야 한다.

#### 개체 무결성 제약조건

- 튜플을 삽입하거나 수정할 때마다 개체 무결성 제약조건을 지키는지 확인한다.
- 만약 조건에 맞지 않으면 삽입 연산을 거부하고 오류 메시지를 보낸다.

##### 삽입

- 새로운 튜플이 삽입될 때 기본키가 유일한지, NULL값이 아닌지를 검사한다.

##### 수정

- 수정 연산 또한 삽입 연산과 동일한 제약에 따라 처리된다.

##### 삭제

- 개체 무결성 제약조건을 확인할 필요는 없지만, 해당 튜플의 기본키를 다른 릴레이션에서 외래키로 참조하고 있으면 참조 무결성 제약조건을 검사한다.

#### 참조 무결성 제약조건

- 단일 릴레이션이 아닌, 참조 관계가 있는 두 릴레이션에서 연산이 발생할 때 수행된다.

#### 삽입

- 자식 릴레이션에 새로운 튜플이 삽입될 때 해당 튜플의 기본키가 부모 릴레이션에 존재하는지 확인한다.
- 부모 릴레이션에 새로운 튜플이 삽입될 때는 참조 무결성 제약조건을 고려할 필요가 없다.

#### 삭제

- 부모 릴레이션에서 튜플을 삭제하는 경우 해당 튜플을 참조하는 다른 릴레이션이 있는지 확인한다.
- 자식 릴레이션의 튜플을 삭제하는 경우 참조 무결성 제약조건을 고려할 필요가 없다.

##### 참조 무결성 제약조건의 옵션(부모 릴레이션에서 튜플을 삭제할 경우)

|    명령어     |                     의미                      |
|:----------:|:-------------------------------------------:|
| RESTRICTED |            부모 릴레이션에서의 삭제 작업을 거부             |
|  CASCADE   |            자식 릴레이션의 관련 튜플을 함께 삭제            |
|  DEFAULT   |       자식 릴레이션의 관련 튜플을 미리 설정해둔 값으로 변경        |
|    NULL    | 자식 릴레이션의 관련 튜플을 NULL 값으로 설정(NULL 값을 허가한 경우) |

#### 수정

- 수정은 삭제와 삽입 명령이 연속해서 수행되는 것과 같다.
- 부모 릴레이션의 수정이 일어날 경우 먼저 삭제 옵션에 따라 처리된 후, 문제가 없으면 다시 삽입 제약조건에 따라 처리된다.

## 3. 관계대수(relational algebra)

### 관계대수(relational algebra)

- 릴레이션에서 원하는 정보와 그 정보를 검색하기 위해서 어떻게 유도하는가를 기술하는 절차적인 언어

### 관계대수 연산자

![관계대수 연산자](https://github.com/Hoya324/2023-DBStudy/assets/128007622/d7339e39-39e4-4a7c-bf0b-88c51880dc6c)

- 릴레이션에 사용되는 연산자
- 순수 관계 연산과 일반 집합 연산 두 가지로 나눌 수 있다.

### 관계대수식

![관계대수식](https://github.com/Hoya324/2023-DBStudy/assets/128007622/1fbaf614-7016-4e24-b8f7-d80fc0e887d0)

- 릴레이션 간 연산을 통해 결과 릴레이션을 찾는 절차를 기술한 언어
- 관계대수식의 결과는 릴레이션으로 반환된다.

# SQL 기초 문법

## 1. SQL 개요

- SQL은 1970년대 IBM에서 개발한 관계형 데이터베이스 언어이다.
- SQL은 완전한 프로그래밍 언어가 아닌 데이터 부속어(data sublanguage)에 해당한다.
- SQL은 Java나 C등의 클라이언트, 서버 응용 프로그램이나 HTML등 여러 프로그램과 문서들에 삽입되어 사용될 수 있다.

### SQL과 일반 프로그래밍 언어의 차이점

|구분|       SQL        |   일반 프로그래밍 언어   |
|:-:|:----------------:|:---------------:|
|용도| 데이터베이스에서 데이터를 추출 |  용도가 정해져있지 않음   |
|입출력| 입력과 출력 모두 테이블 형태 | 입력과 출력 형태가 자유로움 |
|번역|DBMS|컴파일러, 인터프리터|

### SQL 문법 분류

SQL은 기능에 따라 **DLL(Data Definition Language, 데이터 정의어)**, **DML(Data Manipulation Language, 데이터 조작어)** 와 **DCL(Data Control Language, 데이터 제어어)** 로 분류된다.

- DLL(데이터 정의어) : 테이블이나 관계의 구조를 생성하는데 사용, CREATE, ALTER, DROP 문 등이 있다.
- DML(데이터 조작어) : 테이블에 데이터를 검색, 삽입, 수정하는데 사용, SELECT, ALTER, DROP 문 등이 있다. SELECT 문은 특별히 질의어라고 부른다.
- DCL(데이터 제어어) : 데이터의 사용 권한을 관리하는데 사용, GRANT, REVOKE 문 등이 있다.

## 2. 데이터 조작어 - 검색

### SELECT 문

- SELECT 문은 데이터를 검색하는 명령어로, 질의어(query)라고도 부른다.
- SELECT 문은 검색 결과를 테이블 형태로 출력한다.

```mysql
/* Book 테이블에서 price 값이 10000 이상인 bookname, price를 가져오는 쿼리문 */
SELECT bookname, publisher
FROM Book
WHERE price >= 10000;
```

- SQL문은 세미콜론(;)으로 끝마친다.
- 세미콜론은 생략 가능하지만 작성해주는 것이 좋다.
- 주석은 ``` /* 여러줄 주석 */ ```, ``` -- 한 줄 주석 ``` 으로 작성한다.
- SELECT 문에서 열 순서(bookname, publisher)를 바꾸면 출력되는 결과 테이블의 순서도 바뀐다.

```mysql
/* Book 테이블의 모든 열을 가져오는 쿼리문 */
SELECT *
FROM Book;
```

- ``` * ```를 사용하면 모든 열을 나타낼 수 있다.

```mysql
/* Book 테이블의 publisher을 중복을 제거하여 가져오는 쿼리문 */
SELECT DISTINCT publisher
FROM Book;
```

- ``` DISTINCT ``` 키워드를 사용하여 중복을 제거할 수 있다.

### WHERE 문

- WHERE 문은 검색 조건을 지정할 때 사용한다.
- 조건에 사용할 수 있는 술어(predicate)는 아래와 같다.

| 술어(predicate) | 연산자 |
|:-------------:|:---:|
|      비교       |  =, <>, <, <=, >, >= |
|      범위       |   BETWEEN |
|      집합       |   	IN, NOT IN |
|      패턴       |  	LIKE  |
|     NULL      |  	IS NULL, IS NOT NULL  |
|     복합조건      |    AND, OR, NOT |

#### 비교

```mysql
/* 가격이 20000원 미만인 도서 검색 */
SELECT *
FROM Book
WHERE price < 20000;
```

#### 범위

```mysql
/* 가격이 10000원 이상 20000원 이하인 도서 검색 1 */
SELECT *
FROM Book
WHERE price BETWEEN 10000 AND 20000;
```

```mysql
/* 가격이 10000원 이상 20000원 이하인 도서 검색 2 */
SELECT *
FROM Book
WHERE price >= 10000 AND price <= 20000;
```

#### 집합

- ``` IN ```, ``` NOT IN ``` 연산자를 사용

```mysql
/* 출판사가 '굿스포츠' 혹은 '대한미디어'인 도서 검색 */
SELECT *
FROM Book
WHERE publisher IN ('굿스포츠', '대한미디어');
```

```mysql
/* 출판사가 '굿스포츠' 혹은 '대한미디어'가 아닌 도서 검색 */
SELECT *
FROM Book
WHERE publisher NOT IN ('굿스포츠', '대한미디어');
```

#### 패턴

- ``` LIKE ``` 연산자를 사용

```mysql
/* '축구의 역사'를 출간한 출판사를 검색 */
SELECT bookname, publisher
FROM Book
WHERE bookname LIKE '축구의 역사';
```

```mysql
/* 도서이름에 '축구'가 포함된 출판사를 검색 */
SELECT bookname, publisher
FROM Book
WHERE bookname LIKE '%축구%';
```

- 와일드 문자 ``` % ```는 임의의 문자열을 대신하는 기호이다.

```mysql
/* 도서이름의 왼쪽 두 번째 위치에 '구'라는 문자열을 갖는 도서를 검색 */
SELECT *
FROM Book
WHERE bookname LIKE '_구%';
```

- 와일드 문자 ``` % ```는 임의의 문자열을 대신하는 기호이다.

#### 복합조건

```mysql
/* 축구에 관한 도서 중 가격이 20,000원 이상인 도서를 검색 */
SELECT *
FROM Book
WHERE bookname LIKE '%축구%' AND price >= 20000;
```

- ``` AND ```, ``` OR ```, ``` NOT ``` 논리 연산자를 사용한다.

### ORDER BY

```mysql
/* 도서를 이름순으로 검색 */
SELECT *
FROM Book
ORDER BY bookname;
```

- ``` ORDER BY ``` 문을 사용하여 검색 결과를 특정 순서대로 출력할 수 있다.
- ``` DESC ``` 키워드로 내림차순, ``` ASC ``` 키워드로 오름차순으로 출력할 수 있다.

### 집계 함수

- 테이블의 각 열에 대해 계산 함수
- SUM, AVG, MIN, MAX, COUNT 5가지가 있다.
- AS 키워드로 열 이름을 부여할 수 있다.

```mysql
/* 주문된 도서의 총 판매액 */
SELECT SUM(saleprice) AS 총매출
FROM Orders;
```

### GROUP BY

- ``` GROUP BY ``` 문으로 속성 값이 같은 값끼리 그룹을 만들 수 있다.

```mysql
/* 고객별로 주문한 도서의 총 수량과 총 판매액 */
SELECT custid, COUNT(*) AS 도서수량, SUM(saleprice) AS 총액
FROM Orders
GROUP BY custid;
```

- ``` HAVING ``` 문은 ``` GROUP BY ``` 의 결과로 나타나는 그룹을 제한하는 역할을 한다.

```mysql
/* 가격이 8000원 이상인 도서를 구매한 고객에 대해 고객별 주문 도서의 총 수량을 구함. 단, 두 권 이상 구매한 고객에 대해서만 탐색 */
SELECT custid, COUNT(*) AS 도서수량
FROM Orders
WHERE saleprice >= 8000
GROUP BY custid
HAVING count(*) >= 2;
```

### JOIN

- ``` JOIN ``` 은 한 테이블의 행을 다른 테이블의 행에 연결하여 두 개 이상의 테이블을 결합하는 연산이다.

#### 카티전 프로덕트

```mysql
/* Customer와 Orders 테이블을 합치는 연산 */
SELECT *
FROM Customer, Orders;
```

- 조건 없이 테이블을 결합하는 연산

#### 동등조인

```mysql
/* custid가 같은 항목끼리 연결 후 정렬 */
SELECT *
FROM Customer, Orders
WHERE Customer.custid=Orders.custid
ORDER BY Customer.custid;
```

- 동등조건에 의하여 테이블을 결합하는 연산

#### 외부조인

- 조인하는 여러테이블에서 한 쪽에는 데이터가 있고, 한 쪽에는 데이터가 없는 경우, 데이터가 있는 쪽 테이블의 내용을 모두 출력하는 연산

```mysql
/* 도서를 구매하지 않은 고객을 포함하여 고객의 이름과 고객이 주문한 도서의 판매가격을 구하는 연산 */
SELECT Customer.name, saleprice
FROM Customer LEFT OUTER JOIN Orders ON Customer.custid=Orders.custid;
```

### 부속질의

- 하나의 SQL 문 안에 또다른 SQL 문이 삽입되는 것
- 질의가 중첩되어 있다는 의미에서 중첩질의라고도 한다.

```mysql
/* 가장 높은 가격의 책의 이름을 구하는 연산 */
SELECT bookname
FROM Book
WHERE price = (SELECT MAX(price) FROM Book);
```

### 집합연산

- 테이블은 투플의 집합이므로 테이블 간 집합 연산을 수행할 수 있다.
- MySQL에서 합집합 연산은 UNION 을 사용하여 수행할 수 있다.

```mysql
/* 대한민국에서 거주하는 고객의 이름과 도서를 주문한 고객의 이름을 구하는 연산 */
SELECT name
FROM Customer
WHERE address LIKE '대한민국%'
UNION
SELECT name
FROM Customer
WHERE custid IN (SELECT custid FROM Orders);
```

- UNION 대신 UNION ALL을 사용하면 중복을 포함한 결과를 반환한다.

### EXISTS

- 조건에 맞는 투플을 결과에 포함시킨다.
- 부속질의문 형식이다.

```mysql
/* 주문이 있는 고객의 이름과 주소를 구하는 연산 */
SELECT name, address
FROM Customer cs
WHERE EXISTS(SELECT *
            FROM Orders od
            WHERE cs.custid=od.custid);
```

## 3. 데이터 정의어

### CREATE 문

- 테이블의 구조를 만드는 명령어
- 테이블을 구성하고, 속성과 속성에 관한 제약을 정의하며, 기본키 및 외래키를 정의하는 명령

```mysql
CREATE TABLE 테이블이름
( {속성이름 데이터타입
    [NULL | NOT NULL | UNIQUE | DEFAULT 기본값 | CHECK 체크조건]
  }
    [PRIMARY KEY 속성이름(들)]
    [FOREIGN KEY 속성이름 REFERENCES 테이블이름(속성이름)]
      [ON DELETE {CASCADE | SET NULL}]
)
```

### ALTER 문

- 테이블의 구조를 변경하는 명령어
- 생성된 테이블의 속성과 속성에 관한 제약을 변경하며, 기본키 및 외래키를 변경한다.

```mysql
ALTER TABLE 테이블이름
	[ADD 속성이름 데이터타입]
	[DROP COLUMN 속성이름]
	[ALTER COLUMN 속성이름 데이터타입]
	[ALTER COLUMN 속성이름 [NULL | NOT NULL]
	[ADD PRIMARY KEY(속성이름)]
	[[ADD | DROP] 제약이름];
```

### DROP 문

- 테이블을 삭제하는 명령어

```mysql
DROP TABLE 테이블명;
```

## 5. 데이터 조작어 - 삽입, 수정, 삭제

### INSERT 문

- 테이블에 새로운 투플을 삽입하는 명령어

```mysql
INSERT INTO 테이블이름[(속성리스트)]
        VALUES (값리스트);
```

### UPDATE 문

- 특정 속성 값을 수정하는 명령어

```mysql
UPDATE 테이블이름
SET 속성이름1 = 값1 [, 속성이름1 = 값2, ...]
[WHERE <검색조건>];
```

### DELETE 문

- 기존 투플을 삭제하는 명령어

```mysql
DELETE FROM 테이블이름
[WHERE 검색조건];
```

# 문제 풀이

## 문제 1

```sql
SELECT FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE '%강원도%'
ORDER BY FACTORY_ID ASC;
```

## 문제 2

```sql
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1;
```

## 문제 3

```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION='Sick';
```

## 문제 4

```sql
SELECT COUNT(DISTINCT NAME) AS COUNT
FROM ANIMAL_INS;
```

## 문제 5

```sql
SELECT PRICE AS MAX_PRICE
FROM PRODUCT
WHERE PRICE=(SELECT MAX(PRICE) FROM PRODUCT)
```
