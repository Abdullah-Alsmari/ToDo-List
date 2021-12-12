package com.application.todolist.viewmodls

import androidx.lifecycle.*
import com.application.todolist.ToDoDao
import com.application.todolist.ToDoEntity
import com.application.todolist.datasource.ToDoModel
import kotlinx.coroutines.launch


private const val TAG = "ViewModelInit"

class ToDoViewModel(val toDoDao: ToDoDao) : ViewModel() {

    private val responseLiveData: MutableLiveData<MutableList<ToDoModel>> = MutableLiveData()

    companion object {
        var responseUpdatedList = mutableListOf<ToDoModel>()
    }
    // Cache all items form the database using LiveData.
    val allItems: LiveData<MutableList<ToDoEntity>> = toDoDao.getItem()

    var title = MutableLiveData<String>()
    var subtitle = MutableLiveData<String>()
    var date = MutableLiveData<String>()
    var note = MutableLiveData<String>()

    init {
        responseLiveData.value = createResponse()
    }


    fun getResponse(): LiveData<MutableList<ToDoModel>> {
        return responseLiveData
    }

    fun createResponse(): MutableList<ToDoModel> {
        return responseUpdatedList
    }

    fun diplay(postion: Int) {
        var t = responseUpdatedList[postion]
        title.value = t.title
        subtitle.value = t.subTitle
        note.value = t.notes
        date.value = t.date.toString()

    }


    fun addNewTask(toDoEntity: ToDoEntity) {
        insertItem(toDoEntity)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(item: ToDoEntity) {
        viewModelScope.launch {
            toDoDao.insert(item)
        }
    }

    /**
     * Retrieve an item from the repository.
     */
    fun retrieveItem(id: Int): LiveData<MutableList<ToDoEntity>> {
        return toDoDao.getItem()
    }

    fun updateItem(item: ToDoEntity) {
        viewModelScope.launch {
            toDoDao.update(item)

        }
    }

    fun deleteItem(item: ToDoEntity) {
        viewModelScope.launch {
            toDoDao.delete(item)

        }
    }

    class ToDoViewModelFactory(private val itemDao: ToDoDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ToDoViewModel(itemDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }
}