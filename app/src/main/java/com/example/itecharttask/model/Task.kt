package com.example.itecharttask.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Task : RealmObject() {
    @PrimaryKey
    var id = 0
    var title = ""
    var date = ""
    var status = false
    var description = ""
}