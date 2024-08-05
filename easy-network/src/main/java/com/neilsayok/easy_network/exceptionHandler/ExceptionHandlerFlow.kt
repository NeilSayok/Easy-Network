package com.neilsayok.easy_network.exceptionHandler

import com.neilsayok.easy_network.NetworkWrapper.Resource
import com.neilsayok.easy_network.constants.ErrorData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

fun <T> ExceptionHandlerFlow(
    scope: CoroutineScope,
    vararg flows: MutableStateFlow<Resource<T>?>,
) = CoroutineExceptionHandler {_, exception ->
    scope.launch {

        if (exception is UnknownHostException) {

            flows.forEach { flow ->
                flow.apply {
                    emit(Resource.Error(
                        message = "No Internet Connection",
                        errorData = ErrorData(
                            code = null,
                            errorMsg = exception.message
                        ),
                        )
                    )
                }
            }
        }

        if (exception is HttpException) {
            flows.forEach { flow ->
                flow.apply {
                    emit(Resource.Error(
                        errorData = ErrorData(
                            code = exception.response()?.code(),
                            errorMsg = exception.response()?.message()
                        ),
                        message = "Something went wrong"
                    )
                    )
                }
            }
        }else{
            flows.forEach { flow ->
                flow.apply {
                    emit(Resource.Error(
                        message = "Ooh Snap!!",
                        errorData = ErrorData(
                            code = null,
                            errorMsg = exception.message
                        ),
                    )

                    )
                }
            }
        }


    }


}