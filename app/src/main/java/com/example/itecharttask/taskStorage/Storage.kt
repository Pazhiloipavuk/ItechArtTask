package com.example.itecharttask.taskStorage

import com.example.itecharttask.model.Task
import io.realm.Realm
import io.realm.kotlin.delete

class Storage {
    private var realm = Realm.getDefaultInstance()

    fun getAllTasks() = realm.where(Task::class.java).findAll()!!

    fun deleteTask(task: Task) {
        realm.beginTransaction()
        task.deleteFromRealm()
        realm.commitTransaction()
    }

    fun addTask(task: Task) {
        println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")
        println(task.id)
        if (task.id == -1) {
            var id = 0
            if (realm.where(Task::class.java).max("id")?.toInt() != null) {
                id = realm.where(Task::class.java).max("id")!!.toInt() + 1
            }
            println("88888888888888888888888888888")
            println(id)
            task.id = id
        }
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(task)
            realm.commitTransaction()
            println("//////////////////////")
            println("goooood")
        } catch (e: Exception) {
            println(e)
        }
    }

    fun updateTask(task: Task) {
        try {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(task)
            realm.commitTransaction()
        } catch (e: Exception) {
            println(e)
        }
    }

    fun findTask(id: Int) = realm.where(Task::class.java)
                                       .equalTo("id", id)
                                       .findFirst()!!
}