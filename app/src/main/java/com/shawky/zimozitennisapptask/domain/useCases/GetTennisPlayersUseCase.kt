package com.shawky.zimozitennisapptask.domain.useCases

import com.shawky.zimozitennisapptask.data.services.PrefrencesManager.AppPreferenceManager
import com.shawky.zimozitennisapptask.domain.helpers.NetworkCallHandler
import com.shawky.zimozitennisapptask.domain.helpers.extintions.handleCommonException
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository.TennisRepositoryImp
import com.shawky.zimozitennisapptask.shared.Constants.PLAYERS
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class GetTennisPlayersUseCase @Inject constructor(private val tennisRepositoryImp: TennisRepositoryImp,private val appPreferenceManager: AppPreferenceManager) : NetworkCallHandler() {

    private val dataFlow : MutableSharedFlow<ResultState<List<TennisPlayer>>?> = MutableSharedFlow(5)
    suspend operator fun invoke(locally: Boolean ?= false): SharedFlow<ResultState<List<TennisPlayer>>?> {
        dataFlow.emit(ResultState.Loading)
        if(locally == true){
            coroutineScope {
                val savedData = appPreferenceManager.getSavedDataList<TennisPlayer>(this,PLAYERS)
                if(savedData?.isNotEmpty() == true)
                    dataFlow.emit(ResultState.Success(savedData))
                else
                    dataFlow.emit(ResultState.EmptyData("No Data"))
            }
        }else{
            performNetworkOp(
                networkCall = { tennisRepositoryImp.getTennisPlayerData() },
                onData = {
                    dataFlow.emit(
                        when {
                            it.status && it.data.isNotEmpty() -> {
                                appPreferenceManager.storeData(PLAYERS,it.data)
                                ResultState.Success(it.data, it.message)
                            }
                            it.status && it.data.isEmpty() -> ResultState.EmptyData("No data")
                            !it.status -> ResultState.Error(it.message)
                            else -> ResultState.Error("Something went wrong")
                        }
                    )

                },
                onError = {
                    dataFlow.emit(it.handleCommonException())
                }
            )
        }
         return dataFlow
    }
}