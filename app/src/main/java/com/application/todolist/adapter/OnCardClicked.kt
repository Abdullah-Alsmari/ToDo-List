package com.application.todolist.adapter

import android.widget.CheckBox
import com.application.todolist.datasource.ToDoModel

interface OnCardClicked {

    fun onDelete(toDoModel: ToDoModel)

    fun onEdit(position: Int, toDoModel: ToDoModel)

    fun onCheckboxClicked(toDoModel: ToDoModel, checkBox: CheckBox)

}