> HTTP에 대한 내용을 정리한 글입니다.

# 인터넷 네트워크

## 인터넷 통신

![image](https://github.com/Hoya324/SpringNote/assets/96857599/6aaa0df4-661f-40cf-8c9e-6d98d263551b)

- 클라이언트가 서버로 요청을 보내는 방식을 알기 위해서는 IP를 이해해야한다.

## IP(인터넷 프로토콜)

### IP의 역할
- 지정한 IP 주소(IP Address)에 데이터 전달.
- 패킷(Packet)이라는 통신 단위로 데이터 전달

### IP를 통한 통신 과정
1. 클라이언트가 IP를 가진다.
2. 서버에도 IP를 가진다.
3. 메세지를 그냥 전달하는 것이 아니라 IP 패킷이라는 규칙을 통해 전달한다.

![image](https://github.com/Hoya324/SpringNote/assets/96857599/40ee9db2-c2d1-442b-9f3b-4233dd24cdd2)

4. 클라이언트 패킷 전달. (출발, 목적 IP와 메세지를 전달한다.)

![image](https://github.com/Hoya324/SpringNote/assets/96857599/86731f4c-cdb3-4c50-898d-4c31d79fcfda)

5. 서버 패킷 전달. (출발, 목적 IP와 메세지를 전달한다.)

![image](https://github.com/Hoya324/SpringNote/assets/96857599/45dccce3-0bfe-4ea9-b297-b99d6dfcb33a)

> 클라이언트 -> 서버의 노드와 서버 -> 클라이언트의 전달 노드가 다를 수 있다.
> 

### IP 프로토콜의 한계

**비연결성**
- 패킷을 받을 대상이 없거나 서비스 불능 상태여도 패킷 전송 (받을 대상의 컴퓨터가 꺼져있거나 받을 수 없는 상태여도 일단 보낸다는 뜻)

**비신뢰성**
- 통신 중간에 패킷의 소실 문제
- 패킷의 도달 순서가 달라지는 문제

**프로그램 구분**
- 같은 IP를 사용하는 서버에서 통신하는 애플리케이션이 둘 이상일 때 발생하는 문제

> 이와 같은 IP 프로토콜의 한계를 보완해주는 것이 TCP.

## TCP

### 인터넷 프로토콜 스택의 4계층

<img width="358" alt="스크린샷 2023-07-29 오전 11 22 02" src="https://github.com/Hoya324/SpringNote/assets/96857599/fe6325b9-ee71-423e-964a-2bd68c93de8a">

### 프로토콜 계층

<img width="522" alt="스크린샷 2023-07-29 오전 11 22 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/1fe46963-0a8b-4b44-bb90-2c13452e2741">

<img width="585" alt="스크린샷 2023-07-29 오전 11 22 53" src="https://github.com/Hoya324/SpringNote/assets/96857599/2ce6490d-9871-4cb4-a00f-4bc318733bd1">

### IP 패킷과 TCP/IP 패킷 정보의 차이

**IP 패킷 정보**
- 출발지 IP, 목적지 IP, 등을 가짐
- 패킷(packet:소포)

<img width="547" alt="스크린샷 2023-07-29 오전 11 25 39" src="https://github.com/Hoya324/SpringNote/assets/96857599/83be6ce3-9fe6-48fc-aedb-38429935e0d7">

**TCP/IP 패킷 정보**
- 출발지 PORT, 목적지 PORT 전송 제어, 순서, 검증 정보 등
- 순서 제어 문제 해결

<img width="541" alt="스크린샷 2023-07-29 오전 11 26 22" src="https://github.com/Hoya324/SpringNote/assets/96857599/3031d0c2-d704-4ff8-9b63-1715e4f53093">

### TCP(Transmission Control Protocol/전송 제어 프로토콜) 특징
- 연결지향 -> TCP 3 way handshake (가상 연결)
- 데이터 전달 보증
- 순서 보장
- 신뢰할 수 있는 프로토콜
- 현재는 대부분 TCP 사용

**1. TCP 3 way handshake**

<img width="599" alt="스크린샷 2023-07-29 오후 3 35 47" src="https://github.com/Hoya324/SpringNote/assets/96857599/a28c1480-a032-48d4-954d-3066586f28ee">

- 상대가 접속이 가능한 상태인지 확인하고 응답을 보낼지 말지 결정함.
- 가상 연결 -> 물리적인 연결이 아닌, 논리적인 연결임.

**2. 데이터 전달 보증**

<img width="513" alt="스크린샷 2023-07-29 오후 3 36 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/6ab0da61-a9ea-4571-9d75-e9232be83767">

- 수신측에 TCP 세그먼트가 도착하면 수신 측은 송신 측에게 도착했다고 알림. 이것을 ACK라고 함.
- TCP 헤더에 ACK 관련 정보를 넣은 TCP 세그먼트를 반환함.

**3. 순서 보장**

<img width="519" alt="스크린샷 2023-07-29 오후 3 38 52" src="https://github.com/Hoya324/SpringNote/assets/96857599/ad63d0b6-9bf4-4e0e-afef-9b6b7825cfc9">

- 순서가 잘못된 경우 순서를 맞춰서 다시 보낼 수 있도록 함.
- TCP에 출발지 PORT, 목적지 PORT 전송 제어, 순서, 검증 정보 등이 있기 때문에 가능함.
- 데이터 순서를 보증하기 위해서 TCP 세그먼트에 **시퀀스(Sequence)번호**를 붙임.
- 시퀀스 번호도 TCP헤더에 기록되며, 해당 세그먼트가 가지고 있는 데이터가 전송 데이터 전체 중 몇 바이트째부터 시작하는 부분인지를 가리키고 있음.

## UDP(User Datagram Protocol/사용자 데이터그램 프로토콜)

- 하얀 도화지에 비유(기능이 거의 없음)
- 연결지향 - TCP 3 way handshake X
- 데이터 전달 보증 X
- 순서 보장 X
- 데이터 전달 및 순서가 보장되지 않지만, 단순하고 빠름 정리

### 정리
- IP와 거의 같다. PORT, 체크섬 정도만 추가
- 애플리케이션에서 추가 작업 필요

**UDP 사용 이유**
- TCP는 데이터 양도 많고, 속도도 느림(3 way handshake) -> TCP는 변경하기 힘듦
- 더 최적화하기 위해 사용하기 좋음 -> 최근 각광 받기 시작

## PORT

> 같은 IP 서버에 여러 작업을 연결하면 어떻게 구분할 수 있을까?

- 각 IP에서 요청을 보낼 때 패킷에 출발 PORT와 도착 PORT를 저장해서 보내게 됨
- 각 PORT에 맞게 요청-응답

>  비유 IP: 아파트 PORT: 호수

<img width="535" alt="스크린샷 2023-07-29 오후 3 46 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/19479633-b182-45aa-ae15-1de2e74294f8">

### PORT 번호
- 0 ~ 65535 할당 가능
- 0 ~ 1023: 잘 알려진 포트, 사용하지 않는 것이 좋음
  - FTP - 20, 21
  - TELNET - 23
  - HTTP - 80
  - HTTPS - 443
 
## DNS(Domain Name System/도메인 네임 시스템)
- 전화번호부와 같은 느낌
- 도메인 명을 IP 주소로 변환

### IP의 단점
- 기억하기 어렵다.
- 변경될 수 있다.

### DNS 사용
- 도메인을 구매 등으로 획득
- 도메인 명을 IP와 연결
- IP가 바뀌면 바뀐 IP를 도메인에 다시 연결

<img width="598" alt="스크린샷 2023-07-29 오후 3 55 30" src="https://github.com/Hoya324/SpringNote/assets/96857599/51b296bd-c7b0-4979-83f0-1c62b5ac641a">

### DNS의 기본적인 동작 방식

<img width="815" alt="스크린샷 2023-08-02 오후 3 22 11" src="https://github.com/Hoya324/COW-Spring-1/assets/96857599/3c5c3125-6b72-466f-b59a-387023bcc9ee">

1. 사용자가 브라우저의 주소창에 도메인 주소를 입력한다.
2. 브라우저는 이 도메인 주소의 실제 IP주소를 알아내기 위해서 DNS 서버에 요청한다.
3. DNS 서버는 해당 도메인 주소에 맵핑되는 IP주소를 돌려준다.
4. 브라우저는 돌려받은 IP주소를 통해 실제 서버에 페이지를 요청한다.
5. 서버는 요청한 페이지를 응답한다.

### DNS 서버 캐시
- 자주 사용하는 페이지들은 캐시로 저장해둔다.
- 매번 입력할 때마다 DNS 서버에 요청하여 돌려받는 것은 비효율적이기 때문이다.
- 따라서 로컬 저장소에 최근 접속한 페이지의 IP주소를 도메인 주소와 매핑시켜서 저장시켜 놓는다.
- 브라우저에서 요청이 들어오게 되면 DNS 서버에 요청하기 전에 이미 캐싱되어 있는 도메인 주소인지 확인을 하고, 있다면 바로 가져와서 IP주소로 반환시켜 준다.

### IP 주소를 조사하는 방법 - DNS Resolver

<img width="695" alt="스크린샷 2023-08-02 오후 3 29 41" src="https://github.com/Hoya324/COW-Spring-1/assets/96857599/123b9a7f-68cf-4c9d-83b3-add3067fff4a">

Resolver는 DNS 서버에 대한 클라이언트로 동작한다.

1. Resolver는 DNS 서버에 조회 메시지를 보내며
2. 거기에 반송되는 응답 메시지를 클라이언트에게 전달한다.

- 이에, DNS Resolver는 Name Resolution을 실행하는 역할을 수행한다고도 말할 수 있다.

> **Name Resolution**: DNS 원리를 사용하여 IP 주소를 조사하는 것
 
> Resolver의 실체는 Socket 라이브러리에 들어있는 부품화한 프로그램이다. 이를 통해 애플리케이션에서 간단히 Resolver를 호출하여 이용할 수 있다.
> 도메인명에서 IP 주소를 조사할 때 브라우저는 Socket 라이브러리의 Resolver를 이용한다.

**Socket 라이브러리**
- 네트워크의 기능을 호출하기 위한 프로그램 부품집이며 사전에 만들어진 부품을 통해 프로그래밍 작업 수고를 덜 수 있다.

## HTTP

### HTTP 메시지에 모든 것을 전송
- HTML, TEXT
- IMAGE, 음성, 영상, 파일
- JSON, XML (API)
- 거의 모든 형태의 데이터 전송 가능
- 서버간에 데이터를 주고 받을 때도 대부분 HTTP 사용

### HTTP 역사
- HTTP/0.9 1991년: GET 메서드만 지원, HTTP 헤더X
- HTTP/1.0 1996년: 메서드, 헤더 추가
- HTTP/1.1 1997년: 가장 많이 사용, 우리에게 가장 중요한 버전
  - RFC2068 (1997) -> RFC2616 (1999) -> RFC7230~7235 (2014)
- HTTP/2 2015년: 성능 개선
- HTTP/3 진행중: TCP 대신에 UDP 사용, 성능 개선

### HTTP/2
- HTTP 1.1의 파이프라이닝은 앞의 요청이 완료되기 이전에 요청을 연속적으로 보낼 수 있게 해준다.
- 이런 방식은 3개의 요청이 있을 때, 두번째, 세번째 요청이 아무리 일찍 끝나도 HTTP 요청은 순차적이기 때문에 첫번째 요청의 응답이 오기 전까진 블로킹된다.
- 이를 Head Of line blocking(HOL Blocking)이라고 한다.

**Binary Framing Layer**

<img width="649" alt="스크린샷 2023-08-02 오후 3 39 07" src="https://github.com/Hoya324/COW-Spring-1/assets/96857599/65267f21-6851-428f-9781-0326951b18cc">

- HTTP/2는 HOL Blocking을 클라이언트와 서버 간의 HTTP 메시지를 캡슐화하고 전송하는 방법을 위한 새로운 계층을 통해 해결했다.

### HTTP/2의 성능 개선
- 하나의 요청을 차단하지 않고 여러 요청을 병렬로 인터리브 할 수 있다.
	- **인터리브**: 한 요청의 처리과정에 다른 요청을 끼워넣을 수 있다는 의미
- 하나의 응답을 차단하지 않고 여러 응답을 병렬로 인터리브 할 수 있다.
- 단일 연결로 여러 요청과 응답을 병렬 전달한다.
- 이미지 스프라이트, 도메인 샤딩등의 HTTP/1.x의 불필요한 최적화 기능을 삭제한다.
- 불필요한 지연시간을 방지하고 네트워크 용량을 개선해서 페이지 로드 시간을 단축한다.
- 이 외에도 Head Of Line Blocking 문제 또한 해결되어 병렬 처리를 위해 여러 TCP 연결이 필요하지 않게 되었다. 그 결과 어플리케이션을 더욱 빠르고 간단하고, 비용은 적게 만들 수 있게 됐다.

### 기반 프로토콜
- TCP: HTTP/1.1, HTTP/2
- UDP: HTTP/3
- 현재 HTTP/1.1 주로 사용
  - HTTP/2, HTTP/3 도 점점 증가

### HTTP 특징
- 클라이언트 서버 구조
- 무상태 프로토콜(스테이스리스), 비연결성
- HTTP 메시지
- 단순함, 확장 가능

### 클라이언트 서버 구조
- Request Response 구조
- 클라이언트는 서버에 요청을 보내고, 응답을 대기
- 서버가 요청에 대한 결과를 만들어서 응답

<img width="487" alt="스크린샷 2023-07-29 오후 4 07 08" src="https://github.com/Hoya324/SpringNote/assets/96857599/33409fbd-3855-4bd0-b16f-1caaf5a61edc">

### 무상태(Stateless) 프로토콜
- 서버가 클라이언트의 상태를 보존X
- 장점: 서버 확장성 높음(스케일 아웃)
- 단점: 클라이언트가 추가 데이터 전송

**상태유지(Stateful) 예시**

고객: 이 노트북 얼마인가요? 
점원A: 100만원 입니다.

고객: 2개 구매하겠습니다.
점원B: ? 무엇을 2개 구매하시겠어요?

고객: 신용카드로 구매하겠습니다.
점원C: ? 무슨 제품을 몇 개 신용카드로 구매하시겠어요?

- 위의 예시처럼 상태유지의 경우 중간에 점원(서버)이 바뀐다면 문제가 발생한다.
- 즉, 중간에 서버에 장애가 생겨 서버를 교체해야한다면 문제가 생긴다.

**무상태(Stateless) 예시**

고객: 이 노트북 얼마인가요? 
점원A: 100만원 입니다.

고객: 노트북 2개 구매하겠습니다.
점원B: 노트북 2개는 200만원 입니다. 신용카드, 현금중에 어떤 걸로 구매 하시겠어요?

고객: 노트북 2개를 신용카드로 구매하겠습니다. 
점원C: 200만원 결제 완료되었습니다.

- 무상태의 경우 중간에 점원(서버)이 바뀌어도 된다.
- 즉, 중간에 서버에 장애가 생기더라도 아무 서버나 호출할 수 있다.

### Stateful, Stateless 차이 정리
- **상태 유지**: 중간에 다른 점원으로 바뀌면 안된다. 
(중간에 다른 점원으로 바뀔 때 상태 정보를 다른 점원에게 미리 알려줘야 한다.)
  - 서버가 클라이언트의 이전 상태를 유지, 보존해주는 것

- **무상태**: 중간에 다른 점원으로 바뀌어도 된다.
  - 갑자기 고객이 증가해도 점원을 대거 투입할 수 있다.
  - 갑자기 클라이언트 요청이 증가해도 서버를 대거 투입할 수 있다.
  - 무상태는 응답 서버를 쉽게 바꿀 수 있다. -> 무한한 서버 증설 가능

### Stateless 실무 한계
- 모든 것을 무상태로 설계 할 수 있는 경우도 있고 없는 경우도 있다.
- 무상태
  - 예) 로그인이 필요 없는 단순한 서비스 소개 화면
- 상태 유지
  - 예) 로그인
- 로그인한 사용자의 경우 로그인 했다는 상태를 서버에 유지
- 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서
- 상태 유지 상태 유지는 최소한만 사용
- 데이터를 너무 많이 보냄

### 비 연결성(connectionless)

**연결을 유지하는 모델**
- TCP/IP 연결의 경우 기본적으로 요청과 응답 후에 연결을 유지함.

<img width="566" alt="스크린샷 2023-07-29 오후 4 28 05" src="https://github.com/Hoya324/SpringNote/assets/96857599/569fb34c-503f-451e-9d5d-94ab43b60b79">

- 클라이언트2가 연결을 해도 클라이언트1과의 연결은 유지됨.

<img width="576" alt="스크린샷 2023-07-29 오후 4 28 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/c62f03b1-c41e-475c-81ad-bf622e7d4e94">

- 클라이언트3을 연결할 때에도 클라이언트 1, 2의 연결은 유지되므로 서버 자원을 소모하게 됨.

<img width="607" alt="스크린샷 2023-07-29 오후 4 29 39" src="https://github.com/Hoya324/SpringNote/assets/96857599/7a2e3cc8-1f01-49e2-b4ed-6dd4ec6145b3">

- 재연결

<img width="604" alt="스크린샷 2023-07-29 오후 4 29 50" src="https://github.com/Hoya324/SpringNote/assets/96857599/7424c216-c008-4c12-93f2-0e03fe5561dc">

**연결을 유지하지 않는 모델**

- TCP/IP 연결 후 필요 없어진 연결은 끊어짐

<img width="557" alt="스크린샷 2023-07-29 오후 4 30 30" src="https://github.com/Hoya324/SpringNote/assets/96857599/f14a6301-52d4-4344-a258-fe34f2861361">

- 필요할 때 연결하고, 응답후 끊어지면 서버의 자원을 최소한으로 사용함.

<img width="548" alt="스크린샷 2023-07-29 오후 4 30 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/632037e1-b4b7-4cc8-8a86-32c7998e4a63">

<img width="554" alt="스크린샷 2023-07-29 오후 4 30 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/b2920d01-de5b-457d-aa9c-5b9591eb3989">

<img width="594" alt="스크린샷 2023-07-29 오후 4 30 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/35a20e63-8164-4e18-852b-cb51d6001f5c">

<img width="600" alt="스크린샷 2023-07-29 오후 4 31 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/a19484ab-fa20-48f3-8cfe-d44ad262ee63">

**비 연결성**
- HTTP는 기본이 연결을 유지하지 않는 모델
- 일반적으로 초 단위의 이하의 빠른 속도로 응답
- 1시간 동안 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음
  - 예) 웹 브라우저에서 계속 연속해서 검색 버튼을 누르지는 않는다.
- 서버 자원을 매우 효율적으로 사용할 수 있음

**비 연결성의 한계와 극복**
- 한계
  - TCP/IP 연결을 새로 맺어야 함 - 3 way handshake 시간 추가(연결, 종료 낭비)
  - 웹 브라우저로 사이트를 요청하면 HTML 뿐만 아니라 자바스크립트, css, 추가 이미지 등등 수 많은 자원이 함께 다운로드
- 극복
  - 지금은 HTTP 지속 연결(Persistent Connections)로 문제 해결
  - HTTP/2, HTTP/3에서 더 많은 최적화
 
<img width="548" alt="스크린샷 2023-07-29 오후 4 41 24" src="https://github.com/Hoya324/SpringNote/assets/96857599/d052f5e0-b39c-4df5-a858-7c94be737422">

<img width="561" alt="스크린샷 2023-07-29 오후 4 41 41" src="https://github.com/Hoya324/SpringNote/assets/96857599/a5cd82a4-a633-4bd4-b75c-3b21c3ff3154">

**스테이스리스를 기억하자**
- 서버 개발자들이 어려워하는 업무
- 정말 같은 시간에 딱 맞추어 발생하는 대용량 트래픽
  - 예) 선착순 이벤트, 명절 KTX 예약, 학과 수업 등록
  - 예) 저녁 6:00 선착순 1000명 치킨 할인 이벤트 -> 수만명 동시 요청
