package com.example.tddcounter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddcounter.database.Count
import com.example.tddcounter.database.CountDAO
import com.example.tddcounter.database.CountDatabase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
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
    fun initialiseCountTest() {
        runBlocking {
            countDAO.startNewCount()
            assertEquals(countDAO.getCount().first(), 0)
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertReplaceTest(){
        runBlocking {
            countDAO.updateCount(Count(count = 4))
            assertEquals(countDAO.getCount().first(), 4)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteTest(){
        runBlocking {
            countDAO.updateCount(Count(count = 2))
            countDAO.clearCount()
            assertEquals( countDAO.getCount().first(), null )
        }
    }

    @Test
    @Throws(Exception::class)
    fun tableCountTest(){
        runBlocking {
            countDAO.updateCount(Count(count = 7))
            assertEquals(countDAO.tableCount(), 1)
            countDAO.insertCount(Count(count = 4))
            assertThat(countDAO.tableCount(), `is`(2))
        }
    }

}