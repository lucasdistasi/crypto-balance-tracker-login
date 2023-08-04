# Crypto Balance Tracker Login

| |                                                                                                                                                                                              |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Pipeline | [![Pipeline Status](https://github.com/lucasdistasi/crypto-balance-tracker-login/actions/workflows/main.yml/badge.svg)](https://github.com/lucasdistasi/crypto-balance-tracker-login/actions) |
| License | [![License](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)                                                                                      |
| Code Coverage | [![Code Coverage](https://github.com/lucasdistasi/crypto-balance-tracker-login/blob/gh-pages/badges/jacoco.svg)](https://lucasdistasi.github.io/crypto-balance-tracker-login/)               |
| Project views | [![Project views](https://hits.dwyl.com/lucasdistasi/crypto-balance-tracker-login.svg)](#)                                                                                                   |

Crypto Balance Tracker Login it's a Kotlin-Spring application that generates a JWT if the given user and password exists 
in a Mongo database. This JWT is needed in order to consume the endpoints from [crypto-balance-tracker](https://github.com/lucasdistasi/crypto-balance-tracker).
<br>
Bear in mind that you need an User in the Users MongoDB document in order to be able to login and get a token.
All the information you need can be found in [crypto-balance-tracker](https://github.com/lucasdistasi/crypto-balance-tracker).

## Technologies used
- Kotlin
- Spring 6 & Spring Boot 3
    - Spring Security
- MongoDB
- JUnit 5 - Mockk

### I want to try this application on my local machine. What should I do?

- Head towards [crypto-balance-tracker](https://github.com/lucasdistasi/crypto-balance-tracker) :)

---

### Contributing :coffee:

Feel free to star, fork, or study from the code! If you'd like to contribute, you can gift me a coffee.

| Crypto | Network | Address                                    | QR            |
|--------|---------|--------------------------------------------|---------------|
| BTC    | Bitcoin | 15gJYCyCwoHVE3MpjwDYLM51zLRoKo2Q9h         | [BTC-bitcoin] |
| BTC    | TRC20   | TFVmahp7YQiEwd9bh4dEgF7fZyGjrQ7TRW         | [BTC-trc20]   |
| ETH    | BEP20   | 0x304714FDA2060c570B1afb1BC231C0973abBEC23 | [ETH-bep20]   |
| ETH    | ERC20   | 0x304714FDA2060c570B1afb1BC231C0973abBEC23 | [ETH-erc20]   |
| USDT   | TRC20   | TFVmahp7YQiEwd9bh4dEgF7fZyGjrQ7TRW         | [USDT-trc20]  |
| USDT   | BEP20   | 0x304714FDA2060c570B1afb1BC231C0973abBEC23 | [USDT-bep20]  |
| USDT   | ERC20   | 0x304714FDA2060c570B1afb1BC231C0973abBEC23 | [USDT-erc20]  |

[BTC-bitcoin]: https://imgur.com/Hs0DYDk
[BTC-trc20]: https://imgur.com/kdROHrE
[ETH-bep20]: https://imgur.com/DIOiJrL
[ETH-erc20]: https://imgur.com/REXkDmu
[USDT-trc20]: https://imgur.com/ubUWdpI
[USDT-bep20]: https://imgur.com/rrrYd9j
[USDT-erc20]: https://imgur.com/G9DPKvU