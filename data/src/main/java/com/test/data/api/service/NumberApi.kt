package com.test.data.api.service

import com.test.data.constant.ApiEndpoints
import retrofit2.Call
import retrofit2.http.*

interface NumberApi {
    @GET(ApiEndpoints.FACT_NUMBER)
    fun getFactNumber(@Path("number") number: String): Call<String>
}