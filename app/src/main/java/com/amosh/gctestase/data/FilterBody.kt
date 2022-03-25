package com.amosh.gctestase.data

import java.io.Serializable

data class FilterBody(
    var price: Double? = null,
    var color: CarColor? = null,
) : Serializable {
    val hasParams
        get() = price != null || color != null
}
