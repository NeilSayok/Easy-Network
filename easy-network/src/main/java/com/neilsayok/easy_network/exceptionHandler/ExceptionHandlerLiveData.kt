package com.neilsayok.easy_network.exceptionHandler

import androidx.lifecycle.MutableLiveData
import com.neilsayok.easy_network.NetworkWrapper.Resource
import com.neilsayok.easy_network.constants.ErrorData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

fun <T> ExceptionHandlerLiveData(
    scope: CoroutineScope,
    vararg livedata: MutableLiveData<Resource<T>?>,
) = CoroutineExceptionHandler { _, exception ->
    scope.launch {

        if (exception is UnknownHostException) {

            livedata.forEach { ld ->
                ld.apply {
                    postValue(
                        Resource.NetworkDisconnected()
                    )
                }
            }
        }

        if (exception is HttpException) {
            livedata.forEach { ld ->
                ld.apply {
                    postValue(
                        Resource.Error(
                            errorData = ErrorData(
                                code = exception.response()?.code(),
                                errorMsg = exception.response()?.message()
                            ), message = "Something went wrong"
                        )
                    )
                }
            }
        } else {
            livedata.forEach { ld ->
                ld.apply {
                    postValue(
                        Resource.Error(
                            message = "Ooh Snap!!",
                            errorData = ErrorData(
                                code = null, errorMsg = exception.message
                            ),
                        )

                    )
                }
            }
        }


    }


}