목차

- 인터넷과 네트워크
- http
- http 메서드
- http 상태코드
- http 헤더
- Rest API

# 1. 인터넷 네트워크

### 1) 인터넷 통신

- ex) 한국에 사는 데 중국에 메시지를 보낼 때 인터넷 망이 필요하다.
- 클라이언트와 서버 중간에 인터넷이 존재한다.
- 인터넷 망을 통해 클라이언트의 입력을 서버에 보내는 것이다.

### 2) IP(인터넷 프로토콜)

- 복잡한 인터넷 망에서 최소한의 규칙
- IP 주소 부여
- IP 역할
    - 지정한 IP 주소(IP Address)에 데이터 전달
    - 패킷이라는 통신 단위로 데이터 전달
    - 패킷 : pack과 bucket의 합친말, 컴퓨터 간에 데이터를 주고받을 때 네트워크를 통해서 전송되는 데이터 조각
- IP 패킷 정보
    - 출발지 IP(자신의 IP), 목적지 IP(상대방 IP), 기타 … 와 데이터
    - TCP 관련 정보(출발지 PORT, 목적지 PORT, 전송 제어, 순서, 검증 정보)
- IP 프로토콜의 한계
    - 비연결성
        - 패킷을 받을 대상이 없거나  서비스 불능 상태여도 패킷 전송
        - ex) 상대방의 Pc가 꺼져 있어도 전송됨, 받을 대상이 없어도 전송됨
    - 비신뢰성
        - 중간에 패킷이 사라지면? ex)중간의 노드가 문제가 생겼을 때
        - 패킷이 순서대로 안오면?
    - 프로그램 구분
        - 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상이면?

### 3) TCP, UDP

- 인터넷 프로토콜 스택의 4계층
    - 애플리케이션 계층 - HTTP, FTP
    - 전송 계층 - TCP, UDP
    - 인터넷 계층 - IP
    - 네트워크 인터페이스 계층

- 프로토콜 계층
    - 애플리케이션
        1. 프로그램이 Hello, world!메시지 생성
        2. SOCKET 라이브러리를 통해 전달
    - OS
        - TCP 정보 생성, 메시지 데이터 포함
        - IP패킷 생성, TCP데이터 포함
    - 네트워크 인터페이스
        - LAN 카드에 포함되어 전달됨

- TCP 특징(전송 제어 프로토콜, Transmission Control Protocol)
    - 연결지향 - TCP 3 way handshake(가상 연결)
        1. SYN 메시지를 보냄. (클라이언트 → 서버)
        2. SYN+ACK (서버→ 클라이언트)
        3. ACK (클라이언트 → 서버)
        - SYN : 접속 요청, ACK : 요청 수락
        - 참고 : 3번에서 ACK와 함께 데이터 전송 가능
    - 데이터 전달 보증
        - 클라이언트에서 데이터를 전송해주면 서버에서 잘 받았다고 응답을 해준다. 응답이 없다면 전송이 안된 것임.
    - 순서 보장
        - 패킷1,2,3의 순서로 보냈지만 패킷1,3,2로 도착한다면 서버에서 패킷 2번부터 다시보내라고 한다.
    - 신뢰할 수 있는 프로토콜
    - 현재는 대부분 TCP 사용

- UDP 특징(사용자 데이터 프로토콜, User Datagram Protocol)
    - 하얀 도화지에 비유(기능이 거의 없다)
    - 연결지향 - TCP 3 way handshake X
    - 데이터 전달 보증 X
    - 순서 보장 X
    - 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름
    - 정리
        - IP와 거의 같다. PORT와 체크섬 정도만 추가됨.
        - 애플리케이션에서 추가 작업 필요
        - PORT : 하나의 IP에서 여러 애플리케이션을 실행할 때 받은 패킷이 어느 어플리케이션 패킷인가 구분해주는 역할을 해줌.
        - 체크섬 : 메시지에 대해 정확한지, 검증
    - 최근에 굉장히 관심받고 있다.

### 4) PORT

- IP : 목적지 서버를 찾는 것.
- PORT : 서버 안에서 애플리케이션을 찾는 것이다. 즉, 같은 IP내에서 프로세스를 구분해주는 역할이다.
- ex) 한번에 둘 이상 연결하면?
    - 게임, 화상통화, 웹브라우저 요청
    - 게임, 화상통화, 웹브라우저 등에는 각각 8090, 21000, 10010의 PORT가 존재한다.
- 0~65535 할당 가능
- 0~1023: 잘 알려진 포트, 사용하지 않는 것이 좋음
    - FTP - 20, 21
    - TELNET - 23
    - HTTP - 80
    - HTTPS - 443

### 5) DNS

- IP는 기억하기 어렵다
- IP는 변경될 수 있다.

- DNS (도메인 네임 시스템, Domain Name System)
    - 전화번호부
    - 도메인 명을 IP 주소로 변환

---

# URI와 웹 브라우저 요청 흐름

### URI(Uniform Resource Identifier)

- URI? URL? URN?
- “URI”는 로케이터(locator), 이름(name) 또는 둘 다 추가로 분류될 수 있다.
- (URI(URL, URN)) -URI에 URL과 URN이 포함되어 있다.

- URI
    - Uniform : 리소스 식별하는 통일된 방식
    - Resource : 자원, URI로 식별할 수 있는 모든 것(제한 없음)
    - Identifier : 다른 항목과 구분하는 데 필요한 정보
- URL, URN
    - URL - Locator : 리소스가 있는 위치를 지정
    - URN - Name : 리소스에 이름을 부여
    - 위치는 변할 수 있지만, 이름은 변하지 않는다.
    - URN 이름만으로 실제 리소스를 찾을 수 있는 방법이 보편화 되지 않음.

