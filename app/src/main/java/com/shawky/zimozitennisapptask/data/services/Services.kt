package com.shawky.zimozitennisapptask.data.services

import com.shawky.zimozitennisapptask.shared.Constants
import com.shawky.zimozitennisapptask.data.models.ResponseModel
import com.shawky.zimozitennisapptask.domain.models.TennisPlayer
import retrofit2.http.GET

interface Services {
    @GET(Constants.PLAYERS_END_POINT)
    suspend fun getPlayers() : ResponseModel<List<TennisPlayer>>
}