-> 맨 첫 페이지에 html로만 이루어진 페이지를 두어 클라이언트들의 클릭을 분산시키는 방법이 있다.

## HTTP 메시지

<img width="607" alt="스크린샷 2023-07-29 오후 4 46 21" src="https://github.com/Hoya324/SpringNote/assets/96857599/2c9f8d60-0872-4a03-96f4-feddbda986c8">

### 시작 라인

<img width="276" alt="스크린샷 2023-07-29 오후 5 19 05" src="https://github.com/Hoya324/SpringNote/assets/96857599/da46f3f4-7ad5-44b6-9bed-45ccb3206d9c">

**요청 메시지**
- start-line = **request-line** / status-line
- **request-line** = method SP(공백) request-target SP HTTP-version CRLF(엔터)

- HTTP 메서드 (GET: 조회)
- 요청 대상 (/search?q=hello&hl=ko)
- HTTP Version

**요청 메시지 - HTTP 메서드**
- 종류: GET, POST, PUT, DELETE...
- 서버가 수행해야 할 동작 지정
  - GET: 리소스 조회
  - POST: 요청 내역 처리

**요청 메시지 - 요청 대상**
- absolute-path[?query]
- (절대경로[?쿼리]) 절대경로= "/" 로 시작하는 경로
- 참고: *, http://...?x=y 와 같이 다른 유형의 경로지정 방법도 있다.

**요청 메시지 - HTTP 버전**
- HTTP Version

**응답 메시지**
- start-line = request-line / **status-line**
- **status-line** = HTTP-version SP status-code SP reason-phrase CRLF

- HTTP 버전
- HTTP 상태 코드: 요청 성공, 실패를 나타냄
  - 200: 성공
  - 400: 클라이언트 요청 오류
  - 500: 서버 내부 오류
- 이유 문구: 사람이 이해할 수 있는 짧은 상태 코드 설명 글

### HTTP 헤더
- header-field = field-name ":" OWS field-value OWS (OWS: 띄어쓰기 허용)
- field-name은 대소문자 구문 없음

<img width="552" alt="스크린샷 2023-07-29 오후 5 18 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/e030a0dd-477c-412c-8c86-533bc39f8361">

**용도**
- HTTP 전송에 필요한 모든 부가정보
- 예) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...
- 표준 헤더가 너무 많음
  - [List_of_HTTP_header_fields](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields)
- 필요시 임의의 헤더 추가 가능
  - helloworld: hihi

### HTTP 메시지 바디

<img width="278" alt="스크린샷 2023-07-29 오후 5 25 27" src="https://github.com/Hoya324/SpringNote/assets/96857599/da404465-98bf-4403-876a-4c37a7d5df30">

**용도**
- 실제 전송할 데이터
- HTML 문서, 이미지, 영상, JSON 등등 byte로 표현할 수 있는 모든 데이터 전송 가능

## HTTP 메서드

> HTTP API를 만들어보자(좋은 URI 설계는 무엇인가)

**요구사항**
- 회원 정보 관리 API를 만들어라.
	- 회원 목록 조회 
	- 회원 조회
	- 회원 등록
	- 회원 수정
	- 회원 삭제