- URL 전체 문법
    - scheme://[userinfo@]host[:port][/path][?query][#fragment]
    - https://www.google.com:443/search?q=hello&hl=ko

    - 프로토콜(https)
        - 프로토콜 : 어떤 방식으로 자원에 접근할 것인가 하는 약속 규칙
            - 예) http, https, ftp 등등
        - http는 80포트, https는 443포트를 주로 사용, 포트는 생략 가능
        - https는 http에 보안 추가(HTTP Secure)
    - 호스트명(www.google.com)
        - 도메인 명이나, IP주소 직접 입력 가능
    - 포트 번호(443)
        - 접속 포트
        - 일반적으로 생략, 생략시 http는 80 , https는 443
    - 패스(/search)
        - 리소스 경로(path), 계층적 구조
        - 예) /home/file1.jpg
    - 쿼리 파라미터(q=hello&hl=ko)
        - key = value 형태
        - ?로 시작, &로 추가 가능
        - query parameter, query string등으로 불림, 웹서버에 제공하는 파라미터, 문자 형태
    - fragment
        - html내부 북마크 등에 사용
        - 서버에 전송하는 정보 아님

### 웹 브라우저 요청 흐름

- https://www.google.com:443/search?q=hello&hl=ko

1. 웹브라우저가 DNS 조회 → IP가 나옴
2. PORT나옴.
3. 웹브라우저가 HTTP 요청 메시지 생성
    - GET/search?q=hello&hl=ko HTTP/1.1

      Host: www.google.com


- HTTP 메시지 전송
    1. 웹 브라우저가 HTTP 메시지 생성
    2. SOCKET 라이브러리를 통해 전달( 3 way handshake)
        - A: TCP/IP 연결(IP, PORT)
        - B : 데이터 전달 (전송 데이터 : HTTP 메시지)
    3. TCP/IP 패킷 생성, HTTP 메시지 포함
    4. 인터넷으로 감.

- HTTP 응답 메시지
    - HTTP/1.1 200 OK

      Content-Type : text/html;charset=UTF-8

      Content-Length:3423


(데이터)

<html>

<body> … <body>

</html>

---

# [HTTP]

### 1) HTTP(HyperText Transfer Protocol)

- HTTP메시지에 모든 것을 전송
    - HTML, TEXT
    - IMAGE, 음성, 영상, 파일
    - JSON, XML(API)
    - 거의 모든 형태의 데이터 전송 가능
    - 서버 간에 데이터를 주고 받을 때도 대부분 HTTP 사용
    - 지금은 HTTP 시대

- HTTP 역사
    - HTTP/0.9 1991년 : GET메소드만 지원, HTTP 헤더X
    - HTTP/1.0 1996년 : 메소드, 헤더 추가
    - HTTP/1.1 1997년 : 가장 많이 사용, 우리에게 가장 중요한 버전
        - RFC2068(1997) → RFC2616(1999) → RFC7230~7235(2014)
    - HTTP/2 2015년 : 성능 개선
    - HTTP/3 진행중 : TCP 대신에 UDP 사용, 성능 개선

- 기반 프로토콜
    - TCP : HTTP/1.1 , HTTP/2
    - UDP : HTTP/3
    - 현재 HTTP/1.1 주로 사용

### 2) HTTP 특징

### 클라이언트 서버 구조

- Request Response 구조
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
    - 클라이언트는 단순히 UI, 사용성만 생각함
    - 서버에 복잡한 로직이나 데이터를 저장.
- 서버가 요청에 대한 결과를 만들어서 응답

### 무상태 프로토콜(스테이스리스)

- 서버가 클라이언트의 상태를 보존X
- Stateful, Stateless 차이
    - 상태 유지 : 중간에 다른 점원으로 바뀌면 안된다.
        - 중간에 다른 점원으로 바뀔 떄 상태 정보를 다른 점원에게 미리 알려줘야 한다.
    - 무상태 : 중간에 다른 점원으로 바뀌어도 된다.
        - 갑자기 고객이 증가해도 점원을 대거 투입할 수 있다.
        - 갑자기 클라이언트 요청이 증가해도 서버를 대거 투입할 수 있다.
    - 무상태는 응답 서버를 쉽게 바꿀 수 있다 → 무한한 서버 증설 가능
- 스케일 아웃 - 수평 확장에 유리하다.
- 실무 한계
    - 모든 것을 무상태로 설계 할 수 있는 경우도 있고 없는 경우도 있다.
    - 무상태
        - 예) 로그인이 필요 없는 단순한 서비스 소개 화면
    - 상태 유지
        - 예) 로그인
    - 로그인한 사용자의 경우 로그인 했다는 상태를 서버에 유지
    - 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
    - 상태 유지는 최소한만 사용
    - 많은 데이터가 필요하다.
- 서버 개발자들이 어려워하는 업무(스테이스리스를 기억하자)
    - 정말 같은 시간에 딱 맞추어 발생하는 대용량 트래픽
    - 예) 선착순 이벤트, 명절 KTX 예약, 학과 수업 등록

### 비 연결성

- HTTP는 기본이 연결을 유지하지 않는 모델
- 일반적으로 초 단위의 이하의 빠른 속도로 응답
- 1시간 동안 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음
    - 예) 웹 브라우저에서 계속 연속해서 검색 버튼을 누르지는 않는다.
- 서버 자원을 매우 효율적으로 사용할 수 있음, 최소화 할 수 있다.
- 비 연결성 한계와 극복
    - TCP/IP 연결을 새로 맺어야 함 - 3 way handshake 시간 추가
    - 웹브라우저로 사이트를 요청하면 HTML 뿐만 아니라 자바스크립트, css, 추가 이미지 등 수 많은 자원이 함께 다운로드
    - 지금은 HTTP 지속 연결(Persistent Connections)로 문제 해결
        - HTML을 그리기 위해 필요한 자바스크립트, css, 이미지 데이터를 모두 서버에서 받아올 때 까지 연결을 끊지 않는다.
        - HTTP/2, HTTP/3에서 더 많은 최적화

### HTTP 메시지 구조

