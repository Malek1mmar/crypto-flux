package com.malloc.cryptoflux.application.usecase;

import com.malloc.cryptoflux.domain.model.CryptoTicker;
import reactor.core.publisher.Mono;

public interface TickerQueryService {
  /**
   * Provides a read-only use case for querying the latest ticker price.
   *
   * @param symbol The ticker symbol (e.g., "BTCUSDT")
   * @return A Mono that emits the latest CryptoTicker for the given symbol, or empty if not found.
   * <p>
   * Why Mono<CryptoTicker>?
   * - We're using Spring WebFlux and reactive programming (Project Reactor).
   * - Mono<T> is a reactive type that represents a single asynchronous value (or none).
   * - This allows non-blocking I/O, which is ideal for reactive REST endpoints.
   * </p>
   **/
  Mono<CryptoTicker> getLatest(String symbol);
}
