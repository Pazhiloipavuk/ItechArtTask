package com.example.itecharttask.listOfTasks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itecharttask.R
import com.example.itecharttask.descriptionTask.FullDescriptionActivity
import com.example.itecharttask.model.Task
import kotlinx.android.synthetic.main.activity_list_of_tasks.*
import com.example.itecharttask.listOfTasks.settingsRecyclerView.ListAdapter as myAdapter

class ListOfTasksActivity : AppCompatActivity(),
    ListOfTasksView {

    private lateinit var presenter: ListOfTasksPresenter
    private lateinit var myAdapter: myAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_tasks)

        presenter = ListOfTasksPresenter(this)

        initRecyclerview()
        initAllListeners()
    }

    private fun initRecyclerview() {
        vRvTasks.layoutManager = LinearLayoutManager(this)
        myAdapter = myAdapter(presenter.getAllTasks())
        vRvTasks.adapter = myAdapter
        presenter.initRecyclerviewListeners(myAdapter.clickEventTask, myAdapter.clickEventCheckBox)
    }

    private fun initAllListeners() {
        btnFilter.setOnClickListener {
            showAlertDialog()
        }

        btnAdd.setOnClickListener {
            startFullDescriptionActivity(-1)
        }
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(R.string.choosing)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setItems(R.array.filter_array) { _, which  ->
            when (which) {
                0 -> showAlertDialogStatus()
                1 -> showAlertDialogTitle()
                2 -> showAlertDialogDescribe()
                3 -> {
                    presenter.setDefaultSettings()
                    myAdapter.updateListOfItems(presenter.getTasks())
                }
            }
        }
        alertDialogBuilder.show()
    }

    private fun showAlertDialogStatus() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(R.string.choosing)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setItems(R.array.filter_status) { _, which  ->
            when (which) {
                0 -> myAdapter.updateListOfItems(presenter.filterByStatus(true))
                1 -> myAdapter.updateListOfItems(presenter.filterByStatus(false))
            }
        }
        alertDialogBuilder.show()
    }

    private fun showAlertDialogTitle() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(R.string.input_name)
        alertDialogBuilder.setCancelable(true)
        val myEditText = EditText(this)
        alertDialogBuilder.setView(myEditText)
        alertDialogBuilder.setNeutralButton(android.R.string.yes) { _, _ ->
            myAdapter.updateListOfItems(presenter.filterByTitle(myEditText.text.toString()))
        }
        alertDialogBuilder.show()
    }

    private fun showAlertDialogDescribe() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(R.string.input_string)
        alertDialogBuilder.setCancelable(true)
        val myEditText = EditText(this)
        alertDialogBuilder.setView(myEditText)
        alertDialogBuilder.setNeutralButton(android.R.string.yes) { _, _ ->
            myAdapter.updateListOfItems(presenter.filterByDescribe(myEditText.text.toString()))
        }
        alertDialogBuilder.show()
    }

    override fun startFullDescriptionActivity(id: Int) {
        val intent = Intent(this, FullDescriptionActivity::class.java)
        intent.putExtra("task", id)
        startActivityForResult(intent, 1)
    }

    override fun changeTask(oldItem: Task, newItem: Task) {
        myAdapter.changeItem(oldItem, newItem)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1) myAdapter.updateListOfItems(presenter.getAllTasks())
    }
}
