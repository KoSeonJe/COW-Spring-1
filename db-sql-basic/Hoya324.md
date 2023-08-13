> 데이터베이스(mySQL 기준)을 정리한 글입니다.

## 관계 데이터 모델의 개념

### 1. 릴레이션

**릴레이션(relation)-테이블이라고 주로 많이 부름**

- 행과 열로 구성된 테이블
- 릴레이션(relation)과 관계(relationship)을 구분 지을 필요가 있음

******관계(relationship)******

- 릴레이션 내 데이터들의 관계와 릴레이션 간의 관계로 구분할 수 있다.
- 릴레이션 간의 관계는 각 릴레이션 간의 **식별 가능한 값을** 이용하여 연결한다.


### 2. 릴레이션 스키마와 인스턴스

- 릴레이션은 **스키마**와 **인스턴스**로 이루어진다.
- **스키마(schema)**: 관계 데이터베이스의 릴레이션의 구성, 정보에 대한 기본적인 구조를 정의
    - **헤더**: 테이블의 첫 행
    - 스키마는 헤더에 나타나며, 각 데이터의 특징을 나타내는 속성, 자료 타입 등의 정보 내포
- **인스턴스(instance)**: 정의된 스키마에 따라 테이블에 실제로 저장되는 데이터의 집합

**릴레이션 스키마**

- 릴레이션에 어떤 정보가 담길지 정의
- 용어정리
    - **속성(attribute)**: 릴레이션 스키마의 열 / **필드(Fields)** 라고도 한다.
    - **도메인(domain)**: 속성이 가질 수 있는 값의 집합
    - **차수(degree)**: 속성의 개수
- 표기법: ‘릴레이션 이름(속성1, 속성2, 속성3, …)’ 또는 ‘릴레이션 이름(속성1: 도메인1, 속성2: 도메인2, 속성3: 도메인3, …)’

**엔티티(Entity)**
- 정의: 여러 개의 속성을 지닌 명사를 엔티티라고 부른다.
- 약한 엔티티와 강한 엔티티
  - 약한 엔티티: 혼자서는 존재하지 못하고 다른 엔티티의 존재 여부에 따라 종속적인 엔티티
  - 강한 엔티티: 스스로 존재할 수 있는 엔티티

**릴레이션 인스턴스**

- 릴레이션 스키마에 실제로 저장된 데이터의 집합
- 용어정리
    - **투플**: 릴레이션의 행 / **레코드(Records)** 라고도 한다.
    - **카디날리티**: 투플의 수

> 💡엔티티와 레코드의 차이점 (Entity vs Records)
> 
> **레코드**는 실제 데이터베이스 상에 저장되어 있는 값들의 모임을 말한다.
> 반면에, **엔티티**는 현실 세계에 존재하는 객체를 표현하기 위해 비유(추상)적으로 사용됩니다.

> 속성 Attribute
> 
> 엔티티를 설명하는 속성을 Attribute라고 합니다. 이러한 속성들은 각각의 엔티티마다 다를 수 있고, 이를 통해 엔티티를 구별할 수 있습니다. 


### 3. 릴레이션의 특징

1. 속성의 단일 값을 가진다.
2. 속성은 서로 다른 이름을 가진다.
3. 한 속성의 값은 모두 같은 도메인 값을 가진다.
4. 속성의 순서는 상관없다.
5. 릴레이션 내의 중복된 투플은 허용하지 않는다.
6. 투플의 순서는 상관없다.

### 관계 데이터 모델

- 데이터를 2차원 테이블 형태인 릴레이션으로 표현
- **제약조건(constraints)** 와 관계 연산을 위한 **관계대수(relational algebra)** 를 정의
- **관계 데이터베이스 시스템**: 관계 데이터 모델에 기초하여 SQL을 기반으로 구현(아래의 내용을 SQL로 구현한 것)
    - 릴레이션의 생성, 관리
    - 제약조건
    - 관계대수

## 무결성 제약조건

- 데이터를 저장하는 데 있어서 DB는 일관성을 유지하고 중복을 제거하는 등 데이터의 신뢰도를 유지해야 한다.

### 1. 키

- 식별자의 역할
    - `릴레이션 내의 중복된 투플은 허용하지 않는다.` 라는 조건이 있기에 투플들을 서로 구별할 수 있어야한다.
- 관계를 맺는 역할도 수행한다.

**슈퍼키(super key):**

- 투플을 유일하게 식별할 수 있는 하나의 속성 혹은 속성의 집합

**후보키(candidate key)**

- 투플을 유일하게 식별할 수 있는 속성의 최소 집합
- 하나의 속성으로 식별이 불가능할 때, 두 개 이상의 속성으로 이루어진 키를 복합키(composite key)라고 한다.

**기본키(PK, primary key)**

- 여러 후보키 중 하나를 선정하여 대표로 삼는 키
- **기본키 제약조건**
    - 릴레이션 내 투플은 식별할 수 있는 고유한 값을 가져야 한다.
    - NULL 값은 허용하지 않는다.
    - 키 값의 변동이 일어나지 않아야 한다.
    - 최대한 적은 수의 속성을 가진 것이어야 한다.
    - 향후 키를 사용하는 데 있어서 문제 발생 소지가 없어야 한다.
- 릴레이션 스키마를 표현할 때 기본키는 밑줄을 그어 표시한다.

**대리키(surrogate key),** **인조키(artificial key)**

- 기본키가 보안을 요하거나, 여러 개의 속성으로 구성되어 복잡하거나, 마땅한 기본키가 없을 때는 일련번호 같은 가상의 속성을 만들어 기본키로 삼는다. 이것이 대리키 또는 인조키(artificial key)라고 한다.
- DBMS나 관련 소프트웨어에서 임의로 생성하는 값

**대체키(alternate key)**

- 기본키로 선정되지 않은 후보키

**외래키(FK, foreign key)**

- 다른 릴레이션의 기본키를 참조하는 속성
- 관계 데이터 모델의 특징인 릴레이션 간의 관계를 표현
- 성립 조건
    - 참조하고 참조되는 양쪽 릴레이션의 도메인이 서로 같아야한다.
    - 참조되는 릴레이션의 기본키의 값이 변경되면 이 기본키를 참조하는 외래키 값도 변경(연동되어야 한다.)
    - NULL 값과 중복 값 등이 허용된다.
    - 자기 자신의 기본키를 참조하는 외래키도 가능하다.
    - 외래키가 기본키의 일부가 될 수 있다.

### 2. 무결성 제약조건

