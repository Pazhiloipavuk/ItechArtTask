package com.example.itecharttask.listOfTasks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itecharttask.R
import com.example.itecharttask.descriptionTask.FullDescriptionActivity
import kotlinx.android.synthetic.main.activity_list_of_tasks.*
import com.example.itecharttask.listOfTasks.settingsRecyclerView.ListAdapter as myAdapter

class ListOfTasksActivity : AppCompatActivity(),
    ListOfTasksView {

    private lateinit var presenter: ListOfTasksPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_tasks)

        presenter = ListOfTasksPresenter(this)

        initRecyclerview()
        initAllListeners()
    }

    private fun initRecyclerview() {
        vRvTasks.layoutManager = LinearLayoutManager(this)
        val myAdapter = myAdapter(getAllTasks())
        vRvTasks.adapter = myAdapter
        presenter.initRecyclerviewListeners(myAdapter.clickEventTask, myAdapter.clickEventCheckBox)
    }

    private fun initAllListeners() {
        btnFilter.setOnClickListener {

        }

        btnAdd.setOnClickListener {
            startFullDescriptionActivity(-1)
        }
    }

    override fun startFullDescriptionActivity(id: Int) {
        val intent = Intent(this, FullDescriptionActivity::class.java)
        intent.putExtra("task", id)
        startActivity(intent)
    }

    private fun getAllTasks() = presenter.getAllTasks()
}
