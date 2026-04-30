# CoinRates

A live cryptocurrency price tracker for Android, built with Jetpack Compose, Clean Architecture, and modern Jetpack libraries.

CoinRates displays real-time market data for hundreds of cryptocurrencies sourced from the free CoinGecko public API. Prices are cached locally in a Room database so the app stays functional offline, and a WorkManager background task keeps the cache fresh even when the app is not in the foreground.

---

## Features

- Live coin market data — current price, last updated timestamp
- 14-day price history per coin
- Full-text search across all CoinGecko-supported coins with debounced input
- Offline-first — Room cache persists between sessions
- Automatic background refresh every 15 minutes via WorkManager
- In-app price polling every 60 seconds while the app is active
- Material 3 dynamic color theming with dark/light mode support

---

## Screenshots

> Add screenshots here

---

## Architecture

The app follows **Clean Architecture** with a single `feature_coin` module structured into three strict layers. Each layer only depends on the one directly below it; the domain layer has no Android or framework dependencies.

```
+--------------------------------------------------------------+
|                      Presentation Layer                      |
|  CoinListScreen   CoinPriceHistoryScreen   SupportedCoins    |
|        |                   |                     |           |
|  CoinListViewModel  CoinPriceHistoryViewModel  SupportedVM   |
+--------|-------------------|---------------------|----------+
         |                   |                     |
         v                   v                     v
+--------------------------------------------------------------+
|                        Domain Layer                          |
|  GetAndUpdateCoins   GetCoinHistory   GetSupportedCoins      |
|              CoinsRepository (interface)                     |
|      Coin      CoinPriceHistory      SupportedCoin           |
+---------------------------|----------------------------------+
                             |
+---------------------------|----------------------------------+
|                        Data Layer                            |
|             CoinsRepositoryImpl                              |
|          /                              \                    |
|  CoinGeckoApi (Retrofit)         CoinDao (Room)              |
|  CoinDto                         CoinEntity                  |
|  SupportedCoinItemDto            CoinDatabase                |
|  CoinPriceHistoryDto                                         |
|                                                              |
|  CryptoUpdateWorker (WorkManager)                            |
+--------------------------------------------------------------+
```

**MVVM** is used in the presentation layer. Each screen has a `@HiltViewModel` that exposes `StateFlow` state, which composables collect via `collectAsState()`.

---

## Tech Stack

| Category        | Library                        | Version        |
|-----------------|--------------------------------|----------------|
| Language        | Kotlin                         | 2.1.20         |
| UI              | Jetpack Compose BOM             | 2025.03.00     |
| UI Components   | Material 3                     | (via BOM)      |
| Navigation      | Navigation Compose             | 2.8.9          |
| Dependency Injection | Hilt                      | 2.56.2         |
| Local Database  | Room                           | 2.8.4          |
| Networking      | Retrofit + Gson converter      | 2.11.0         |
| Background Work | WorkManager                    | 2.11.2         |
| Async           | Kotlin Coroutines + Flow       | 1.9.0          |
| Build System    | Android Gradle Plugin          | 8.13.2         |
| Annotation Proc | KSP                            | 2.1.20-1.0.32  |
| Min SDK         | Android 7.0 (Nougat)           | API 24         |
| Target SDK      | Android 15                     | API 35         |

---

## Project Structure

