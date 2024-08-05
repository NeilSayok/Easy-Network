package com.neilsayok.networkcall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neilsayok.easy_network.NetworkWrapper.Resource
import com.neilsayok.easy_network.NetworkWrapper.toLoading
import com.neilsayok.easy_network.exceptionHandler.ExceptionHandlerFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class TestRes(val name : String)

class ViewModel : ViewModel() {

    val testFlow = MutableStateFlow<Resource<TestRes>?>(Resource.Idle())

    fun testResource(){
        val exceptionHandler = ExceptionHandlerFlow(
            viewModelScope,
            testFlow,
        )
        viewModelScope.launch {
            testFlow.apply {
                emit(value.toLoading())
            }
        }

        viewModelScope.launch(exceptionHandler) {
            testFlow.apply {
                delay(3000)
                val apiResp = TestRes("Success")
                emit(Resource.Success(apiResp))
            }
        }


    }

}