1. start-line 시작 라인
    1. 요청 메시지(GET /search?q=hello&hl=ko HTTP/1.1)
        - start-line = request-line / status-line
        - request-line
            - = method SP(공백) request-target SP HTTP-version CRLF(엔터)

        - HTTP 메소드 (GET : 조회)
            - 종류 : GET, POST, PUT, DELETE
            - 서버가 수행해야 할 동작 지정
        - 요청(/search?q=hello&hl=ko)
            - absolute-path[?query]
            - 절대경로= “/”로 시작하는 경로
        - HTTP Version

   b.  응답 메시지(HTTP/1.1 200 OK)

    - start-line =request-line / status-line
    - status-line = HTTP-version  SP status-code SP reason-phrase CRLF

    - HTTP 버전
    - HTTP 상태 코드 : 요청 성공, 실패를 나타냄
        - 200: 성공
        - 400: 클라이언트 요청 오류
        - 500: 서버 내부 오류
    - 이유 문구 : 사람이 이해할 수 있는 짧은 상태 코드 설명 글

2. HTTP 헤더
- field-name “:” OWS field-value OWS
    - (OWS: 띄어쓰기 허용)
- field-name은 대소문자 구분 없음
    - Host: www.google.com
    - Content-Type : text/html;charset=UTF-8

      Content-Length:3423

- 용도
    - HTTP전송에 필요한 모든 부가정보(필요한 메타데이터 전부)
    - 예) 메시지 바디의 내용, 메시지 바디의 크기, 압충, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보

1. empty line 공백 라인 (CRLF)

1. message body
- 용도
    - 실제 전송할 데이터
    - HTML문서, 이미지, 영상, JSON 등등 byte로 표현할 수 있는 모든 데이터 전송 가능

단순함, 확장 가능

- HTTP는 단순하다.
- HTTP메시지도 매우 단순
- 크게 성공하는 표준 기술은 단순하지만 확장 가능한 기술

---

# HTTP메소드

### 1) HTTP API만들기

- 요구사항- 회원 정보 관리 API를 만들어라
1. API URI 설계 (계층 구조 활용)

→ 가장 중요한 것은 **리소스 식별**이다.

- 리소스의 의미는 뭘까?
    - 회원을 등록하고 수정하고 조회하는게 리소스가 아니다.
    - 예) 미네랄을 캐라 → 미네랄이 리소스
    - 회원이라는 개념 자체가 바로 리소스이다.
- 리소스를 어떻게 식별하는게 좋을까?
    - 회원을 등록하고 수정하고 조회하는 것을 모두 배제
    - 회원이라는 리소스만 식별하면 된다.

      → 회원 리소스를 URI에 매핑

        - 회원 목록 조회 /members
        - 회원 조회 /members/{id} → 어떻게 구분?
        - 회원 등록 /members/{id} → 어떻게 구분?
        - 회원 수정 /members/{id} → 어떻게 구분?
        - 회원 삭제 /members/{id} → 어떻게 구분?
- 리소스와 행위를 분리!!
    - URI는 리소스만 식별
    - 리소스와 해당 리소스를 대상으로 하는 행위를 분리
        - 리소스 : 회원
        - 행위 : 조회, 등록, 삭제, 변경
    - 리소스는 명사, 행위는 동사 (미네랄을 캐라)
    - 행위(메소드)는 어떻게 구분?

      →HTTP 메소드


### 2) HTTP메소드 - GET, POST

1. HTTP 메소드 종류
- 주요 메소드
    - GET : 리소스 조회
    - POST : 요청 데이터 처리, 주로 등록에 사용
    - PUT : 리소스를 대체, 해당 리소스가 없으면 생성
    - PATCH : 리소스 부분 변경
    - DELETE : 리소스 삭제
- 기타 메소드
    - HEAD
    - OPTIONS
    - CONNECT
    - TRACE

### GET

- 리소스 조회
- 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)을 통해서 전달
- 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아 권장 X
- 리소스 조회 예시
    - 클라이언트에서 “GET /members/100 HTTP/1.1 “
      HTTP메시지를 서버에 전달
    - 서버는 /members/100의 데이터를 JSON형식으로 데이터를 만들어 응답 메시지와 함께 클라이언트로 보냄.

### POST(만능)

- 요청 데이터 처리
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 요청 데이터를 처리
    - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행
- 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용
- 리소스 등록 예시
    - 클라이언트에서 “POST /members HTTP/1.1” HTTP메시지와 등록할 데이터를 서버에 전달
    - 받은 데이터를 /members 데이터베이스에 저장하고 신규 리소스 식별자(/members/100)를 생성
    - “HTTP/1.1 201 Created”와 자원이 생성된 경로 그리고 등록된 데이터를 응답 메시지로 클라이언트에 보내줌.

- 요청 데이터를 어떻게 처리한다는 뜻일까?
    - 스펙 : POST 메소드는 대상 리소스가 리소스의 고유한 의미 체계에 따라 요청에 포함 된 표현을 처리하도록 요청한다.
    - 예
        - HTML 양식에 입력 된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공
            - HTML FORM에 입력한 정보로 회원 가입, 주문 등에 사용
        - 게시판, 뉴스 그룹, 메일링 리스트, 블로그 또는 유사한 기사 그룹에 메시지 게시
            - 예) 게시판 글쓰기, 댓글 달기
        - 서버가 아직 식별하지 않은 새 리소스 생성
            - 예) 신규 주문 생성
        - 기존 자원에 데이터 추가
            - 예) 한 문서 끝에 내용 추가하기
    - 정리 : 이 리소스 URI에 POST요청이 오면 요청 데이터를 어떻게 처리할지 리소스마다 따로 정해야 함 → 정해진 것이 없다.

- 정리
    1. 새 리소스 생성(등록)
        - 서버가 아직 식별하지 않은 새 리소스 생성
    2. 요청 데이터 처리
        - 단순히 데이터를 생성하거나, 변경하는 것을 넘어 프로세스를 처리해야 하는 경우
        - 예) 주문에서 결제완료 → 배달시작 → 배달완료 처럼 단순히 값 변경을 넘어 프로세스의 상태가 변경되는 경우
        - POST의 결과로 새로운 리소스가 생성되지 않을 수 있다.
        - 예) POST /orders/orderId/start-delivery(컨트롤 URI)
    3. 다른 메소드로 처리하기 애매한 경우
        - 예) JSON으로 조회 데이터를 넘겨야 하는데, GET메소드를 사용하기 어려운 경우

