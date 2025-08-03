package com.malloc.cryptoflux.domain.model;

import java.math.BigDecimal;
import java.time.Instant;

public record CryptoTicker(
    String symbol,
    BigDecimal price,
    Instant timestamp
) {}