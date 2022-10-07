package com.shawky.zimozitennisapptask.domain.helpers

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.internal.threadName

abstract class NetworkCallHandler {

    suspend fun <T> performNetworkOp(
        networkCall: suspend () -> T,
        onData: suspend (T) -> Unit,
        onError: suspend (Exception?) -> Unit
    ) {
        coroutineScope {
            val data = async(Dispatchers.IO) {
                Log.i("CoRoutine","Running Task On ${Thread.currentThread().name}")
                networkCall()
            }
            try {
                withContext(Dispatchers.Main) {
                    Log.i("CoRoutine","Running Done Task On ${Thread.currentThread().name}")
                    onData(data.await())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.i("CoRoutine","Running Error Task On ${Thread.currentThread().name}")
                    e.printStackTrace()
                    onError(e)
                }
            }
        }
    }

}