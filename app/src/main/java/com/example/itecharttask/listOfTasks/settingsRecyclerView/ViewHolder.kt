package com.example.itecharttask.listOfTasks.settingsRecyclerView

import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.itecharttask.model.Task
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
        itemView.txtTitleItemRecyclerview.text = item.title
        if (item.status) {
            itemView.checkBoxSwitchStatus.isChecked = true
            itemView.txtTitleItemRecyclerview.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else {
            itemView.checkBoxSwitchStatus.isChecked = false
            itemView.txtTitleItemRecyclerview.paintFlags = 0
        }
    }

    private fun setOnClickListener(item: Task) {
        itemView.txtTitleItemRecyclerview.setOnClickListener {
            clickTask.onNext(item)
        }

        itemView.checkBoxSwitchStatus.setOnClickListener {
            clickCheckBox.onNext(item)
        }
    }
}