```
app/src/main/java/com/makhabatusen/coinrates/
├── CoinApplication.kt                    ← Hilt + WorkManager init
└── feature_coin/
    ├── common/
    │   └── Constants.kt                  ← API params, date formats, intervals
    ├── data/
    │   ├── local_source/
    │   │   ├── CoinDao.kt                ← Room DAO (Flow-based queries)
    │   │   ├── CoinDatabase.kt           ← Room database definition
    │   │   └── entity/
    │   │       └── CoinEntity.kt         ← Room entity + toCoin() mapper
    │   ├── remote_source/
    │   │   ├── CoinGeckoApi.kt           ← Retrofit API interface
    │   │   └── dto/
    │   │       ├── CoinDto.kt            ← Market data DTO + toCoinEntity() mapper
    │   │       ├── CoinPriceHistoryDto.kt← History DTO + toCoinPriceHistoryList() mapper
    │   │       └── SupportedCoinItemDto.kt← Supported coins DTO + toSupportedCoin() mapper
    │   └── repository/
    │       └── CoinsRepositoryImpl.kt    ← Repository implementation
    ├── di/
    │   └── CoinModule.kt                 ← Hilt module (Retrofit, Room, Repository)
    ├── domain/
    │   ├── model/
    │   │   ├── Coin.kt                   ← Domain model for market coin
    │   │   ├── CoinPriceHistory.kt       ← Domain model for price history entry
    │   │   └── SupportedCoin.kt          ← Domain model for supported coin + search logic
    │   ├── repository/
    │   │   └── CoinsRepository.kt        ← Repository interface (domain types only)
    │   └── use_case/
    │       ├── get_and_update_coins/
    │       │   └── GetAndUpdateCoins.kt
    │       ├── get_coin_history/
    │       │   └── GetCoinHistory.kt
    │       └── get_supported_coins/
    │           └── GetSupportedCoins.kt
    ├── presentation/
    │   ├── MainActivity.kt
    │   ├── coin_list/
    │   │   ├── CoinListItem.kt           ← Composable list item
    │   │   └── CoinListViewModel.kt
    │   ├── coin_price_history/
    │   │   ├── CoinPriceHistoryListItem.kt
    │   │   └── CoinPriceHistoryViewModel.kt
    │   ├── supported_coins/
    │   │   ├── SupportedCoinsListItem.kt
    │   │   └── SupportedCoinsViewModel.kt
    │   └── ui/
    │       ├── screens/
    │       │   ├── CoinListScreen.kt
    │       │   ├── CoinPriceHistoryScreen.kt
    │       │   └── Screen.kt             ← Sealed navigation routes
    │       └── theme/
    │           ├── Color.kt
    │           ├── Theme.kt
    │           └── Type.kt
    └── utils/
        └── CryptoUpdateWorker.kt         ← @HiltWorker for background sync
```

---

## Getting Started

### Prerequisites

- Android Studio Meerkat (2024.3) or newer
- JDK 17
- Android SDK with API 35 platform installed

### Build

```bash
git clone https://github.com/<your-username>/CoinRates.git
cd CoinRates
./gradlew assembleDebug
```

### Run

```bash
./gradlew installDebug
```

No API key is required. CoinGecko's free public API is used without authentication.

### API Endpoints Used

| Endpoint                        | Purpose                              |
|---------------------------------|--------------------------------------|
| `GET /coins/markets`            | Top coins by market cap (EUR)        |
| `GET /coins/{id}/market_chart`  | 14-day daily price history           |
| `GET /coins/list`               | Full list of supported coin IDs      |

Base URL: `https://api.coingecko.com/api/v3/`

---

## Best Practices

- **Clean Architecture** — domain layer has zero imports from `data` or Android framework; repository interface uses only domain models
- **Unidirectional Data Flow** — ViewModels expose `StateFlow`, composables observe and never mutate state directly
- **Offline-first** — Room is the single source of truth; network writes to cache, UI reads from cache
- **Hilt DI** — all dependencies injected through interfaces; `@HiltWorker` for WorkManager workers, `@ApplicationContext` for context in ViewModels
- **WorkManager** — background sync uses `ExistingPeriodicWorkPolicy.KEEP` to prevent duplicate workers after configuration changes
- **Coroutines & Flow** — all async work runs in `viewModelScope`; DAO returns `Flow<List<CoinEntity>>` for reactive cache updates; no `Handler` or manual thread management
- **Compose BOM** — all Compose library versions aligned through the BOM to prevent version conflicts
- **Version Catalog** — all dependency versions declared in `gradle/libs.versions.toml` for single-point maintenance
- **KSP** — annotation processing for Hilt and Room uses KSP instead of KAPT for faster incremental builds
- **ProGuard** — enabled in release builds with resource shrinking; debug builds keep full symbols
- **Java 17** — source and target compatibility set to JVM 17 for modern language features
