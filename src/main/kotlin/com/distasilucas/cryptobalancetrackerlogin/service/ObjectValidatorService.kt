package com.distasilucas.cryptobalancetrackerlogin.service

fun interface ObjectValidatorService<T> {

    fun validate(t: T)
}