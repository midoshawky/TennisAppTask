package com.shawky.zimozitennisapptask.data.di

import com.shawky.zimozitennisapptask.data.services.Services
import com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository.TennisPlayersRepository
import com.shawky.zimozitennisapptask.domain.repositories.tennisPlayersRepository.TennisRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @Provides
    @ViewModelScoped
    fun provideTennisPlayersRepo(services: Services) : TennisPlayersRepository {
        return TennisRepositoryImp(services)
    }
}