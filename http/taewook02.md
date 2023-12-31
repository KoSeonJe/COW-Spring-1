# HTTP

## 인터넷과 네트워크

### IP (Internet Protocol)

- 지정한 IP 주소에 데이터를 전달
- 패킷(Packet)이라는 통신 단위로 데이터를 전달

#### IP 패킷

- 출발지 IP, 목적지 IP, 정보등을 담은 데이터
- 클라이언트와 서버는 각각 IP 패킷을 생성하여 서로 데이터를 주고받는다.

#### IP의 한계

- 비연결성
  - 패킷을 받을 대상이 없거나, 서비스 불능 상태여도 패킷을 전송한다.
  - IP 프로토콜만으로는 클라이언트가 대상 서버가 패킷을 받을 수 있는 상태인지 알 수 없다.

- 비신뢰성
  - 중간에 패킷이 사라졌을 때의 문제 (패킷 소실)
  - 패킷이 순서대로 오지 않았을 때의 문제

- 프로그램 구분
  - 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상일 경우의 문제

위와 같은 문제들을 사용하기 위해 TCP(UDP) 프로토콜을 사용한다.

### 인터넷 프로토콜 스택의 4계층

![image](https://user-images.githubusercontent.com/128007622/257033530-4d8fa15b-e6cb-4c59-ae00-faf4fb34b9dc.png)

### TCP(Transmission Control Protocol)

- 전송 제어 프로토콜
- 연결지향
  - TCP 3 way handshake(가상 연결)
  - 상대방과 연결을 확인한다.
- 데이터 전달 보증
- 데이터 전달 순서 보장

위의 장점들과 신뢰성을 바탕으로 대부분의 애플리케이션에서 사용된다.

#### TCP/IP 패킷

- 기존 IP 패킷에 출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증등의 정보들을 추가로 담은 데이터

#### TCP 3 way handshake

![image](https://user-images.githubusercontent.com/128007622/257033581-4c6839e5-d3c6-41be-af59-efcbb1285f62.png)

- SYN : 접속 요청
- ACK : 요청 수락

- 3단계를 거치고 나서 데이터를 전송한다.
- 3번째 단계에서 ACK와 함께 데이터를 전송할 수도 있다.

#### 데이터 전달 보증

![image](https://user-images.githubusercontent.com/128007622/257033542-66835ab9-2af8-43e7-bdc3-c928995d72e2.png)

#### 데이터 전달 순서 보증

![image](https://user-images.githubusercontent.com/128007622/257033550-9383359b-c8fb-4ff2-917d-a674ebd2e26d.png)

### UDP(User Datagram Protocol)

- 사용자 데이터그램 프로토콜
- 기능이 거의 없음
- 연결지향 x, 데이터 전달 보증 x, 순서 보장 x
- IP와 거의 같지만, PORT와 체크섬 등의 정보가 추가되어 있다.
- 기능이 거의 없는 대신 단순하고 속도가 빠르다.
- 애플리케이션에서 추가 작업이 필요하다.

### PORT

![image](https://user-images.githubusercontent.com/128007622/257033421-af33d683-8204-43e9-936f-e6bb5aa90f62.png)

- 같은 IP 내에서 프로세스를 구분
- 0 ~ 65535 내에서 할당 가능
- 0 ~ 1023 사이의 포트 번호는 사용하지 않는 것이 좋음 (잘 알려진 포트)
- FTP : 20, 21
- TELNET : 23
- HTTP : 80
- HTTPS : 443

### DNS(Domain Name System)

![image](https://user-images.githubusercontent.com/128007622/257033466-54b551d3-829b-4999-9796-22982c86bcc8.png)

- 전화번호부와 같은 역할
- 도메인 주소를 아이피 주소로 변환

## URI, URL, URN

### URI(Uniform Resource Identifier)

- Uniform : 리소스를 식별하는 통일된 방식
- Resource : 자원, URI로 식별할 수 있는 모든 것(제한 없음)
- Identifier : 다른 항목과 구분하는데 필요한 정보

### URL(Uniform Resouce Locator)와 URN(Uniform Resource Name)

![image](https://user-images.githubusercontent.com/128007622/257033704-b8e2f169-eed3-4f93-a1f2-8d15e0601f55.png)

- URL (Locator) : 리소스가 있는 위치를 지정
- URN (Name) : 리소스에 이름을 부여
- URN에서의 이름만으로 실제 리소스를 찾을 수 있는 방법이 보편화되어 있지 않다.

### URL 문법

![image](https://user-images.githubusercontent.com/128007622/257033890-50836fd5-96c8-44f2-b7f2-c2dfe61def30.png)

#### Path

- 리소스의 경로
- 계층적 구조

#### Query

- Key=value 형태
- ?로 시작, &로 추가가 가능
  - ex : ?keyA=valueA&keyB=valueB

#### Fragment

- html 내부 북마크 등에서 사용
- 서버에 전송되는 정보가 아님

## HTTP 기본

### HTTP (HyperText Transfer Protocol)

- HTML 뿐만 아니라 Text, Image, 음성, 영상 파일 등 거의 모든 형태의 데이터 전송이 가능
- 서버간에 데이터를 주고 받을 때도 대부분 HTTP를 사용

- 클라이언트 - 서버 구조
- 무상태 프로토콜(스테이트리스), 비연결성
- HTTP 메세지 사용
- 단순함, 확장 가능

### 클라이언트 - 서버 구조

![image](https://user-images.githubusercontent.com/128007622/257034414-fcb6cb8a-be28-4d3c-b7bd-f03229f46f3e.png)

- Request, Response 구조
- 클라이언트는 서버에 요청을 보내고 응답을 대기
- 서버가 요청에 대한 결과를 만들어 응답

### 무상태 프로토콜 (Stateless)

- 서버가 클라이언트의 상태를 보관하지 않음
- 서버 확장성(Scale out)이 높아지는 장점이 있지만, 클라이언트가 처리해야할 데이터의 양이 늘어나는 단점도 있다.

#### Stateless(무상태) vs Stateful(상태 유지)

- 상태 유지 : 클라이언트의 요청을 처리하는 응답 서버가 중간에 변경될 수 없다.
- 무상태 : 응답 서버를 쉽게 변경 가능하기 때문에 서버 증설이 쉽다.

![image](https://user-images.githubusercontent.com/128007622/257034689-65398c9c-781e-4dcb-8395-2841855fef68.png)

- 모든 것을 무상태로 설계할 수는 없다. (로그인 유지 기능 등)

### 비연결성 (connectionless)

#### 연결을 유지하는 모델 vs 연결을 유지하지 않는 모델

![image](https://user-images.githubusercontent.com/128007622/257034786-aeb43df5-bbce-4227-ad1b-60a62ffa1a7b.png)

- 동시에 여러 클라이언트의 요청을 처리할 때 각 클라이언트들과 연결을 유지해야 함
- 자원 소모가 크다.

![image](https://user-images.githubusercontent.com/128007622/257034813-418e6185-ee14-4c56-8899-8e582e24d754.png)

- 각 요청에 대해 빠르게 응답하고 연결을 종료
- 서버 자원을 매우 효율적으로 사용할 수 있다.
- 그러나 매번 TCP/IP 연결을 새로 맺고 3 way handshake를 해야 하는 단점이 존재한다.
- HTTP/2,3에서 최적화가 이뤄지고 HTTP 지속 연결(Persistent Connections)로 문제를 해결했다.

##### HTTP 지속 연결 (Persistent Connections)

![image](https://user-images.githubusercontent.com/128007622/257034979-26f9a6f5-d1dc-410e-867f-bcc0afd19a78.png)

![image](https://user-images.githubusercontent.com/128007622/257034989-2126d132-57b2-43ae-b79d-864f0d31133b.png)

### HTTP 메시지

![image](https://user-images.githubusercontent.com/128007622/257035082-f8feba99-340a-49f1-82c9-62ba9bed9581.png)

## HTTP 메서드

### HTTP API 설계

![image](https://user-images.githubusercontent.com/96857599/257170227-d160c5ee-979f-4ef4-9bfa-77794fd1bc49.png)

- 계층 구조를 활용
- URI는 리소스만 식별하도록 설계
- 리소스와 해당 리소스를 대상으로 하는 행위를 분리 (메서드 사용)

### 주요 메서드

- GET : 리소스 조회
- POST : 요청 데이터 처리, 주로 등록에 사용
- PUT : 리소스를 대체, 해당 리소스가 없으면 생성
- PATCH : 리소스 부분 변경
- DELETE : 리소스 삭제

### 기타 메서드

- HEAD : GET과 동일하지만 메시지 부분을 제외하고 상태 줄과 헤더만 반환
- OPTIONS : 대상 리소스에 대한 통신 가능 옵션(메서드)을 주로 설명 (CORS에서 주로 사용함)
- CONNECT : 대상 자원으로 식별되는 서버에 대한 터널을 설정
- TRACE : 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행

### GET

- 리소스 조회
- 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)을 통해 전달
- 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않음

### POST

![image](https://user-images.githubusercontent.com/96857599/257200355-f33cb971-17f5-402a-9651-30a5715e94e0.png)

- 요청 데이터 처리
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 요청 데이터를 처리
- 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용
- 리소스마다 따로 POST 요청을 처리할 방법을 지정해야 한다.

### PUT

- 리소스를 대체
  - 리소스가 있으면 대체
  - 리소스가 없으면 생성
- POST와 다르게 클라이언트가 리소스 위치를 알고 URI를 지정함

### PATCH

- 리소스를 부분 변경

### DELETE

- 리소스를 제거

### HTTP 메서드의 속성

![image](https://user-images.githubusercontent.com/96857599/257202571-896a077b-699b-45e4-a710-fb29c4abe33f.png)

#### 안전 (Safe)

- 호출해도 리소스를 변경하지 않음

#### 멱등 (Idempotent)

- 여러 번 호출해도 같은 결과가 반환
- GET, PUT, DELETE가 멱등 메서드이다.
- POST는 멱등 메서드가 아니다.
- 멱등은 외부 요인으로 중간에 리소스가 변경되는 것까지는 고려하지 않는다.

#### 캐시가능 (Cacheable)

- 응답 결과 리소스를 캐시해서 사용이 가능한지
- GET, HEAD, POST, PATCH는 캐시 가능
- 실제로는 GET, HEAD 정도만 캐시로 사용
- POST, PATCH는 본문 내용까지 캐시 키로 고려해야 하기 때문에 구현이 어려움

## HTTP 메서드 활용

### 클라이언트에서 서버로 데이터 전송

- 정적 데이터 조회 (쿼리 스트링 x)
- 동적 데이터 조회 (쿼리 스트링 o)
- HTML Form을 통한 데이터 전송
- HTTP API를 통한 데이터 전송 (서버 to 서버, 앱 클라이언트, Ajax)

#### 쿼리 파라미터를 통한 데이터 전송

- GET 사용
- 검색 등의 기능에서 사용

#### 메시지 바디를 통한 데이터 전송

- POST, PUT, PATCH
- 회원 가입, 상품 주문, 리소스 등록, 리소스 변경

## HTTP API 설계 예시

![image](https://user-images.githubusercontent.com/96857599/257481296-a7440b1e-60f1-4808-864d-48e7969f70ae.png)

### 컬렉션

![image](https://user-images.githubusercontent.com/96857599/257479872-406d6182-94e3-43df-b78d-77f11fbed531.png)

- POST 사용
- 서버가 관리하는 리소스 디렉토리
- 서버가 리소스의 URI를 생성하고 관리(클라이언트는 등록될 리소스의 URI를 모름)

### 스토어

![image](https://user-images.githubusercontent.com/96857599/257480003-97c2c02f-3a2e-42b6-98ec-9a832a8eb905.png)

- PUT 사용
- 클라이언트가 관리하는 리소스 저장소
- 클라이언트가 직접 리소스의 URI를 지정 (클라이언트가 리소스 URI를 알고 있어야 함)

### FORM 사용

![image](https://user-images.githubusercontent.com/96857599/257480091-d66f2bc3-ae5d-4800-836c-5d8465584272.png)

- HTML FORM은 GET, POST만 지원
- 그 외의 메서드를 사용하기 위해서는 AJAX와 같은 기술을 사용해야 함
- GET, POST만 지원하기 때문에 발생하는 제약을 해결하기 위해 동사로 된 리소스 경로를 사용한다.

## HTTP 상태 코드

![image](https://user-images.githubusercontent.com/96857599/257481651-f2409414-cb4d-4f88-990f-4249552464de.png)

- 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 역할을 하는 코드
- 서버에서 반환한 코드를 인식할 수 없는 경우 클라이언트는 상태 코드의 백의 자리 번호를 해석하여 처리한다.
  - 예) 299 -> 2xx(성공)

### 100번대

- 요청이 수신되어 처리중
- 거의 사용하지 않는다.

### 200번대

- 클라이언트의 요청을 성공적으로 처리

#### 201 CREATED

- 요청을 성공적으로 처리하여 새로운 리소스가 생성되었음

#### 202 Accepted

- 요청이 접수되었으나 처리가 완료되지 않았음

#### 204 No Content

- 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음
- 예) 웹 문서 편집기의 Save 버튼 등

### 300번대

- 요청을 완료하기 위해 유저 에이전트의 추가조치 필요

#### 리다이렉션

![image](https://user-images.githubusercontent.com/96857599/257484147-a13401ab-8d4d-4f18-8c1b-544d45bccac3.png)

- 웹 브라우저는 3xx의 응답 결과에 Location 헤더가 있는 경우, 해당 위치로 이동(리다이렉트)한다.

|종류|설명|
|:---:|:---:|
|영구 리다이렉션|특정 리소스의 URI가 영구적으로 이동|
|일시 리다이렉션|일시적으로 변경|
|특수 리다이렉션|클라이언트가 캐시를 사용하도록 응답|

##### 영구 리다이렉션

- 리소스의 URI가 영구적으로 이동했음
- 301 Moved Permanently : 리다이렉트시 요청 메서드가 GET으로 바뀌고, 본문이 제거될 수 있음
- 308 Permanent Redirect : 리다이렉트시 요청 메서드와 본문 유지, 그 외의 기능은 301과 같음

##### 일시 리다이렉션

- 리소스의 URI가 일시적으로 변경
- 304 Found : 리다이렉트시 요청 메서드가 GET으로 변하거나 본문이 제거될 수 있음
- 307 Temporary Redirect : 리다이렉트시 요청 메서드와 본문 유지, 그 외의 기능은 304와 같음
- 302 See Other : 302와 같은 기능, 리다이렉트시 요청 메서드가 반드시 Get으로 변경
- 모호한 302 대신 307, 303이 권장되지만 많은 애플리케이션 라이브러리들이 302를 사용하기 때문에, 302를 사용해도 큰 문제는 없다.

##### PRG : POST/REDIRECT/GET

![image](https://user-images.githubusercontent.com/96857599/257488830-69354803-1113-4923-b565-3307ab380bbb.png)

![image](https://user-images.githubusercontent.com/96857599/257488940-01003fde-06e9-439b-a1f3-205c7c2ece86.png)

- POST로 주문 후에 새로 고침으로 인한 중복 주문 방지
- POST로 주문 후에 주문 결과 화면을 GET 메서드로 리다이렉트
- 새로고침을 해도 GET으로 결과 화면이 조회된다.

##### 기타 리다이렉션

- 300 Multiple Choices : 사용하지 않음
- 304 Not Modified
  - 클라이언트에게 리소스가 수정되지 않았음을 알린다.
  - 응답을 받은 클라이언트는 로컬에 저장된 캐시를 재사용한다.
  - 응답에 메시지 바디를 포함하지 않아야 한다.

### 400번대

- 클라이언트 오류
- 클라이언트의 요청에 잘못된 문법등이 있어 서버가 요청을 수행할 수 없음
- 오류의 원인이 클라이언트에 있음
- 요청 자체에 오류가 있기 때문에 요청을 재시도해도 실패한다.

#### 400 Bad Request

- 요청 구문, 메시지 등의 오류
- 클라이언트가 요청 내용을 다시 검토하고 보내야 함

#### 401 Unauthorized

- 인증(Authentication)되지 않음
- 인증(Authentication) : 유저가 누구인지 확인 (로그인 자체)
- 인가(Authorization) : 권한을 부여 (특정 리소스에 접근할 수 있는 권한)

#### 403 Forbidden

- 서버가 요청을 이해했지만 승인을 거부
- 인증 자격은 있지만 접근 권한이 불충분

#### 404 Not Found

- 요청 리소스가 서버에 없음
- 클라이언트가 권한이 부족한 리소스에 접근할 경우에 해당 리소스를 숨기고 싶을 때 사용하기도 함 (403을 사용하지 않기 위해)

### 500번대

- 서버 문제로 오류
- 서버의 문제이기 때문에 재시도 시 성공할 수도 있음

#### 500 Internak Serval Error

- 서버 문제로 오류 발생

#### 503 Service Unavailable

- 서비스 이용 불가
- 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없음

## HTTP 헤더

- General Header(공통 헤더) : 요청과 응답 모두에 적용되지만 바디에서 최종적으로 전송되는 데이터와는 관련이 없는 헤더
- Request Header(요청 헤더) : 페치될 리소스나 클라이언트 자체에 대한 자세한 정보를 포함하는 헤더
- Response Header(응답 헤더) : 위치 또는 서버 자체에 대한 정보(이름, 버전 등)와 같이 응답에 대한 부가적인 정보를 갖는 헤더
- Entity Header(엔티티 헤더) : 컨텐츠 길이나 MIME 타입과 같이 엔티티 바디에 대한 자세한 정보를 포함하는 헤더

### General Header

- Date

> Date: (day-name), (day) (month) (year) (hour):(minute):(econd) GMT
> 예) "Date: Wed, 21 Oct 2015 07:28:00 GMT"

- Connection

> close, Keep-Alive가 있다.
> close는 메세지 교환 후 TCP 연결 종료
> Keep-Alive는 메세지 교환 후 TCP 연결 유지
> 예) Connection: close

### Request Header

- Host

> 요청하는 자의 호스트명, 포트 번호를 포함하고 있음
> 예) Host: developer.mozilla.org

- User-Agent

> 요청자의 소프트웨어 정보를 표현한다.
> 소프트웨어 정보 : os, 브라우저, 기타 버전 정보
> 예) User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:50.0) Gecko/20100101 Firefox/50.0

- Accept

> 요청자가 원하는 미디어의 타입 및 우선순위를 표현한다.
> Accept-Language : 사용자가 원하는 언어셋
> Accept-Encodig : 사용자가 원하는 인코딩 방식
> Accept: application/json, text/plain, */*
> -> json > text > all type 순으로 받는다는 표현이다.
> Accept-Language: en-US,en;q=0.5
> -> 언어는 en이라는 표현이다. q는 가중치다.
> Accept-Encoding: gzip, deflate, br
> -> gzip, deflate, br(Brotli) 등등의 압축 포맷을 받는다는 표현이다.

- Cookie

> 서버에 의해서 이전에 저장된 쿠키를 포함시키는 속성이다.

- Referer

> 현재 요청을 보낸 페이지의 절대 혹은 부분 주소를 포함하는 속성이다.
> referrer-policy : 헤더는 요청과 함께 얼마나 많은 레퍼럴 정보를 포함하는지 알려준다.
> referer-after : 응답 헤더에 속하는 것인데, 다음에 올 요청이 이루어지기 전에 사용자가 대기해야 하는 시간을 가르킨다.

### Response Header

- Server

> 서버 소프트웨어의 정보를 표현

- content-encoding

> 응답하는 내용의 인코딩 포맷을 표현

- content-type

> 응답하는 내용의 타입과 문자 포맷을 표현

- cache-control

> 캐시 관리에 대한 정보를 표현

- date

> 응답 메세지가 생성된 시간을 표현

- vary

> 캐시된 응답을 향후의 응답에 사용할 기준을 표현

- Set-Cookie

> 서버에서 사용자에게 세션 쿠키 정보를 전달

- Age

> max-age내에서 캐시가 얼마나 지났는지 초 단위로 표현

## REST API

웹을 위한 하나의 아키텍처 스타일

### API : Application Programming Interface

API는 요청을 받아 프로그램과 상호작용하여 원하는 값을 전달하는 역할을 한다.

API는 모든 접속을 표준화한다. (디바이스나 OS의 종류에 구애받지 않고 동일한 액세스를 얻울 수 있다.)

REST API에는 6개의 제약조건이 존재하는데 아래는 그 중 가장 중요한 두가지 내용이다.

### 1. 자원에 대한 식별

자원을 URL로 표현

### 2. 표현(representation)을 통한 자원 조작

**RE**presentational **S**tate **T**ransfer

리소스에 대한 행위를 HTTP 메서드로 표현
