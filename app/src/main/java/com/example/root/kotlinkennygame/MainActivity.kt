package com.example.root.kotlinkennygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var score: Int = 0

    var imageArray = ArrayList<ImageView>()

    var handler: Handler = Handler()

    var runnable: Runnable = Runnable {  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0

        imageArray = arrayListOf(imageView00,imageView01, imageView02, imageView10,
                imageView11, imageView12, imageView20, imageView21, imageView22)

        hideImage()

        object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                timerTextView.text = "Time: " + p0/1000
            }

            override fun onFinish() {

                timerTextView.text = "Time Up!"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.VISIBLE
                }
            }

        }.start()


    }

    fun hideImage(){

        runnable = object : Runnable{
            override fun run() {

                for (images in imageArray){
                    images.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8-0)

                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)


            }

        }
        handler.post(runnable)

    }

    fun increaseScore(view: View){

        score++
        scoreTextView.text = "Score: " + score
    }
}
