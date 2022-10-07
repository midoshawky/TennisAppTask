package com.shawky.zimozitennisapptask.domain.helpers.extintions

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.presentation.helpers.gone
import com.shawky.zimozitennisapptask.presentation.helpers.visible

fun <T> Exception?.handleCommonException(): ResultState<T> {
    if (
        this?.message?.contains("host", ignoreCase = true) == true
        ||
        this?.message?.contains("network", ignoreCase = true) == true
    ) {
        return ResultState.NetworkException(message) as ResultState<T>
    }
    Log.e("ApiError", "Err : $this")
    return ResultState.Error("Something wrong please try again later !!") as ResultState<T>
}

fun <T>AppCompatActivity.stateHandler(
    result: ResultState<T>,
    withPlaceHolder : View? = null,
    onLoading: () -> Unit = {},
    onSuccess: (data:T?) -> Unit = {},
    onFailure: (message:String?,isEmpty:Boolean?) -> Unit = { _ , _ ->},

) {
    when(result){
        is ResultState.EmptyData -> {
            onFailure(result.errorMsg,true)
            withPlaceHolder?.visible()
        }
        is ResultState.Error -> {
            onFailure(result.error,false)
            withPlaceHolder?.visible()
        }
        ResultState.Loading -> {
            withPlaceHolder?.gone()
            onLoading()
        }
        is ResultState.NetworkException -> {
            onFailure(result.errorMsg,false)
        }
        is ResultState.Success -> {
            onSuccess(result.data)
            withPlaceHolder?.gone()
        }
        is ResultState.AuthError -> {
            onFailure(result.error,false)
            withPlaceHolder?.visible()
        }
        else -> {}
    }
}