### API URI 설계
- URI(Uniform Resource Identifier)
	- 회원 목록 조회 `/read-member-list`
	- 회원 조회 `/read-member-by-id` 
	- 회원 등록 `/create-member`
	- 회원 수정 `/update-member`
	- 회원 삭제 `/delete-member`
	
### 이것은 좋은 URI 설계일까?
- **가장 중요한 것은 리소스를 식별**하는 것이다.
 
### API URI(Uniform Resource Identifier) 고민
- 리소스의 의미는 뭘까?
	- 회원을 등록하고 수정하고 조회하는게 리소스가 아니다!
	- 예) 미네랄을 캐라 -> 미네랄이 리소스
	- 회원이라는 **개념 자체**가 바로 리소스다.
- **리소스를 어떻게 식별하는게 좋을까?**
	- 회원을 등록하고 수정하고 조회하는 것을 모두 배제
	- **회원이라는 리소스만 식별**하면 된다. -> 회원 리소스를 URI에 매핑

### API URI 설계
**리소스 식별, URI 계층 구조 활용**

- **회원** 목록 조회 `/members`
- **회원** 조회 `/members/{id}` -> 어떻게 구분하지? 
- **회원** 등록 `/members/{id}` -> 어떻게 구분하지? 
- **회원** 수정 `/members/{id}` -> 어떻게 구분하지? 
- **회원** 삭제 `/members/{id}` -> 어떻게 구분하지?

> 참고: 계층 구조상 상위를 컬렉션으로 보고 복수단어 사용 권장(member -> members)

**리소스와 행위를 분리**
가장 중요한 것은 리소스를 식별하는 것

- **URI는 리소스만 식별!**
- **리소스**와 해당 리소스를 대상으로 하는 **행위**을 분리
	- 리소스: 회원
	- 행위: 조회, 등록, 삭제, 변경
- 리소스는 명사, 행위는 동사 (미네랄을 캐라) 
- 행위(메서드)는 어떻게 구분할까?

### HTTP 메서드 - GET, POST

> Representation 설명 전

**주요 메서드**
- GET: 리소스 조회
- POST: 요청 데이터 처리, 주로 등록에 사용 
- PUT: 리소스를 대체, 해당 리소스가 없으면 생성 
- PATCH: 리소스 부분 변경
- DELETE: 리소스 삭제

**기타 메서드**
- HEAD: GET과 동일하지만 메시지 부분을 제외하고, 상태 줄과 헤더만 반환
- OPTIONS: 대상 리소스에 대한 통신 가능 옵션(메서드)을 설명(주로 CORS에서 사용) 
- CONNECT: 대상 자원으로 식별되는 서버에 대한 터널을 설정
- TRACE: 대상 리소스에 대한 경로를 따라 메시지 루프백 테스트를 수행


### GET

<img width="367" alt="스크린샷 2023-07-29 오후 5 36 03" src="https://github.com/Hoya324/SpringNote/assets/96857599/fd603fc4-a08f-4c91-981c-905f2a0b289c">

- 리소스 조회
- 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)를 통해서 전달
- 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않음

**리소스 조회1 - 메시지 전달**

<img width="625" alt="스크린샷 2023-07-29 오후 5 36 36" src="https://github.com/Hoya324/SpringNote/assets/96857599/42797d07-9e02-4e1d-82f6-957403fce976">

**리소스 조회2 - 서버도착**

<img width="608" alt="스크린샷 2023-07-29 오후 5 36 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/21b4a3de-d197-4018-8465-15e520b9d8e4">

**리소스 조회3 - 응답 데이터**

<img width="593" alt="스크린샷 2023-07-29 오후 5 37 09" src="https://github.com/Hoya324/SpringNote/assets/96857599/1496454e-50e3-425c-b378-fea1c8c470bd">

### POST

**클라이언트 측에서 데이터를 전달하여 처리를 요청하는 것**

<img width="247" alt="스크린샷 2023-07-29 오후 5 37 35" src="https://github.com/Hoya324/SpringNote/assets/96857599/11284cc0-8f73-4e81-8c4a-8cad28e56318">

- 요청 데이터 처리
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 요청 데이터를 처리
  - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다.
- 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용

**메시지 등록 - `/members`로 오면 전달된 데이터를 어떤 방식으로 처리할지 미리 약속함.**

<img width="561" alt="스크린샷 2023-07-29 오후 5 39 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/bae63814-29d7-48cd-a7c7-a209dba81274">

**신규 리소스 생성 - 신규 리소스 식별자 생성**

<img width="629" alt="스크린샷 2023-07-29 오후 5 40 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/9ae0df40-284c-4dce-9512-220dc21a168e">

**응답 데이터**

<img width="611" alt="스크린샷 2023-07-29 오후 5 40 53" src="https://github.com/Hoya324/SpringNote/assets/96857599/9dd77a7f-b20c-4217-9b90-52acd56d561f">

### 요청 데이터를 어떻게 처리한다는 뜻일까? 
예시)
- 스펙: **POST 메서드**는 대상 리소스가 리소스의 고유 한 의미 체계에 따라 **요청에 포함 된 표현을 처리**하도록 요청합니다. (구글 번역) 
- 예를 들어 POST는 다음과 같은 기능에 사용됩니다.
	- HTML 양식에 입력 된 필드와 같은 데이터 블록을 데이터 처리 프로세스에 제공	
		- 예) HTML FORM에 입력한 정보로 **회원 가입, 주문** 등에서 사용
	- 게시판, 뉴스 그룹, 메일링 리스트, 블로그 또는 유사한 기사 그룹에 메시지 게시
		- 예) **게시판 글쓰기, 댓글 달기**
	- 서버가 아직 식별하지 않은 새 리소스 생성
		- 예) **신규 주문 생성**
	- 기존 자원에 데이터 추가
		- 예) **한 문서 끝에 내용 추가**하기
- 정리
  - 이 리소스 URI에 POST 요청이 오면 요청 데이터를 어떻게 처리할지 리소스마다 따로 정해야 함 -> 정해진 것이 없음


### POST 정리
1. **새 리소스 생성(등록)**
	- 서버가 아직 식별하지 않은 새 리소스 생성
2. **요청 데이터 처리**
	- 단순히 데이터를 생성하거나, 변경하는 것을 넘어서 프로세스를 처리해야 하는 경우
	  - 예) 주문에서 결제완료 -> 배달시작 -> 배달완료 처럼 단순히 값 변경을 넘어 프로세스의 상태가 변경되는 경우 
	- POST의 결과로 새로운 리소스가 생성되지 않을 수도 있음
	  - 예) POST /orders/{orderId}/start-delivery (컨트롤 URI)
3. **다른 메서드로 처리하기 애매한 경우**
	- 예) JSON으로 조회 데이터를 넘겨야 하는데, GET 메서드를 사용하기 어려운 경우 
	- 애매하면 POST

### HTTP 메서드 - PUT, PATCH, DELETE

### PUT

<img width="239" alt="스크린샷 2023-07-29 오후 5 49 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/c9974401-5330-40de-b18c-9f3bd89f3aef">

- **리소스를 완전히 대체**
	- 리소스가 있으면 대체 
	- 리소스가 없으면 생성 
	- 쉽게 이야기해서 덮어버림
- **중요! 클라이언트가 리소스를 식별**
	- 클라이언트가 리소스 위치를 알고 URI 지정 -> 구체적인 리소스를 지정한다는 점에서 POST와의 차이를 보임
	- POST와 차이점

### PATCH

<img width="222" alt="스크린샷 2023-07-29 오후 5 51 38" src="https://github.com/Hoya324/SpringNote/assets/96857599/a370f015-c83b-4e4d-a987-e4ddb294dbb8">

- 리소스를 부분 변경하는 것
- 특정 HTTP가 PATCH를 못 받아들이는 경우 POST를 사용하면 됨

### DELETE

<img width="222" alt="스크린샷 2023-07-29 오후 5 52 22" src="https://github.com/Hoya324/SpringNote/assets/96857599/3b642232-2db3-4fdf-9973-d36966fe6520">

- 리소스 제거

### HTTP 메서드의 속성
- **안전(Safe Methods)**
- **멱등(Idempotent Methods)**
- **캐시가능(Cacheable Methods)**

![image](https://github.com/Hoya324/SpringNote/assets/96857599/f05d43e6-3131-443c-a53a-1367c6db4524)

### 안전(Safe)
- 호출해도 리소스를 변경하지 않는다. (예: GET, HEAD)
- Q: 그래도 계속 호출해서, 로그 같은게 쌓여서 장애가 발생하면요?
- A: 안전은 해당 리소스만 고려한다. 그런 부분까지 고려하지 않는다.

### 멱등(Idempotent)
- f(f(x)) = f(x)
- 한 번 호출하든 두 번 호출하든 100번 호출하든 결과가 똑같다.
- 멱등 메서드
	- **GET**: 한 번 조회하든, 두 번 조회하든 같은 결과가 조회된다.
	- **PUT**: 결과를 대체한다. 따라서 같은 요청을 여러번 해도 최종 결과는 같다. 
	- **DELETE**: 결과를 삭제한다. 같은 요청을 여러번 해도 삭제된 결과는 똑같다. 
	- **POST**: **멱등이 아니다!** 두 번 호출하면 같은 결제가 중복해서 발생할 수 있다.

- Q: 재요청 중간에 다른 곳에서 리소스를 변경해버리면?
	- 사용자1: GET -> username:A, age:20
	- 사용자2: PUT -> username:A, age:30
	- 사용자1: GET -> username:A, age:30 -> 사용자2의 영향으로 바뀐 데이터 조회
- **A: 멱등은 외부 요인으로 중간에 리소스가 변경되는 것 까지는 고려하지는 않는다.**

**활용**
- 자동 복구 메커니즘
- 서버가 TIMEOUT 등으로 정상 응답을 못 주었을 때, 클라이언트가 같은 요청을 다시 해도 되는가? 판단 근거

### 캐시가능(Cacheable) 
- 응답 결과 리소스를 캐시해서 사용해도 되는가? 
- GET, HEAD, POST, PATCH 캐시가능
- 실제로는 GET, HEAD 정도만 캐시로 사용(URL만 키로 고려하면 되기 때문에 간단함)
	- POST, PATCH는 본문 내용까지 캐시 키로 고려해야 하는데, 구현이 쉽지 않음

## HTTP 메서드 활용

### 클라이언트에서 서버로 데이터 전송

**데이터 전달 방식 2가지**
- **쿼리 파라미터를 통한 데이터 전송**
	- GET
	- 주로 정렬 필터(검색어)
- **메시지 바디를 통한 데이터 전송**
	- POST, PUT, PATCH
	- 회원 가입, 상품 주문, 리소스 등록, 리소스 변경

**4가지 상황**
- **정적 데이터 조회**
	- 이미지, 정적 텍스트 문서 
- **동적 데이터 조회**
	- 주로 검색, 게시판 목록에서 정렬 필터(검색어) 
- **HTML Form을 통한 데이터 전송**
	- 회원 가입, 상품 주문, 데이터 변경 
- **HTTP API를 통한 데이터 전송**
	- 회원 가입, 상품 주문, 데이터 변경
	- 서버 to 서버, 앱 클라이언트, 웹 클라이언트(Ajax)

### 정적 데이터 조회
- 쿼리 파라미터 미사용

<img width="627" alt="스크린샷 2023-07-30 오후 12 51 12" src="https://github.com/Hoya324/SpringNote/assets/96857599/e0dafca4-4372-4a2f-a08f-97fa6702f294">

**정리**
- 이미지, 정적 텍스트 문서
- 조회는 GET 사용
- 정적 데이터는 일반적으로 쿼리 파라미터 없이 리소스 경로로 단순하게 조회 가능

### 동적 데이터 조회
- 쿼리 파라미터 사용

<img width="519" alt="스크린샷 2023-07-30 오후 12 52 38" src="https://github.com/Hoya324/SpringNote/assets/96857599/eaaa0aae-0013-4ab6-9db0-61d29788b61d">

**정리**
- 주로 검색, 게시판 목록에서 정렬 필터(검색어)
- 조회 조건을 줄여주는 필터, 조회 결과를 정렬하는 정렬 조건에 주로 사용 
- 조회는 GET 사용
- GET은 쿼리 파라미터 사용해서 데이터를 전달

### HTML Form 데이터 전송
- POST 전송 -> 저장

<img width="546" alt="스크린샷 2023-07-30 오후 12 53 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/00905558-0283-4418-bab1-911189d6917e">

- GET 전송 -> 저장

<img width="542" alt="스크린샷 2023-07-30 오후 12 53 35" src="https://github.com/Hoya324/SpringNote/assets/96857599/35dd84ef-f1d2-43b5-947d-d046d2598eeb">

- GET 전송 -> 조회

<img width="526" alt="스크린샷 2023-07-30 오후 12 53 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/885220a4-b1c9-4cc5-8a60-d6ee6c8e816d">

> 주의! GET은 조회에만 사용!
> 
> 리소스 변경이 발생하는 곳에 사용하면 안됨!

- multipart/form-data (주로 바이너리 데이터를 전송할 때 사용)

<img width="643" alt="스크린샷 2023-07-30 오후 12 54 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/1f85b67e-1db3-4dd2-9d31-bc5478d09cb2">

**정리**

- HTML Form submit시 POST 전송
	- 예) 회원 가입, 상품 주문, 데이터 변경
