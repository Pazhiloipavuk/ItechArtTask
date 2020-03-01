package com.example.itecharttask.descriptionTask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.itecharttask.R
import com.example.itecharttask.model.Task
import kotlinx.android.synthetic.main.activity_full_description.*
import kotlinx.android.synthetic.main.recyclerview_item.*

class FullDescriptionActivity : AppCompatActivity(), FullDescriptionView {

    private lateinit var presenter: FullDescriptionPresenter
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_description)

        id = intent.extras?.getInt("task")!!
        println("/////////////////////////////")
        println(id)

        presenter = FullDescriptionPresenter(this)

        initAllListeners()

        fillFieldsWithInformation(id)
    }

    private fun initAllListeners() {
        btnSave.setOnClickListener{
            val task = Task()
            task.id = id
            task.title = edtTitle.text.toString()
            task.date = edtDate.text.toString()
            task.description = edtDescription.text.toString()
            presenter.addTask(task)
        }

        btnDelete.setOnClickListener {
            presenter.deleteTask(id)
        }
    }

    private fun fillFieldsWithInformation(id: Int) {
        if (id == -1) {
            edtTitle.setText("")
            edtDate.setText("")
            edtDescription.setText("")
        }
        else {
            val task = presenter.findTaskById(id)
            edtTitle.setText(task.title)
            edtDate.setText(task.date)
            edtDescription.setText(task.description)
        }
    }
}
