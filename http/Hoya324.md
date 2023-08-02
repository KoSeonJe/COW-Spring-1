> HTTP에 대한 내용을 정리한 글입니다.

# 인터넷 네트워크

## 인터넷 통신

<img width="671" alt="스크린샷 2023-08-02 오후 4 49 18" src="https://github.com/Hoya324/SpringNote/assets/96857599/8e439d39-fa1d-43f5-a062-1b6d53263f8e">

- 클라이언트가 서버로 요청을 보내는 방식을 알기 위해서는 IP를 이해해야한다.

## IP(인터넷 프로토콜)

### IP의 역할
- 지정한 IP 주소(IP Address)에 데이터 전달.
- 패킷(Packet)이라는 통신 단위로 데이터 전달

### IP를 통한 통신 과정
1. 클라이언트가 IP를 가진다.
2. 서버에도 IP를 가진다.
3. 메세지를 그냥 전달하는 것이 아니라 IP 패킷이라는 규칙을 통해 전달한다.

<img width="586" alt="스크린샷 2023-08-02 오후 4 50 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/ad7ecd1b-d9d0-43b9-a0fd-a4f74ad17097">

4. 클라이언트 패킷 전달. (출발, 목적 IP와 메세지를 전달한다.)

<img width="640" alt="스크린샷 2023-08-02 오후 4 50 31" src="https://github.com/Hoya324/SpringNote/assets/96857599/790dfdd3-8fed-4f01-b410-d91d9367ec3c">

5. 서버 패킷 전달. (출발, 목적 IP와 메세지를 전달한다.)

<img width="639" alt="스크린샷 2023-08-02 오후 4 50 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/7e79065e-6438-4d9e-b247-a79b8fc47a23">

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

<img width="344" alt="스크린샷 2023-08-02 오후 4 51 00" src="https://github.com/Hoya324/SpringNote/assets/96857599/81180b64-9c00-4887-a1ce-9c4935291c7b">

### 프로토콜 계층

<img width="533" alt="스크린샷 2023-08-02 오후 4 51 12" src="https://github.com/Hoya324/SpringNote/assets/96857599/33ad31b9-c7f8-42d7-9065-faab58d620c2">

<img width="593" alt="스크린샷 2023-08-02 오후 4 51 39" src="https://github.com/Hoya324/SpringNote/assets/96857599/3d09c7b3-3c37-40e9-8236-e80f1b54cd2d">

### IP 패킷과 TCP/IP 패킷 정보의 차이

**IP 패킷 정보**
- 출발지 IP, 목적지 IP, 등을 가짐
- 패킷(packet:소포)

<img width="540" alt="스크린샷 2023-08-02 오후 4 51 55" src="https://github.com/Hoya324/SpringNote/assets/96857599/12cffb38-c3e9-4ec1-af8c-49d8920a0c51">

**TCP/IP 패킷 정보**
- 출발지 PORT, 목적지 PORT 전송 제어, 순서, 검증 정보 등
- 순서 제어 문제 해결

<img width="576" alt="스크린샷 2023-08-02 오후 4 52 07" src="https://github.com/Hoya324/SpringNote/assets/96857599/181ccb87-43b6-46e3-b0a9-3ffb888d1406">

### TCP(Transmission Control Protocol/전송 제어 프로토콜) 특징
- 연결지향 -> TCP 3 way handshake (가상 연결)
- 데이터 전달 보증
- 순서 보장
- 신뢰할 수 있는 프로토콜
- 현재는 대부분 TCP 사용

**1. TCP 3 way handshake**

<img width="591" alt="스크린샷 2023-08-02 오후 4 53 16" src="https://github.com/Hoya324/SpringNote/assets/96857599/1487fe20-4ec4-456f-aba5-5b8eb0af072c">

- 상대가 접속이 가능한 상태인지 확인하고 응답을 보낼지 말지 결정함.
- 가상 연결 -> 물리적인 연결이 아닌, 논리적인 연결임.

**2. 데이터 전달 보증**

<img width="552" alt="스크린샷 2023-08-02 오후 4 53 26" src="https://github.com/Hoya324/SpringNote/assets/96857599/62ef9341-89ee-43c7-b806-9df09679873d">

- 수신측에 TCP 세그먼트가 도착하면 수신 측은 송신 측에게 도착했다고 알림. 이것을 ACK라고 함.
- TCP 헤더에 ACK 관련 정보를 넣은 TCP 세그먼트를 반환함.

**3. 순서 보장**

<img width="523" alt="스크린샷 2023-08-02 오후 4 53 37" src="https://github.com/Hoya324/SpringNote/assets/96857599/8f43d2b9-238c-4b0f-866e-49eaf6a825d3">

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

<img width="541" alt="스크린샷 2023-08-02 오후 4 54 12" src="https://github.com/Hoya324/SpringNote/assets/96857599/45ff3a96-d6ed-4b8e-a0be-32a8943889fe">

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