- Content-Type: application/x-www-form-urlencoded 사용
	- form의 내용을 메시지 바디를 통해서 전송(key=value, 쿼리 파라미터 형식)
	- 전송 데이터를 url encoding 처리
		- 예) abc김 -> abc%EA%B9%80 
- HTML Form은 GET 전송도 가능(쿼리 파라미터 방식으로 전송된다.)
- Content-Type: multipart/form-data
	- 파일 업로드 같은 바이너리 데이터 전송시 사용
	- 다른 종류의 여러 파일과 폼의 내용 함께 전송 가능(그래서 이름이 multipart) 

> 참고: HTML Form 전송은 **GET, POST만 지원**

### HTTP API 데이터 전송
- IOS나 안드로이드 같은 모바일 기기에서 서버에 바로 데이터를 전송해야할 때

<img width="535" alt="스크린샷 2023-07-30 오후 12 57 15" src="https://github.com/Hoya324/SpringNote/assets/96857599/f8a63f15-7ce3-4bba-95e9-69469880562f">

**정리**
- 서버 to 서버
	- 백엔드 시스템 통신 
- 앱 클라이언트
	- 아이폰, 안드로이드 
- 웹 클라이언트
	-	HTML에서 Form 전송 대신 자바 스크립트를 통한 통신에 사용(AJAX)
	-	예) React, VueJs 같은 웹 클라이언트와 API 통신 
- POST, PUT, PATCH: 메시지 바디를 통해 데이터 전송
- GET: 조회, 쿼리 파라미터로 데이터 전달
- Content-Type: application/json을 주로 사용 (사실상 표준)
	- TEXT, XML, JSON 등등 (요즘엔(2022년) JSON 많이 사용함)

### HTTP API 설계 예시

- **HTTP API - 컬렉션**
	- **POST 기반 등록**
	- 예) 회원 관리 API 제공
- **HTTP API - 스토어**
	- **PUT 기반 등록**
	- 예) 정적 컨텐츠 관리, 원격 파일 관리 
- **HTML FORM 사용**
	- 웹 페이지 회원 관리 
	- GET, POST만 지원


### 회원 관리 시스템

**API 설계 - post 기반 등록**
- **회원** 목록 /members -> **GET**
- **회원** 등록 /members -> **POST**
- **회원** 조회 /members/{id} -> **GET**
- **회원** 수정 /members/{id} -> **PATCH**(부분적인 수정이므로 가장 자주 씀), **PUT**(게시글 작성할 때), **POST** 
- **회원** 삭제 /members/{id} -> **DELETE**

**POST - 신규 자원 등록 특징**
- **클라이언트는 등록될 리소스의 URI를 모른다.(서버가 만들어준다.)**
	- 회원 등록 /members -> POST
	- POST /members
- ⭐️**서버가 새로 등록된 리소스 URI를 생성해준다.**
	- `HTTP/1.1 201 Created`<br>`Location: /members/100`
- ⭐️**컬렉션(Collection)**
	- 서버가 관리하는 리소스 디렉토리 
	- 서버가 리소스의 URI를 생성하고 관리 
	- 여기서 컬렉션은 /members

### 파일 관리 시스템

**API 설계 - PUT 기반 등록**
- **파일** 목록 /files -> **GET**
- **파일** 조회 /files/{filename} -> **GET** 
- **파일** 등록 /files/{filename} -> **PUT** 
- **파일** 삭제 /files/{filename} -> **DELETE** 
- **파일** 대량 등록 /files -> **POST**

**PUT - 신규 자원 등록 특징**

- 클라이언트가 리소스 URI를 알고 있어야 한다.
	- 파일 등록 /files/{filename} -> PUT
	- PUT **/files/star.jpg**
- ⭐️클라이언트가 직접 리소스의 URI를 지정한다.
- ⭐️**스토어(Store)**
	- 클라이언트가 관리하는 리소스 저장소 
	- 클라이언트가 리소스의 URI를 알고 관리 
	- 여기서 스토어는 /files
	
> 대부분 post 기반의 컬렉션을 사용한다.

### HTML FORM 사용

**설계 명세**
- HTML FORM은 **GET, POST만 지원**
- AJAX 같은 기술을 사용해서 해결 가능 -> 회원 API 참고 
- 여기서는 순수 HTML, HTML FORM 이야기
- GET, POST만 지원하므로 제약이 있음

**HTML FORM 사용 예시**
- **회원** 목록   /members -> **GET**
- **회원** 등록 폼 /members/new -> **GET**
- **회원** 등록   /members/new(오류시에 등록 폼과 경로를 맞추면 좋다), /members -> **POST**
- **회원** 조회   /members/{id} -> **GET**
- **회원** 수정 폼 /members/{id}/edit -> **GET**
- **회원** 수정    /members/{id}/edit, /members/{id} -> **POST**
- **회원** 삭제    /members/{id}/delete -> **POST** (DELETE 메서드를 사용하지 못하기 때문에 컨드롤 URI 사용)

**특징**
- HTML FORM은 GET, POST만 지원 
- ⭐️**컨트롤 URI** -> 최대한 리소스라는 개념을 가지고 URI를 설계하고, 안 될 때 사용한다.
	- GET, POST만 지원하므로 제약이 있음
	- 이런 제약을 해결하기 위해 동사로 된 리소스 경로 사용 (조작하는 기능을 수행하기 때문)
	- POST의 /new, /edit, /delete가 컨트롤 URI
	- HTTP 메서드로 해결하기 애매한 경우 사용(HTTP API 포함)


### 정리
- **HTTP API - 컬렉션**
	- **POST 기반 등록**
	- **서버가 리소스 URI 결정**
- **HTTP API - 스토어**
	- **PUT 기반 등록**
	- **클라이언트가 리소스 URI 결정**
- **HTML FORM 사용**
	- 순수 HTML + HTML form 사용
	- GET, POST만 지원

### [참고하면 좋은 URI 설계 개념](https://restfulapi.net/resource-naming)

- **문서(document)**
	- 단일 개념(파일 하나, 객체 인스턴스, 데이터베이스 row)
	- 예) /members/100, /files/star.jpg 
- **컬렉션(collection)**
	- 서버가 관리하는 리소스 디렉터리 
	- 서버가 리소스의 URI를 생성하고 관리 
	- 예) /members
- **스토어(store)**
	- 클라이언트가 관리하는 자원 저장소 
	- 클라이언트가 리소스의 URI를 알고 관리 
	- 예) /files
- **컨트롤러(controller), 컨트롤 URI**
	- 문서, 컬렉션, 스토어로 해결하기 어려운 추가 프로세스 실행 
	- 동사를 직접 사용
	- 예) /members/{id}/delete

## HTTP 상태코드

### HTTP 상태코드 소개
 
**상태 코드 - 클라이언트가 보낸 요청의 처리 상태를 응답에서 알려주는 기능**
- **1xx (Informational)**: 요청이 수신되어 처리중
- **2xx (Successful)**: 요청 정상 처리
- **3xx (Redirection)**: 요청을 완료하려면 추가 행동이 필요
- **4xx (Client Error)**: 클라이언트 오류, 잘못된 문법등으로 서버가 요청을 수행할 수 없음 
- **5xx (Server Error)**: 서버 오류, 서버가 정상 요청을 처리하지 못함

**만약 모르는 상태 코드가 나타나면?**

- 클라이언트가 인식할 수 없는 상태코드를 서버가 반환하면? 
- 클라이언트는 상위 상태코드로 해석해서 처리
- 미래에 새로운 상태 코드가 추가되어도 클라이언트를 변경하지 않아도 됨 
- 예)
	- 299 ??? -> 2xx (Successful) 
	- 451 ??? -> 4xx (Client Error) 
	- 599 ??? -> 5xx (Server Error)

### 1xx (Informational)
-> 요청이 수신되어 처리중

- 거의 사용하지 않으므로 생략

### 2xx - 성공
-> 클라이언트의 요청을 성공적으로 처리

- 프로젝트를 진행할 때, 200 또는 201까지만 정해두고 사용하느 경우가 많다. 
- 각 팀에서 범위를 정해서 사용하는 것이 좋다. (너무 양이 많기 때문)

**200 OK - 요청성공**

<img width="562" alt="스크린샷 2023-07-30 오후 1 21 26" src="https://github.com/Hoya324/SpringNote/assets/96857599/1ef35af7-31a0-4bce-9df5-b56330751a66">

**201 Created**
- POST 요청이므로 서버에서 자원을 생성하고, 자원에 대한 URI도 관리

<img width="641" alt="스크린샷 2023-07-30 오후 1 22 08" src="https://github.com/Hoya324/SpringNote/assets/96857599/951110d4-fcf1-4e9d-82fb-346e6aa52f37">

**202 Accepted**
-> 요청이 접수되었으나 처리가 완료되지 않았음
- 배치 처리 같은 곳에서 사용
- 예) 요청 접수 후 1시간 뒤에 배치 프로세스가 요청을 처리함

**204 No Content**
-> 서버가 요청을 성공적으로 수행했지만, 응답 페이로드 본문에 보낼 데이터가 없음

- 예) 웹 문서 편집기에서 save 버튼
- save 버튼의 결과로 아무 내용이 없어도 된다.
- save 버튼을 눌러도 같은 화면을 유지해야 한다.
- 결과 내용이 없어도 204 메시지(2xx)만으로 성공을 인식할 수 있다.

### 3xx - 리다이렉션
-> 요청을 완료하기 위해 유저 에이전트(웹 브라우저)의 추가 조치 필요

- 300 Multiple Choices 
- 301 Moved Permanently 
- 302 Found
- 303 See Other
- 304 Not Modified
- 307 Temporary Redirect 
- 308 Permanent Redirect

**리다이렉션 이해**
- 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동 (리다이렉트)

**자동 리다이렉트 흐름**

<img width="559" alt="스크린샷 2023-07-30 오후 1 25 24" src="https://github.com/Hoya324/SpringNote/assets/96857599/a4298d99-5753-4fa1-9ecb-437a59454c3c">

