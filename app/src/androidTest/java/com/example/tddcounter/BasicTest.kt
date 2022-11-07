package com.example.tddcounter

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.tddcounter.database.CountDAO
import com.example.tddcounter.database.CountDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class BasicTest {
    private lateinit var countDAO: CountDAO
    private lateinit var db: CountDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CountDatabase::class.java).allowMainThreadQueries().build()
        countDAO = db.countDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun databaseSimpleTest() =
       runBlocking {
            countDAO.startNewCount()

           val latch  = CountDownLatch(1)
           val job = async(Dispatchers.IO) {
               Assert.assertEquals(countDAO.getCount().count(), 0)
           }
           latch.await()
           job.cancelAndJoin()
        }



}