### 3) HTTP메소드 - PUT, PATCH, DELETE

### PUT

PUT /members/100 HTTP/1.1

- 리소스를 대체
    - 리소스가 있으면 대체
    - 리소스가 없으면 생성
    - 쉽게 이야기해서 덮어버림
- 중요! 클라이언트가 리소스를 식별
    - 클라이언트가 리소스 위치를 알고 URI 지정
    - POST와의 차이
- 리소스를 부분 수정하는데 용이하지 않다. 클라이언트에서 보낸 데이터로 완전히 대체가 되기 때문이다.

### PATCH

PATCH /members/100 HTTP/1.1

- 리소스 부분 수정
    - 클라이언트에서
      { “age” : 50} 의 데이터를 넘기면
    - 서버에서
      {”username” : “young”,
      “age” : 20}
      →
      {”username” : “young”,
      “age” : 50}

### DELETE

DELETE /members/100 HTTP/1.1

- 리소스 제거

### 4) HTTP 메소드의 속성

### 안전(Safe Methods)

- 호출해도 리소스를 변경하지 않는 메소드
- Q : 그래도 계속 호출해서, 로그 같은게 쌓여서 장애가 발생하면요?
- A : 안전은 해당 리소스만 고려한다. 그런 부분까지 고려하지 않음

### 멱등(Idempotent Methods)

- 한번 호출하든 두 번 호출하든 100번 호출하든 결과가 똑같은 것
- 멱등 메소드
    - GET : 한 번 조회하든, 두 번 조회하든 같은 결과가 조회된다.
    - PUT : 결과를 대체한다. 따라서 같은 요청을 여러 번 해도 최종 결과는 같다.
    - DELETE : 결과를 삭제한다. 같은 요청을 여러 번 해도 삭제된 결과는 똑같다.
    - POST : 멱등이 아니다! 두 번 호출하면 같은 결제가 중복해서 발생 할 수 있다.
- 활용
    - 자동 복구 매커니즘
    - 서버가 TIMEOUT 등으로 정상 응답을 못주었을 떄, 클라이언트가 같은 요청을 다시 해도 되는가? 판단 근거
- Q: 재요청 중간에 다른 곳에서 리소스를 변경해버리면?
    - 사용자 1 : GET → username: A, age: 20
    - 사용자2 : PUT → username : A, age : 30
    - 사용자 1 : GET → username : A, age : 30 → 바뀜
- A : 멱등은 외부 요인으로 중간에 리소스가 변경되는 것 까지 고려하지 않는다. 동일한 사용자만 고려함

### 캐시가능(Cacheable Methods)

- 응답 결과 리소스를 캐시해서 사용해도 되는가?
- GET, HEAD, POST, PATCH 캐시가능
- 실제로는 GET, HEAD 정도만 캐시로 사용
    - POST, PATCH는 본문 내용까지 캐시 키로 고려해야 하는데, 구현이 쉽지 않음

---

# HTTP 메소드 활용

## 1. 클라이언트에서 서버로 데이터 전송

### 데이터 전달 방식은 크게 2가지

1. 쿼리 파라미터를 통한 데이터 전송
    - GET
    - 주로 정렬 필터(검색어) ex) q=hello&hl=ko
2. 메시지 바디를 통한 데이터 전송
    - POST, PUT, PATCH
    - 회원 가입, 상품 주문, 리소스 등록 , 리소스 변경

### 4가지 상황

1. 정적 데이터 조회
    - 이미지, 정적 텍스트 문서
    - 조회는 GET 사용
    - 정적 데이터는 일반적으로 쿼리 파라미터 없이 리소스 경로로 단순하게 조회 가능

1. 동적 데이터 조회
    - GET /search?q=hello&hl=ko HTTP/1.1
      Host : www.google.comy
    - 주로 검색, 게시판 목록에서 정렬 필터(검색어)
    - 조회 조건을 줄여주는 필터, 조회 결과를 정렬하는 정렬 조건에 주로 사용
    - 조회는 GET 사용
    - GET은 쿼리 파라미터 사용해서 데이터를 전달

1. HTML Form 데이터 전송

POST 전송 -저장

```html
<form action="/save" method="post"> 
	<input type="text" name="username" /> 
	<input type="text" name="age" /> 
	<button type="submit">전송</button>
</form>
```

→ 웹 브라우저가 생성한 요청 HTTP 메시지

**POST /save HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded**

**username=kim&age=20**

multipart/form-data(파일과 같은 데이터를 전송할 때 사용)

```html
<form action="/save" method="post" enctype="multipart/form-data">
 <input type="text" name="username" />
 <input type="text" name="age" />
 <input type="file" name="file1" />
<button type="submit">전송</button> </form>
```

- 정리
    - HTML Form submit시 POST전송
        - 예) 회원 가입, 상품 주문, 데이터 변경
    - Content-Type : application/x-www-form-urlencoded 사용 (default)
        - form의 내용을 메시지 바디를 통해 전송(key =value, 쿼리 파라미터 형식)
        - 전송 데이터를 url encoding처리
            - 예) abc 김 → abc%EA%B9%80
    - HTML Form은 GET 전송도 가능
    - Content-Type : multipart/form-data
        - 파일 업로드 같은 바이너리 데이터 전송시 사용
        - 다른 종류의 여러 파일과 폼의 내용 함께 전송 가능
    - 참고 : HTML Form 전송은 GET, POST만 지원

1. HTTP API 데이터 전송
- 서버 to 서버
    - 백엔드 시스템 통신
- 앱 클라이언트
    - 아이폰, 안드로이드
- 웹 클라이언트
    - HTML에서 Form 전송 대신 자바 스크립트를 통한 통신에 사용 (AJAX)
    - 예) React, VueJs 같은 웹 클라이언트와 API 통신
- POST, PUT, PATCH :  메시지 바디를 통해 데이터 전송
- GET : 조회, 쿼리 파라미터로 데이터 전달
- Content-Type : applcation/json을 주로 사용 (표준)
    - JSON을 주로 사용함.

## 2. HTTP API 설계 예시

