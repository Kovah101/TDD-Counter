package com.example.tddcounter.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.flow.Flow
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CounterDatabaseTest {

    private val inMemorySqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
        SqlDriver.Schema.create(this)
    }

    private val queries = Database(inMemorySqlDriver).CounterTableEntityQueries

    @Test
    fun emptyTest() {
        // check there is no count in the database
        val emptyCount: Flow<Int> = queries.getCount()
        assertEquals(null, emptyCount)
    }

    @Test
    fun createAndUpdateCountTest() {
        // create new count and test its there
        queries.startNewCount()
        assertEquals(0, queries.getCount())

        // add one to the count and test its updated successfully
        queries.updateCount(4)
        assertEquals(4, queries.getCount())

        // clear the count from the database and check
        queries.clearCount()
        assertEquals(null, queries.getCount())

    }
}