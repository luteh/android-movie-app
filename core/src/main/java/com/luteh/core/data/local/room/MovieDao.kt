package com.luteh.core.data.local.room

import androidx.room.*
import com.luteh.core.data.local.entity.MovieDiscoverEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_discover")
    fun getAllMovie(): Flow<List<MovieDiscoverEntity>>

    @Query("SELECT * FROM movie_discover where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieDiscoverEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(tourism: List<MovieDiscoverEntity>)

    @Update
    fun updateFavoriteMovie(tourism: MovieDiscoverEntity)
}