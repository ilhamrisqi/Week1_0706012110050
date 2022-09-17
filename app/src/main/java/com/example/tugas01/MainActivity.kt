package com.example.tugas01

import Adapter.ListTampilan
import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.tugas01.databinding.ActivityCreateBinding
import com.example.tugas01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CardListener {

    private lateinit var viewBind: ActivityMainBinding
    private val adapter = ListTampilan(GlobalVar.listDataUser,this)

    override fun onCreate(savedInstaceState: Bundle?){
        super.onCreate(savedInstaceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)

        setContentView(viewBind.root)


        setupRecycleView()
        listener()
        addDummyData()
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun listener(){

        viewBind.AddButton.setOnClickListener{
            val myIntent = Intent(this, Create::class.java)
            startActivity(myIntent)
        }

    }


    private fun setupRecycleView(){
        val layoutManager = LinearLayoutManager(this)
        viewBind.AllTampilan.layoutManager = layoutManager // set
        viewBind.AllTampilan.adapter = adapter
    }


    private fun addDummyData(){
        adapter.notifyDataSetChanged()
    }

    override fun onCardClick(position: Int) {
        val myIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }


}