package com.example.myspotify

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(var context: Activity, var dataArrayList: List<Data>):
RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var myListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setItemClickListener(listener: onItemClickListener){
        myListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentItem = dataArrayList[position]
        holder.title.text = currentItem.title
        holder.art.text = currentItem.artist.name
        Picasso.get().load(currentItem.artist.picture).into(holder.img);
    }

    override fun getItemCount(): Int {
        return dataArrayList.size
    }


    class MyViewHolder(itemView: View,listener:onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val img: ImageView
        val art: TextView
        val front: ImageView

        init {
            title = itemView.findViewById(R.id.textView2)
            img = itemView.findViewById(R.id.imageView2)
            art = itemView.findViewById(R.id.textView3)
            front = itemView.findViewById(R.id.imageView4)

            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
