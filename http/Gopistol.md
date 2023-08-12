<details>
<summary>인터넷과 네트워크</summary>
<div markdown="1">
  
  ### 인터넷
  
  인터넷 프로토콜 스위트(TCP/IP)를 기반으로 하여 여러 컴퓨터가 각각 클라이언트와 서버로써 전 세계적으로 연결되어있는 컴퓨터 네트워크 통신망
  
  ### 네트워크
  
  > 몇 개의 독립적인 장치가 적절한 영역내에서 적당히 빠른 속도의 물리적 통신 채널을 통하여 서로가 직접 통신할 수 있도록 지원해 주는 데이터 통신 체계 
  <br>*IEEE(Institute of Electrical and Electronics Engineers:국제 전기 전자 공학회)*
  > 
  
  ### IP(인터넷 프로토콜)
  
  IP주소를 부여함으로써 메시지를 보낼 수 있음
  
  IP 역할
  
  - 지정한 IP Address에 데이터 전달
  - Packet (통신 단위)로 데이터 전달
  
  전달되는 IP 패킷에는 출발지 IP, 목적지 IP가 들어가야 함
  
  클라이언트 패킷 전달 - 노드들을 통해 목적이 IP까지 정확히 도달하게 됨
  
  서버 패킷 전달 - 같은 방식으로 클라이언트로 패킷을 전달함, 노드를 통해 전달되므로 전달 경로는 클라이언트 패킷의 경로와 다를 수 있음(인터넷망)
  
  IP 프로토콜의 한계
  
  - 비연결성
      - 패킷을 수신할 대상 X, 서비스 불능 상태여도 패킷이 전송됨
  - 비신뢰성
      - 패킷이 중간에 사라지거나 순서대로 도착하지 않을 수 있음
  - 프로그램 구분
      - 같은 IP를 사용할 때(인터넷 게임을 하면서 음악 듣기 등) 어떻게 구분?
  
  → TCP, UDP를 통해 위와 같은 한계 문제를 해결할 수 있음
  
  ### TCP, UDP
  
  인터넷 프로토콜 스택의 4계층
  
  - 애플리케이션 계층 - HTTP, FTP
  - 전송 계층 - TCP, UDP
  - 인터넷 계층 - IP
  - 네트워크 인터페이스 계층
  
  <img width="356" alt="스크린샷 2023-07-31 오후 6 11 53" src="https://github.com/Youngcircle-kim/CowSubway-BackEnd/assets/104254012/3e072957-accc-409c-b456-1a9aaaade6f8">

  
  TCP 특징 - 전송 제어 프로토콜(Transmission Control Protocol)
  
  - 연결지향 - TCP 3 way handshake(가상 연결)
      - 컴퓨터가 꺼져 있어도 우선 가상으로 연결시킴
      1. 클라이언트에서 서버로 SYN 메시지를 보냄
      2. 서버가 성공적으로 받으면 SYN+ACK 을 클라이언트에게 다시 보냄
      3. 클라이언트가 받게 되면 ACK 메시지를 서버에게 보냄 (데이터도 같이 전송 가능)
      
      → 클라이언트와 서버가 서로 믿을 수 있음 (서로 연결되었다는 것)
      
      → 진짜 연결이 된 것이 아니라, 논리적인 연결을 말함.
      
  - 데이터 전달 보증
      - 클라이언트에서 서버로 데이터를 전송하면, 서버에서 클라이언트에게 데이터를 잘 받았다는 메시지를 전송함
  - 순서 보장
      - 만약 클라이언트에서 패킷 1,2,3 순서로 전송했지만 서버가 1,3,2 순서로 받았을 때, 서버는 클라이언트에게 1번 다음에 3번이 왔으므로 2번부터 다시 보내라는 메시지를 전송함
  - 신뢰할 수 있는 프로토콜, 대부분 TCP 사용
      - TCP/IP 패킷에는 출발지, 목적지 IP 뿐만 아니라 출발지 목적지 PORT, 전송 제어 정보, 순서 정보, 검증 정보 등이 포함되기 때문에 신뢰성이 높아짐
  
  UDP 특징 - 사용자 데이터그램 프로토콜(User Datagram Protocol)
  
  - 기능이 거의 없음
  - 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름
  - IP와 비슷 + PORT + 체크섬(메시지가 제대로 받았는지 검증해주는 데이터)
  - 최근에 뜨는 이유: HTTP3 - UDP 프로토콜을 사용 (최적화를 위해, 3 way handshake 사용 X)
  
  ### PORT
  
  IP만 가지고는 나에게 날아오는 패킷들이 어떤 작업에 필요한 패킷인지 알 수가 없어서, 이것을 구분하기 위해 PORT를 사용함
  
  같은 IP 내에서 프로세스 구분 
  
  IP : 아파트
  
  PORT : 몇 동 몇 호
  
  ### DNS
  
  IP는
  
  - 기억하기 어렵다
  - 변경될 수 있다(나중에 접근 X)
  
  → 도메인 네임 시스템(Domain Name System)
  
  DNS 서버에 도메인 명 등록 가능
  
  전화번호부 같은 역할 
  
  but 정확히 이해가 안감
  
  ### URI와 웹 브라우저 요청 흐름
  
  Uniform Resource Identifier: 리소스를 식별하는 통합된 방법?
  
  Uniform: 리소스 식별하는 통일된 방식
  
  Resource: 자원, URI로 식별할 수 있는 모든 것(제한 X)
  
  Identifier: 다른 항목과 구분하는데 필요한 정보
  
  URI 는 Locater, Name 또는 둘 다 추가로 분류될 수 있다.
  
  URL - 리소스의 위치 ex: 김영한이 사는 위치
  
  URN - 리소스의 이름 ex: ‘김영한’ 그자체
  
  URN은 이름을 부여해 버려서 찾기 힘듦 그래서 거의 URL만 씀
  
  → URI = URL
  
  Content-Type: 응답 언어의 종류와 형식
