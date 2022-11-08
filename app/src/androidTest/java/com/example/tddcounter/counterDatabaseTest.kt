package com.example.tddcounter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tddcounter.database.Count
import com.example.tddcounter.database.CountDAO
import com.example.tddcounter.database.CountDatabase
import kotlinx.coroutines.flow.count
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CounterDatabaseTest {
    private lateinit var countDAO: CountDAO
    private lateinit var db: CountDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CountDatabase::class.java).build()
        countDAO = db.countDao()
    }

    @After
   // @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    //@Throws(Exception::class)
    suspend fun initialiseCountTest(){
        countDAO.startNewCount()
        assertEquals(countDAO.getCount(), 0)
    }

    @Test
   // @Throws(Exception::class)
    suspend fun countDaoFunctionsTest(){
        countDAO.startNewCount()

        countDAO.updateCount(Count(count = 4))
        assertEquals(countDAO.getCount(), 4)

        countDAO.clearCount()
        assertEquals(countDAO.tableCount(), 0)
    }




}