- 기존에 /event를 사용하다가 경로를 /new-event로 바꿈
-> 문제점: 기존의 경로를 저장해두고 사용하던 클라이언트들은 /event를 타고 들어옴
- HTTP/1.1 301 Moved Permanently Location: /new-event 라고 응답이 들어옴
- Location 경로로 자동으로 URL이 변경돼서 서버에 다시 /new-event 페이지로 요청함

**종류**
- **영구 리다이렉션** - 특정 리소스의 URI가 영구적으로 이동
	- 예) /members -> /users
	- 예) /event -> /new-event 
- **일시 리다이렉션** - 일시적인 변경
	- 주문 완료 후 주문 내역 화면으로 이동
	- **PRG: Post/Redirect/Get (굉장히 자주 사용하는 패턴)**
- **특수 리다이렉션**
	- 결과 대신 캐시를 사용

### 영구 리다이렉션 -> 301, 308
- 리소스의 URI가 영구적으로 이동
- 원래의 URL를 사용X, 검색 엔진 등에서도 변경 인지
- **301 Moved Permanently (실무에서 자주 사용함)**
	- **리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음(MAY)**

<img width="468" alt="스크린샷 2023-07-30 오후 1 32 22" src="https://github.com/Hoya324/SpringNote/assets/96857599/d4f4efb4-9e2f-408d-ab72-04ba05cc6559">

- **308 Permanent Redirect**
	- 301과 기능은 같음
	- **리다이렉트 시 요청 메서드와 본문 유지(처음 POST를 보내면 리다이렉트도 POST 유지)**

<img width="456" alt="스크린샷 2023-07-30 오후 1 33 26" src="https://github.com/Hoya324/SpringNote/assets/96857599/9b3ba024-e337-47f9-8ade-4433ead674dd">

### 일시적인 리다이렉션 -> 302, 307, 303
 
- 리소스의 URI가 일시적으로 변경
- 따라서 검색 엔진 등에서 URL을 변경하면 안됨
- **302 Found**
	- **리다이렉트시 요청 메서드가 GET으로 변하고, 본문이 제거될 수 있음(MAY)**
- **307 Temporary Redirect**
	- 302와 기능은 같음
	- **리다이렉트시 요청 메서드와 본문 유지(요청 메서드를 변경하면 안된다. MUST NOT)**
- **303 See Other**
	- 302와 기능은 같음
	- **리다이렉트시 요청 메서드가 GET으로 변경**

### PRG: Post/Redirect/Get
-> 일시적인 리다이렉션 - 예시

**PRG 사용전**
- POST로 주문후에 웹 브라우저를 새로고침하면? 
- 새로고침은 다시 요청
- 중복 주문이 될 수 있다.

<img width="549" alt="스크린샷 2023-07-30 오후 1 36 24" src="https://github.com/Hoya324/SpringNote/assets/96857599/1b1430fb-c42c-47f3-aaa9-7abd432e0009">

> 서버 차원에서 중복되 주문번호를 제거하는 것이 맞지만, 클라이언트 차원에서도 한번 방지하는 것도 좋다.

**PRG 사용**
- POST로 주문후에 새로 고침으로 인한 중복 주문 방지 
- POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트 
- 새로고침해도 결과 화면을 GET으로 조회
- 중복 주문 대신에 결과 화면만 GET으로 다시 요청

**리다이렉션 302 Found 또는 303 See Other를 통해 메서드를 Get으로 변경**

<img width="551" alt="스크린샷 2023-07-30 오후 1 37 03" src="https://github.com/Hoya324/SpringNote/assets/96857599/11c4e9f2-2582-4b4c-8127-bf445700e6b3">

- PRG 이후 리다이렉트
	- URL이 이미 POST -> GET으로 리다이렉트 됨
	- 새로 고침 해도 GET으로 결과 화면만 조회

### 정리
> 그래서 뭘 써야 하나요?

**302, 307, 303**

- **정리**
	- 302 Found -> GET으로 변할 수 있음
	- 307 Temporary Redirect -> 메서드가 변하면 안됨 
	- 303 See Other -> 메서드가 GET으로 변경
- **역사**
	- 처음 302 스펙의 의도는 HTTP 메서드를 유지하는 것
	- 그런데 웹 브라우저들이 대부분 GET으로 바꾸어버림(일부는 다르게 동작)
	- 그래서 모호한 302를 대신하는 명확한 307, 303이 등장함(301 대응으로 308도 등장)
- **현실**
	- 307, 303을 권장하지만 현실적으로 이미 많은 애플리케이션 라이브러리들이 302를 기본값으로 사용 
	- 자동 리다이렉션시에 GET으로 변해도 되면 그냥 302를 사용해도 큰 문제 없음

### 기타 리다이렉션 -> 300, 304

- 300 Multiple Choices: 안쓴다. 
- 304 Not Modified
	- 캐시를 목적으로 사용
	- 클라이언트에게 리소스가 수정되지 않았음을 알려준다. 따라서 클라이언트는 로컬PC에 저장된 캐시를 재사용한다. (캐시로 리다이렉트 한다.)
	- 304 응답은 응답에 메시지 바디를 포함하면 안된다. (로컬 캐시를 사용해야 하므로) 
	- 조건부 GET, HEAD 요청시 사용

### 4xx (Client Error)
-> 클라이언트 오류

- 클라이언트의 요청에 잘못된 문법등으로 서버가 요청을 수행할 수 없음
- **오류의 원인이 클라이언트에 있음**
- ⭐️**중요! 클라이언트가 이미 잘못된 요청, 데이터를 보내고 있기 때문에, 똑같은 재시도가 실패함**

> 4xx과 5xx의 가장 큰 차이: 4xx는 클라이언트에 오류 원인이 있으므로 재시도가 실패하지만,
>
> 5xx은 서버에 오류 원인이 있으므로 서버의 데이터가 복구된다면 같은 클라이언트의 요청에도 성공할 가능성이 있음

**400 Bad Request**
-> **클라이언트가 잘못된 요청을 해서 서버가 요청을 처리할 수 없음**

- 요청 구문, 메시지 등등 오류
- 클라이언트는 요청 내용을 다시 검토하고, 보내야함
- 예) 요청 파라미터가 잘못되거나, API 스펙이 맞지 않을 때

=> 백엔드 개발자가 막아야함

**401 Unauthorized**
-> **클라이언트가 해당 리소스에 대한 인증이 필요함**

- 인증(Authentication) 되지 않음
- 401 오류 발생시 응답에 WWW-Authenticate 헤더와 함께 인증 방법을 설명 
- 참고
	- 인증(Authentication): 본인이 누구인지 확인, (로그인)
	- 인가(Authorization): 권한부여 (ADMIN 권한처럼 특정 리소스에 접근할 수 있는 권한, 인증이 있어야 인가가 있음)
	- 오류 메시지가 Unauthorized 이지만 인증 되지 않음 (이름이 아쉬움)

**403 Forbidden**
-> **서버가 요청을 이해했지만 승인을 거부함**

- 주로 인증 자격 증명은 있지만, 접근 권한이 불충분한 경우
- 예) 어드민 등급이 아닌 사용자가 로그인은 했지만, 어드민 등급의 리소스에 접근하는 경우

**404 Not Found**
-> **요청 리소스를 찾을 수 없음**

- 요청 리소스가 서버에 없음
- 또는 클라이언트가 권한이 부족한 리소스에 접근할 때 해당 리소스를 숨기고 싶을 때

### 5xx (Server Error)
-> 서버 오류

- 서버 문제로 오류 발생
- 서버에 문제가 있기 때문에 재시도 하면 성공할 수도 있음(복구가 되거나 등등)

**500 Internal Server Error**
-> 서버 문제로 오류 발생, 애매하면 500 오류

- 서버 내부 문제로 오류 발생 
- 애매하면 500 오류

**503 Service Unavailable**
-> 서비스 이용 불가
- 서버가 일시적인 과부하 또는 예정된 작업으로 잠시 요청을 처리할 수 없음 
- Retry-After 헤더 필드로 얼마뒤에 복구되는지 보낼 수도 있음

> 5xx는 정말 서버에 문제가 생겼을 때만 사용해야함. (로직에 문제가 있거나, 쿼리에 문제가 있거나, 데이터베이스에 문제가 생기는 경우 등등)


## HTTP 헤더

> 일반 헤더

### HTTP 헤더 개요
- header-field = field-name ":" OWS field-value OWS (OWS: 띄어쓰기 허용)
- field-name은 대소문자 구문 없음

<img width="552" alt="스크린샷 2023-07-29 오후 5 18 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/e030a0dd-477c-412c-8c86-533bc39f8361">

**용도**
- HTTP 전송에 필요한 모든 부가정보
- 예) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...
- 표준 헤더가 너무 많음
  - [List_of_HTTP_header_fields](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields)
- 필요시 임의의 헤더 추가 가능
  - helloworld: hihi

### RFC2616(과거)

**HTTP 헤더 - 분류**

<img width="371" alt="스크린샷 2023-07-30 오후 1 52 23" src="https://github.com/Hoya324/SpringNote/assets/96857599/73300359-8f16-44de-8b17-c49f346a5584">

- 헤더 분류
	- **General 헤더**: 메시지 전체에 적용되는 정보, 예) Connection: close
	- **Request 헤더**: 요청 정보, 예) User-Agent: Mozilla/5.0 (Macintosh; ..)
	- **Response 헤더**: 응답 정보, 예) Server: Apache
	- **Entity 헤더**: 엔티티 바디 정보, 예) Content-Type: text/html, Content-Length: 3423

**HTTP Body - message body**

<img width="348" alt="스크린샷 2023-07-30 오후 1 53 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/42e5caf8-878b-48dd-89f9-9e24b53f8124">

- 메시지 본문(message body)은 엔티티 본문(entity body)을 전달하는데 사용
- 엔티티 본문은 요청이나 응답에서 전달할 실제 데이터
- **엔티티 헤더는 엔티티 본문**의 데이터를 해석할 수 있는 정보 제공
	- 데이터 유형(html, json), 데이터 길이, 압축 정보 등등

### HTTP 표준 변화
- 1999년 RFC2616 -> **폐기됨**
- 2014년 RFC7230~7235 등장

**RFC723x 변화**
- 엔티티(Entity) -> 표현(Representation)
- Representation = representation Metadata + Representation Data
- 표현 = 표현 메타데이터 + 표현 데이터

### RFC7230(최신)

**HTTP BODY - message body**

<img width="297" alt="스크린샷 2023-07-30 오후 1 59 03" src="https://github.com/Hoya324/SpringNote/assets/96857599/e2d979fb-2e02-48ee-a743-ed08c12079fc">

- 메시지 본문(message body)을 통해 표현 데이터 전달
- 메시지 본문 = 페이로드(payload)
- **표현**은 요청이나 응답에서 전달할 실제 데이터
- **표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공**
	- 데이터 유형(html, json), 데이터 길이, 압축 정보 등등

> 참고: 표현 헤더는 표현 메타데이터와, 페이로드 메시지를 구분해야 하지만, 여기서는 생략

### 표현

<img width="293" alt="스크린샷 2023-07-30 오후 2 03 15" src="https://github.com/Hoya324/SpringNote/assets/96857599/b840b2d6-d904-4c34-bf71-f72116276c50">

- Content-Type: 표현 데이터의 형식
- Content-Encoding: 표현 데이터의 압축 방식
- Content-Language: 표현 데이터의 자연 언어
- Content-Length: 표현 데이터의 길이

- 표현 헤더는 전송, 응답 둘다 사용

### Content-Type
**표현 데이터의 형식 설명**
- application/json은 기본이 UTF-8임

<img width="250" alt="스크린샷 2023-07-30 오후 2 04 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/80799316-b800-464c-b459-2b2b97a26c63">

