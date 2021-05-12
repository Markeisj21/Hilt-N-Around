package com.mj.hilt_n_around.di

import com.mj.hilt_n_around.repository.MainRepository
import com.mj.hilt_n_around.retrofit.BlogRetrofit
import com.mj.hilt_n_around.retrofit.NetworkMapper
import com.mj.hilt_n_around.room.BlogDao
import com.mj.hilt_n_around.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, retrofit, cacheMapper, networkMapper)

    }
}