package com.example.itecharttask.listOfTasks

import com.example.itecharttask.model.Task

interface ListOfTasksView {
    fun startFullDescriptionActivity(id: Int)
    fun changeTask(oldItem: Task, newItem: Task)
}