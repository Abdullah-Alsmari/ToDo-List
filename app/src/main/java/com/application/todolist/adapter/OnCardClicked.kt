package com.application.todolist.adapter

import android.widget.CheckBox
import com.application.todolist.ToDoEntity
import com.application.todolist.datasource.ToDoModel

interface OnCardClicked {

    fun onDelete(toDoModel: ToDoEntity)

    fun onEdit(position: Int, toDoModel: ToDoEntity)

    fun onCheckboxClicked(toDoModel: ToDoEntity, checkBox: CheckBox)

}