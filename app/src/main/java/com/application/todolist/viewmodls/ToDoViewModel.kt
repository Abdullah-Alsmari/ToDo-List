package com.application.todolist.viewmodls

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.application.todolist.datasource.ToDoModel


private const val TAG = "ViewModelInit"

class ToDoViewModel : ViewModel() {

    private val responseLiveData: MutableLiveData<MutableList<ToDoModel>> = MutableLiveData()

    companion object{
        var responseUpdatedList = mutableListOf<ToDoModel>()
    }
    var title=MutableLiveData<String>()
    var subtitle =MutableLiveData<String>()
    var date =MutableLiveData<String>()
    var note =MutableLiveData<String>()
    init {
        responseLiveData.value = createResponse()
    }


    fun getResponse(): LiveData<MutableList<ToDoModel>> {
        return responseLiveData
    }

    fun createResponse(): MutableList<ToDoModel> {
        return responseUpdatedList
    }
    fun diplay(postion:Int){
        var t= responseUpdatedList[postion]
        title.value=t.title
        subtitle.value=t.subTitle
        note.value=t.notes
        date.value=t.date

    }



}