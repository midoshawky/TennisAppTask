package com.shawky.zimozitennisapptask.domain.helpers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

abstract class NetworkCallHandler {

    suspend fun <T> performNetworkOp(
        networkCall: suspend () -> T,
        onData: suspend (T) -> Unit,
        onError: suspend (Exception?) -> Unit
    ) {
        try {
            coroutineScope {

                val data = async(Dispatchers.IO) {
                    networkCall()
                }
                withContext(Dispatchers.Main) {
                    onData(data.await())
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                onError(e)
            }
        }
    }

}