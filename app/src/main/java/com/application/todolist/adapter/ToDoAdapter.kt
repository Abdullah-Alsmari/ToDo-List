package com.application.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.todolist.R
import com.application.todolist.databinding.ItemLayoutBinding
import com.application.todolist.datasource.ToDoModel

class ToDoAdapter(
    private val toDoModelList: List<ToDoModel>,
    private val onCardClicked: OnCardClicked
) : RecyclerView.Adapter<ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val itemLayoutBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout, parent, false
        )
        return ToDoViewHolder(itemLayoutBinding,onCardClicked)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDoModel = toDoModelList[position]
        holder.onBind(toDoModel)
    }

    override fun getItemCount(): Int {
        return toDoModelList.size
    }

}
class ToDoViewHolder(
    private val binding: ItemLayoutBinding,
    private val onCardClicked: OnCardClicked
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(toDoModel: ToDoModel) {
        binding.todoModel = toDoModel // whatever data is coming from the database put it directly into the itemLayout
        binding.position = adapterPosition
        binding.onCardClicked = onCardClicked
    }
}