- **데이터 무결성(integrity)**: 데이터베이스에 저장된 데이터의 **일관성**과 **정확성**을 지키는 것
- 투플의 삽입, 삭제, 수정 시 데이터의 제약조건 준수 여부를 확인하여야 한다.

### **[도메인] 제약조건**

**도메인 무결성 제약조건(domain integrity constraint)**

- 릴레이션 내의 투플들이 각 속성의 도메인에 지정된 값만을 가져야 한다.
- 도메인 제약이라고도 한다.
- 속성 값과 관련된 무결성으로, SQL 문에서 데이터 형식(type), 널(null, not null), 기본 값(default), 체크(check) 등을 사용하여 지정할 수 있다.

### [키] 제약조건

**개체 무결성 제약조건(entity integrity constraint)**

- 릴레이션은 기본키를 지정하고 그에 따른 무결성 원칙 즉, 기본키는 NULL 값을 가져서는 안 되며 릴레이션 내에 어오직 하나의 값만 존재해야 한다는 조건이다.
- **기본키 제약(primary key constraint)이라고도 한다.**

**참조 무결성 제약조건(referential integrity constraint)**

- 부모 릴레이션: 참조되는(제공하는) 릴레이션
- 자식 릴레이션: 참조하는(제공받는) 릴레이션
- 자식 릴레이션의 외래키는 부모 릴레이션의 기본키와 속성의 도메인이 동일해야 하며, 자식 릴레이션의 값 변경 시 부모 릴레이션의 값에 제약을 받는다는 조건이다.
- 즉, 부모 릴레이션의 도메인과 다른 값으로 삽입, 수정될 경우 거부되고, 반대로 자식 릴레이션에서 참조하고 있는 값을 부모 릴레이션에서 삭제하거나 다른 값으로 변경하려고 하면 거부된다.
- 외래키 제약이라고도 한다.

**유일성 제약조건, 고유성 제약조건(**UNIQUE 제약조건**)**

- 속성의 모든 값들에 서로 같은 값이 없어야 한다는 것.
- 기본키와 달리 NULL값을 허용한다.

### 3. 무결성 제약조건의 수행

- 제약조건의 준수여부는 데이터의 변경(삽입, 수정, 삭제)이 있을 때마다 확인해야 한다.

**개체 무결성 제약조건**

- DBMS는 투플을 삽입하거나 수정할 때마다 개체 무결성 제약 조건을 지키는지 확인한다.
- 삽입
    - 같은 PK를 가지거나, PK가 NULL인 경우 삽입 거부
- 수정
    - 삽입과 마찬가지로 DBMS가 동일 값이 존재하는지 검색 후 처리한다.
- 삭제
    - 외래키로 참조하고 있지 않으면 바로 삭제한다.

**참조 무결성 제약조건**

- 단일 릴레이션에 대한 내용이 아니다. 두 릴레이션 간의 참조 관계를 처리한다.
- 삽입
    - 자식 릴레이션에 삽입하려는 외래키가 부모 릴레이션에 없다면 삽입은 거부
    - 단, 자식 릴레이션의 외래기 속성에 NULL값을 허용한다면 삽입가능하다.
- 삭제
    - 자식 릴레이션에서 투플이 삭제되는 경우 부모 릴레이션에는 아무런 영향을 주지 않으므로 바로 삭제 가능
    - 단, 부모 릴레이션에서 삭제 시에는 해당 투플을 참조하는 다른 릴레이션이 없는지 확인해야한다.
    - 만약 문제가 생긴다면,
        - 즉시 작업 중지
        - 자식 릴레이션의 관련 투플을 삭제
        - 초기에 설정된 다른 어떤 값으로 변경
        - NULL 값으로 설정

**투플을 삭제할 때, 참조 무결성 제약조건을 수행하기 위한 4가지 옵션**

| 명령어 | 의미 |
| --- | --- |
| RESTRICTED | 자식 릴레이션에서 참조하고 있을 경우 부모 릴레이션의 삭제 작업을 거부함 |
| CASCADE | 자식 릴레이션의 관련 투플을 같이 삭제 |
| DEFAULT | 자식 릴레이션의 관련 투플을 미리 설정해둔 값으로 변경 |
| NULL | 자식 릴레이션의 관련 투플을 NULL 값으로 설정함(NULL 값을 허가한 경우) |
- 수정
    - 삭제와 삽입 명령이 연속해서 수행된다고 보면 된다.
    - 부모 릴레이션의 수정이 일어날 경우 삭제 옵션에 따라 처리된 후 문제가 없으면 다시 삽입 제약조건에 따라 처리된다.

## 관계 대수

- 릴레이션에서 데이터를 추출하는 데 사용하는 언어인 관계대수에 대해 배운다.

### 1. 관계 대수

- 릴레이션에서 원하는 결과를 얻기 위해 수학의 대수와 같은 연산을 이용하여 질의하는 방법을 기술하는 언어이다.

**************관계의 수학적 의미**************

- 관계 데이터베이스는 수학적 의미의 릴레이션에 기초한다.
- 집합의 개념을 적용한 것으로, 합집합(**∪**), 교집합(**∩**), 카티전 프로덕트(X) 등이 있다.

**관계대수 연산자**

- 순수 관계 연산: 관계형 데이터 모델을 위해 고안
    - 셀렉션(selection), 프로젝션(projection), 조인(join), 디비전(division), 개명(rename)
- 일반 집합 연산: 수학의 집합이론에서 차용
    - 합집합(union), 차집합(difference), 교집합(intersection), 카티전 프로덕트(cartesian product)
    

