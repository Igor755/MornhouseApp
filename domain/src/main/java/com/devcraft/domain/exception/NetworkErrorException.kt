package com.devcraft.domain.exception

class NetworkErrorException(override val message: String = "network error occurred") :
    Exception(message)