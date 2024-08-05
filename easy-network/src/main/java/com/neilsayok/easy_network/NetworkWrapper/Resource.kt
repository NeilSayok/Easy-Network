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
    class Error<T>(message: String, data: T? = null, errorData: ErrorData? = null) : Resource<T>(NetworkStatus.ERROR, data, message, errorData = errorData)
    class Loading<T>(data: T? = null) : Resource<T>(NetworkStatus.LOADING, data)
    class NetworkDisconnected<T>(data: T? = null) : Resource<T>(NetworkStatus.NETWORK_DISCONNECTED, data)
    class Idle<T>(data: T? = null) : Resource<T>(NetworkStatus.IDLE, data)




}

fun <T> Resource<T>?.toLoading() : Loading<T> {
    return Loading(this?.data)
}
