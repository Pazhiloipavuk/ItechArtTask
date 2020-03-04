package com.example.itecharttask.descriptionTask

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.itecharttask.R
import com.example.itecharttask.model.Task
import kotlinx.android.synthetic.main.activity_full_description.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class FullDescriptionActivity : AppCompatActivity(), FullDescriptionView {

    private var presenter: FullDescriptionPresenter = FullDescriptionPresenter(this)
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.extras?.getInt("task")!!
        if (id == -1) {
            setContentView(R.layout.activity_add_task)
        }
        else {
            setContentView(R.layout.activity_full_description)
            fillFieldsWithInformation(id)
            initDeleteButtonListener()
        }
        initSaveButtonListener()
    }

    private fun initSaveButtonListener() {
        btnSave.setOnClickListener{
            val task = Task()
            task.id = id
            task.title = edtTitle.text.toString()
            var date = Date()
            val formatter = SimpleDateFormat("dd MMM  HH:mm")
            val dateAndTime: String = formatter.format(date)
            task.date = dateAndTime
            task.description = edtDescription.text.toString()
            presenter.addTask(task)
            showAlertDialog(getString(R.string.save_complete))
        }
    }

    private fun initDeleteButtonListener() {
        btnDelete.setOnClickListener {
                presenter.deleteTask(id)
                showAlertDialog(getString(R.string.delete_complete))
            }
        }

    private fun showAlertDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setNeutralButton(android.R.string.yes) { _, _ ->
            setResult(1)
            finish()
        }
        alertDialogBuilder.show()
    }

    private fun fillFieldsWithInformation(id: Int) {
        val task = presenter.findTaskById(id)
        edtTitle.setText(task.title)
        txtDate.text = task.date
        txtStatus.text = task.status.toString()
        if (task.status) {
            txtStatus.text = "done"
        }
        else {
            txtStatus.text = "undone"
        }
        edtDescription.setText(task.description)
    }
}
