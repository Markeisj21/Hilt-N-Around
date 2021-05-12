package com.mj.hilt_n_around.repository

import com.mj.hilt_n_around.Model.Blog
import com.mj.hilt_n_around.retrofit.BlogRetrofit
import com.mj.hilt_n_around.retrofit.NetworkMapper
import com.mj.hilt_n_around.room.BlogDao
import com.mj.hilt_n_around.room.CacheMapper
import com.mj.hilt_n_around.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper : NetworkMapper
){
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow{
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try{
            val networkBlogs = blogRetrofit.get()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs){
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        }catch (e:Exception){
                emit(DataState.Error(e))
        }

    }

}