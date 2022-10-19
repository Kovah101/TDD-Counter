package com.example.tddcounter.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CountDAO {


        @Query("SELECT count FROM count_table")
        fun getCount() : Flow<Int>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun updateCount(count: Count)

        @Query("INSERT INTO count_table DEFAULT VALUES")
        suspend fun startNewCount()

        @Query("DELETE FROM count_table")
        suspend fun clearCount()

        @Query("SELECT COUNT(*) FROM count_table")
        suspend fun tableCount(): Int

    }
