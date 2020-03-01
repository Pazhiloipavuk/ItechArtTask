package com.example.itecharttask.listOfTasks

import com.example.itecharttask.MyApp
import com.example.itecharttask.model.Task
import com.example.itecharttask.taskStorage.Storage
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.realm.Realm
import org.kodein.di.generic.instance

class ListOfTasksPresenter(private val activity: ListOfTasksView) {

    private lateinit var listOfAllTasks: ArrayList<Task>
    private lateinit var subscribeTask: Disposable
    private lateinit var subscribeCheckbox: Disposable
    private val storage: Storage by MyApp.kodein.instance()

    fun getAllTasks() : List<Task> {
       listOfAllTasks = ArrayList(storage.getAllTasks())
       return listOfAllTasks
    }

    fun initRecyclerviewListeners(clickTask: Observable<Task>, clickCheckBox: Observable<Task>) {
        subscribeTask = clickTask.subscribe{
            println("-------------------------------")
            println("${it.id}, ${it.description}")
            println("-------------------------------")
            activity.startFullDescriptionActivity(it.id)
        }

        subscribeCheckbox = clickCheckBox.subscribe{
            println("-------------------------------")
            if (it.status) println("false")
            else println("true")
            println("-------------------------------")
        }
    }
}