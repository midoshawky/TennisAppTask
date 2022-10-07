package com.shawky.zimozitennisapptask.domain.models

sealed class ResultState<out T> {

    object Loading : ResultState<Nothing>()
    object Initial : ResultState<Nothing>()

    data class Success<T>(val data: T?, val successMsg: String? = null) : ResultState<T>()
    data class Error(val error: String) : ResultState<Nothing>()
    data class AuthError(val error: String) : ResultState<Nothing>()
    data class NetworkException(val errorMsg: String?) : ResultState<Nothing>()
    data class EmptyData(val errorMsg: String?) : ResultState<Nothing>()
    object Complete : ResultState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Initial -> "Init .. "
            is Loading -> "Loading .. "
            is Success -> "Success[$successMsg , data: $data]"
            is Error -> "Error [error: $error]"
            is NetworkException -> "Error [error: $errorMsg]"
            is EmptyData -> "Error [error: $errorMsg]"
            is AuthError -> "Error [error: $error]"
            is Complete -> "Completed .."
        }
    }

    val isSuccess : Boolean get() = this is Success

    val isLoading : Boolean get() = this is Loading

    val isFailure: Pair<Boolean, String>
        get() {
            if (this is Error)  return Pair(true, this.error)
            if (this is NetworkException)  return Pair(true, this.errorMsg ?: "No Connection")
            if (this is EmptyData) return Pair(true, this.errorMsg ?: "No Data")
            return Pair(false, "")
        }

    val getAnyMessage: String
        get() {
            if (this is EmptyData) return this.errorMsg ?: "No Data"
            if (this is Error) return this.error
            if (this is NetworkException) return this.errorMsg ?: "No Connection"
            return ""
        }
}
