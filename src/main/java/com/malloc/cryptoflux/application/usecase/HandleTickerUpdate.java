package com.malloc.cryptoflux.application.usecase;

import com.malloc.cryptoflux.domain.model.CryptoTicker;

public interface HandleTickerUpdate {
  void handle(CryptoTicker ticker);
}