FROM eclipse-temurin:21.0.2_13-jdk
RUN mkdir "/home/crypto-balance-tracker-login"
WORKDIR /home/crypto-balance-tracker-login
COPY /build/libs/crypto-balance-tracker-login.jar .
EXPOSE 8081
CMD ["java", "-jar", "crypto-balance-tracker-login.jar"]