<img width="607" alt="스크린샷 2023-08-02 오후 4 55 05" src="https://github.com/Hoya324/SpringNote/assets/96857599/2ec6e4de-f8ee-4f6f-80a1-461b5f93d4c4">

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

<img width="490" alt="스크린샷 2023-08-02 오후 4 56 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/9cd0b5e5-551d-4240-b947-85685a42897d">

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

<img width="536" alt="스크린샷 2023-08-02 오후 4 57 04" src="https://github.com/Hoya324/SpringNote/assets/96857599/2188f1ab-6dc4-44d0-ad69-65025cc37393">

- 클라이언트2가 연결을 해도 클라이언트1과의 연결은 유지됨.

<img width="537" alt="스크린샷 2023-08-02 오후 4 57 31" src="https://github.com/Hoya324/SpringNote/assets/96857599/a675bb08-2092-45ef-a817-3f74414b2a84">

- 클라이언트3을 연결할 때에도 클라이언트 1, 2의 연결은 유지되므로 서버 자원을 소모하게 됨.

<img width="589" alt="스크린샷 2023-08-02 오후 4 57 49" src="https://github.com/Hoya324/SpringNote/assets/96857599/aaba04af-c373-41f7-b82d-6d0943b24d1f">

- 재연결

<img width="594" alt="스크린샷 2023-08-02 오후 4 58 08" src="https://github.com/Hoya324/SpringNote/assets/96857599/4cdfffde-b96b-46cb-9253-8248b0cf8ab6">

**연결을 유지하지 않는 모델**

- TCP/IP 연결 후 필요 없어진 연결은 끊어짐

<img width="572" alt="스크린샷 2023-08-02 오후 4 58 30" src="https://github.com/Hoya324/SpringNote/assets/96857599/d55ee098-f79b-4fea-975a-93b9b14384c2">

- 필요할 때 연결하고, 응답후 끊어지면 서버의 자원을 최소한으로 사용함.

<img width="566" alt="스크린샷 2023-08-02 오후 4 58 37" src="https://github.com/Hoya324/SpringNote/assets/96857599/a185bfa2-9bdc-493c-87b9-15bb52288e13">

<img width="555" alt="스크린샷 2023-08-02 오후 4 58 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/1cb9163f-f61b-4958-a715-0cd895bca34f">

<img width="593" alt="스크린샷 2023-08-02 오후 4 58 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/b86c7e68-6d80-4e4a-b734-48b2fd0b405c">

<img width="601" alt="스크린샷 2023-08-02 오후 4 58 58" src="https://github.com/Hoya324/SpringNote/assets/96857599/22c34e0a-2beb-41ef-aabe-d222cd75d822">

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
 
<img width="363" alt="스크린샷 2023-08-02 오후 4 59 23" src="https://github.com/Hoya324/SpringNote/assets/96857599/776bae38-5268-4c23-aca0-79de423c8037">

<img width="360" alt="스크린샷 2023-08-02 오후 4 59 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/a750ee0e-6370-4bb9-acf3-103411c8fb95">

**스테이스리스를 기억하자**
- 서버 개발자들이 어려워하는 업무
- 정말 같은 시간에 딱 맞추어 발생하는 대용량 트래픽
  - 예) 선착순 이벤트, 명절 KTX 예약, 학과 수업 등록
  - 예) 저녁 6:00 선착순 1000명 치킨 할인 이벤트 -> 수만명 동시 요청
-> 맨 첫 페이지에 html로만 이루어진 페이지를 두어 클라이언트들의 클릭을 분산시키는 방법이 있다.

## HTTP 메시지

<img width="644" alt="스크린샷 2023-08-02 오후 4 59 45" src="https://github.com/Hoya324/SpringNote/assets/96857599/a577ffcf-fca4-4f62-9793-12efd47b1ea4">

### 시작 라인

<img width="288" alt="스크린샷 2023-08-02 오후 5 00 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/745beebb-b298-4160-8feb-4f1a3b28ffcf">

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

<img width="597" alt="스크린샷 2023-08-02 오후 5 00 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/8f687996-83a4-410a-93e6-2d9405977be7">

**용도**
- HTTP 전송에 필요한 모든 부가정보
- 예) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...
- 표준 헤더가 너무 많음
  - [List_of_HTTP_header_fields](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields)
- 필요시 임의의 헤더 추가 가능
  - helloworld: hihi

### HTTP 메시지 바디

<img width="291" alt="스크린샷 2023-08-02 오후 5 01 02" src="https://github.com/Hoya324/SpringNote/assets/96857599/a5fb9db0-ef21-499b-9cdd-daf06a8e91ba">

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

<img width="351" alt="스크린샷 2023-08-02 오후 5 01 41" src="https://github.com/Hoya324/SpringNote/assets/96857599/d5c47fb9-4405-4ce3-a8cb-d9a00a0ebcf0">

