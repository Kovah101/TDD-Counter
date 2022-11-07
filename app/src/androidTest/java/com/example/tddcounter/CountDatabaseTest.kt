package com.example.tddcounter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddcounter.database.CountDAO
import com.example.tddcounter.database.CountDatabase
import kotlinx.coroutines.flow.count
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountDatabaseTest {

    private lateinit var countDAO: CountDAO
    private lateinit var db: CountDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CountDatabase::class.java
        ).build()
        countDAO = db.countDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    suspend fun initialiseCountTest() {
        //runCatching {
        countDAO.startNewCount()
        assertEquals(countDAO.getCount().count(), 0)
        //}
    }
}