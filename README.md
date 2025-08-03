# 🔁 Reactive Crypto Price Streaming Backend

A Java backend service built with **Spring Boot WebFlux** and **Clean Architecture** to stream real-time cryptocurrency prices from **Binance WebSocket API**, and expose the latest prices via a REST API.

---
## 🧠 Business Logic Explanation

This backend service listens to live cryptocurrency price updates from Binance and makes the latest price available to API consumers in real time.

### 🔄 Real-time Price Streaming (Reactive Flow)
1. The system connects to Binance via WebSocket using the `btcusdt@ticker` stream.
2. For each incoming JSON message (representing the latest ticker info):
    - The system extracts the symbol (e.g. `BTCUSDT`) and current price.
    - It constructs a domain object (`CryptoTicker`) containing this data.
    - The object is passed to a **use case**: `HandleTickerUpdate`.
3. `HandleTickerUpdate` stores the data in a **reactive in-memory store** (`InMemoryTickerStore`).

### 🔍 Price Query API
When a client makes a GET request to `/api/price/BTCUSDT`:
1. The REST controller delegates the request to the **`TickerQueryService`** use case.
2. The service retrieves the latest `CryptoTicker` object from memory.
3. The latest price is returned as a JSON response.

### 🧱 Clean Architecture Principles Used
- **Domain Model** (`CryptoTicker`) is free of any framework dependencies.
- **Use Cases** (`HandleTickerUpdate`, `TickerQueryService`) contain core business logic.
- **Interfaces (adapters)** expose and consume use cases:
    - REST controller (incoming adapter)
    - WebSocket client (outgoing adapter)
- **Infrastructure** holds implementation details (e.g. WebSocket connection).

This separation makes the system:
- Modular
- Testable
- Framework-agnostic

## 🚀 Features

- Connects reactively to Binance WebSocket API (e.g., `btcusdt@ticker`)
- Processes and stores live price updates in memory
- Exposes the latest price via a REST endpoint (`/api/price/BTCUSDT`)
- Organized with Clean Architecture principles
- Fully reactive with Project Reactor (`Flux`, `Mono`)

---

## 🧱 Technologies

- Java 17+
- Spring Boot 3
- Spring WebFlux
- Project Reactor
- Maven

---

## 🧠 Clean Architecture Structure

├── domain # Business models
│ └── model
│ └── CryptoTicker.java
│
├── application # Use cases (business logic)
│ ├── usecase
│ │ ├── HandleTickerUpdate.java
│ │ └── TickerQueryService.java
│ └── service
│ ├── InMemoryTickerStore.java
│ ├── HandleTickerUpdateImpl.java
│ └── TickerQueryServiceImpl.java
│
├── infrastructure # External systems (Binance)
│ └── websocket
│ └── BinanceWebSocketClient.java
│
├── interfaces # API endpoints (REST/WebSocket)
│ └── api
│ └── PriceStreamController.java
│
└── CryptoStreamApplication.java # Spring Boot app entry point


## 📦 REST API

| Method | Endpoint              | Description                |
|--------|-----------------------|----------------------------|
| GET    | `/api/price/BTCUSDT`  | Get the latest BTC price   |

---

## 📡 Binance WebSocket Endpoint Used
wss://stream.binance.com:9443/ws/btcusdt@ticker


## 📌 Future Ideas
Support multiple symbols (ETH, SOL, etc.)

Store historical data in a reactive DB (e.g., MongoDB, PostgreSQL via R2DBC)

Add WebSocket/SSE output to push prices to clients