package com.test.data.api.data_source

import com.test.data.api.service.NumberApi
import com.test.domain.exception.NetworkErrorException
import com.test.domain.throwException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NumberNetSource(private val numberApi: NumberApi) {

    fun getFactNumber(
        number: String,
        onComplete: (fact: String) -> Unit,
        onError: (Exception) -> Unit
    ) {
        numberApi.getFactNumber(number)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    onError(NetworkErrorException())
                }

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            onComplete(it)
                        }
                    } else {
                        onError(response.code().throwException(response.message()))
                    }
                }
            })
    }
}