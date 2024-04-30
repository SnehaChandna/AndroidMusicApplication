package com.example.myspotify

import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso
import java.net.URL

class One_song_complete : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer
    var totalTime: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_song_complete)

        val topic=intent.getStringExtra("Title")
        val art=intent.getStringExtra("Artist")
        val img=intent.getStringExtra("pic")
        val gana=intent.getStringExtra("Song")
        val audio= Uri.parse(gana)

        mediaPlayer=MediaPlayer.create(this,audio)
        mediaPlayer.setVolume(1f,1f)
        totalTime=mediaPlayer.duration

        val main_pic=findViewById<ImageView>(R.id.imageView3)
        val main_title=findViewById<TextView>(R.id.textView4)
        val main_artist=findViewById<TextView>(R.id.textView5)
        val main_seekbar=findViewById<SeekBar>(R.id.seekBar)
        val play=findViewById<ImageView>(R.id.imageView6)
        val pause=findViewById<ImageView>(R.id.imageView7)
        val stop=findViewById<ImageView>(R.id.imageView8)

        Picasso.get().load(img).into(main_pic)
        main_artist.text=art
        main_title.text=topic

        main_seekbar.thumb.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)

        main_seekbar.progressDrawable.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)

        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer?.release()
        }

        main_seekbar.max=totalTime
        main_seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser)
                {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            override fun onStopTrackingTouch(seekBar: SeekBar?) { }
        })

        val handler=Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    main_seekbar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                    } catch (exception: java.lang.Exception){
                        main_seekbar.progress=0
                    }
            }
        },0 )
    }
}