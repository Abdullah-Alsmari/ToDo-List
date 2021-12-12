package com.application.todolist.ui

import android.app.DatePickerDialog
import android.database.DatabaseUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels

import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.application.todolist.MyApplication
import com.application.todolist.R
import com.application.todolist.ToDoEntity
import com.application.todolist.databinding.FragmentCreateToDoTaskBinding
import com.application.todolist.datasource.ToDoModel
import com.application.todolist.viewmodls.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do_task.*
import java.text.SimpleDateFormat
import java.util.*

class CreateToDoTask : Fragment() {

    private var cal = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private var millis: Long = 0
    var currentDataPos = 0
    private var editItem: ToDoEntity? = null


    var bind: FragmentCreateToDoTaskBinding? = null

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val toDoViewModel: ToDoViewModel by activityViewModels {
        ToDoViewModel.ToDoViewModelFactory(
            (activity?.application as MyApplication).database.toDoDao()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bind = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_create_to_do_task,
            container,
            false
        )
        return bind?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        datePickerDialog()
        try {
            val args: CreateToDoTaskArgs by navArgs()
            if (args.data != null) {
                populateDataForEditingTask(args.data!!)
                editItem = args.data
            }
        } catch (e: Exception) {

        }

        bind?.apply {

            lifecycleOwner = viewLifecycleOwner
            viewModel = toDoViewModel
        }
        btnDoneNotes.setOnClickListener {
            if (editItem != null) {
                // editing task
                val title = etTitle.text.toString()
                val subTitle = etsubTitle.text.toString()
                val toDoDate = tvDate.text.toString()
                val desc = etTakenotes.text.toString()
                val toDoModel =
                    ToDoEntity(title, subTitle, millis, desc, editItem!!.checked, editItem!!.id)
//                toDOViewModel.createResponse().set(args.getInt("position"), toDoModel)
                toDoViewModel.updateItem(toDoModel)
                Navigation.findNavController(it)
                    .navigate(R.id.action_createToDoTask_to_homeFragmnent)
            } else {
                val title = etTitle.text.toString()
                val subTitle = etsubTitle.text.toString()
                val toDoDate = tvDate.text.toString()
                val desc = etTakenotes.text.toString()
                val toDoModel = ToDoEntity(title, subTitle, millis, desc)
//                toDOViewModel.createResponse().add(toDoModel)
                toDoViewModel.addNewTask(toDoModel)

                Navigation.findNavController(it)
                    .navigate(R.id.action_createToDoTask_to_homeFragmnent)
            }
        }

        tvDate.setOnClickListener {
            val mYear: Int = cal.get(Calendar.YEAR)
            val mMonth: Int = cal.get(Calendar.MONTH)
            val mDay: Int = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(requireContext(), datePicker, mYear, mMonth, mDay).show()
        }
    }

    private fun datePickerDialog() {
        datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            tvDate.text = dateFormat.format(cal.time).toString()
            millis = cal.timeInMillis

        }
    }

    private fun populateDataForEditingTask(item: ToDoEntity) {
        toDoViewModel.title.value = item.title
        toDoViewModel.subtitle.value = item.subtitle
        toDoViewModel.note.value = item.description
        setDateFromMilliseconds(item.date)

    }

    private fun setDateFromMilliseconds(date: Long) {
        val formatter = SimpleDateFormat("dd/MM/yyyy");
        val dateString = formatter.format(Date(date))
        bind!!.tvDate.setText(dateString)
    }
}
