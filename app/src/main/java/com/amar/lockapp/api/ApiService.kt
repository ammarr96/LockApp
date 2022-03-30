package com.amar.lockapp.api

import com.amar.lockapp.model.LocksResponse
import retrofit2.http.GET

interface ApiService {

    @GET("sv_lsm_data.json")
    suspend fun getLocks(): LocksResponse

}