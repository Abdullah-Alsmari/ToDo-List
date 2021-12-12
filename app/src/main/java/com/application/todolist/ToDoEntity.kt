package com.application.todolist

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "todo_table")
data class ToDoEntity(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "subtitle")
    var subtitle: String,
    @ColumnInfo(name = "date")
    var date: Long,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "checked")
    var checked: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    ) : Serializable