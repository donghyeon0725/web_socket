π web_socket-spring
-
* μ€νλ§ λΆνΈλ‘ μμ±λ μΉ μμΌ νλ‘μ νΈ μμ  μλλ€.
* [νλ‘ νΈμλ μλ²](https://github.com/donghyeon0725/web_socket_vue) λ₯Ό νμλ‘ ν©λλ€.

<details>
    <summary>νΌμ³λ³΄κΈ°</summary>

μμ²­μ μ²λ¦¬
-

π step 0 : νμν λν¬λμ μΆκ°
-
```xml
<!-- μ€νλ§ μνλ¦¬ν° -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>

<!-- μΉμμΌ -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>

<!-- ν ν°κ³Ό json λ³νμ μν ν΄λμ€ -->
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

π step 1 : μΈμ¦μ μν΄ ν ν° & μλ² url μ λ³΄ λ°κΈλ°κΈ°
-
* μΉμμΌ μλ²μ μ°κ²°μ νκΈ° μν΄μ μ¬μ©ν  ν ν°κ³Ό μ°κ²° μ£Όμ(url)λ₯Ό λ°κΈ λ°μ΅λλ€.
* μ΄λ ν ν° λ§€λμ λ₯Ό μ¬μ©ν©λλ€. [TokenManager](./src/main/java/com/websocket/web_socket/security/TokenManager.java)
* μνλ¦¬ν° κΈ°λ°μ΄κΈ° λλ¬Έμ λ€μκ³Ό κ°μ μ€μ μ΄ νμν©λλ€. [SecurityConfig](./src/main/java/com/websocket/web_socket/security/SecurityConfig.java) 
* λ€λ₯Έ originμμ μμΌν΅μ μ μμ²­νλ€λ©΄ λ€μκ³Ό κ°μ μ€μ λ νμν©λλ€. [CORSConfig](./src/main/java/com/websocket/web_socket/security/CORSConfig.java) 
* ν΄λμ€ : [MeApiController](./src/main/java/com/websocket/web_socket/MeApiController.java)

π step 2 : ν΄λΌμ΄μΈνΈκ° μμΌ ν΅μ μ μμ²­νμ λ, μ²λ¦¬ν  ν΄λμ€ μ νκΈ°
-
* μμΌ μμ²­μ΄ λ€μ΄μμ λ ν΄λΉ μ²λ¦¬λ₯Ό μ΄λμμ ν μ§ μ νλ μ€μ νμΌμ μμ±ν©λλ€.
* μ΄ λ μ΄ μμ±νμΌμ CROS κ΄λ ¨ μ€μ  λ΄μ© λν λ€μ΄κ°λλ€.
* ν΄λμ€ : [WebSocketConfiguration](./src/main/java/com/websocket/web_socket/WebSocketConfiguration.java)


π step 3 : ν΄λΌμ΄μΈνΈ μμ²­μ΄ λ€μ΄μμ λ, μΈμ¦, μ»€λ₯μ κ΄λ¦¬λ₯Ό ν  λμ€ν¨μ² μμ±
-
* μμΌ μμ²­μ΄ λ€μ΄μμ λ ν΄λΉ μμ²­μ νΈλ€λ§ν  νΈλ€λ¬λ₯Ό μμ±νκΈ° μν΄μ TextWebSocketHandlerμ μμ λ°μ ν΄λμ€λ₯Ό νλ μμ±ν©λλ€. κ·Έλ¦¬κ³  μ΄μ λΆν° μ΄κ²μ λμ€ν¨μ²λΌκ³  λΆλ₯΄κ² μ΅λλ€. (μ»¨νΈλ‘€λ¬μ λΉμ·ν κ°λ)
* ν΄λμ€ [WebSocketRequestDispatcher](./src/main/java/com/websocket/web_socket/socket/WebSocketRequestDispatcher.java)
* ν΄λΉ ν΄λμ€μ μ­ν μ μ»€λ₯μ μ°κ²° μμ²­μ΄ λ€μ΄μμ λ μΈμ¦μ μννκ³  μ°κ²°μ ν΄μ£Όλ©°, μ°κ²°μ΄ λκ²Όμ λ κ΅¬λμ ν΄μ ν©λλ€.
* λν λ©μΈμ§κ° λ€μ΄μμ λ ν΄λΉ λ©μΈμ§λ₯Ό μ²λ¦¬ν  μ μ ν νΈλ€λ¬λ₯Ό μ°Ύμ μ€λλ€.


π step 4 : μ¬μ©μμ μΉμμΌ μΈμ(μ°κ²°μ λ³΄)μ μ½κ² κ΄λ¦¬νκΈ° μν wrapper ν΄λμ€ μμ±
-

* μ€νλ§μ WebSocketSession ν΄λμ€λ μΉμμΌ ν΅μ μ κ°λ₯νκ² ν΄μ£Όλ api μλλ€.
* ν΄λΉ ν΄λμ€λ₯Ό wrap ν΄μ μ¬μ©νκΈ° μ½λλ‘ ν©λλ€.
* μΈμμμ ν ν°μ μΆμΆνκ±°λ, κ°κ°μ μΈμμ λ©μΈμ§λ₯Ό λ³΄λ΄λ μ­ν μ μνν©λλ€.
* ν΄λμ€ [RealTimeSession](./src/main/java/com/websocket/web_socket/socket/RealTimeSession.java)


π step 5 : κ΅¬λμ ν ν΄λΌμ΄μΈνΈλ₯Ό λ³λλ‘ κ΄λ¦¬ν  ν΄λμ€ μμ±
-
* μ°κ²°μ μ±κ³΅ν μΈμ μ€, κ΅¬λμ ν΄μ μ€μκ° μλμ μνν  ν΄λΌμ΄μΈνΈμ μΈμμ λ³λλ‘ κ΄λ¦¬νκΈ° μν ν΄λμ€ μλλ€.
* μ¬μ©μ λ³ κ΅¬λν μ±λμ μ λ³΄μ μ±λ λ³λ‘ κ΅¬λν μ¬μ©μ μ λ³΄λ₯Ό λλ€ κ°μ§κ³  μκΈ° λλ¬Έμ μ²΄λ λ³λ‘ λ©μΈμ§μ ν  μ μκ² ν΄μ€λλ€.
* κ΅¬λμ νλλ°, μ±λμ΄ μλ κ²½μ° λ°ν λν μνν©λλ€.
* ν΄λμ€ [SubscriptionHub](./src/main/java/com/websocket/web_socket/socket/SubscriptionHub.java)


π step 6 : ν΄λΌμ΄μΈνΈμκ² λ³΄λΌ λͺκ°μ§ μ νμ λ©μΈμ§ ννλ¦Ώμ κ΄λ¦¬ν  ν΄λμ€ μμ±
-
* λ΅μ₯(μ±κ³΅μ), μλ¬, μ€ν¨ μΈκ°μ§ μ νμ κ΄λ¦¬ν©λλ€.
* ν΄λμ€ [WebSocketMessages](./src/main/java/com/websocket/web_socket/socket/WebSocketMessages.java)

π step 7 : ν΄λΌμ΄μΈνΈμ μν μ λ³΄λ₯Ό λ΄μ (ννλ¦Ών) ν΄λμ€ μμ±
-
* channel(μ±λμ λ³΄), action(μνν  λ©μλ), payload(λ©μλ μνμ μν΄ νμν μ λ³΄) λ₯Ό λ΄μ΅λλ€.
* ν΄λμ€ [IncomingMessage](./src/main/java/com/websocket/web_socket/socket/handlerManager/IncomingMessage.java)

π step 8 : νΈλ€λ¬λ₯Ό μ°Ύλ μ­ν μ μνν  ν΄λμ€ & νΈλ€λ¬ μ¬μ©μ μ½κ²ν  ν΄λμ€ μμ±
-
* νΈλ€λ¬μ λΆμ μ΄λΈνμ΄μμ κΈ°λ°μΌλ‘ spring containerμ λ€μ΄κ° beanμ μ°Ύμμ¬ ν΄λμ€ : [ChannelHandlerResolver](./src/main/java/com/websocket/web_socket/socket/handlerManager/ChannelHandlerResolver.java)
* κ°κ°μ νΈλ€λ¬λ₯Ό κ°μλλ€. νΈλ€λ¬κ° actionμ μ§μνλμ§ κ²μ¬νκ±°λ, μμ²­ μνμ μν payloadλ₯Ό actionμ ν λΉν΄μ£Όλ λ±λ±μ μ­ν μ μνν©λλ€.  : [ChannelHandlerInvoker](./src/main/java/com/websocket/web_socket/socket/handlerManager/ChannelHandlerInvoker.java)

π step 9 : κ΅¬λ & ν΄μ²΄λ₯Ό κ΄λ¦¬ν  νΈλ€λ¬ μμ±
-
* [SubscriptionHub](./src/main/java/com/websocket/web_socket/socket/SubscriptionHub.java) ν΄λμ€λ₯Ό ν΅ν΄μ κ΅¬λκ³Ό ν΄μ λ₯Ό ν©λλ€. 
* μ΄ ν΄λμ€λ μ±λλ³λ‘ λ€λ₯Ό μ μλ λμμ μ μνκΈ° μν΄ μμ± λμμ΅λλ€.
* λν μ΄ ν΄λμ€λ₯Ό μμ±ν¨μΌλ‘μ¨, μλ²κ° μμΉμλ μ±λμ΄ λ°ν λλ κ²μ λ§μ΅λλ€ => ν΄λΌμ΄μΈνΈκ° λ°νμ μμ²­ν νΈλ€λ¬κ° μκΈ° λλ¬Έμλλ€. 
* [ClockChannelHandler](./src/main/java/com/websocket/web_socket/socket/handlers/ClockChannelHandler.java) ν΄λμ€λ₯Ό ν΅ν΄μ κ΅¬λκ³Ό ν΄μ λ₯Ό ν©λλ€. 
* νΈλ€λ¬μ μ¬μ©ν  μ΄λΈνμ΄μμ λ³λλ‘ κ΄λ¦¬ν©λλ€.

π step 10 : μ¬μ©μμκ² λ©μΈμ§λ₯Ό λ³΄λΌ updater ν΄λμ€ μμ±
-
* μ μ ν message template μ μ νν΄μ, νΉμ  μ±λμ λ©μΈμ§λ₯Ό λ³΄λΌ μ μλλ‘ updater ν΄λμ€λ₯Ό μ¬μ©ν©λλ€.
* ν΄λμ€ : [ClockUpdater](./src/main/java/com/websocket/web_socket/socket/updater/ClockUpdater.java)

</details>
