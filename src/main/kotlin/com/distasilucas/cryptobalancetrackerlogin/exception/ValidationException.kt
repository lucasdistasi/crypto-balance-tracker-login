package com.distasilucas.cryptobalancetrackerlogin.exception

class ValidationException(val errors: Set<String>, message: String) : RuntimeException(message)