### 회원 관리 시스템

### API 설계 - POST 기반 등록

- 회원 목록 /members → GET
- 회원 등록 /members → POST
- 회원 조회 /members/{id} → GET
- 회원 수정 /members/{id} → PATCH, PUT, POST
- 회원 삭제 /members/{id} → DELETE

### POST - 신규 자원 등록 특징

- 클라이언트는 등록될 리소스의 URI를 모른다.
    - 회원 등록 POST /members
- 서버가 새로 등록된 리소스 URI를 생성해준다.
    - HTTP/1.1 201 Created
      Location: /members/100
- 컬렉션(Collections)
    - 서버가 관리하는 리소스 디렉토리
    - 서버가 리소스의 URI를 생성하고 관리
    - 여기서 컬렉션은 /members

### 파일 관리 시스템
API 설계 - PUT 기반 등록

- 파일 목록 /files → GET
- 파일 조회 /files/{filename} → GET
- 파일 등록 /files/{filename} → PUT
- 파일 삭제 /files/{filename} → DELETE
- 파일 대량 등록 /files/{filename} → POST

### PUT - 신규 자원 등록 특징

- 클라이언트가 리소스 URI를 알고 있어야 한다.
    - 파일 등록 PUT /files/star.jpg
- 클라이언트가 직접 리소스의 URI를 지정한다.
- 스토어(Store)
    - 클라이언트가 관리하는 리소스 저장소
    - 클라이언트가 리소스의 URI를 알고 관리
    - 여기서 스토어는 /files


→ 실무에서는 거의 POST를 사용

### HTML FORM 사용

- HTML FORM은 GET, POST만 지원
- AJAX 같은 기술을 사용해서 해결 가능 → 회원 API 참고
- 여기서는 순수 HTML, HTML FORM이야기
- GET, POST만 지원하므로 제약이 있음.

- 회원 목록 /members → GET
- 회원 등록 폼 /members/new → GET
- 회원 등록  /members/new, /members →  POST
- 회원 조회 /members/{id} → GET
- 회원 수정 폼 /members/{id}/edit → GET
- 회원 수정 /members/{id}/edit, /members/{id} → POST
- 회원 삭제 /members/{id}/delete → POST

- 컨트롤 URI
    - GET, POST만 지원하므로 제약이 있음
    - 이런 제약을 해결하기 위해 **동사**로 된 리소스 경로 사용
    - POST의 /new, /edit, /delete가 컨트롤 URI
    - HTTP 메소드로 해결하기 애매한 경우 사용(HTTP API 포함)

→ 실무에서는 사실 컨트롤 URI를 많이 사용함. 어쩔 수 없는 경우가 많음, 하지만 최대한 리소스만의 개념을 가지고 설계하는 것이 중요.

### 참고하면 좋은 URI 설계 개념

- 문서
    - 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)
    - 예) /members/100, /files/star.jpg
- 컬렉션(주로 사용)
    - 서버가 관리하는 리소스 디렉토리
    - 서버가 리소스의 URI를 생성하고 관리
    - 예) members
- 스토어
    - 클라이언트가 관리하는 자원 저장소
    - 클라이언트가 리소스의 URI를 알고 관리
    - 예) /files
- 컨트롤러, 컨트롤 URI
    - 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행
    - 동사를 직접 사용

---

# 상태코드

### 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능

- 1xx(Informational) : 요청이 수신되어 처리중
    - 거의 사용하지 않아서 생략.
- 2xx(Successful) : 요청 정상 처리
- 3xx(Redirection): 요청을 완료하려면 추가 행동이 필요
- 4xx(Client Error) : 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 5xx(Server Error) : 서버 오류, 서버가 정상 요청을 처리하지 못함.

### 2xx (Successful)
- 클라이언트의 요청을 성공적으로 처리

- 200 OK
    - 요청에 성공
- 201 Created
    - 요청에 성공해서 새로운 리소스가 생성됨.
- 202 Accepted
    - 요청이 접수되었으나 처리가 완료되지 않음.
    - 배치 처리 같은 곳에서 사용
    - 예) 요청 접수 후 1시간 뒤에 배치 프로세스가 요청을 처리함
- 204 No Content
    - 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음
    - 예) 웹 문서 편집기에서 save 버튼
      save 버튼의 결과로 아무 내용이 없어도 된다.
      save 버튼을 눌러도 같은 화면을 유지해야 한다.
    - 결과 내용이 없어도 204 메시지(2xx)만으로 성공을 인식할 수 있다.

### 3xx -(Redirection)
요청을 완료하기 위해 유저 에이전트의 추가 조치 필요

- 300 Multiple Choice( 거의 안씀)
- 301 Moved Permanently
- 302 Found
- 303 See Other
- 304 Not Modified
- 307 Temporary Redirect
- 308 Permanent Redirect

- 리다이렉션 이해
    - 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동 → 리다이렉트

- 리다이렉션 종류
    - 영구 리다이렉션 - 특정 리소스의 URI가 영구적으로 이동
        - 리소스의 URI가 영구적으로 이동
        - 원래의 URL을 사용x, 검색 엔진 등에서도 변경 인지
        - 301 Moved Permanently
            - POST가 리다이렉트시 요청시 메소드가 GET으로 변하고, 본문이 제거될 수 있음(MAY)
        - 308 Permanent Redirect (거의 못봄)
            - 301과 기능은 같음
            - 리다이렉트시 요청 메소드와 본문 유지(처음 POST메소드 사용되고, 리다이렉트시에도 POST 사용)
        - 예) /members → /users
        - 예) /event → /new-event

    - 일시 리다이렉션 - 일시적인 변경
        - 리소스의 URI가 일시적으로 변경
        - 따라서 검색 엔진 등에서 URL을 변경하면 안됨
        - 302 Found (명확하지 않음, 대부분 사용함)
            - 리다이렉트 시 요청 메소드가 GET으로 변하고, 본문이 제거될 수 있음.
        - 307 Temporary Redirect
            - 302와 기능은 같음
            - 리다이렉트시 요청 메소드와 본문 유지(요청 메소드를 변경하면 안된다. MUST NOT)
        - 303 See Other
            - 302와 기능은 같음
            - 리다이렉트시 요청 메소드가 GET으로 변경
        - 주문 완료 후 주문 내역 화면으로 이동
        - PRG : Post/Redirect/Get (예시)
            - POST로 주문 후에 웹 브라우저를 새로고침하면?

              새로고침은 다시 요청

              중복 주문이 될 수 있다.

            - PRG 이후 리다이렉트
                - URL이 이미 POST → GET으로 리다이렉트 됨
                - 새로 고침 해도 GET으로 결과 화면만 조회

    - 특수 리다이렉션
        - 300 Multiple Choices : 안쓴다.
        - 304 Not Modified
            - 캐시를 목적으로 이용
            - 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 클라이언트는 로컬 PC에 저장된 캐시를 재사용한다. (캐시로 리다이렉트 한다.)
            - 304 응답은 응답에 메시지 바디를 포함하면 안된다.(로컬 캐시를 사용해야 하므로)
            - 조건부 GET, HEAD 요청시 사용

