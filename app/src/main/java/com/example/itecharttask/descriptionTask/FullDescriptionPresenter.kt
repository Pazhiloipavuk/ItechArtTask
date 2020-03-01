package com.example.itecharttask.descriptionTask

import com.example.itecharttask.MyApp
import com.example.itecharttask.model.Task
import com.example.itecharttask.taskStorage.Storage
import org.kodein.di.generic.instance

class FullDescriptionPresenter(activity: FullDescriptionView) {
    private val storage: Storage by MyApp.kodein.instance()

    fun addTask(task: Task) {
        storage.addTask(task)
    }

    fun findTaskById(id: Int) = storage.findTask(id)

    fun deleteTask(id: Int) {
        storage.deleteTask(findTaskById(id))
    }
}