![](https://file.notion.so/f/s/eeef22e2-f903-449d-a76e-48d0d90570f9/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-07-04_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_6.36.43.png?id=0fd2b22c-a518-4f3e-bee8-3e6961c422b9&table=block&spaceId=fee67429-4153-4f2a-9f5f-751b1a73dc03&expirationTimestamp=1688558400000&signature=2Yp-nqlJNYMtsv9O83k_9aOoqgyNVRcTySl_QKK2JFY&downloadName=%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA+2023-07-04+%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE+6.36.43.png)

**관계대수식**

- 단항 연산자: 연산자<조건> 릴레이션
- 이항 연산자: 릴레이션1 연산자<조건> 릴레이션2

### 2. 셀렉션과 프로젝션

- 셀렉션과 프로젝션은 관계 대수에서 가장 기본적인 연산으로 하나의 릴레에션을 대상으로 하는 단항 연산자이다.

**셀렉션(selection)**

- 릴레이션의 투플을 추출하기 위한 연산
- 하나의 릴레이션을 대상으로 하는 단항 연산자
- 찾고자 하는 투플의 조건을 명시하고 그 조건에 만족하는 투플을 반환한다.
- 조건: `<속성> = <상수값 또는 속성>` 형태가 올 수 있다.

**프로젝션(projection)**

- 릴레이션의 속성을 추출하기 위한 연산으로 단항 연산자이다.
- 결과 릴레이션의 차수는 대상 릴레이션의 차수보다 작거나 같고 카디날리티는 동일하다.

### 3. 집합 연산

- 집합연산자 중 합집합, 교집합, 차집합은 두 릴레이션의 차수 및 도메인과 속성의 순서가 동일해야 한다.
- 각 릴레이션의 속성 수가 같고 순서도 같아야 하며, 각각 동일한 도메인으로 대응되어야 한다. → 합병 가능
    - 속성 이름은 동일하지 않아도 되먀, 반환되는 릴레이션의 속성 이름은 첫 번째 릴레이션의 이름과 같다.

**합집합**

- 두 개의 릴레이션을 합하여 하나의 릴레이션을 반환한다.
- 두 개의 릴레이션은 서로 같은 속성 순서와 도메인을 가져야 한다.

**교집합**

- 두 릴레이션이 공통으로 가지고 있는 투플 반환

**차집합**

- 첫 번째 릴레이션에는 속하고 두 번째 릴레이션에는 속하지 않는 투플을 반환한다.

**카티전 프로덕트**

- 합집합, 교집합, 차집합이 수직적 연산이라면, 카티전 프로덕트는 수평적 연산
- 두 릴레이션을 연결시켜 하나로 합칠 때 사용
- 속성과 도메인이 동일할 필요는 없다.
- 결과 릴레이션의 차수는 두 릴레이션의 차수의 합이며, 카디날리티는 두 릴레이션의 카디날리티의 곱이다.
- 결과 릴레이션의 속성 이름은 두 릴레이션에서 그대로 가져와 사용
    - 만약 두 릴레이션의 속성 이름이 같다면, ‘<릴레이션 이름>.<속성 이름>’의 형태로 표현하거나 순서(위치)를 사용하여 표현한다.
- 카티전 프로덕트는 두 릴레이션을 무조건 수평으로 합친 결과를 반환하기 때문에 결과에 의미가 없다.
    - 즉, 유용한 자료로 활용하기 힘들다.
    - 이를 해결한 것이 **조인**이다.

### 4. 조인

- 두 릴레이션의 공통 속성을 기준으로 속성 값이 같은 투플을 수평으로 결합하는 연산
    - 즉, 두 릴레이션을 카티전 프로적트 연산을 한 후 셀렉션 연산을 한 것
- 조인을 수행하기 위해서는 두 릴레이션의 조인에 참여하는 속성이 서로 동일한 도메인으로 구성되어야 한다.

**세타조인(theta join)**

- 조인에 참여하는 두 릴레이션의 속성 값을 비교하여 조건을 만족하는 투플만 반환한다.

****************동등조인(equi join)****************

- 내부조인(inner join)이라고도 한다.
- 세타조인에서 `=` 연산자를 사용한 조인
- 보통 조인 연산은 동등조인을 지칭한다.

**자연조인(natural join)**

- 동등조인에서 조인에 참여한 속성이 두 번 나오지 않도록 두 번째 속성을 제거한 결과를 반환

**외부조인(outer join)**

- 외부조인은 자연조인의 확장된 형태
- 외부조인은 자연조인 시 조인에 실패한 투플을 모두 보여주되 값이 없는 대응 속성에는 NULL 값을 채워서 반환한다.
    - 왼쪽(left) 외부조인, 오른쪽(right) 외부조인, 완전(full) 외부조인이 있으며, 각 방향을 기준으로 자연조인에 실패한 투플을 모두 보여준다.(빈 값은 NULL)

**세미조인(semi join)**

- 자연조인을 한 후 두 릴레이션 중 한쪽 릴레이션의 결과만 반환한다.
- 왼쪽, 오른쪽이 있다.

### 5. 디비전

- 릴레리션의 속성 값의 집합으로 연산을 수행
- 속성 A와 B로 이루어진 릴레이션 R과 속성 B로 이루어진 릴레이션 S의 디비전 연산은 , 릴레이션 S의 속성 B값과 서로 동일하게 대응하는 릴레이션 R의 속성 A의 투플들을 반환한다.


## SQL 개요

### SQL 특징

- SQL 문은 세미클론(;)으로 끝난다.(생략 가능하지만 좋은 습관은 아니다.)
- SQL 예약어는 대분자로, 테이블이나 속성 이름은 소문자로 적어주는 것이 좋다.
- 문자열 비교 시 인용부호 `''`(작은 따옴표)

**SQL 구분**

- 데이터 정의어(DDL, Data Definition Language)
    - 테이블이나 관계의 구조를 생성하는 데 사용
    - CREATE, ALTER, DROP
- 데이터 조작어(DML, Data Manipulation Language)
    - 테이블에 데이터를 검색, 삽입, 수정, 삭제하는 데 사용
    - SELECT, INSERT, DELETE, UPDATE
- 데이터 제어어(DCL, Data Control Language)
    - 데이터의 사용 권한을 관리하는 데 사용
    - GRANT, REVOKE, COMMIT, ROLLBACK

## 데이터 정의어(DDL)

### CREATE 문

- create 문은 테이블을 구성하고, 속성과 속성에 관한 제약을 정의하며, 기본키 및 외래키를 정의하는 명령
  ```sql
  CREATE TABLE 테이블이름
  ( {속성이름 데이터타입
      [NULL | NOT NULL | UNIQUE | DEFAULT 기본값 | CHECK 체크조건]
    }
      [PRIMARY KEY 속성이름(들)]
      [FOREIGN KEY 속성이름 REFERENCES 테이블이름(속성이름)]
        [ON DELETE {CASCADE | SET NULL}]
  )
  ```

- 대문자는 키워드, {} 안의 내용은 반복 가능, []은 선택적 사용 가능, | 는 1개 선택, <>은 해당되는 문법 사항이 있음을 나타낸다.
- NOT NULL: NULL 값을 허용하지 않는다.
- UNIQUE: 유일한 값에 대한 제약
- CHECK: 값에 대한 조건을 부여할 때
- PRIMARY KEY, FOREIGN KEY: 각각 기본키, 외래키 지정
- ON DELETE: 투플의 삭제 시 외래키 속성에 대한 동작
    - 옵션: CASCADE, SET, NULL, RESTRICT(default: NO ACTION)
        
        
        | 명령어 | 의미 |
        | --- | --- |
        | RESTRICTED | 자식 릴레이션에서 참조하고 있을 경우 부모 릴레이션의 삭제 작업을 거부함 |
        | CASCADE | 자식 릴레이션의 관련 투플을 같이 삭제 |
        | DEFAULT | 자식 릴레이션의 관련 투플을 미리 설정해둔 값으로 변경 |
        | NULL | 자식 릴레이션의 관련 투플을 NULL 값으로 설정함(NULL 값을 허가한 경우) |
- 예) NewBook 테이블 생성, 정수형은 INTEGER, 문자형은 VARCHAR
    
    ```sql
    CREATE TABLE NewBook (
    	bookid INTEGER,
    	bookname VARCHAR(20),
    	publisher VARCHAR(20),
    	price INTEGER);
    ```
    