- 리소스 조회
- 서버에 전달하고 싶은 데이터는 query(쿼리 파라미터, 쿼리 스트링)를 통해서 전달
- 메시지 바디를 사용해서 데이터를 전달할 수 있지만, 지원하지 않는 곳이 많아서 권장하지 않음

**리소스 조회1 - 메시지 전달**

<img width="598" alt="스크린샷 2023-08-02 오후 5 01 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/bbdc7293-4bc4-4f27-924c-a7f8728f8967">

**리소스 조회2 - 서버도착**

<img width="604" alt="스크린샷 2023-08-02 오후 5 02 16" src="https://github.com/Hoya324/SpringNote/assets/96857599/8dfec5b3-3678-4a88-bb2f-a6991a4af708">

**리소스 조회3 - 응답 데이터**

<img width="578" alt="스크린샷 2023-08-02 오후 5 02 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/d458840c-7685-4686-91f2-39c12428a445">

### POST

**클라이언트 측에서 데이터를 전달하여 처리를 요청하는 것**

<img width="228" alt="스크린샷 2023-08-02 오후 5 02 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/1d74475e-3052-4935-b9f0-9a82524665de">

- 요청 데이터 처리
- 메시지 바디를 통해 서버로 요청 데이터 전달
- 서버는 요청 데이터를 처리
  - 메시지 바디를 통해 들어온 데이터를 처리하는 모든 기능을 수행한다.
- 주로 전달된 데이터로 신규 리소스 등록, 프로세스 처리에 사용

**메시지 전달 - `/members`로 오면 전달된 데이터를 어떤 방식으로 처리할지 미리 약속함.**

<img width="532" alt="스크린샷 2023-08-02 오후 5 03 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/5b23225c-c8a3-4863-865b-528e995bde73">

**신규 리소스 생성 - 신규 리소스 식별자 생성**

<img width="603" alt="스크린샷 2023-08-02 오후 5 04 02" src="https://github.com/Hoya324/SpringNote/assets/96857599/026adcbd-9372-4deb-b629-b821c8a69a43">

**응답 데이터**

<img width="555" alt="스크린샷 2023-08-02 오후 5 04 14" src="https://github.com/Hoya324/SpringNote/assets/96857599/001d8c88-a203-47b7-96ce-bda36568a34d">

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

<img width="227" alt="스크린샷 2023-08-02 오후 5 04 26" src="https://github.com/Hoya324/SpringNote/assets/96857599/357ff198-ed5c-4d53-be54-1e5ef07f8e94">

- **리소스를 완전히 대체**
	- 리소스가 있으면 대체 
	- 리소스가 없으면 생성 
	- 쉽게 이야기해서 덮어버림
- **중요! 클라이언트가 리소스를 식별**
	- 클라이언트가 리소스 위치를 알고 URI 지정 -> 구체적인 리소스를 지정한다는 점에서 POST와의 차이를 보임
	- POST와 차이점

### PATCH

<img width="221" alt="스크린샷 2023-08-02 오후 5 05 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/05c0638b-436a-4f1f-96f8-686b12be4aa7">

- 리소스를 부분 변경하는 것
- 특정 HTTP가 PATCH를 못 받아들이는 경우 POST를 사용하면 됨

### DELETE

<img width="226" alt="스크린샷 2023-08-02 오후 5 05 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/61ef5f95-156d-40c6-80ef-3893d4d4fead">

- 리소스 제거

### HTTP 메서드의 속성
- **안전(Safe Methods)**
- **멱등(Idempotent Methods)**
- **캐시가능(Cacheable Methods)**

<img width="563" alt="스크린샷 2023-08-02 오후 5 06 01" src="https://github.com/Hoya324/SpringNote/assets/96857599/f747bb79-8969-46fd-8ffc-fe7521631fbb">

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

<img width="622" alt="스크린샷 2023-08-02 오후 5 06 41" src="https://github.com/Hoya324/SpringNote/assets/96857599/a62dcdba-f925-4dee-bcac-5136339c66c4">

**정리**
- 이미지, 정적 텍스트 문서
- 조회는 GET 사용
- 정적 데이터는 일반적으로 쿼리 파라미터 없이 리소스 경로로 단순하게 조회 가능

### 동적 데이터 조회
- 쿼리 파라미터 사용

<img width="604" alt="스크린샷 2023-08-02 오후 5 06 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/2593cef3-3a66-4f0f-9bb8-80ead966b881">

**정리**
- 주로 검색, 게시판 목록에서 정렬 필터(검색어)
- 조회 조건을 줄여주는 필터, 조회 결과를 정렬하는 정렬 조건에 주로 사용 
- 조회는 GET 사용
- GET은 쿼리 파라미터 사용해서 데이터를 전달

