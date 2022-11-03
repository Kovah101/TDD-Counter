package com.example.tddcounter.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
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
       // val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CountDatabase::class.java).allowMainThreadQueries().build()
        countDAO = db.countDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test fun basicTest(){
        runBlocking {
            countDAO.startNewCount()
            assertEquals(countDAO.getCount().count(), 0)
        }
    }
}