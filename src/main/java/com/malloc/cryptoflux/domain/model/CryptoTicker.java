package com.malloc.cryptoflux.domain.model;

public class CryptoTicker {
  private final String symbol;
  private final double price;

  public CryptoTicker(String symbol, double price) {
    this.symbol = symbol;
    this.price = price;
  }

  public String getSymbol() { return symbol; }
  public double getPrice() { return price; }
}