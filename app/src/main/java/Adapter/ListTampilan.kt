package Adapter

import Database.GlobalVar
import Interface.CardListener
import Model.User
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugas01.Create
import com.example.tugas01.MainActivity
import com.example.tugas01.R
import com.example.tugas01.databinding.CardlistBinding
import java.util.ArrayList

class ListTampilan(val listUser: ArrayList<User>, val cardListener: CardListener):
    RecyclerView.Adapter<ListTampilan.viewHolder>(){
    class viewHolder(val itemView: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemView){

        val binding = CardlistBinding.bind(itemView)

        fun setData(data : User){
            binding.NamatextView2.text = data.nama
            binding.JenistextView.text = data.jenis
            binding.UsiatextView4.text = data.usia


            itemView.setOnClickListener{
                cardListener1.onCardClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.cardlist, parent, false)
        return  viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: ListTampilan.viewHolder, position: Int) {
        holder.setData(listUser[position])
        with(holder) {
            binding.EditButton.setOnClickListener(){
                val myIntent = Intent(it.context, Create::class.java)
                myIntent.putExtra("position",position)

                it.context.startActivity(myIntent)

            }

            binding.HapusButton.setOnClickListener(){
                GlobalVar.listDataUser.removeAt(position)
                notifyItemRemoved(position)
                notifyItemChanged(position,itemCount)
            }
        }

    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}