</div>    
</details>

<details>
<summary>http</summary>
<div markdown="2">
  
  ### HTTP
  
  Hypertext Transfer Protocol 의 약자로, 클라이언트와 서버 간 통신을 위한 통신 규칙 세트 또는 프로토콜
  
  80번 포트를 사용함
  
  1.1 버전이 가장 많이 사용
  
  ### HTTPS
  
  Hypertext Transfer Protocol Secure 의 약자로, 브라우저와 서버가 데이터를 전송하기 전에 안전하고 암호화된 연결을 설정함
  
  443번 포트를 사용함
  
  암호화 방식은 두 가지가 있는데, 둘 다 사용함
  
  - 대칭키 암호화
      - 클라이언트, 서버가 동일한 키를 사용해 암호화/복호화를 진행함
      - 키 노출 시 위험하지만, 연산 속도가 빠름
  - 비대칭키 암호화
      - 1개의 쌍으로 구성된 공개키, 개인키를 암호화/복호화 하는 데 사용함
          - 공개키: 모두에게 공개 가능한 키
          - 개인키: 나만 가지고 알고 있어야 하는 키
      - 키 노출 시 비교적 안전하지만, 연산 속도가 느림
  
  공개키, 개인키 암호화 방식
  
  - 공개키 암호화: 공개키로 암호화를 하면 개인키로만 복호화할 수 있다. -> 개인키는 나만 가지고 있으므로, 나만 볼 수 있다.
  - 개인키 암호화: 개인키로 암호화하면 공개키로만 복호화할 수 있다. -> 공개키는 모두에게 공개되어 있으므로, 내가 인증한 정보임을 알려 신뢰성을 보장할 수 있다.
  
  ### 클라이언트 서버 구조
  
  비즈니스 로직, 데이터 등을 서버에 다 밀어넣고
  
  클라이언트는 UI, 사용성에 집중함
  
  ### 특징
  
  ### Stateful, Stateless
  
  stateless
  
  - 서버가 클라이언트의 상태 보존 X
  - 점원이 바껴도 문제 X
  
  → 무한한 서버 증설 가능, 수평 확장 유리
  
  상태 유지로 설계해야 하는 것
  
  - 로그인
  - 브라우저 쿠키와 세션 등을 사용
  - 상태 유지는 최소한으로 사용(어쩔 수 없이)
  
  ### Connectionless 비연결성
  
  연결을 유지하지 않으면 서버는 최소한의 자원만 유지하면 된다
  
  HTTP는 기본이 연결을 유지하지 않는 모델이다
  
  서버 자원을 매우 효율적으로 사용 가능
  
  - 서버 개발자는 같은 시간에 딱 발생하는 대용량 트래픽을 어려워함
  - 가능하면 상태 유지는 최소화!

