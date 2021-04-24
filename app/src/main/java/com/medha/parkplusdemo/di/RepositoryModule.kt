package com.medha.parkplusdemo.di

import com.medha.parkplusdemo.ParkService
import com.medha.parkplusdemo.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {
    @Provides
    @ActivityRetainedScoped
    fun provideLoginRepository(api: ParkService): LoginRepository {
        return LoginRepository(api)
    }
}