### HTML Form 데이터 전송
- POST 전송 -> 저장

<img width="637" alt="스크린샷 2023-08-02 오후 5 07 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/cc207957-379c-4eaf-b62a-e4858e7fdd37">

- GET 전송 -> 저장

<img width="623" alt="스크린샷 2023-08-02 오후 5 07 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/834228d1-5d60-4a76-b274-3c3031c04751">

- GET 전송 -> 조회

<img width="633" alt="스크린샷 2023-08-02 오후 5 07 48" src="https://github.com/Hoya324/SpringNote/assets/96857599/3f078e9a-13a7-499b-9f40-bef088f24bef">

> 주의! GET은 조회에만 사용!
> 
> 리소스 변경이 발생하는 곳에 사용하면 안됨!

- multipart/form-data (주로 바이너리 데이터를 전송할 때 사용)

<img width="638" alt="스크린샷 2023-08-02 오후 5 08 06" src="https://github.com/Hoya324/SpringNote/assets/96857599/02bb75f7-b336-4302-b1ca-2d2bca80a643">

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

<img width="539" alt="스크린샷 2023-08-02 오후 5 08 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/82b96565-f634-4a14-9d9f-9e46179bfd38">

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

<img width="549" alt="스크린샷 2023-08-02 오후 5 09 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/9882ca36-ed35-4ef0-837f-f0bc5519d274">

**201 Created**
- POST 요청이므로 서버에서 자원을 생성하고, 자원에 대한 URI도 관리

<img width="653" alt="스크린샷 2023-08-02 오후 5 09 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/71e0b9eb-3073-4300-bc8b-3a3e50bac2d9">

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

<img width="560" alt="스크린샷 2023-08-02 오후 5 10 14" src="https://github.com/Hoya324/SpringNote/assets/96857599/6765bec6-f762-4d55-af05-85530571f4ca">

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

<img width="555" alt="스크린샷 2023-08-02 오후 5 10 46" src="https://github.com/Hoya324/SpringNote/assets/96857599/592c2de0-5117-4ac7-92a0-32728b988b75">

- **308 Permanent Redirect**
	- 301과 기능은 같음
	- **리다이렉트 시 요청 메서드와 본문 유지(처음 POST를 보내면 리다이렉트도 POST 유지)**

<img width="561" alt="스크린샷 2023-08-02 오후 5 11 01" src="https://github.com/Hoya324/SpringNote/assets/96857599/a75435cf-afa7-480a-a2d2-278bc66fbe88">

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

<img width="662" alt="스크린샷 2023-08-02 오후 5 11 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/441780e7-398e-4731-a4cc-56e9e0e407f8">

> 서버 차원에서 중복되 주문번호를 제거하는 것이 맞지만, 클라이언트 차원에서도 한번 방지하는 것도 좋다.

**PRG 사용**
- POST로 주문후에 새로 고침으로 인한 중복 주문 방지 
- POST로 주문후에 주문 결과 화면을 GET 메서드로 리다이렉트 
- 새로고침해도 결과 화면을 GET으로 조회
- 중복 주문 대신에 결과 화면만 GET으로 다시 요청

**리다이렉션 302 Found 또는 303 See Other를 통해 메서드를 Get으로 변경**

<img width="668" alt="스크린샷 2023-08-02 오후 5 11 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/460b4c7e-c4f5-4fe9-9be3-7a1aa3890be5">

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

<img width="609" alt="스크린샷 2023-08-02 오후 5 12 56" src="https://github.com/Hoya324/SpringNote/assets/96857599/548c7c85-b3b4-458d-9cb4-f9dd588362dc">

**용도**
- HTTP 전송에 필요한 모든 부가정보
- 예) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트(브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...
- 표준 헤더가 너무 많음
  - [List_of_HTTP_header_fields](https://en.wikipedia.org/wiki/List_of_HTTP_header_fields)
- 필요시 임의의 헤더 추가 가능
  - helloworld: hihi

### RFC2616(과거)

**HTTP 헤더 - 분류**

<img width="397" alt="스크린샷 2023-08-02 오후 5 16 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/9602e0ad-a48a-4492-8680-7826b5543dae">

- 헤더 분류
	- **General 헤더**: 메시지 전체에 적용되는 정보, 예) Connection: close
	- **Request 헤더**: 요청 정보, 예) User-Agent: Mozilla/5.0 (Macintosh; ..)
	- **Response 헤더**: 응답 정보, 예) Server: Apache
	- **Entity 헤더**: 엔티티 바디 정보, 예) Content-Type: text/html, Content-Length: 3423

**HTTP Body - message body**

<img width="396" alt="스크린샷 2023-08-02 오후 5 16 49" src="https://github.com/Hoya324/SpringNote/assets/96857599/e4ea994f-3471-45de-9c88-aa21dc07b4fd">

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

