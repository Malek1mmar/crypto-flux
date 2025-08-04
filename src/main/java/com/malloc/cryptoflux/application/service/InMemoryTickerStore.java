package com.malloc.cryptoflux.application.service;

import com.malloc.cryptoflux.application.usecase.HandleTickerUpdate;
import com.malloc.cryptoflux.application.usecase.TickerQueryService;
import com.malloc.cryptoflux.domain.model.CryptoTicker;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class InMemoryTickerStore implements HandleTickerUpdate, TickerQueryService {

  private final Map<String, CryptoTicker> latestTickers = new ConcurrentHashMap<>();

  @Override
  public void handle(CryptoTicker ticker) {
    latestTickers.put(ticker.symbol().toLowerCase(), ticker);
  }

  @Override
  public Mono<CryptoTicker> getLatest(String symbol) {
    CryptoTicker ticker = latestTickers.get(symbol.toLowerCase());
    return ticker != null ? Mono.just(ticker) : Mono.empty();
  }
}
