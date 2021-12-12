package com.application.todolist

import android.app.Application

class MyApplication : Application() {
    val database: ToDoRoomDatabase by lazy { ToDoRoomDatabase.getDatabase(this) }

}