<img width="399" alt="스크린샷 2023-08-02 오후 5 17 08" src="https://github.com/Hoya324/SpringNote/assets/96857599/888353d5-44c6-47f6-93ea-b8d36eac680e">

- 메시지 본문(message body)을 통해 표현 데이터 전달
- 메시지 본문 = 페이로드(payload)
- **표현**은 요청이나 응답에서 전달할 실제 데이터
- **표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공**
	- 데이터 유형(html, json), 데이터 길이, 압축 정보 등등

> 참고: 표현 헤더는 표현 메타데이터와, 페이로드 메시지를 구분해야 하지만, 여기서는 생략

### 표현

<img width="341" alt="스크린샷 2023-08-02 오후 5 17 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/ba5b8400-e152-459d-ace2-c16c25c3f6ba">

- Content-Type: 표현 데이터의 형식
- Content-Encoding: 표현 데이터의 압축 방식
- Content-Language: 표현 데이터의 자연 언어
- Content-Length: 표현 데이터의 길이

- 표현 헤더는 전송, 응답 둘다 사용

### Content-Type
**표현 데이터의 형식 설명**
- application/json은 기본이 UTF-8임

<img width="289" alt="스크린샷 2023-08-02 오후 5 17 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/0e68d978-a106-4381-9646-ec8b1bc77c70">

- 미디어 타입, 문자 인코딩
- 예)
	- text/html; charset=utf-8
	- application/json
  - image/png

### Content-Encoding (압축할 때)
**표현 데이터 인코딩**

<img width="285" alt="스크린샷 2023-08-02 오후 5 18 15" src="https://github.com/Hoya324/SpringNote/assets/96857599/d3f9e962-c027-4ca5-86ed-d876176f2633">

- 표현 데이터를 압축하기 위해 사용
- 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
- 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
- 예)
	- gizp
	- deflate
	- identity

### Content-Language (표현 데이터의 자연어 표시)
**표현 데이터의 자연 언어**

<img width="249" alt="스크린샷 2023-08-02 오후 5 18 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/8dea2c08-d969-4e23-bb9c-825bd4f55497">

- 표현 데이터의 자연 언어를 표현
- 예)
	- ko
	- en
	- en-US

### Content-Length
**표현 데이터의 길이**

<img width="244" alt="스크린샷 2023-08-02 오후 5 18 43" src="https://github.com/Hoya324/SpringNote/assets/96857599/617cbee3-85ae-4bc7-927a-8f1167166aee">

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

<img width="503" alt="스크린샷 2023-08-02 오후 5 19 06" src="https://github.com/Hoya324/SpringNote/assets/96857599/ad3fa25f-b24a-46cc-87b5-1025ab607e48">

**Accept-Language 적용 후**

-> **Accept-Language가 있으면 선호하는 언어가 한국어인 것을 HTTP Header로 처리할 수 있음.**

<img width="496" alt="스크린샷 2023-08-02 오후 5 19 19" src="https://github.com/Hoya324/SpringNote/assets/96857599/ab401903-b43b-42b8-85e8-1d94dc922422">

**Accept-Language 복잡한 예시**

-> 복잡한 예시: 한국어를 희망하는 클라이언트가 독일어와 영어를 지원하는 서버에서 기본 설정인 독일어보단 영어를 희망함.

<img width="505" alt="스크린샷 2023-08-02 오후 5 19 34" src="https://github.com/Hoya324/SpringNote/assets/96857599/66be346b-1eff-409b-8f96-00478f318273">

**협상과 우선순위1** 
-> Quality Values(q)

<img width="366" alt="스크린샷 2023-08-02 오후 5 19 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/c82e7376-117e-41c4-8c0e-b69b7f96c2e6">

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

<img width="359" alt="스크린샷 2023-08-02 오후 5 21 06" src="https://github.com/Hoya324/SpringNote/assets/96857599/bb06e314-ecf7-412a-a9ec-277facafb58a">

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

<img width="177" alt="스크린샷 2023-08-02 오후 5 21 20" src="https://github.com/Hoya324/SpringNote/assets/96857599/bd1a41e8-ab34-4ac8-a019-f9d7172240e7">

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

<img width="599" alt="스크린샷 2023-08-02 오후 5 21 37" src="https://github.com/Hoya324/SpringNote/assets/96857599/6fef6558-b93b-4e0b-9653-33933848856d">

### 압축 전송 Content-Encoding

-> **Content-Encoding을 꼭 입력해야함**

<img width="582" alt="스크린샷 2023-08-02 오후 5 21 48" src="https://github.com/Hoya324/SpringNote/assets/96857599/7555b6ca-c5b9-45dd-9284-48217e227a95">

### 분할 전송 Transfer-Encoding

-> **Content-Length를 보내면 안 됨. (예상이 되지 않음, 청크들마다 길이가 나옴.)**

