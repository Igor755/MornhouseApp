package com.test.mornhouse.viemodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.interactor.FactNumberInteractor
import com.test.domain.model.Result
import com.test.mornhouse.extension.SingleLiveData
import com.test.mornhouse.extension.map
import com.test.mornhouse.extension.mapFactModel
import com.test.mornhouse.model.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FactNumberViewModel (private val interactor: FactNumberInteractor) : ViewModel(){

    val getFactNumber = SingleLiveData<Result<String>>()

    private val _number = MutableLiveData("")
    var number: LiveData<String> = _number

    private val _factsLiveData = MutableLiveData<List<Fact>>()
    val factLiveData: MutableLiveData<List<Fact>> = _factsLiveData

    private val _insertFactLiveData = MutableLiveData<Boolean>()
    val insertFactLiveData: MutableLiveData<Boolean> = _insertFactLiveData

    private val _validText = MutableLiveData(false)
    var validText: LiveData<Boolean> = _validText

    private fun getFactNumber(random : Boolean) {
        if (random){
            viewModelScope.launch(Dispatchers.IO) {
                interactor.getFactNumber(getRandom().toString(), {
                    getFactNumber.value = Result.Success(it)
                }) {
                    getFactNumber.postValue(Result.Error(it))
                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                interactor.getFactNumber(number.value!!, {
                    getFactNumber.value = Result.Success(it)
                }) {
                    getFactNumber.postValue(Result.Error(it))
                }
            }
        }
    }

    fun getFacts() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _factsLiveData.value = interactor.getFacts()?.mapFactModel()
            }
        }
    }

    fun insertFact(fact: Fact) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                interactor.insertFact(fact.map())
                _insertFactLiveData.value = true
            }
        }
    }

    fun textChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        val text = s.toString()
        _number.value = text
    }

    private fun getRandom() : Int{
        return (0..9999).random()
    }

    fun getFactWithNumber(){
        getFactNumber(false)
    }

    fun getFactRandomNumber(){
        getFactNumber(true)
    }
}