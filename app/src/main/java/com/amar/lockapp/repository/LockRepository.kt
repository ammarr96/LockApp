package com.amar.lockapp.repository

import com.amar.lockapp.api.ApiService
import com.amar.lockapp.model.Lock
import javax.inject.Inject

class LockRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getLocks(): List<Lock> {
        return apiService.getLocks().locks
    }

}