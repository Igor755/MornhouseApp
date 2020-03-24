package com.devcraft.data.api.data_source

import android.accounts.NetworkErrorException
import com.devcraft.data.api.service.GenericApi
import com.devcraft.data.entity.network.GenericNet
import com.devcraft.domain.throwException
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenericNetSource(private val genericApi: GenericApi) : KoinComponent {
    fun getCategories(onComplete: (List<GenericNet>?) -> Unit, onError: (Exception) -> Unit) {
        genericApi.getCategories().enqueue(object : Callback<List<GenericNet>> {
            override fun onFailure(call: Call<List<GenericNet>>, t: Throwable) {
                onError(NetworkErrorException(t))
            }

            override fun onResponse(
                call: Call<List<GenericNet>>,
                response: Response<List<GenericNet>>
            ) {
                if (response.isSuccessful) {
                    onComplete(response.body())
                } else {
                    onError(response.code().throwException(response.message()))
                }
            }
        })
    }
}