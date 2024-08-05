package com.neilsayok.easy_network.NetworkWrapper

import com.neilsayok.easy_network.NetworkWrapper.Resource.Loading
import com.neilsayok.easy_network.constants.ErrorData
import com.neilsayok.easy_network.states.NetworkStatus

sealed class Resource<T>(
    var status: NetworkStatus = NetworkStatus.IDLE,
    val data: T? = null,
    val message: String? = null,
    val errorData: ErrorData? = null
){
    class Success<T>(data: T,) : Resource<T>(NetworkStatus.SUCCESS, data)
    class Error<T>(message: String,  errorData: ErrorData? = null,data: T? = null,) : Resource<T>(
        status = NetworkStatus.ERROR,
        data = data,
        message = message, errorData = errorData)
    class Loading<T>(data: T? = null) : Resource<T>(status = NetworkStatus.LOADING, data = data)
    class NetworkDisconnected<T>(data: T? = null) : Resource<T>(
        status = NetworkStatus.NETWORK_DISCONNECTED,
        data = data
    )
    class Idle<T>(data: T? = null) : Resource<T>(status = NetworkStatus.IDLE, data = data)

}

fun <T> Resource<T>?.toLoading() : Loading<T> {
    return Loading()
}

fun <T> Resource<T>?.toSuccess(data: T) : Resource.Success<T> {
    return Resource.Success(data)
}

fun <T> Resource<T>?.toError(message: String, errorData: ErrorData?) : Resource.Error<T> {
    return Resource.Error(message = message, errorData = errorData)
}

fun <T> Resource<T>?.toNetworkDisconnected() : Resource.NetworkDisconnected<T> {
    return Resource.NetworkDisconnected()
}

fun <T> Resource<T>?.toIdle() : Resource.Idle<T> {
    return Resource.Idle()
}
