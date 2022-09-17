package com.example.tugas01

import Database.GlobalVar
import Model.User
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.solver.GoalRow
import com.example.tugas01.databinding.ActivityCreateBinding
import com.example.tugas01.databinding.ActivityMainBinding

class Create : AppCompatActivity() {

    private lateinit var viewBind : ActivityCreateBinding
    private lateinit var user: User
    private var position = -1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        GetIntent()
        Listener()
    }


    private fun GetIntent(){
        position = intent.getIntExtra("position", -1 )
        if(position != -1){
            val user = GlobalVar.listDataUser[position]
            Display(user)
        }
    }

    private fun Display(user: User){
        viewBind.NamatextInputLayout2.editText?.setText(user.nama)
        viewBind.JenistextInputLayout3.editText?.setText(user.jenis)
        viewBind.UsiatextInputLayout4.editText?.setText(user.usia)

    }


    private fun Listener(){
        viewBind.SaveButton.setOnClickListener{
            var nama = viewBind.NamatextInputLayout2.editText?.text.toString().trim()
            var jenis = viewBind.JenistextInputLayout3.editText?.text.toString().trim()
            var usia = viewBind.UsiatextInputLayout4.editText?.text.toString().trim()


            user = User(nama,jenis,usia)
            checker()
        }

    }


    //pengecekan
    private fun checker(){
        var isCompleted = true
        //nama
        if(user.nama!!.isEmpty()){
            viewBind.NamatextInputLayout2.error ="Tolong isi Nama dengan benar!"
            isCompleted = false
        }
        else{
            viewBind.NamatextInputLayout2.error = ""
        }
        //jenis
        if(user.jenis!!.isEmpty()){
            viewBind.JenistextInputLayout3.error ="Tolong isi Jenis Hewan dengan benar!"
            isCompleted = false
        }
        else{
            viewBind.JenistextInputLayout3.error =""
        }
        //usia
        if (user.usia!!.isEmpty()){
            viewBind.UsiatextInputLayout4.error ="Tolong isi Usia Hewan dengan benar!"
            isCompleted = false
        }
        else{
            viewBind.UsiatextInputLayout4.error =""
        }


        if (isCompleted){
            if(position== -1){
                GlobalVar.listDataUser.add(user)
            }else{
                GlobalVar.listDataUser[position] = user
            }
            finish()
        }

    }


}