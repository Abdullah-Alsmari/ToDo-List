package com.application.todolist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(item: ToDoEntity)

    @Update
    suspend fun update(item: ToDoEntity)

    @Delete
    suspend fun delete(item: ToDoEntity)

    @Query("SELECT * from todo_table")
    fun getItem(): LiveData<MutableList<ToDoEntity>>
}