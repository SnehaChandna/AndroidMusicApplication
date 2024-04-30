package com.example.myspotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Songs : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)

        recyclerView=findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem")


        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(p0: Call<MyData?>, p1: Response<MyData?>) {
                var responseBody=p1.body()
                val dataList=responseBody?.data!!
                myAdapter=MyAdapter(this@Songs,dataList)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager=LinearLayoutManager(this@Songs)

                myAdapter.setItemClickListener(object :MyAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        // on clicking eac item what action is to be performed
                        val intent= Intent(applicationContext,One_song_complete::class.java)
                        intent.putExtra("Title", dataList[position].title)
                        intent.putExtra("Song", dataList[position].preview)
                        intent.putExtra("Artist",dataList[position].artist.name)
                        intent.putExtra("pic",dataList[position].artist.picture)
                        startActivity(intent)
                    }

                })

            }
            override fun onFailure(p0: Call<MyData?>, p1: Throwable) {
                Log.d("Songs", "onFailure: "+p1.message)
            }
        })

        val user=intent.getStringExtra("name")
        val profile=findViewById<ImageView>(R.id.imageProfile)
        profile.setOnClickListener {
            val intents=Intent(this,Profile::class.java)
            intents.putExtra("Username",user)
            startActivity(intents)
        }
    }
}