- 미디어 타입, 문자 인코딩
- 예)
	- text/html; charset=utf-8
	- application/json
  - image/png

### Content-Encoding (압축할 때)
**표현 데이터 인코딩**

<img width="251" alt="스크린샷 2023-07-30 오후 2 06 41" src="https://github.com/Hoya324/SpringNote/assets/96857599/d597acc5-4b18-447a-b74a-87fa686a5fba">

- 표현 데이터를 압축하기 위해 사용
- 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
- 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
- 예)
	- gizp
	- deflate
	- identity

### Content-Language (표현 데이터의 자연어 표시)
**표현 데이터의 자연 언어**

<img width="217" alt="스크린샷 2023-07-30 오후 2 07 15" src="https://github.com/Hoya324/SpringNote/assets/96857599/84318f35-4d43-4f95-b2b2-d4362e5d8762">

- 표현 데이터의 자연 언어를 표현
- 예)
	- ko
	- en
	- en-US

### Content-Length
**표현 데이터의 길이**

<img width="218" alt="스크린샷 2023-07-30 오후 2 08 36" src="https://github.com/Hoya324/SpringNote/assets/96857599/c5e20700-24c3-475e-a70a-57fa6509d525">

- 바이트 단위
- Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안됨

### 콘텐츠 협상 (콘텐츠 네고시에이션)
-> 클라이언트가 (우선순위에 맞게) 선호하는 표현을 서버에게 요청

- Accept: 클라이언트가 선호하는 미디어 타입 전달 
- Accept-Charset: 클라이언트가 선호하는 문자 인코딩 
- Accept-Encoding: 클라이언트가 선호하는 압축 인코딩 
- Accept-Language: 클라이언트가 선호하는 자연 언어

> 협상 헤더는 요청시에만 사용

### Accept-Language

**Accept-Language 적용 전**

-> **클라이언트가 한국인지 아닌지 정보가 존재하지 않음**

<img width="447" alt="스크린샷 2023-07-30 오후 2 12 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/22ffdc1f-db81-48ad-88e5-10987ae71195">

**Accept-Language 적용 후**

-> **Accept-Language가 있으면 선호하는 언어가 한국어인 것을 HTTP Header로 처리할 수 있음.**

<img width="443" alt="스크린샷 2023-07-30 오후 2 12 21" src="https://github.com/Hoya324/SpringNote/assets/96857599/9afac02a-fe09-4b7a-954b-b790b47dfac3">

**Accept-Language 복잡한 예시**

-> 복잡한 예시: 한국어를 희망하는 클라이언트가 독일어와 영어를 지원하는 서버에서 기본 설정인 독일어보단 영어를 희망함.

<img width="432" alt="스크린샷 2023-07-30 오후 2 14 04" src="https://github.com/Hoya324/SpringNote/assets/96857599/75068068-f1aa-4f39-8ffb-987d6dfdc44f">

**협상과 우선순위1** 
-> Quality Values(q)

<img width="437" alt="스크린샷 2023-07-30 오후 2 18 30" src="https://github.com/Hoya324/SpringNote/assets/96857599/60bd0e14-19cc-4059-8731-979b2988e32a">

- Quality Values(q) 값 사용
- 0~1, **클수록 높은 우선순위**
- 생략하면 1
- Accept-Language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
	- 1. ko-KR;q=1 (q생략)
	- 2. ko;q=0.9
	- 3. en-US;q=0.8
	- 4. 4. en:q=0.7

**협상과 우선순위2** 

-> Quality Values(q)

<img width="322" alt="스크린샷 2023-07-30 오후 2 20 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/08045322-dcd3-4df5-a441-5aea2ab28c33">

- 구체적인 것이 우선한다.
- Accept: text/*, text/plain, text/plain;format=flowed, */*
	1. text/plain;format=flowed
	2. text/plain
	3. text/*
	4. */*

**협상과 우선순위3** 

-> Quality Values(q)

- 구체적인 것을 기준으로 미디어 타입을 맞춘다.
- Accept: text/*;q=0.3, text/html;q=0.7, text/html;level=1,  text/html;level=2;q=0.4, */*;q=0.5

<img width="154" alt="스크린샷 2023-07-30 오후 2 21 09" src="https://github.com/Hoya324/SpringNote/assets/96857599/d51f9efb-0bd6-4208-8604-3bbbbcac3a07">

### 전송 방식
- Transfer-Encoding
- Range, Content-Range

### 전송 방식 설명
- 단순 전송
- 압축 전송
- 분할 전송
- 범위 전송


### 단순 전송 Content-Length**

-> **컨텐츠에 대한 길이를 알 때 사용**

<img width="517" alt="스크린샷 2023-07-30 오후 2 23 35" src="https://github.com/Hoya324/SpringNote/assets/96857599/b920c398-30f1-453e-9afc-729ce16e3b54">

### 압축 전송 Content-Encoding

-> **Content-Encoding을 꼭 입력해야함**

<img width="512" alt="스크린샷 2023-07-30 오후 2 24 19" src="https://github.com/Hoya324/SpringNote/assets/96857599/cc26dada-4bc0-4046-b039-b85f7bc892de">

### 분할 전송 Transfer-Encoding

-> **Content-Length를 보내면 안 됨. (예상이 되지 않음, 청크들마다 길이가 나옴.)**

<img width="521" alt="스크린샷 2023-07-30 오후 2 26 15" src="https://github.com/Hoya324/SpringNote/assets/96857599/a2e27d8a-a702-4910-9e52-c6767322632a">

### 범위 전송 Range, Content-Range

-> **중간에 끊기면 처음부터 다시 받지 않도록 범위를 설정할 수 있음.**

<img width="483" alt="스크린샷 2023-07-30 오후 2 27 02" src="https://github.com/Hoya324/SpringNote/assets/96857599/78199b7f-df2d-4a2e-a228-d4a4841f1f88">

### 일반 전송
- From: 유저 에이전트의 이메일 정보
- Referer: 이전 웹 페이지 주소
- User-Agent: 유저 에이전트 애플리케이션 정보
- Server: 요청을 처리하는 오리진 서버의 소프트웨어 정보 
- Date: 메시지가 생성된 날짜

### From

-> **유저 에이전트의 이메일 정보**

- 일반적으로 잘 사용되지 않음 
- 검색 엔진 같은 곳에서, 주로 사용 
- 요청에서 사용

### Referer
-> **이전 웹 페이지 주소**
- 현재 요청된 페이지의 이전 웹 페이지 주소
- A -> B로 이동하는 경우 B를 요청할 때 Referer: A 를 포함해서 요청 
- Referer를 사용해서 유입 경로 분석 가능
- 요청에서 사용

> 참고: referer는 단어 referrer의 오타

**이전의 웹 페이지를 알려줌.**

![image](https://github.com/Hoya324/SpringNote/assets/96857599/4b021585-d233-4f00-81fe-b539da8228df)

![image](https://github.com/Hoya324/SpringNote/assets/96857599/66a159c7-2637-42f6-abfc-d18cc461bd8e)

### User-Agent

-> **유저 에이전트 애플리케이션 정보**

- user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/ 537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36
- 클리이언트의 애플리케이션 정보(웹 브라우저 정보, 등등) 
- 통계 정보
- 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능 
- 요청에서 사용

![image](https://github.com/Hoya324/SpringNote/assets/96857599/b581ffdd-2f6e-465d-a5ef-832293733538)

### Server

-> **요청을 처리하는 ORIGIN 서버(프록시 서버가 아닌 클라이언트의 응답을 해주는 서버)의 소프트웨어 정보**

- Server: Apache/2.2.22 (Debian) 
- server: nginx
- 응답에서 사용

### Date

-> **메시지가 발생한 날짜와 시간**

- Date: Tue, 15 Nov 1994 08:12:31 GMT
- 응답에서 사용


### 특별한 정보

- Host: 요청한 호스트 정보(도메인)
- Location: 페이지 리다이렉션
- Allow: 허용 가능한 HTTP 메서드
- Retry-After: 유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간


### ⭐️Host (필수 헤더)

-> **요청한 호스트 정보(도메인)**

<img width="242" alt="스크린샷 2023-07-30 오후 2 35 03" src="https://github.com/Hoya324/SpringNote/assets/96857599/553f3c1b-05da-4a08-8433-89289d2ab686">

- 요청에서 사용
- 필수
- 하나의 서버가 여러 도메인을 처리해야 할 때
- 하나의 IP 주소에 여러 도메인이 적용되어 있을 때

**1. 같은 IP를 공유하는 여러 도메인이 있다.**

<img width="559" alt="스크린샷 2023-07-30 오후 2 37 02" src="https://github.com/Hoya324/SpringNote/assets/96857599/cd1270c3-a8a4-47ba-a4e0-46b5b4da200c">

**2. Host가 없으면, 어느 도메인으로 가야할지 알 수 없다.**

<img width="517" alt="스크린샷 2023-07-30 오후 2 37 10" src="https://github.com/Hoya324/SpringNote/assets/96857599/a64b2e4c-5735-4416-9ab0-1dcf68065754">

**3. Host 정보 필수**

<img width="519" alt="스크린샷 2023-07-30 오후 2 37 18" src="https://github.com/Hoya324/SpringNote/assets/96857599/edd12fb7-8f4f-4f26-9628-4dd84004c31c">

### Location

-> **페이지 리다이렉션**

- 웹 브라우저는 3xx 응답의 결과에 Location 헤더가 있으면, Location 위치로 자동 이동 (리다이렉트)
- 응답코드 3xx에서 설명
- 201 (Created): Location 값은 요청에 의해 생성된 리소스 URI
- 3xx (Redirection): Location 값은 요청을 자동으로 리디렉션하기 위한 대상 리소스를 가리킴

### Allow

-> **허용 가능한 HTTP 메서드**

- 405 (Method Not Allowed) 에서 응답에 포함해야함 
- Allow: GET, HEAD, PUT

### Retry-After
-> **유저 에이전트가 다음 요청을 하기까지 기다려야 하는 시간**

- 503 (Service Unavailable): 서비스가 언제까지 불능인지 알려줄 수 있음 
- Retry-After: Fri, 31 Dec 1999 23:59:59 GMT (날짜 표기) 
- Retry-After: 120 (초단위 표기)

### 인증
- Authorization: 클라이언트 인증 정보를 서버에 전달 
- WWW-Authenticate: 리소스 접근시 필요한 인증 방법 정의

### Authorization
-> **클라이언트 인증 정보를 서버에 전달**

- Authorization: Basic xxxxxxxxxxxxxxxx

> 인증 방식이 여러가지 있기 때문에 value에는 각 인증에 맞는 값을 넣으면 됨

### WWW-Authenticate

-> **리소스 접근시 필요한 인증 방법 정의**

- 리소스 접근시 필요한 인증 방법 정의
- 401 Unauthorized 응답과 함께 사용 
- 401 오류가 나면, 아래와 같은 내용을 같이 클라이언트에게 제공하게 된다.
	- WWW-Authenticate: Newauth realm="apps", type=1,<br>
		    title="Login to \"apps\"", Basic realm="simple"
		    

### 쿠키
- **Set-Cookie**: 서버에서 클라이언트로 쿠키 전달(응답)
- **Cookie**: 클라이언트가 서버에서 받은 쿠키를 저장하고, HTTP 요청시 서버로 전달
		    
### 쿠키 미사용

**1. 처음 welcome 페이지 접근**

<img width="493" alt="스크린샷 2023-07-30 오후 2 45 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/d7fba655-6077-4bca-9475-a4100c3a73d5">

**2. 로그인**

<img width="491" alt="스크린샷 2023-07-30 오후 2 46 32" src="https://github.com/Hoya324/SpringNote/assets/96857599/b7ec0ce8-72be-4763-8421-5bb653bb1a8b">

**3. 로그인 이후 welcome 페이지 접근 (안녕하세요. 홍길동님을 기대했으나, 안녕하세요 손님이 다시 나옴)**

<img width="496" alt="스크린샷 2023-07-30 오후 2 47 01" src="https://github.com/Hoya324/SpringNote/assets/96857599/232cf149-f852-46e7-be46-cbc17e94b636">


> ⭐️**Stateless**⭐️
>
> - HTTP는 기본적으로 무상태(Stateless) 프로토콜이다.
> - 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어진다. 
> - 클라이언트가 다시 요청하면 서버는 이전 요청을 기억하지 못한다. 
> - 클라이언트와 서버는 서로 상태를 유지하지 않는다.
> 

**4. 대안- 모든 요청에 사용자 정보 포함**

<img width="487" alt="스크린샷 2023-07-30 오후 2 48 58" src="https://github.com/Hoya324/SpringNote/assets/96857599/58f945ec-eaa9-48b3-bf8b-fc554721bc77">

**모든 링크에 사용자 정보를 포함하면, 보안상의 문제부터 여러 문제점이 발생함.**

<img width="332" alt="스크린샷 2023-07-30 오후 2 50 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/79e5aad7-5158-48c2-92ac-65551cfc01a7">

> **모든 요청에 정보를 넘기는 문제**
> - 모든 요청에 사용자 정보가 포함되도록 개발 해야함 
> - 브라우저를 완전히 종료하고 다시 열면?
> 쿠키를 사용하면 해결

### 쿠키 사용

**1. 로그인**

-> **웹브라우저 내부에 저장있는 쿠키 저장소에에 user=홍길동을 저장**

<img width="497" alt="스크린샷 2023-07-30 오후 2 52 09" src="https://github.com/Hoya324/SpringNote/assets/96857599/c3291797-4f68-4f71-950c-6550b738624c">

**2. 로그인 이후 welcome 페이지 접근**

-> **웹브라우저는 쿠키 저장소를 무조건 확인함.**

<img width="497" alt="스크린샷 2023-07-30 오후 2 53 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/86babf55-5c59-4b4b-afa8-829c012d5b40">

**3. 모든 요청에 쿠키 정보 자동 포함**

<img width="387" alt="스크린샷 2023-07-30 오후 2 53 23" src="https://github.com/Hoya324/SpringNote/assets/96857599/8dae6c05-0d3e-49fa-8021-7b9422606272">

> ⭐️중요! 모든 요청에 쿠키 정보를 넣으면 보안상의 문제부터 여러 문제 초래

### 쿠키
- 홍길동이라는 **이름을 그대로 노출하면 보안에 문제**가 생기므로, **sessionId를 만들어서 해결**한다.
- 예) set-cookie: **sessionId=abcde1234**; **expires**=Sat, 26-Dec-2020 00:00:00 GMT; **path**=/; **domain**=.google.com; **Secure**

- 사용처
	- 사용자 로그인 세션 관리
	- 광고 정보 트래킹
- 쿠키 정보는 항상 서버에 전송됨
	- 네트워크 트래픽 추가 유발 (문제점)
	- 최소한의 정보만 사용(세션 id, 인증 토큰)
	- 서버에 전송하지 않고, 웹 브라우저 내부에 데이터를 저장하고 싶으면 웹 스토리지 (localStorage, sessionStorage) 참고
- 주의!
	- 보안에 민감한 데이터는 저장하면 안됨(주민번호, 신용카드 번호 등등)

### 쿠키 - 생명주기 

-> **Expires, max-age**

- Set-Cookie: **expires**=Sat, 26-Dec-2020 04:39:21 GMT
	- 만료일이 되면 쿠키 삭제
- Set-Cookie: **max-age**=3600 (3600초)
	- 0이나 음수를 지정하면 쿠키 삭제
- **세션 쿠키**: 만료 날짜를 생략하면 브라우저 종료시 까지만 유지 
- **영속 쿠키**: 만료 날짜를 입력하면 해당 날짜까지 유지

### 쿠키 - 도메인

-> **Domain**

- 예) domain=example.org
- **명시: 명시한 문서 기준 도메인 + 서브 도메인 포함**
	- domain=example.org를 지정해서 쿠키 생성
		- example.org는 물론이고
		- dev.example.org도 쿠키 접근 
- **생략: 현재 문서 기준 도메인만 적용**
	- example.org 에서만 쿠키 접근
		- example.org 에서 쿠키를 생성하고 domain 지정을 생략
		- dev.example.org는 쿠키 미접근

### 쿠키 - 경로

-> **Path**

- 예) path=/home
- **이 경로를 포함한 하위 경로 페이지만 쿠키 접근**
- **일반적으로 path=/ 루트로 지정 **
- 예)
	- **path=/home 지정**
	- /home -> 가능 
	- /home/level1 -> 가능 
	- /home/level1/level2 -> 가능 
	- /hello -> 불가능