**✅ 문자형 데이터 타입 - CHAR, VARCHAR**

- CHAR(n)은 n바이트를 가진 문자형타입
    - 저장되는 문자의 길이가 n보다 작으면 나머지는 공백으로 채워서 저장
- VARCHAR(n) 타입은 마찬가지로 n바이트를 가진 문자형 타입이지만 저장되는 문자의 길이만큼만 기억장소를 차지
- 주의점
    - CHAR(n)에 저장된 값과 VARCHAR(n)에 저장된 값이 비록 같은지라도 CHAR(n)은 공백을 채운 문자열이기 때문에 동등 비교 시 실패할 수 있음.

**PK(기본키) 생성 방법**

```sql
CREATE TABLE NewBook (
	bookid INTEGER,
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER,
	PRIMARY KEY (bookid));
-------------------------------------------
CREATE TABLE NewBook (
	bookid INTEGER PRIMARY KEY,
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER);
-------------------------------------------
/* bookid 속성이 없어서 bookname과 publisher가 기본키가 된다면 */
CREATE TABLE NewBook (
	bookname VARCHAR(20),
	publisher VARCHAR(20),
	price INTEGER,
	PRIMARY KEY (bookname, publisher));
```

**복잡한 제약사항 추가**

- 예) bookname은 NULL 값을 가질 수 없고, publisher는 같은 값이 있으면 안 된다. price에 값이 입력되지 않을 경우 기본값 10000을 저장한다. 또 가격은 최소 1,000원 이상으로 한다.
    
    ```sql
    CREATE TABLE NewBook (
    	bookname VARCHAR(20) NOT NULL,
    	publisher VARCHAR(20) UNIQUE,
    	price INTEGER DEFAULT 10000 CHECK(price >= 1000),
    	PRIMARY KEY (bookname, publisher));
    ```
    

**FK(외래키) 생성 방법**

- 주의할 점
    - 반드시 참조되는 테이블(부모 릴레이션)이 존재해야 하며 참조되는 테이블의 기본키여야 한다.

```sql
CREATE TABLE NewOrders (
	orderid INTEGER,
	custid INTEGER,
	bookid INTEGER NOT NULL,
	saleprice INTEGER,
	orderdate DATE,
	PRIMARY KEY(orderid),
	FOREIGN KEY(custid) REFERENCES NewCustomer(custid) ON DELETE CASCADE);
```

**데이터 타입 종류**

| 데이터 타입 | 설명 | ANSI SQL 표준 타입 |
| --- | --- | --- |
| INTEGER<br> INT | 4바이트 정수형을 저장한다. | INTEGER, INT <br>SMALLINT |
| NUMERIC(m, d)<br> DECIMAL(m, d) | 전체 자릿수 m, 소수점이하 자릿수 d를 가진 숫자형을 저장한다. | DECIMAL(p, s) <br>NUMERIC[(p, s)] |
| CHAR(n) | 문자형 고정길이, 문자를 저장하고 남은 공간은 공백 로 채운다. | CHARACTER(n) <br>CHAR(n) |
| VARCHAR(n) | 문자형 가변길이를 저장한다. | CHARACTER VARYING(n) |
| DATE | 날짜형, 연도, 월, 날, 시간을 저장한다. |  |
### ALTER 문

- 생성된 테이블의 속성과 속성에 관한 제약을 변경
- 기본키 및 외래키를 변경

```sql
ALTER TABLE 테이블이름
	[ADD 속성이름 데이터타입]
	[DROP COLUMN 속성이름]
	[ALTER COLUMN 속성이름 데이터타입]
	[ALTER COLUMN 속성이름 [NULL | NOT NULL]
	[ADD PRIMARY KEY(속성이름)]
	[[ADD | DROP] 제약이름];
```

- ALTER 문에서 ADD, DROP은 속성을 추가하거나 제거할 때 사용하고, MODIFY는 속성을 변경할 때 사용한다.
- 기본키로 변경하는 경우 NOT NULL 속성만 가능하다.
    
    ```sql
    ALTER TABLE NewBook ADD PRIMARY KEY(bookid);
    ```
    

### DROP 문

- 테이블을 삭제하는 명령어
- 삭제하려는 테이블의 기본키를 다른 테이블에서 참조 중이라면 참조하고 있는 테이블부터 삭제해야한다.

```sql
DROP TABLE 테이블이름;
```


## 데이터 조작어(DML)-검색

### SELECT 문법

```sql
SELECT 
	[ALL | DISTINCT] 속성이름(들)
	[테이블 이름.]{* | 속성이름 [[AS] 속성이름별칭]}
[FROM 
	{테이블이름 [AS 테이블이름별칭]
	[INNER JOIN | LEFT [OUTER] JOIN | RIGHT [OUTER] JOIN
	{테이블이름[ON 검색 조건]}
	| FULL [OUTER] JOIN {테이블이름}]]
[WHERE 검색조건(들)]
[GROUP BY {속성이름, [..., n]}]
[HAVING 검색조건(들)]
[질의 UNION 질의 | 질의 UNION ALL 질의]
[ORDER BY {속성이름 [ASC | DESC], [..., n]}]
-----------------------------------------------------------------------------
[]: 대괄호 안의 SQL 예약어들은 선택적으로 사용한다.
{}: 중괄호 안의 SQL 예약어들은 필수적으로 사용한다.
| : 선택 가능한 문법들 중 한 개를 사용할 수 있다.
```

### **SELECT/FROM**

- FROM 뒤에는 검색하려는 테이블이 나온다.
- `*`(asterisk)를 사용하면 모든 열을 나타낼 수 있다.
    
    ```sql
    SELECT *
    FROM Book;
    ```
    