### 4xx(Client Error)
클라이언트 오류

- 클라이언트의 요청에 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- 오류의 원인이 클라이언트에 있음
- 중요! 클라이언트가 이미 잘못된 요청, 데이터를 보내고 있기 때문에, 똑같은 재시도가 실패함

- 400 Bad Request
  클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음
    - 요청 구문, 메시지 등등 오류
    - 클라이언트는 요청 내용을 다시 검토하고, 보내야함
    - 예) 요청 파라미터가 잘못되거나, API 스펙이 맞지 않을 때

- 401 Unauthorized
  클라이언트가 해당 리소스에 대한 인증이 필요함(로그인 안됨)
    - 인증 되지 않음
    - 401 오류 발생시 응답에 WWW-Authenticate헤더와 함께 인증 방법을 설명
    - 참고
        - 인증 : 본인이 누구인지 확인
        - 인가 : 권한부여(ADMIN 권한처럼 특정 리소스에 접근할 수 있는 권한, 인증이 있어야 인가가 있음)
        - 오류 메시지가 Unauthorized이지만 인증 되지 않음

- 403 Forbidden
  서버가 요청을 이해했지만 승인을 거부함
    - 주로 인증 자격 증명은 있지만, 접근 권한이 불충분한 경우
    - 예) 어드민 등급이 아닌 사용자가 로그인은 했지만, 어드민 등급의 리소스에 접근하는 경우

- 404 Not Found
  요청 리소스를 찾을 수 없음
    - 요청 리소스가 서버에 없음
    - 또는 클라이언트가 권한이 부족한 리소스에 접근할 때, 해당 리소스를 숨기고 싶을 때

### 5xx(Server Error)
서버 오류

- 서버 문제로 오류 발생
- 서버에 문제가 있기 때문에 재시도 하면 성공할 수 있음(복구되면)

- 500 Internal Server Error
    - 서버 내부 문제로 오류 발생
    - 애매하면 500 오류

- 503 Service Unavailable(서비스 이용 불가)
    - 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없음
    - Retry-After 헤더 필드로 얼마뒤에 복구되는지 보낼 수도 있음

---

# HTTP 헤더

- header-field
  = field-name “:” OWS field-value OWS
  (OWS: 띄어쓰기 허용)

### 용도

- HTTP전송에 필요한 모든 부가정보(필요한 메타데이터 전부)
- 예) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보

### 분류

- 헤더 분류(과거)
    - General 헤더 : 메시지 전체에 적용되는 정보
    - Request헤더 : 요청 정보
    - Response 헤더 : 응답 정보
    - Entity 헤더 : 엔터티 바디 정보
- RFC723x변화
    - 엔터티(Entity) → 표현(Representation)
    - 표현 = 표현 메타데이터 + 표현 데이터

- 메시지 본문(message body)을 통해 표현 데이터 전달
- 메시지 본문 = 페이로드(payload)
- 표현은 요청이나 응답에서 전달할 실제 데이터
- 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공
    - 데이터 유형(html, json), 데이터 길이, 압축 정보 등등

### 1. 표현 헤더

- Content-Type : 표현 데이터의 형식
    - 메시지 바디에 들어가는 내용이 무엇인지 알려줌.
    - 미디어 타입, 문자 인코딩

- Content - Encoding : 표현 데이터의 압축 방식
    - 표현 데이터를 압축하기 위해 사용
    - 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
    - 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제

- Content -Language : 표현 데이터의 자연 언어
    - 예) ko, en, en-US

- Content -Length : 표현  데이터의 길이
    - 바이트 단위
    - Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안됨.

- 표현 헤더는 전송, 응답 둘 다 사용

### 2. 협상(콘텐츠 네고시에이션)
클라이언트가 선호하는 표현 요청

- Accept : 클라이언트가 선호하는 미디어 타입 전달
- Accept-Charset: 클라이언트가 선호하는 문자 인코딩
- Accept-Encoding : 클라이언트가 선호하는 압축 인코딩
- Accept-Language : 클라이언트가 선호하는 자연 언어

- 협상 헤더는 요청시에만 사용

→ 모든 것을 맞춰주지는 않는다. 하지만 서버에서 최대한 맞춰주려고 한다.

- 협상과 우선순위1(Quality Values(q))
    - Quality Values(q) 값 사용
    - 0~1 클수록 높은 우선순위
    - 생략하면 1
    - Accept-Language : ko-KR, ko;q=0.9, en-US;q=0.8, en;q=0.7
        1. ko-KR;q=1 (q생략)
        2. ko;q=0.9
        3. en-US;q=0.8
        4. en;q=0.7

- 협상과 우선순위2
    - 구체적인 것이 우선한다.
    - ****Accept: text/*, text/plain,text/plain;format=flowed, */*****
        1. text/plain;format=flowed
        2. text/plain
        3. text/*
        4. */*

- 협상과 우선순위3
    - 구체적인 것을 기준으로 미디어 타입을 맞춘다.
    - Accept :
      **text/***;q=0.3, **text/html**;q=0.7, **text/html;level=1**,

      **text/html;level=2**;q=0.4, **/***;q=0.5


