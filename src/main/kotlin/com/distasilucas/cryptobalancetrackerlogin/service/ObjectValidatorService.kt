package com.distasilucas.cryptobalancetrackerlogin.service

interface ObjectValidatorService<T> {

    fun validate(t: T)
}