- 중복을 제거하고 싶으면 DISTINCT 키워드를 사용한다.
    
    ```sql
    SELECT DISTINCT publiser
    FROM Book;
    ```
    

### **WHERE 조건**

- WHERE 절에 조건으로 사용할 수 있는 술어
    
    
    | 술어 | 연산자 | 사용 예 |
    | --- | --- | --- |
    | 비교 | =, <>, <, <=, >, >= | price < 20000 |
    | 범위 | BETWEEN | price BETWEEN 10000 AND 20000 |
    | 집합 | IN, NOT IN | price IN (10000, 20000, 30000) |
    | 패턴 | LIKE | bookname LIKE '축구의 역사’ |
    | NULL | IS NULL, IS NOT NULL | price IS NULL |
    | 복합조건 | AND, OR, NOT | (price < 20000) AND (bookname LIKE '축구의 역사') |
- 와일드 문자의 종류
    
    
    | 와일드 문자 | 의미 | 사용 예 |
    | --- | --- | --- |
    | + | 문자열을 연결 | ‘골프’ + ‘바이블’ = ‘골프 바이블’ |
    | % | 0개 이상의 문자열과 일치 | ‘%축구%’: 축구를 포함하는 문자열 |
    | [] | 1개의 문자열과 일치 | ‘[TO-5]%’: 0-5 사이 숫자로 시작하는 문자열 |
    | [^] | 1개의 문자열과 불일치 | ‘[^0-5]%’: 0-5 사이 숫자로 시작하지 않는 문자열 |
    | _ | 특정 위치의 1개의 문자와 일치 | ‘_구%’: 두 번째 위치에 '구’가 들어가는 문자열 |

### **ORDER BY**

- SQL 문의 실행 결과를 특정 순서대로 출력
- 예시
    
    ```sql
    SELECT *
    FROM Book
    ORDER BY price, bookname;
    ```
    
- 정렬의 기본은 오름차순이므로 내림차순으로 정렬하려면 열 이름 다음에 DESC 키워드 사용
    
    ```sql
    SELECT *
    FROM Book
    ORDER BY price DESC, publisher ASC;
    ```
    

### GROUP BY와 집계 함수

- 집계를 하기 위한 문법 GROUP BY
- 구체적인 집계 내용은 집계 함수 사용

**집계 함수**

- 집계함수는 여러 개 혼합하여 쓸 수 있다.
- WHERE 문과 같이 사용하면 더 유용하다.
- 집계 함수 종류
    
    
    | 집계 함수 | 문법 | 사용 예 |
    | --- | --- | --- |
    | SUM | SUM (ALL I DISTINCT] 속성이름) | SUM(price) |
    | AVG | AVG([ALL | DISTINCT] 속성이름) | AVG(price) |
    | COUNT | COUNT({[[ALL | DISTINCT] 속성이름] | * }) | COUNT(*) |
    | MAX | MAX(ALL | DISTINCT] 속성이름) | MAX(price) |
    | MIN | MIN(ALL | DISTINCT] 속성이름) | MIN(price) |

**GROUP BY**

- SQL 문에서 GROUP BY 절을 사용하면 속성 값이 같은 값끼리 그룹을 만들 수 있다.
- 예) 고객별로 주문한 도서의 총 수량과 총 판매액을 구하시오
    
    ```sql
    SELECT custid, COUNT(*) AS 도서수량, SUM(saleprice) AS 총액
    FROM Orders
    GROUP BY custid;
    ```
    
- HAVING 절은 GROUP BY 절의 결과 나타나는 그룹을 제한하는 역할을 한다.
- 예) 가격이 8000원 이상인 도서를 구매한 고객에 대하여 고객별 주문 도서의 총 수량을 구하시오. 단, 두 권 이상 구매한 고객만 구하시오.
    
    ```sql
    SELECT custid, COUNT(*) AS 도서수량
    FROM Orders
    WHERE saleprice >= 8000
    GROUP BY custid
    HAVING count(*) >= 2;
    ```
    

- GROUP BY와 HAVING 절의 문법과 주의사항
    
    
    | 문법 | 주의 사항 |
    | --- | --- |
    | GROUP BY<속성> | GROUP BY로 투플을 그룹으로 묶은 후 SELECT 절에는 GROUP BY에서 사용한 <속성>과 집계 함수만 나올 수 있다. |
    | HAVING<검색조건> | WHERE 절과 HAVING 절이 같이 포함된 SQL 문은 검색조건이 모호해질 수 있다. HAVING 절은 
    1. 반드시 GROUP BY 절과 같이 작성해야 하고,
    2. WHERE 절보다 뒤에 나와야 한다.
    3. <검색조건>에는 SUM, AVG, MAX, MIN, COUNT와 같은 집계함수가 와야 한다. |
 
    
### SELECT문 실행 순서 
```sql
SELECT custid, COUNT(*) AS 도서수량    (5)
FROM Orders                          (1)
WHERE saleprice > 8000               (2)
GROUP BY custid                      (3)
HAVING count(*) > 1                  (4)
ORDER BY custid;                     (6)
```

### 두 개 이상 테이블에서 SQL 질의

**조인(Join)**

- 조인은 한 테이블의 행을 다른 테이블의 행에 연결하여 두 개 이상의 테이블을 결합하는 연산
- 동등조인 예시
- 예) 고객의 이름과 고객이 주문한 도서의 판매가격을 검색하시오.
  
  ```sql
  SELECT name, saleprice
  FROM Customer, Orders
  WHERE Customer.custid=Orders.custid;
  ```
  
- 예) 고객별로 주문한 모든 도서의 총 판매액을 구하고, 고객별로 정렬하시오
  
  ```sql
  SELECT name, SUM(saleprice)
  FROM Customer, Orders
  WHERE Customer.custid=Orders.custid
  GROUP BY Customer.name
  ORDER BY Customer.name**;**
  ```
  
- 예) 고객의 이름과 고객이 주문한 도서의 이름을 구하시오
  
  ```sql
  SELECT Customer.name, Orders.bookname
  FROM Customer, Orders, Book
  WHERE Customer.custid=Orders.custid AND Orders.bookid=Book.booid
  ```
  
- 외부조인 예시
- 예) 도서를 구매하지 않은 고객을 포함하여 고객의 이름과 고객이 주문한 도서의 판매가격을 구하시오
  
  ```sql
  SELECT Customer.name, saleprice
  FROM Customer LEFT OUTER JOIN Orders
    ON Customer.custid=Orders.custid;
  ```
  