### 3. 전송 방식

- 단순 전송
    - Content-Length를 정확히 알 때
- 압축 전송
    - Content-Encoding : gzip
- 분할 전송
    - Transfer-Encoding : chunked
    - 쪼개서 보냄. Content-Length를 보내면 안됨.
- 범위 전송
    - Content-Range :bytes 1001-2000

### 4. 일반 정보

- Form(유저 에이전트의 이메일 정보)
    - 일반적으로 잘 사용되지 않음
    - 검색 엔진 같은 곳에서, 주로 사용
    - 요청에서 사용

- Referer(이전 웹 페이지 주소)
    - 현재 요청된 페이지의 이전 웹 페이지 주소
    - A→B로 이동하는 경우 B를 요청할 때 Referer: A를 포함해서 요청
    - Referer를 사용해서 유입 경로 분석 가능
    - 요청에서 사용

- User-Agent(유저 에이전트 애플리케이션 정보)
    - 클라이언트의 애플리케이션 정보(웹 브라우저 정보, 등등)
    - 통계 정보
    - 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능
    - 요청에서 사용

- Server(요청을 처리하는 ORIGIN 서버의 소프트웨어 정보)
    - Server : Apache/2.2.22(Debian)
    - server : nginx
    - 응답에서 사용

- Date(메시지가 발생한 날짜와 시간)
    - 응답에서 사용

### 5. 특별한 정보

- Host(요청한 호스트 정보(도메인))
    - 요청에서 사용
    - 필수
    - 하나의 서버가 여러 도메인을 처리해야 할 때
    - 하나의 IP주소에 여러 도메인이 적용 되었을 때

- Location(페이지 리다이렉션)
    - 웹브라우저는 3xx 응답의 결과에 Location헤더가 있으면, Location 위치로 자동 이동(리다이렉트)
    - 응답코드 3xx에서 설명
    - 201(Created) : Location 값은 요청에 의해 생성된 리소스 URI
    - 3xx(Redirection) : Location 값은 요청을 자동으로 리다이렉션하기 위한 대상 리소스를 가리킴

- Allow(허용 가능한 HTTP 메소드)
    - 405(Method Not Allowed)에서 응답에 포함해야함
    - Allow : GET, HEAD, PUT

- Retry-After(유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간)
    - 503(Service Unavailable): 서비스가 언제까지 불능인지 알려줄 수 있음
    - Retry-After : Fri, 31, Dec 1999 23:59:59 GMT(날짜 표기)
    - Retry-After : 120(초단위 표기)

### 6. 인증

- Authorization : 클라이언트 인증 정보를 서버에 전달
    - Authorization : Basic xxxxxxxxxx
- WWW-Authenticate : 리소스 접근시 필요한 인증 방법 정의
    - 401 Unauthorized 응답과 함께 사용

### 7. 쿠키

- Set-Cookie : 서버에서 클라이언트로 쿠키 전달(응답)
- Cookie : 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP요청시 서버로 전달

예) set-cookie : **seesionId=abcde1234;** **expires**=Sat, 26-Dec-2020 00:00:00 GMT; path=/; domain=.google.com;**Secure**

- 사용처
    - 사용자 로그인 세션 관리
        - 쿠키에서 직접 로그인 정보를 넘겨준다기 보단 로그인의 세션 아이디를 받아 사용자 정보 로그인을 찾는다
        - 광고 정보 트래킹
    - 쿠키 정보는 항상 서버에 전송됨
        - 네트워크 트래픽 추가 유발
        - 최소한의 정보만 사용(세션 id, 인증 토큰)
        - 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지 참고
    - 쿠키에는 보안에 중요한 정보를 저장해서는 안된다.

- 생명주기(Expires, max-age)
    - Set-Cookie: expires = Sat, 26-Dec-2020 04:39:31 GMT
        - 만료일이 되면 쿠키 삭제
    - Set-Cookie: max-age =3600(3600초)
        - 0이나 음수를 지정하면 쿠키 삭제
    - 세션 쿠키 : 만료 날짜를 생략하면 브라우저 종료시 까지만 유지
    - 영속 쿠키 : 만료 날짜를 입력하면 해당 날짜까지 유지

- 도메인
    - 예) domain = example.org
    - 명시 : 명시한 문서 기준 도메인 + 서브 도메인 포함
        - domain=example.org를 지정해서 쿠키 생성
            - example.org는 물론
            - dev.example.org도 쿠키 접근
    - 생략 : 현재 문서 기준 도메인만 적용
        - example.org에서 쿠키를 생성하고 domain지정을 생략
            - example.org에서만 쿠키 접근
            - dev.example.org는 쿠키 미접근

- 경로
    - 예) path=/home
    - 이 경로를 포함한 하위 경로 페이지만 쿠키 접근
    - 일반적으로 path=/ 루트로 지정

- 보안
    - Secure
        - 쿠키는 http, https를 구분하지 않고 전송
        - Secure를 적용하면 https인 경우에만 전송
    - HttpOnly
        - XSS공격 방지
        - 자바스크립트에서 접근 불가(document.cookie)
        - HTTP전송에만 사용
    - SameSite
        - XSRF 공격 방지
        - 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송

### 8. 캐시 기본 동작

- 캐시가 없을 때
    - 데이터가 변경되지 않아도 계속 네트워크를 통해 데이터를 다운로드 받아야 한다.
    - 인터넷 네트워크는 매우 느리고 비싸다
    - 브라우저 로딩 속도가 느리다
    - 느린 사용자 경험

- 캐시 적용
    - 웹 브라우저에는 캐시를 저장할 공간이 있다.
    - 캐시 덕분에 캐시 가능 시간동안에 네트워크를 사용하지 않아도 된다.
    - 비싼 네트워크 사용량을 줄일 수 있다.
    - 브라우저 로딩 속도가 매우 빠르다.
    - 빠른 사용자 경험

- 캐시 시간 초과
    - 캐시 유효 시간이 초과하면, 서버를 통해 데이터를 다시 조회하고, 캐시를 갱신한다.
    - 이때 다시 네트워크 다운로드가 발생한다.

