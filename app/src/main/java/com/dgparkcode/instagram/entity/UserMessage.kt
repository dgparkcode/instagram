package com.dgparkcode.instagram.entity

import java.util.*

data class UserMessage(
    val id: Long = UUID.randomUUID().mostSignificantBits,
    val message: String = ""
)
