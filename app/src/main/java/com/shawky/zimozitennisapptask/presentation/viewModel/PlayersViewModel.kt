package com.shawky.zimozitennisapptask.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawky.zimozitennisapptask.domain.models.ResultState
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import com.shawky.zimozitennisapptask.domain.useCases.GetTennisPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val tennisPlayersUseCase : GetTennisPlayersUseCase
) : ViewModel() {

    private val _tennisPlayers : MutableStateFlow<ResultState<List<TennisPlayer>>?> = MutableStateFlow(null)
    val tennisPlayers = _tennisPlayers

    fun showAllTennisPlayers(): StateFlow<ResultState<List<TennisPlayer>>?> {
        viewModelScope.launch {
            tennisPlayersUseCase.invoke().collect {
                Log.i("DataFlow","Result $it")
                _tennisPlayers.emit(it)
            }
        }
        return _tennisPlayers
    }

}