- 조인 문법
  - 일반적인 조인
  - SQL 문에서는 주로 동등조인을 사용한다. 두 가지 문법 중 하나를 사용할 수 있다.
      
      ```sql
      SELECT <속성들>
      FROM 테이블1, 테이블2
      WHERE <조인조건> AND <검색조건>
      ```
      
      ```sql
      SELECT <속성들>
      FROM 테이블1 INNER JOIN 테이블2 ON <조인조건>
      WHERE <검색 조건>
      ```
      
  - 외부조인
  - 외부조인은 FROM 절에 조인 종류를 적고 ON을 이용하여 조인조건을 명시한다.
      
      ```sql
      SELECT <속성들>
      FROM 테이블1 {LEFT | RIGHT | FULL [OUTER]}
        JOIN 테이블2 ON <조인조건>
      WHERE <검색조건>
      ```
        

**부속질의**

- SELECT 문의 WHERE 절에 또 다른 테이블 결과를 이용하기 위해 다시 SELECT 문을 괄호로 묶는 것
- 결과는 단일행-단일열(1 x 1), 다중행-단일열(n x 1), 단일행-다중열(1 x n), 다중행-다중열(n x n)
- 예) 가장 비싼 도서의 이름을 보이시오.
    
    ```sql
    SELECT bookname
    FROM Book
    WHERE price = (SELECT MAX(price) FROM Book);
    ```
    
- 부속질의 간에는 상하 관계가 있으며, 실행 순서는 하위 부속질의를 먼저 실행하고 그 결과를 이용해 상위 부속질의를 실행한다.
- 반면, 상관 부속질의는 상위 부속질의의 투플을 이용하여 하위 부속질의를 계산한다.
- 즉, 상위 부속질의와 하위 부속질의는 의존적이다.
- 예) 대한 미디어에서 출판한 도서를 구매한 고객의 이름을 보이시오.
    
    ```sql
    SELECT name
    FROM Customer
    WHERE custid IN(SELECT custid
            FROM Orders
            WHERE bookid IN(SELECT bookid
                        FROM Book
                        WHERE publisher='대한미디어'));
    ```
    

> 투플변수
테이블 이름이 길 때 테이블의 별칭을 붙여서 사용하는 것, FROM 절의 테이블 이름 뒤에 표기
ex)
…
FROM Book b1
…
> 

조인은 부속질의가 할 수 있는 모든 것을 할 수 있기 때문에, 한 개의 테이블에서만 결과를 얻는 여러 테이블 질의는 조인보다 부속질의가 편하다.

**집합 연산**

- 예) 대한민국에서 거주하는 고객의 이름과 도서를 주문한 고객의 이름을 보이시오.
    
    ```sql
    SELECT name
    FROM Customer
    WHERE address LIKE '대한민국%'
    UNION
    SELECT name
    FROM Customer
    WHERE custid IN (SELECT custid FROM Orders);
    ```
    
    - 이 때, 중복을 포함하고 싶다면 UNION ALL을 대신 사용하면 된다.

**EXISTS**

- 상관 부속질의문 형식
- 원래 단어에서 의미하는 것과 같이 조건에 맞는 투플이 존재하면 결과에 포함시킨다.
- 예) 주문이 있는 고객의 이름과 주소를 보이시오.
  ```sql
  SELECT name, address
  FROM Customer cs
  WHERE EXISTS(SELECT *
              FROM Orders od
              WHERE cs.custid=od.custid);
  ```


## 데이터 조작어(DML)-삽입, 수정, 삭제

### INSERT 문

- 테이블에 새로운 투플을 삽입하는 명령

```sql
INSERT INTO 테이블이름[(속성리스트)]
	VALUES (값리스트);
```

- 속성 리스트는 생략 가능하다. 대신 입력 순서는 속성의 순서와 일치해야한다.
- 속성 리스트를 생략하지 않은 경우에는  순서가 바뀌어도 된다.(속성에 맞는 값끼리만 맞추면 된다.)

```sql
INSERT INTO Book (bookid, bookname, publisher, price)
	VALUES (11, '스포츠 의학', '한솔의학서적', 90000);
---------------------------------------------------------
INSERT INTO Book
	VALUES (11, '스포츠 의학', '한솔의학서적', 90000);
```

### UPDATE 문

- 특정 속성 값을 수정하는 명령

```sql
UPDATE 테이블이름
SET 속성이름1 = 값1 [, 속성이름1 = 값2, ...]
[WHERE <검색조건>];
```

- 예) Book 테이블에서 14번 ‘스포츠 의학’의 출판사를 imported_book 테이블의 21번 책의 출판사와 동일하게 변경하시오
    
    ```sql
    UPDATE Book
    SET publisher = (SELECT publisher
    								 FROM imported_book
    								 WHERE bookid = '21')
    WHERE bookid = '14';
    ```
    
- 주의사항
    - UPDATE 문에서 여러 속성 값을 한꺼번에 수정하는 작업은 가능하나 잘못 사용하면 위험하다.

### DELETE 문

- 기존 투플을 삭제하는 명령

```sql
DELETE FROM 테이블이름
[WHERE 검색조건];
```

- WHERE 절을 빼면 모든 투플 삭제


## 데이터 제어어(DCL)

### GRANT 명령어

- 사용자에게 권한을 부여하기 위한 명령어

```sql
-- 사용자 권한 부여 명령어
GRANT ALL PRIVILEGES ON [dbname.table_name] TO [user@host] IDENTIFIED BY 'my_password';
 
 
-- 예제 (호스트 : 로컬호스트)
GRANT ALL PRIVILEGES ON testDB.testTable TO myuser@localhost IDENTIFIED BY 'testPassword';
 
-- 예제 (호스트 : 원격 접속)
GRANT ALL PRIVILEGES ON testDB.testTable TO myuser@'%' IDENTIFIED BY 'testPassword';
 
-- 예제 (호스트 : 아이피)
GRANT ALL PRIVILEGES ON testDB.testTable TO myuse@192.168.0.100 IDENTIFIED BY 'testPassword';
```

GRANT 명령어 이후 설정한 권한을 적용해야 합니다.

```sql
-- 설정한 권한 적용 명령어
FLUSH PRIVILEGES;
```

### REVOKE 명령어

- REVOKE 명령어는 GRANT 명령어로 적용한 권한을 해제해주는 명령어

```sql
-- 권한 해제 명령어(INSERT, UPDATE, CREATE 권한 해제)
REVOKE insert, update, create ON [dbname.table_name] TO [user@host];
 
-- 권한 해제 명령어(전체 권한 해제)
REVOKE ALL ON [dbname.table_name] TO [user@host];
```

