📌 web_socket-spring
-
* 스프링 부트로 작성된 웹 소켓 프로젝트 예제 입니다.
* [프론트엔드 서버](https://github.com/donghyeon0725/web_socket_vue) 를 필요로 합니다.

<details>
    <summary>펼쳐보기</summary>

요청의 처리
-

📌 step 0 : 필요한 디팬던시 추가
-
```xml
<!-- 스프링 시큐리티 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>

<!-- 토큰과 json 변환을 위한 클래스 -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.10.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.10.5</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.10.5</version>
    <scope>runtime</scope>
</dependency>
```

📌 step 1 : 인증을 위해 토큰 & 서버 url 정보 발급받기
-
* 웹소켓 서버와 연결을 하기 위해서 사용할 토큰과 연결 주소(url)를 발급 받습니다.
* 이때 토큰 매니저를 사용합니다. [TokenManager](./src/main/java/com/websocket/web_socket/security/TokenManager.java)
* 시큐리티 기반이기 때문에 다음과 같은 설정이 필요합니다. [SecurityConfig](./src/main/java/com/websocket/web_socket/security/SecurityConfig.java) 
* 다른 origin에서 소켓통신을 요청한다면 다음과 같은 설정도 필요합니다. [CORSConfig](./src/main/java/com/websocket/web_socket/security/CORSConfig.java) 
* 클래스 : [MeApiController](./src/main/java/com/websocket/web_socket/MeApiController.java)

📌 step 2 : 클라이언트가 소켓 통신을 요청했을 때, 처리할 클래스 정하기
-
* 소켓 요청이 들어왔을 때 해당 처리를 어디에서 할지 정하는 설정파일을 작성합니다.
* 이 때 이 작성파일엔 CROS 관련 설정 내용 또한 들어갑니다.
* 클래스 : [WebSocketConfiguration](./src/main/java/com/websocket/web_socket/WebSocketConfiguration.java)


📌 step 3 : 클라이언트 요청이 들어왔을 때, 인증, 커넥션 관리를 할 디스패처 작성
-
* 소켓 요청이 들어왔을 때 해당 요청을 핸들링할 핸들러를 작성하기 위해서 TextWebSocketHandler을 상속 받아 클래스를 하나 작성합니다. 그리고 이제부터 이것을 디스패처라고 부르겠습니다. (컨트롤러와 비슷한 개념)
* 클래스 [WebSocketRequestDispatcher](./src/main/java/com/websocket/web_socket/socket/WebSocketRequestDispatcher.java)
* 해당 클래스의 역할은 커넥션 연결 요청이 들어왔을 때 인증을 수행하고 연결을 해주며, 연결이 끊겼을 때 구독을 해제합니다.
* 또한 메세지가 들어왔을 때 해당 메세지를 처리할 적절한 핸들러를 찾아 줍니다.


📌 step 4 : 사용자의 웹소켓 세션(연결정보)을 쉽게 관리하기 위한 wrapper 클래스 작성
-

* 스프링의 WebSocketSession 클래스는 웹소켓 통신을 가능하게 해주는 api 입니다.
* 해당 클래스를 wrap 해서 사용하기 쉽도록 합니다.
* 세션에서 토큰을 추출하거나, 각각의 세션에 메세지를 보내는 역할을 수행합니다.
* 클래스 [RealTimeSession](./src/main/java/com/websocket/web_socket/socket/RealTimeSession.java)


📌 step 5 : 구독을 한 클라이언트를 별도로 관리할 클래스 작성
-
* 연결에 성공한 세션 중, 구독을 해서 실시간 작동을 수행할 클라이언트의 세션을 별도로 관리하기 위한 클래스 입니다.
* 사용자 별 구독한 채널의 정보와 채널 별로 구독한 사용자 정보를 둘다 가지고 있기 때문에 체널 별로 메세징을 할 수 있게 해줍니다.
* 구독을 했는데, 채널이 없는 경우 발행 또한 수행합니다.
* 클래스 [SubscriptionHub](./src/main/java/com/websocket/web_socket/socket/SubscriptionHub.java)


📌 step 6 : 클라이언트에게 보낼 몇가지 유형의 메세지 템플릿을 관리할 클래스 작성
-
* 답장(성공시), 에러, 실패 세가지 유형을 관리합니다.
* 클래스 [WebSocketMessages](./src/main/java/com/websocket/web_socket/socket/WebSocketMessages.java)

📌 step 7 : 클라이언트의 수행 정보를 담을 (템플릿화) 클래스 작성
-
* channel(채널정보), action(수행할 메소드), payload(메소드 수행을 위해 필요한 정보) 를 담습니다.
* 클래스 [IncomingMessage](./src/main/java/com/websocket/web_socket/socket/handlerManager/IncomingMessage.java)

📌 step 8 : 핸들러를 찾는 역할을 수행할 클래스 & 핸들러 사용을 쉽게할 클래스 작성
-
* 핸들러에 붙은 어노테이션을 기반으로 spring container에 들어간 bean을 찾아올 클래스 : [ChannelHandlerResolver](./src/main/java/com/websocket/web_socket/socket/handlerManager/ChannelHandlerResolver.java)
* 각각의 핸들러를 감쌉니다. 핸들러가 action을 지원하는지 검사하거나, 요청 수행을 위한 payload를 action에 할당해주는 등등의 역할을 수행합니다.  : [ChannelHandlerInvoker](./src/main/java/com/websocket/web_socket/socket/handlerManager/ChannelHandlerInvoker.java)

📌 step 9 : 구독 & 해체를 관리할 핸들러 작성
-
* [SubscriptionHub](./src/main/java/com/websocket/web_socket/socket/SubscriptionHub.java) 클래스를 통해서 구독과 해제를 합니다. 
* 이 클래스는 채널별로 다를 수 있는 동작을 정의하기 위해 작성 되었습니다.
* 또한 이 클래스를 작성함으로써, 서버가 원치않는 채널이 발행 되는 것을 막습니다 => 클라이언트가 발행을 요청한 핸들러가 없기 때문입니다. 
* [ClockChannelHandler](./src/main/java/com/websocket/web_socket/socket/handlers/ClockChannelHandler.java) 클래스를 통해서 구독과 해제를 합니다. 
* 핸들러에 사용할 어노테이션은 별도로 관리합니다.

📌 step 10 : 사용자에게 메세지를 보낼 updater 클래스 작성
-
* 적절한 message template 을 선택해서, 특정 채널에 메세지를 보낼 수 있도록 updater 클래스를 사용합니다.
* 클래스 : [ClockUpdater](./src/main/java/com/websocket/web_socket/socket/updater/ClockUpdater.java)

</details>
