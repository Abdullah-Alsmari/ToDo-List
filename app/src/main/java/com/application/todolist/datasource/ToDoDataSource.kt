package com.application.todolist.datasource


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import com.application.todolist.dao.ToDoDao


//@Database(entities = [ToDoModel::class], version = 1, exportSchema = false)
//abstract class ToDoDataSource : RoomDatabase() {
//
//    abstract fun notesDao(): ToDoDao
//
//    companion object {
//        @Volatile
//        var INSTANCE: ToDoDataSource? = null
//
//        fun getDatabaseInstances(context: Context): ToDoDataSource? {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val roomDatabaseInstance = Room.databaseBuilder(
//                    context,
//                    ToDoDataSource::class.java,
//                    "Notes"
//                ).allowMainThreadQueries().build()
//                INSTANCE = roomDatabaseInstance
//                return roomDatabaseInstance
//            }
//        }
//    }
//}