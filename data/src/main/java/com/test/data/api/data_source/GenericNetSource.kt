package com.test.data.api.data_source

import android.accounts.NetworkErrorException
import com.test.data.api.service.GenericApi
import com.test.data.entity.network.GenericNet
import com.test.domain.throwException
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenericNetSource(private val genericApi: GenericApi) : KoinComponent {
    fun getGeneric(onComplete: (List<GenericNet>?) -> Unit, onError: (Exception) -> Unit) {
        genericApi.getGeneric().enqueue(object : Callback<List<GenericNet>> {
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