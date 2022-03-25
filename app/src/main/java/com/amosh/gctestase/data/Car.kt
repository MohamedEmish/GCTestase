package com.amosh.gctestase.data

import java.io.Serializable

data class Car(
    val model: Int? = null,
    val plate_number: String? = null,
    val brand: String? = null,
    val unit_price: Double? = null,
    val currency: String? = null,
    val color: CarColor? = null,
) : Serializable