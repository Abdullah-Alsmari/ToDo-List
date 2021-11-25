package com.application.todolist.ui

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.todolist.R
import com.application.todolist.adapter.OnCardClicked
import com.application.todolist.adapter.ToDoAdapter
import com.application.todolist.datasource.ToDoModel
import com.application.todolist.viewmodls.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_home_fragmnent.*


class HomeFragmnent : Fragment(R.layout.fragment_home_fragmnent), OnCardClicked {

    //connecting adater with home fragment
    val toDoViewModel: ToDoViewModel by viewModels()
    lateinit var toDoAdapter: ToDoAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()

        btnNewNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragmnent_to_createToDoTask)
        }

        toDoViewModel.getResponse().observe(viewLifecycleOwner, Observer {

            toDoAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecyclerView() {
        toDoAdapter = ToDoAdapter(toDoViewModel.createResponse(), this)
        recyclerViewHomeFragment.layoutManager = LinearLayoutManager(context)
        recyclerViewHomeFragment.adapter = toDoAdapter
    }


    override fun onDelete(toDoModel: ToDoModel) {
        Toast.makeText(requireContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show()
        toDoViewModel.createResponse().remove(toDoModel)
        toDoAdapter.notifyDataSetChanged()
    }

    override fun onEdit(position: Int, toDoModel: ToDoModel) {
        val bundle = Bundle()
        bundle.putInt("position",position)
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragmnent_to_createToDoTask,bundle)
        toDoAdapter.notifyDataSetChanged()
    }

    override fun onCheckboxClicked(toDoModel: ToDoModel,checkBox: CheckBox) {
        toDoModel.isChecked = checkBox.isChecked
        toDoAdapter.notifyDataSetChanged()
    }
}
