package com.shawky.zimozitennisapptask.domain.useCases

import androidx.lifecycle.MutableLiveData
import com.shawky.zimozitennisapptask.domain.helpers.NetworkCallHandler
import com.shawky.zimozitennisapptask.domain.helpers.extintions.handleCommonException
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository.TennisRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class GetTennisPlayersUseCase @Inject constructor(private val tennisRepositoryImp: TennisRepositoryImp) : NetworkCallHandler() {

    private val dataFlow : MutableSharedFlow<ResultState<List<TennisPlayer>>?> = MutableSharedFlow(5)
    suspend operator fun invoke(): SharedFlow<ResultState<List<TennisPlayer>>?> {
        dataFlow.emit(ResultState.Loading)
        performNetworkOp(
            networkCall = { tennisRepositoryImp.getTennisPlayerData() },
            onData = {
                dataFlow.emit(
                    when {
                        it.status && it.data.isNotEmpty() -> ResultState.Success(it.data,it.message)
                        it.status && it.data.isEmpty() -> ResultState.EmptyData("No data")
                        !it.status -> ResultState.Error("Something went wrong")
                        else -> ResultState.Error("Something went wrong")
                    }
                )

            },
            onError = {
                dataFlow.emit(it.handleCommonException())
            }
        )
         return dataFlow
    }
}