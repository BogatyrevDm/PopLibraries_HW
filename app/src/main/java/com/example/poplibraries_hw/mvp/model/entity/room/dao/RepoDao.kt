package com.example.poplibraries_hw.mvp.model.entity.room.dao

import androidx.room.*
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubRepo
import com.example.poplibraries_hw.mvp.model.entity.room.RoomGitHubUser

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repo: RoomGitHubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repos: RoomGitHubRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RoomGitHubRepo>)

    @Update
    fun update(repo: RoomGitHubRepo)

    @Update
    fun update(vararg repos: RoomGitHubRepo)

    @Update
    fun update(repos: List<RoomGitHubRepo>)

    @Delete
    fun delete(repo: RoomGitHubRepo)

    @Delete
    fun delete(vararg repos: RoomGitHubRepo)

    @Delete
    fun delete(repos: List<RoomGitHubRepo>)

    @Query("SELECT * FROM RoomGitHubRepo")
    fun getAll():List<RoomGitHubRepo>

    @Query("SELECT * FROM RoomGitHubRepo WHERE userId = :userId ")
    fun findForUser(userId:String): List<RoomGitHubRepo>
}