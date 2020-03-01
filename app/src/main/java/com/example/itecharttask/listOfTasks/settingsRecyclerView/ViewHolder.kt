package com.example.itecharttask.listOfTasks.settingsRecyclerView

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.itecharttask.model.Task
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ViewHolder(view: View, private var clickTask: PublishSubject<Task>,
                 private var clickCheckBox: PublishSubject<Task>)
    : RecyclerView.ViewHolder(view) {

    fun bind(item: Task) {
        showTask(item)
        setOnClickListener(item)
    }

    private fun showTask(item: Task) {
        itemView.txtVTitle.text = item.title
        itemView.txtVDate.text = item.date
        itemView.txtVStatus.text = item.status.toString()
        itemView.txtVDescription.text = item.description
    }

    private fun setOnClickListener(item: Task) {
        itemView.linLtTask.setOnClickListener {
            clickTask.onNext(item)
        }

        itemView.checkBoxSwitchStatus.setOnClickListener {
            clickCheckBox.onNext(item)
        }
    }
}