package com.malloc.cryptoflux.adapters.api;

import com.malloc.cryptoflux.application.usecase.TickerQueryService;
import com.malloc.cryptoflux.domain.model.CryptoTicker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
public class TickerController {
  private final TickerQueryService tickerQueryService;

  public TickerController(TickerQueryService tickerQueryService) {
    this.tickerQueryService = tickerQueryService;
  }

  /**
   * Get the latest price for a given symbol.
   * @param symbol e.g., BTCUSDT
   * @return Mono<CryptoTicker> with latest price info or empty if not found
   */
  @GetMapping("/{symbol}")
  public Mono<CryptoTicker> getLatestTicker(@PathVariable String symbol) {
    return tickerQueryService.getLatest(symbol);
  }
}