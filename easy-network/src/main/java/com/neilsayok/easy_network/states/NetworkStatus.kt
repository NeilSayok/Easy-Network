package com.neilsayok.easy_network.states

sealed class NetworkStatus {
    data object SUCCESS : NetworkStatus()
    data object ERROR : NetworkStatus()
    data object NETWORK_DISCONNECTED : NetworkStatus()
    data object LOADING : NetworkStatus()
    data object IDLE : NetworkStatus()
}