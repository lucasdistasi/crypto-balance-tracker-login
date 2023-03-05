# Crypto Balance Tracker Login

Crypto Balance Tracker Login it's a Kotlin-Spring application that generates a JWT if the given user and password exists 
in a Mongo database. This JWT is needed in order to consume the endpoints from [crypto-balance-tracker](https://gitlab.com/lucas.distasi/crypto-balance-tracker).
<br>
Bear in mind that you need an User in the Users MongoDB document in order to be able to login and get a token.
<br>
If you want to create an user straightforward you can use the code below to create an user from the main class.
<br>
Just remember to rollback those changes later and replate *username* and *password* with the desired ones.
```kotlin
class CryptoBalanceTrackerLoginApplication(
    val passwordEncoder: PasswordEncoder,
    val userRepository: UserRepository
): CommandLineRunner {
    override fun run(vararg args: String?) {
        val userEntity = UserEntity("username", passwordEncoder.encode("password"), Role.ROLE_ADMIN)
        
        userRepository.save(userEntity) 
    }
}
```
With that user you'll be able to login, get a JWT and use all endpoints from the [crypto-balance-tracker](https://gitlab.com/lucas.distasi/crypto-balance-tracker).
<br>
Feel free to star, fork or study from the code :)

## Technologies used
- Kotlin
- Spring 6 & Spring Boot 3
    - Spring Security
- MongoDB
- JUnit 5 - Mockk

### I want to try this application on my local machine. What should I do?

---

- Either your are using master or no-security branch, you'll find all the needed information in
[crypto-balance-tracker](https://gitlab.com/lucas.distasi/crypto-balance-tracker) project.

If you found this project useful or you learnt something from it, you can consider gifting me a coup of coffee :)

| Crypto | Network | Address                                    | QR      |
|--------|---------|--------------------------------------------|---------|
| BTC    | BEP20   | 0x03c5551d3122e9c2d6bda94521e2f75bab74de21 | [BEP20] |
| USDT   | TRC20   | TWBfjXcKcgZVajVxTZpp8qA3fyJVoEsqer         | [TRC20] |
| USDT   | BEP20   | 0x03c5551d3122e9c2d6bda94521e2f75bab74de21 | [BEP20] |

[BEP20]: https://i.imgur.com/ADeTSXC.png "BEP20"
[TRC20]: https://i.imgur.com/PbgZwew.png "TRC20"