<img width="590" alt="스크린샷 2023-08-02 오후 5 23 19" src="https://github.com/Hoya324/SpringNote/assets/96857599/76ca1a40-8930-431d-9575-1de722f7ff00">

### 범위 전송 Range, Content-Range

-> **중간에 끊기면 처음부터 다시 받지 않도록 범위를 설정할 수 있음.**

<img width="561" alt="스크린샷 2023-08-02 오후 5 23 44" src="https://github.com/Hoya324/SpringNote/assets/96857599/b4ce3c0d-eed4-4bad-8917-a0ca52a86637">

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

### User-Agent

-> **유저 에이전트 애플리케이션 정보**

- user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/ 537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36
- 클리이언트의 애플리케이션 정보(웹 브라우저 정보, 등등) 
- 통계 정보
- 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능 
- 요청에서 사용

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

<img width="278" alt="스크린샷 2023-08-02 오후 5 24 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/ab374b06-5b3b-4c1b-8674-1699f73ff2e6">

- 요청에서 사용
- 필수
- 하나의 서버가 여러 도메인을 처리해야 할 때
- 하나의 IP 주소에 여러 도메인이 적용되어 있을 때

**1. 같은 IP를 공유하는 여러 도메인이 있다.**

<img width="638" alt="스크린샷 2023-08-02 오후 5 25 00" src="https://github.com/Hoya324/SpringNote/assets/96857599/bec030c4-bf9e-42f7-aec5-50109ad5998e">


**2. Host가 없으면, 어느 도메인으로 가야할지 알 수 없다.**

<img width="608" alt="스크린샷 2023-08-02 오후 5 25 18" src="https://github.com/Hoya324/SpringNote/assets/96857599/a0037348-27d5-487f-bb25-983b7fbc235b">

**3. Host 정보 필수**

<img width="608" alt="스크린샷 2023-08-02 오후 5 25 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/3d85d62d-1c8d-4132-861a-e933327d7d8f">

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

<img width="574" alt="스크린샷 2023-08-02 오후 5 25 53" src="https://github.com/Hoya324/SpringNote/assets/96857599/29005689-539f-4412-9714-4d8d0641fccf">

**2. 로그인**

<img width="559" alt="스크린샷 2023-08-02 오후 5 26 03" src="https://github.com/Hoya324/SpringNote/assets/96857599/914b0b4d-e1b3-44a3-8823-48fe1421bf8c">

**3. 로그인 이후 welcome 페이지 접근 (안녕하세요. 홍길동님을 기대했으나, 안녕하세요 손님이 다시 나옴)**

<img width="585" alt="스크린샷 2023-08-02 오후 5 26 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/bcea972a-12c5-43a1-8643-32a12c48be53">


> ⭐️**Stateless**⭐️
>
> - HTTP는 기본적으로 무상태(Stateless) 프로토콜이다.
> - 클라이언트와 서버가 요청과 응답을 주고 받으면 연결이 끊어진다. 
> - 클라이언트가 다시 요청하면 서버는 이전 요청을 기억하지 못한다. 
> - 클라이언트와 서버는 서로 상태를 유지하지 않는다.
> 

**4. 대안- 모든 요청에 사용자 정보 포함**

<img width="568" alt="스크린샷 2023-08-02 오후 7 00 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/09d09d7e-f1f6-4e00-bb8f-3d6a4182b976">

**모든 링크에 사용자 정보를 포함하면, 보안상의 문제부터 여러 문제점이 발생함.**

<img width="372" alt="스크린샷 2023-08-02 오후 7 00 59" src="https://github.com/Hoya324/SpringNote/assets/96857599/52a37263-b45b-445d-8c0e-6a7e93606c2f">

> **모든 요청에 정보를 넘기는 문제**
> - 모든 요청에 사용자 정보가 포함되도록 개발 해야함 
> - 브라우저를 완전히 종료하고 다시 열면?
> 쿠키를 사용하면 해결

### 쿠키 사용

**1. 로그인**

-> **웹브라우저 내부에 저장있는 쿠키 저장소에에 user=홍길동을 저장**

<img width="559" alt="스크린샷 2023-08-02 오후 7 01 23" src="https://github.com/Hoya324/SpringNote/assets/96857599/3041caa2-e29f-4c70-9e82-0a66353fb98e">

**2. 로그인 이후 welcome 페이지 접근**

-> **웹브라우저는 쿠키 저장소를 무조건 확인함.**

<img width="566" alt="스크린샷 2023-08-02 오후 7 01 46" src="https://github.com/Hoya324/SpringNote/assets/96857599/0b77b4b1-ccfc-4b18-8bd4-8350178a49cd">

**3. 모든 요청에 쿠키 정보 자동 포함**

<img width="455" alt="스크린샷 2023-08-02 오후 7 01 56" src="https://github.com/Hoya324/SpringNote/assets/96857599/ba5f33e4-6fa6-47ce-8527-567b4976d8bf">

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

