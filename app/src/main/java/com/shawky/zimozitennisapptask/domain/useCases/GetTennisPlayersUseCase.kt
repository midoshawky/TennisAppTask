package com.shawky.zimozitennisapptask.domain.useCases

import com.shawky.zimozitennisapptask.domain.helpers.NetworkCallHandler
import com.shawky.zimozitennisapptask.domain.helpers.extintions.handleCommonException
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository.TennisRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class GetTennisPlayersUseCase @Inject constructor(private val tennisRepositoryImp: TennisRepositoryImp) : NetworkCallHandler() {

    suspend operator fun invoke(): Flow<ResultState<List<TennisPlayer>>?> {
        val dataFlow : MutableSharedFlow<ResultState<List<TennisPlayer>>?> = MutableSharedFlow(3)
        dataFlow.emit(ResultState.Loading as ResultState<List<TennisPlayer>>)
        performNetworkOp(
            networkCall = { tennisRepositoryImp.getTennisPlayerData() },
            onData = {
                dataFlow.tryEmit(
                    when {
                        it.status && it.data.isNotEmpty() -> ResultState.Success(it.data,it.message)
                        it.status && it.data.isEmpty() -> ResultState.EmptyData("No data")
                        !it.status -> ResultState.Error("Something went wrong")
                        else -> ResultState.Error("Something went wrong")
                    } as ResultState<List<TennisPlayer>>
                )

            },
            onError = {
                //dataFlow.tryEmit(it.handleCommonException())
            }
        )
         return dataFlow
    }
}