- 해제한 권한이 잘 적용되었는지 확인해보고자 한다면 다음 명령을 사용

  ```sql
  -- 권한 확인 명령어
  SHOW GRANTS FOR [user@host];
  ```

### COMMIT 명령어

- 작업한 결과를 물리적 디스크로 저장하고, 조작 작업이 정상적으로 완료되었음을 관리자에게 알려주는 명령어

- 이 명령어는 INSERT, UPDATE, DELETE 등의 작업 내용에 대해 데이터가 물리 디스크로 완전히 업데이트되며, 모든 사용자가 변경한 데이터의 결과를 볼 수 있게 된다.

```sql
-- 이전 까지의 작업을 완전 저장하는 명령어
COMMIT;
```

### ROLLBACK 명령어

- 작업했던 내용을 원래의 상태로 복구하기 위한 명령
-  INSERT, UPDATE, DELETE 와 같은 트랜잭션의 작업 내용을 취소할 수 있다.

> 주의할 점!💡
>
> COMMIT 명령어를 사용하기 이전의 상태만 ROLLBACK이 가능하다.
> COMMIT을 하게 되면, 물리디스크에 직접 저장하고 알리는 기능이므로, 이미 물리적으로는 이전의 상태가 저장되어 있지 않다는 의미한다.

```sql
-- 이전 까지의 작업을 취소하는 명령어
ROLLBACK;
```

## 관계와 관계 타입

**관계**: 개체 사이의 연관성을 나타내는 개념.

**관계 타입**: 개체 타입과 개체 타입 간의 연결 가능한 관계를 정의한 것이며, 관계 집합은 관계로 연결된 집합을 의미한다. (마름모가 관계 타입을 뜻한다.)

<img width="577" alt="스크린샷 2023-08-02 오후 7 44 48" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/1a80de62-9515-493d-af52-269463476f62">

**관계 타입의 ER 다이어그램 표현**

<img width="547" alt="스크린샷 2023-08-02 오후 7 49 35" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/a48e4172-eeee-46a0-8f1e-3249fdd7f3e4">

**차수에 따른 유형**
- 관계 집합에 참가하는 개체 타입의 수를 관계 타입의 차수라고 한다.

<img width="589" alt="스크린샷 2023-08-02 오후 7 52 27" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/30d5f9aa-de48-4052-939e-dbab8b6cd052">

<img width="524" alt="스크린샷 2023-08-02 오후 7 53 29" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/6235c3e9-43a4-450b-bc87-0e921dac5de3">

**관계 대응 수에 따른 유형**
- **관계 대응수(cardinality)**: 두 개체 타입의 관계에 실제로 참여하는 개별 개체 수

<img width="707" alt="스크린샷 2023-08-02 오후 7 54 07" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/c7a0db44-e168-4b9c-8675-c2181c6e239a">

1. **일대일 관계 (1:1)**
좌측 개체 타입에 포함된 개체가 우측 개체 타입에 포함된 개체와 일대일로 대응하는 관계

<img width="395" alt="스크린샷 2023-08-02 오후 7 55 57" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/e56fa575-e138-4e56-9a60-bf47256fd326">

2. **일대다(1:N), 다대일(N:1) 관계**
실제 일상생활에서 가장 많이 볼 수 있는 관계로, 한쪽 개체 타입의 개체 하나가 다른쪽 개체 타입의 여러 개체와 관계를 맺는다.

<img width="393" alt="스크린샷 2023-08-02 오후 8 14 03" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/9229012a-198f-443a-b0fc-e8c65e7b96fc">

3. **다대다(N:M) 관계**
각 개체 타입의 개체들이 서로 임의의 개수의 개체들과 서로 복합적인 관계를 맺고 있는 관계를 말함.

<img width="561" alt="스크린샷 2023-08-02 오후 8 15 19" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/febec4ed-64ed-492e-a1ce-bfd33abbacd2">

**관계 대응수의 최솟값과 최댓값**
- 관계 대응수 1:1, 1:N, M:N에서 1, N, M은 각 개체가 관계에 참여하는 최댓값을 의미한다.
- 관계에 참여하는 개체의 최솟값을 표시하지 않는다는 단점을 보완하기 위해 다이어그램에서는 대응수 외에 최솟값과 최댓값을 관계실선 위에 (최솟값, 최댓값)으로 표기한다.

<img width="618" alt="스크린샷 2023-08-02 오후 8 25 25" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/2fc7ffa8-ffd4-4550-8b0d-abe9edb7517f">

- max 값을 `*`로 표시하면 임의의 수만큼 참여할 수 있다는 뜻이다.

<img width="606" alt="스크린샷 2023-08-02 오후 8 40 06" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/3227e865-c339-4fdf-9a9a-665888b8ddd4">

**ISA 관계**
- 상위 개체 타입의 특성에 따라 하위 개체 타입이 결정되는 형태
- 상위 개체 타입을 슈퍼 클래스, 하위 개체 타입을 서브 클래스라고 한다.

<img width="502" alt="스크린샷 2023-08-02 오후 8 42 59" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/6d437d05-8ff9-4ccd-81f4-ab810edb026d">

**참여 제약 조건**
- 개체 집합 내 모든 개체가 관계에 참여하는지 유무에 따라 전체 참여와 부분 참여로 구분할 수 있다.
- **전체 참여**: 모든 개체가 관계에 참여
- **부분 참여**: 일부만 참여

<img width="620" alt="스크린샷 2023-08-02 오후 8 46 06" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/26e4ac5e-d669-4a11-ac72-2e4acb709fc2">

**역할**
- 개체 타입 간의 관계를 표현할 때 각 개체들은 고유한 역할을 담당한다.
- 관계에서 역할이 명확하지 않을 경우에 반드시 표기

<img width="564" alt="스크린샷 2023-08-02 오후 8 46 50" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/bbe0202b-a46e-4ac7-8c1b-2e002e621623">

**순환적 관계**
- 하나의 개체 타입이 동일한 개체 타입(자기 자신)과 순환적으로 가지는 형태

<img width="399" alt="스크린샷 2023-08-02 오후 11 12 18" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/126ad781-ac05-4a4a-9596-57a79114b901">

### 약한 개체 타입과 식별자
- **약한 개체 타입**: 상위 개체 타입이 결정되지 않으면 개별 개체를 식별할 수 없는 종속된 개체 타입
- 약한 개체 타입은 독립적인 키로는 존재할 수 없지만 상위 개체 타입의 키와 결합하여 약한 개체 타입의 개별 개체를 고유하게 식별하는 속성을 식별자(discriminator) 혹은 부분키(partial key)라고 한다.

