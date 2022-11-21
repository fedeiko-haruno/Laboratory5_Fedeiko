package com.example.laboratory5_Fedeiko.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.laboratory5_Fedeiko.R
import com.example.laboratory5_Fedeiko.adapter.UserAdapter
import com.example.laboratory5_Fedeiko.model.DataUser
import com.example.laboratory5_Fedeiko.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: UserAdapter
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initMainViewModel()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this) {
            recyclerViewAdapter.listDataUser = it
        }

        val buttonRefreshData = findViewById<FloatingActionButton>(R.id.button_refresh_data)
        buttonRefreshData.setOnClickListener {
            Log.d("log", "refresh data from site")
            initViewModel()
            initMainViewModel()
        }

    }

    private fun initViewModel() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = UserAdapter()
            adapter = recyclerViewAdapter
            Log.d("log", "prepare ViewModel")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer<List<DataUser>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
        Log.d("log", "make Main ViewModel")


        recyclerViewAdapter.onDataUserClickListener = {
            Log.d("log", "click by " + it.id.toString())
            val firstname = it.first_name
            val lastname = it.last_name
            val email = it.email
            val avatar = it.avatar
            val intent = EditActivity
                .newIntentEditDataUser(this, it.id, firstname, lastname, email, avatar)
            startActivity(intent)
        }
    }
}