<img width="594" alt="스크린샷 2023-08-02 오후 7 02 27" src="https://github.com/Hoya324/SpringNote/assets/96857599/6e0b9152-1236-42f0-84a0-86123a6d67cf">

<img width="587" alt="스크린샷 2023-08-02 오후 7 03 06" src="https://github.com/Hoya324/SpringNote/assets/96857599/aece33f1-12d9-4348-87e1-602a21cbc6ce">

**두 번째 요청**

<img width="585" alt="스크린샷 2023-08-02 오후 7 03 35" src="https://github.com/Hoya324/SpringNote/assets/96857599/4db728ea-6a14-487a-bcc6-da055a3389ee">

<img width="589" alt="스크린샷 2023-08-02 오후 7 03 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/224e6357-a731-41c0-b097-2e1491e81341">

**캐시가 없을 때 특징**
- 데이터가 변경되지 않아도 계속 네트워크를 통해서 데이터를 다운로드 받아야 한다. 
- 인터넷 네트워크는 매우 느리고 비싸다.
- 브라우저 로딩 속도가 느리다.
- 느린 사용자 경험

### 캐시 적용

**첫 번째 요청 - 캐시의 생명 주기를 설정하고 데이터를 저장해둠**

<img width="590" alt="스크린샷 2023-08-02 오후 7 04 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/93b8c246-9000-4916-a426-2f3ac7fc0bb4">

<img width="564" alt="스크린샷 2023-08-02 오후 7 05 11" src="https://github.com/Hoya324/SpringNote/assets/96857599/0ab8acc4-57c2-4e4f-a422-c75c9db7aeec">

**두 번째 요청 - 브라우저 캐시에 저장되어있는 데이터는 바로 응답함.**

<img width="492" alt="스크린샷 2023-08-02 오후 7 05 42" src="https://github.com/Hoya324/SpringNote/assets/96857599/5b829e41-e1ec-4064-bdb8-229202950ad8">

<img width="500" alt="스크린샷 2023-08-02 오후 7 05 51" src="https://github.com/Hoya324/SpringNote/assets/96857599/59e15a94-3bd0-4eef-a7e6-d215a769194f">

**캐시를 적용했을 때 특징**
- 캐시 덕분에 캐시 가능 시간동안 네트워크를 사용하지 않아도 된다. 
- 비싼 네트워크 사용량을 줄일 수 있다.
- 브라우저 로딩 속도가 매우 빠르다.
- 빠른 사용자 경험
 
**세 번째 요청 - 캐시 시간 초과**

<img width="494" alt="스크린샷 2023-08-02 오후 7 06 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/2a05a33e-287f-4fa2-8779-6d2f0fb28892">

<img width="588" alt="스크린샷 2023-08-02 오후 7 06 22" src="https://github.com/Hoya324/SpringNote/assets/96857599/44dcbf10-1d65-45e2-967f-c94439b45ed5">

<img width="562" alt="스크린샷 2023-08-02 오후 7 06 58" src="https://github.com/Hoya324/SpringNote/assets/96857599/7f63e974-550e-43bd-b692-a003d6c673a1">

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

<img width="585" alt="스크린샷 2023-08-02 오후 7 14 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/d6898b69-bcf4-43d8-ad99-b76e0c2e8821">

**캐시 유효시간 안에는 그대로 캐시 데이터 사용**

<img width="582" alt="스크린샷 2023-08-02 오후 7 08 36" src="https://github.com/Hoya324/SpringNote/assets/96857599/ce14aa8f-6da1-4cbd-b6e1-0986ab1dcefa">

**두 번째 요청 - 캐시 시간 초과**

<img width="497" alt="스크린샷 2023-08-02 오후 7 09 04" src="https://github.com/Hoya324/SpringNote/assets/96857599/2e5195b5-67d9-4e7b-8ed7-6cc4334214d2">

<img width="500" alt="스크린샷 2023-08-02 오후 7 09 11" src="https://github.com/Hoya324/SpringNote/assets/96857599/1c46b45c-68e1-4e4d-8d9f-e6dd21f538e7">

**데이터의 최종 수정일을 확인함.**

<img width="491" alt="스크린샷 2023-08-02 오후 7 09 29" src="https://github.com/Hoya324/SpringNote/assets/96857599/f6c6b1bc-46c1-4519-8c8c-cea37f905047">

<img width="497" alt="스크린샷 2023-08-02 오후 7 09 46" src="https://github.com/Hoya324/SpringNote/assets/96857599/2b53fda3-bd2f-40e1-9791-5c2ee3a3d77f">

**304 Not Modified를 내보낸다.**
- 단, HTTP Body가 없이 내보낸다.