<img width="698" alt="스크린샷 2023-08-02 오후 11 22 51" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/c711d202-ef30-4a7a-869a-97c245c7b0c7">

### IE (Informaion Engineering) 표기법

- ER 다이어그램을 더 축약하여 쉽게 표현하면 Erwin 등 소프트웨어에서 사용한다.
- IE 표기법에서 개체 타입과 속성은 직사각형으로 표현한다.

<img width="514" alt="스크린샷 2023-08-02 오후 11 24 12" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/c35cc2aa-2086-4fca-ad1f-c412cbb35b1f">

- IE 표기법에서 관계는 실선 혹은 점선으로 표기한다.

<img width="675" alt="스크린샷 2023-08-02 오후 11 24 55" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/ea8cd736-4b4a-424a-b82d-acb8364b6310">

<img width="705" alt="스크린샷 2023-08-02 오후 11 28 58" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/d9c45b05-b9f6-45b1-ad40-9ec4205b6f60">

## ER 모델을 관계 데이터 모델로 사상

- ER 모델은 데이터베이스 생명주기의 개념적 모델링에서 사용하는 모델로, ER 다이어그램을 통해 완성된다.
- 완성된 ER 모델은 실제 데이터베이스로 구축하기 위해 논리적 모델링 단계를 거치는데, 이 단게에서 mapping이 이루어진다.

**ER 모델과 관계 데이터 모델의 사상(mapping) 알고리즘**

<img width="354" alt="스크린샷 2023-08-02 오후 11 32 11" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/c4ff589e-05d7-4443-9c29-40632bd6c34f">

### 개체 타입의 사상

**[1단계] 강한(정규) 개체 타입**
- 정규 개체 타입 E의 경우 대응하는 릴레이션 R을 생성한다.
- 각 개체 타입의 일반 속성은 각각 새로 생성하는 릴레이션의 속성으로 표시하고, 기본키와 외래키는 PK나 FK 등으로 표시한다.

**[2단계] 약한 개체 타입**
- 약한 개체 타입에서 생성된 릴레이션은 **자신의 키와 함께 강한 개체 타입의 키를 외래키로 사상하여 자신의 기본키를 구성한다**.
- 이때, 유도된 속성이나 복합 속성 등은 속성의 성격에 맞게 판단하여 mapping한다.

**이진 관계 타입**

<img width="672" alt="스크린샷 2023-08-02 오후 11 37 19" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/d9d7b9e7-ac74-408a-be8d-491a897f12bf">

### 관계 타입의 사상
- 관계 타입은 각 관계 타입이 맺고 있는 차수와 관계 대응 수에 따라 mapping 방식을 구분할 수 있다.

<img width="704" alt="스크린샷 2023-08-02 오후 11 38 35" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/45c2cbd4-6ddc-4684-b864-ce276fadabd8">

<img width="502" alt="스크린샷 2023-08-02 오후 11 41 51" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/b41fc5d9-b9e5-4759-888a-d027653ed92c">

**[3단계] 이진 1:1 관계 타입**
- 이진 1:1 관계 타입의 경우 [방법1] ~ [방법4] 까지 모든 유형으로 사상이 가능하다.
- 개체가 가진 정보 유형에 따라 판단을 하면 된다.
- **[방법1] 또는 [방법2] 중 외래키(FK)에 NULL 값이 덜 발생하는 방법을 사용하면 된다.**

<img width="651" alt="스크린샷 2023-08-02 오후 11 46 04" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/5927ff05-23d9-44c1-971f-858a9f554bde">

**[4단계] 이진 1:N 관계 타입**
- 이진 1:N 관계 타입의 경우 N의 위치에 따라 [방법1] 또는 [방법2]의 유형으로 사상된다.
- 1:N 관계에서는 N에 외래키를 가지면 된다.

<img width="703" alt="스크린샷 2023-08-02 오후 11 48 53" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/f53c2cba-5ad4-4475-8fe2-c83aaa4d0b46">

**[5단계] 이진 M:N 관계 타입**
- 이진 M:N 관계 타입은 [방법4]의 유형으로 사상된다.
- 양쪽 모두 다수의 대응 수를 가지므로 새로운 릴레이션을 반드시 생성해야 한다.
- 새로운 릴레이션은 교차 테이블이라고 한다.

<img width="637" alt="스크린샷 2023-08-02 오후 11 50 38" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/0689b774-9bf0-4632-93d3-4d0f1a907074">

**[6단계] N진 관계 타입**
- ER 모델의 차수가 3 이상인 다진 관계 타입의 경우 [방법4]의 유형으로 사상된다.

<img width="642" alt="스크린샷 2023-08-02 오후 11 51 28" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/32482e6f-4279-4773-a497-2babab3f0610">

### 다중값 속성의 사상
- 속성의 사상(mapping) 시 단일 값으로 표현된 속성은 특별한 문제 없이 관계 데이터 모델로 직접 mapping 할 수 있다.
- 하지만, 다중값 속성의 경우 직접 mapping할 수 없다.
  - 다중값 속성: 하나의 속성에 여러 값을 가질 수 잇는 속성을 말한다.

<img width="646" alt="스크린샷 2023-08-02 오후 11 58 40" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/8ad17914-9930-4e10-b13c-f36863bc17a7">

**[7단계] 다중값 속성**
- 속성의 개수를 알 수 없는 경우 [방법1]을, 속성의 개수가 제한적으로 정해지는 경우 [방법2]를 사용한다.

<img width="582" alt="스크린샷 2023-08-03 오전 12 00 08" src="https://github.com/Hoya324/2023-DBStudy/assets/96857599/6bebfc1a-3b3b-45ca-91ef-c75bf0226674">


## 프로그래머스 문제 해결

### 문제1
```sql
SELECT FACTORY_ID,FACTORY_NAME,ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE '강원도%'
ORDER BY FACTORY_ID;
```

### 문제2
```sql
SELECT NAME
FROM ANIMAL_INS 
ORDER BY DATETIME
LIMIT 1;
```

### 문제3
```sql
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = "Sick"
ORDER BY ANIMAL_ID;
```

### 문제4
```sql
SELECT COUNT(*) count
FROM (SELECT NAME 
FROM ANIMAL_INS
WHERE NAME IS NOT NULL
GROUP BY NAME) ANIMALS;
```

### 문제5
```sql
SELECT MAX(PRICE) AS MAX_PRICE
FROM PRODUCT
```