### 쿠키 - 보안

-> **Secure, HttpOnly, SameSite**

- Secure
	- 쿠키는 http, https를 구분하지 않고 전송
	- Secure를 적용하면 https인 경우에만 전송
- HttpOnly
	- XSS 공격 방지
	- 자바스크립트에서 접근 불가(document.cookie)
	- HTTP 전송에만 사용
- SameSite
	- XSRF 공격 방지
	- 요청 도메인과 쿠키에 설정된 도메인이 같은 경우만 쿠키 전송

[캐시와 쿠키의 차이](https://zorba91.tistory.com/163)


### 캐시

### 캐시 기본 동작- 캐시가 없을 때
**첫 번째 요청**

<img width="595" alt="스크린샷 2023-07-30 오후 3 04 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/7b1eb691-1e06-4d3a-9633-d4168c5dda64">

<img width="593" alt="스크린샷 2023-07-30 오후 3 05 04" src="https://github.com/Hoya324/SpringNote/assets/96857599/e13f441e-f9d2-4655-80a6-32779f8548eb">

**두 번째 요청**

<img width="594" alt="스크린샷 2023-07-30 오후 3 05 47" src="https://github.com/Hoya324/SpringNote/assets/96857599/1e35f55c-1923-40e0-8ea2-5e4779f07c13">

<img width="587" alt="스크린샷 2023-07-30 오후 3 06 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/ba226bb7-422d-4044-8b06-01257035b5ff">

**캐시가 없을 때 특징**
- 데이터가 변경되지 않아도 계속 네트워크를 통해서 데이터를 다운로드 받아야 한다. 
- 인터넷 네트워크는 매우 느리고 비싸다.
- 브라우저 로딩 속도가 느리다.
- 느린 사용자 경험

### 캐시 적용

**첫 번째 요청 - 캐시의 생명 주기를 설정하고 데이터를 저장해둠**

<img width="603" alt="스크린샷 2023-07-30 오후 3 09 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/8b4ff496-0838-4118-82f6-efd808946f8f">

<img width="598" alt="스크린샷 2023-07-30 오후 3 10 05" src="https://github.com/Hoya324/SpringNote/assets/96857599/fa4190e1-e695-4772-aeed-e7e761525b0f">

**두 번째 요청 - 브라우저 캐시에 저장되어있는 데이터는 바로 응답함.**

<img width="521" alt="스크린샷 2023-07-30 오후 3 10 56" src="https://github.com/Hoya324/SpringNote/assets/96857599/6c15ac21-6911-4ef0-b490-cec5463e49fa">

<img width="509" alt="스크린샷 2023-07-30 오후 3 11 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/72bd784b-ef8b-4d68-96f3-48432f1c29db">

**캐시를 적용했을 때 특징**
- 캐시 덕분에 캐시 가능 시간동안 네트워크를 사용하지 않아도 된다. 
- 비싼 네트워크 사용량을 줄일 수 있다.
- 브라우저 로딩 속도가 매우 빠르다.
- 빠른 사용자 경험
 
**세 번째 요청 - 캐시 시간 초과**

<img width="515" alt="스크린샷 2023-07-30 오후 3 14 55" src="https://github.com/Hoya324/SpringNote/assets/96857599/393bfebc-2e82-4306-9ff0-de2ea106d2d3">

<img width="610" alt="스크린샷 2023-07-30 오후 3 15 10" src="https://github.com/Hoya324/SpringNote/assets/96857599/a2d41e4b-b69b-4d21-98a0-6d45d50be8f5">

<img width="602" alt="스크린샷 2023-07-30 오후 3 16 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/1e4b36c2-3607-4be9-a9b8-6a4f2f91b441">

**캐시 시간 초과**
- 캐시 유효 시간이 초과하면, 서버를 통해 데이터를 다시 조회하고, 캐시를 갱신한다. 
- 이때 다시 네트워크 다운로드가 발생한다.

> 캐시 유효 시간이 만료된 데이터가 다시 들어올 때, 같은 데이터더라도 다시 다운받게 된다. 시간,
>
> 용량이 아깝다.
>
> 이걸 해결하기 위한 매커니즘 -> 검증 헤더와 조건부 요청

### 검증 헤더와 조건부 요청1

**캐시 시간 초과**

- 캐시 유효 시간이 초과해서 서버에 다시 요청하면 다음 두 가지 상황이 나타난다.
  1. 서버에서 기존 데이터를 변경함
  2. 서버에서 기존 데이터를 변경하지 않음


### 검증 헤더 추가
- 최종 수정일 확인/UTC로 적어야함

**첫 번째 요청**

<img width="632" alt="스크린샷 2023-07-30 오후 4 29 24" src="https://github.com/Hoya324/SpringNote/assets/96857599/d54dcbb3-0feb-456b-9133-0a2b5eab8531">

**캐시 유효시간 안에는 그대로 캐시 데이터 사용**

<img width="619" alt="스크린샷 2023-07-30 오후 4 30 33" src="https://github.com/Hoya324/SpringNote/assets/96857599/199d3d3f-d6ab-4a04-b705-8b9cbf8d11ed">

**두 번째 요청 - 캐시 시간 초과**

<img width="516" alt="스크린샷 2023-07-30 오후 4 31 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/1cc9ba89-f581-4d87-bee5-6702417d6f6f">

<img width="518" alt="스크린샷 2023-07-30 오후 4 31 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/7bde21b8-e27b-4115-8d2f-06dff509c086">

**데이터의 최종 수정일을 확인함.**

<img width="507" alt="스크린샷 2023-07-30 오후 4 36 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/d6e41c5d-8284-4c0e-908d-2cba4bf0b2a0">

<img width="523" alt="스크린샷 2023-07-30 오후 4 37 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/6b3659eb-5ba0-4632-8d58-91649c11fe40">

**304 Not Modified를 내보낸다.**
- 단, HTTP Body가 없이 내보낸다.

<img width="641" alt="스크린샷 2023-07-30 오후 4 37 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/d7486fb0-6388-4bce-9247-7688a5ccf950">

**응답 결과를 재사용하고, 헤더 데이터를 갱신한다.**

<img width="652" alt="스크린샷 2023-07-30 오후 4 38 23" src="https://github.com/Hoya324/SpringNote/assets/96857599/5e3a057b-aaaa-4972-8cab-75028de77473">

**정리**
- 캐시 유효 시간이 초과해도, 서버의 데이터가 갱신되지 않으면
- 304 Not Modified + 헤더 메타 정보만 응답(바디X)
- 클라이언트는 서버가 보낸 응답 헤더 정보로 캐시의 메타 정보를 갱신 
- 클라이언트는 캐시에 저장되어 있는 데이터 재활용
- 결과적으로 네트워크 다운로드가 발생하지만 용량이 적은 헤더 정보만 다운로드 
- 매우 실용적인 해결책

**캐시에서 불러온 데이터인지 확인**

![image](https://github.com/Hoya324/SpringNote/assets/96857599/3f6145d9-5274-4903-91b0-44e44d346370)

![image](https://github.com/Hoya324/SpringNote/assets/96857599/3a0d3f2c-7f5f-40fe-ab9a-a7d126869808)

- 이미지를 더블클릭하고, 새로 고침해보면 304 Not Modified가 뜨게 되고,
- if-modified-since: Tue, 03 Mar 2020 20:15:00 GMT라는 조건부를 확인할 수 있다.

![image](https://github.com/Hoya324/SpringNote/assets/96857599/1a9fce1d-36f7-42e9-9ce0-774dac6410b1)

![image](https://github.com/Hoya324/SpringNote/assets/96857599/2bef1469-6a31-4ae1-9a7f-8d10b7de13f7)

### 검증 헤더와 조건부 요청2

- **검증 헤더**
	- 캐시 데이터와 서버 데이터가 같은지 검증하는 데이터
	- Last-Modified , ETag
- **조건부 요청 헤더**
	- 검증 헤더로 조건에 따른 분기 
	- If-Modified-Since: Last-Modified 사용 
	- If-None-Match: ETag 사용
	- 조건이 만족하면 200 OK
	- 조건이 만족하지 않으면 304 Not Modified

**예시**
- If-Modified-Since: 이후에 데이터가 수정되었으면?
	- **데이터 미변경 예시**
		- 캐시: 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 10:00:00 
		- **304 Not Modified**, 헤더 데이터만 전송(BODY 미포함)
		- 전송 용량 0.1M (헤더 0.1M, 바디 1.0M)
	- **데이터 변경 예시**
		- 캐시: 2020년 11월 10일 10:00:00 vs 서버: 2020년 11월 10일 11:00:00 
		- **200 OK**, 모든 데이터 전송(BODY 포함)
		- 전송 용량 1.1M (헤더 0.1M, 바디 1.0M)

**Last-Modified, If-Modified-Since 단점**

- 1초 미만(0.x초) 단위로 캐시 조정이 불가능
- 날짜 기반의 로직 사용
- 데이터를 수정해서 날짜가 다르지만, 같은 데이터를 수정해서 데이터 결과가 똑같은 경우
- 서버에서 별도의 캐시 로직을 관리하고 싶은 경우
	- 예) 스페이스나 주석처럼 크게 영향이 없는 변경에서 캐시를 유지하고 싶은 경우

> ETag 사용를 사용해서 단점을 해결해보자

### ETag, If-None-Match

- ETag(Entity Tag)
- 캐시용 데이터에 임의의 고유한 버전 이름을 달아둠
	- 예) ETag: "v1.0", ETag: "a2jiodwjekjl3"
- 데이터가 변경되면 이 이름을 바꾸어서 변경함(Hash를 다시 생성)
	- 예) ETag: "aaaaa" -> ETag: "bbbbb"
- 진짜 단순하게 ETag만 보내서 같으면 유지, 다르면 다시 받기!

### 검증 헤더 추가
**첫 번째 요청 -> ETag 저장**

<img width="624" alt="스크린샷 2023-07-30 오후 4 45 30" src="https://github.com/Hoya324/SpringNote/assets/96857599/e6a26dd7-e977-4717-a4b7-69570493174a">

<img width="610" alt="스크린샷 2023-07-30 오후 4 45 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/0fb0f946-8db0-4801-949b-d3e52e234fc8">

**두 번째 요청 - 캐시 시간 초과**

<img width="506" alt="스크린샷 2023-07-30 오후 4 46 33" src="https://github.com/Hoya324/SpringNote/assets/96857599/e37e8b1e-d488-413f-a4bf-76c8569493a6">

**요청한 이미지가 가진 ETag와 캐시가 가진 ETag와 동일함**<br>
**`If-None-Match: "aaaaaaaaaa"` 이기 때문에 일치하지 않으면 성공, 일치하면 실패이다.**

<img width="522" alt="스크린샷 2023-07-30 오후 4 46 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/2fde2481-a8a1-466c-9aae-ac3fd98529ab">

<img width="513" alt="스크린샷 2023-07-30 오후 4 47 18" src="https://github.com/Hoya324/SpringNote/assets/96857599/8c4964d1-baa6-43c0-be5b-699c0b443353">

**데이터가 수정되지 않음**

<img width="509" alt="스크린샷 2023-07-30 오후 4 47 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/b8479485-c62f-4548-bf98-215f6682333a">

**실패(때문에 304 Not Modified가 뜸)**

<img width="648" alt="스크린샷 2023-07-30 오후 4 51 21" src="https://github.com/Hoya324/SpringNote/assets/96857599/c6be0965-1c44-445b-8241-5890ac3008b5">

**HTTP Body를 제외하고 전송**

<img width="653" alt="스크린샷 2023-07-30 오후 4 54 08" src="https://github.com/Hoya324/SpringNote/assets/96857599/9dd668ba-555b-4f33-adf9-1edb0e612483">

<img width="311" alt="스크린샷 2023-07-30 오후 4 54 38" src="https://github.com/Hoya324/SpringNote/assets/96857599/710631c2-6e1f-4057-9f6a-347f42a319d5">

### ETag, If-None-Match 정리
- 진짜 단순하게 ETag만 서버에 보내서 같으면 유지, 다르면 다시 받기!
- **캐시 제어 로직을 서버에서 완전히 관리**
- 클라이언트는 단순히 이 값을 서버에 제공(클라이언트는 캐시 메커니즘을 모름) 
- 예)
	- 서버는 배타 오픈 기간인 3일 동안 파일이 변경되어도 ETag를 동일하게 유지 
	- **애플리케이션 배포 주기에 맞추어 ETag 모두 갱신**

### 캐시 제어 헤더
- Cache-Control: 캐시 제어 
- Pragma: 캐시 제어(하위 호환) 
- Expires: 캐시 유효 기간(하위 호환)

### Cache-Control (가장 중요⭐️)

-> **캐시 지시어(directives)**

- Cache-Control: max-age
	- 캐시 유효 시간, 초 단위
- Cache-Control: no-cache
	- 데이터는 캐시해도 되지만, 항상 원(origin) 서버에 검증하고 사용
- Cache-Control: no-store
	- 데이터에 민감한 정보가 있으므로 저장하면 안됨 (메모리에서 사용하고 최대한 빨리 삭제)

### Pragma

-> **캐시 제어(하위 호환)**

- Pragma: no-cache 
- HTTP 1.0 하위 호환


### Expires

-> **캐시 만료일 지정(하위 호환)**

- expires: Mon, 01 Jan 1990 00:00:00 GMT

- 캐시 만료일을 정확한 날짜로 지정
- HTTP 1.0 부터 사용
- 지금은 더 유연한 Cache-Control: max-age 권장 
- Cache-Control: max-age와 함께 사용하면 Expires는 무시

### 검증 헤더와 조건부 요청 헤더

- **검증 헤더 (Validator)**
	- **ETag**: "v1.0", **ETag**: "asid93jkrh2l"
	- **Last-Modified**: Thu, 04 Jun 2020 07:19:24 GMT 
- **조건부 요청 헤더**
	- If-Match, If-None-Match: ETag 값 사용
	- If-Modified-Since, If-Unmodified-Since: Last-Modified 값 사용

### 프록시 캐시

- 원 서버(origin) 직접 접근
-> 오래 걸림

<img width="562" alt="스크린샷 2023-07-30 오후 4 57 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/46ac3fc0-7764-4bff-8301-cecbae3b1d9e">

### 프록시 캐시 도입

**첫 번째 요청**

<img width="615" alt="스크린샷 2023-07-30 오후 4 57 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/6b609c08-0983-4b63-ad85-f65a7fd56939">

<img width="607" alt="스크린샷 2023-07-30 오후 4 58 48" src="https://github.com/Hoya324/SpringNote/assets/96857599/6ea61986-85aa-4282-89c6-b4aacbad6e65">

> 외국의 인기 없는 유튜브 영상을 보면 다운받는 로딩 속도가 느리지만, 한국의 인기 있는 영상을 보면 로딩 속도가 빠른 것이 프록시 캐시 서버 덕분이다.

### 캐시 지시어(directives) - 기타
- **Cache-Control: public**
	- 응답이 public 캐시에 저장되어도 됨 
- **Cache-Control: private**
	- 응답이 해당 사용자만을 위한 것임, private 캐시에 저장해야 함(기본값) 
- **Cache-Control: s-maxage**
	- 프록시 캐시에만 적용되는 max-age 
- **Age: 60** (HTTP 헤더)
	- 오리진 서버에서 응답 후 프록시 캐시 내에 머문 시간(초)

### 캐시 무효화

### Cache-Control

-> **확실한 캐시 무효화 응답(확실하게 캐시하지 않도록 만들어주는 것)**

- **Cache-Control: no-cache, no-store, must-revalidate**
- **Pragma: no-cache**
	- HTTP 1.0 하위 호환

> Pragma는 과거의 HTTP까지 호환하기 위해 사용한 것.


### Cache-Control 정리

-> **캐시 지시어(directives) - 확실한 캐시 무효화**

- **Cache-Control: no-cache**
	- 데이터는 캐시해도 되지만, 항상 원 서버에 검증하고 사용(이름에 주의!) 
- **Cache-Control: no-store**
	- 데이터에 민감한 정보가 있으므로 저장하면 안됨(메모리에서 사용하고 최대한 빨리 삭제)
- **Cache-Control: must-revalidate**
	- 캐시 만료후 최초 조회시 **원 서버에 검증해야함**
	- 원 서버 접근 실패시 반드시 오류가 발생해야함 - 504(Gateway Timeout)
	- must-revalidate는 캐시 유효 시간이라면 캐시를 사용함
- **Pragma: no-cache**
	- HTTP 1.0 하위 호환

### no-cache vs must-revalidate 

**no-cache 기본 동작**

<img width="624" alt="스크린샷 2023-07-30 오후 5 02 18" src="https://github.com/Hoya324/SpringNote/assets/96857599/ae7804a1-09ec-438c-b094-c4872bef1d7f">

- 만약, 순간적으로 네트워크가 단절되어 원 서버에 접근이 불가능하다면?
**Error or 200 OK (오류 보다는 오래된 데이터라도 보여주자)**

<img width="637" alt="스크린샷 2023-07-30 오후 5 02 58" src="https://github.com/Hoya324/SpringNote/assets/96857599/724aa542-ee8e-4c97-9c9c-743824785a71">

**must-revalidate는 문제가 생기면 오류가 발생하도록 함.**

<img width="622" alt="스크린샷 2023-07-30 오후 5 03 37" src="https://github.com/Hoya324/SpringNote/assets/96857599/c301c258-f55b-447a-8460-a9a08c7e9b78">

### [Internet Engineering Task Force (IETF) - HTTP 추가적인 공부](https://datatracker.ietf.org/doc/html/rfc7230)

## Reference

- [모든 개발자를 위한 HTTP 웹 기본 지식/김영한](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard)
- [REST API란, HTTP Method](https://velog.io/@ellyheetov/REST-API)
- [DNS 구성 요소 및 분류(DNS Resolver, DNS 서버)](https://anggeum.tistory.com/entry/DNS-%EA%B0%9C%EB%85%90%EC%9E%A1%EA%B8%B0-2-DNS-%EA%B5%AC%EC%84%B1-%EC%9A%94%EC%86%8C-%EB%B0%8F-%EB%B6%84%EB%A5%98DNS-Resolver-DNS-%EC%84%9C%EB%B2%84)
- [도메인 네임(Domain Name)](https://hyoje420.tistory.com/33)
