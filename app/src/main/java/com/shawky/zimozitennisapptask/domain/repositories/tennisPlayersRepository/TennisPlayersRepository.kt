package com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository

import com.shawky.zimozitennisapptask.data.models.ResponseModel
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer

interface TennisPlayersRepository {
    suspend fun getTennisPlayerData() : ResponseModel<List<TennisPlayer>>
}