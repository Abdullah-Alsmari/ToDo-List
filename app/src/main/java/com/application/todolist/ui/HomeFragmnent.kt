package com.application.todolist.ui

import android.app.Application
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.todolist.MyApplication
import com.application.todolist.R
import com.application.todolist.ToDoEntity
import com.application.todolist.adapter.OnCardClicked
import com.application.todolist.adapter.ToDoAdapter
import com.application.todolist.datasource.ToDoModel
import com.application.todolist.viewmodls.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_home_fragmnent.*


class HomeFragmnent : Fragment(R.layout.fragment_home_fragmnent), OnCardClicked {

    //connecting adater with home fragment
//    val toDoViewModel: ToDoViewModel by viewModels()
    lateinit var toDoAdapter: ToDoAdapter

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val viewModel: ToDoViewModel by activityViewModels {
        ToDoViewModel.ToDoViewModelFactory(
            (activity?.application as MyApplication).database.toDoDao()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRecyclerView()

        btnNewNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragmnent_to_createToDoTask)
        }
        var list = mutableListOf<ToDoEntity>()
        toDoAdapter = ToDoAdapter(list, this)


        viewModel.allItems.observe(this.viewLifecycleOwner) { items ->
            items.let {
//                toDoAdapter.notifyDataSetChanged()
                toDoAdapter = ToDoAdapter(it, this)
                recyclerViewHomeFragment.adapter = toDoAdapter


            }
        }
        /* toDoViewModel.getResponse().observe(viewLifecycleOwner, Observer {

             toDoAdapter.notifyDataSetChanged()
         })*/
    }

    private fun setUpRecyclerView() {
        recyclerViewHomeFragment.layoutManager = LinearLayoutManager(context)
    }


    override fun onDelete(toDoModel: ToDoEntity) {
        Toast.makeText(requireContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show()
        viewModel.deleteItem(toDoModel)
        toDoAdapter.notifyDataSetChanged()
    }

    override fun onEdit(position: Int, toDoModel: ToDoEntity) {
        val action = HomeFragmnentDirections.actionHomeFragmnentToCreateToDoTask(toDoModel)
        findNavController().navigate(action)
//        toDoAdapter.notifyDataSetChanged()
    }

    override fun onCheckboxClicked(toDoModel: ToDoEntity, checkBox: CheckBox) {
        toDoModel.checked = checkBox.isChecked
        toDoAdapter.notifyDataSetChanged()
    }
}
