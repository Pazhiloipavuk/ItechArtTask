package com.example.itecharttask.listOfTasks

import com.example.itecharttask.MyApp
import com.example.itecharttask.model.Task
import com.example.itecharttask.taskStorage.Storage
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.realm.Realm
import org.kodein.di.generic.instance

class ListOfTasksPresenter(private val activity: ListOfTasksView) {

    private lateinit var listOfTasks: ArrayList<Task>
    private lateinit var subscribeTask: Disposable
    private lateinit var subscribeCheckbox: Disposable
    private val storage: Storage by MyApp.kodein.instance()
    private var statusFilter: Boolean? = null
    private var titleFilter = ""
    private var descriptionFilter = ""

    fun getAllTasks() : List<Task> {
       listOfTasks = ArrayList(storage.getAllTasks())
       return listOfTasks
    }

    fun initRecyclerviewListeners(clickTask: Observable<Task>, clickCheckBox: Observable<Task>) {
        subscribeTask = clickTask.subscribe{
            activity.startFullDescriptionActivity(it.id)
        }

        subscribeCheckbox = clickCheckBox.subscribe{ task ->
            val newTask = Task()
            val index = listOfTasks.indexOf(listOfTasks.find { it.id == task.id })
            newTask.id = task.id
            newTask.title = task.title
            newTask.date = task.date
            newTask.description = task.description
            newTask.status = !task.status
            storage.updateTask(newTask)
            listOfTasks[index] = newTask
            activity.changeTask(task, newTask)
        }
    }

    fun filterByStatus(status: Boolean) : List<Task> {
        statusFilter = status
        var listOfTasksAfterFilter = listOfTasks.filter { it.status ==  status}
        if (titleFilter.isNotEmpty()) listOfTasksAfterFilter = listOfTasksAfterFilter
                                                               .filter { it.title.contains(titleFilter, true) }
        if (descriptionFilter.isNotEmpty()) listOfTasksAfterFilter = listOfTasksAfterFilter
                                                                     .filter { it.description.contains(descriptionFilter, true) }
        return listOfTasksAfterFilter
    }

    fun setDefaultSettings() {
        statusFilter = null
        titleFilter = ""
        descriptionFilter = ""
    }

    fun filterByTitle(title: String) : List<Task> {
        titleFilter = title
        var listOfTasksAfterFilter = listOfTasks.filter { it.title.contains(title, true) }
        if (statusFilter != null) listOfTasksAfterFilter = listOfTasksAfterFilter.filter { it.status ==  statusFilter}
        if (descriptionFilter.isNotEmpty()) listOfTasksAfterFilter = listOfTasksAfterFilter.filter { it.description.contains(descriptionFilter, true) }
        return listOfTasksAfterFilter
    }

    fun filterByDescribe(description: String) : List<Task> {
        descriptionFilter = description
        var listOfTasksAfterFilter = listOfTasks.filter { it.description.contains(description, true) }
        if (statusFilter != null) listOfTasksAfterFilter = listOfTasksAfterFilter.filter { it.status ==  statusFilter}
        if (titleFilter.isNotEmpty()) listOfTasksAfterFilter = listOfTasksAfterFilter.filter { it.title.contains(titleFilter, true) }
        return listOfTasksAfterFilter
    }

    fun getTasks() = listOfTasks
}