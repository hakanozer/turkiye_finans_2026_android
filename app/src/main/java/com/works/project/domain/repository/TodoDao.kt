package com.works.project.domain.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.works.project.domain.model.TodoEntity

@Dao
interface TodoDao {

    // crud dao
    @Insert
    fun insert(todoEntity: TodoEntity) : Long

    // where tid delete
    @Query("DELETE FROM todos WHERE tid = :tid")
    fun deleteByTid(tid: Long)

    // update todo where tid
    @Query("UPDATE todos SET title = :title WHERE tid = :tid")
    fun updateTodoByTid(tid: Long, title: String)

    @Query("SELECT * FROM todos")
    fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todos WHERE uid = :uid")
    fun getTodosByUserId(uid: Int): List<TodoEntity>


}