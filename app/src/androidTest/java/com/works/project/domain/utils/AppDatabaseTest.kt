package com.works.project.domain.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.works.project.domain.model.TodoEntity
import com.works.project.domain.repository.TodoDao
import kotlinx.coroutines.runBlocking
import org.junit.runner.RunWith
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@RunWith(AndroidJUnit4::class)
@Tag("instrumented")
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: TodoDao

    @BeforeEach
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.todoDao()
    }

    @AfterEach
    fun tearDown() {
        db.close()
    }

    @Test
    fun `database_is_created_and_dao_is_not_null`() {
        assertNotNull(db)
        assertNotNull(dao)
    }

    @Test
    fun `getDatabase_returns_singleton_instance`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val instance1 = AppDatabase.getDatabase(context)
        val instance2 = AppDatabase.getDatabase(context)
        assertEquals(instance1, instance2)
    }

    @Test
    fun `insert_and_get_todo`() = runBlocking {
        val todo = TodoEntity(1, "Test Todo", false, 1)
        dao.insert(todo)
        val allTodos = dao.getAllTodos()
        assertTrue(allTodos.contains(todo))
    }

    @Test
    fun `delete_todo`() = runBlocking {
        val todo = TodoEntity(1, "Test Todo", false, 1)
        dao.insert(todo)
        dao.delete(todo)
        val allTodos = dao.getAllTodos()
        assertTrue(allTodos.isEmpty())
    }
}