package com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository

import com.shawky.zimozitennisapptask.data.models.ResponseModel
import com.shawky.zimozitennisapptask.data.services.Services
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import javax.inject.Inject

class TennisRepositoryImp @Inject constructor(private val services:Services) : TennisPlayersRepository {
    override suspend fun getTennisPlayerData(): ResponseModel<List<TennisPlayer>> {
        return services.getPlayers()
    }
}