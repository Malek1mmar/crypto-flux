package com.malloc.cryptoflux.application.service;

import com.malloc.cryptoflux.application.usecase.HandleTickerUpdate;
import com.malloc.cryptoflux.application.usecase.TickerQueryService;
import com.malloc.cryptoflux.domain.model.CryptoTicker;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import reactor.core.publisher.Mono;

public class InMemoryTickerStore implements TickerQueryService, HandleTickerUpdate {

  private final Map<String, CryptoTicker> latestTickers = new ConcurrentHashMap<>();

  @Override
  public void handle(CryptoTicker ticker) {
    latestTickers.put(ticker.symbol(), ticker);
  }


  @Override
  public Mono<CryptoTicker> getLatest(String symbol) {
    return Mono.justOrEmpty(latestTickers.get(symbol));
  }
}