### 9. 검증 헤더와 조건부 요청

- 캐시 시간 초과
    - 캐시 유효 시간이 초과해서 서버에 다시 요청하면 다음 두 가지 상황이 나타난다.
        1. 서버에서 기존 데이터를 변경
        2. 서버에서 기존 데이터를 변경하지 않는다.
    - 캐시 만료 후에도 서버에서 데이터를 변경하지 않음

  → 데이터를 전송하는 대신에 저장해 두었던 캐시를 재사용

  → 그러기 위해 클라이언트 데이터와 서버의 데이터가 같다는 사실을 확인할 수 있는 방법이 필요하다.


- 서버에서 Last-Modified(검증 헤더)를 응답메시지에 보내 캐시에 같이 저장해준다.
- 클라이언트가 if-modified-since(조건부 요청)을 요청 메시지에 보내서, 캐시의 데이터가 변경되었는지 확인한다.

정리

- 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면
- 304 Not Modified + 헤더 메타 정보만 응답(바디x)
- 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보를 갱신
- 클라이언트는 캐시에 저장되어 있는 데이터 재활용
- 결과적으로 네트워크 다운로드가 발생하지만 용량이 적은 헤더 정보만 다운로드
- 매우 실용적인 해결책

### 10. 검증 헤더와 조건부 요청2

- 검증 헤더
    - 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터
    - Last-Modified, ETag
- 조건부 요청 헤더
    - 검증 헤더로 조건에 따른 분기
    - If-Modified-Since : Last-Modified 사용
    - If-None-Match: ETag 사용
    - 조건이 만족하면 200 OK
    - 조건이 만족하지 않으면 304 Not Modified

- Last-Modified, If-Modified-Since 단점
    - 1초 미만단위로 캐시 조정이 불가능
    - 날짜 기반의 로직 사용
    - 데이터를 수정해서 날짜가 다르지만, 같은 데이터를 수정해서 데이터 결과가 똑같은 경우
    - 서버에서 별도의 캐시 로직을 관리하고 싶은 경우
        - ex) 스페이스, 주석처럼 영향 없는 변경에 캐시 유지하고 싶은 경우

- ETag, If-None-Match
    - ETag(Entity Tag)
    - 캐시용 데이터에 임의의 고유한 버전 이름을 달아둠
    - 데이터가 변경되면 이 이름을 바꾸어서 변경함(Hash를 다시 만듦)
        - Hash는 콘텐츠의 내용이 같으면 항상 같은 값을 반환한다.
    - ETag만 보내서 같으면 캐시 유지, 다르면 다시 받기
    - 클라이언트는 단순히 이 값을 서버에 제공할 뿐, 캐시 제어 로직을 서버에서 완전히 관리한다.

### 11. 캐시와 조건부 요청 헤더

- Cache-Control (캐시 지시어)
    - Cache-Control : max-age
        - 캐시 유효 시간, 초 단위
    - Cache-Control: no-cache
        - 데이터는 캐시해도 되지만, 항상 원(origin)서버에 검증하고 사용
    - Cache-Control : no-strore
        - 데이터에 민감한 정보가 있으므로 저장하면 안됨
    - Cache-Control : must-revalidate
        - 캐시 만료후 최초 조회시 원 서버에 검증해야함
        - 원 서버 접근 실패시 반드시 오류가 발생해야 함
        - 504(Gateway Timeout)
        - must-revalidate는 캐시 유효 시간이라면 캐시를 사용함
    - Cache-Control : public
        - 응답이 public 캐시에 저장되어도 됨
    - Cache-Control : private
        - 응답이 해당 사용자만을 위한 것임. private캐시에 저장해야 한다.(default)
    - Cache-Control : s-maxage
        - 프록시 캐시에만 적용되는 max-age
    - Age : 60 (HTTP 헤더)
        - origin 서버에서 응답 후 프록시 캐시 내에 머문 시간

### 12. 프록시 캐시

클라이언트 1, 2, 3 —— 프록시 캐시 서버(한국에서 만든 서버) —— 미국에 있는 원 서버

→ 미국에 있는 원 서버에서 이미지를 다운로드 받아오려면 0.5 초가 걸린다고 가정하자. 프록시 캐시 서버에서 한번만 0.5초 다운로드 받고, 클라이언트 1, 2, 3에는 빠른 시간으로 전송 해주는 중간 다리 역할이다.

- 프록시 : 클라이언트와 서버 사이에 대리로 통신을 수행하는 것
- 프록시 서버 : 중계 기능을 하는 서버

### 13. 캐시 무효화

- 확실한 캐시 무효화 응답
    - Cache-Control : no-cache, no-store, must-revalidate
    - Pragma: no-cache

→ 완벽한 캐시 무효화를 위해 위 헤더를 모두 추가해 주어야 한다.

- no-cache vs must revalidate
    - no-cache
        - 원 서버에 접근 할 수 없는 경우 캐시 서버에 따라 오래된 데이터라도 보여주자는 것으로, 프록시 캐시 데이터를 반환할 수 있다.
        - Error or 200 Ok
    - must revalidate
        - 원 서버에 접근할 수 없는 경우, 항상 오류 발생
        - 504 Gateway Timeout

---

# REST API

- REST API란 REST를 기반으로 만들어진 API이다.

### REST((Representational State Transfer)

- 자원을 이름으로 구분하여 해당 자원의 상태를 주고받는 모든 것을 의미합니다.
- 즉, HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE, PATCH 등)를 통해 해당 자원(URI)에 대한 CRUD Operation을 적용하는 것을 의미합니다.

### REST 구성 요소

1. 자원(Resource) : HTTP URI
2. 자원에 대한 행위 : HTTP Method
3. 자원에 대한 행위의 내용 : HTTP Message Pay Load

### REST의 특징

1. Server-Client(서버-클라이언트 구조)
2. Stateless(무상태)
3. Cacheable(캐시 처리 가능)
4. Layered System(계층화)
5. Uniform Interface(인터페이스 일관성)