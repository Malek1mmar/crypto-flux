package com.malloc.cryptoflux.config;

import com.malloc.cryptoflux.application.service.InMemoryTickerStore;
import com.malloc.cryptoflux.application.usecase.HandleTickerUpdate;
import com.malloc.cryptoflux.application.usecase.TickerQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TickerConfiguration {

  @Bean
  public InMemoryTickerStore tickerStore() {
    return new InMemoryTickerStore();
  }

  @Bean
  public HandleTickerUpdate handleTickerUpdate(InMemoryTickerStore store) {
    return store;
  }

  @Bean
  public TickerQueryService tickerQueryService(InMemoryTickerStore store) {
    return store;
  }
}