<img width="624" alt="스크린샷 2023-08-02 오후 7 10 09" src="https://github.com/Hoya324/SpringNote/assets/96857599/6d76fa33-149e-408f-82f8-4fa4646b271a">

**응답 결과를 재사용하고, 헤더 데이터를 갱신한다.**

<img width="615" alt="스크린샷 2023-08-02 오후 7 10 37" src="https://github.com/Hoya324/SpringNote/assets/96857599/b634e31c-49cf-48d8-ae0b-9244653d7373">

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

<img width="582" alt="스크린샷 2023-08-02 오후 7 16 25" src="https://github.com/Hoya324/SpringNote/assets/96857599/e2304959-b182-4769-b505-a9b07bf0beba">

<img width="580" alt="스크린샷 2023-08-02 오후 7 16 54" src="https://github.com/Hoya324/SpringNote/assets/96857599/f71b63c3-9b59-4d30-8f00-62b0f3d1b83f">

**두 번째 요청 - 캐시 시간 초과**

<img width="506" alt="스크린샷 2023-08-02 오후 7 17 16" src="https://github.com/Hoya324/SpringNote/assets/96857599/13b57be7-9f0d-4967-a069-521770c0ef97">

**요청한 이미지가 가진 ETag와 캐시가 가진 ETag와 동일함**<br>
**`If-None-Match: "aaaaaaaaaa"` 이기 때문에 일치하지 않으면 성공, 일치하면 실패이다.**

<img width="493" alt="스크린샷 2023-08-02 오후 7 17 31" src="https://github.com/Hoya324/SpringNote/assets/96857599/eeb7ea65-91ff-4779-9adc-f149a6f41a9f">

<img width="496" alt="스크린샷 2023-08-02 오후 7 17 38" src="https://github.com/Hoya324/SpringNote/assets/96857599/9606561e-83ce-48e0-a919-45e5c34de713">

**데이터가 수정되지 않음**

<img width="497" alt="스크린샷 2023-08-02 오후 7 17 46" src="https://github.com/Hoya324/SpringNote/assets/96857599/42ff5c0a-75e1-4d64-920d-11b2789dbbc7">

**실패(때문에 304 Not Modified가 뜸)**

<img width="606" alt="스크린샷 2023-08-02 오후 7 18 00" src="https://github.com/Hoya324/SpringNote/assets/96857599/45f368a7-f0c1-4b8a-ab83-3a2f97f98cf6">

**HTTP Body를 제외하고 전송**

<img width="612" alt="스크린샷 2023-08-02 오후 7 18 41" src="https://github.com/Hoya324/SpringNote/assets/96857599/ac78ff2a-668e-4f81-a99e-07d086a57dca">

<img width="570" alt="스크린샷 2023-08-02 오후 7 18 52" src="https://github.com/Hoya324/SpringNote/assets/96857599/5d40a734-e0a4-41fd-83c3-5aded0aa77da">

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

<img width="552" alt="스크린샷 2023-08-02 오후 7 19 40" src="https://github.com/Hoya324/SpringNote/assets/96857599/75416de2-065e-44fd-a571-0adc51f19814">

### 프록시 캐시 도입

**첫 번째 요청**

<img width="605" alt="스크린샷 2023-08-02 오후 7 19 52" src="https://github.com/Hoya324/SpringNote/assets/96857599/7d37b01b-913e-4b7e-884a-fbae2e5c7970">

<img width="615" alt="스크린샷 2023-08-02 오후 7 20 00" src="https://github.com/Hoya324/SpringNote/assets/96857599/ef584a13-d8da-475b-b2c6-425dabc18f17">

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

<img width="605" alt="스크린샷 2023-08-02 오후 7 20 57" src="https://github.com/Hoya324/SpringNote/assets/96857599/0931b856-6109-4200-b951-1d0236ff3ee9">

- 만약, 순간적으로 네트워크가 단절되어 원 서버에 접근이 불가능하다면?
**Error or 200 OK (오류 보다는 오래된 데이터라도 보여주자)**

<img width="592" alt="스크린샷 2023-08-02 오후 7 20 46" src="https://github.com/Hoya324/SpringNote/assets/96857599/b6f90d86-9271-49f9-bee7-c0e940cee0b3">

**must-revalidate는 문제가 생기면 오류가 발생하도록 함.**

<img width="585" alt="스크린샷 2023-08-02 오후 7 21 13" src="https://github.com/Hoya324/SpringNote/assets/96857599/75d243ce-884f-4e2f-9652-2778a61e9522">

### [Internet Engineering Task Force (IETF) - HTTP 추가적인 공부](https://datatracker.ietf.org/doc/html/rfc7230)

## Reference

- [모든 개발자를 위한 HTTP 웹 기본 지식/김영한](https://www.inflearn.com/course/http-%EC%9B%B9-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC/dashboard)
- [REST API란, HTTP Method](https://velog.io/@ellyheetov/REST-API)
