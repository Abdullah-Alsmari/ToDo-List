package com.application.todolist.datasource




data class ToDoModel(



    val title: String,
    val subTitle: String,
    val date: Long,
    val notes : String,
    var isChecked : Boolean=false

) {

}

