package com.malloc.cryptoflux.infrastructure.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.malloc.cryptoflux.application.usecase.HandleTickerUpdate;
import com.malloc.cryptoflux.domain.model.CryptoTicker;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;

@Component
public class BinanceWebSocketClient {

  private final HandleTickerUpdate handleTickerUpdate;
  private final ObjectMapper objectMapper = new ObjectMapper();

  private static final String SYMBOL = "btcusdt";
  private static final String WS_URL = "wss://stream.binance.com:9443/ws/" + SYMBOL + "@ticker";

  public BinanceWebSocketClient(HandleTickerUpdate handleTickerUpdate) {
    this.handleTickerUpdate = handleTickerUpdate;
  }
  @PostConstruct
  public void start() {
    ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();

    client.execute(
        URI.create(WS_URL),
        session -> session.receive()
            .map(msg -> msg.getPayloadAsText())
            .map(this::parseTicker)
            .doOnNext(handleTickerUpdate::handle)
            .then()
    ).subscribe();
  }
  private CryptoTicker parseTicker(String json) {
    try {
      JsonNode node = objectMapper.readTree(json);
      String symbol = node.get("s").asText();
      BigDecimal price = new BigDecimal(node.get("c").asText());
      long eventTime = node.get("E").asLong();
      return new CryptoTicker(symbol, price, Instant.ofEpochMilli(eventTime));
    } catch (Exception e) {
      throw new RuntimeException("Failed to parse ticker: " + json, e);
    }
  }
}