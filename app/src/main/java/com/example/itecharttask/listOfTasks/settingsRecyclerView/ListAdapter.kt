package com.example.itecharttask.listOfTasks.settingsRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.itecharttask.R
import com.example.itecharttask.model.Task
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ListAdapter(private var items: List<Task>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val clickTask = PublishSubject.create<Task>()
    val clickEventTask: Observable<Task> = clickTask

    private val clickCheckBox = PublishSubject.create<Task>()
    val clickEventCheckBox: Observable<Task> = clickCheckBox

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.recyclerview_item,
                    parent,
                    false
                ),
            clickTask,
            clickCheckBox
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}