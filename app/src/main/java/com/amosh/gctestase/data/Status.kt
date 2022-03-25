package com.amosh.gctestase.data

import java.io.Serializable

data class Status(
    val code: Int? = null,
    val message: String? = null
): Serializable
