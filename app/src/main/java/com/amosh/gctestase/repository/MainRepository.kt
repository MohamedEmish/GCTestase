package com.amosh.gctestase.repository

import com.amosh.gctestase.data.AppResult

class MainRepository {
    // In real case we gonna use suspended functions to get remote data
    fun getCarsList(): AppResult {
        return AppResult.SuccessWithList()
    }
}