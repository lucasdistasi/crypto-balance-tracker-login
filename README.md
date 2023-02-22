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

1. You must have **MongoDB** installed in your machine.
2. Download the project.
3. Create the database. You can use a custom database name if you want.
4. Once you downloaded the project, you need to set up some environment variables.
    1. _MONGODB_DATABASE_. The database name.
    2. _MONGODB_USERNAME_. The username of your database.
    3. _MONGODB_PASSWORD_. The password of your user.
    4. _JWT_SIGNING_KEY_. The Signing Key if you are working with the secured branch.
5. Start the program in your favourite IDE or run the following command from the CLI.

>./gradlew bootRun


If you found this project useful or you learnt something from it, you can consider gifting me a coup of coffee :)

| Crypto | Network | Address                                    | QR      |
|--------|---------|--------------------------------------------|---------|
| BTC    | BEP20   | 0x03c5551d3122e9c2d6bda94521e2f75bab74de21 | [BEP20] |
| USDT   | TRC20   | TWBfjXcKcgZVajVxTZpp8qA3fyJVoEsqer         | [TRC20] |
| USDT   | BEP20   | 0x03c5551d3122e9c2d6bda94521e2f75bab74de21 | [BEP20] |

[BEP20]: https://i.imgur.com/ADeTSXC.png "BEP20"
[TRC20]: https://i.imgur.com/PbgZwew.png "TRC20"
