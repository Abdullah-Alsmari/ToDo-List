package com.application.todolist.adapter

import com.application.todolist.datasource.ToDoModel

interface OnCardClicked {

    fun onDelete(toDoModel: ToDoModel)

    fun onEdit(position: Int, toDoModel: ToDoModel)

}