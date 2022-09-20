package com.test.data.api.service

import com.test.data.constant.ApiEndpoints
import com.test.data.entity.network.GenericNet
import retrofit2.Call
import retrofit2.http.GET

interface GenericApi {
    @GET(ApiEndpoints.ENDPOINT_NAME)
    fun getGeneric(): Call<List<GenericNet>>
}