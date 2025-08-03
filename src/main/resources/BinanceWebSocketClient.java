/*
package com.malloc.cryptoflux;

import jakarta.annotation.PostConstruct;
import java.net.URI;
import java.time.Duration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Component
public class BinanceWebSocketClient {

  private final ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();

  @PostConstruct
  public void start() {
    String wsUrl = "wss://stream.binance.com:9443/ws/btcusdt@ticker";

    Disposable disposable = client.execute(
        URI.create(wsUrl),
        session -> session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .doOnNext(System.out::println)
            .then()
    ).subscribe();

    // Dispose after 60 seconds to stop streaming
    Mono.delay(Duration.ofSeconds(60)).doOnTerminate(disposable::dispose).subscribe();
  }
}*/
