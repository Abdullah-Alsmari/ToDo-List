package com.application.todolist.ui

import android.app.DatePickerDialog
import android.database.DatabaseUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import androidx.navigation.Navigation
import com.application.todolist.R
import com.application.todolist.databinding.FragmentCreateToDoTaskBinding
import com.application.todolist.datasource.ToDoModel
import com.application.todolist.viewmodls.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do_task.*
import java.text.SimpleDateFormat
import java.util.*

class CreateToDoTask : Fragment() {

    private var cal = Calendar.getInstance()
    val toDOViewModel: ToDoViewModel by viewModels()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private var millis: Long =0
    var currentDataPos = 0

    var bind: FragmentCreateToDoTaskBinding? = null

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

        val args = arguments
        args?.let {
            currentDataPos = it.getInt("position")!!

            toDOViewModel.diplay(currentDataPos)
        }
        bind?.apply {

            lifecycleOwner = viewLifecycleOwner
            viewModel = toDOViewModel
        }
        btnDoneNotes.setOnClickListener {
            if (args != null) {
                val title = etTitle.text.toString()
                val subTitle = etsubTitle.text.toString()
                val toDoDate = tvDate.text.toString()
                val desc = etTakenotes.text.toString()
                val toDoModel = ToDoModel(title, subTitle, millis, desc)
                toDOViewModel.createResponse().set(args.getInt("position"), toDoModel)
                Navigation.findNavController(it)
                    .navigate(R.id.action_createToDoTask_to_homeFragmnent)
            } else {
                val title = etTitle.text.toString()
                val subTitle = etsubTitle.text.toString()
                val toDoDate = tvDate.text.toString()
                val desc = etTakenotes.text.toString()
                val toDoModel = ToDoModel(title, subTitle, millis, desc)
                toDOViewModel.createResponse().add(toDoModel)
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
}