</div>
</details>

<details>
<summary>http 메소드</summary>
<div markdown="3">
  
  ### 주요 메소드
  
  - GET: 리소스 조회 요청
  - POST: 요청 데이터를 처리함, 주로 데이터 등록에 사용됨
  - PUT: 리소스를 대체하고, 해당 리소스가 없으면 생성함
  - DELETE: 리소스를 삭제함
  - PATCH: 리소스의 일부를 변경함.
      - PUT vs PATCH (대체, 수정)
          
          PUT은 리소스를 수정하는 것이 아니라 **리소스를 요청 바디에 담긴 내용으로 대체**하는 것이므로 사용 시 요청 본문에 리소스 전체를 표현하여 보내야 한다. 하지만 PATCH는 현재 저장된 리소스에 수정을 가하는 행위를 의미하므로 수정하지 않은 사항을 요청 본문에 담아줄 필요가 없다. 따라서 PATCH 메소드가 ‘**수정**’이라는 의미를 가지기에 더욱 적합하다고 볼 수 있다.
          
  
  ### 기타 메소드
  
  - HEAD: GET과 동일하지만 메시지 부분을 제외하고 상태 줄과 헤더만 반환함
  - CONNECT: 대상 리소스로 식별되는 서버로의 터널을 정해줌
  - OPTIONS: 목적 리소스의 통신을 설정하는 데 사용됨
  - TRACE: 목적 리소스의 경로를 따라 메시지 loop-back 테스트를 함
  
  ### 속성
  
  - 안전성
      - 메소드 호출을 계속해도 리소스를 변경하지 않는다.
      - GET 메소드가 안전하다고 할 수 있음
  - 멱등성
      - 메소드를 계속 호출해도 결과가 똑같음
      - GET, PUT, DELETE는 멱등성을 보장한다고 할 수 있음
      - POST, PATCH는 멱등성을 보장한다고 할 수 없음
          - POST: 리소스를 새롭게 생성하는 행위이므로 여러 번 수행할 때 수행 결과가 매번 달라짐
          - PATCH: 스펙 상 구현 방법에 대한 제한이 없으므로 API를 어떻게 구현하느냐에 따라서 멱등성이 보장될 수도 있고(단순 수정할 부분만 보내는 경우), 안 될수도 있음
          [https://evan-moon.github.io/2020/04/07/about-restful-api/#rest가-의미하는-것이-무엇인가요](https://evan-moon.github.io/2020/04/07/about-restful-api/#rest%EA%B0%80-%EC%9D%98%EB%AF%B8%ED%95%98%EB%8A%94-%EA%B2%83%EC%9D%B4-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94)

</div>
</details>

<details>
<summary>http 상태코드</summary>
<div markdown="4">

  <br>세 자리 숫자로 되어 있는데 첫 번째 숫자는 HTTP 응답의 종류를 구분하고, 나머지 2개의 숫자는 세부적인 응답 내용을 위한 번호이다<br> 
  
  - 100번대 (정보 제공)- 현재 클라이언트의 요청까지는 처리되었으므로 진행하라.
      - 100: 계속 진행하라
      - 101: 프로토콜을 전환하라
      - 102: 처리 중이다
  - 200번대 (성공) - 클라이언트의 요청이 서버에서 성공적으로 처리됨.
      - 200: 서버가 요청을 성공적으로 처리했다
      - 201: 요청이 처리되어서 새로운 리소스가 생성되었다
      - 202: 요청을 접수했지만 처리가 완료되지 않았다
  - 300번대 (리다이렉션) - 완전한 처리를 위해 추가 동작이 필요함. 서버 주소 또는 요청 URI 웹 문서가 이동되었으니 그 주소로 다시 시도하라.
      - 300: 선택 항목이 여러 개 있다
      - 301: 지정한 리소스가 새로운 URI로 이동했다
      - 302: 요청한 리소스를 다른 URI에서 찾았다
      - 303: 다른 위치로 요청하라
      - 304: 마지막 요청 이후 그 페이지를 수정되지 않았다
      - 307: 임시로 리다이렉션 요청이 필요하다
  - 400번대 (클라이언트 에러) - 클라이언트의 요청 메시지 내용이 잘못됨.
      - 400: 요청 구문이 잘못되었다
      - 401: 지정한 리소스에 액세스 권한이 없다
      - 403: 지정한 리소스에 액세스가 금지되었다
      - 404: 지정한 리소스를 찾을 수 없다
  - 500번대 (서버 에러) - 서버에서 메시지 처리에 문제가 발생함. 주로 서버 부하, DB 오류, 서버 예외 등이 발생하는 경우.
      - 500: 서버에 에러가 발생했다
      - 501: 요청 URI에 대해 서버가 구현하고 있지 않다
      - 502: 게이트웨이 또는 프록시 역할을 하는 서버가 그 뒷단의 서버로부터 잘못된 응답을 받았다
      - 503: 현재 서버에서 서비스를 제공할 수 없다
      - 504: 프록시 서버가 타임아웃이 발생하였다

</div>
</details>

<details>
<summary>http 헤더</summary>
<div markdown="5">
  
  ## 종류
  
  ### HTTP 공통 헤더
  
  주요 항목
  
  - Date
      - HTTP 메시지 생성 일시
      - `Date: Sat, 2 Oct 2018 02:00:12 GMT`
  - Connection
      - 클라이언트, 서버 간 연결에 대한 옵션 설정
      - Connection: close
          - 현재 Http 메시지 직후에 TCP 접속을 끊는다는 것을 알림
      - Connection: Keep-Alive
          - 현재 TCP 커넥션을 유지함
  - Cache-Control
      - 쿠키, 캐시 관련 헤더
  - Pragma
  - Trailer
  
  ### HTTP 엔티티 관련 헤더
  
  HTTP 메시지 내 포함된 선택적인 개체에 대한 구체적인 미디어 타입 등의 설명
  
  주요 항목
  
  - Content-Type
      - 해당 개체에 포함되는 미디어 타입 정보
      - 타입, 서브타입으로 구성됨
      - `Content-Type: text/html; charset-latin-1`
  - Content-Language
  - Content-Encoding
      - 해당 개체 데이터의 압축 방식
  - Content-Length
      - 전달되는 개체의 바이트 길이 또는 크기
      - 응답 메시지 본문의 길이 지정함
  - Content-Location
  - Content-Disposition
      - 응답 본문을 브라우저가 어떻게 표시해야할 지 알려줌
      - inline: 화면에 표시, attachment: 다운로드
      - `Content-Disposition: inline`
      - `Content-Disposition: attachment; filename='filename.csv'`
  - Content-Security-Policy
  - Location
      - 리소스가 리다이렉트 된 때에 이동된 주소, 또는 새로 생성된 주소를 명시함
  - Last-Modified
  - Transfer-Encoding
  
  ### HTTP 요청 헤더
  
  요청 헤더는 HTTP 요청 메시지 내에서만 나타나며 가장 크다
  
  주요 항목
  
  - Host
      - 요청하는 호스트명 및 포트번호 (필수)
      - 도메인명 및 호스트명 모두를 포함한 전체 URI 지정이 필요함
  - User-Agent
      - 클라이언트 소프트웨어 명칭 및 버전 정보
  - From
      - 클라이언트 사용자 메일 주소
  - Cookie
      - 서버에 의해 Set-Cookie로 클라이언트에게 설정된 쿠키 정보
  - Referer
      - 바로 직전에 머물렀던 웹 링크 주소
  - Authorization
      - 인증 토큰을 서버로 보낼 때 사용하는 헤더
      - “토큰 종류 + 실제 토큰 문자”를 전송
  
  요청 헤더 예시
  
  ```java
  GET /home.html HTTP/1.1
  Host: developer.mozilla.org
  User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:50.0) Gecko/20100101 Firefox/50.0
  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/ *;q=0.8
  Accept-Language: en-US,en;q=0.5
  Accept-Encoding: gzip, deflate, br
  Referer: https://developer.mozilla.org/testpage.html
  Connection: keep-alive
  Upgrade-Insecure-Requests: 1
  If-Modified-Since: Mon, 18 Jul 2016 02:36:04 GMT
  If-None-Match: "c561c68d0ba92bbeb8b0fff2a9199f722e3a621a"
  Cache-Control: max-age=0
  ```
  
  ### HTTP 응답 헤더
  
  주요 항목
  
  - Server
      - 서버 소프트웨어 정보
  - Set-Cookie
      - 서버측에서 클라이언트에게 세션 쿠키 정보를 설정
  - Expires
      - 리소스가 지정된 일시까지 캐시로써 유효함을 나타냄
      - `Expires: Thu, 26 Jul 2018 07:28:00 GMT`
  - Age
      - max-age 시간 내 얼마나 흘렀는지 초 단위로 알려줌
  - Access-Control-Allow-Origin
      - 요청을 보내는 프론트 주소와 백엔드 주소가 다르면 CORS 에러 발생
          - 서버에서 이 헤더에 프론트 주소를 적어 주어야 에러가 발생하지 않음
      - 프로토콜, 서브도메인, 도메인, 포트 중 하나만 달라도 에러가 발생함
  
  예시
  
  ```java
  200 OK
  Access-Control-Allow-Origin: *
  Connection: Keep-Alive
  Content-Encoding: gzip
  Content-Type: text/html; charset=utf-8
  Date: Mon, 18 Jul 2016 16:06:00 GMT
  Etag: "c561c68d0ba92bbeb8b0f612a9199f722e3a621a"
  Keep-Alive: timeout=5, max=997
  Last-Modified: Mon, 18 Jul 2016 02:36:04 GMT
  Server: Apache
  Set-Cookie: mykey=myvalue; expires=Mon, 17-Jul-2017 16:06:00 GMT; Max-Age=31449600; Path=/; secure
  Transfer-Encoding: chunked
  Vary: Cookie, Accept-Encoding
  X-Backend-Server: developer2.webapp.scl3.mozilla.com
  X-Cache-Info: not cacheable; meta data too large
  X-kuma-revision: 1085259
  x-frame-options: DENY
  ```

</div>
</details>


<details>
<summary>RestAPI</summary>
<div markdown="6">   
  
  ### REST란?
  
  Representational State Transfer의 약자로서 로이 필딩의 박사학위 논문에서 최초로 소개되었다. HTTP의 주요 저자 중 하나인 그는 당시 웹의 장점을 최대한 활용할 수 있는 아키텍처로써 REST를 발표했다
  
  ### 구성
  
  - 자원(Resource) - URI
  - 행위(Verb) - HTTP Method
  - 표현(Representations)
  
  ### 특징
  
  - Uniform Interface - URI로 지정한 리소스에 대한 조작을 통일되고 한정적인 인터페이스로 수행하는 아키텍처 스타일
  - Stateless - 상태정보를 따로 저장하고 관리하지 않음. 그러므로 API 서버는 들어오는 요청만을 단순히 처리하면 됨
  - Cacheable - HTTP의 캐싱 기능 적용 가능
  - Self-descriptiveness - REST API 메시지만 보고도 이를 쉽게 이해할 수 있는 표현 구조로 되어있음
  - Client-Server - 서버는 API 제공, 클라이언트는 사용자 인증이나 컨텍스트(세션, 로그인 정보)를 직접 관리하는 구조, 서로의 역할이 명확해지고 의존성이 줄어들음
  - 계층형 구조 - 다중 계층으로 구성될 수 있으며 보안, 로드밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있음
  
  ### 디자인 가이드
  
  - URI는 정보의 자원을 표현해야 함
  - 자원에 대한 행위는 HTTP Method로 표현함
  
  ```java
  GET /members/show/1     (x)
  GET /members/1          (o)
  ```
  
  - 슬래시 구분자(/)는 계층 관계를 나타내는 데 사용
  
  ```java
  http://restapi.example.com/houses/apartments
  http://restapi.example.com/animals/mammals/whales
  ```
  
  - 리소스 간 관계 표현
  
  ```java
  GET : /users/{userid}/devices (일반적으로 소유 ‘has’의 관계를 표현할 때)
  ```
  
  - 자원을 표현하는 Collection, Document
      - Collection은 문서들의 집합, 객체들의 집합 → 복수로 사용
      - Document는 단순 문서, 하나의 객체 → 단수로 사용
  
  ```java
  http:// restapi.example.com/sports/soccer/players/13
  ```
  
  sports, players → Collection (복수)
  
  soccer, 13 → Document (단수)

</div>
</details>
