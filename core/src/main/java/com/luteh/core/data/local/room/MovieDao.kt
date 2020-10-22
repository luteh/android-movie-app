package com.luteh.core.data.local.room

import androidx.room.*
import com.luteh.core.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM favorite_movie")
    fun getAllFavoriteMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM favorite_movie WHERE id=:movieId")
    fun getFavoriteMovieById(movieId: Int): Flow<FavoriteMovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Update
    fun updateFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Query("DELETE FROM favorite_movie WHERE id = :movieId")
    suspend fun deleteFavoriteMovieById(movieId: Int)
}